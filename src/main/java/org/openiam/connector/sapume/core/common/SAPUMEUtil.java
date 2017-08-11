package org.openiam.connector.sapume.core.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.connector.sapume.core.beans.AttributeBean;
import org.openiam.connector.sapume.core.beans.ProvisionSAPUMEUserBean;
import org.openiam.connector.sapume.core.beans.SAPUMEGroupBean;
import org.openiam.connector.sapume.core.beans.SAPUMERoleBean;
import org.openiam.connector.sapume.core.connection.SAPUMEConnection;
import org.openiam.connector.sapume.core.security.SecuredString;
import org.openiam.connector.sapume.service.types.ExtensibleAttribute;
import org.openspml.message.Attribute;
import org.openspml.message.DeleteRequest;
import org.openspml.message.Filter;
import org.openspml.message.FilterTerm;
import org.openspml.message.Modification;
import org.openspml.message.ModifyRequest;
import org.openspml.message.SearchRequest;
import org.openspml.message.SearchResponse;
import org.openspml.message.SearchResult;
import org.openspml.message.SpmlResponse;

public class SAPUMEUtil
{
	/*************************************************************
     **********          OBJETOS INTERNOS              ***********
     *************************************************************/
		
	private static Log logger = LogFactory.getLog(SAPUMEUtil.class);
	
	public static final String SAPUME_DATE_MASK = "yyyyMMddHHmmss'Z'";
	
		
	/*************************************************************
     ***********           MÉTODOS PÚBLICOS            ***********
     *************************************************************/
	
	public static boolean checkIfUserExist(String logonname, SAPUMEConnection connection, SAPUMEConfiguration config) throws SAPUMEConnectorException
	{
		boolean userExists = false;
		try
		{
			logger.info("Start checkIfUserExist() method");
			
			logger.info("User to search: " + logonname);
			SearchRequest spmlSearchReq = new SearchRequest();
			
			// Definimos tipo objetos a buscar
			spmlSearchReq.setSearchBase(SAPUMEConstants.SAPUME_TARGETOBJCLASS_USER);
			// Construimos el filtro de búsqueda para buscar a un usuario concreto
			Filter filter = SAPUMEFilterGenerator.buildSPMLFiltertoMatchUserByLogonName(logonname); 
			spmlSearchReq.setFilter(filter);
			// Definimos los atributos a recuperar del usuario
			spmlSearchReq.addAttribute(SAPUMEConstants.SAPUME_FIELD_LOGONNAME);
			spmlSearchReq.addAttribute(SAPUMEConstants.SAPUME_FIELD_UNIQUEID);
			
			logger.info("SPML request will be send to SAPUME...");
			SpmlResponse spmlResponse = connection.sendSPMLRequest(spmlSearchReq, config.isTraceSPMLRequest());
			logger.info("SPML response received from SAPUME");
					
			List<SearchResult> listResults = ((SearchResponse)spmlResponse).getResults();
			if (listResults != null && listResults.size() > 0)
			{
				logger.info("User " + logonname + " exists in SAPUME repository");
				userExists = true;
				
				if (listResults.size() > 1)
					logger.warn("Please note that found more than one user for user ID specified");
				
			} else {
				logger.info("User " + logonname + " does not exist in SAPUME repository");
				userExists = false;
			}
			
		} catch (SAPUMEConnectorException e) {
			throw e;
			
		}  catch (Exception e) {
			logger.error("Generic error while checking user existence in SAPUME: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error while checking user existence in SAPUME: " + e.getMessage(), e);
		}
		return userExists;
	}
	
	
	public static String getUserUniqueID(String logonname, SAPUMEConnection connection, SAPUMEConfiguration config) throws SAPUMEConnectorException
	{
		String userUniqueID = "";
		try
		{
			logger.info("Start getUserUniqueID() method");
			
			logger.info("User to search: " + logonname);
			SearchRequest spmlSearchReq = new SearchRequest();
			
			// Definimos tipo objetos a buscar
			spmlSearchReq.setSearchBase(SAPUMEConstants.SAPUME_TARGETOBJCLASS_USER);
			// Construimos el filtro de búsqueda para buscar a un usuario concreto
			Filter filter = SAPUMEFilterGenerator.buildSPMLFiltertoMatchUserByLogonName(logonname); 
			spmlSearchReq.setFilter(filter);
			// Definimos los atributos a recuperar del usuario
			spmlSearchReq.addAttribute(SAPUMEConstants.SAPUME_FIELD_LOGONNAME);
			spmlSearchReq.addAttribute(SAPUMEConstants.SAPUME_FIELD_UNIQUEID);
			
			logger.info("SPML request will be send to SAPUME...");
			SpmlResponse spmlResponse = connection.sendSPMLRequest(spmlSearchReq, config.isTraceSPMLRequest());
			logger.info("SPML response received from SAPUME");
					
			List<SearchResult> listResults = ((SearchResponse)spmlResponse).getResults();
			if (listResults != null && listResults.size() > 0)
			{
				logger.info("User " + logonname + " exists in SAPUME repository");
				
				if (listResults.size() == 1)
				{
					userUniqueID = listResults.get(0).getIdentifierString();
					logger.info("User UniqueID obtained: " + userUniqueID);
					
				} else {
					logger.error("Found more than one account for user ID specified");
					throw new SAPUMEConnectorException("Found more than one account for user ID specified");
				}
				
			} else {
				logger.info("User " + logonname + " does not exist in SAPUME repository");
				throw new SAPUMEConnectorException("User " + logonname + " does not exist in SAPUME repository");
			}
			
		} catch (SAPUMEConnectorException e) {
			throw e;
			
		}  catch (Exception e) {
			logger.error("Generic error while searching user in SAPUME to obtain user Unique ID: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error while searching user in SAPUME to obtain user Unique ID: " + e.getMessage(), e);
		}
		return userUniqueID;
	}
	
	
	public static boolean checkIfUserIsTechnical(String logonname, SAPUMEConnection connection, SAPUMEConfiguration config) throws SAPUMEConnectorException
	{
		boolean isTechnicalUser = false;
		try
		{
			logger.info("Start checkIfUserIsTechnical() method");
			
			logger.info("User to search: " + logonname);
			SearchRequest spmlSearchReq = new SearchRequest();
			
			// Definimos tipo objetos a buscar
			spmlSearchReq.setSearchBase(SAPUMEConstants.SAPUME_TARGETOBJCLASS_USER);
			// Construimos el filtro de búsqueda para buscar a un usuario concreto
			Filter filter = SAPUMEFilterGenerator.buildSPMLFiltertoMatchUserByLogonName(logonname); 
			spmlSearchReq.setFilter(filter);
			// Definimos los atributos a recuperar del usuario
			spmlSearchReq.addAttribute(SAPUMEConstants.SAPUME_FIELD_SECURITYPOLICY);
			
			logger.info("SPML request will be send to SAPUME...");
			SpmlResponse spmlResponse = connection.sendSPMLRequest(spmlSearchReq, config.isTraceSPMLRequest());
			logger.info("SPML response received from SAPUME");
					
			List<SearchResult> listResults = ((SearchResponse)spmlResponse).getResults();
			if (listResults != null && listResults.size() > 0)
			{
				logger.info("User " + logonname + " exists in SAPUME repository");
				
				if (listResults.size() == 1)
				{
					SearchResult result = listResults.get(0);
					// Será usuario técnico solo si securitypolicy=technical 
					isTechnicalUser = (result.getAttribute(SAPUMEConstants.SAPUME_FIELD_SECURITYPOLICY) != null &&
									   result.getAttribute(SAPUMEConstants.SAPUME_FIELD_SECURITYPOLICY).getValue() != null &&
									   result.getAttribute(SAPUMEConstants.SAPUME_FIELD_SECURITYPOLICY).getValue().toString().equals(SAPUMEConstants.SAPUME_USER_SECURITYPOLICY_TECHNICAL)) ? true : false;
					
					if (isTechnicalUser )
						logger.info("User is Technical");
					else
						logger.info("User is not Technical");
					
				} else {
					logger.error("Found more than one account for user ID specified");
					throw new SAPUMEConnectorException("Found more than one account for user ID specified");
				}
				
			} else {
				logger.info("User " + logonname + " does not exist in SAPUME repository");
				throw new SAPUMEConnectorException("User " + logonname + " does not exist in SAPUME repository");
			}
			
		} catch (SAPUMEConnectorException e) {
			throw e;
			
		}  catch (Exception e) {
			logger.error("Generic error while checking if is technical user in SAPUME: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error while checking if is technical user in SAPUME: " + e.getMessage(), e);
		}
		return isTechnicalUser;
	}
	
	
	@SuppressWarnings("deprecation")
	public static void updateModifyRequestForDisableUser(String logonname, ModifyRequest spmlModifyRequest, SAPUMEConnection connection, SAPUMEConfiguration config) throws SAPUMEConnectorException
	{
		try
		{
			logger.info("Start updateModifyRequestForDisableUser() method");
			
			if (config.isModifyDatesForEnableDisable())
			{
				Date dateToDisable = null;
				// Obtenemos la fecha actual
				Date dateNow = DateUtils.getCurrentDate(false);
				logger.debug("dateNow: " + DateUtils.getStrFromDate(dateNow, config.getSapDateMask()));
				// Obtenemos el valor de la fecha VALID_FROM que el usuario tiene en SAPUME
				Date validFrom = SAPUMEUtil.getUserValidFrom(logonname, connection, config);
				logger.debug("validFrom: " + DateUtils.getStrFromDate(validFrom, config.getSapDateMask()));
				
				// Detectamos cuando la fecha actual (fecha en la que se va a deshabilitar) es el mismo día que la fecha de inicio que ya tiene en SAPUME
				if (validFrom != null && 
					(validFrom.getYear() == dateNow.getYear() && validFrom.getMonth() == dateNow.getMonth() && validFrom.getDay() == dateNow.getDay()))
				{
					// Definimos cono fecha de inicio el dia en el que estamos
					dateToDisable = dateNow;
					
				} else {
					// Obtenemos la fecha con la que actualizar el Valid Through para deshabilitar al usuario (FECHA_ACTUAL - 1)
					dateToDisable = DateUtils.getCurrentDateModByDays(false, -1);
				}
				
				// Obtenemos el valor del atributo con el formato de fecha esperado por SAP			
				String strDateValue = DateUtils.getStrFromDate(dateToDisable, SAPUMEUtil.SAPUME_DATE_MASK);
				logger.info("In order to disable user, ValidTo field will be updated with value: " + strDateValue);
				spmlModifyRequest.addModification(SAPUMEConstants.SAPUME_FIELD_VALIDTO, strDateValue);
				
			} else {
				logger.info("ModifyDatesForEnableDisable flag is disabled, so no VALID_TO modification is required");
			}
			
			logger.info("Disable user operation also required lock user");
			spmlModifyRequest.addModification(SAPUMEConstants.SAPUME_FIELD_ISLOCKED, SAPUMEConstants.SAPUME_VALUE_USERLOCK_YES);
			
			logger.debug("ModifyRequest was sucess update");
			
		}  catch (Exception e) {
			logger.error("Generic error updating spml ModifyRequest for disabling user: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error updating spml ModifyRequest for disabling user: " + e.getMessage(), e);
		}
	}
	
	
	public static void updateModifyRequestForEnableUser(ModifyRequest spmlModifyRequest, SAPUMEConfiguration config) throws SAPUMEConnectorException
	{
		try
		{
			logger.info("Start updateModifyRequestForEnableUser() method");
			
			if (config.isModifyDatesForEnableDisable())
			{
				// Obtenemos la fecha con la que actualizar el Valid Through para activar al usuario
				Date date = DateUtils.getDateFromStr(config.getEnableAccountsDate(), config.getSapDateMask());
				
				// Obtenemos el valor del atributo con el formato de fecha esperado por SAP			
				String strDateValue = DateUtils.getStrFromDate(date, SAPUMEUtil.SAPUME_DATE_MASK);
				logger.info("In order to enable user, ValidTo field will be updated with value: " + strDateValue);
				spmlModifyRequest.addModification(SAPUMEConstants.SAPUME_FIELD_VALIDTO, strDateValue);
				
			} else {
				logger.info("ModifyDatesForEnableDisable flag is disabled, so no VALID_TO modification is required");
			}
			
			logger.info("Enable user operation also required unlock user");
			spmlModifyRequest.addModification(SAPUMEConstants.SAPUME_FIELD_ISLOCKED, SAPUMEConstants.SAPUME_VALUE_USERLOCK_NO);
			
			logger.debug("ModifyRequest was sucess update");
			
		}  catch (Exception e) {
			logger.error("Generic error updating spml ModifyRequest for enabling user: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error updating spml ModifyRequest for enabling user: " + e.getMessage(), e);
		}
	}
	
	
	public static void deleteUser(String sapumeUniqueID, SAPUMEConnection connection, SAPUMEConfiguration config) throws SAPUMEConnectorException
	{
		try
		{
			logger.info("Start deleteUser() method");
			
			logger.info("User to be deleted: " + sapumeUniqueID);
			DeleteRequest spmlDeleteReq = new DeleteRequest();
			spmlDeleteReq.setIdentifier(sapumeUniqueID);
			
			logger.info("SPML request will be send to SAPUME...");
			SpmlResponse spmlResponse = connection.sendSPMLRequest(spmlDeleteReq, config.isTraceSPMLRequest());
			logger.info("SPML response received from SAPUME");
			
			SAPUMEUtil.validateSAPUMESpmlResponse(spmlResponse, sapumeUniqueID);
			logger.info("SPML response passed failure validation");
										
			logger.info("User " + sapumeUniqueID + " has been deleted in SAPUME target system");
			
		} catch (SAPUMEConnectorException e) {
			throw e;
			
		}  catch (Exception e) {
			logger.error("Generic error while deleting user in SAPUME: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error while deleting user in SAPUME: " + e.getMessage(), e);
		}
	}
	
	
	public static void changeUserPassword(String sapumeUniqueID, SecuredString oldPassword, SecuredString newPassword, SAPUMEConnection connection, SAPUMEConfiguration config) throws SAPUMEConnectorException
	{
		try
		{
			logger.info("Start changeUserPassword() method");
			
			logger.info("User to make a change password: " + sapumeUniqueID);
			ModifyRequest spmlModifyReq = new ModifyRequest();
			spmlModifyReq.setIdentifier(sapumeUniqueID);
			spmlModifyReq.addModification(SAPUMEConstants.SAPUME_FIELD_OLDPASSWORD, String.valueOf(oldPassword.getClearValue()));
			spmlModifyReq.addModification(SAPUMEConstants.SAPUME_FIELD_PASSWORD, String.valueOf(newPassword.getClearValue()));
			
			logger.info("SPML request will be send to SAPUME...");
			SpmlResponse spmlResponse = connection.sendSPMLRequest(spmlModifyReq, config.isTraceSPMLRequest());
			logger.info("SPML response received from SAPUME");
			
			SAPUMEUtil.validateSAPUMESpmlResponse(spmlResponse, sapumeUniqueID);
			logger.info("SPML response passed failure validation");
										
			logger.info("Change password was done for user " + sapumeUniqueID + " in SAPUME target system");
						
		} catch (SAPUMEConnectorException e) {
			throw e;
			
		}  catch (Exception e) {
			logger.error("Generic error trying to change password for a user: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error trying to change password for a user: " + e.getMessage(), e);
		}
			
	}
	

