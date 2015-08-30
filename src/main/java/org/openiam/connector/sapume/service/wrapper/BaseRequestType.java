
package org.openiam.connector.sapume.service.wrapper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import org.openiam.connector.sapume.service.types.ExtensibleObject;
import org.openiam.connector.sapume.service.wrapper.CrudRequest;
import org.openiam.connector.sapume.service.wrapper.PasswordRequest;
import org.openiam.connector.sapume.service.wrapper.SearchRequest;
import org.openiam.connector.sapume.service.wrapper.SuspendResumeRequest;


/**
 * <p>Java class for BaseRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BaseRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="requestID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="executionMode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="targetID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hostUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hostPort" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hostLoginId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hostLoginPassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="baseDN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="containerID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="scriptHandler" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="extensibleObject" type="{http://www.openiam.org/service/types}ExtensibleObject" minOccurs="0"/>
 *         &lt;element name="objectIdentity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BaseRequestType", propOrder = {
    "requestID",
    "executionMode",
    "targetID",
    "hostUrl",
    "hostPort",
    "hostLoginId",
    "hostLoginPassword",
    "baseDN",
    "containerID",
    "scriptHandler",
    "operation",
    "extensibleObject",
    "objectIdentity"
})
@XmlSeeAlso({
    SuspendResumeRequest.class,
    PasswordRequest.class,
    CrudRequest.class,
    SearchRequest.class
})
public class BaseRequestType {

    @XmlElement(required = true)
    protected String requestID;
    protected String executionMode;
    protected String targetID;
    protected String hostUrl;
    protected String hostPort;
    protected String hostLoginId;
    protected String hostLoginPassword;
    protected String baseDN;
    protected String containerID;
    protected String scriptHandler;
    protected String operation;
    protected ExtensibleObject extensibleObject;
    @XmlElement(required = true)
    protected String objectIdentity;

    /**
     * Gets the value of the requestID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestID() {
        return requestID;
    }

    /**
     * Sets the value of the requestID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestID(String value) {
        this.requestID = value;
    }

    /**
     * Gets the value of the executionMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExecutionMode() {
        return executionMode;
    }

    /**
     * Sets the value of the executionMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExecutionMode(String value) {
        this.executionMode = value;
    }

    /**
     * Gets the value of the targetID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetID() {
        return targetID;
    }

    /**
     * Sets the value of the targetID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetID(String value) {
        this.targetID = value;
    }

    /**
     * Gets the value of the hostUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHostUrl() {
        return hostUrl;
    }

    /**
     * Sets the value of the hostUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHostUrl(String value) {
        this.hostUrl = value;
    }

    /**
     * Gets the value of the hostPort property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHostPort() {
        return hostPort;
    }

    /**
     * Sets the value of the hostPort property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHostPort(String value) {
        this.hostPort = value;
    }

    /**
     * Gets the value of the hostLoginId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHostLoginId() {
        return hostLoginId;
    }

    /**
     * Sets the value of the hostLoginId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHostLoginId(String value) {
        this.hostLoginId = value;
    }

    /**
     * Gets the value of the hostLoginPassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHostLoginPassword() {
        return hostLoginPassword;
    }

    /**
     * Sets the value of the hostLoginPassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHostLoginPassword(String value) {
        this.hostLoginPassword = value;
    }

    /**
     * Gets the value of the baseDN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaseDN() {
        return baseDN;
    }

    /**
     * Sets the value of the baseDN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaseDN(String value) {
        this.baseDN = value;
    }

    /**
     * Gets the value of the containerID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContainerID() {
        return containerID;
    }

    /**
     * Sets the value of the containerID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContainerID(String value) {
        this.containerID = value;
    }

    /**
     * Gets the value of the scriptHandler property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScriptHandler() {
        return scriptHandler;
    }

    /**
     * Sets the value of the scriptHandler property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScriptHandler(String value) {
        this.scriptHandler = value;
    }

    /**
     * Gets the value of the operation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperation() {
        return operation;
    }

    /**
     * Sets the value of the operation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperation(String value) {
        this.operation = value;
    }

    /**
     * Gets the value of the extensibleObject property.
     * 
     * @return
     *     possible object is
     *     {@link ExtensibleObject }
     *     
     */
    public ExtensibleObject getExtensibleObject() {
        return extensibleObject;
    }

    /**
     * Sets the value of the extensibleObject property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtensibleObject }
     *     
     */
    public void setExtensibleObject(ExtensibleObject value) {
        this.extensibleObject = value;
    }

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

}
