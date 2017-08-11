package org.openiam.connector.sapume.core.beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openiam.connector.sapume.core.common.SAPUMEConstants;

public class ProvisionSAPUMEUserBean
{
	/*************************************************************
     **********          OBJETOS INTERNOS              ***********
     *************************************************************/	
	
	/**
	 * Identificador cuenta de usuario
	 */
	private String userID = "";
			
	/**
     * Listado de atributos del usuario
     */
    private List<AttributeBean> attsList = null;
    
    /**
     * Listado de Grupos asociados a un usuario (usado en consulta: lookup, search)
     */
    private List<SAPUMEGroupBean> groupsList = null;
    
    /**
     * Listado de Grupos para añadir al usuario (usado en Provisioning: create, modify)
     */
    private List<SAPUMEGroupBean> groupsForAdd = null;
    
    /**
     * Listado de Grupos para borrar al usuario (usado en Provisioning: create, modify)
     */
    private List<SAPUMEGroupBean> groupsForDelete = null;
    
    /**
     * Listado de Roles asociados a un usuario
     */
    private List<SAPUMERoleBean> rolesList = null;    
    
    /**
     * Listado de Roles para añadir al usuario (usado en Provisioning: create, modify)
     */
    private List<SAPUMERoleBean> rolesForAdd = null;
    
    /**
     * Listado de Roles para borrar al usuario (usado en Provisioning: create, modify)
     */
    private List<SAPUMERoleBean> rolesForDelete = null;
    
    
    // Flags que determinan operaciones especiales a realizar sobre el usuario
    private boolean flagDisable = false;
    private boolean flagEnable = false;
    private boolean flagMngRoles = false;
    private boolean flagMngGroups = false;
    // Flags que identifica si el cambio de contraseña a realizar es administrativo o no
    private boolean administrativePwdChange = false;
    
    
    /**********************************************************
     **********          CONSTRUCTORES              ***********
	 **********************************************************/

    public ProvisionSAPUMEUserBean()
    {
    	
    }
    
    public ProvisionSAPUMEUserBean(String userID)
    {
    	this.userID = userID;
    }
    
    public ProvisionSAPUMEUserBean(String userID, Map<String, Object> attributesMap)
    {
    	this.userID = userID;
    	if (attributesMap != null && attributesMap.size() > 0)
    	{
    		for (Map.Entry<String, Object> oneAtt : attributesMap.entrySet())
    		{
        		this.addNewAttribute(new AttributeBean(oneAtt.getKey(), oneAtt.getValue()));
    		}
    	}
    }
   
    
    /*************************************************************
     ***********           MÉTODOS PÚBLICOS            ***********
     *************************************************************/
    
    /**
	 * Used for validation for creating a Notes user.
	 */
	public static String[] getSAPUMERequiredFields()
	{
		return new String[] { SAPUMEConstants.SAPUME_FIELD_LOGONNAME, SAPUMEConstants.SAPUME_FIELD_FIRSTNAME, SAPUMEConstants.SAPUME_FIELD_LASTNAME, SAPUMEConstants.SAPUME_FIELD_PASSWORD };
	}
	
	
	/**
	 * Método que añade un nuevo atributo a la lista de atributos
	 * @param att
	 */
	public void addNewAttribute (AttributeBean att) 
	{	
		if (this.attsList == null)
			this.attsList = new ArrayList<AttributeBean>();
		
		this.attsList.add(att);
	}
	
	
	/**
	 * Método que obtiene una cadena de texto con la representación de la información contenida en el bean de usuario 
	 * @return
	 */
	public String getBeanInPrintedFormat()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("userID==");
		sb.append(this.userID);
		sb.append("###AttList==");
		if (this.attsList != null && this.attsList.size() > 0)
		{
			boolean first =  true;
			for (AttributeBean oneAtt : attsList)
			{
				if (!first)
					sb.append("|");
				else
					first = false;
				sb.append(oneAtt.getAttName());
				sb.append("=");
				sb.append((!oneAtt.getAttName().equals(SAPUMEConstants.SAPUME_FIELD_PASSWORD)) ? oneAtt.getAttValue() : "xxxxx");
			}
			
		} else {
			sb.append("**EMPTY**");
		}
		