	public static void manageMembershipForUser(String sapumeUniqueID, ProvisionSAPUMEUserBean provisioningUser, SAPUMEConnection connection, SAPUMEConfiguration config) throws SAPUMEConnectorException
	{
		try
		{
			logger.info("Start manageMembershipForUser() method");
			
			logger.info("User to manage memberships: " + sapumeUniqueID);
			
			ModifyRequest spmlModifyRequest = new ModifyRequest();
			spmlModifyRequest.setIdentifier(sapumeUniqueID);
			
			logger.info("Membership relations for user will be managed and added to Modify request");
			SAPUMEUtil.updateModifyRequestForMembership(provisioningUser, spmlModifyRequest, connection, config);
			logger.info("Membership relations was managed successfully for user");
			
			logger.info("SPML request will be send to SAPUME...");
			SpmlResponse spmlResponse = connection.sendSPMLRequest(spmlModifyRequest, config.isTraceSPMLRequest());
			logger.info("SPML response received from SAPUME");
			
			SAPUMEUtil.validateSAPUMESpmlResponse(spmlResponse, sapumeUniqueID);
			logger.info("SPML response passed failure validation");
										
			logger.info("Membership relations were updated for user " + sapumeUniqueID + " in SAPUME target system");
						
		} catch (SAPUMEConnectorException e) {
			throw e;
			
		}  catch (Exception e) {
			logger.error("Generic error trying to update membership relations for a user: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error trying to update membership relations for a user: " + e.getMessage(), e);
		}
	}

	
	public static void updateModifyRequestForMembership(ProvisionSAPUMEUserBean provisioningUser, ModifyRequest spmlModifyRequest, SAPUMEConnection connection, SAPUMEConfiguration config) throws SAPUMEConnectorException
	{
		try
		{
			logger.info("Start updateModifyRequestForMembership() method");
			if (provisioningUser != null && provisioningUser.getUserID() != null && !provisioningUser.getUserID().equals(""))
			{
				String logonname = provisioningUser.getUserID();
				logger.info("User Logonname to manage membership relations: " + logonname);
				
				/* En el ProvisionSAPUMEUserBean que me llega, la información de Grupos y Roles estará represntada mediante objetos SAPUMEGroupBean y
				 * SAPUMERoleBean que tan solo tienen informados el groupName o el roleName correspondiente (es decir, el uniqueName que tiene en SAPUME).
				 * Esto es así porque en OpenIAM, lo que se tiene de estos elementos y por tanto lo que manda al conector son UniqueNames.
				 * Como en SAPUME, lo que se almacena en los campos assignedgroups y assignedroles del usuario son listas de identificadores (uniqueID), y
				 * no nombres, se tendrá que buscar cada elemento en SAPUME para obtener los uniqueID correspondientes y añadir estos en la ModifyRequest
				*/
				
				if (provisioningUser.isFlagMngGroups())
				{
					logger.info("Groups will be manage");
					SAPUMEUtil.updateModifyRequestForMembershipGroups(provisioningUser.getGroupsNamesShortedList(provisioningUser.getGroupsForAdd()),
																	  provisioningUser.getGroupsNamesShortedList(provisioningUser.getGroupsForDelete()),
																	  spmlModifyRequest,connection, config);
					logger.info("ModifyRequest updated for add/delete Groups");
					
				} else {
					logger.info("Groups manage flag is deactivate in ProvisionSAPUMEUserBean so will not be manage");
				}
				
				if (provisioningUser.isFlagMngRoles())
				{
					logger.info("Roles membership will be manage");
					SAPUMEUtil.updateModifyRequestForMembershipRoles(provisioningUser.getRolesNamesShortedList(provisioningUser.getRolesForAdd()),
																	 provisioningUser.getRolesNamesShortedList(provisioningUser.getRolesForDelete()),
																	 spmlModifyRequest,connection, config);
					logger.info("ModifyRequest updated for add/delete Roles");
					
				} else {
					logger.info("Roles manage flag is deactivate in ProvisionSAPUMEUserBean so will not be manage");
				}
			}
			
		} catch (SAPUMEConnectorException e) {
			throw e;
			
		}  catch (Exception e) {
			logger.error("Generic error while managing membership relations for user: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error while managing membership relations for user: " + e.getMessage(), e);
		}
	}
	
	
	public static ProvisionSAPUMEUserBean getUserDetails(String logonname, List<String> attributes, SAPUMEConnection connection, SAPUMEConfiguration config) throws SAPUMEConnectorException
	{
		ProvisionSAPUMEUserBean provisionSAPUMEUserBean = null;
		
		try
		{
			logger.info("Start getUserDetails() method");
						
			logger.info("User to search: " + logonname);
			SearchRequest spmlSearchReq = new SearchRequest();
			
			// Definimos tipo objetos a buscar
			spmlSearchReq.setSearchBase(SAPUMEConstants.SAPUME_TARGETOBJCLASS_USER);
			// Construimos el filtro de búsqueda para buscar a un usuario concreto
			Filter filter = SAPUMEFilterGenerator.buildSPMLFiltertoMatchUserByLogonName(logonname); 
			spmlSearchReq.setFilter(filter);
			// Definimos los atributos a recuperar del usuario
			for (String oneAtt : attributes)
			{
				spmlSearchReq.addAttribute(oneAtt);
			}
			
			logger.info("SPML request will be send to SAPUME...");
			SpmlResponse spmlResponse = connection.sendSPMLRequest(spmlSearchReq, config.isTraceSPMLRequest());
			logger.info("SPML response received from SAPUME");
					
			List<SearchResult> listResults = ((SearchResponse)spmlResponse).getResults();
			if (listResults != null && listResults.size() > 0)
			{
				logger.info("Found " + listResults.size() + " users with ID " + logonname + " in SAPUME repository");
				
				if (listResults.size() == 1)
				{
					logger.debug("User obtained from SAPUME repository!");
					logger.debug("ProvisionSAPUMEUserBean instance will be create from SMPL SearchResult return data...");
					provisionSAPUMEUserBean = SAPUMEUtil.buildProvSAPUMEUserFromSPMLSearchResult(logonname, attributes, listResults.get(0), connection, config);
					if (provisionSAPUMEUserBean != null)
					{
						logger.debug("ProvisionSAPUMEUserBean was build successfully");
						
					} else {
						logger.error("Can not create ProvisionSAPUMEUserBean instance from SMPL SearchResult return data");
						throw new SAPUMEConnectorException("Can not create ProvisionSAPUMEUserBean SMPL SearchResult return data");
					}
					
				} else {
					logger.error("More than one users were found for user ID specified");
					throw new SAPUMEConnectorException("More than one users were found for user ID specified");
				}
				
			} else {
				logger.info("User " + logonname + " was not found in SAPUME repository");
				throw new SAPUMEConnectorException("User was not found in SAPUME repository");
			}
						
		} catch (SAPUMEConnectorException e) {
			throw e;
			
		} catch (Exception e) {
			logger.error("Generic error trying to get user details in SAPUME: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error trying to get user details in SAPUME: " + e.getMessage(), e);
		}
		
		return provisionSAPUMEUserBean;
	}
	
