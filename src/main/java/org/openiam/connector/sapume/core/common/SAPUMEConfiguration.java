package org.openiam.connector.sapume.core.common;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.connector.sapume.core.beans.SAPUMESystemBean;
import org.openiam.connector.sapume.core.config.Config;
import org.openiam.connector.sapume.core.config.ConfigPropertiesKeys;
import org.openiam.connector.sapume.core.security.SecuredString;

public class SAPUMEConfiguration
{
	/*************************************************************
     **********          OBJETOS INTERNOS              ***********
     *************************************************************/
	
	private static Log logger = LogFactory.getLog(SAPUMEConfiguration.class);
	
	// Variables para propiedades de la conexión con SAPUME
	private String sapUmeUrl;
	private String user;
	private SecuredString password;
		
	// Variables para parámetros de configuración
	private boolean changePasswordAtNextLogon;
	private SecuredString dummyPassword;
	private String sapDateMask;
	private String enableAccountsDate;
	private boolean traceSPMLRequest;
	private String[] policyMapAttList;
	private List<String> reconSearchUsersAttList;
		
	
	/*************************************************************
     ***********             CONSTRUCTORES             *********** 
     *************************************************************/
	
	public SAPUMEConfiguration(SAPUMESystemBean sapumeSystem) throws SAPUMEConnectorException
	{
		try
		{
			logger.debug("Start SAPUMEConfiguration constructor...");
			
			logger.info("Configuration SAP connection params will be obtaied from SAPUMESystemBean object passed...");
			if (sapumeSystem != null)
			{
				this.sapUmeUrl = sapumeSystem.getUrl();
				this.user = sapumeSystem.getUser();
				this.password = sapumeSystem.getPasswd();
			}
			
			logger.info("Configuration general params will be obtaied using Config class which manage properties configuration file entries...");
			this.changePasswordAtNextLogon = (Config.get(ConfigPropertiesKeys.CONFIG_KEY_SAPUME_CHANGEPASSWORDATNEXTLOGON).equalsIgnoreCase("True")) ? true : false;
			
			String strDummyPwd = Config.get(ConfigPropertiesKeys.CONFIG_KEY_SAPUME_DUMMYPASSWORD);
			if (strDummyPwd != null)
			{
				this.dummyPassword = new SecuredString(strDummyPwd.toCharArray());
			}
			strDummyPwd = null;
					
			this.sapDateMask = Config.get(ConfigPropertiesKeys.CONFIG_KEY_SAPUME_DATEMASK);
			this.enableAccountsDate = Config.get(ConfigPropertiesKeys.CONFIG_KEY_SAPUME_ENABLEACCOUNTS_DATE);
			this.traceSPMLRequest = (Config.get(ConfigPropertiesKeys.CONFIG_KEY_SAPUME_TRACESPMLREQUEST).equalsIgnoreCase("True")) ? true : false;
			this.policyMapAttList = Config.getAsArray(ConfigPropertiesKeys.CONFIG_KEY_SAPUME_POLICYMAP_ATTS);
			this.reconSearchUsersAttList = Config.getAsList(ConfigPropertiesKeys.CONFIG_KEY_SAPUME_RECON_SEARCH_USERS_ATTS);
			
		} catch (Exception e) {
			logger.error("Generic error in SAPUMEConfiguration constructor: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error in SAPUMEConfiguration constructor: " + e.getMessage(), e);
		}
	}
	
	
	/*************************************************************
     ***********           MÉTODOS PÚBLICOS            *********** 
     *************************************************************/

	public void validate() throws SAPUMEConnectorException
	{
		logger.debug("Start validate() method");
		
		if (this.sapUmeUrl == null || this.sapUmeUrl.equals(""))
		{
			logger.error("SAPUMEConfiguration 'sapUmeUrl' property cannot be null or empty");
			throw new SAPUMEConnectorException("SAPUMEConfiguration 'sapUmeUrl' property cannot be null or empty");
		}
		
		if (this.user == null || this.user.equals(""))
		{
			logger.error("SAPUMEConfiguration 'user' property cannot be null or empty");
			throw new SAPUMEConnectorException("SAPUMEConfiguration 'user' property cannot be null or empty");
		}
		
		if (this.password == null || this.password.equals(""))
		{
			logger.error("SAPUMEConfiguration 'password' property cannot be null or empty");
			throw new SAPUMEConnectorException("SAPUMEConfiguration 'password' property cannot be null or empty");
		}
		logger.debug("SAPUMEConfiguration validated successfully");
	}
	
	
	/**
	 * @return the sapUmeUrl
	 */
	public String getSapUmeUrl() {
		return sapUmeUrl;
	}
	/**
	 * @param sapUmeUrl the sapUmeUrl to set
	 */
	public void setSapUmeUrl(String sapUmeUrl) {
		this.sapUmeUrl = sapUmeUrl;
	}
	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}
	/**
	 * @return the password
	 */
	public SecuredString getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(SecuredString password) {
		this.password = password;
	}
	/**
	 * @return the dummyPassword
	 */
	public SecuredString getDummyPassword() {
		return dummyPassword;
	}
	/**
	 * @param dummyPassword the dummyPassword to set
	 */
	public void setDummyPassword(SecuredString dummyPassword) {
		this.dummyPassword = dummyPassword;
	}
	/**
	 * @return the traceSPMLRequest
	 */
	public boolean isTraceSPMLRequest() {
		return traceSPMLRequest;
	}
	/**
	 * @param traceSPMLRequest the traceSPMLRequest to set
	 */
	public void setTraceSPMLRequest(boolean traceSPMLRequest) {
		this.traceSPMLRequest = traceSPMLRequest;
	}
	/**
	 * @return the changePasswordAtNextLogon
	 */
	public boolean isChangePasswordAtNextLogon() {
		return changePasswordAtNextLogon;
	}
	/**
	 * @param changePasswordAtNextLogon the changePasswordAtNextLogon to set
	 */
	public void setChangePasswordAtNextLogon(boolean changePasswordAtNextLogon) {
		this.changePasswordAtNextLogon = changePasswordAtNextLogon;
	}
	/**
	 * @return the sapDateMask
	 */
	public String getSapDateMask() {
		return sapDateMask;
	}
	/**
	 * @param sapDateMask the sapDateMask to set
	 */
	public void setSapDateMask(String sapDateMask) {
		this.sapDateMask = sapDateMask;
	}
	/**
	 * @return the policyMapAttList
	 */
	public String[] getPolicyMapAttList() {
		return policyMapAttList;
	}
	/**
	 * @param policyMapAttList the policyMapAttList to set
	 */
	public void setPolicyMapAttList(String[] policyMapAttList) {
		this.policyMapAttList = policyMapAttList;
	}
	/**
	 * @return the reconSearchUsersAttList
	 */
	public List<String> getReconSearchUsersAttList() {
		return reconSearchUsersAttList;
	}

	/**
	 * @param reconSearchUsersAttList the reconSearchUsersAttList to set
	 */
	public void setReconSearchUsersAttList(List<String> reconSearchUsersAttList) {
		this.reconSearchUsersAttList = reconSearchUsersAttList;
	}
	/**
	 * @return the enableAccountsDate
	 */
	public String getEnableAccountsDate() {
		return enableAccountsDate;
	}
	/**
	 * @param enableAccountsDate the enableAccountsDate to set
	 */
	public void setEnableAccountsDate(String enableAccountsDate) {
		this.enableAccountsDate = enableAccountsDate;
	}
	
}
