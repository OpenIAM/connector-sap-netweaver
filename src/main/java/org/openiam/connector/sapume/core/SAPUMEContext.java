package org.openiam.connector.sapume.core;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.connector.sapume.core.beans.ProvisionSAPUMEUserBean;
import org.openiam.connector.sapume.core.beans.SAPUMEGroupBean;
import org.openiam.connector.sapume.core.beans.SAPUMERoleBean;
import org.openiam.connector.sapume.core.beans.SAPUMESystemBean;
import org.openiam.connector.sapume.core.common.ExecutionModeTypes;
import org.openiam.connector.sapume.core.common.SAPUMEConfiguration;
import org.openiam.connector.sapume.core.common.SAPUMEConnectorException;
import org.openiam.connector.sapume.core.common.SAPUMEUtil;
import org.openiam.connector.sapume.core.connection.SAPUMEConnection;
import org.openiam.connector.sapume.core.operations.CreateUserSAPUMEOperation;
import org.openiam.connector.sapume.core.operations.DeleteUserSAPUMEOperation;
import org.openiam.connector.sapume.core.operations.LookupGroupSAPUMEOperation;
import org.openiam.connector.sapume.core.operations.LookupRoleSAPUMEOperation;
import org.openiam.connector.sapume.core.operations.LookupUserSAPUMEOperation;
import org.openiam.connector.sapume.core.operations.ModifyUserSAPUMEOperation;
import org.openiam.connector.sapume.core.operations.SearchGroupsSAPUMEOperation;
import org.openiam.connector.sapume.core.operations.SearchRolesSAPUMEOperation;
import org.openiam.connector.sapume.core.operations.SearchUsersSAPUMEOperation;
import org.openiam.connector.sapume.core.operations.TestConnectionSAPUMEOperation;
import org.openiam.connector.sapume.service.types.ExtensibleAttribute;

public class SAPUMEContext
{
	/*************************************************************
     **********          OBJETOS INTERNOS              ***********
     *************************************************************/
	
	private static Log logger = LogFactory.getLog(SAPUMEContext.class);
	
	private SAPUMEConfiguration sapumeConfiguration;
	private SAPUMEConnection sapumeConnection;
	
	
	/*************************************************************
     ***********             CONSTRUCTORES             *********** 
     *************************************************************/
	
	public SAPUMEContext(SAPUMESystemBean sapumeSystem) throws SAPUMEConnectorException
	{
		logger.info("SAPUMESystemBean constructor.");
		initSAPUMEContext(sapumeSystem);
		logger.info("SAPUME context was initialized sucessfully");
	}
		
	
	/*************************************************************
     ***********           MÉTODOS PÚBLICOS            *********** 
     *************************************************************/
	
