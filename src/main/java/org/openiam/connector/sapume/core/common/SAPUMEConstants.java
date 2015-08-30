package org.openiam.connector.sapume.core.common;

/**
 * Interfaz para la definición de constantes que se requieran.
 * <br>
 * @author SIA
 *
 */
public interface SAPUMEConstants
{
	// Indica el nombre del parámetro de configuración del servlet que contendrá la ruta del fichero properties de configuración.
	public static final String SERVLET_PARAM_CONFIG_PROPERTIES_FILE = "ConfigFile";
	
	public static final String SAPUME_FIELD_LOGONNAME = "logonname";
	
	public static final String SAPUME_FIELD_UNIQUEID = "id";
		
	public static final String SAPUME_FIELD_FIRSTNAME = "firstname";
	
	public static final String SAPUME_FIELD_LASTNAME = "lastname";
	
	public static final String SAPUME_FIELD_PASSWORD = "password";
	
	public static final String SAPUME_FIELD_OLDPASSWORD = "oldpassword";
	
	public static final String SAPUME_FIELD_VALIDTO = "validto";
	
	public static final String SAPUME_FIELD_VALIDFROM = "validfrom";

	public static final String SAPUME_FIELD_ISLOCKED = "islocked";
	
	public static final String SAPUME_FIELD_SECURITYPOLICY = "securitypolicy";
		
	public static final String SAPUME_FIELD_ASSIGNEDGROUPS = "assignedgroups";
	
	public static final String SAPUME_FIELD_ASSIGNEDROLES = "assignedroles";
	
	public static final String SAPUME_FIELD_UNIQUENAME = "uniquename";
	
	public static final String SAPUME_FIELD_DESCRIPTION = "description";
	
	public static final String SAPUME_FIELD_LASTMODIFYDATE = "lastmodifydate";	
	
	
	public static final String SAPUME_ATTRIBUTES_METADATAELEMENT_SEPARATOR = ":";
			
	
	public static final String SAPUME_TARGETOBJCLASS_USER = "sapuser";
	
	public static final String SAPUME_TARGETOBJCLASS_GROUP = "sapgroup";
	
	public static final String SAPUME_TARGETOBJCLASS_ROLE = "saprole";
	
	
	public static final String SAPUME_MEMBERSHIP_OPERATION_ADD = "add";
	
	public static final String SAPUME_MEMBERSHIP_OPERATION_DELETE = "delete";
	
	
	public static final String SAPUME_USER_SECURITYPOLICY_TECHNICAL = "technical";
	
	
	public static final String SAPUME_VALUE_USERLOCK_YES = "True";
	
	public static final String SAPUME_VALUE_USERLOCK_NO = "False";
	
	public static final String SAPUME_RECON_FILTER_INCREMENTAL_TIMESTAMP_FLAG = "#TIMESTAMP#";
	
	public static final String SAPUME_RECON_FILTER_GROUPTYPE_FLAG = "GROUP_TYPE";
	
	public static final String SAPUME_RECON_FILTER_SEARCHFILTER_FLAG = "FILTER";
	
}