		sb.append("###Groups==");
		if (this.groupsList != null && this.groupsList.size() > 0)
		{
			List<String> groupsNames = this.getGroupsNamesShortedList(this.groupsList);
			boolean first =  true;
			for (String oneGroup : groupsNames)
			{
				if (!first)
					sb.append(",");
				else
					first = false;
				sb.append(oneGroup);
			}
			
		} else {
			sb.append("**EMPTY**");
		}
		
		sb.append("###GroupsForAdd==");
		if (this.groupsForAdd != null && this.groupsForAdd.size() > 0)
		{
			List<String> groupsNames = this.getGroupsNamesShortedList(this.groupsForAdd);
			boolean first =  true;
			for (String oneGroup : groupsNames)
			{
				if (!first)
					sb.append(",");
				else
					first = false;
				sb.append(oneGroup);
			}
			
		} else {
			sb.append("**EMPTY**");
		}
		
		sb.append("###GroupsForDelete==");
		if (this.groupsForDelete != null && this.groupsForDelete.size() > 0)
		{
			List<String> groupsNames = this.getGroupsNamesShortedList(this.groupsForDelete);
			boolean first =  true;
			for (String oneGroup : groupsNames)
			{
				if (!first)
					sb.append(",");
				else
					first = false;
				sb.append(oneGroup);
			}
			
		} else {
			sb.append("**EMPTY**");
		}
		
		sb.append("###Roles==");
		if (this.rolesList != null && this.rolesList.size() > 0)
		{
			List<String> rolesNames = this.getRolesNamesShortedList(this.rolesList);
			boolean first =  true;
			for (String oneRole : rolesNames)
			{
				if (!first)
					sb.append(",");
				else
					first = false;
				sb.append(oneRole);
			}
			
		} else {
			sb.append("**EMPTY**");
		}
		
		sb.append("###RolesForAdd==");
		if (this.rolesForAdd != null && this.rolesForAdd.size() > 0)
		{
			List<String> rolesNames = this.getRolesNamesShortedList(this.rolesForAdd);
			boolean first =  true;
			for (String oneRole : rolesNames)
			{
				if (!first)
					sb.append(",");
				else
					first = false;
				sb.append(oneRole);
			}
			
		} else {
			sb.append("**EMPTY**");
		}
		
		sb.append("###RolesForDelete==");
		if (this.rolesForDelete != null && this.rolesForDelete.size() > 0)
		{
			List<String> rolesNames = this.getRolesNamesShortedList(this.rolesForDelete);
			boolean first =  true;
			for (String oneRole : rolesNames)
			{
				if (!first)
					sb.append(",");
				else
					first = false;
				sb.append(oneRole);
			}
			
		} else {
			sb.append("**EMPTY**");
		}
		
