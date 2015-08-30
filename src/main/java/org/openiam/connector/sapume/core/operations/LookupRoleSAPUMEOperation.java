package org.openiam.connector.sapume.core.operations;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.connector.sapume.core.beans.SAPUMERoleBean;
import org.openiam.connector.sapume.core.common.SAPUMEConfiguration;
import org.openiam.connector.sapume.core.common.SAPUMEConnectorException;
import org.openiam.connector.sapume.core.common.SAPUMEUtil;
import org.openiam.connector.sapume.core.connection.SAPUMEConnection;

public class LookupRoleSAPUMEOperation extends AbstractSAPUMEOperation
{
	/*************************************************************
     **********          OBJETOS INTERNOS              ***********
     *************************************************************/
	
	private static Log logger = LogFactory.getLog(LookupRoleSAPUMEOperation.class);
	
	
	/*************************************************************
     ***********             CONSTRUCTORES             *********** 
     *************************************************************/
	
	public LookupRoleSAPUMEOperation(SAPUMEConnection conn, SAPUMEConfiguration config)
	{
		super(conn, config);
		logger.info("LookupRoleSAPUMEOperation class constrtuctor");
	}
	
	
	/*************************************************************
     ***********           MÉTODOS PÚBLICOS            *********** 
     *************************************************************/
	
	public SAPUMERoleBean execute(String roleName) throws SAPUMEConnectorException
	{	
		SAPUMERoleBean sapumeRoleBean = null;
		try
		{
			logger.info("Start LookupRoleSAPUMEOperation.execute() method");
			
			logger.info("Lookup SAPUME role: " + roleName);
			if (roleName != null && !roleName.equals(""))
			{
				logger.info("Get role details function will be invoke to lookup user...");
				sapumeRoleBean = SAPUMEUtil.getRoleDetailsFromUniqueName(roleName, this.sapConnection, this.sapConfiguration);
				if (sapumeRoleBean != null)
				{
					logger.info("Role lookup for " + roleName + " was sucessfully, so SAPUMERoleBean instance was created and filled");
					
				} else {
					logger.info("Could not get role details for role name '" + roleName + "'");
					sapumeRoleBean = null;
					logger.info("Ends operation");
				}
								
			} else {
				logger.error("Role Name passed is null or empty");
				throw new SAPUMEConnectorException("Role Name passed is null or empty");
			}
						
		} catch (SAPUMEConnectorException e) {
			logger.error("SAPUMEConnectorException handled in LookupRoleSAPUMEOperation.execute() method: " + e.getMessage());
			throw e;
			
		} catch (Exception e) {
			logger.error("Generic error ocurred in LookupRoleSAPUMEOperation.execute() method: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error ocurred in LookupRoleSAPUMEOperation.execute() method: " + e.getMessage(), e);
		}
		
		return sapumeRoleBean;
	}
	
}
