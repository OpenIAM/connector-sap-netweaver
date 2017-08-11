package org.openiam.connector.sapume.core.operations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.openspml.message.AddRequest;
import org.openspml.message.AddResponse;
import org.openspml.message.Attribute;
import org.openspml.message.SpmlResponse;

public class CreateUserSAPUMEOperation extends AbstractSAPUMEOperation
{
	/*************************************************************
     **********          OBJETOS INTERNOS              ***********
     *************************************************************/
	
	private static Log logger = LogFactory.getLog(CreateUserSAPUMEOperation.class);
	
	
	/*************************************************************
     ***********             CONSTRUCTORES             *********** 
     *************************************************************/
	
	public CreateUserSAPUMEOperation(SAPUMEConnection conn, SAPUMEConfiguration config)
	{
		super(conn, config);
		logger.info("CreateUserSAPUMEOperation class constrtuctor");
	}
	
	
	/*************************************************************
     ***********           MÉTODOS PÚBLICOS            *********** 
     *************************************************************/
	
	public void execute(ProvisionSAPUMEUserBean provisioningUser) throws SAPUMEConnectorException
	{		
		try
		{
			logger.info("Start CreateUserSAPUMEOperation.execute() method");
			
			if (provisioningUser != null && provisioningUser.getUserID() != null && !provisioningUser.getUserID().equals(""))
			{
				String userID = provisioningUser.getUserID();
				logger.info("Create SAPUME account with userID: " + userID);

				if (!SAPUMEUtil.checkIfUserExist(userID, this.sapConnection, this.sapConfiguration))
				{
					if (provisioningUser.getAttsList() != null && provisioningUser.getAttsList().size() > 0)
					{
						SecuredString realUserPwd = null;
						boolean changePwdFlag = false;
						List<Attribute> listSpmlAtts = new ArrayList<Attribute>();
						for (AttributeBean attribute : provisioningUser.getAttsList())
						{
							Attribute attSpml = new Attribute();

							String attName = attribute.getAttName();
							logger.debug("Start process for attribute " + attName);


							// Estos campos en teoría no llegan nunca hasta aquí, ya que son procesados, tratados y eliminados por la clase org.openiam.connector.sapume.request.RequestReader
							if (!attName.equals(SAPUMEConstants.SAPUME_FIELD_ASSIGNEDGROUPS) && !attName.equals(SAPUMEConstants.SAPUME_FIELD_ASSIGNEDROLES))
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

									attSpml.setName(attName);
									attSpml.setValue(strDateValue);

								} else if (attName.equals(SAPUMEConstants.SAPUME_FIELD_PASSWORD)) {

									logger.debug("Field " + SAPUMEConstants.SAPUME_FIELD_PASSWORD + " detected");

									attSpml.setName(attName);

									// La operación de creación de un usuario es considerada como una operación administrativa, por eso aqui no hace falta consultar
									// el flag isAdministrativePwdChange como se hace en la clase ModifyUserSAPUMEOperation
									if (!this.sapConfiguration.isChangePasswordAtNextLogon())
									{
										// La contraseña establecida para el usuario se guarda y se le asignará tras la creación
										realUserPwd = (attribute.getAttValue() instanceof SecuredString) ?
													  (SecuredString)attribute.getAttValue() : new SecuredString(attribute.getAttValue().toString().toCharArray());
										// Le asignamos inicialmente al usuario primero la contraseña dummy
										attSpml.setValue(String.valueOf(this.sapConfiguration.getDummyPassword().getClearValue()));
										changePwdFlag = true;
										logger.debug("fieldValue: " + "xxxxx");

									} else {
										
										attSpml.setValue((attribute.getAttValue() instanceof SecuredString) ?
												String.valueOf(((SecuredString)attribute.getAttValue()).getClearValue()) : attribute.getAttValue());
										changePwdFlag = false;
										logger.debug("fieldValue: " + "xxxxx");
									}

								} else if (attName.equals(SAPUMEConstants.SAPUME_FIELD_ISLOCKED)) {
									
									logger.debug("Field " + attName + " detected");
									Object isLockedValue = attribute.getAttValue();
									logger.debug("isLockedValue: " + isLockedValue);
									
									String fieldValue = "";
									if (isLockedValue != null && !isLockedValue.toString().equals(""))
									{
										
										if (attribute.getAttValue().toString().equalsIgnoreCase(SAPUMEConstants.SAPUME_VALUE_USERLOCK_YES))
											fieldValue = "true";
										else if (attribute.getAttValue().toString().equalsIgnoreCase(SAPUMEConstants.SAPUME_VALUE_USERLOCK_NO))
											fieldValue = "false";
									}
									
									logger.debug("Final dateValue: " + fieldValue);
									attSpml.setName(attName);
									attSpml.setValue(fieldValue);
											
								} else {

									logger.debug("Normal attribute");
									attSpml.setName(attName);
									String fieldValue = (String)attribute.getAttValue();
									logger.debug("fieldValue: " + fieldValue);
									attSpml.setValue(fieldValue);
								}

								if (attSpml.getName() != null && !attSpml.getName().equals(""))
								{
									listSpmlAtts.add(attSpml);
									logger.info("SPML attribute was created and added to List");
								}

								logger.info("Attribute '" + attName + "' was processed");
								
							} else {
								logger.debug("Skip " + attName + " attribute for create user operation");
							}
						}

						logger.info("Ends attributes process.");
						
						// Enviamos el mensaje SPML
						AddRequest spmlAddRequest = new AddRequest();
						spmlAddRequest.setAttributes(listSpmlAtts);
						spmlAddRequest.setObjectClass(SAPUMEConstants.SAPUME_TARGETOBJCLASS_USER);
						logger.info("SPML AddRequest object created for " + SAPUMEConstants.SAPUME_TARGETOBJCLASS_USER);

						logger.info("SPML request will be send to SAPUME...");
						SpmlResponse spmlResponse = this.sapConnection.sendSPMLRequest(spmlAddRequest, this.sapConfiguration.isTraceSPMLRequest());
						logger.info("SPML response received from SAPUME");

						SAPUMEUtil.validateSAPUMESpmlResponse(spmlResponse, userID);
						logger.info("SPML response passed failure validation");

						logger.info("User " + userID + " has been created in SAPUME target system");
						
						String sapumeUniqueID = ((AddResponse)spmlResponse).getIdentifierString();
						logger.info("SAPUME UniqueID for this user is: " + sapumeUniqueID);
						if (sapumeUniqueID == null || sapumeUniqueID.equals(""))
						{
							logger.error("Cannot obtain SAPUME UniqueID from SPML AddResponse");
							throw new SAPUMEConnectorException("Cannot obtain SAPUME UniqueID from SPML AddResponse");
						}
						
						boolean deleteUser = false;
						try
						{
							// La operación de creación de un usuario es considerada como una operación administrativa, por eso aqui no hace falta consultar
							// el flag isAdministrativePwdChange como se hace en la clase ModifyUserSAPUMEOperation
							if (changePwdFlag && !this.sapConfiguration.isChangePasswordAtNextLogon())
							{
								// Hacemos un cambio de contraseña. Si tenemos el isChangePasswordAtNextLogon desctivado, el usuario habra sido creado
								// con la contraseña dummy, por lo que se la cambiaremos por la contraseña inicial que se guardó
								logger.info("ChangePasswordAtNextLogon property is false --> Make a change password with the user password (instead dummy password)");
								SAPUMEUtil.changeUserPassword(sapumeUniqueID, this.sapConfiguration.getDummyPassword(), realUserPwd, this.sapConnection, this.sapConfiguration);
								logger.info("Password was changed to user succesfully");
								
							}  else {
								logger.debug("Password manage is NOT required");
							}

							if(provisioningUser.isFlagMngGroups() || provisioningUser.isFlagMngRoles())
							{
								logger.info("Membership relations for user will be managed");
								SAPUMEUtil.manageMembershipForUser(sapumeUniqueID, provisioningUser, this.sapConnection, this.sapConfiguration);
								logger.info("Membership relations was managed successfully for user");
								
							} else {
								logger.info("Membership relations is not required to manage for this user");
							}
							
						} catch (SAPUMEConnectorException e) {
							logger.error("SAPUMEConnectorException handled in CreateUserSAPUMEOperation.execute() method after user creation: " + e.getMessage());
							deleteUser = true;
							throw e;

						} catch (Exception e) {
							logger.error("Generic error ocurred in CreateUserSAPUMEOperation.execute() method after user creation: " + e.getMessage(), e);
							deleteUser = true;
							throw new SAPUMEConnectorException("Generic error ocurred in CreateUserSAPUMEOperation.execute() method: " + e.getMessage(), e);

						} finally {
							if (deleteUser)
							{
								try {
									logger.debug("User recently created must be deleted in SAPUME...");
									SAPUMEUtil.deleteUser(sapumeUniqueID, this.sapConnection, this.sapConfiguration);
									logger.debug("User has been deleted sucessfully");
								} catch (Exception e) {
									logger.error("Error at deleting user recently created: " + e.getMessage(), e);
								}
							}
						}

					} else {
						logger.error("ProvisionSAPUMEUserBean has a empty AttsList, so user creation is not possible");
						throw new SAPUMEConnectorException("ProvisionSAPUMEUserBean has a empty AttsList, so user creation is not possible");
					}

				} else {
					logger.error("User to be created '" + userID + "' already exists in SAPUME target ssystem");
					throw new SAPUMEConnectorException("User to be created '" + userID + "' already exists in SAPUME target ssystem");
				}

			} else {
				logger.error("ProvisionSAPUMEUserBean object passed is null or empty");
				throw new SAPUMEConnectorException("ProvisionSAPUMEUserBean object passed is null or empty");
			}

		} catch (SAPUMEConnectorException e) {
			logger.error("SAPUMEConnectorException handled in CreateUserSAPUMEOperation.execute() method: " + e.getMessage());
			throw e;
			
		} catch (Exception e) {
			logger.error("Generic error ocurred in CreateUserSAPUMEOperation.execute() method: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error ocurred in CreateUserSAPUMEOperation.execute() method: " + e.getMessage(), e);
		}
	}
	
}