	public static Date getUserValidFrom(String logonname, SAPUMEConnection connection, SAPUMEConfiguration config) throws SAPUMEConnectorException
	{
		Date validFrom = null;
		try
		{
			logger.info("Start getUserValidFrom() method");
			
			logger.info("User to search: " + logonname);
			SearchRequest spmlSearchReq = new SearchRequest();
			
			// Definimos tipo objetos a buscar
			spmlSearchReq.setSearchBase(SAPUMEConstants.SAPUME_TARGETOBJCLASS_USER);
			// Construimos el filtro de búsqueda para buscar a un usuario concreto
			Filter filter = SAPUMEFilterGenerator.buildSPMLFiltertoMatchUserByLogonName(logonname); 
			spmlSearchReq.setFilter(filter);
			// Definimos el atributo a recuperar del usuario
			spmlSearchReq.addAttribute(SAPUMEConstants.SAPUME_FIELD_VALIDFROM);
			
			logger.info("SPML request will be send to SAPUME...");
			SpmlResponse spmlResponse = connection.sendSPMLRequest(spmlSearchReq, config.isTraceSPMLRequest());
			logger.info("SPML response received from SAPUME");
			
			List<SearchResult> listResults = ((SearchResponse)spmlResponse).getResults();
			if (listResults != null && listResults.size() > 0)
			{
				if (listResults.size() == 1)
				{
					logger.info("User " + logonname + " exists in SAPUME repository");
					SearchResult searchResult = listResults.get(0);
					if (searchResult.getAttribute(SAPUMEConstants.SAPUME_FIELD_VALIDFROM) != null)
					{
						Object fieldValue = searchResult.getAttribute(SAPUMEConstants.SAPUME_FIELD_VALIDFROM).getValue();
						logger.debug("Value of '" + SAPUMEConstants.SAPUME_FIELD_VALIDFROM + "' field its: " + fieldValue);
						if (fieldValue != null)
						{
							// Obtenemos el valor de la fecha en formato esperado por OpenIAM a partir del valor de fecha devuelto por SAPUME
							fieldValue = DateUtils.changeDateMask(fieldValue.toString(), SAPUMEUtil.SAPUME_DATE_MASK, config.getSapDateMask());
							logger.debug("ValidFrom value with SAPUME Date Mask: " + fieldValue);
							validFrom = DateUtils.getDateFromStr((String)fieldValue, config.getSapDateMask());
						}
					}

				} else {
					logger.error("Found more than one account for user ID specified");
					throw new SAPUMEConnectorException("Found more than one account for user ID specified");
				}
				
			} else {
				logger.info("User " + logonname + " cannot be found in SAPUME repository");
				throw new SAPUMEConnectorException("User " + logonname + " cannot be found in SAPUME repository");
			}

		} catch (SAPUMEConnectorException e) {
			throw e;
			
		} catch (Exception e) {
			logger.error("Generic error trying to get ValidFrom date for user in SAPUME: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error trying to get ValidFrom date for user in SAPUME: " + e.getMessage(), e);
		}
		
		return validFrom;
	}
	
	public static List<SAPUMEGroupBean> getUserGroups(String logonname, SAPUMEConnection connection, SAPUMEConfiguration config) throws SAPUMEConnectorException
	{
		List<SAPUMEGroupBean> groupsAssignedToUser = null;
		
		try
		{
			// Recuperamos los grupos que el usuario tiene actualmente en SAPUME
			List<String> groupIdsAssignedInSapume = SAPUMEUtil.getSAPUMEAuthorElementsAsignedToUser(logonname, SAPUMEConstants.SAPUME_FIELD_ASSIGNEDGROUPS, connection, config);
			if (groupIdsAssignedInSapume != null)
			{
				logger.info("The user has " + groupIdsAssignedInSapume.size() + " Groups in SAPUME");
				logger.info("groupsNamesAssignedInSapume: " + groupIdsAssignedInSapume);
				
				// Se construye la lista de beans de grupos a partir del uniquename de cada uno de ellos
				logger.info("Now need search each group to obtain uniqueName and build SAPUMEGroupBean List...");
				groupsAssignedToUser = SAPUMEUtil.buildUserGroupBeanListFromGroupIds(groupIdsAssignedInSapume, connection, config);
				if (groupsAssignedToUser != null && groupsAssignedToUser.size() > 0)
					logger.info("SAPUMEGroupBean List obatined after parse ids");
				else
					logger.info("No any SAPUMEGroupBean obatined after parse ids");
				
			} else {
				logger.info("The user has not any Group in SAPUME");				
			}
		
		} catch (SAPUMEConnectorException e) {
			throw e;
			
		} catch (Exception e) {
			logger.error("Generic error trying to get Groups assigned to a user in SAPUME: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error trying to get Groups assigned to a user in SAPUME: " + e.getMessage(), e);
		}
		
		return groupsAssignedToUser;
	}
	
	
	public static List<SAPUMERoleBean> getUserRoles(String logonname, SAPUMEConnection connection, SAPUMEConfiguration config) throws SAPUMEConnectorException
	{
		List<SAPUMERoleBean> rolesAssignedToUser = null;
		
		try
		{
			// Recuperamos los roles que el usuario tiene actualmente en SAPUME
			List<String> roleIdsAssignedInSapume = SAPUMEUtil.getSAPUMEAuthorElementsAsignedToUser(logonname, SAPUMEConstants.SAPUME_FIELD_ASSIGNEDROLES, connection, config);
			if (roleIdsAssignedInSapume != null)
			{
				logger.info("The user has " + roleIdsAssignedInSapume.size() + " Roles in SAPUME");
				logger.info("roleIdsAssignedInSapume: " + roleIdsAssignedInSapume);
								
				// Se construye la lista de beans de roles a partir del uniquename de cada uno de ellos
				logger.info("Now need search each role to obtain uniqueName and build SAPUMERoleBean List...");
				rolesAssignedToUser = SAPUMEUtil.buildUserRoleBeanListFromRoleIds(roleIdsAssignedInSapume, connection, config);
				if (rolesAssignedToUser != null && rolesAssignedToUser.size() > 0)
					logger.info("SAPUMERoleBean List obatined after parse ids");
				else
					logger.info("No any SAPUMERoleBean obatined after parse ids");
				
			} else {
				logger.info("The user has not any Role in SAPUME");				
			}
		
		}  catch (SAPUMEConnectorException e) {
			throw e;
			
		}  catch (Exception e) {
			logger.error("Generic error trying to get Roles assigned to a user in SAPUME: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error trying to get Roles assigned to a user in SAPUME: " + e.getMessage(), e);
		}
		
		return rolesAssignedToUser;
	}
	
	
	public static SAPUMEGroupBean getGroupDetailsFromUniqueID(String groupUniqueID, SAPUMEConnection connection, SAPUMEConfiguration config) throws SAPUMEConnectorException
	{
		SAPUMEGroupBean sapumeGroupBean = null;
		try
		{
			logger.info("Start getGroupDetailsFromUniqueID() method");
			
			logger.info("Group to search: " + groupUniqueID);
			
			Filter filter = SAPUMEFilterGenerator.buildSPMLFiltertoMatchGroupByUniqueId(groupUniqueID);
			sapumeGroupBean = SAPUMEUtil.executeSingleGroupSearch(filter, connection, config);
			if (sapumeGroupBean != null)
			{
				logger.info("SAPUMEGroupBean was build successfully");
				
			} else {
				logger.error("Not Group was found in SAPUME repository for filter search defined");
				throw new SAPUMEConnectorException("Not Group was found in SAPUME repository for filter search defined");
			}
			
		} catch (SAPUMEConnectorException e) {
			throw e;
			
		}  catch (Exception e) {
			logger.error("Generic error trying to get group details in SAPUME: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error trying to get group details in SAPUME: " + e.getMessage(), e);
		}
		return sapumeGroupBean;
	}
	
	
	public static SAPUMERoleBean getRoleDetailsFromUniqueID(String roleUniqueID, SAPUMEConnection connection, SAPUMEConfiguration config) throws SAPUMEConnectorException
	{
		SAPUMERoleBean sapumeRoleBean = null;
		try
		{
			logger.info("Start getRoleDetailsFromUniqueID() method");
			
			logger.info("Role to search: " + roleUniqueID);
			
			Filter filter = SAPUMEFilterGenerator.buildSPMLFiltertoMatchRoleByUniqueId(roleUniqueID);
			sapumeRoleBean = SAPUMEUtil.executeSingleRoleSearch(filter, connection, config);
			if (sapumeRoleBean != null)
			{
				logger.info("SAPUMERoleBean was build successfully");
				
			} else {
				logger.error("Not Role was found in SAPUME repository for filter search defined");
				throw new SAPUMEConnectorException("Not Role was found in SAPUME repository for filter search defined");
			}
			
		} catch (SAPUMEConnectorException e) {
			throw e;
			
		}  catch (Exception e) {
			logger.error("Generic error trying to get role details in SAPUME: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error trying to get role details in SAPUME: " + e.getMessage(), e);
		}
		return sapumeRoleBean;
	}
	
	
	public static SAPUMEGroupBean getGroupDetailsFromUniqueName(String groupUniqueName, SAPUMEConnection connection, SAPUMEConfiguration config) throws SAPUMEConnectorException
	{
		SAPUMEGroupBean sapumeGroupBean = null;
		try
		{
			logger.info("Start getGroupDetailsFromUniqueName() method");
			
			logger.info("Group to search: " + groupUniqueName);
			
			Filter filter = SAPUMEFilterGenerator.buildSPMLFiltertoMatchGroupByUniqueName(groupUniqueName);
			sapumeGroupBean = SAPUMEUtil.executeSingleGroupSearch(filter, connection, config);
			if (sapumeGroupBean != null)
				logger.info("SAPUMEGroupBean was build successfully");
			else
				logger.info("Not Group was found in SAPUME repository for filter search defined");
			
		} catch (SAPUMEConnectorException e) {
			throw e;
			
		}  catch (Exception e) {
			logger.error("Generic error trying to get group details in SAPUME: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error trying to get group details in SAPUME: " + e.getMessage(), e);
		}
		return sapumeGroupBean;
	}
	
	
	public static SAPUMERoleBean getRoleDetailsFromUniqueName(String roleUniqueName, SAPUMEConnection connection, SAPUMEConfiguration config) throws SAPUMEConnectorException
	{
		SAPUMERoleBean sapumeRoleBean = null;
		try
		{
			logger.info("Start getRoleDetailsFromUniqueName() method");
			
			logger.info("Role to search: " + roleUniqueName);
			
			Filter filter = SAPUMEFilterGenerator.buildSPMLFiltertoMatchRoleByUniqueName(roleUniqueName);
			sapumeRoleBean = SAPUMEUtil.executeSingleRoleSearch(filter, connection, config);
			if (sapumeRoleBean != null)
			{
				logger.info("SAPUMERoleBean was build successfully");
				
			} else {
				logger.info("Not Role was found in SAPUME repository for filter search defined");
			}
			
		} catch (SAPUMEConnectorException e) {
			throw e;
			
		}  catch (Exception e) {
			logger.error("Generic error trying to get role details in SAPUME: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error trying to get role details in SAPUME: " + e.getMessage(), e);
		}
		return sapumeRoleBean;
	}
	
	
	public static String getGroupsForPrint(List<SAPUMEGroupBean> listSapGroups)
	{
		StringBuffer sb = new StringBuffer("###Groups==");
		if (listSapGroups != null && listSapGroups.size() > 0)
		{
			boolean first =  true;
			for (SAPUMEGroupBean sapGroupBean : listSapGroups)
			{
				if (!first)
					sb.append(",");
				else
					first = false;
				
				sb.append(sapGroupBean.getGroupName());
			}
		} else {
			sb.append("**EMPTY**");
		}
		return sb.toString();
	}
	
	
	public static String getRolesForPrint(List<SAPUMERoleBean> listSapRoles)
	{
		StringBuffer sb = new StringBuffer("###Roles==");
		if (listSapRoles != null && listSapRoles.size() > 0)
		{
			boolean first =  true;
			for (SAPUMERoleBean sapRoleBean : listSapRoles)
			{
				if (!first)
					sb.append(",");
				else
					first = false;
				
				sb.append(sapRoleBean.getRoleName());
			}
		} else {
			sb.append("**EMPTY**");
		}
		return sb.toString();
	}
	
	
	public static String getListForPrint(List<String> list, String listName)
	{
		StringBuffer sb = new StringBuffer("###" + listName + "==");
		if (list != null && list.size() > 0)
		{
			boolean first =  true;
			for (String item : list)
			{
				if (!first)
					sb.append(",");
				else
					first = false;
				
				sb.append(item);
			}
		} else {
			sb.append("**EMPTY**");
		}
		return sb.toString();
	}
	
	
	public static boolean groupListContainSigleGroupName(String groupName, List<SAPUMEGroupBean> listSapumeGroups)
	{
		boolean groupFound = false; 
		if (groupName != null && listSapumeGroups != null && listSapumeGroups.size() > 0)
		{
			for (SAPUMEGroupBean sapumeGroupBean : listSapumeGroups)
			{
				if (sapumeGroupBean.getGroupName().equals(groupName))
					groupFound = true;
			}
		}
		return groupFound;
	}
	
	
	public static boolean roleListContainSigleRoleName(String roleName, List<SAPUMERoleBean> listSapumeRoles)
	{
		boolean roleFound = false; 
		if (roleName != null && listSapumeRoles != null && listSapumeRoles.size() > 0)
		{
			for (SAPUMERoleBean sapumeRoleBean : listSapumeRoles)
			{
				if (sapumeRoleBean.getRoleName().equals(roleName))
					roleFound = true;
			}
		}
		return roleFound;
	}
	
	
	public static List<ExtensibleAttribute> lookupAttributeNames(SAPUMEConfiguration config, ExecutionModeTypes executionMode) throws SAPUMEConnectorException
	{
		List<ExtensibleAttribute> extensibleAttributeList = null;
		
		try
		{
			logger.info("Start lookupAttributeNames() method");
			logger.debug("Param executionMode value: " + executionMode);
			
			String[] attList = null;
			switch (executionMode)
			{
				case POLICY_MAP:
					attList = config.getPolicyMapAttList();
					break;

				case MANAGED_SYSTEM:
					attList = config.getManagedSystemAttList();
					break;
					
				default:
					break;
			}
			
			if (attList != null && attList.length > 0)
			{
				logger.info("Attributes List obtained from configuration");
				if (logger.isDebugEnabled())
				{
					StringBuffer sb = new StringBuffer();
					for (String att : attList)
					{
						if (sb.length() > 0)
							sb.append(",");
						sb.append(att);
					}
					logger.debug("Attributes List obtained from configuration file --> " + sb.toString());
				}
				
				extensibleAttributeList = SAPUMEUtil.buildExtensibleAttributeList(attList);
				logger.info("ExtensibleAttribute list obtained from configuration properties");
				
			} else {
				logger.info("Not obtain any attribute from configuration for SAP target system");
			}
			
		} catch (SAPUMEConnectorException e) {
			throw e;

		}  catch (Exception e) {
			logger.error("Generic error trying to lookup SAPUME attribute names: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error trying to lookup SAPUME attribute names: " + e.getMessage(), e);
		}
		
		return extensibleAttributeList;
	}
	
