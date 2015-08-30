
package org.openiam.connector.sapume.service.wrapper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.openiam.connector.sapume.service.wrapper.BaseRequestType;


/**
 * <p>Java class for CrudRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CrudRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.openiam.org/service/connector}BaseRequestType">
 *       &lt;sequence>
 *         &lt;element name="objectIdentityAttributeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrudRequest", propOrder = {
    "objectIdentityAttributeName"
})
public class CrudRequest
    extends BaseRequestType
{

    protected String objectIdentityAttributeName;

    /**
     * Gets the value of the objectIdentityAttributeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectIdentityAttributeName() {
        return objectIdentityAttributeName;
    }

    /**
     * Sets the value of the objectIdentityAttributeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectIdentityAttributeName(String value) {
        this.objectIdentityAttributeName = value;
    }

}
