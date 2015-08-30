package org.openiam.connector.sapume.core.beans;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.connector.sapume.core.common.SAPUMEConnectorException;
import org.openiam.connector.sapume.core.common.SAPUMEConstants;
import org.openiam.connector.sapume.core.common.SAPUMEGroupTypes;


public class GroupFilterBean {
	
	
	/*************************************************************
     **********          OBJETOS INTERNOS              ***********
     *************************************************************/

	public static final String IDM_FILTER_EXPRESSION_SEPARATOR = "\\|";
	
	
	private static Log logger = LogFactory.getLog(GroupFilterBean.class);
	
    /** 
     * Tipo de grupo
     */
    private SAPUMEGroupTypes groupType = null;
    
    /** 
     * Filtro de búsqueda
     */
    private String filter = "";
        
    
    /**********************************************************
     **********          CONSTRUCTORES              ***********
	 **********************************************************/
	    
	public GroupFilterBean()
	{
	
	}
		
	public GroupFilterBean(SAPUMEGroupTypes groupType, String filter)
	{
		this.groupType = groupType;
		this.filter = filter;
	}

	
	/*************************************************************
     ***********           MÉTODOS PÚBLICOS            ***********
     *************************************************************/
	
	//GROUP_TYPE=GROUP | FILTER=equalityMatch('uniquename', 'Guest')
	public void fillObjectFromidmFilterExpression(String idmGroupFilterExpression) throws SAPUMEConnectorException
	{
		try
		{
			logger.info("Start fillObjectFromidmFilterExpression() method");
			logger.info("idmGroupFilterExpression: " + idmGroupFilterExpression);
			if (idmGroupFilterExpression != null && !idmGroupFilterExpression.equals(""))
			{
				String[] idmFilterExprSplited = idmGroupFilterExpression.split(IDM_FILTER_EXPRESSION_SEPARATOR);
				for (String expression : idmFilterExprSplited)
				{
					expression = expression.trim();
					if(expression.startsWith(SAPUMEConstants.SAPUME_RECON_FILTER_GROUPTYPE_FLAG))
					{
						// Determinamos si es GROUP o ROLE    			
						this.groupType = SAPUMEGroupTypes.getGroupTypesFromSearchQuery(expression);
		    			
					} else if (expression.startsWith(SAPUMEConstants.SAPUME_RECON_FILTER_SEARCHFILTER_FLAG)) {
						
						String[] filterSplited = expression.split("=");
						if (filterSplited != null && filterSplited.length == 2)
							this.filter = filterSplited[1].trim();
						
					}
				}
			}
			
			logger.info("groupType: " + this.groupType);
			logger.info("filter: " + this.filter);
			
		} catch (Exception e) {
			logger.error("Error trying to fill GroupFilterBean from IDM search filter expression: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Error trying to fill GroupFilterBean from IDM search filter expression: " + e.getMessage(), e);
		}
	}
	
	
	/**
	 * @return the groupType
	 */
	public SAPUMEGroupTypes getGroupType() {
		return groupType;
	}

	/**
	 * @param groupType the groupType to set
	 */
	public void setGroupType(SAPUMEGroupTypes groupType) {
		this.groupType = groupType;
	}

	/**
	 * @return the filter
	 */
	public String getFilter() {
		return filter;
	}

	/**
	 * @param filter the filter to set
	 */
	public void setFilter(String filter) {
		this.filter = filter;
	}
	
}
