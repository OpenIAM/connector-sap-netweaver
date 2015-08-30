
package org.openiam.connector.sapume.service.wrapper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.openiam.connector.sapume.service.wrapper.ReconciliationConfig;


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
 *         &lt;element name="config" type="{http://www.openiam.org/service/connector}ReconciliationConfig" minOccurs="0"/>
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
    "config"
})
@XmlRootElement(name = "reconcileResource")
public class ReconcileResource {

    protected ReconciliationConfig config;

    /**
     * Gets the value of the config property.
     * 
     * @return
     *     possible object is
     *     {@link ReconciliationConfig }
     *     
     */
    public ReconciliationConfig getConfig() {
        return config;
    }

    /**
     * Sets the value of the config property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReconciliationConfig }
     *     
     */
    public void setConfig(ReconciliationConfig value) {
        this.config = value;
    }

}
