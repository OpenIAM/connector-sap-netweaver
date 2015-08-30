package org.openiam.connector.sapume.core.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openspml.message.Filter;
import org.openspml.message.FilterTerm;
import org.openspml.util.SpmlBuffer;

public class SAPUMEFilterGenerator
{
	/*************************************************************
     **********          OBJETOS INTERNOS              ***********
     *************************************************************/
		
	private static Log logger = LogFactory.getLog(SAPUMEFilterGenerator.class);

	
	/*************************************************************
     ***********           MÉTODOS PÚBLICOS            ***********
     *************************************************************/
		
	public static Filter buildSPMLFiltertoMatchUserByLogonName(String accountID) throws SAPUMEConnectorException
	{
		try
		{
			logger.info("Start buildSPMLFiltertoMatchUserByLogonName() method");
			
			FilterTerm filterTerm = new FilterTerm();
			filterTerm.setOperation(FilterTerm.OP_EQUAL);
			filterTerm.setName(SAPUMEConstants.SAPUME_FIELD_LOGONNAME);
			filterTerm.setValue(accountID);
			
			Filter filter = new Filter();
			filter.addTerm(filterTerm);
			
			logger.info("SPML Filter build");
			SAPUMEFilterGenerator.traceFilter(filter);
			
			return filter;
			
		} catch (Exception e) {
			logger.error("Generic error building SPML filter to search single user account in SAPUME: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error building SPML filter to search single user account in SAPUME: " + e.getMessage(), e);
		}
		
	}
	
	public static Filter buildSPMLFiltertoMatchGroupByUniqueId(String groupUniqueID) throws SAPUMEConnectorException
	{
		try
		{
			logger.info("Start buildSPMLFiltertoMatchGroupByUniqueId() method");
			
			FilterTerm filterTerm = new FilterTerm();
			filterTerm.setOperation(FilterTerm.OP_EQUAL);
			filterTerm.setName(SAPUMEConstants.SAPUME_FIELD_UNIQUEID);
			filterTerm.setValue(groupUniqueID);
			
			Filter filter = new Filter();
			filter.addTerm(filterTerm);
			
			logger.info("SPML Filter build");
			SAPUMEFilterGenerator.traceFilter(filter);
			
			return filter;
			
		} catch (Exception e) {
			logger.error("Generic error building SPML filter to search single group in SAPUME: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error building SPML filter to search single group in SAPUME: " + e.getMessage(), e);
		}
	}
	
	public static Filter buildSPMLFiltertoMatchGroupByUniqueName(String groupUniqueName) throws SAPUMEConnectorException
	{
		try
		{
			logger.info("Start buildSPMLFiltertoMatchGroupByUniqueName() method");
			
			FilterTerm filterTerm = new FilterTerm();
			filterTerm.setOperation(FilterTerm.OP_EQUAL);
			filterTerm.setName(SAPUMEConstants.SAPUME_FIELD_UNIQUENAME);
			filterTerm.setValue(groupUniqueName);
			
			Filter filter = new Filter();
			filter.addTerm(filterTerm);
			
			logger.info("SPML Filter build");
			SAPUMEFilterGenerator.traceFilter(filter);
			
			return filter;
			
		} catch (Exception e) {
			logger.error("Generic error building SPML filter to search single group in SAPUME: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error building SPML filter to search single group in SAPUME: " + e.getMessage(), e);
		}
		
	}
	
	public static Filter buildSPMLFiltertoMatchRoleByUniqueId(String roleUniqueID) throws SAPUMEConnectorException
	{
		try
		{
			logger.info("Start buildSPMLFiltertoMatchRoleByUniqueId() method");
			
			FilterTerm filterTerm = new FilterTerm();
			filterTerm.setOperation(FilterTerm.OP_EQUAL);
			filterTerm.setName(SAPUMEConstants.SAPUME_FIELD_UNIQUEID);
			filterTerm.setValue(roleUniqueID);
			
			Filter filter = new Filter();
			filter.addTerm(filterTerm);
			
			logger.info("SPML Filter build");
			SAPUMEFilterGenerator.traceFilter(filter);
			
			return filter;
			
		} catch (Exception e) {
			logger.error("Generic error building SPML filter to search single role in SAPUME: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error building SPML filter to search single role in SAPUME: " + e.getMessage(), e);
		}
		
	}
	
