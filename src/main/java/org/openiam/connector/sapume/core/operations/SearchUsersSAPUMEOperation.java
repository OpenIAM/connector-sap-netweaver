package org.openiam.connector.sapume.core.operations;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.connector.sapume.core.beans.ProvisionSAPUMEUserBean;
import org.openiam.connector.sapume.core.common.SAPUMEConfiguration;
import org.openiam.connector.sapume.core.common.SAPUMEConnectorException;
import org.openiam.connector.sapume.core.common.SAPUMEUtil;
import org.openiam.connector.sapume.core.connection.SAPUMEConnection;

public class SearchUsersSAPUMEOperation extends AbstractSAPUMEOperation
{
	/*************************************************************
     **********          OBJETOS INTERNOS              ***********
     *************************************************************/
	
	private static Log logger = LogFactory.getLog(SearchUsersSAPUMEOperation.class);
	
	
	/*************************************************************
     ***********             CONSTRUCTORES             *********** 
     *************************************************************/
	
	public SearchUsersSAPUMEOperation(SAPUMEConnection conn, SAPUMEConfiguration config)
	{
		super(conn, config);
		logger.info("SearchUsersSAPUMEOperation class constrtuctor");
	}
	
	
	/*************************************************************
     ***********           MÉTODOS PÚBLICOS            *********** 
     *************************************************************/
	
	public List<ProvisionSAPUMEUserBean> execute(String searchQuery) throws SAPUMEConnectorException
	{	
		List<ProvisionSAPUMEUserBean> listProvisioningUser = null;
		try
		{
			logger.info("Start SearchUsersSAPUMEOperation.execute() method");
			
			logger.info("Search SAPUME accounts for SearchQuery: " + searchQuery);
			
			logger.info("Search users accouts function will be invoke ...");
			listProvisioningUser = SAPUMEUtil.searchSAPUMEAccounts(searchQuery, this.sapConnection, this.sapConfiguration);
			if (listProvisioningUser != null && listProvisioningUser.size() > 0)
			{
				logger.info("After search accounts in SAPUME was done, " + listProvisioningUser.size() + " users were found, so ProvisionSAPUMEUserBean instances were created and filled");
				
			} else {
				logger.info("No users found in SAPUME for SearchQuery defined");
			}
									
		} catch (SAPUMEConnectorException e) {
			logger.error("SAPUMEConnectorException handled in SearchUsersSAPUMEOperation.execute() method: " + e.getMessage());
			throw e;
			
		} catch (Exception e) {
			logger.error("Generic error ocurred in SearchUsersSAPUMEOperation.execute() method: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error ocurred in SearchUsersSAPUMEOperation.execute() method: " + e.getMessage(), e);
		}
		
		return listProvisioningUser;
	}
	
}
