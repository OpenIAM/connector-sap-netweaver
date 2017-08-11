package org.openiam.connector.sapume.core.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public enum ExecutionModeTypes
{
	POLICY_MAP, MANAGED_SYSTEM;
	
	private static Log logger = LogFactory.getLog(ExecutionModeTypes.class);
	
	public static ExecutionModeTypes getExecutionModeTypeFromString(String strExecMode) throws SAPUMEConnectorException
	{
		try
		{
			logger.info("Start getExecutionModeTypeFromString() method");
			if (strExecMode != null && !strExecMode.equals(""))
			{
				if (ExecutionModeTypes.POLICY_MAP.toString().equals(strExecMode))
				{
					return ExecutionModeTypes.POLICY_MAP;
					
				} else if (ExecutionModeTypes.MANAGED_SYSTEM.toString().equals(strExecMode)) {
					
					return ExecutionModeTypes.MANAGED_SYSTEM;
					
				} else {
					
					logger.error("Unexpected ExecutionModeTypes value");
					throw new SAPUMEConnectorException("Unexpected ExecutionModeTypes value");
				}
				
			} else {
				logger.error("Can not obtain one ExecutionModeTypes value from string");
				throw new SAPUMEConnectorException("Can not obtain one ExecutionModeTypes value from string");
			}
			
		} catch (SAPUMEConnectorException e) {
			throw e;
			
		} catch (Exception e) {
			logger.error("Error trying to obtain Execution Mode type from string received: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Error trying to obtain Execution Mode type from string received: " + e.getMessage(), e);
		}
	}
	
}