		return sb.toString();
	}
	
	
	public List<String> getGroupsNamesShortedList(List<SAPUMEGroupBean> listGroups)
	{
		List<String> groupsNames = null;
		if (listGroups != null && listGroups.size() > 0)
		{
			groupsNames = new ArrayList<String>();
			for (SAPUMEGroupBean oneGrpBean : listGroups)
			{
				groupsNames.add(oneGrpBean.getGroupName());
			}
			Collections.sort(groupsNames);
		}
		return groupsNames;
	}
	
	
	public List<String> getRolesNamesShortedList(List<SAPUMERoleBean> listRoles)
	{
		List<String> rolesNames = null;
		if (listRoles != null && listRoles.size() > 0)
		{
			rolesNames = new ArrayList<String>();
			for (SAPUMERoleBean oneRoleBean : listRoles)
			{
				rolesNames.add(oneRoleBean.getRoleName());
			}
			Collections.sort(rolesNames);
		}
		return rolesNames;
	}
	
	
	public Map<String, Object> getAttsListInMap()
	{
		Map<String, Object> attributesMap = new HashMap<String, Object>();
		if (attsList != null && attsList.size() > 0)
		{
			for (AttributeBean oneAtt : attsList)
			{
				attributesMap.put(oneAtt.getAttName(), oneAtt.getAttValue());
			}
		}
		return attributesMap;
	}
	
	public void setAttsListFromMap(Map<String, Object> attributesMap)
	{
		if (attributesMap != null && attributesMap.size() > 0)
    	{
    		for (Map.Entry<String, Object> oneAtt : attributesMap.entrySet())
    		{
        		this.addNewAttribute(new AttributeBean(oneAtt.getKey(), oneAtt.getValue()));
    		}
    	}
	}
	
	
	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * @return the attsList
	 */
	public List<AttributeBean> getAttsList() {
		return attsList;
	}

	/**
	 * @param attsList the attsList to set
	 */
	public void setAttsList(List<AttributeBean> attsList) {
		this.attsList = attsList;
	}

	/**
	 * @return the flagDisable
	 */
	public boolean isFlagDisable() {
		return flagDisable;
	}

	/**
	 * @param flagDisable the flagDisable to set
	 */
	public void setFlagDisable(boolean flagDisable) {
		this.flagDisable = flagDisable;
	}

	/**
	 * @return the flagEnable
	 */
	public boolean isFlagEnable() {
		return flagEnable;
	}

	/**
	 * @param flagEnable the flagEnable to set
	 */
	public void setFlagEnable(boolean flagEnable) {
		this.flagEnable = flagEnable;
	}

	/**
	 * @return the flagMngRoles
	 */
	public boolean isFlagMngRoles() {
		return flagMngRoles;
	}

	/**
	 * @param flagMngRoles the flagMngRoles to set
	 */
	public void setFlagMngRoles(boolean flagMngRoles) {
		this.flagMngRoles = flagMngRoles;
	}

	/**
	 * @return the flagMngGroups
	 */
	public boolean isFlagMngGroups() {
		return flagMngGroups;
	}

	/**
	 * @param flagMngGroups the flagMngGroups to set
	 */
	public void setFlagMngGroups(boolean flagMngGroups) {
		this.flagMngGroups = flagMngGroups;
	}

	/**
	 * @return the administrativePwdChange
	 */
	public boolean isAdministrativePwdChange() {
		return administrativePwdChange;
	}

	/**
	 * @param administrativePwdChange the administrativePwdChange to set
	 */
	public void setAdministrativePwdChange(boolean administrativePwdChange) {
		this.administrativePwdChange = administrativePwdChange;
	}

	/**
	 * @return the groupsList
	 */
	public List<SAPUMEGroupBean> getGroupsList() {
		return groupsList;
	}

	/**
	 * @param groupsList the groupsList to set
	 */
	public void setGroupsList(List<SAPUMEGroupBean> groupsList) {
		this.groupsList = groupsList;
	}

	/**
	 * @return the rolesList
	 */
	public List<SAPUMERoleBean> getRolesList() {
		return rolesList;
	}

	/**
	 * @param rolesList the rolesList to set
	 */
	public void setRolesList(List<SAPUMERoleBean> rolesList) {
		this.rolesList = rolesList;
	}

	/**
	 * @return the groupsForAdd
	 */
	public List<SAPUMEGroupBean> getGroupsForAdd() {
		return groupsForAdd;
	}

	/**
	 * @param groupsForAdd the groupsForAdd to set
	 */
	public void setGroupsForAdd(List<SAPUMEGroupBean> groupsForAdd) {
		this.groupsForAdd = groupsForAdd;
	}

	/**
	 * @return the groupsForDelete
	 */
	public List<SAPUMEGroupBean> getGroupsForDelete() {
		return groupsForDelete;
	}

	/**
	 * @param groupsForDelete the groupsForDelete to set
	 */
	public void setGroupsForDelete(List<SAPUMEGroupBean> groupsForDelete) {
		this.groupsForDelete = groupsForDelete;
	}

	/**
	 * @return the rolesForAdd
	 */
	public List<SAPUMERoleBean> getRolesForAdd() {
		return rolesForAdd;
	}

	/**
	 * @param rolesForAdd the rolesForAdd to set
	 */
	public void setRolesForAdd(List<SAPUMERoleBean> rolesForAdd) {
		this.rolesForAdd = rolesForAdd;
	}

	/**
	 * @return the rolesForDelete
	 */
	public List<SAPUMERoleBean> getRolesForDelete() {
		return rolesForDelete;
	}

	/**
	 * @param rolesForDelete the rolesForDelete to set
	 */
	public void setRolesForDelete(List<SAPUMERoleBean> rolesForDelete) {
		this.rolesForDelete = rolesForDelete;
	}
	
}
