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
						
					if (provisioningUser.isFlagEnable())
					{
						logger.info("Enable flag detected");
						SAPUMEUtil.updateModifyRequestForEnableUser(spmlModifyRequest, this.sapConfiguration);
						logger.info("SPML ModifyRequest was updated for Enable user account sucessfully");
						
					} else if (provisioningUser.isFlagDisable()) {
						
						logger.info("Disable flag detected");
						SAPUMEUtil.updateModifyRequestForDisableUser(userID, spmlModifyRequest, this.sapConnection, this.sapConfiguration);
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

							// Estos campos en teoría no llegan nunca hasta aquí, ya que son procesados, tratados y eliminados por la clase org.openiam.connector.sapume.request.RequestReader
							// El logonname si que llegaría, pero no lo tenemos que procesar, por eso se salta
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
									
									// Establecemos primero una contraseña dummy para luego cambiársela y dejar la cuenta operativa si:
									// 	> Es un cambio de contraseña NO administrativo (operación SET_PASSWORD lanzada por un usuario nominal)
									//  ó
									//	> Es un cambio de contraseña administrativo (operación RESET_PASSWORD realizada por admin ded GID),
									//    y tiene desactivado el flag ChangePasswordAtNextLogon, y el usuario objeto de la operación no es Technical en SAPUME
									if ((!provisioningUser.isAdministrativePwdChange()) || 
										(provisioningUser.isAdministrativePwdChange() && !this.sapConfiguration.isChangePasswordAtNextLogon() && !SAPUMEUtil.checkIfUserIsTechnical(userID, this.sapConnection, this.sapConfiguration)))
									{
										// La contraseña establecida para el usuario se guarda y se le asignará tras la modificación
										realUserPwd = (attribute.getAttValue() instanceof SecuredString) ?
												  	  (SecuredString)attribute.getAttValue() : new SecuredString(attribute.getAttValue().toString().toCharArray());
										changePwdFlag = true;
										// Le asignamos inicialmente al usuario primero la contraseña dummy
										logger.debug("dummy password assigned to fieldValue");
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
								
					if (changePwdFlag)
					{
						// Tenemos que hacer un cambio de contraseña
						if (provisioningUser.isAdministrativePwdChange() && !this.sapConfiguration.isChangePasswordAtNextLogon())
						{
							// Estamos ante un resetPassword, un cambio de contraseña administrativo 
							// Si tenemos el isChangePasswordAtNextLogon desactivado, el usuario habrá sido creado con la contraseña dummy,
							// por lo que se la cambiaremos por la contraseña inicial que se guardó
							logger.info("Is administrative Password change (its a resetPassword requested by an administrator) and ChangePasswordAtNextLogon property is false --> Make a change password with the user password (instead dummy password)");
							SAPUMEUtil.changeUserPassword(sapumeUserUniqueId, this.sapConfiguration.getDummyPassword(), realUserPwd, this.sapConnection, this.sapConfiguration);
							logger.info("Password was changed to user succesfully");
							
						} else if (!provisioningUser.isAdministrativePwdChange()) {
							
							// Estamos ante un setPassword, un cambio de contraseña directo de un propio usuario
							// No hace falta mirar isChangePasswordAtNextLogon ni nada, ya que se considera que la cuenta de usuario
							// ha de quedar operativa tras un cambio de contraseña provocado por el propio usuario
							logger.info("Password change is NOT administrative (its a setPassword requested by a single nominal user) --> Make a change password with the user password (instead dummy password)");
							SAPUMEUtil.changeUserPassword(sapumeUserUniqueId, this.sapConfiguration.getDummyPassword(), realUserPwd, this.sapConnection, this.sapConfiguration);
							logger.info("Password was changed to user succesfully");
							
						} else {
							logger.debug("Passwords flags logics determine thats password change is NOT required");
						}
												
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