	// Este método sólo se ha de invocar para validar las peticiones SPML de Creación, Modificación y borrado.
	// No llamar tras opertación de consulta, ya que al lanzar excepciones, puede alterar la lógica de negocio del conector
	public static void validateSAPUMESpmlResponse(SpmlResponse spmlResponse, String userId) throws SAPUMEConnectorException
	{
		logger.info("Start validateSAPUMESpmlResponse() method");
		
		if (spmlResponse.getResult().contains("failure"))
		{
			if (spmlResponse.getErrorMessage().contains("doesn't exist"))
			{
				logger.error("Entity " + userId + " doesn't exist in SAPUME repository: " + spmlResponse.getErrorMessage());
				throw new SAPUMEConnectorException("Entity " + userId + " doesn't exist in SAPUME repository");
			}

			if ((spmlResponse.getErrorMessage().contains("ALPHANUM_REQUIRED_FOR_PSWD")) || (spmlResponse.getErrorMessage().contains("PASSWORD_TOO_SHORT")) || (spmlResponse.getErrorMessage().contains("PASSWORD_TOO_LONG")))
			{
				logger.error("Invalid password try to set to user account in SAPUME repository: " + spmlResponse.getErrorMessage());
				throw new SAPUMEConnectorException("Invalid password try to set to user account in SAPUME repository");
			}

			if (spmlResponse.getErrorMessage().contains("already exists"))
			{
				logger.error("Entity " + userId + " already exists in SAPUME repository: " + spmlResponse.getErrorMessage());
				throw new SAPUMEConnectorException("Entity " + userId + " already exists in SAPUME repository");
			}

			logger.error("SPML failure message response detected: " + spmlResponse.getErrorMessage());
			throw new SAPUMEConnectorException("SPML failure message response: " + spmlResponse.getErrorMessage());
		}
		
		if (spmlResponse.getResult().contains("success"))
		{
			logger.info("SPML success message response");
			logger.info("Operation executted successfully for account " + userId);
		}
	}
	
	
	public static List<ProvisionSAPUMEUserBean> searchSAPUMEAccounts(String searchQuery, SAPUMEConnection connection, SAPUMEConfiguration config) throws SAPUMEConnectorException
	{
		List<ProvisionSAPUMEUserBean> listProvisionSAPUMEUserBean = null;
		
		try
		{
			logger.info("Start searchSAPUMEAccounts() method");
	
			String searchFilter = "";
			String updateSince = "";
									
			logger.info("SearchQuery: " + searchQuery);
			if (searchQuery != null && !searchQuery.equals(""))
			{
				logger.info("SearchQuery will be parsed");
				searchFilter = SAPUMEUtil.getSearchFilterFromSearchQuery(searchQuery);
				logger.info("searchFilter obtained: " + searchFilter);
				updateSince = SAPUMEUtil.getUpdateSinceFromSearchQuery(searchQuery);
				logger.debug("updateSince obtained: " + updateSince);
				
			} else {
				logger.info("SearchQuery is null or empty");
			}
			
			List<String> reconSearchUsersAttList = config.getReconSearchUsersAttList();
			logger.info("reconSearchUsersAttList: " + reconSearchUsersAttList );
						
			logger.info("Process reconciliation search for SAPUME users accounts...");
			listProvisionSAPUMEUserBean = SAPUMEUtil.reconSAPUMEUsersAccounts(searchFilter, updateSince, reconSearchUsersAttList, connection, config);
			
			if (listProvisionSAPUMEUserBean != null && listProvisionSAPUMEUserBean.size() > 0)
			{
				logger.info("Were obtained " + listProvisionSAPUMEUserBean.size() + " user accounts from SAPUME");
				
			} else {
				logger.info("No users obtained from SAPUME");
			}
			
		} catch (SAPUMEConnectorException e) {
			throw e;
			
		}  catch (Exception e) {
			logger.error("Generic error trying to search users accounts in SAPUME: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error trying to search users accounts in SAPUME: " + e.getMessage(), e);
		}
		
		return listProvisionSAPUMEUserBean;
	}
	
	
	public static List<SAPUMEGroupBean> searchSAPUMEGroups(String searchQuery, SAPUMEConnection connection, SAPUMEConfiguration config) throws SAPUMEConnectorException
	{
		List<SAPUMEGroupBean> listSAPUMEGroupBean = null;
		
		try
		{
			logger.info("Start searchSAPUMEGroups() method");
							
			logger.info("Process reconciliation search for SAPUME Groups...");
			listSAPUMEGroupBean = SAPUMEUtil.reconSAPUMEGroups(searchQuery, connection, config);
			
			if (listSAPUMEGroupBean != null && listSAPUMEGroupBean.size() > 0)
			{
				logger.info("Were obtained " + listSAPUMEGroupBean.size() + " Groups from SAPUME");
				
			} else {
				logger.info("No Groups obtained from SAPUME");
			}
			
		} catch (SAPUMEConnectorException e) {
			throw e;
			
		}  catch (Exception e) {
			logger.error("Generic error trying to search Groups in SAPUME: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error trying to search Groups in SAPUME: " + e.getMessage(), e);
		}
		
		return listSAPUMEGroupBean;
	}
	
	
	public static List<SAPUMERoleBean> searchSAPUMERoles(String searchQuery, SAPUMEConnection connection, SAPUMEConfiguration config) throws SAPUMEConnectorException
	{
		List<SAPUMERoleBean> listSAPUMERoleBean = null;
		
		try
		{
			logger.info("Start searchSAPUMEGroups() method");
							
			logger.info("Process reconciliation search for SAPUME Roles...");
			listSAPUMERoleBean = SAPUMEUtil.reconSAPUMERoles(searchQuery, connection, config);
			
			if (listSAPUMERoleBean != null && listSAPUMERoleBean.size() > 0)
			{
				logger.info("Were obtained " + listSAPUMERoleBean.size() + " Roles from SAPUME");
				
			} else {
				logger.info("No Roles obtained from SAPUME");
			}
			
		} catch (SAPUMEConnectorException e) {
			throw e;
			
		}  catch (Exception e) {
			logger.error("Generic error trying to search Roles in SAPUME: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error trying to search Roles in SAPUME: " + e.getMessage(), e);
		}
		
		return listSAPUMERoleBean;
	}
	
	
	public static String getUniqueNameFromUniqueID(String uniqueID) throws SAPUMEConnectorException
	{
		try
		{
			logger.info("Start getUniqueNameFromUniqueID() method");
			
			String uniqueName = null;
			if (uniqueID.lastIndexOf(":") > -1)
				uniqueName = uniqueID.substring(uniqueID.lastIndexOf(":") + 1);
			else {
				uniqueName = uniqueID.substring(uniqueID.lastIndexOf(".") + 1);
			}
			return uniqueName;
			
		} catch (Exception e) {
			logger.error("Generic error trying to get UniqueName from UniqueID for a SAPUME entity: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error trying to get UniqueName from UniqueID for a SAPUME entity: " + e.getMessage(), e);
		}
	}
	
	
	/*************************************************************
     ***********           MÉTODOS PRIVADOS            ***********
     *************************************************************/
	