	public void createUser(ProvisionSAPUMEUserBean provUser) throws SAPUMEConnectorException
	{
		try
		{
			logger.info("Start createUser() method");
			if (provUser != null && provUser.getUserID() != null && !provUser.getUserID().equals(""))
			{
				logger.info("Creation requested for user " + provUser.getUserID());
				CreateUserSAPUMEOperation createOpSap = new CreateUserSAPUMEOperation(this.sapumeConnection, this.sapumeConfiguration);
				createOpSap.execute(provUser);
				logger.info("Creation processed");
				
			} else {
				logger.error("ProvisionSAPUMEUserBean object passed is null or empty");
				throw new SAPUMEConnectorException("ProvisionSAPUMEUserBean object passed is null or empty");
			}
			
		} catch (SAPUMEConnectorException e) {
			logger.error("SAPUMEConnectorException handled in SAPUMEContext.createUser() operation: " + e.getMessage());
			throw e;
			
		} catch (Exception e) {
			logger.error("Generic error ocurred in SAPUMEContext.createUser() method: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error ocurred in SAPUMEContext.createUser() method: " + e.getMessage(), e);
		}
	}
	
	
	public void modifyUser(ProvisionSAPUMEUserBean provUser) throws SAPUMEConnectorException
	{
		try
		{
			logger.info("Start modifyUser() method");
			if (provUser != null && provUser.getUserID() != null && !provUser.getUserID().equals(""))
			{
				logger.info("Modification requested for user " + provUser.getUserID());
				ModifyUserSAPUMEOperation modifyOpSap = new ModifyUserSAPUMEOperation(this.sapumeConnection, this.sapumeConfiguration);
				modifyOpSap.execute(provUser, true);
				logger.info("Modification processed");
								
			} else {
				logger.error("ProvisionSAPUMEUserBean object passed is null or empty");
				throw new SAPUMEConnectorException("ProvisionSAPUMEUserBean object passed is null or empty");
			}
			
		} catch (SAPUMEConnectorException e) {
			logger.error("SAPUMEConnectorException handled in SAPUMEContext.modifyUser() operation: " + e.getMessage());
			throw e;
			
		} catch (Exception e) {
			logger.error("Generic error ocurred in SAPUMEContext.modifyUser() method: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error ocurred in SAPUMEContext.modifyUser() method: " + e.getMessage(), e);
		}
	}
	
	
	public void disableUser(ProvisionSAPUMEUserBean provUser) throws SAPUMEConnectorException
	{
		try
		{
			logger.info("Start disableUser() method");
			if (provUser != null && provUser.getUserID() != null && !provUser.getUserID().equals(""))
			{
				logger.info("Disabling requested for user " + provUser.getUserID());
								
				logger.info("Modification for user " + provUser.getUserID() + " will be requested...");
				ModifyUserSAPUMEOperation modifyOpSap = new ModifyUserSAPUMEOperation(this.sapumeConnection, this.sapumeConfiguration);
				modifyOpSap.execute(provUser, false);
				logger.info("Modification success --> Disable processed");
								
			} else {
				logger.error("ProvisionSAPUMEUserBean object passed is null or empty");
				throw new SAPUMEConnectorException("ProvisionSAPUMEUserBean object passed is null or empty");
			}
			
		} catch (SAPUMEConnectorException e) {
			logger.error("SAPUMEConnectorException handled in SAPUMEContext.disableUser() operation: " + e.getMessage());
			throw e;
			
		} catch (Exception e) {
			logger.error("Generic error ocurred in SAPUMEContext.disableUser() method: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error ocurred in SAPUMEContext.disableUser() method: " + e.getMessage(), e);
		}
	}
	
	
	public void enableUser(ProvisionSAPUMEUserBean provUser) throws SAPUMEConnectorException
	{
		try
		{
			logger.info("Start enableUser() method");
			if (provUser != null && provUser.getUserID() != null && !provUser.getUserID().equals(""))
			{
				logger.info("Enabling requested for user " + provUser.getUserID());
								
				logger.info("Modification for user " + provUser.getUserID() + " will be requested...");
				ModifyUserSAPUMEOperation modifyOpSap = new ModifyUserSAPUMEOperation(this.sapumeConnection, this.sapumeConfiguration);
				modifyOpSap.execute(provUser, false);
				logger.info("Modification success --> Enabling processed");
								
			} else {
				logger.error("ProvisionSAPUMEUserBean object passed is null or empty");
				throw new SAPUMEConnectorException("ProvisionSAPUMEUserBean object passed is null or empty");
			}
			
		} catch (SAPUMEConnectorException e) {
			logger.error("SAPUMEConnectorException handled in SAPUMEContext.enableUser() operation: " + e.getMessage());
			throw e;
			
		} catch (Exception e) {
			logger.error("Generic error ocurred in SAPUMEContext.enableUser() method: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error ocurred in SAPUMEContext.enableUser() method: " + e.getMessage(), e);
		}
	}
	
	
	public void resetPassword(ProvisionSAPUMEUserBean provUser) throws SAPUMEConnectorException
	{
		try
		{
			logger.info("Start resetPassword() method");
			if (provUser != null && provUser.getUserID() != null && !provUser.getUserID().equals(""))
			{
				logger.info("Reset password requested for user " + provUser.getUserID());
								
				logger.info("Reset password will be treated like user modification...");
				ModifyUserSAPUMEOperation modifyOpSap = new ModifyUserSAPUMEOperation(this.sapumeConnection, this.sapumeConfiguration);
				modifyOpSap.execute(provUser, false);
				logger.info("Modification success --> Reset password processed");
								
			} else {
				logger.error("ProvisionSAPUMEUserBean object passed is null or empty");
				throw new SAPUMEConnectorException("ProvisionSAPUMEUserBean object passed is null or empty");
			}
			
		} catch (SAPUMEConnectorException e) {
			logger.error("SAPUMEConnectorException handled in SAPUMEContext.resetPassword() operation: " + e.getMessage());
			throw e;
			
		} catch (Exception e) {
			logger.error("Generic error ocurred in SAPUMEContext.resetPassword() method: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error ocurred in SAPUMEContext.resetPassword() method: " + e.getMessage(), e);
		}
	}
	
	
	public void deleteUser(ProvisionSAPUMEUserBean provUser) throws SAPUMEConnectorException
	{
		try
		{
			logger.info("Start deleteUser() method");
			if (provUser != null && provUser.getUserID() != null && !provUser.getUserID().equals(""))
			{
				logger.info("Deletion requested for user " + provUser.getUserID());
				DeleteUserSAPUMEOperation deleteOpSap = new DeleteUserSAPUMEOperation(this.sapumeConnection, this.sapumeConfiguration);
				deleteOpSap.execute(provUser);
				logger.info("Deletion processed");
								
			} else {
				logger.error("ProvisionSAPUMEUserBean object passed is null or empty");
				throw new SAPUMEConnectorException("ProvisionSAPUMEUserBean object passed is null or empty");
			}
			
		} catch (SAPUMEConnectorException e) {
			logger.error("SAPUMEConnectorException handled in SAPUMEContext.deleteUser() operation: " + e.getMessage());
			throw e;
			
		} catch (Exception e) {
			logger.error("Generic error ocurred in SAPUMEContext.deleteUser() method: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error ocurred in SAPUMEContext.deleteUser() method: " + e.getMessage(), e);
		}
	}
	
	
	public ProvisionSAPUMEUserBean lookupUser(String userID, List<String> attrs) throws SAPUMEConnectorException
	{
		ProvisionSAPUMEUserBean provUser = null;
		
		try
		{
			logger.info("Start lookupUser() method");
			if (userID != null && !userID.equals(""))
			{
				logger.info("Lookup requested for user " + userID);

				LookupUserSAPUMEOperation lookupOpSap = new LookupUserSAPUMEOperation(this.sapumeConnection, this.sapumeConfiguration);
				provUser = lookupOpSap.execute(userID, attrs);
				logger.info("Lookup processed");

			} else {
				logger.error("UserID passed is null or empty");
				throw new SAPUMEConnectorException("UserID passed is null or empty");
			}
			
		} catch (SAPUMEConnectorException e) {
			logger.error("SAPUMEConnectorException handled in SAPUMEContext.lookupUser() operation: " + e.getMessage());
			throw e;
			
		} catch (Exception e) {
			logger.error("Generic error ocurred in SAPUMEContext.lookupUser() method: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error ocurred in SAPUMEContext.lookupUser() method: " + e.getMessage(), e);
		}
		
		return provUser;
	}
	
	
	public List<ExtensibleAttribute> lookupAttributeNames(ExecutionModeTypes executionMode) throws SAPUMEConnectorException
	{
		try
		{
			logger.info("Start lookupAttributeNames() method");
			
			List<ExtensibleAttribute> extensibleAttributeList = SAPUMEUtil.lookupAttributeNames(this.sapumeConfiguration, executionMode);
			logger.info("Attribute names obtained from SAP");
			return extensibleAttributeList;
			
		} catch (SAPUMEConnectorException e) {
			logger.error("SAPUMEConnectorException handled in SAPUMEContext.lookupAttributeNames() operation: " + e.getMessage());
			throw e;
			
		} catch (Exception e) {
			logger.error("Generic error ocurred in SAPUMEContext.lookupAttributeNames() method: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error ocurred in SAPUMEContext.lookupAttributeNames() method: " + e.getMessage(), e);
		}
	}
	
	
	public List<ProvisionSAPUMEUserBean> searchUsers(String searchQuery) throws SAPUMEConnectorException
	{
		List<ProvisionSAPUMEUserBean> listProvSapUsers = null;
		
		try
		{
			logger.info("Start searchUsers() method");
			
			logger.info("Search users requested with this searchQuery: " + searchQuery);
			SearchUsersSAPUMEOperation searchOpSap = new SearchUsersSAPUMEOperation(this.sapumeConnection, this.sapumeConfiguration);
			listProvSapUsers = searchOpSap.execute(searchQuery);
			logger.info("Search processed");
			
		} catch (SAPUMEConnectorException e) {
			logger.error("SAPUMEConnectorException handled in SAPUMEContext.searchUsers() operation: " + e.getMessage());
			throw e;
			
		} catch (Exception e) {
			logger.error("Generic error ocurred in SAPUMEContext.searchUsers() method: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error ocurred in SAPUMEContext.searchUsers() method: " + e.getMessage(), e);
		}
		
		return listProvSapUsers;
	}
	
	
	public List<SAPUMEGroupBean> searchGroups(String searchQuery) throws SAPUMEConnectorException
	{
		List<SAPUMEGroupBean> listGroups = null;
		
		try
		{
			logger.info("Start searchGroups() method");
			
			logger.info("Search groups requested with this searchQuery: " + searchQuery);
			SearchGroupsSAPUMEOperation searchOpSap = new SearchGroupsSAPUMEOperation(this.sapumeConnection, this.sapumeConfiguration);
			listGroups = searchOpSap.execute(searchQuery);
			logger.info("Search processed");
			
		} catch (SAPUMEConnectorException e) {
			logger.error("SAPUMEConnectorException handled in SAPUMEContext.searchGroups() operation: " + e.getMessage());
			throw e;
			
		} catch (Exception e) {
			logger.error("Generic error ocurred in SAPUMEContext.searchGroups() method: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error ocurred in SAPUMEContext.searchGroups() method: " + e.getMessage(), e);
		}
		
		return listGroups;
	}	
	
	
	public List<SAPUMERoleBean> searchRoles(String searchQuery) throws SAPUMEConnectorException
	{
		List<SAPUMERoleBean> listRoles = null;
		
		try
		{
			logger.info("Start searchRoles() method");
			
			logger.info("Search roles requested with this searchQuery: " + searchQuery);
			SearchRolesSAPUMEOperation searchOpSap = new SearchRolesSAPUMEOperation(this.sapumeConnection, this.sapumeConfiguration);
			listRoles = searchOpSap.execute(searchQuery);
			logger.info("Search processed");
			
		} catch (SAPUMEConnectorException e) {
			logger.error("SAPUMEConnectorException handled in SAPUMEContext.searchRoles() operation: " + e.getMessage());
			throw e;
			
		} catch (Exception e) {
			logger.error("Generic error ocurred in SAPUMEContext.searchRoles() method: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error ocurred in SAPUMEContext.searchRoles() method: " + e.getMessage(), e);
		}
		
		return listRoles;
	}
	

