package org.openiam.connector.sapume.core.operations;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.connector.sapume.core.beans.ProvisionSAPUMEUserBean;
import org.openiam.connector.sapume.core.common.SAPUMEConfiguration;
import org.openiam.connector.sapume.core.common.SAPUMEConnectorException;
import org.openiam.connector.sapume.core.common.SAPUMEUtil;
import org.openiam.connector.sapume.core.connection.SAPUMEConnection;

public class DeleteUserSAPUMEOperation extends AbstractSAPUMEOperation
{
	/*************************************************************
     **********          OBJETOS INTERNOS              ***********
     *************************************************************/
	
	private static Log logger = LogFactory.getLog(DeleteUserSAPUMEOperation.class);
	
	
	/*************************************************************
     ***********             CONSTRUCTORES             *********** 
     *************************************************************/
	
	public DeleteUserSAPUMEOperation(SAPUMEConnection conn, SAPUMEConfiguration config)
	{
		super(conn, config);
		logger.info("DeleteUserSAPUMEOperation class constrtuctor");
	}
	
	
	/*************************************************************
     ***********           MÉTODOS PÚBLICOS            *********** 
     *************************************************************/
	
	public void execute(ProvisionSAPUMEUserBean provisioningUser) throws SAPUMEConnectorException
	{		
		try
		{
			logger.info("Start DeleteUserSAPUMEOperation.execute() method");
			
			if (provisioningUser != null && provisioningUser.getUserID() != null && !provisioningUser.getUserID().equals(""))
			{
				String userID = provisioningUser.getUserID();
				logger.info("Delete SAPUME account for user: " + userID);
				
				if (SAPUMEUtil.checkIfUserExist(userID, this.sapConnection, this.sapConfiguration))
				{
					String sapumeUserUniqueId = SAPUMEUtil.getUserUniqueID(userID, this.sapConnection, this.sapConfiguration);
					logger.info("SAMUME UniqueID obtained for user: " + sapumeUserUniqueId);
					if (sapumeUserUniqueId != null && !sapumeUserUniqueId.equals(""))
					{
						logger.info("User delete function will be invoke...");
						SAPUMEUtil.deleteUser(sapumeUserUniqueId, this.sapConnection, this.sapConfiguration);
						logger.info("User " + sapumeUserUniqueId + " deleted sucessfully");
					
					} else {
						logger.error("Colud not obtain UniqueID for user in SAPUME");
						throw new SAPUMEConnectorException("Colud not obtain UniqueID for user in SAPUME");
					}
					
				} else {
					logger.info("User not found in SAPUME, so there is not account in target system named '" + userID + "'");
					logger.info("Ends operation");
				}
				
			} else {
				logger.error("ProvisionSAPUMEUserBean object passed is null or empty");
				throw new SAPUMEConnectorException("ProvisionSAPUMEUserBean object passed is null or empty");
			}
			
		} catch (SAPUMEConnectorException e) {
			logger.error("SAPConnectorException handled in DeleteUserSAPUMEOperation.execute() method: " + e.getMessage());
			throw e;
			
		} catch (Exception e) {
			logger.error("Generic error ocurred in DeleteUserSAPUMEOperation.execute() method: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error ocurred in DeleteUserSAPUMEOperation.execute() method: " + e.getMessage(), e);
		}
	}
	
}
