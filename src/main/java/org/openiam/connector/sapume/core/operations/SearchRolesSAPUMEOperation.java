package org.openiam.connector.sapume.core.operations;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.connector.sapume.core.beans.SAPUMERoleBean;
import org.openiam.connector.sapume.core.common.SAPUMEConfiguration;
import org.openiam.connector.sapume.core.common.SAPUMEConnectorException;
import org.openiam.connector.sapume.core.common.SAPUMEUtil;
import org.openiam.connector.sapume.core.connection.SAPUMEConnection;

public class SearchRolesSAPUMEOperation extends AbstractSAPUMEOperation
{
	/*************************************************************
     **********          OBJETOS INTERNOS              ***********
     *************************************************************/
	
	private static Log logger = LogFactory.getLog(SearchRolesSAPUMEOperation.class);
	
	
	/*************************************************************
     ***********             CONSTRUCTORES             *********** 
     *************************************************************/
	
	public SearchRolesSAPUMEOperation(SAPUMEConnection conn, SAPUMEConfiguration config)
	{
		super(conn, config);
		logger.info("SearchRolesSAPUMEOperation class constrtuctor");
	}
	
	
	/*************************************************************
     ***********           MÉTODOS PÚBLICOS            *********** 
     *************************************************************/
	
	public List<SAPUMERoleBean> execute(String searchQuery) throws SAPUMEConnectorException
	{	
		List<SAPUMERoleBean> listRoles = null;
		try
		{
			logger.info("Start SearchRolesSAPUMEOperation.execute() method");
			
			logger.info("Search SAPUME Roles for SearchQuery: " + searchQuery);
			
			logger.info("Search Roles function will be invoke ...");
			listRoles = SAPUMEUtil.searchSAPUMERoles(searchQuery, this.sapConnection, this.sapConfiguration);
			if (listRoles != null && listRoles.size() > 0)
			{
				logger.info("After search Roles in SAPUME was done, " + listRoles.size() + " roles were found, so SAPUMERoleBean instances were created and filled");
				
			} else {
				logger.info("No Roles found in SAPUME for SearchQuery defined");
			}
									
		} catch (SAPUMEConnectorException e) {
			logger.error("SAPUMEConnectorException handled in SearchRolesSAPUMEOperation.execute() method: " + e.getMessage());
			throw e;
			
		} catch (Exception e) {
			logger.error("Generic error ocurred in SearchRolesSAPUMEOperation.execute() method: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error ocurred in SearchRolesSAPUMEOperation.execute() method: " + e.getMessage(), e);
		}
		
		return listRoles;
	}
	
}
