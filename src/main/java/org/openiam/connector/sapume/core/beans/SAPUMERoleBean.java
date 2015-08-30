package org.openiam.connector.sapume.core.beans;

public class SAPUMERoleBean
{
	/*************************************************************
     **********          OBJETOS INTERNOS              ***********
     *************************************************************/
	
	/**
	 * Nombre del Rol de SAPUME
	 */
	private String roleName = "";
	
	/**
	 * Identificador del Rol en SAPUME
	 */
	private String uniqueID = "";
	
	/**
	 * Descripción del Role de SAPUME
	 */
	private String roleDesc = "";
	
    
    /**********************************************************
     **********          CONSTRUCTORES              ***********
	 **********************************************************/

    public SAPUMERoleBean()
    {
    	
    }
    
    public SAPUMERoleBean(String roleName)
    {
    	this.roleName = roleName;
    }
    
    public SAPUMERoleBean(String roleName, String roleId)
    {
    	this.roleName = roleName;
    	this.uniqueID = roleId;
    }
    
    public SAPUMERoleBean(String roleName, String roleId, String roleDesc)
    {
    	this.roleName = roleName;
    	this.uniqueID = roleId;
    	this.roleDesc = roleDesc;
    }

    
    /*************************************************************
     ***********           MÉTODOS PÚBLICOS            ***********
     *************************************************************/
    
    public String getBeanInPrintedFormat()
   	{
   		StringBuffer sb = new StringBuffer();
   		sb.append("uniquename=");
   		sb.append(this.roleName);
   		sb.append("|");
   		sb.append("id=");
   		sb.append(this.uniqueID);
   		sb.append("|");
   		sb.append("description=");
   		sb.append(this.roleDesc);
   		return sb.toString();
   	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return the roleDesc
	 */
	public String getRoleDesc() {
		return roleDesc;
	}

	/**
	 * @param roleDesc the roleDesc to set
	 */
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	/**
	 * @return the uniqueID
	 */
	public String getUniqueID() {
		return uniqueID;
	}

	/**
	 * @param uniqueID the uniqueID to set
	 */
	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}
	
}
