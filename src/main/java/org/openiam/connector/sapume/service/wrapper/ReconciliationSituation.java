
package org.openiam.connector.sapume.service.wrapper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReconciliationSituation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReconciliationSituation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="reconSituationId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reconConfigId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="situation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="situationResp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="script" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReconciliationSituation", propOrder = {
    "reconSituationId",
    "reconConfigId",
    "situation",
    "situationResp",
    "script"
})
public class ReconciliationSituation {

    protected String reconSituationId;
    protected String reconConfigId;
    protected String situation;
    protected String situationResp;
    protected String script;

    /**
     * Gets the value of the reconSituationId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReconSituationId() {
        return reconSituationId;
    }

    /**
     * Sets the value of the reconSituationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReconSituationId(String value) {
        this.reconSituationId = value;
    }

    /**
     * Gets the value of the reconConfigId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReconConfigId() {
        return reconConfigId;
    }

    /**
     * Sets the value of the reconConfigId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReconConfigId(String value) {
        this.reconConfigId = value;
    }

    /**
     * Gets the value of the situation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSituation() {
        return situation;
    }

    /**
     * Sets the value of the situation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSituation(String value) {
        this.situation = value;
    }

    /**
     * Gets the value of the situationResp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSituationResp() {
        return situationResp;
    }

    /**
     * Sets the value of the situationResp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSituationResp(String value) {
        this.situationResp = value;
    }

    /**
     * Gets the value of the script property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScript() {
        return script;
    }

    /**
     * Sets the value of the script property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScript(String value) {
        this.script = value;
    }

}
