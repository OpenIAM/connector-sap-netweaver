package org.openiam.connector.sapume.response;

import java.nio.charset.Charset;
import java.util.*;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.connector.sapume.core.beans.ProvisionSAPUMEUserBean;
import org.openiam.connector.sapume.core.beans.SAPUMEGroupBean;
import org.openiam.connector.sapume.core.beans.SAPUMERoleBean;
import org.openiam.connector.sapume.core.common.SAPUMEConstants;
import org.openiam.connector.sapume.request.AttributeReader;
import org.openiam.connector.sapume.service.types.ExtensibleAttribute;
import org.openiam.connector.sapume.service.wrapper.AttributeOperationEnum;
import org.openiam.connector.sapume.service.wrapper.BaseAttribute;
import org.openiam.connector.sapume.service.wrapper.BaseAttributeContainer;
import org.openiam.connector.sapume.service.wrapper.ObjectValue;
import org.openiam.connector.sapume.service.wrapper.SearchResponse2;


public class LookupResponseWriter
{
	/*************************************************************
     **********          OBJETOS INTERNOS              ***********
     *************************************************************/
	
	private static Log logger = LogFactory.getLog(LookupResponseWriter.class);

	private SearchResponse2 response;
	
	/*************************************************************
     ***********             CONSTRUCTORES             *********** 
     *************************************************************/

	public LookupResponseWriter(SearchResponse2 response)
	{
		logger.debug("LookupResponseWriter constructor");
		this.response = response;
	}

	
	/*************************************************************
     ***********           MÉTODOS PÚBLICOS            *********** 
     *************************************************************/
	
	/**
	 * Write a user's data into the Web service response.
	 * Presently the attribute values need to be encoded in Base64.
	 * @param user
	 */
	public void writeUser(ProvisionSAPUMEUserBean sapUser)
	{
		logger.debug("Start writeUser() method");
		
		String identity = sapUser.getUserID();
		logger.debug("Identity userID: " + identity);
		ObjectValue objectValue = new ObjectValue();
		objectValue.setObjectIdentity(identity);

		// Proceso de los atributos del usuario
		if (sapUser.getAttsListInMap() != null && sapUser.getAttsListInMap().size() > 0)
		{
			logger.debug("There are user attributes to write in response");
			AttributeWriter writer = new AttributeWriter(objectValue.getAttributeList());
			Map<String, Object> items = sapUser.getAttsListInMap();
			for (String field : items.keySet())
			{
				writer.setEncodedValue(field, items.get(field));
			}
			
		} else {
			logger.debug("There arent't user attributes to write in response");
		}

		// Proceso de los Groups
		if (sapUser.getGroupsList() != null && sapUser.getGroupsList().size() > 0)
		{
			logger.debug("There are Groups to write in response");
			BaseAttributeContainer containerGroup = new BaseAttributeContainer();
			List<String> groups = sapUser.getGroupsNamesShortedList(sapUser.getGroupsList());
			for (String group : groups)
			{
				String value = DatatypeConverter.printBase64Binary(group.getBytes(Charset.forName("UTF-8")));
				BaseAttribute ba = new BaseAttribute();
				ba.setName(group);
				ba.setValue(value);
				ba.setOperationEnum(AttributeOperationEnum.NOCHANGE);
				containerGroup.getAttributeList().add(ba);
			}
			ExtensibleAttribute eaGroup = new ExtensibleAttribute();
			eaGroup.setName(SAPUMEConstants.SAPUME_FIELD_ASSIGNEDGROUPS);
			eaGroup.setDataType(AttributeReader.DATA_TYPE_MEMBERSHIP);
			eaGroup.setAttributeContainer(containerGroup);
			objectValue.getAttributeList().add(eaGroup);
			
		} else {
			logger.debug("There aren´t Groups to write in response");
		}
	
		// Proceso de los Roles
		if (sapUser.getRolesList() != null && sapUser.getRolesList().size() > 0)
		{
			logger.debug("There are Roles to write in response");
			BaseAttributeContainer containerRole = new BaseAttributeContainer();
			List<String> roles = sapUser.getRolesNamesShortedList(sapUser.getRolesList());
			for (String role : roles)
			{
				String value = DatatypeConverter.printBase64Binary(role.getBytes(Charset.forName("UTF-8")));
				BaseAttribute ba = new BaseAttribute();
				ba.setName(role);
				ba.setValue(value);
				ba.setOperationEnum(AttributeOperationEnum.NOCHANGE);
				containerRole.getAttributeList().add(ba);
			}
			ExtensibleAttribute eaRole = new ExtensibleAttribute();
			eaRole.setName(SAPUMEConstants.SAPUME_FIELD_ASSIGNEDROLES);
			eaRole.setDataType(AttributeReader.DATA_TYPE_MEMBERSHIP);
			eaRole.setAttributeContainer(containerRole);
			objectValue.getAttributeList().add(eaRole);
			
		} else {
			logger.debug("There aren´t Roles to write in response");
		}
		
		response.getObjectList().add(objectValue);
	}

	
	public void writeUsers(List<ProvisionSAPUMEUserBean> sapUsers)
	{
		for (ProvisionSAPUMEUserBean sapUser : sapUsers)
		{
			writeUser(sapUser);
		}
	}

	
	public void writeGroup(SAPUMEGroupBean group)
	{
		logger.debug("Start writeGroup() method");
		
		String groupName = group.getGroupName();
		logger.debug("Group name: " + groupName);
		ObjectValue objectValue = new ObjectValue();
		objectValue.setObjectIdentity(groupName);
		
		AttributeWriter writer = new AttributeWriter(objectValue.getAttributeList());
		writer.setEncodedValue(SAPUMEConstants.SAPUME_FIELD_UNIQUENAME, groupName);

		if (group.getUniqueID() != null && !group.getUniqueID().equals(""))
			writer.setEncodedValue(SAPUMEConstants.SAPUME_FIELD_UNIQUEID, group.getUniqueID());
		
		if (group.getGroupDesc() != null && !group.getGroupDesc().equals(""))
			writer.setEncodedValue(SAPUMEConstants.SAPUME_FIELD_DESCRIPTION, group.getGroupDesc());
		
		response.getObjectList().add(objectValue);
	}
	
	
	public void writeGroups(List<SAPUMEGroupBean> groups)
	{
		for (SAPUMEGroupBean group : groups)
		{
			writeGroup(group);
		}
	}
	
	public void writeRole(SAPUMERoleBean role)
	{
		logger.debug("Start writeRole() method");
		
		String roleName = role.getRoleName();
		logger.debug("Role name: " + roleName);
		ObjectValue objectValue = new ObjectValue();
		objectValue.setObjectIdentity(roleName);
		
		AttributeWriter writer = new AttributeWriter(objectValue.getAttributeList());
		writer.setEncodedValue(SAPUMEConstants.SAPUME_FIELD_UNIQUENAME, roleName);

		if (role.getUniqueID() != null && !role.getUniqueID().equals(""))
			writer.setEncodedValue(SAPUMEConstants.SAPUME_FIELD_UNIQUEID, role.getUniqueID());
		
		if (role.getRoleDesc() != null && !role.getRoleDesc().equals(""))
			writer.setEncodedValue(SAPUMEConstants.SAPUME_FIELD_DESCRIPTION, role.getRoleDesc());
		
		response.getObjectList().add(objectValue);
	}
	
	
	public void writeRoles(List<SAPUMERoleBean> roles)
	{
		for (SAPUMERoleBean role : roles)
		{
			writeRole(role);
		}
	}
	
}
