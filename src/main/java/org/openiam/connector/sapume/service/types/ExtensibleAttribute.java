
package org.openiam.connector.sapume.service.types;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.openiam.connector.sapume.service.wrapper.Attribute;
import org.openiam.connector.sapume.service.wrapper.BaseAttributeContainer;


/**
 * <p>
 * Java class for ExtensibleAttribute complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="ExtensibleAttribute">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.openiam.org/service/connector}attribute">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valueAsByteArray" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="metadataElementId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operation" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="multivalued" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="valueList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="dataType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="objectType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attributeContainer" type="{http://www.openiam.org/service/connector}BaseAttributeContainer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtensibleAttribute", propOrder = { 
		"name",
		"value",
		"valueAsByteArray",
		"metadataElementId",
		"operation",
		"multivalued",
		"valueList",
		"dataType",
		"objectType",
		"attributeContainer"
 })
public class ExtensibleAttribute extends Attribute {
	protected String name;
	protected String value;
	protected byte[] valueAsByteArray;
	protected String metadataElementId;
	protected int operation;
	protected boolean multivalued;
	@XmlElement(nillable = true)    
	protected List<String> valueList;
	protected String dataType;
	protected String objectType;
	protected BaseAttributeContainer attributeContainer;
	
	public ExtensibleAttribute(){}
	
	public ExtensibleAttribute(String name,String value){
		this.name=name;
		this.value=value;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the valueAsByteArray
	 */
	public byte[] getValueAsByteArray() {
		return valueAsByteArray;
	}
	/**
	 * @param valueAsByteArray the valueAsByteArray to set
	 */
	public void setValueAsByteArray(byte[] valueAsByteArray) {
		this.valueAsByteArray = valueAsByteArray;
	}
	/**
	 * @return the metadataElementId
	 */
	public String getMetadataElementId() {
		return metadataElementId;
	}
	/**
	 * @param metadataElementId the metadataElementId to set
	 */
	public void setMetadataElementId(String metadataElementId) {
		this.metadataElementId = metadataElementId;
	}
	/**
	 * @return the operation
	 */
	public int getOperation() {
		return operation;
	}
	/**
	 * @param operation the operation to set
	 */
	public void setOperation(int operation) {
		this.operation = operation;
	}
	/**
	 * @return the multivalued
	 */
	public boolean isMultivalued() {
		return multivalued;
	}
	/**
	 * @param multivalued the multivalued to set
	 */
	public void setMultivalued(boolean multivalued) {
		this.multivalued = multiValued;
	}
	/**
	 * @return the dataType
	 */
	public String getDataType() {
		return dataType;
	}
	/**
	 * @param dataType the dataType to set
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	/**
	 * @return the objectType
	 */
	public String getObjectType() {
		return objectType;
	}
	/**
	 * @param objectType the objectType to set
	 */
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	/**
	 * @return the attributeContainer
	 */
	public BaseAttributeContainer getAttributeContainer() {
		return attributeContainer;
	}
	/**
	 * @param attributeContainer the attributeContainer to set
	 */
	public void setAttributeContainer(BaseAttributeContainer attributeContainer) {
		this.attributeContainer = attributeContainer;
	}
    /**
     * Gets the value of the valueList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the valueList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValueList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getValueList() {
        if (valueList == null) {
            valueList = new ArrayList<String>();
        }
        return this.valueList;
    }
}