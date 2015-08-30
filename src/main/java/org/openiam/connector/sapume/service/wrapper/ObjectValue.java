
package org.openiam.connector.sapume.service.wrapper;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.openiam.connector.sapume.service.types.ExtensibleAttribute;


/**
 * <p>Java class for ObjectValue complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ObjectValue">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="objectIdentity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attributeList" type="{http://www.openiam.org/service/types}ExtensibleAttribute" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObjectValue", propOrder = {
    "objectIdentity",
    "attributeList"
})
public class ObjectValue {

    protected String objectIdentity;
    @XmlElement(nillable = true)
    protected List<ExtensibleAttribute> attributeList;

    /**
     * Gets the value of the objectIdentity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectIdentity() {
        return objectIdentity;
    }

    /**
     * Sets the value of the objectIdentity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectIdentity(String value) {
        this.objectIdentity = value;
    }

    /**
     * Gets the value of the attributeList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attributeList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttributeList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExtensibleAttribute }
     * 
     * 
     */
    public List<ExtensibleAttribute> getAttributeList() {
        if (attributeList == null) {
            attributeList = new ArrayList<ExtensibleAttribute>();
        }
        return this.attributeList;
    }

}
