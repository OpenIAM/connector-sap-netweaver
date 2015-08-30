package org.openiam.connector.sapume.request;

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AValidator
{
	private static Log logger = LogFactory.getLog(AValidator.class);
	
	Hashtable<String, String> fields = new Hashtable<String, String>();
	protected String currentField = "";

	protected List<String> invalidFields = new ArrayList<String>();
	protected String invalidField = "";


	public AValidator()
	{
		
	}

	/**
	 * @param fieldName
	 * @param label
	 *            Add a field to be checked.
	 */
	public void add(String fieldName, String label) {
		fields.put(fieldName, label);
	}

	/**
	 * @param fieldName
	 *            Add a field which has the same label with the name.
	 */
	public void add(String fieldName) {
		add(fieldName, fieldName);
	}

	public void add(String[] fieldNames) {
		for (String field : fieldNames) {
			add(field, field);
		}
	}

	protected abstract boolean validateField();

	/**
	 * Return false on the first empty field.
	 */
	public boolean validate()
	{
		logger.debug("Start validate() method");
		
		// Reset the results
		invalidField = "";

		Enumeration<String> keys = fields.keys();
		while (keys.hasMoreElements()) {
			currentField = keys.nextElement();
			if (!validateField()) {
				invalidField = currentField;
				return false;
			}
		}
		return true;
	}

	/**
	 * Validate all the fields. Return false if any field is empty.
	 */
	public boolean validateAll()
	{
		logger.debug("Start validateAll() method");
		
		// Reset the results
		boolean result = true;
		invalidFields.clear();

		Enumeration<String> keys = fields.keys();
		while (keys.hasMoreElements()) {
			currentField = keys.nextElement();
			if (!validateField()) {
				invalidFields.add(currentField);
				result = false;
			}
		}
		return result;
	}

	public void validateAllWithException() throws ValidationException
	{
		logger.debug("Start validateAllWithException() method");
		if (!validateAll()) {
			throw new ValidationException("The following fields are required for SAP user: ", invalidFields);
		}
	}
}
