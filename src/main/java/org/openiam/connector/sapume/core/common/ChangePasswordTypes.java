package org.openiam.connector.sapume.core.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public enum ChangePasswordTypes
{
	SET_PASSWORD, RESET_PASSWORD;
	
	private static Log logger = LogFactory.getLog(ChangePasswordTypes.class);
	
	public static ChangePasswordTypes getChangePasswordTypeFromString(String strChangePwdType) throws SAPUMEConnectorException
	{
		try
		{
			logger.info("Start getChangePasswordTypeFromString() method");
			if (strChangePwdType != null && !strChangePwdType.equals(""))
			{
				if (ChangePasswordTypes.SET_PASSWORD.toString().equals(strChangePwdType))
				{
					return ChangePasswordTypes.SET_PASSWORD;
					
				} else if (ChangePasswordTypes.RESET_PASSWORD.toString().equals(strChangePwdType)) {
					
					return ChangePasswordTypes.RESET_PASSWORD;
					
				} else {
					
					logger.error("Unexpected ChangePasswordTypes value");
					throw new SAPUMEConnectorException("Unexpected ChangePasswordTypes value");
				}
				
			} else {
				logger.error("Can not obtain one ChangePasswordTypes value from string");
				throw new SAPUMEConnectorException("Can not obtain one ChangePasswordTypes value from string");
			}
			
		} catch (SAPUMEConnectorException e) {
			throw e;
			
		} catch (Exception e) {
			logger.error("Error trying to obtain a ChangePassword type from string received: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Error trying to obtain a ChangePassword type from string received: " + e.getMessage(), e);
		}
	}
	
}