	private static List<ExtensibleAttribute> buildExtensibleAttributeList(String[] policyMapAttList) throws SAPUMEConnectorException
	{
		List<ExtensibleAttribute> extensibleAttributeList = null;
	
		try
		{
			logger.info("Start buildExtensibleAttributeList() method");
			
			if (policyMapAttList != null && policyMapAttList.length > 0)
			{
				logger.debug("Attribute names list for SAP will be created...");
			    extensibleAttributeList = new LinkedList<ExtensibleAttribute>();
			    for (String oneAtt : policyMapAttList)
			    {
			    	if (oneAtt.contains(SAPUMEConstants.SAPUME_ATTRIBUTES_METADATAELEMENT_SEPARATOR))
			    	{
			    		String[] attSplited = oneAtt.split(SAPUMEConstants.SAPUME_ATTRIBUTES_METADATAELEMENT_SEPARATOR);
						if (attSplited != null && attSplited.length > 1)
						{
							String finalAttName = attSplited[0];
							String metadata = attSplited[1];
							logger.debug("Metadata element deteced ' " + metadata + "' for attribute " + finalAttName);
							ExtensibleAttribute extAtt = new ExtensibleAttribute(finalAttName, "");
							extAtt.setMetadataElementId(metadata);
							extensibleAttributeList.add(extAtt);
							logger.debug("Attribute " + finalAttName + " added to ExtensibleAttribute list");
							
						} else {
							extensibleAttributeList.add(new ExtensibleAttribute(oneAtt, ""));
							logger.debug("Attribute " + oneAtt + " added to ExtensibleAttribute list");
						}
			    		
			    	} else {
			    		
						extensibleAttributeList.add(new ExtensibleAttribute(oneAtt, ""));
						logger.debug("Attribute " + oneAtt + " added to ExtensibleAttribute list");
			    	}
				}
			}
			
		} catch (Exception e) {
			logger.error("Generic error while try to build ExtensibleAttribute List from attributes list defined in configuration: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error while try to build ExtensibleAttribute List from attributes list defined in configuration: " + e.getMessage(), e);
		}
		
		return extensibleAttributeList;
	}
	
	private static ProvisionSAPUMEUserBean buildProvSAPUMEUserFromSPMLSearchResult(String logonname, List<String> attributes, SearchResult spmlSearchResult, SAPUMEConnection connection, SAPUMEConfiguration config) throws SAPUMEConnectorException
	{
		ProvisionSAPUMEUserBean provisionSAPUMEUserBean = null;
		
		try
		{
			logger.info("Start buildProvSAPUMEUserFromSPMLSearchResult() method");
			
			if (logonname != null && !logonname.equals(""))
			{
				provisionSAPUMEUserBean = new ProvisionSAPUMEUserBean();
				provisionSAPUMEUserBean.setUserID(logonname);
				if (attributes != null && attributes.size() > 0)
				{
					List<AttributeBean> attsList = new ArrayList<AttributeBean>();
					Date validTo = null;
					Date validFrom = null;
					List<String> groupsList = null;
					List<String> rolesList = null;
					
					for (String attName : attributes)
					{
						logger.debug("Field to process: " + attName);
						
						try
						{							
							Object fieldValue = null;
							
							Attribute spmlAtt = spmlSearchResult.getAttribute(attName);
							if (spmlAtt != null)
							{
								fieldValue = spmlAtt.getValue();
								logger.debug("Value of '" + attName + "' field its: " + fieldValue);
							}
							
							if (attName.equals(SAPUMEConstants.SAPUME_FIELD_ASSIGNEDGROUPS) && fieldValue != null)
							{
								// Como llega el atributo en la lista recuperamos su valor para posterior tratamiento
								// Lo que tenemos es la lista de los UniqueID de los grupos asignados al usuario
								// Si solo tenemos un grupo, no llega una lista sino un String
								if (fieldValue instanceof String)
								{
									groupsList = new ArrayList<String>();
									groupsList.add((String)fieldValue);
									
								} else {
									groupsList = (List<String>)fieldValue;
								}
								
							} else if (attName.equals(SAPUMEConstants.SAPUME_FIELD_ASSIGNEDROLES) && fieldValue != null) {
								
								// Como llega el atributo en la lista recuperamos su valor para posterior tratamiento
								// Lo que tenemos es la lista de los UniqueID de los roles asignados al usuario
								// Si solo tenemos un rol, no llega una lista sino un String
								if (fieldValue instanceof String)
								{
									rolesList = new ArrayList<String>();
									rolesList.add((String)fieldValue);
									
								} else {
									rolesList = (List<String>)fieldValue;
								}
																
							} else {
								
								// Tratamiento del valor final a establecer en el atributo
								if (fieldValue != null)
								{
									if (attName.equals(SAPUMEConstants.SAPUME_FIELD_VALIDTO))
									{
										// Obtenemos el valor de la fecha en formato esperado por OpenIAM a partir del valor de fecha devuelto por SAPUME
										fieldValue = DateUtils.changeDateMask(fieldValue.toString(), SAPUMEUtil.SAPUME_DATE_MASK, config.getSapDateMask());
										validTo = DateUtils.getDateFromStr((String)fieldValue, config.getSapDateMask());
										
									} else if (attName.equals(SAPUMEConstants.SAPUME_FIELD_VALIDFROM)) {
										
										// Obtenemos el valor de la fecha en formato esperado por OpenIAM a partir del valor de fecha devuelto por SAPUME
										fieldValue = DateUtils.changeDateMask(fieldValue.toString(), SAPUMEUtil.SAPUME_DATE_MASK, config.getSapDateMask());
										validFrom = DateUtils.getDateFromStr((String)fieldValue, config.getSapDateMask());
										
									} else if (attName.equals(SAPUMEConstants.SAPUME_FIELD_ISLOCKED)) {
										// Obtenemos el valor de bloqueo del usuario
										String userLockStatus = ((String)fieldValue).trim();
										if (userLockStatus.equalsIgnoreCase(SAPUMEConstants.SAPUME_VALUE_USERLOCK_YES)) {
											fieldValue = SAPUMEConstants.SAPUME_VALUE_USERLOCK_YES;
										} else if (userLockStatus.equalsIgnoreCase(SAPUMEConstants.SAPUME_VALUE_USERLOCK_NO)) {
											fieldValue = SAPUMEConstants.SAPUME_VALUE_USERLOCK_NO;
										}
										
									} else {
										fieldValue = ((String)fieldValue).trim();
									}
									
								} else {
									// Si el valor que se obtiene es null, se devuelve una cadena vacía
									fieldValue = "";
								}
								logger.debug("Final field value: " + attName + "=" + fieldValue);
								
								attsList.add(new AttributeBean(attName, fieldValue));
								logger.debug("Attribute added to list");
								
							}
							
						} catch (Exception e) {
							logger.error("Error processing attribute " + attName + ": " + e.getMessage());
							logger.debug("Continue with next field...");
						}
					}
					
					provisionSAPUMEUserBean.setAttsList(attsList);
					logger.debug("Attribute list set in ProvisionSAPUMEUserBean object");
					
					// Gestion del estado en funcion de las fechas validfrom, validto
					Date currentDate = DateUtils.getCurrentDate(false);
					logger.debug("CURRENT DATE: " + DateUtils.getStrFromDate(currentDate, config.getSapDateMask()));
					if (validTo != null)
					{						
						logger.debug("validto: " + DateUtils.getStrFromDate(validTo, config.getSapDateMask()));
						
						if (validFrom != null)
						{
							// Tenemos informados validto y validfrom 
							logger.debug("validfrom: " + DateUtils.getStrFromDate(validFrom, config.getSapDateMask()));
							
							if ((currentDate.after(validFrom) || currentDate.equals(validFrom)) && (currentDate.before(validTo) || currentDate.equals(validTo)))
							{
								logger.debug("Current date is between validfrom and validto user dates");
								
							} else {
								logger.debug("Current date is not in the range validfrom and validto --> User disabled");
								provisionSAPUMEUserBean.setFlagDisable(true);
								logger.debug("User disable flag set true");
							}
							
						} else {
							
							// Tenemos informado sólo validto
							if (currentDate.after(validTo))
							{
								logger.debug("CURRENT DATE > validto --> User disabled");
								provisionSAPUMEUserBean.setFlagDisable(true);
								logger.debug("User disable flag set true");
							}
						}
						
					} else if (validFrom != null) {
						
						// Tenemos informado sólo validfrom
						if (currentDate.before(validFrom))
						{
							logger.debug("CURRENT DATE < validfrom --> User disabled");
							provisionSAPUMEUserBean.setFlagDisable(true);
							logger.debug("User disable flag set true");
						}
					}
					if (!provisionSAPUMEUserBean.isFlagDisable())
						logger.debug("User has date values to be considered as Active");

					// Gestión de grupos
					if (groupsList != null && groupsList.size() > 0)
					{
						logger.debug("Groups must necersary obtain now ...");
						// Se construye la lista de beans de grupos a partir de la lista de identificadores de grupos devueltos por SAPUME
						List<SAPUMEGroupBean> sapumeGrpsList = SAPUMEUtil.buildUserGroupBeanListFromGroupIds(groupsList, connection, config);
						if (sapumeGrpsList != null && sapumeGrpsList.size() > 0)
						{
							provisionSAPUMEUserBean.setGroupsList(sapumeGrpsList);
							logger.debug("ProvisionSAPUMEUserBean updated with Groups list asigned to user");
							
						} else {
							logger.debug("No any SAPUMEGroupBean obatined after parse ids");
						}
					}
					
					// Gestión de roles
					if (rolesList != null && rolesList.size() > 0)
					{
						logger.debug("Roles must necersary obtain now ...");
						// Se construye la lista de beans de roles a partir de la lista de identificadores de roles devueltos por SAPUME
						List<SAPUMERoleBean> sapumeRolesList = SAPUMEUtil.buildUserRoleBeanListFromRoleIds(rolesList, connection, config);
						if (sapumeRolesList != null && sapumeRolesList.size() > 0)
						{
							provisionSAPUMEUserBean.setRolesList(sapumeRolesList);
							logger.debug("ProvisionSAPUMEUserBean updated with Roles list asigned to user");
							
						} else {
							logger.debug("No any SAPUMERoleBean obatined after parse ids");
						}
					}
					
				}
				
			}
			
		
		} catch (Exception e) {
			logger.error("Generic error while try to build a ProvisionSAPUMEUserBean from SPML SearchResult: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error while try to build a ProvisionSAPUMEUserBean from SPML SearchResult: " + e.getMessage(), e);
		}
		
		return provisionSAPUMEUserBean;
	}
		
