package org.openiam.connector.sapume.core.operations;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.connector.sapume.core.beans.ProvisionSAPUMEUserBean;
import org.openiam.connector.sapume.core.common.SAPUMEConfiguration;
import org.openiam.connector.sapume.core.common.SAPUMEConnectorException;
import org.openiam.connector.sapume.core.common.SAPUMEConstants;
import org.openiam.connector.sapume.core.common.SAPUMEUtil;
import org.openiam.connector.sapume.core.connection.SAPUMEConnection;

public class LookupUserSAPUMEOperation extends AbstractSAPUMEOperation
{
	/*************************************************************
     **********          OBJETOS INTERNOS              ***********
     *************************************************************/
	
	private static Log logger = LogFactory.getLog(LookupUserSAPUMEOperation.class);
	
	
	/*************************************************************
     ***********             CONSTRUCTORES             *********** 
     *************************************************************/
	
	public LookupUserSAPUMEOperation(SAPUMEConnection conn, SAPUMEConfiguration config)
	{
		super(conn, config);
		logger.info("LookupUserSAPUMEOperation class constrtuctor");
	}
	
	
	/*************************************************************
     ***********           MÉTODOS PÚBLICOS            *********** 
     *************************************************************/
	
	public ProvisionSAPUMEUserBean execute(String userID, List<String> attrs) throws SAPUMEConnectorException
	{	
		ProvisionSAPUMEUserBean provisioningUser = null;
		try
		{
			logger.info("Start LookupUserSAPUMEOperation.execute() method");
			
			logger.info("Lookup SAPUME account for user: " + userID);
			if (userID != null && !userID.equals(""))
			{
				if (SAPUMEUtil.checkIfUserExist(userID, this.sapConnection, this.sapConfiguration))
				{
					logger.info("User exists in SAPUME");
					
					this.cleanAttributeList(attrs);
					logger.debug("User attributes to lookup: " + attrs);
					logger.info("Get user details function will be invoke to lookup user...");
					provisioningUser = SAPUMEUtil.getUserDetails(userID, attrs, this.sapConnection, this.sapConfiguration);
					if (provisioningUser != null)
					{
						logger.info("User lookup for " + userID + " was sucessfully, so ProvisionSAPUMEUserBean instance was created and filled");
						
					} else {
						logger.info("Could not get user details for account '" + userID + "'");
						provisioningUser = null;
						logger.info("Ends operation");
					}
					
				} else {
					logger.info("User not found in SAP, so there is not account in target system named '" + userID + "'");
					provisioningUser = null;
					logger.info("Ends operation");
				}
				
			} else {
				logger.error("UserID passed is null or empty");
				throw new SAPUMEConnectorException("UserID passed is null or empty");
			}
						
		} catch (SAPUMEConnectorException e) {
			logger.error("SAPUMEConnectorException handled in LookupUserSAPUMEOperation.execute() method: " + e.getMessage());
			throw e;
			
		} catch (Exception e) {
			logger.error("Generic error ocurred in LookupUserSAPUMEOperation.execute() method: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error ocurred in LookupUserSAPUMEOperation.execute() method: " + e.getMessage(), e);
		}
		
		return provisioningUser;
	}
	
	
	private void cleanAttributeList(List<String> attrs)
	{
		logger.info("Start cleanAttributeList.execute() method");
		if (attrs != null)
		{
			for (int i = 0; i < attrs.size(); i++)
			{
				String attribute = attrs.get(i);
				if (attribute.equals(SAPUMEConstants.SAPUME_FIELD_PASSWORD)) // TODO: || attribute.equals(SAPConstants.SAP_FIELD_OLD_IDENTITY))
					attrs.remove(i);
			}
		}
	}
	
}
