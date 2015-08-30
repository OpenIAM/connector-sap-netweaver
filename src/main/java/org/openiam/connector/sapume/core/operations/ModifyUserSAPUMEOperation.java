package org.openiam.connector.sapume.core.operations;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.connector.sapume.core.beans.AttributeBean;
import org.openiam.connector.sapume.core.beans.ProvisionSAPUMEUserBean;
import org.openiam.connector.sapume.core.common.DateUtils;
import org.openiam.connector.sapume.core.common.SAPUMEConfiguration;
import org.openiam.connector.sapume.core.common.SAPUMEConnectorException;
import org.openiam.connector.sapume.core.common.SAPUMEConstants;
import org.openiam.connector.sapume.core.common.SAPUMEUtil;
import org.openiam.connector.sapume.core.connection.SAPUMEConnection;
import org.openiam.connector.sapume.core.security.SecuredString;
import org.openspml.message.ModifyRequest;
import org.openspml.message.SpmlResponse;

public class ModifyUserSAPUMEOperation extends AbstractSAPUMEOperation
{
	/*************************************************************
     **********          OBJETOS INTERNOS              ***********
     *************************************************************/
	
	private static Log logger = LogFactory.getLog(ModifyUserSAPUMEOperation.class);
	
	
	/*************************************************************
     ***********             CONSTRUCTORES             *********** 
     *************************************************************/
	
	public ModifyUserSAPUMEOperation(SAPUMEConnection conn, SAPUMEConfiguration config)
	{
		super(conn, config);
		logger.info("ModifyUserSAPUMEOperation class constrtuctor");
	}
	
	
	/*************************************************************
     ***********           MÉTODOS PÚBLICOS            *********** 
     *************************************************************/
	
