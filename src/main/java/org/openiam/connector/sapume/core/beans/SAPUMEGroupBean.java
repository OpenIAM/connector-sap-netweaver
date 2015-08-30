package org.openiam.connector.sapume.core.beans;

public class SAPUMEGroupBean
{
	/*************************************************************
     **********          OBJETOS INTERNOS              ***********
     *************************************************************/
	
	/**
	 * Nombre del Grupo de SAPUME
	 */
	private String groupName = "";
	
	/**
	 * Identificador del Grupo en SAPUME
	 */
	private String uniqueID = "";
	
	/**
	 * Descripción del Grupo de SAPUME
	 */
	private String groupDesc = "";
	
    
    /**********************************************************
     **********          CONSTRUCTORES              ***********
	 **********************************************************/

    public SAPUMEGroupBean()
    {
    	
    }
    
    public SAPUMEGroupBean(String groupName)
    {
    	this.groupName = groupName;
    }
    
    public SAPUMEGroupBean(String groupName, String groupId)
    {
    	this.groupName = groupName;
    	this.uniqueID = groupId;
    }
    
    public SAPUMEGroupBean(String groupName, String groupId, String groupDesc)
    {
    	this.groupName = groupName;
    	this.uniqueID = groupId;
    	this.groupDesc = groupDesc;
    }
    
    /*************************************************************
     ***********           MÉTODOS PÚBLICOS            ***********
     *************************************************************/
    
    public String getBeanInPrintedFormat()
   	{
   		StringBuffer sb = new StringBuffer();
   		sb.append("uniquename=");
   		sb.append(this.groupName);
   		sb.append("|");
   		sb.append("id=");
   		sb.append(this.uniqueID);
   		sb.append("|");
   		sb.append("description=");
   		sb.append(this.groupDesc);
   		return sb.toString();
   	}

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @return the groupDesc
	 */
	public String getGroupDesc() {
		return groupDesc;
	}

	/**
	 * @param groupDesc the groupDesc to set
	 */
	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
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
