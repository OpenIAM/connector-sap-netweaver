package org.openiam.connector.sapume.core.operations;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.connector.sapume.core.common.SAPUMEConfiguration;
import org.openiam.connector.sapume.core.common.SAPUMEConnectorException;
import org.openiam.connector.sapume.core.connection.SAPUMEConnection;

public class TestConnectionSAPUMEOperation extends AbstractSAPUMEOperation
{
	/*************************************************************
     **********          OBJETOS INTERNOS              ***********
     *************************************************************/
	
	private static Log logger = LogFactory.getLog(TestConnectionSAPUMEOperation.class);
	
	
	/*************************************************************
     ***********             CONSTRUCTORES             *********** 
     *************************************************************/
	
	public TestConnectionSAPUMEOperation(SAPUMEConnection conn, SAPUMEConfiguration config)
	{
		super(conn, config);
		logger.info("TestConnectionSAPUMEOperation class constrtuctor");
	}
	
	
	/*************************************************************
     ***********           MÉTODOS PÚBLICOS            *********** 
     *************************************************************/
	
	public void execute() throws SAPUMEConnectorException
	{		
		try
		{
			logger.info("Start TestConnectionSAPUMEOperation.execute() method");
			
			this.sapConfiguration.validate();
			logger.info("SAPUMEConfiguration validation sacessfully!");
			
			this.sapConnection.test();
			logger.info("SAPUMEConnection testing sacessfully!");
			
		} catch (SAPUMEConnectorException e) {
			logger.error("SAPConnectorException handled in TestConnectionSAPUMEOperation.execute() method: " + e.getMessage());
			throw e;
			
		} catch (Exception e) {
			logger.error("Generic error ocurred in TestConnectionSAPUMEOperation.execute() method: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error ocurred in TestConnectionSAPUMEOperation.execute() method: " + e.getMessage(), e);
		}
	}
	
}
