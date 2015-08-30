
package org.openiam.connector.sapume.service.wrapper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.openiam.connector.sapume.service.wrapper.BaseResponseType;
import org.openiam.connector.sapume.service.wrapper.ObjectValue;


/**
 * <p>Java class for ObjectResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ObjectResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.openiam.org/service/connector}BaseResponseType">
 *       &lt;sequence>
 *         &lt;element name="objectValue" type="{http://www.openiam.org/service/connector}ObjectValue" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObjectResponse", propOrder = {
    "objectValue"
})
public class ObjectResponse
    extends BaseResponseType
{

    protected ObjectValue objectValue;

    /**
     * Gets the value of the objectValue property.
     * 
     * @return
     *     possible object is
     *     {@link ObjectValue }
     *     
     */
    public ObjectValue getObjectValue() {
        return objectValue;
    }

    /**
     * Sets the value of the objectValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectValue }
     *     
     */
    public void setObjectValue(ObjectValue value) {
        this.objectValue = value;
    }

}
