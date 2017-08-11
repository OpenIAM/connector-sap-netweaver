package org.openiam.connector.sapume.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.connector.sapume.core.SAPUMEContext;
import org.openiam.connector.sapume.core.beans.AttributeBean;
import org.openiam.connector.sapume.core.beans.ProvisionSAPUMEUserBean;
import org.openiam.connector.sapume.core.beans.SAPUMEGroupBean;
import org.openiam.connector.sapume.core.beans.SAPUMERoleBean;
import org.openiam.connector.sapume.core.beans.SAPUMESystemBean;
import org.openiam.connector.sapume.core.common.ChangePasswordTypes;
import org.openiam.connector.sapume.core.common.SAPUMEConnectorException;
import org.openiam.connector.sapume.core.common.SAPUMEConstants;
import org.openiam.connector.sapume.core.common.SAPUMEUtil;
import org.openiam.connector.sapume.core.security.SecuredString;
import org.openiam.connector.sapume.service.types.ExtensibleAttribute;
import org.openiam.connector.sapume.service.wrapper.BaseAttribute;
import org.openiam.connector.sapume.service.wrapper.BaseAttributeContainer;
import org.openiam.connector.sapume.service.wrapper.BaseRequestType;
import org.openiam.connector.sapume.service.wrapper.PasswordRequest;


public class RequestReader<E extends BaseRequestType>
{
	/*************************************************************
     **********          OBJETOS INTERNOS              ***********
     *************************************************************/
	
	private static Log logger = LogFactory.getLog(RequestReader.class);

	private E request;
	private AttributeReader attributeReader;
	private String userID;

	/*************************************************************
     ***********             CONSTRUCTORES             *********** 
     *************************************************************/
		
	public RequestReader(E request)
	{
		logger.debug("RequestReader constructor");
		this.request = request;
		this.attributeReader = AttributeReader.getInstance(request);
		this.userID = request.getObjectIdentity();
	}

	
	/*************************************************************
     ***********           MÉTODOS PÚBLICOS            *********** 
     *************************************************************/
	
