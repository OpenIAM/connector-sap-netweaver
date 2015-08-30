
package org.openiam.connector.sapume.service.wrapper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.openiam.connector.sapume.service.wrapper.LookupRequest;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="reqType" type="{http://www.openiam.org/service/connector}LookupRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "reqType"
})
@XmlRootElement(name = "lookupAttributeNames")
public class LookupAttributeNames {

    protected LookupRequest reqType;

    /**
     * Gets the value of the reqType property.
     * 
     * @return
     *     possible object is
     *     {@link LookupRequest }
     *     
     */
    public LookupRequest getReqType() {
        return reqType;
    }

    /**
     * Sets the value of the reqType property.
     * 
     * @param value
     *     allowed object is
     *     {@link LookupRequest }
     *     
     */
    public void setReqType(LookupRequest value) {
        this.reqType = value;
    }

}
