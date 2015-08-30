package org.openiam.connector.sapume.request;

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RequiredAttributeValidator extends AValidator
{
	private static Log logger = LogFactory.getLog(RequiredAttributeValidator.class);
	
	private AttributeReader attributeReader;
	
	public RequiredAttributeValidator(AttributeReader attributeReader)
	{
		logger.debug("RequiredAttributeValidator constructor");
		this.attributeReader=attributeReader;
	}
	
	@Override
	protected boolean validateField()
	{
		logger.debug("Start validateField() method");
		Object value=attributeReader.getValue(currentField);
		if (value instanceof String){
			if (value==null || value.equals("")){
				return false;
			}else{
				return true;
			}			
		}else{
			Vector<String> values=(Vector<String>) value;
			if (values==null || values.size()==0 || values.get(0).equals("")){
				return false;  
			}else{
				return true;
			}
		}
	}

}