	private static List<SAPUMEGroupBean> buildUserGroupBeanListFromGroupIds(List<String> groupUniqueIdList, SAPUMEConnection connection, SAPUMEConfiguration config) throws SAPUMEConnectorException
	{
		List<SAPUMEGroupBean> sapumeGrpsList = null;
		logger.info("Start buildUserGroupBeanListFromGroupIds() method");
		
		try
		{
			logger.debug(SAPUMEConstants.SAPUME_FIELD_ASSIGNEDGROUPS + "=" + groupUniqueIdList);
			
			if (groupUniqueIdList != null && groupUniqueIdList.size() > 0)
			{
				sapumeGrpsList = new ArrayList<SAPUMEGroupBean>();
				for (String groupId : groupUniqueIdList)
				{
					logger.debug("Group UniqueID --> " + groupId);
					SAPUMEGroupBean sapumeGroupBean = SAPUMEUtil.getGroupDetailsFromUniqueID(groupId, connection, config);
					logger.debug("SAPUMEGroupBean created for Group and will be added to list");
					sapumeGrpsList.add(sapumeGroupBean);
				}
			}
			
		} catch (SAPUMEConnectorException e) {
			throw e;
			
		} catch (Exception e) {
			logger.error("Generic error while try to build a List of SAPUMEGroupBean from groups uniqueIDs lists: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error while try to build a List of SAPUMEGroupBean from groups uniqueIDs lists: " + e.getMessage(), e);
		}
		
		return sapumeGrpsList;
	}
	
	private static List<SAPUMERoleBean> buildUserRoleBeanListFromRoleIds(List<String> roleUniqueIdList, SAPUMEConnection connection, SAPUMEConfiguration config) throws SAPUMEConnectorException
	{
		List<SAPUMERoleBean> sapumeRolesList = null;
		logger.info("Start buildUserRolepBeanListFromRoleIds() method");
		
		try
		{
			logger.debug(SAPUMEConstants.SAPUME_FIELD_ASSIGNEDROLES + "=" + roleUniqueIdList);
			
			if (roleUniqueIdList != null && roleUniqueIdList.size() > 0)
			{
				sapumeRolesList = new ArrayList<SAPUMERoleBean>();
				for (String roleId : roleUniqueIdList)
				{
					logger.debug("Role UniqueID --> " + roleId);
					SAPUMERoleBean sapumeRoleBean = SAPUMEUtil.getRoleDetailsFromUniqueID(roleId, connection, config);
					logger.debug("SAPUMERoleBean created for Role and will be added to list");
					sapumeRolesList.add(sapumeRoleBean);
				}
			}
			
		} catch (SAPUMEConnectorException e) {
			throw e;
			
		} catch (Exception e) {
			logger.error("Generic error while try to build a List of SAPUMERoleBean from roles uniqueIDs lists: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error while try to build a List of SAPUMERoleBean from roles uniqueIDs lists: " + e.getMessage(), e);
		}
		
		return sapumeRolesList;
	}
	
	private static SAPUMEGroupBean executeSingleGroupSearch(Filter smplFilter, SAPUMEConnection connection, SAPUMEConfiguration config) throws SAPUMEConnectorException
	{
		SAPUMEGroupBean sapumeGroupBean = null;
		try
		{
			logger.debug("Start executeSingleGroupSearch() method");
			
			SearchRequest spmlSearchReq = new SearchRequest();
			// Definimos tipo objetos a buscar
			spmlSearchReq.setSearchBase(SAPUMEConstants.SAPUME_TARGETOBJCLASS_GROUP);
			// Establecemos el filtro de búsqueda para buscar a un grupo concreto 
			spmlSearchReq.setFilter(smplFilter);
			FilterTerm ft =smplFilter.getTerms().get(0);
			logger.info("Filter set to search: [Filter.FieldName=" + ft.getName() + ", Field.Operation=" + ft.getOperation() + ", Field.Value=" + ft.getValue() + "]");
			
			// Definimos los atributos a recuperar del grupo
			spmlSearchReq.addAttribute(SAPUMEConstants.SAPUME_FIELD_UNIQUENAME);
			spmlSearchReq.addAttribute(SAPUMEConstants.SAPUME_FIELD_UNIQUEID);
			spmlSearchReq.addAttribute(SAPUMEConstants.SAPUME_FIELD_DESCRIPTION);
			
			logger.debug("SPML request will be send to SAPUME...");
			SpmlResponse spmlResponse = connection.sendSPMLRequest(spmlSearchReq, config.isTraceSPMLRequest());
			logger.debug("SPML response received from SAPUME");
					
			List<SearchResult> listResults = ((SearchResponse)spmlResponse).getResults();
			if (listResults != null && listResults.size() > 0)
			{
				logger.info("Found " + listResults.size() + " groups in SAPUME repository");
				
				if (listResults.size() == 1)
				{
					logger.debug("Group obtained from SAPUME repository!");
					String groupUniqueID = listResults.get(0).getIdentifierString();
					logger.debug("Group UniqueID obtained: " + groupUniqueID);
					String groupUniquename = listResults.get(0).getAttribute(SAPUMEConstants.SAPUME_FIELD_UNIQUENAME).getValue().toString();
					logger.debug("Group Uniquename obtained: " + groupUniquename);
					String groupDescription = (listResults.get(0).getAttribute(SAPUMEConstants.SAPUME_FIELD_DESCRIPTION) != null &&
											   listResults.get(0).getAttribute(SAPUMEConstants.SAPUME_FIELD_DESCRIPTION).getValue() != null) ? 
											   listResults.get(0).getAttribute(SAPUMEConstants.SAPUME_FIELD_DESCRIPTION).getValue().toString() : ""; 
					sapumeGroupBean = new SAPUMEGroupBean(groupUniquename, groupUniqueID, groupDescription);
					logger.debug("SAPUMEGroupBean instance created");
					
				} else {
					logger.error("More than one group were found for filter search defined");
					throw new SAPUMEConnectorException("More than one group were found for filter search defined");
				}
				
			} else {
				logger.info("Not Group was found in SAPUME repository for filter search defined");
			}
			
		} catch (SAPUMEConnectorException e) {
			throw e;
			
		}  catch (Exception e) {
			logger.error("Generic error searching single Group in SAPUME: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error searching single Group in SAPUME: " + e.getMessage(), e);
		}
		return sapumeGroupBean;
	}
	
	
	private static SAPUMERoleBean executeSingleRoleSearch(Filter smplFilter, SAPUMEConnection connection, SAPUMEConfiguration config) throws SAPUMEConnectorException
	{
		SAPUMERoleBean sapumeRoleBean = null;
		try
		{
			logger.debug("Start executeSingleRoleSearch() method");
						
			SearchRequest spmlSearchReq = new SearchRequest();
			// Definimos tipo objetos a buscar
			spmlSearchReq.setSearchBase(SAPUMEConstants.SAPUME_TARGETOBJCLASS_ROLE);
			// Establecemos el filtro de búsqueda para buscar a un rol concreto 
			spmlSearchReq.setFilter(smplFilter);
			FilterTerm ft =smplFilter.getTerms().get(0);
			logger.info("Filter set to search: [Filter.FieldName=" + ft.getName() + ", Field.Operation=" + ft.getOperation() + ", Field.Value=" + ft.getValue() + "]");
			
			// Definimos los atributos a recuperar del grupo
			spmlSearchReq.addAttribute(SAPUMEConstants.SAPUME_FIELD_UNIQUENAME);
			spmlSearchReq.addAttribute(SAPUMEConstants.SAPUME_FIELD_UNIQUEID);
			spmlSearchReq.addAttribute(SAPUMEConstants.SAPUME_FIELD_DESCRIPTION);
			
			logger.debug("SPML request will be send to SAPUME...");
			SpmlResponse spmlResponse = connection.sendSPMLRequest(spmlSearchReq, config.isTraceSPMLRequest());
			logger.debug("SPML response received from SAPUME");
					
			List<SearchResult> listResults = ((SearchResponse)spmlResponse).getResults();
			if (listResults != null && listResults.size() > 0)
			{
				logger.info("Found " + listResults.size() + " roles in SAPUME repository");
				
				if (listResults.size() == 1)
				{
					logger.debug("Role obtained from SAPUME repository!");
					String roleUniqueID = listResults.get(0).getIdentifierString();
					logger.debug("Role UniqueID obtained: " + roleUniqueID);
					String roleUniquename = listResults.get(0).getAttribute(SAPUMEConstants.SAPUME_FIELD_UNIQUENAME).getValue().toString();
					logger.debug("Role Uniquename obtained: " + roleUniquename);
					String roleDescription = (listResults.get(0).getAttribute(SAPUMEConstants.SAPUME_FIELD_DESCRIPTION) != null &&
							   				  listResults.get(0).getAttribute(SAPUMEConstants.SAPUME_FIELD_DESCRIPTION).getValue() != null) ? 
							   				  listResults.get(0).getAttribute(SAPUMEConstants.SAPUME_FIELD_DESCRIPTION).getValue().toString() : "";
					sapumeRoleBean = new SAPUMERoleBean(roleUniquename, roleUniqueID, roleDescription);
					logger.debug("SAPUMERoleBean instance created");
					
				} else {
					logger.error("More than one role were found for filter search defined");
					throw new SAPUMEConnectorException("More than one role were found for filter search defined");
				}
				
			} else {
				logger.info("Not Role was found in SAPUME repository for filter search defined");
			}
			
		} catch (SAPUMEConnectorException e) {
			throw e;
			
		}  catch (Exception e) {
			logger.error("Generic error searching single Role in SAPUME: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error searching single Role in SAPUME: " + e.getMessage(), e);
		}
		return sapumeRoleBean;
	}
	
