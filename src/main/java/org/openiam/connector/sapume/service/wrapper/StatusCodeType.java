
package org.openiam.connector.sapume.service.wrapper;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

import org.openiam.connector.sapume.service.wrapper.StatusCodeType;


/**
 * <p>Java class for StatusCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="StatusCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="success"/>
 *     &lt;enumeration value="failure"/>
 *     &lt;enumeration value="successMoreResultsToReturn"/>
 *     &lt;enumeration value="pending"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "StatusCodeType")
@XmlEnum
public enum StatusCodeType {

    @XmlEnumValue("success")
    SUCCESS("success"),
    @XmlEnumValue("failure")
    FAILURE("failure"),
    @XmlEnumValue("successMoreResultsToReturn")
    SUCCESS_MORE_RESULTS_TO_RETURN("successMoreResultsToReturn"),
    @XmlEnumValue("pending")
    PENDING("pending");
    private final String value;

    StatusCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static StatusCodeType fromValue(String v) {
        for (StatusCodeType c: StatusCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