	public void execute(ProvisionSAPUMEUserBean provisioningUser, boolean manageMembershipRequired) throws SAPUMEConnectorException
	{		
		try
		{
			logger.info("Start ModifyUserSAPUMEOperation.execute() method");
			
			if (provisioningUser != null && provisioningUser.getUserID() != null && !provisioningUser.getUserID().equals(""))
			{
				String userID = provisioningUser.getUserID();
				logger.info("Update SAPUME account for user: " + userID);
				logger.info("Param manageMembershipRequired: " + manageMembershipRequired);
		
				ModifyRequest spmlModifyRequest = new ModifyRequest();
				String sapumeUserUniqueId = SAPUMEUtil.getUserUniqueID(userID, this.sapConnection, this.sapConfiguration);
				logger.info("SAMUME UniqueID obtained for user: " + sapumeUserUniqueId);
				if (sapumeUserUniqueId != null && !sapumeUserUniqueId.equals(""))
				{
					spmlModifyRequest.setIdentifier(sapumeUserUniqueId);
					logger.debug("SAMUME UniqueID set in SPML ModifyRequest");
				
/* TODO				
					// Si es una rename del identificador del usuario, establecemos como UserID el id anterior, para que así se pueda hacer
					// el update, ya que si no, el usuairo con el nuevo ID no lo encontraría para acutlaizarlo, ya que aún no existirá en SAP
					if (provisioningUser.isFlagRename())
					{
						logger.info("Rename flag detected");
						userID = provisioningUser.getOldUserID();
						logger.info("Old UserID obtanined from bean: " + userID + ". This will be the user that now exists in SAP. Rename will be executed after update user atts...");
					}
*/				
									
					if (provisioningUser.isFlagEnable())
					{
						logger.info("Enable flag detected");
						SAPUMEUtil.updateModifyRequestForEnableUser(spmlModifyRequest, this.sapConfiguration);
						logger.info("SPML ModifyRequest was updated for Enable user account sucessfully");
						
					} else if (provisioningUser.isFlagDisable()) {
						
						logger.info("Disable flag detected");
						SAPUMEUtil.updateModifyRequestForDisableUser(spmlModifyRequest, this.sapConfiguration);
						logger.info("SPML ModifyRequest was updated for Disable user account sucessfully");
					}
			
					SecuredString realUserPwd = null;
					boolean changePwdFlag = false;
					
					if (provisioningUser.getAttsList() != null && provisioningUser.getAttsList().size() > 0)
					{
						for (AttributeBean attribute : provisioningUser.getAttsList())
						{
							String attName = attribute.getAttName();
							logger.debug("Start process for attribute " + attName);

							/* TODO
								// Estos campos en teoría no llegan nunca hasta aquí, ya que son procesados, tratados y eliminados por la clase org.openiam.connector.sapume.request.RequestReader
								// El USERNAME si que llegaría, pero no lo tenemos que procesar, por eso se salta
								if (!!attName.equals(SAPConstants.SAP_FIELD_OLD_IDENTITY))
								{*/
							if (!attName.equals(SAPUMEConstants.SAPUME_FIELD_LOGONNAME) && !attName.equals(SAPUMEConstants.SAPUME_FIELD_ASSIGNEDGROUPS) && 
								!attName.equals(SAPUMEConstants.SAPUME_FIELD_ASSIGNEDROLES))
							{
								if (attName.equals(SAPUMEConstants.SAPUME_FIELD_VALIDTO) || attName.equals(SAPUMEConstants.SAPUME_FIELD_VALIDFROM))
								{
									logger.debug("Field " + attName + " detected");
									String dateValue = (String)attribute.getAttValue();
									logger.debug("dateValue: " + dateValue);
	
									String strDateValue = "";
									if (dateValue != null && !dateValue.equals(""))
									{									
										// Comprobamos que sea un valor de fecha válido construyendo un Date
										Date date = DateUtils.getDateFromStr(dateValue, sapConfiguration.getSapDateMask());
	
										// Actualizamos el valor del atributo con el formato de fecha esperado por SAPUME
										strDateValue = DateUtils.getStrFromDate(date, SAPUMEUtil.SAPUME_DATE_MASK);
									}
									logger.debug("Final dateValue: " + strDateValue);
	
									spmlModifyRequest.addModification(attName, strDateValue);
	
								} else if (attName.equals(SAPUMEConstants.SAPUME_FIELD_PASSWORD)) {
	
									logger.debug("Field " + SAPUMEConstants.SAPUME_FIELD_PASSWORD + " detected");
	
									if (!this.sapConfiguration.isChangePasswordAtNextLogon() && 
										!SAPUMEUtil.checkIfUserIsTechnical(userID, this.sapConnection, this.sapConfiguration))
									{
										// La contraseña establecida para el usuario se guarda y se le asignará tras la modificación
										realUserPwd = (attribute.getAttValue() instanceof SecuredString) ?
												  	  (SecuredString)attribute.getAttValue() : new SecuredString(attribute.getAttValue().toString().toCharArray());
										changePwdFlag = true;
										// Le asignamos inicialmente al usuario primero la contraseña dummy
										logger.debug("fieldValue: " + "xxxxx");
										spmlModifyRequest.addModification(attName, String.valueOf(this.sapConfiguration.getDummyPassword().getClearValue()));
	
									} else {
										logger.debug("fieldValue: " + "xxxxx");
										spmlModifyRequest.addModification(attName, 
												(attribute.getAttValue() instanceof SecuredString) ? String.valueOf(((SecuredString)attribute.getAttValue()).getClearValue()) 
																								   : attribute.getAttValue());
										changePwdFlag = false;
									}

								} else if (attName.equals(SAPUMEConstants.SAPUME_FIELD_ISLOCKED)) {
									
									logger.debug("Field " + SAPUMEConstants.SAPUME_FIELD_ISLOCKED + " detected");
									if (attribute.getAttValue() != null && !attribute.getAttValue().toString().equals(""))
									{
										String fieldValue = "";
										if (attribute.getAttValue().toString().equalsIgnoreCase(SAPUMEConstants.SAPUME_VALUE_USERLOCK_YES))
											fieldValue = "true";
										else if (attribute.getAttValue().toString().equalsIgnoreCase(SAPUMEConstants.SAPUME_VALUE_USERLOCK_NO))
											fieldValue = "false";
										
										logger.debug("fieldValue: " + fieldValue);
										spmlModifyRequest.addModification(attName, fieldValue);
									}
																		
								} else {
	
									logger.debug("Normal attribute");
									String fieldValue = (String)attribute.getAttValue();
									logger.debug("fieldValue: " + fieldValue);
									spmlModifyRequest.addModification(attName, fieldValue);
								}
	
								logger.info("Attribute '" + attName + "' was processed");

							} else {
								logger.debug("Skip " + attName + " attribute for create user operation");
							}
						}

						logger.info("Ends attributes process.");
						
						// Gestionamos las relaciones de grupos sólo si aplica
						if (manageMembershipRequired)
						{
							logger.info("Membership relations for user will be managed and added to Modify request");
							SAPUMEUtil.updateModifyRequestForMembership(provisioningUser, spmlModifyRequest, this.sapConnection, this.sapConfiguration);
							logger.info("Membership relations was managed successfully for user");
							
						} else {
							logger.info("Manage membership relations for user is not required");
						}
						
					} else {
						logger.warn("ProvisionSAPUserBean has a empty AttsList");
					}
					
					if (spmlModifyRequest.getModifications() != null && spmlModifyRequest.getModifications().size() > 0)
					{
						logger.info("SPML request will be send to SAPUME...");
						SpmlResponse spmlResponse = this.sapConnection.sendSPMLRequest(spmlModifyRequest, this.sapConfiguration.isTraceSPMLRequest());
						logger.info("SPML response received from SAPUME");

						SAPUMEUtil.validateSAPUMESpmlResponse(spmlResponse, sapumeUserUniqueId);
						logger.info("SPML response passed failure validation");

						logger.info("User " + userID + " has been updated in SAPUME target system");
						
					} else {
						logger.warn("SPML ModifyRequest modifications is null or empty, so there are not modifications to do.");
					}
					
/* TODO
					if (provisioningUser.isFlagRename())
					{
						logger.info("Rename flag detected and pending to execute");
						userID = SAPUtil.renameSAPAccount(userID, provisioningUser.getUserID(), pwdInfo, this.sapConnection, this.sapConfiguration);
						logger.info("Rename user done!");
					}
*/					
					if (changePwdFlag && !this.sapConfiguration.isChangePasswordAtNextLogon())
					{
						// Hacemos un cambio de contraseña. Si tenemos el isChangePasswordAtNextLogon desctivado, el usuario habra sido creado
						// con la contraseña dummy, por lo que se la cambiaremos por la contraseña inicial que se guardó
						logger.info("ChangePasswordAtNextLogon property is false --> Make a change password with the user password (instead dummy password)");
						SAPUMEUtil.changeUserPassword(sapumeUserUniqueId, this.sapConfiguration.getDummyPassword(), realUserPwd, this.sapConnection, this.sapConfiguration);
						logger.info("Password was changed to user succesfully");
						
					}  else {
						logger.debug("Password manage is NOT required");
					}
					
				} else {
					logger.error("User to be updated '" + userID + "' does not exist in SAPUME target system");
					throw new SAPUMEConnectorException("User to be updated '" + userID + "' does not exist in SAPUME target system");
				}

			} else {
				logger.error("ProvisionSAPUMEUserBean object passed is null or empty");
				throw new SAPUMEConnectorException("ProvisionSAPUMEUserBean object passed is null or empty");
			}

		} catch (SAPUMEConnectorException e) {
			logger.error("SAPUMEConnectorException handled in ModifyUserSAPUMEOperation.execute() method: " + e.getMessage());
			throw e;
			
		} catch (Exception e) {
			logger.error("Generic error ocurred in ModifyUserSAPUMEOperation.execute() method: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error ocurred in ModifyUserSAPUMEOperation.execute() method: " + e.getMessage(), e);
		}
	}
	
}