	public void testConnection() throws SAPUMEConnectorException
	{
		try
		{
			logger.info("Start testConnection() method");

			TestConnectionSAPUMEOperation testOpSap = new TestConnectionSAPUMEOperation(this.sapumeConnection, this.sapumeConfiguration);
			testOpSap.execute();
			logger.info("Test connection processed");
			
		} catch (SAPUMEConnectorException e) {
			logger.error("SAPUMEConnectorException handled in SAPUMEContext.testConnection() operation: " + e.getMessage());
			throw e;
			
		} catch (Exception e) {
			logger.error("Generic error ocurred in SAPUMEContext.testConnection() method: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error ocurred in SAPUMEContext.testConnection() method: " + e.getMessage(), e);
		}
	}
	
	
	public List<SAPUMEGroupBean> getUserGroups(String userID) throws SAPUMEConnectorException
	{
		List<SAPUMEGroupBean> sapumeGroups = null;

		try
		{
			logger.info("Start getUserGroups() method");
			sapumeGroups = SAPUMEUtil.getUserGroups(userID, this.sapumeConnection, this.sapumeConfiguration);
			logger.info("User Groups obtained from SAPUME");

		} catch (SAPUMEConnectorException e) {
			logger.error("SAPConnectorException handled in SAPUMEContext.getUserGroups() operation: " + e.getMessage());
			throw e;

		} catch (Exception e) {
			logger.error("Generic error ocurred in SAPUMEContext.getUserGroups() method: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error ocurred in SAPUMEContext.getUserGroups() method: " + e.getMessage(), e);
		}

		return sapumeGroups;
	}
	
	
	public List<SAPUMERoleBean> getUserRoles(String userID) throws SAPUMEConnectorException
	{
		List<SAPUMERoleBean> sapumeRoles = null;

		try
		{
			logger.info("Start getUserRoles() method");
			sapumeRoles = SAPUMEUtil.getUserRoles(userID, this.sapumeConnection, this.sapumeConfiguration);
			logger.info("User Roles obtained from SAPUME");

		} catch (SAPUMEConnectorException e) {
			logger.error("SAPConnectorException handled in SAPUMEContext.getUserRoles() operation: " + e.getMessage());
			throw e;

		} catch (Exception e) {
			logger.error("Generic error ocurred in SAPUMEContext.getUserRoles() method: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error ocurred in SAPUMEContext.getUserRoles() method: " + e.getMessage(), e);
		}

		return sapumeRoles;
	}
	
	
	public SAPUMEGroupBean lookupGroup(String groupName) throws SAPUMEConnectorException
	{
		SAPUMEGroupBean sapumeGroupBean = null;
		
		try
		{
			logger.info("Start lookupGroup() method");
			if (groupName != null && !groupName.equals(""))
			{
				logger.info("Lookup requested for group " + groupName);				
				LookupGroupSAPUMEOperation lookupOpSap = new LookupGroupSAPUMEOperation(this.sapumeConnection, this.sapumeConfiguration);
				sapumeGroupBean = lookupOpSap.execute(groupName);
				logger.info("Lookup processed");
				
			} else {
				logger.error("Group Name passed is null or empty");
				throw new SAPUMEConnectorException("Group Name passed is null or empty");
			}
			
		} catch (SAPUMEConnectorException e) {
			logger.error("SAPUMEConnectorException handled in SAPUMEContext.lookupGroup() operation: " + e.getMessage());
			throw e;
			
		} catch (Exception e) {
			logger.error("Generic error ocurred in SAPUMEContext.lookupGroup() method: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error ocurred in SAPUMEContext.lookupGroup() method: " + e.getMessage(), e);
		}
		
		return sapumeGroupBean;
	}
	
	
	public SAPUMERoleBean lookupRole(String roleName) throws SAPUMEConnectorException
	{
		SAPUMERoleBean sapumeRoleBean = null;
		
		try
		{
			logger.info("Start lookupRole() method");
			if (roleName != null && !roleName.equals(""))
			{
				logger.info("Lookup requested for role " + roleName);				
				LookupRoleSAPUMEOperation lookupOpSap = new LookupRoleSAPUMEOperation(this.sapumeConnection, this.sapumeConfiguration);
				sapumeRoleBean = lookupOpSap.execute(roleName);
				logger.info("Lookup processed");
				
			} else {
				logger.error("Role Name passed is null or empty");
				throw new SAPUMEConnectorException("Role Name passed is null or empty");
			}
			
		} catch (SAPUMEConnectorException e) {
			logger.error("SAPUMEConnectorException handled in SAPUMEContext.lookupRole() operation: " + e.getMessage());
			throw e;
			
		} catch (Exception e) {
			logger.error("Generic error ocurred in SAPUMEContext.lookupRole() method: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error ocurred in SAPUMEContext.lookupRole() method: " + e.getMessage(), e);
		}
		
		return sapumeRoleBean;
	}
	
	
	public void dispose()
	{
		try
		{
			logger.info("Start dispose() method");
			this.sapumeConfiguration = null;
			this.sapumeConnection = null;
			
		} catch (Throwable e) {
			logger.error("Error trying to dispose SAP UME Contex: " + e.getMessage(), e);
		}
	}
	
	
	/*************************************************************
     ***********           MÉTODOS PRIVADOS            *********** 
     *************************************************************/

	private void initSAPUMEContext(SAPUMESystemBean sapumeSystem) throws SAPUMEConnectorException
	{
		try
		{
			logger.info("Start initSAPUMEContext() method");
			this.sapumeConfiguration = new SAPUMEConfiguration(sapumeSystem);
			this.sapumeConfiguration.validate();
			logger.debug("SAPUME Configuration validated");
			this.sapumeConnection = new SAPUMEConnection(this.sapumeConfiguration);
			logger.info("SAPUMEContext initilized successfully");
			
		} catch (SAPUMEConnectorException e) {
			logger.error("SAPUMEContext initialization error: " + e.getMessage());
			throw e;
			
		} catch (Exception e) {
			logger.error("Generic SAPUMEContext initialization error: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic SAPUMEContext initialization error: " + e.getMessage(), e);
		}
	}
	
}
