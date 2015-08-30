
package org.openiam.connector.sapume.service.wrapper;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import org.openiam.connector.sapume.service.wrapper.ReconExecStatusOptions;
import org.openiam.connector.sapume.service.wrapper.ReconciliationSituation;


/**
 * <p>Java class for ReconciliationConfig complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReconciliationConfig">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="reconConfigId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resourceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="managedSysId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="frequency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="situationSet" type="{http://www.openiam.org/service/connector}ReconciliationSituation" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="reportPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="separator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endOfLine" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="notificationEmailAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="manualReconciliationFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="targetSystemMatchScript" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="targetSystemSearchFilter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="matchScript" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="searchFilter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="updatedSince" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="customIdentityMatchScript" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="scriptHandler" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="matchFieldName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customMatchAttr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="matchSrcFieldName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastExecTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="execStatus" type="{http://www.openiam.org/service/connector}ReconExecStatusOptions" minOccurs="0"/>
 *         &lt;element name="requesterId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReconciliationConfig", propOrder = {
    "reconConfigId",
    "resourceId",
    "managedSysId",
    "frequency",
    "status",
    "situationSet",
    "reportPath",
    "separator",
    "endOfLine",
    "notificationEmailAddress",
    "manualReconciliationFlag",
    "targetSystemMatchScript",
    "targetSystemSearchFilter",
    "matchScript",
    "searchFilter",
    "updatedSince",
    "customIdentityMatchScript",
    "scriptHandler",
    "matchFieldName",
    "customMatchAttr",
    "matchSrcFieldName",
    "lastExecTime",
    "execStatus",
    "requesterId"
})
public class ReconciliationConfig {

    protected String reconConfigId;
    protected String resourceId;
    protected String managedSysId;
    protected String frequency;
    protected String status;
    @XmlElement(nillable = true)
    protected List<ReconciliationSituation> situationSet;
    protected String reportPath;
    protected String separator;
    protected String endOfLine;
    protected String notificationEmailAddress;
    protected boolean manualReconciliationFlag;
    protected String targetSystemMatchScript;
    protected String targetSystemSearchFilter;
    protected String matchScript;
    protected String searchFilter;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updatedSince;
    protected String customIdentityMatchScript;
    protected String scriptHandler;
    protected String matchFieldName;
    protected String customMatchAttr;
    protected String matchSrcFieldName;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastExecTime;
    protected ReconExecStatusOptions execStatus;
    protected String requesterId;

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
     * Gets the value of the resourceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResourceId() {
        return resourceId;
    }

    /**
     * Sets the value of the resourceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResourceId(String value) {
        this.resourceId = value;
    }

    /**
     * Gets the value of the managedSysId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManagedSysId() {
        return managedSysId;
    }

    /**
     * Sets the value of the managedSysId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManagedSysId(String value) {
        this.managedSysId = value;
    }

    /**
     * Gets the value of the frequency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrequency() {
        return frequency;
    }

    /**
     * Sets the value of the frequency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrequency(String value) {
        this.frequency = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the situationSet property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the situationSet property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSituationSet().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReconciliationSituation }
     * 
     * 
     */
    public List<ReconciliationSituation> getSituationSet() {
        if (situationSet == null) {
            situationSet = new ArrayList<ReconciliationSituation>();
        }
        return this.situationSet;
    }

    /**
     * Gets the value of the reportPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReportPath() {
        return reportPath;
    }

    /**
     * Sets the value of the reportPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReportPath(String value) {
        this.reportPath = value;
    }

    /**
     * Gets the value of the separator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeparator() {
        return separator;
    }

    /**
     * Sets the value of the separator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeparator(String value) {
        this.separator = value;
    }

    /**
     * Gets the value of the endOfLine property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndOfLine() {
        return endOfLine;
    }

    /**
     * Sets the value of the endOfLine property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndOfLine(String value) {
        this.endOfLine = value;
    }

    /**
     * Gets the value of the notificationEmailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotificationEmailAddress() {
        return notificationEmailAddress;
    }

    /**
     * Sets the value of the notificationEmailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotificationEmailAddress(String value) {
        this.notificationEmailAddress = value;
    }

    /**
     * Gets the value of the manualReconciliationFlag property.
     * 
     */
    public boolean isManualReconciliationFlag() {
        return manualReconciliationFlag;
    }

    /**
     * Sets the value of the manualReconciliationFlag property.
     * 
     */
    public void setManualReconciliationFlag(boolean value) {
        this.manualReconciliationFlag = value;
    }

    /**
     * Gets the value of the targetSystemMatchScript property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetSystemMatchScript() {
        return targetSystemMatchScript;
    }

    /**
     * Sets the value of the targetSystemMatchScript property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetSystemMatchScript(String value) {
        this.targetSystemMatchScript = value;
    }

    /**
     * Gets the value of the targetSystemSearchFilter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetSystemSearchFilter() {
        return targetSystemSearchFilter;
    }

    /**
     * Sets the value of the targetSystemSearchFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetSystemSearchFilter(String value) {
        this.targetSystemSearchFilter = value;
    }

    /**
     * Gets the value of the matchScript property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatchScript() {
        return matchScript;
    }

    /**
     * Sets the value of the matchScript property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatchScript(String value) {
        this.matchScript = value;
    }

    /**
     * Gets the value of the searchFilter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearchFilter() {
        return searchFilter;
    }

    /**
     * Sets the value of the searchFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearchFilter(String value) {
        this.searchFilter = value;
    }

    /**
     * Gets the value of the updatedSince property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdatedSince() {
        return updatedSince;
    }

    /**
     * Sets the value of the updatedSince property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdatedSince(XMLGregorianCalendar value) {
        this.updatedSince = value;
    }

    /**
     * Gets the value of the customIdentityMatchScript property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomIdentityMatchScript() {
        return customIdentityMatchScript;
    }

    /**
     * Sets the value of the customIdentityMatchScript property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomIdentityMatchScript(String value) {
        this.customIdentityMatchScript = value;
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
     * Gets the value of the matchFieldName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatchFieldName() {
        return matchFieldName;
    }

    /**
     * Sets the value of the matchFieldName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatchFieldName(String value) {
        this.matchFieldName = value;
    }

    /**
     * Gets the value of the customMatchAttr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomMatchAttr() {
        return customMatchAttr;
    }

    /**
     * Sets the value of the customMatchAttr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomMatchAttr(String value) {
        this.customMatchAttr = value;
    }

    /**
     * Gets the value of the matchSrcFieldName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatchSrcFieldName() {
        return matchSrcFieldName;
    }

    /**
     * Sets the value of the matchSrcFieldName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatchSrcFieldName(String value) {
        this.matchSrcFieldName = value;
    }

    /**
     * Gets the value of the lastExecTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastExecTime() {
        return lastExecTime;
    }

    /**
     * Sets the value of the lastExecTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastExecTime(XMLGregorianCalendar value) {
        this.lastExecTime = value;
    }

    /**
     * Gets the value of the execStatus property.
     * 
     * @return
     *     possible object is
     *     {@link ReconExecStatusOptions }
     *     
     */
    public ReconExecStatusOptions getExecStatus() {
        return execStatus;
    }

    /**
     * Sets the value of the execStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReconExecStatusOptions }
     *     
     */
    public void setExecStatus(ReconExecStatusOptions value) {
        this.execStatus = value;
    }

    /**
     * Gets the value of the requesterId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequesterId() {
        return requesterId;
    }

    /**
     * Sets the value of the requesterId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequesterId(String value) {
        this.requesterId = value;
    }

}
