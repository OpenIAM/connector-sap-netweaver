package org.openiam.connector.sapume.core.operations;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.connector.sapume.core.beans.SAPUMEGroupBean;
import org.openiam.connector.sapume.core.common.SAPUMEConfiguration;
import org.openiam.connector.sapume.core.common.SAPUMEConnectorException;
import org.openiam.connector.sapume.core.common.SAPUMEUtil;
import org.openiam.connector.sapume.core.connection.SAPUMEConnection;

public class LookupGroupSAPUMEOperation extends AbstractSAPUMEOperation
{
	/*************************************************************
     **********          OBJETOS INTERNOS              ***********
     *************************************************************/
	
	private static Log logger = LogFactory.getLog(LookupGroupSAPUMEOperation.class);
	
	
	/*************************************************************
     ***********             CONSTRUCTORES             *********** 
     *************************************************************/
	
	public LookupGroupSAPUMEOperation(SAPUMEConnection conn, SAPUMEConfiguration config)
	{
		super(conn, config);
		logger.info("LookupGroupSAPUMEOperation class constrtuctor");
	}
	
	
	/*************************************************************
     ***********           MÉTODOS PÚBLICOS            *********** 
     *************************************************************/
	
	public SAPUMEGroupBean execute(String groupName) throws SAPUMEConnectorException
	{	
		SAPUMEGroupBean sapumeGroupBean = null;
		try
		{
			logger.info("Start LookupGroupSAPUMEOperation.execute() method");
			
			logger.info("Lookup SAPUME group: " + groupName);
			if (groupName != null && !groupName.equals(""))
			{
				logger.info("Get group details function will be invoke to lookup user...");
				sapumeGroupBean = SAPUMEUtil.getGroupDetailsFromUniqueName(groupName, this.sapConnection, this.sapConfiguration);
				if (sapumeGroupBean != null)
				{
					logger.info("Group lookup for " + groupName + " was sucessfully, so SAPUMEGroupBean instance was created and filled");
					
				} else {
					logger.info("Could not get group details for group name '" + groupName + "'");
					sapumeGroupBean = null;
					logger.info("Ends operation");
				}
								
			} else {
				logger.error("Group Name passed is null or empty");
				throw new SAPUMEConnectorException("Group Name passed is null or empty");
			}
						
		} catch (SAPUMEConnectorException e) {
			logger.error("SAPUMEConnectorException handled in LookupGroupSAPUMEOperation.execute() method: " + e.getMessage());
			throw e;
			
		} catch (Exception e) {
			logger.error("Generic error ocurred in LookupGroupSAPUMEOperation.execute() method: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error ocurred in LookupGroupSAPUMEOperation.execute() method: " + e.getMessage(), e);
		}
		
		return sapumeGroupBean;
	}
	
}
