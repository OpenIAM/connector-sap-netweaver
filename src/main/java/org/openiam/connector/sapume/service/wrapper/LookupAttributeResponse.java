
package org.openiam.connector.sapume.service.wrapper;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.openiam.connector.sapume.service.types.ExtensibleAttribute;
import org.openiam.connector.sapume.service.wrapper.BaseResponseType;


/**
 * <p>Java class for LookupAttributeResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LookupAttributeResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.openiam.org/service/connector}BaseResponseType">
 *       &lt;sequence>
 *         &lt;element name="attributes" type="{http://www.openiam.org/service/types}ExtensibleAttribute" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LookupAttributeResponse", propOrder = {
    "attributes"
})
public class LookupAttributeResponse
    extends BaseResponseType
{

    @XmlElement(nillable = true)
    protected List<ExtensibleAttribute> attributes;

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

    public void setAttributes(List<ExtensibleAttribute> attributes) {
        this.attributes = attributes;
    }
    
}
