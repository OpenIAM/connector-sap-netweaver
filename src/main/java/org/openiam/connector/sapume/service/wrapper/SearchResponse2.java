
package org.openiam.connector.sapume.service.wrapper;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.openiam.connector.sapume.service.wrapper.BaseResponseType;
import org.openiam.connector.sapume.service.wrapper.ObjectValue;


/**
 * <p>Java class for SearchResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.openiam.org/service/connector}BaseResponseType">
 *       &lt;sequence>
 *         &lt;element name="objectList" type="{http://www.openiam.org/service/connector}ObjectValue" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchResponse", propOrder = {
    "objectList"
})
public class SearchResponse2
    extends BaseResponseType
{

    @XmlElement(nillable = true)
    protected List<ObjectValue> objectList;

    /**
     * Gets the value of the objectList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the objectList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getObjectList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ObjectValue }
     * 
     * 
     */
    public List<ObjectValue> getObjectList() {
        if (objectList == null) {
            objectList = new ArrayList<ObjectValue>();
        }
        return this.objectList;
    }

}