	public static Filter buildSPMLFiltertoMatchRoleByUniqueName(String roleUniqueName) throws SAPUMEConnectorException
	{
		try
		{
			logger.info("Start buildSPMLFiltertoMatchRoleByUniqueName() method");
			
			FilterTerm filterTerm = new FilterTerm();
			filterTerm.setOperation(FilterTerm.OP_EQUAL);
			filterTerm.setName(SAPUMEConstants.SAPUME_FIELD_UNIQUENAME);
			filterTerm.setValue(roleUniqueName);
			
			Filter filter = new Filter();
			filter.addTerm(filterTerm);
			
			logger.info("SPML Filter build");
			SAPUMEFilterGenerator.traceFilter(filter);
			
			return filter;
			
		} catch (Exception e) {
			logger.error("Generic error building SPML filter to search single role in SAPUME: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error building SPML filter to search single role in SAPUME: " + e.getMessage(), e);
		}
		
	}
	
	// El filtro a de ser compuesto o una serie de clausualas con SOLO opepadores 'and' o una serie de clausualas con SOLO opepadores 'or'
	// A este método le llegan cosas como esta:
	// equalityMatch('logonname', 'sapusu01')
	// substrings('logonname', 'a') and substrings('logonname', 'z')
	// substrings('logonname', 'a') or substrings('logonname', 'z') or equalityMatch('logonname', 'sapusu01')
	// Como no es posible cosntruir filtros anidados ya que SAPUME no lo soporta, si llega un filtro compuesto de varias clausualas 'or'
	// no se puede incluir en el filtro la clausua que filtra por fecha lastModifyDate, por lo que se prescinde de esta última
	public static Filter buildSPMLFilterFromIDMExpression(String idmFilter, String lastModifyDate) throws SAPUMEConnectorException
	{
		try
		{
			logger.info("Start buildSPMLFiltertoMatchUser() method");
			Filter filter = null;
			if (idmFilter != null)
			{
				if (idmFilter.contains(FilterTerm.OP_AND))
				{
					String[] idmFilterSplited = idmFilter.split(FilterTerm.OP_AND);
					filter = createFilterFromArrayOperations(idmFilterSplited, FilterTerm.OP_AND, lastModifyDate);
					
				} else if (idmFilter.contains(FilterTerm.OP_OR)) {
					
					String[] idmFilterSplited = idmFilter.split(FilterTerm.OP_OR);
					logger.warn("With use of '" + FilterTerm.OP_OR + "' filter operator, its not possible combine with 'lastmodifydate'." +
								" So 'lastmodifydate' will not be used as part of search filter");
					filter = createFilterFromArrayOperations(idmFilterSplited, FilterTerm.OP_OR);
					
				} else { // Se trata de un filtro simple
					
					filter = new Filter();
					
					FilterTerm filterTerm = null;
					if (idmFilter.startsWith(FilterTerm.OP_EQUAL))
						filterTerm = createFilterTermForEqualsExpression(idmFilter);
					else if (idmFilter.startsWith(FilterTerm.OP_SUBSTRINGS))
						filterTerm = createFilterTermForSubstringExpression(idmFilter);
					if (filterTerm != null)
					{
						// Comprobamos si tenemos que incluir la clausula de filtro por fecha lastmodifydate
						if (lastModifyDate == null || lastModifyDate.equals(""))
						{
							filter.addTerm(filterTerm);
							
						} else {
							
							FilterTerm dateFilterTerm = new FilterTerm();
							dateFilterTerm.setOperation(FilterTerm.OP_GTE);
							dateFilterTerm.setName(SAPUMEConstants.SAPUME_FIELD_LASTMODIFYDATE);
							dateFilterTerm.setValue(lastModifyDate);
							
							FilterTerm topFilterTerm = new FilterTerm();
							topFilterTerm.setOperation(FilterTerm.OP_AND);
							topFilterTerm.addOperand(filterTerm);
							topFilterTerm.addOperand(dateFilterTerm);
							filter.addTerm(topFilterTerm);
						}
					}
				}
			}
			
			if (filter == null || filter.getTerms() == null || filter.getTerms().size() < 1)
			{
				logger.error("Could not build a correct SPML Filter");
				throw new SAPUMEConnectorException("Could not build a correct SPML Filter");
			}
			
			logger.info("SPML Filter build");
			SAPUMEFilterGenerator.traceFilter(filter);
			return filter;
			
		} catch (SAPUMEConnectorException e) {
			throw e;
			
		} catch (Exception e) {
			logger.error("Generic error building SPML filter from IDM expression: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error building SPML filter from IDM expression: " + e.getMessage(), e);
		}
	}
	
	
	public static void traceFilter(Filter spmlFilter)
	{
		if (spmlFilter != null)
		{
			if (logger.isDebugEnabled())
			{
				SpmlBuffer buffer = new SpmlBuffer();
				spmlFilter.toXml(buffer);
				logger.debug(System.getProperty("line.separator") + buffer);
			}
			
		} else {
			logger.debug("Filter is null");
		}
	}
	
	
	/*************************************************************
	 ***********           MÉTODOS PRIVADOS            ***********
	 *************************************************************/

