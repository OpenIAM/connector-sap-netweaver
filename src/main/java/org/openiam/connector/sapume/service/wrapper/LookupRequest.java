
package org.openiam.connector.sapume.service.wrapper;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.openiam.connector.sapume.service.types.ExtensibleAttribute;
import org.openiam.connector.sapume.service.wrapper.SearchRequest;


/**
 * <p>Java class for LookupRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LookupRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.openiam.org/service/connector}SearchRequest">
 *       &lt;sequence>
 *         &lt;element name="requestedAttributes" type="{http://www.openiam.org/service/types}ExtensibleAttribute" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LookupRequest", propOrder = {
    "requestedAttributes"
})
public class LookupRequest
    extends SearchRequest
{

    @XmlElement(nillable = true)
    protected List<ExtensibleAttribute> requestedAttributes;

    /**
     * Gets the value of the requestedAttributes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the requestedAttributes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRequestedAttributes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExtensibleAttribute }
     * 
     * 
     */
    public List<ExtensibleAttribute> getRequestedAttributes() {
        if (requestedAttributes == null) {
            requestedAttributes = new ArrayList<ExtensibleAttribute>();
        }
        return this.requestedAttributes;
    }

}
