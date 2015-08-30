package org.openiam.connector.sapume.response;

import java.nio.charset.Charset;
import java.util.*;

import javax.xml.bind.DatatypeConverter;

import org.openiam.connector.sapume.service.types.ExtensibleAttribute;
import org.openiam.connector.sapume.service.types.ExtensibleObject;
import org.openiam.connector.sapume.service.wrapper.BaseRequestType;


public class AttributeWriter
{
	private BaseRequestType request;
	private List<ExtensibleAttribute> attributeList;

	public AttributeWriter(BaseRequestType request) {
		this.request = request;
		if (this.request.getExtensibleObject() == null) {
			this.request.setExtensibleObject(new ExtensibleObject());
		}
		attributeList = this.request.getExtensibleObject().getAttributes();
	}

	public AttributeWriter(List<ExtensibleAttribute> attributeList) {
		this.attributeList = attributeList;
	}

	public void setValue(String name, Object value) {
		ExtensibleAttribute attribute = new ExtensibleAttribute();
		attribute.setName(name);
		if (value instanceof List) {
			attribute.setMultivalued(true);
			for (String v : (List<String>) value) {
				attribute.getValueList().add(v);
			}
		} else {
			attribute.setValue(value.toString());
		}
		attributeList.add(attribute);
	}

	/**
	 * Add an attribute whose value is encoded in Base64.
	 * 
	 * @param name
	 * @param value
	 */
	public void setEncodedValue(String name, Object value) {
		if (value instanceof List) {
			List valueList=(List)value;
			if (valueList.size()==1){
				setEncodedValue(name,valueList.get(0));
				return;
			}
			
			List<String> encoded = new ArrayList<String>();
			byte[] raw;
			for (Object v : valueList) {
				raw = v.toString().getBytes(Charset.forName("UTF-8"));
				encoded.add(DatatypeConverter.printBase64Binary(raw));
			}
			setValue(name, encoded);
		} else {
			byte[] raw = value.toString().getBytes(Charset.forName("UTF-8"));
			String encoded = DatatypeConverter.printBase64Binary(raw);
			setValue(name, encoded);
		}
	
	}

	public void add(ExtensibleAttribute ea) {
		attributeList.add(ea);
	}
}
