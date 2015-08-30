package org.openiam.connector.sapume.core.beans;

import org.openiam.connector.sapume.core.security.SecuredString;

public class SAPUMESystemBean
{
		
	/*************************************************************
     **********          OBJETOS INTERNOS              ***********
     *************************************************************/
		
	private String url;
	private String user;
	private SecuredString passwd;
	
	
    /**********************************************************
     **********          CONSTRUCTORES              ***********
	 **********************************************************/

    public SAPUMESystemBean()
    {
    	
    }
    
    public SAPUMESystemBean(String url, String user, SecuredString passwd)
    {
    	this.url = url;
    	this.user = user;
    	this.passwd = passwd;
    }

    
    /*************************************************************
     ***********           MÉTODOS PÚBLICOS            ***********
     *************************************************************/
    
    /**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
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
	 * @return the passwd
	 */
	public SecuredString getPasswd() {
		return passwd;
	}

	/**
	 * @param passwd the passwd to set
	 */
	public void setPasswd(SecuredString passwd) {
		this.passwd = passwd;
	}
    
}
