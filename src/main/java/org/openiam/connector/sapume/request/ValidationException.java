package org.openiam.connector.sapume.request;

import java.util.*;

public class ValidationException extends Exception {
	public static final String REQUIRED = "REQUIRED";
	public static final String DUPLICATED = "DUPLICATED";
	private static final long serialVersionUID = -357366671084738405L;
	private List<String> invalidFields;

	public ValidationException(String message,List<String> fields) {
		super(message);
		this.invalidFields= fields;
	}

	public ValidationException(String message,String field) {
		super(message);
		this.invalidFields= new ArrayList<String>();
		invalidFields.add(field);
	}
	
	public List<String> getInvalidFields() {
		return invalidFields;
	}

	public String toString() {
		return getMessage() + invalidFields;
	}
}