	private static void updateModifyRequestForMembershipGroups(List<String> groupsNameForAdd, List<String> groupsNameForDelete, ModifyRequest spmlModifyRequest, SAPUMEConnection connection, SAPUMEConfiguration config) throws SAPUMEConnectorException
	{
		try
		{
			logger.info("Start updateModifyRequestForMembershipGroups() method");
			
			logger.debug("List groups to add: " + SAPUMEUtil.getListForPrint(groupsNameForAdd, "groupsForAdd"));
			if (groupsNameForAdd != null && groupsNameForAdd.size() > 0)
			{
				for (String grpToAdd : groupsNameForAdd)
				{
					logger.info("Group '" + grpToAdd + "' details wil be obtained...");
					SAPUMEGroupBean sapumeGroupBean = SAPUMEUtil.getGroupDetailsFromUniqueName(grpToAdd, connection, config);
					if (sapumeGroupBean != null)
					{
						logger.info("Group details were obtained from SAPUME. UniqueID: " + sapumeGroupBean.getUniqueID());
						Modification spmlModif = new Modification(SAPUMEConstants.SAPUME_FIELD_ASSIGNEDGROUPS, sapumeGroupBean.getUniqueID());
						spmlModif.setOperation(SAPUMEConstants.SAPUME_MEMBERSHIP_OPERATION_ADD);
						spmlModifyRequest.addModification(spmlModif);
						logger.debug("Modification added to SPML ModifyRequest: [Group: " + sapumeGroupBean.getUniqueID() + " - Operation: " + SAPUMEConstants.SAPUME_MEMBERSHIP_OPERATION_ADD + "]");
						
					} else {
						logger.error("Could not get group details in SAP UME for group name '" + grpToAdd + "'");
						throw new SAPUMEConnectorException("Not Group was found in SAPUME repository for filter search defined");
					}
				}
			}

			logger.debug("List groups to delete: " + SAPUMEUtil.getListForPrint(groupsNameForDelete, "groupsForDelete"));
			if (groupsNameForDelete != null && groupsNameForDelete.size() > 0)
			{
				for (String grpToDelete : groupsNameForDelete)
				{
					logger.info("Group details wil be obtained...");
					SAPUMEGroupBean sapumeGroupBean = SAPUMEUtil.getGroupDetailsFromUniqueName(grpToDelete, connection, config);
					if (sapumeGroupBean != null)
					{
						logger.info("Group details were obtained from SAPUME. UniqueID: " + sapumeGroupBean.getUniqueID());
						Modification spmlModif = new Modification(SAPUMEConstants.SAPUME_FIELD_ASSIGNEDGROUPS, sapumeGroupBean.getUniqueID());
						spmlModif.setOperation(SAPUMEConstants.SAPUME_MEMBERSHIP_OPERATION_DELETE);
						spmlModifyRequest.addModification(spmlModif);
						logger.debug("Modification added to SPML ModifyRequest: [Group: " + sapumeGroupBean.getUniqueID() + " - Operation: " + SAPUMEConstants.SAPUME_MEMBERSHIP_OPERATION_DELETE + "]");
						
					} else {
						logger.error("Could not get group details in SAP UME for group name '" + grpToDelete + "'");
						throw new SAPUMEConnectorException("Could not get group details in SAP UME for group name '" + grpToDelete + "'");
					}
				}
			}
			
			logger.debug("ModifyRequest was sucess update");
			
		}  catch (Exception e) {
			logger.error("Generic error updating spml ModifyRequest for membership groups for user: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error updating spml ModifyRequest for membership groups for user: " + e.getMessage(), e);
		}
	}
	
	private static void updateModifyRequestForMembershipRoles(List<String> rolesNameForAdd, List<String> rolesNameForDelete, ModifyRequest spmlModifyRequest, SAPUMEConnection connection, SAPUMEConfiguration config) throws SAPUMEConnectorException
	{
		try
		{
			logger.info("Start updateModifyRequestForMembershipRoles() method");
		
			logger.debug("List roles to add: " + SAPUMEUtil.getListForPrint(rolesNameForAdd, "rolesForAdd"));
			if (rolesNameForAdd != null && rolesNameForAdd.size() > 0)
			{
				for (String roleToAdd : rolesNameForAdd)
				{
					logger.info("Role '" + roleToAdd + "'details wil be obtained...");
					SAPUMERoleBean sapumeRoleBean = SAPUMEUtil.getRoleDetailsFromUniqueName(roleToAdd, connection, config);
					if (sapumeRoleBean != null)
					{
						logger.info("Role details were obtained from SAPUME. UniqueID: " + sapumeRoleBean.getUniqueID());
						Modification spmlModif = new Modification(SAPUMEConstants.SAPUME_FIELD_ASSIGNEDROLES, sapumeRoleBean.getUniqueID());
						spmlModif.setOperation(SAPUMEConstants.SAPUME_MEMBERSHIP_OPERATION_ADD);
						spmlModifyRequest.addModification(spmlModif);
						logger.debug("Modification added to SPML ModifyRequest: [Role: " + sapumeRoleBean.getUniqueID() + " - Operation: " + SAPUMEConstants.SAPUME_MEMBERSHIP_OPERATION_ADD + "]");
						
					} else {
						logger.error("Could not get role details in SAP UME for role name '" + roleToAdd + "'");
						throw new SAPUMEConnectorException("Could not get role details in SAP UME for role name '" + roleToAdd + "'");
					}
				}
			}

			logger.debug("List roles to delete: " + SAPUMEUtil.getListForPrint(rolesNameForDelete, "rolesForDelete"));
			if (rolesNameForDelete != null && rolesNameForDelete.size() > 0)
			{
				for (String roleToDelete : rolesNameForDelete)
				{
					logger.info("Role details wil be obtained...");
					SAPUMERoleBean sapumeRoleBean = SAPUMEUtil.getRoleDetailsFromUniqueName(roleToDelete, connection, config);
					if (sapumeRoleBean != null)
					{
						logger.info("Role details were obtained from SAPUME. UniqueID: " + sapumeRoleBean.getUniqueID());
						Modification spmlModif = new Modification(SAPUMEConstants.SAPUME_FIELD_ASSIGNEDROLES, sapumeRoleBean.getUniqueID());
						spmlModif.setOperation(SAPUMEConstants.SAPUME_MEMBERSHIP_OPERATION_DELETE);
						spmlModifyRequest.addModification(spmlModif);
						logger.debug("Modification added to SPML ModifyRequest: [Role: " + sapumeRoleBean.getUniqueID() + " - Operation: " + SAPUMEConstants.SAPUME_MEMBERSHIP_OPERATION_DELETE + "]");
						
					} else {
						logger.error("Could not get role details in SAP UME for role name '" + roleToDelete + "'");
						throw new SAPUMEConnectorException("Could not get role details in SAP UME for role name '" + roleToDelete + "'");
					}
				}
			}
			
			logger.debug("ModifyRequest was sucess update");
			
		}  catch (Exception e) {
			logger.error("Generic error updating spml ModifyRequest for membership roles for user: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error updating spml ModifyRequest for membership roles for user: " + e.getMessage(), e);
		}
	}
		
	private static List<String> getSAPUMEAuthorElementsAsignedToUser(String logonname, String authorElementAttName, SAPUMEConnection connection, SAPUMEConfiguration config) throws SAPUMEConnectorException
	{
		List<String> sapumeElements = null;
		
		try
		{			
			logger.info("Start getSAPUMEAuthorElementsAsignedToUser() method");
			
			logger.info("logonname: " + logonname);
			logger.info("authorElementAttName: " + authorElementAttName);

			SearchRequest spmlSearchReq = new SearchRequest();
			// Definimos tipo objetos a buscar
			spmlSearchReq.setSearchBase(SAPUMEConstants.SAPUME_TARGETOBJCLASS_USER);
			// Construimos el filtro de búsqueda para buscar a un usuario concreto
			Filter filter = SAPUMEFilterGenerator.buildSPMLFiltertoMatchUserByLogonName(logonname); 
			spmlSearchReq.setFilter(filter);
			// Definimos los atributos a recuperar del usuario
			spmlSearchReq.addAttribute(authorElementAttName);
			
			logger.info("SPML request will be send to SAPUME...");
			SpmlResponse spmlResponse = connection.sendSPMLRequest(spmlSearchReq, config.isTraceSPMLRequest());
			logger.info("SPML response received from SAPUME");
			
			List<SearchResult> listResults = ((SearchResponse)spmlResponse).getResults();
			if (listResults != null && listResults.size() > 0)
			{
				if (listResults.size() == 1)
				{
					logger.info("User " + logonname + " exists in SAPUME repository");
					SearchResult searchResult = listResults.get(0);
					if (searchResult.getAttribute(authorElementAttName) != null)
					{
						if ((searchResult.getAttributeValue(authorElementAttName) instanceof ArrayList))
						{
							sapumeElements = (ArrayList)searchResult.getAttributeValue(authorElementAttName);
							
						} else {
							sapumeElements = new ArrayList<String>();
							sapumeElements.add(searchResult.getAttributeValue(authorElementAttName).toString());
						}
					}

				} else {
					logger.error("Found more than one account for user ID specified");
					throw new SAPUMEConnectorException("Found more than one account for user ID specified");
				}
				
			} else {
				logger.info("User " + logonname + " cannot be found in SAPUME repository");
				throw new SAPUMEConnectorException("User " + logonname + " cannot be found in SAPUME repository");
			}

			logger.debug((sapumeElements != null && sapumeElements.size() > 0) ? sapumeElements.size() + " Authorizative elements (" + authorElementAttName +") obtained for user" : "No Authorizative elements (" + authorElementAttName +") obtained for user");
			
		}  catch (SAPUMEConnectorException e) {
			throw e;
			
		}  catch (Exception e) {
			logger.error("Generic error trying to get authorizative elements (" + authorElementAttName +") assigned to a user in SAPUME: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error trying to get authorizative elements (" + authorElementAttName +") assigned to a user in SAPUME: " + e.getMessage(), e);
		}
		
		return sapumeElements;
	}
	
	private static String getSearchFilterFromSearchQuery(String searchQuery) throws SAPUMEConnectorException
	{
		try
		{
			logger.info("Start getSearchFilterFromSearchQuery() method");
			String searchFilter = "";
			if (searchQuery != null)
			{
				if (searchQuery.contains(SAPUMEConstants.SAPUME_RECON_FILTER_INCREMENTAL_TIMESTAMP_FLAG))
				{
					searchFilter = searchQuery.substring(0, searchQuery.indexOf(SAPUMEConstants.SAPUME_RECON_FILTER_INCREMENTAL_TIMESTAMP_FLAG));
				} else {
					searchFilter = searchQuery;
				}
			}
			logger.debug("searchFilter: " + searchFilter);
			return searchFilter;
			
		} catch (Exception e) {
			logger.error("Incorrect SearchQuery format: " + searchQuery);
			logger.error("Error trying to obtain search filter from SearchQuery received: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Incorrect SearchQuery format", e);
		}
	}
	
	private static String getUpdateSinceFromSearchQuery(String searchQuery) throws SAPUMEConnectorException
	{
		try
		{
			logger.info("Start getUpdateSinceFromSearchQuery() method");
			String updateSince = "";
			if (searchQuery != null)
			{
				if (searchQuery.contains(SAPUMEConstants.SAPUME_RECON_FILTER_INCREMENTAL_TIMESTAMP_FLAG))
				{
					int i = searchQuery.indexOf(SAPUMEConstants.SAPUME_RECON_FILTER_INCREMENTAL_TIMESTAMP_FLAG);
					String timeStamp = searchQuery.substring(i);
					String[] timeStampSplited = timeStamp.split("=");
					if (timeStampSplited != null && timeStampSplited.length == 2)
						updateSince = timeStampSplited[1].trim();
				}
			}
			logger.debug("updateSince: " + updateSince);
			return updateSince;
			
		} catch (Exception e) {
			logger.error("Incorrect SearchQuery format: " + searchQuery);
			logger.error("Error trying to obtain update since param from SearchQuery received: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Incorrect SearchQuery format", e);
		}
	}
	
