package org.openiam.connector.sapume.core.common;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.connector.sapume.service.types.ExtensibleAttribute;
import org.openiam.connector.sapume.service.wrapper.BaseRequestType;

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
	
	public static SAPUMEGroupTypes getGroupTypesFromRequest(BaseRequestType idmRequest) throws SAPUMEConnectorException
	{
		try
		{
			SAPUMEGroupTypes groupType = null;
			
			logger.info("Start getGroupTypesFromRequest() method");
			if (idmRequest != null && idmRequest.getExtensibleObject() != null && idmRequest.getExtensibleObject().getAttributes() != null)
			{
				logger.info("Group type will be search in request extensible attribute: " + SAPUMEConstants.SAPUME_RECON_FILTER_GROUPTYPE_FLAG);
				String extAttValue = "";
				for (ExtensibleAttribute extAttribute : idmRequest.getExtensibleObject().getAttributes())
				{
					logger.debug("AttName: " + extAttribute.getName() + " -- AttValue: " + extAttribute.getValue());
					if (extAttribute.getName().equals(SAPUMEConstants.SAPUME_RECON_FILTER_GROUPTYPE_FLAG))
					{
						logger.debug(SAPUMEConstants.SAPUME_RECON_FILTER_GROUPTYPE_FLAG + " ExtensibleAttribute detected!");
						byte[] decodeValue = Base64.decodeBase64(extAttribute.getValue());
						extAttValue = new String(decodeValue);
						logger.debug("Base64 value decoded: " + extAttValue);
					}
				}
				
				logger.debug("Extensible attibute for Group Type obtained: " + extAttValue);
				if (extAttValue != null && !extAttValue.equals(""))
				{
					if (SAPUMEGroupTypes.GROUP.toString().equals(extAttValue))
					{
						groupType = SAPUMEGroupTypes.GROUP;
						
					} else if (SAPUMEGroupTypes.ROLE.toString().equals(extAttValue)) {
						
						groupType = SAPUMEGroupTypes.ROLE;
						
					} else {
						
						logger.error("Unexpected GROUP_TYPE value --> return null");
					}
					
				} else {
					logger.error("Can not obtain group type from request extensible attribute --> return null");
				}
			}
			
			return groupType;
			
		} catch (Exception e) {
			logger.error("Error trying to obtain group type from IDM request data: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Error trying to obtain group type from IDM request data: " + e.getMessage(), e);
		}
	}
	
}