	private static Filter createFilterFromArrayOperations(String[] idmFilters, String operator) throws SAPUMEConnectorException
	{
		return createFilterFromArrayOperations(idmFilters, operator, "");
	}
	
	private static Filter createFilterFromArrayOperations(String[] idmFilters, String operator, String lastModifyDate) throws SAPUMEConnectorException
	{
		try
		{
			logger.debug("Start createFilterFromArrayOperations() method");
			
			Filter filter = new Filter();
			if (idmFilters != null && idmFilters.length > 0)
			{
				List<FilterTerm> listFilterTerms = new ArrayList<FilterTerm>();
				for (String oneSubFilter : idmFilters)
				{
					if (oneSubFilter != null)
					{
						FilterTerm filterTerm = null;
						oneSubFilter = oneSubFilter.trim();
						if (oneSubFilter.startsWith(FilterTerm.OP_EQUAL))
							filterTerm = createFilterTermForEqualsExpression(oneSubFilter);
						else if (oneSubFilter.startsWith(FilterTerm.OP_SUBSTRINGS))
							filterTerm = createFilterTermForSubstringExpression(oneSubFilter);
	
						if (filterTerm != null)
							listFilterTerms.add(filterTerm);
					}
				}
				
				if (listFilterTerms.size() > 1)
				{
					// Se construye un FilterTerm con todas las clausulas  
					FilterTerm topFilterTerm = new FilterTerm();
					topFilterTerm.setOperation(operator);
					for (FilterTerm filterTerm : listFilterTerms)
					{
						topFilterTerm.addOperand(filterTerm);
					}
					
					// Comprobamos si tenemos que incluir la clausula de filtro por fecha lastmodifydate
					if (lastModifyDate == null || lastModifyDate.equals(""))
					{
						filter.addTerm(topFilterTerm);
						
					} else {
						
						FilterTerm dateFilterTerm = new FilterTerm();
						dateFilterTerm.setOperation(FilterTerm.OP_GTE);
						dateFilterTerm.setName(SAPUMEConstants.SAPUME_FIELD_LASTMODIFYDATE);
						dateFilterTerm.setValue(lastModifyDate);
						
						topFilterTerm.addOperand(dateFilterTerm);
						filter.addTerm(topFilterTerm);
					}
					
				} else {
					
					// Comprobamos si tenemos que incluir la clausula de filtro por fecha lastmodifydate
					if (lastModifyDate == null || lastModifyDate.equals(""))
					{
						filter.addTerm(listFilterTerms.get(0));
						
					} else {
						
						FilterTerm dateFilterTerm = new FilterTerm();
						dateFilterTerm.setOperation(FilterTerm.OP_GTE);
						dateFilterTerm.setName(SAPUMEConstants.SAPUME_FIELD_LASTMODIFYDATE);
						dateFilterTerm.setValue(lastModifyDate);
						
						FilterTerm topFilterTerm = new FilterTerm();
						topFilterTerm.setOperation(FilterTerm.OP_AND);
						topFilterTerm.addOperand(listFilterTerms.get(0));
						topFilterTerm.addOperand(dateFilterTerm);
						filter.addTerm(topFilterTerm);
					}
				}
			}
			return filter;
			
		} catch (Exception e) {
			logger.error("Generic error creating Filter from array operations: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error creating Filter from array operations: " + e.getMessage(), e);
		}
	}