	private static List<ProvisionSAPUMEUserBean> reconSAPUMEUsersAccounts(String searchFilter, String lastModifyDate, List<String> attributes, SAPUMEConnection connection, SAPUMEConfiguration config) throws SAPUMEConnectorException
	{
		List<ProvisionSAPUMEUserBean> listProvSAPUMEUsers = new ArrayList<ProvisionSAPUMEUserBean>(); 
		
		try
		{
			logger.debug("Start reconSAPUMEUsersAccounts() method");
			logger.debug("searchFilter: " + searchFilter);
			logger.debug("lastModifyDate: " + lastModifyDate);
			logger.debug("attributes: " + attributes);
			
			SearchRequest spmlSearchReq = new SearchRequest();
			// Definimos tipo objetos a buscar
			spmlSearchReq.setSearchBase(SAPUMEConstants.SAPUME_TARGETOBJCLASS_USER);
			// Construimos el filtro de búsqueda
			Filter sapumeSearchFilter = SAPUMEFilterGenerator.buildSPMLFilterFromIDMExpression(searchFilter, lastModifyDate);
			logger.debug("SAPUME Search Filter build");
			spmlSearchReq.setFilter(sapumeSearchFilter);
			// Definimos los atributos a recuperar del usuario
			for (String oneAtt : attributes)
			{
				spmlSearchReq.addAttribute(oneAtt);
			}
			
			logger.info("SPML request will be send to SAPUME...");
			SpmlResponse spmlResponse = connection.sendSPMLRequest(spmlSearchReq, config.isTraceSPMLRequest());
			logger.info("SPML response received from SAPUME");
			
			SAPUMEUtil.validateSAPUMESpmlResponse(spmlResponse, "");
			logger.info("SPML response passed failure validation");
			
			List<SearchResult> listResults = ((SearchResponse)spmlResponse).getResults();
			if (listResults != null && listResults.size() > 0)
			{
				logger.info("Found " + listResults.size() + " users in SAPUME repository");
				
				for (SearchResult searchResult : listResults)
				{
					String uid = searchResult.getIdentifierString();
					logger.debug("User UniqueID: " + uid);
					String logonname = searchResult.getAttribute(SAPUMEConstants.SAPUME_FIELD_LOGONNAME).getValue().toString();
					logger.debug("User UniqueName: " + logonname);
					logger.debug("ProvisionSAPUMEUserBean instance will be create from SMPL SearchResult return data for user " + logonname);
					ProvisionSAPUMEUserBean provisionSAPUMEUserBean = SAPUMEUtil.buildProvSAPUMEUserFromSPMLSearchResult(logonname, attributes, searchResult, connection, config);
					if (provisionSAPUMEUserBean != null)
					{
						logger.debug("ProvisionSAPUMEUserBean was build successfully");
						logger.debug(provisionSAPUMEUserBean.getBeanInPrintedFormat());
						
						listProvSAPUMEUsers.add(provisionSAPUMEUserBean);
						logger.debug("ProvisionSAPUMEUserBean for accout " + provisionSAPUMEUserBean.getUserID() + " added to List");
						
					} else {
						logger.warn("Cannot create ProvisionSAPUMEUserBean instance for account " + logonname + ". Skip to next account...");
					}
				}
			
			} else {
				logger.debug("No user accounts found in SAPUME");
			}
			
			logger.debug("Total SAPUME user accounts obtained: " + listProvSAPUMEUsers.size());
	
		} catch (SAPUMEConnectorException e) {
			throw e;
			
		} catch (Exception e) {
			logger.error("Generic error trying to recon SAPUME users accounts: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error trying to recon SAPUME users accounts: " + e.getMessage(), e);
		}
		return listProvSAPUMEUsers;
	}
	
	private static List<SAPUMEGroupBean> reconSAPUMEGroups(String searchFilter, SAPUMEConnection connection, SAPUMEConfiguration config) throws SAPUMEConnectorException
	{
		List<SAPUMEGroupBean> listSAPUMEGroups = new ArrayList<SAPUMEGroupBean>(); 
		
		try
		{
			logger.debug("Start reconSAPUMEGroups() method");
			logger.debug("searchFilter: " + searchFilter);
						
			SearchRequest spmlSearchReq = new SearchRequest();
			// Definimos tipo objetos a buscar
			spmlSearchReq.setSearchBase(SAPUMEConstants.SAPUME_TARGETOBJCLASS_GROUP);
			// Construimos el filtro de búsqueda
			Filter sapumeSearchFilter = SAPUMEFilterGenerator.buildSPMLFilterFromIDMExpression(searchFilter, "");
			logger.debug("SAPUME Search Filter build");
			spmlSearchReq.setFilter(sapumeSearchFilter);
			// Definimos los atributos a recuperar del grupo
			spmlSearchReq.addAttribute(SAPUMEConstants.SAPUME_FIELD_UNIQUENAME);
			spmlSearchReq.addAttribute(SAPUMEConstants.SAPUME_FIELD_UNIQUEID);
			spmlSearchReq.addAttribute(SAPUMEConstants.SAPUME_FIELD_DESCRIPTION);
			
			logger.info("SPML request will be send to SAPUME...");
			SpmlResponse spmlResponse = connection.sendSPMLRequest(spmlSearchReq, config.isTraceSPMLRequest());
			logger.info("SPML response received from SAPUME");
			
			SAPUMEUtil.validateSAPUMESpmlResponse(spmlResponse, "");
			logger.info("SPML response passed failure validation");
			
			List<SearchResult> listResults = ((SearchResponse)spmlResponse).getResults();
			if (listResults != null && listResults.size() > 0)
			{
				logger.info("Found " + listResults.size() + " groups in SAPUME repository");
				
				for (SearchResult searchResult : listResults)
				{
					String uid = searchResult.getIdentifierString();
					logger.debug("Group UniqueID: " + uid);
					String uniqueName = searchResult.getAttribute(SAPUMEConstants.SAPUME_FIELD_UNIQUENAME).getValue().toString();
					logger.debug("Group UniqueName: " + uniqueName);
					String description = (searchResult.getAttribute(SAPUMEConstants.SAPUME_FIELD_DESCRIPTION) != null &&
										  searchResult.getAttribute(SAPUMEConstants.SAPUME_FIELD_DESCRIPTION).getValue() != null) ? 
										  searchResult.getAttribute(SAPUMEConstants.SAPUME_FIELD_DESCRIPTION).getValue().toString() : ""; 
					logger.debug("Group Description: " + description);
					SAPUMEGroupBean oneGroupBean = new SAPUMEGroupBean(uniqueName, uid, description);
					logger.debug("SAPUMEGroupBean was build successfully");
					logger.debug(oneGroupBean.getBeanInPrintedFormat());
					
					listSAPUMEGroups.add(oneGroupBean);
					logger.debug("SAPUMEGroupBean added to List");
				}
			
			} else {
				logger.debug("No Groups found in SAPUME");
			}
			
			logger.debug("Total SAPUME Groups obtained: " + listSAPUMEGroups.size());
	
		} catch (SAPUMEConnectorException e) {
			throw e;
			
		} catch (Exception e) {
			logger.error("Generic error trying to recon SAPUME Groups: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error trying to recon SAPUME Groups: " + e.getMessage(), e);
		}
		return listSAPUMEGroups;
	}
	
	private static List<SAPUMERoleBean> reconSAPUMERoles(String searchFilter, SAPUMEConnection connection, SAPUMEConfiguration config) throws SAPUMEConnectorException
	{
		List<SAPUMERoleBean> listSAPUMERoles = new ArrayList<SAPUMERoleBean>(); 
		
		try
		{
			logger.debug("Start reconSAPUMERoles() method");
			logger.debug("searchFilter: " + searchFilter);
						
			SearchRequest spmlSearchReq = new SearchRequest();
			// Definimos tipo objetos a buscar
			spmlSearchReq.setSearchBase(SAPUMEConstants.SAPUME_TARGETOBJCLASS_ROLE);
			// Construimos el filtro de búsqueda
			Filter sapumeSearchFilter = SAPUMEFilterGenerator.buildSPMLFilterFromIDMExpression(searchFilter, "");
			logger.debug("SAPUME Search Filter build");
			spmlSearchReq.setFilter(sapumeSearchFilter);
			// Definimos los atributos a recuperar del rol
			spmlSearchReq.addAttribute(SAPUMEConstants.SAPUME_FIELD_UNIQUENAME);
			spmlSearchReq.addAttribute(SAPUMEConstants.SAPUME_FIELD_UNIQUEID);
			spmlSearchReq.addAttribute(SAPUMEConstants.SAPUME_FIELD_DESCRIPTION);
			
			logger.info("SPML request will be send to SAPUME...");
			SpmlResponse spmlResponse = connection.sendSPMLRequest(spmlSearchReq, config.isTraceSPMLRequest());
			logger.info("SPML response received from SAPUME");
			
			SAPUMEUtil.validateSAPUMESpmlResponse(spmlResponse, "");
			logger.info("SPML response passed failure validation");
			
			List<SearchResult> listResults = ((SearchResponse)spmlResponse).getResults();
			if (listResults != null && listResults.size() > 0)
			{
				logger.info("Found " + listResults.size() + " roles in SAPUME repository");
				
				for (SearchResult searchResult : listResults)
				{
					String uid = searchResult.getIdentifierString();
					logger.debug("Role UniqueID: " + uid);
					String uniqueName = searchResult.getAttribute(SAPUMEConstants.SAPUME_FIELD_UNIQUENAME).getValue().toString();
					logger.debug("Role UniqueName: " + uniqueName);
					String description = (searchResult.getAttribute(SAPUMEConstants.SAPUME_FIELD_DESCRIPTION) != null &&
										  searchResult.getAttribute(SAPUMEConstants.SAPUME_FIELD_DESCRIPTION).getValue() != null) ? 
										  searchResult.getAttribute(SAPUMEConstants.SAPUME_FIELD_DESCRIPTION).getValue().toString() : "";
			   		logger.debug("Role Description: " + description);
			   		SAPUMERoleBean oneRoleBean = new SAPUMERoleBean(uniqueName, uid, description);
					logger.debug("SAPUMERoleBean was build successfully");
					logger.debug(oneRoleBean.getBeanInPrintedFormat());
					
					listSAPUMERoles.add(oneRoleBean);
					logger.debug("SAPUMERoleBean added to List");
				}
			
			} else {
				logger.debug("No Roles found in SAPUME");
			}
			
			logger.debug("Total SAPUME Roles obtained: " + listSAPUMERoles.size());
	
		} catch (SAPUMEConnectorException e) {
			throw e;
			
		} catch (Exception e) {
			logger.error("Generic error trying to recon SAPUME Roles: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error trying to recon SAPUME Roles: " + e.getMessage(), e);
		}
		return listSAPUMERoles;
	}
	
}
