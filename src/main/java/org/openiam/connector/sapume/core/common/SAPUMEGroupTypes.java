package org.openiam.connector.sapume.core.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public enum SAPUMEGroupTypes
{
	GROUP,
	ROLE;

	public String value() {
		return name();
	}

	public static SAPUMEGroupTypes fromValue(String v) {
		return valueOf(v);
	}

	private static Log logger = LogFactory.getLog(SAPUMEGroupTypes.class);
	
	public static SAPUMEGroupTypes getGroupTypesFromSearchQuery(String searchQuery) throws SAPUMEConnectorException
	{
		try
		{
			logger.info("Start getGroupTypesFromSearchQuery() method");
			String type = "";
			if (searchQuery != null)
			{
				if (searchQuery.contains(SAPUMEConstants.SAPUME_RECON_FILTER_GROUPTYPE_FLAG))
				{
					int i = searchQuery.indexOf(SAPUMEConstants.SAPUME_RECON_FILTER_GROUPTYPE_FLAG);
					String groupType = searchQuery.substring(i);
					String[] groupTypeSplited = groupType.split("=");
					if (groupTypeSplited != null && groupTypeSplited.length == 2)
						type = groupTypeSplited[1].trim();
				}
			}
			logger.debug("Group Type obtained: " + type);
			if (type != null && !type.equals(""))
			{
				if (SAPUMEGroupTypes.GROUP.toString().equals(type))
				{
					return SAPUMEGroupTypes.GROUP;
					
				} else if (SAPUMEGroupTypes.ROLE.toString().equals(type)) {
					
					return SAPUMEGroupTypes.ROLE;
					
				} else {
					
					logger.error("Unexpected GROUP_TYPE value");
					throw new SAPUMEConnectorException("Unexpected GROUP_TYPE value");
				}
				
			} else {
				logger.error("Can not obtain group type from searchQuery");
				throw new SAPUMEConnectorException("Can not obtain group type from searchQuery");
			}
			
		} catch (SAPUMEConnectorException e) {
			throw e;
			
		} catch (Exception e) {
			logger.error("Incorrect SearchQuery format: " + searchQuery);
			logger.error("Error trying to obtain group type param from SearchQuery received: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Incorrect SearchQuery format", e);
		}
	}
	
}