	private static FilterTerm createFilterTermForEqualsExpression(String idmFilter) throws SAPUMEConnectorException
	{
		try
		{
			logger.debug("Start createFilterForEqualsExpression() method");

			if (idmFilter != null && idmFilter.startsWith(FilterTerm.OP_EQUAL))
			{
				FilterTerm filterTerm = new FilterTerm();
				filterTerm.setOperation(FilterTerm.OP_EQUAL);
				String[] filterElements = SAPUMEFilterGenerator.parseSubfilter(idmFilter);
				filterTerm.setName(filterElements[0]);
				filterTerm.setValue(filterElements[1]);
				return filterTerm;

			} else {
				logger.error("Filter format is unexpected for " + FilterTerm.OP_EQUAL + " expression");
				throw new SAPUMEConnectorException("Filter format is unexpected for " + FilterTerm.OP_EQUAL + " expression");
			}

		} catch (SAPUMEConnectorException e) {
			throw e;

		} catch (Exception e) {
			logger.error("Generic error creating FilterTerm for equals expression: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error creating FilterTerm for equals expression: " + e.getMessage(), e);
		}
	}


	private static FilterTerm createFilterTermForSubstringExpression(String idmFilter) throws SAPUMEConnectorException
	{
		try
		{
			logger.debug("Start createFilterForSubstringExpression() method");

			if (idmFilter != null && idmFilter.startsWith(FilterTerm.OP_SUBSTRINGS))
			{
				FilterTerm filterTerm = new FilterTerm();
				filterTerm.setOperation(FilterTerm.OP_SUBSTRINGS);
				String[] filterElements = SAPUMEFilterGenerator.parseSubfilter(idmFilter);
				filterTerm.setName(filterElements[0]);
				List<String> listSubstr = new ArrayList<String>();
				listSubstr.add(filterElements[1]);
				filterTerm.setSubstrings(listSubstr);
				return filterTerm;

			} else {
				logger.error("Filter format is unexpected for " + FilterTerm.OP_SUBSTRINGS + " expression");
				throw new SAPUMEConnectorException("Filter format is unexpected for " + FilterTerm.OP_SUBSTRINGS + " expression");
			}

		} catch (SAPUMEConnectorException e) {
			throw e;

		} catch (Exception e) {
			logger.error("Generic error creating FilterTerm for substrings expression: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error creating FilterTerm for substrings expression: " + e.getMessage(), e);
		}
	}

	private static String[] parseSubfilter(String subFilter) throws SAPUMEConnectorException
	{
		try
		{
			logger.debug("Start parseSubfilter() method");
			logger.debug("subFilter to parse: " + subFilter);
			String str = subFilter.substring(subFilter.indexOf("(") + 1, subFilter.indexOf(")")).trim();
			String[] subFilterElements = str.split(",");
			for (int i=0; i < subFilterElements.length; i++)
			{
				subFilterElements[i] = subFilterElements[i].trim().replaceAll("'",""); 
			}
			return subFilterElements;

		} catch (Exception e) {
			logger.error("Error parsing subFilter: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Error parsing subFilter: " + e.getMessage(), e);
		}
	}
	
}
