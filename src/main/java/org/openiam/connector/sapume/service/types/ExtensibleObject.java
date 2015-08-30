
package org.openiam.connector.sapume.service.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ExtensibleObject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExtensibleObject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="objectId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operation" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="attributes" type="{http://www.openiam.org/service/types}ExtensibleAttribute" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="principalFieldName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="principalFieldDataType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="extensibleObjectType" type="{http://www.openiam.org/service/types}ProvisionObjectType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtensibleObject", propOrder = {
    "objectId",
    "name",
    "operation",
    "attributes",
    "principalFieldName",
    "principalFieldDataType",
    "extensibleObjectType"
})
public class ExtensibleObject {

    protected String objectId;
    protected String name;
    protected int operation;
    @XmlElement(nillable = true)
    protected List<ExtensibleAttribute> attributes;
    protected String principalFieldName;
    protected String principalFieldDataType;
    protected ProvisionObjectType extensibleObjectType;

    /**
     * Gets the value of the objectId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * Sets the value of the objectId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectId(String value) {
        this.objectId = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the operation property.
     * 
     */
    public int getOperation() {
        return operation;
    }

    /**
     * Sets the value of the operation property.
     * 
     */
    public void setOperation(int value) {
        this.operation = value;
    }

    /**
     * Gets the value of the attributes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attributes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttributes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExtensibleAttribute }
     * 
     * 
     */
    public List<ExtensibleAttribute> getAttributes() {
        if (attributes == null) {
            attributes = new ArrayList<ExtensibleAttribute>();
        }
        return this.attributes;
    }

    /**
     * Gets the value of the principalFieldName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrincipalFieldName() {
        return principalFieldName;
    }

    /**
     * Sets the value of the principalFieldName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrincipalFieldName(String value) {
        this.principalFieldName = value;
    }

    /**
     * Gets the value of the principalFieldDataType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrincipalFieldDataType() {
        return principalFieldDataType;
    }

    /**
     * Sets the value of the principalFieldDataType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrincipalFieldDataType(String value) {
        this.principalFieldDataType = value;
    }

    /**
     * Gets the value of the extensibleObjectType property.
     * 
     * @return
     *     possible object is
     *     {@link ProvisionObjectType }
     *     
     */
    public ProvisionObjectType getExtensibleObjectType() {
        return extensibleObjectType;
    }

    /**
     * Sets the value of the extensibleObjectType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProvisionObjectType }
     *     
     */
    public void setExtensibleObjectType(ProvisionObjectType value) {
        this.extensibleObjectType = value;
    }

}
