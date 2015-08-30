package org.openiam.connector.sapume.request;

import java.nio.charset.Charset;
import java.util.*;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.connector.sapume.core.common.SAPUMEConstants;
import org.openiam.connector.sapume.core.security.SecuredString;
import org.openiam.connector.sapume.service.types.ExtensibleAttribute;
import org.openiam.connector.sapume.service.wrapper.BaseRequestType;


public class AttributeReader
{
	/*************************************************************
     **********          OBJETOS INTERNOS              ***********
     *************************************************************/
	
	private static Log logger = LogFactory.getLog(AttributeReader.class);
		
	public static final String DATA_TYPE_MEMBERSHIP = "memberOf";
	
	private static BaseRequestType request;
	
	private List<ExtensibleAttribute> attributes;

	/*************************************************************
     ***********             SINGLETOON             *********** 
     *************************************************************/
	
	private static AttributeReader attributeReader;
	
	public static AttributeReader getInstance(BaseRequestType req)
	{
		if (request != req) {
			attributeReader = new AttributeReader(req);
		}
		return attributeReader;
	}

	
	/*************************************************************
     ***********             CONSTRUCTORES             *********** 
     *************************************************************/

	private AttributeReader(BaseRequestType req)
	{
		logger.debug("AttributeReader constructor");
		
		request = req;
		if (request.getExtensibleObject() == null) {
			attributes = new ArrayList<ExtensibleAttribute>();
		} else {
			attributes = request.getExtensibleObject().getAttributes();
		}
	}

	
	/*************************************************************
     ***********           MÉTODOS PÚBLICOS            ***********
     *************************************************************/
	
	
	public Map<String, Object> getAllValues() throws Exception
	{
		logger.debug("Start getAllValues() method");
		
		Map<String, Object> result = new Hashtable<String, Object>();

		Object value;
		for (ExtensibleAttribute ea : attributes)
		{
			if (ea.getName().equals(SAPUMEConstants.SAPUME_FIELD_PASSWORD))
			{
				try {
					
					String strPwd = getValue(ea.getName()).toString();
					value = new SecuredString(strPwd.toCharArray());
					strPwd = null;
					
				} catch (Exception e) {
					logger.error("Error reading sensible information and building a SecuredString instance: " + e.getMessage(), e);
					throw e;
				}
				
			} else {
				
				// memberOf attribute doesn't have a value.
				if (ea.getDataType() != null && ea.getDataType().equalsIgnoreCase(DATA_TYPE_MEMBERSHIP))
					value = "";
				else
					value = getValue(ea.getName());
			}
			
			result.put(ea.getName(), value);
		}

		return result;
	}

	public ExtensibleAttribute getAttributeByType(String type)
	{
		logger.debug("Start getAttributeByType() method");
		for (ExtensibleAttribute ea : attributes) {
			if (ea.getDataType() != null
					&& ea.getDataType().equalsIgnoreCase(type)) {
				return ea;
			}
		}
		return null;
	}
	
	public ExtensibleAttribute getAttributeByName(String name)
	{
		logger.debug("Start getAttributeByName() method");
		for (ExtensibleAttribute ea : attributes) {
			if (ea.getName() != null && ea.getName().equalsIgnoreCase(name)) {
				return ea;
			}
		}
		return null;
	}

	public ExtensibleAttribute getAttributeByNameAndType(String name, String type)
	{
		logger.debug("Start getAttributeByNameAndType() method");
		for (ExtensibleAttribute ea : attributes) {
			if (ea.getName() != null && ea.getName().equalsIgnoreCase(name) && ea.getDataType() != null && ea.getDataType().equalsIgnoreCase(type))
			{
				return ea;
			}
		}
		return null;
	}
	
	public String getBase64Decoded(String encoded)
	{
		logger.debug("Start getBase64Decoded() method");
		byte[] decoded = DatatypeConverter.parseBase64Binary(encoded);
		String value = new String(decoded, Charset.forName("UTF-8"));
		return value;
	}

	public Map<String, Object> getKeyValues(String[] keys)
	{
		logger.debug("Start getKeyValues() method");
		Map<String, Object> result = new Hashtable<String, Object>();
		Object value;
		for (String key : keys) {
			value = getValue(key);
			if (value != null) {
				result.put(key, value);
			}
		}
		return result;
	}

	/**
	 * @see #getTypedValue(ExtensibleAttribute ea)
	 */
	@Deprecated
	public Object getTypedValue(String key)
	{
		logger.debug("Start getTypedValue() method");
		ExtensibleAttribute ea = getAttribute(key);
		if (ea == null) {
			return null;
		} else {
			Object value = getTypedValue(ea);
			return value;
		}
	}

	public String getStringValue(String key)
	{
		logger.debug("Start getStringValue() method");
		ExtensibleAttribute ea = getAttribute(key);
		if (ea == null) {
			return null;
		}
		String value = getBase64Decoded(ea.getValue());
		return value;
	}

	public Object getValue(String key)
	{
		logger.debug("Start getValue() method");
		ExtensibleAttribute ea = getAttribute(key);
		// According to the wsdl, value should be a literal string and
		// valueAsByteArray should be encoded by base64. Now in the message,
		// valueAsByteArray is absent and value or valueList is present and encoded.
		// <xs:element minOccurs="0" name="value" type="xs:string" /> <xs:element
		// minOccurs="0" name="valueAsByteArray" type="xs:base64Binary" />
		if (ea == null) {
			return null;
		}
		if (ea.isMultiValued()) {
			Vector<String> value = new Vector<String>();
			for (String v : ea.getValueList()) {
				value.add(getBase64Decoded(v));
			}
			return value;
		} else {
			String value = getBase64Decoded(ea.getValue());
			return value;
		}

	}

	public List<String> keys()
	{
		logger.debug("Start keys() method");
		ArrayList<String> result = new ArrayList<String>();
		for (ExtensibleAttribute ea : attributes) {
			result.add(ea.getName());
		}
		return result;
	}

	
	/*************************************************************
     ***********           MÉTODOS PRIVADOS            *********** 
     *************************************************************/
	
	private ExtensibleAttribute getAttribute(String key)
	{
		logger.debug("Start getAttribute() method");
		for (ExtensibleAttribute ea : attributes) {
			// Attributes for different systems are usually distinguished by a prefix
			// like dom-isEnabled for Domino.
			if (ea.getName().equalsIgnoreCase(key)) {
				return ea;
			}
		}
		return null;
	}

	/**
	 * The typed values were put into a map which was used to create a user. The
	 * deprecation is due to the following reasons. 1. Most user property values are
	 * strings and need no type conversion. 2. ExtensibleAttribute.getDataType() has
	 * no boolean type, so boolean values cannot be converted here. 3. Converted
	 * values are still put into a Map<String, Object>, so when they are retrieved
	 * the type information are lost. 4. When a user property value of specified type
	 * is required, a getter for that property is created in the user class.
	 */
	@Deprecated
	private Object getTypedValue(ExtensibleAttribute ea)
	{
		logger.debug("Start getTypedValue() method");
		String value = getBase64Decoded(ea.getValue());
		// logger.debug(value);
		String type = ea.getDataType();
		// Soap messages from the testing web service kind don't contain this value.
		type = (type == null) ? "" : type;
		Object typedValue;
		switch (type) {
		// case "memberOf":
		// continue;
		case "int":
			typedValue = Integer.parseInt(value);
			break;
		default:
			typedValue = value;
			break;
		}
		return typedValue;
	}
}