	public ProvisionSAPUMEUserBean createProvisionUserForAddOperation(SAPUMEContext sapumeContext) throws SAPUMEConnectorException
	{
		try
		{
			logger.debug("Start createProvisionUserForAddOperation() method");
			
			this.validate(ProvisionSAPUMEUserBean.getSAPUMERequiredFields());
			logger.debug("Requires fields validation passed");
			
			Map<String, Object> attributesMap = this.getUserAttributes();
			logger.debug("User attributes map obtained form request");
			if (logger.isDebugEnabled())
			{
				for (Map.Entry<String, Object> attEntry : attributesMap.entrySet())
				{
					if (!attEntry.getKey().equals(SAPUMEConstants.SAPUME_FIELD_PASSWORD))
						logger.debug("[" + attEntry.getKey() + "," + attEntry.getValue() + "]");
					else
						logger.debug("[" + attEntry.getKey() + ",xxxxx]");
				}
			}
			
			ProvisionSAPUMEUserBean newUser = new ProvisionSAPUMEUserBean(this.userID);
			
			if (attributesMap.containsKey(SAPUMEConstants.SAPUME_FIELD_ASSIGNEDGROUPS))
			{
				List<SAPUMEGroupBean> addGroups = new ArrayList<SAPUMEGroupBean>();
				List<SAPUMEGroupBean> deleteGroups = new ArrayList<SAPUMEGroupBean>();
				this.setGroupsMembership(addGroups, deleteGroups, sapumeContext, false);
				logger.debug("AddGroups list formed: " + SAPUMEUtil.getGroupsForPrint(addGroups));
				logger.debug("DeleteGroups list formed: " + SAPUMEUtil.getGroupsForPrint(deleteGroups));
				
				attributesMap.remove(SAPUMEConstants.SAPUME_FIELD_ASSIGNEDGROUPS);
				logger.debug("Attribute " + SAPUMEConstants.SAPUME_FIELD_ASSIGNEDGROUPS + " was removed from map, its already processed and groups lists created");
				newUser.setGroupsForAdd(addGroups);
				newUser.setGroupsForDelete(deleteGroups);
				logger.debug("Groups information was added to ProvisionSAPUMEUserBean object");
				newUser.setFlagMngGroups(true);
				logger.debug("Manage Groups flag was activated in ProvisionSAPUMEUserBean object");
			}
			
			if (attributesMap.containsKey(SAPUMEConstants.SAPUME_FIELD_ASSIGNEDROLES))
			{
				List<SAPUMERoleBean> addRoles = new ArrayList<SAPUMERoleBean>();
				List<SAPUMERoleBean> deleteRoles = new ArrayList<SAPUMERoleBean>();
				this.setRolesMembership(addRoles, deleteRoles, sapumeContext, false);
				logger.debug("AddRoles list formed: " + SAPUMEUtil.getRolesForPrint(addRoles));
				logger.debug("DeleteRoles list formed: " + SAPUMEUtil.getRolesForPrint(deleteRoles));
				
				attributesMap.remove(SAPUMEConstants.SAPUME_FIELD_ASSIGNEDROLES);
				logger.debug("Attribute " + SAPUMEConstants.SAPUME_FIELD_ASSIGNEDROLES + " was removed from map, its already processed and roles lists created");
				newUser.setRolesForAdd(addRoles);
				newUser.setRolesForDelete(deleteRoles);
				logger.debug("Roles information was added to ProvisionSAPUMEUserBean object");
				newUser.setFlagMngRoles(true);
				logger.debug("Manage Roles flag was activated in ProvisionSAPUMEUserBean object");
			}
						
			newUser.setAttsListFromMap(attributesMap);
			logger.debug("Attributes List information was added to ProvisionSAPUMEUserBean object");
			
			logger.debug("ProvisionSAPUMEUserBean instance created");
			
			return newUser;
			
		} catch (ValidationException e) {
			logger.error("Validation fields are not passed: " + e.toString(), e);
			throw new SAPUMEConnectorException("Validation fields are not passed: " + e.toString(), e);
			
		} catch (Exception e) {
			logger.error("Generic error creating ProvisionSAPUMEUserBean object from request attributes : " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error creating ProvisionSAPUMEUserBean object from request attributes : " + e.getMessage(), e);
		}
	}
	
	
	public ProvisionSAPUMEUserBean createProvisionUserForModifyOperation(SAPUMEContext sapumeContext) throws SAPUMEConnectorException
	{
		try
		{
			logger.debug("Start createProvisionUserForModifyOperation() method");
			
			Map<String, Object> attributesMap = this.getUserAttributes();
			
			logger.debug("User attributes map obtained form request");
			if (logger.isDebugEnabled())
			{
				for (Map.Entry<String, Object> attEntry : attributesMap.entrySet())
				{
					if (!attEntry.getKey().equals(SAPUMEConstants.SAPUME_FIELD_PASSWORD))
						logger.debug("[" + attEntry.getKey() + "," + attEntry.getValue() + "]");
					else
						logger.debug("[" + attEntry.getKey() + ",xxxxx]");
						
				}
			}			
			
			ProvisionSAPUMEUserBean provSapUser = new ProvisionSAPUMEUserBean(this.userID);	

			if (attributesMap.containsKey(SAPUMEConstants.SAPUME_FIELD_ASSIGNEDGROUPS))
			{
				List<SAPUMEGroupBean> addGroups = new ArrayList<SAPUMEGroupBean>();
				List<SAPUMEGroupBean> deleteGroups = new ArrayList<SAPUMEGroupBean>();
				this.setGroupsMembership(addGroups, deleteGroups, sapumeContext, true);
				logger.debug("AddGroups list formed: " + SAPUMEUtil.getGroupsForPrint(addGroups));
				logger.debug("DeleteGroups list formed: " + SAPUMEUtil.getGroupsForPrint(deleteGroups));
				
				attributesMap.remove(SAPUMEConstants.SAPUME_FIELD_ASSIGNEDGROUPS);
				logger.debug("Attribute " + SAPUMEConstants.SAPUME_FIELD_ASSIGNEDGROUPS + " was removed from map, its already processed and groups lists created");
				provSapUser.setGroupsForAdd(addGroups);
				provSapUser.setGroupsForDelete(deleteGroups);
				logger.debug("Groups information was added to ProvisionSAPUMEUserBean object");
				provSapUser.setFlagMngGroups(true);
				logger.debug("Manage Groups flag was activated in ProvisionSAPUMEUserBean object");
			}
			
			if (attributesMap.containsKey(SAPUMEConstants.SAPUME_FIELD_ASSIGNEDROLES))
			{
				List<SAPUMERoleBean> addRoles = new ArrayList<SAPUMERoleBean>();
				List<SAPUMERoleBean> deleteRoles = new ArrayList<SAPUMERoleBean>();
				this.setRolesMembership(addRoles, deleteRoles, sapumeContext, true);
				logger.debug("AddRoles list formed: " + SAPUMEUtil.getRolesForPrint(addRoles));
				logger.debug("DeleteRoles list formed: " + SAPUMEUtil.getRolesForPrint(deleteRoles));
				
				attributesMap.remove(SAPUMEConstants.SAPUME_FIELD_ASSIGNEDROLES);
				logger.debug("Attribute " + SAPUMEConstants.SAPUME_FIELD_ASSIGNEDROLES + " was removed from map, its already processed and roles lists created");
				provSapUser.setRolesForAdd(addRoles);
				provSapUser.setRolesForDelete(deleteRoles);
				logger.debug("Roles information was added to ProvisionSAPUMEUserBean object");
				provSapUser.setFlagMngRoles(true);
				logger.debug("Manage Roles flag was activated in ProvisionSAPUMEUserBean object");
			}
			
			provSapUser.setAttsListFromMap(attributesMap);
			logger.debug("Attributes List information was added to ProvisionSAPUMEUserBean object");
			
			logger.debug("ProvisionSAPUMEUserBean instance created");
			
			return provSapUser;
			
		} catch (Exception e) {
			logger.error("Generic error creating ProvisionSAPUMEUserBean object from request attributes : " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error creating ProvisionSAPUMEUserBean object from request attributes : " + e.getMessage(), e);
		}
	}
	
	public ProvisionSAPUMEUserBean createProvisionUserForSuspendOperation() throws SAPUMEConnectorException
	{
		try
		{
			logger.debug("Start createProvisionUserForSuspendOperation() method");
			
			ProvisionSAPUMEUserBean provUser = new ProvisionSAPUMEUserBean(this.userID);
			provUser.setFlagDisable(true);
			logger.info("Disable flag was activated in ProvisionSAPUMEUserBean object");
			
			logger.debug("ProvisionSAPUMEUserBean instance created");
			return provUser;
			
		} catch (Exception e) {
			logger.error("Generic error creating ProvisionSAPUMEUserBean object from request attributes : " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error creating ProvisionSAPUMEUserBean object from request attributes : " + e.getMessage(), e);
		}
	}

	
	public ProvisionSAPUMEUserBean createProvisionUserForResumeOperation() throws SAPUMEConnectorException
	{
		try
		{
			logger.debug("Start createProvisionUserForResumeOperation() method");
			
			ProvisionSAPUMEUserBean provUser = new ProvisionSAPUMEUserBean(this.userID);
			provUser.setFlagEnable(true);
			logger.info("Enable flag was activated in ProvisionSAPUMEUserBean object");
			
			logger.debug("ProvisionSAPUMEUserBean instance created");
			return provUser;
			
		} catch (Exception e) {
			logger.error("Generic error creating ProvisionSAPUMEUserBean object from request attributes : " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error creating ProvisionSAPUMEUserBean object from request attributes : " + e.getMessage(), e);
		}
	}
	
	
	public ProvisionSAPUMEUserBean createProvisionUserForResetPasswordOperation(ChangePasswordTypes changePwdType) throws SAPUMEConnectorException
	{
		try
		{
			logger.debug("Start createProvisionUserForResetPasswordOperation() method");
			
			ProvisionSAPUMEUserBean provUser = new ProvisionSAPUMEUserBean(this.userID);
			
			String newPwdValue = ((PasswordRequest)this.request).getPassword();
			SecuredString securedStr = new SecuredString(newPwdValue.toCharArray());
			newPwdValue = null;
			
			provUser.addNewAttribute(new AttributeBean(SAPUMEConstants.SAPUME_FIELD_PASSWORD, securedStr));
			logger.debug("Field " + SAPUMEConstants.SAPUME_FIELD_PASSWORD + " with the password to set was added in ProvisionSAPUMEUserBean object");
			
			// Marcamos para detectar que es un cambio de contraseña administrativo (es un ResetPassword y no un SetPassword)
			if (changePwdType.toString().equals(ChangePasswordTypes.RESET_PASSWORD.toString())) {
				provUser.setAdministrativePwdChange(true);
				logger.info("Administrative Pasword change flag was activated in ProvisionSAPUMEUserBean object");
			} else {
				provUser.setAdministrativePwdChange(false);
				logger.info("Administrative Pasword change flag was deactivated in ProvisionSAPUMEUserBean object");
			}
			
			logger.debug("ProvisionSAPUMEUserBean instance created");
			return provUser;
			
		} catch (Exception e) {
			logger.error("Generic error creating ProvisionSAPUMEUserBean object from request attributes : " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error creating ProvisionSAPUMEUserBean object from request attributes : " + e.getMessage(), e);
		}
	}
	
	
	public ProvisionSAPUMEUserBean createProvisionUserForDeleteOperation() throws SAPUMEConnectorException
	{
		try
		{
			logger.debug("Start createProvisionUserForDeleteOperation() method");
			
			ProvisionSAPUMEUserBean provUser = new ProvisionSAPUMEUserBean(this.userID);
			logger.debug("ProvisionSAPUMEUserBean instance created");
			return provUser;
			
		} catch (Exception e) {
			logger.error("Generic error creating ProvisionSAPUMEUserBean object from request attributes : " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error creating ProvisionSAPUMEUserBean object from request attributes : " + e.getMessage(), e);
		}
	}

	
	public SAPUMESystemBean createSAPUMESystemBean() throws SAPUMEConnectorException
	{
		try
		{
			logger.debug("Start createSAPUMESystemBean() method");
			String hostUrl = this.getHostUrl();
			logger.debug("SAPUME connection url obtained from request: " + hostUrl);
			String user = this.getHostLoginId();
			logger.debug("SAPUME connection user obtained from request: " + user);
			String pwd = this.getHostLoginPassword();
			SecuredString securedPwd = new SecuredString(pwd.toCharArray());
			pwd = null;
			logger.debug("SAPUME connection password obtained from request: " + "xxxxx");
			SAPUMESystemBean sapumeSystem = new SAPUMESystemBean(hostUrl, user, securedPwd);
			logger.debug("SAPUMESystemBean instance created");
			return sapumeSystem;
			
		} catch (Exception e) {
			logger.error("Generic error creating SAPUMESystemBean object from request attributes : " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error creating SAPUMESystemBean object from request attributes : " + e.getMessage(), e);
		}
	}
	
	public String getHostUrl()
	{
		return this.request.getHostUrl();
	}
	
	
	public String getHostLoginId()
	{
		return this.request.getHostLoginId();
	}

	
	public String getHostLoginPassword()
	{
		return this.request.getHostLoginPassword();
	}

	
	/*************************************************************
     ***********           MÉTODOS PRIVADOS            *********** 
     *************************************************************/
	
	private Map<String, Object> getUserAttributes() throws SAPUMEConnectorException
	{
		try
		{
			logger.debug("Start getUserAttributes() method");
			Map<String, Object> items = this.attributeReader.getAllValues();
			return items;
			
		} catch (Exception e) {
			logger.error("Generic error reading user attributes from request: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error reading user attributes from request: " + e.getMessage(), e);
		}
	}
	
	private void validate(String[] fields) throws ValidationException
	{
		logger.debug("Start validate() method");
		RequiredAttributeValidator validator = new RequiredAttributeValidator(this.attributeReader);
		validator.add(fields);
		validator.validateAllWithException();
	}
	
	private void setGroupsMembership(List<SAPUMEGroupBean> addGroups, List<SAPUMEGroupBean> deleteGroups, SAPUMEContext sapumeContext, boolean querySapume) throws SAPUMEConnectorException
	{
		try
		{
			logger.debug("Start setGroupsMembership() method");
			ExtensibleAttribute ea = this.attributeReader.getAttributeByNameAndType(SAPUMEConstants.SAPUME_FIELD_ASSIGNEDGROUPS, AttributeReader.DATA_TYPE_MEMBERSHIP);
			if (ea != null)
			{
				BaseAttributeContainer container = ea.getAttributeContainer();
				if (container != null)
				{
					List<SAPUMEGroupBean> sapumeGroups = null;
					if (querySapume) // En Creacion el usuairo no existirá por lo que no tenemos que buscar. En modificación si que habrá que consultar en SAPUME
					{
						// Recuperamos los grupos que el usuario tiene actualmente en SAPUME (obtenemos la lista completa)
						sapumeGroups = sapumeContext.getUserGroups(this.userID);
						if (sapumeGroups == null)
						{
							logger.debug("The user has not any Groups in SAPUME");
							sapumeGroups = new ArrayList<SAPUMEGroupBean>();
						} else {
							logger.debug("The user has " + sapumeGroups.size() + " Groups in SAPUME");
						}
					}
						
					/* En este punto lo que se van a crear son objetos SAPUMEGroupBean con tan solo informado el GroupName (equivalente al uniqueMeember de SAPUME)
					 * ya que en OpenIAM es esto lo que se guarda y por tanto lo que llegará al conector.
					 * Posteriormente, en la fase de ejecución de las operaciones contra SAPUME, se tendrán que buscar estos grupos para obtener los 
					 * identificadores (uniqueID) y añadirlos en la petición SPML correspondiente, porqure SAPUME lo que esperará son uniqueID. 
					 */
					for (BaseAttribute ba : container.getAttributeList())
					{
						String groupName = ba.getName();
						switch (ba.getOperationEnum())
						{
							case ADD:
								// Lo añado a la lista sólo si no existe entre los grupos devueltos de la consulta realizada a SAPUME
								if (!SAPUMEUtil.groupListContainSigleGroupName(groupName, sapumeGroups))
								{
									addGroups.add(new SAPUMEGroupBean(groupName));
									logger.debug("Group " + groupName + " added to addGroups List (group was marked as ADD opertarion in request)");
									
								} else {
									logger.debug("Group " + groupName + " already exist in SAPUME List, so will be ignored because is marked as ADD opertarion");
								}
								break;
							case NOCHANGE:
								// Lo añado a la lista sólo si no existe entre los grupos devueltos de la consulta realizada a SAPUME
								if (!SAPUMEUtil.groupListContainSigleGroupName(groupName, sapumeGroups))
								{
									addGroups.add(new SAPUMEGroupBean(groupName));
									logger.debug("Group " + groupName + " added to addGroups List (group was marked as NOCHANGE opertarion in request)");
								} else {
									logger.debug("Group " + groupName + " already exist in SAPUME List, so will be ignored because is marked as NOCHANGE opertarion");
								}
								break;
							case DELETE:
								// Lo añado a la lista de borrados sólo si existe entre los grupos devueltos de la consulta realizada a SAPUME
								if (SAPUMEUtil.groupListContainSigleGroupName(groupName, sapumeGroups))
								{
									deleteGroups.add(new SAPUMEGroupBean(groupName));
									logger.debug("Group " + groupName + " added to deleteGroups List (group was marked as DELETE opertarion in request)");
								} else {
									logger.debug("Group " + groupName + " does not exist in SAPUME List, so will be ignored because is marked as DELETE opertarion");
								}
								break;
						}
					}
					
				} else {
					logger.debug("The BaseAttributeContainer of the memberOf ExtensibleAttribute " + SAPUMEConstants.SAPUME_FIELD_ASSIGNEDGROUPS + " is null. No Membership set");
				}
							
			} else {
				logger.debug("The memberOf ExtensibleAttribute " + SAPUMEConstants.SAPUME_FIELD_ASSIGNEDGROUPS + " is null. No Membership set");
			}
			
		} catch (Exception e) {
			logger.error("Generic error setting add/delete Groups list from request attributes and target SAPUME: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error setting add/delete Groups list from request attributes and target SAPUME: " + e.getMessage(), e);
		}
	}
	
	private void setRolesMembership(List<SAPUMERoleBean> addRoles, List<SAPUMERoleBean> deleteRoles, SAPUMEContext sapumeContext, boolean querySapume) throws SAPUMEConnectorException
	{
		try
		{
			logger.debug("Start setRolesMembership() method");
			ExtensibleAttribute ea = this.attributeReader.getAttributeByNameAndType(SAPUMEConstants.SAPUME_FIELD_ASSIGNEDROLES, AttributeReader.DATA_TYPE_MEMBERSHIP);
			if (ea != null)
			{
				BaseAttributeContainer container = ea.getAttributeContainer();
				if (container != null)
				{
					List<SAPUMERoleBean> sapumeRoles = null;
					if (querySapume) // En Creacion el usuairo no existirá por lo que no tenemos que buscar. En modificación si que habrá que consultar en SAPUME
					{
						// Recuperamos los roles que el usuario tiene actualmente en SAPUME (obtenemos la lista completa)
						sapumeRoles = sapumeContext.getUserRoles(this.userID);
						if (sapumeRoles == null)
						{
							logger.debug("The user has not any Roles in SAPUME");
							sapumeRoles = new ArrayList<SAPUMERoleBean>();
						} else {
							logger.debug("The user has " + sapumeRoles.size() + " Roles in SAPUME");
						}
					} 
					
					/* En este punto lo que se van a crear son objetos SAPUMERoleBean con tan solo informado el RoleName (equivalente al uniqueMeember de SAPUME)
					 * ya que en OpenIAM es esto lo que se guarda y por tanto lo que llegará al conector.
					 * Posteriormente, en la fase de ejecución de las operaciones contra SAPUME, se tendrán que buscar estos roles para obtener los 
					 * identificadores (uniqueID) y añadirlos en la petición SPML correspondiente, porqure SAPUME lo que esperará son uniqueID. 
					 */
					for (BaseAttribute ba : container.getAttributeList())
					{
						String roleName = ba.getName();
						switch (ba.getOperationEnum())
						{
							case ADD:
								// Lo añado a la lista sólo si no existe entre los roles devueltos de la consulta realizada a SAPUME
								if (!SAPUMEUtil.roleListContainSigleRoleName(roleName, sapumeRoles))
								{
									addRoles.add(new SAPUMERoleBean(roleName));
									logger.debug("Role " + roleName + " added to addRoles List (role was marked as ADD opertarion in request)");
								} else {
									logger.debug("Role " + roleName + " already exist in SAPUME List, so will be ignored because is marked as ADD opertarion");
								}
								break;
							case NOCHANGE:
								// Lo añado a la lista sólo si no existe entre los roles devueltos de la consulta realizada a SAPUME
								if (!SAPUMEUtil.roleListContainSigleRoleName(roleName, sapumeRoles))
								{
									addRoles.add(new SAPUMERoleBean(roleName));
									logger.debug("Role " + roleName + " added to addRoles List (role was marked as NOCHANGE opertarion in request)");
								} else {
									logger.debug("Role " + roleName + " already exist in SAPUME List, so will be ignored because is marked as NOCHANGE opertarion");
								}
								break;
							case DELETE:
								// Lo añado a la lista de borrados sólo si existe entre los roles devueltos de la consulta realizada a SAPUME
								if (SAPUMEUtil.roleListContainSigleRoleName(roleName, sapumeRoles))
								{
									deleteRoles.add(new SAPUMERoleBean(roleName));
									logger.debug("Role " + roleName + " added to deleteRoles List (role was marked as DELETE opertarion in request)");
								} else {
									logger.debug("Role " + roleName + " does not exist in SAPUME List, so will be ignored because is marked as DELETE opertarion");
								}
								break;
						}
					}
					
				} else {
					logger.debug("The BaseAttributeContainer of the memberOf ExtensibleAttribute " + SAPUMEConstants.SAPUME_FIELD_ASSIGNEDROLES + " is null. No Membership set");
				}
							
			} else {
				logger.debug("The memberOf ExtensibleAttribute " + SAPUMEConstants.SAPUME_FIELD_ASSIGNEDROLES + " is null. No Membership set");
			}
			
		} catch (Exception e) {
			logger.error("Generic error setting add/delete Roles list from request attributes and target SAPUME: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error setting add/delete Roles list from request attributes and target SAPUME: " + e.getMessage(), e);
		}
	}
	
}
