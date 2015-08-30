package org.openiam.connector.sapume.core.operations;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.connector.sapume.core.beans.SAPUMEGroupBean;
import org.openiam.connector.sapume.core.common.SAPUMEConfiguration;
import org.openiam.connector.sapume.core.common.SAPUMEConnectorException;
import org.openiam.connector.sapume.core.common.SAPUMEUtil;
import org.openiam.connector.sapume.core.connection.SAPUMEConnection;

public class SearchGroupsSAPUMEOperation extends AbstractSAPUMEOperation
{
	/*************************************************************
     **********          OBJETOS INTERNOS              ***********
     *************************************************************/
	
	private static Log logger = LogFactory.getLog(SearchGroupsSAPUMEOperation.class);
	
	
	/*************************************************************
     ***********             CONSTRUCTORES             *********** 
     *************************************************************/
	
	public SearchGroupsSAPUMEOperation(SAPUMEConnection conn, SAPUMEConfiguration config)
	{
		super(conn, config);
		logger.info("SearchGroupsSAPUMEOperation class constrtuctor");
	}
	
	
	/*************************************************************
     ***********           MÉTODOS PÚBLICOS            *********** 
     *************************************************************/
	
	public List<SAPUMEGroupBean> execute(String searchQuery) throws SAPUMEConnectorException
	{	
		List<SAPUMEGroupBean> listGroups = null;
		try
		{
			logger.info("Start SearchGroupsSAPUMEOperation.execute() method");
			
			logger.info("Search SAPUME Groups for SearchQuery: " + searchQuery);
			
			logger.info("Search Groups function will be invoke ...");
			listGroups = SAPUMEUtil.searchSAPUMEGroups(searchQuery, this.sapConnection, this.sapConfiguration);
			if (listGroups != null && listGroups.size() > 0)
			{
				logger.info("After search Groups in SAPUME was done, " + listGroups.size() + " groups were found, so SAPUMEGroupBean instances were created and filled");
				
			} else {
				logger.info("No Groups found in SAPUME for SearchQuery defined");
			}
									
		} catch (SAPUMEConnectorException e) {
			logger.error("SAPUMEConnectorException handled in SearchGroupsSAPUMEOperation.execute() method: " + e.getMessage());
			throw e;
			
		} catch (Exception e) {
			logger.error("Generic error ocurred in SearchGroupsSAPUMEOperation.execute() method: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error ocurred in SearchGroupsSAPUMEOperation.execute() method: " + e.getMessage(), e);
		}
		
		return listGroups;
	}
	
}
