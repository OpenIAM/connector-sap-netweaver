
package org.openiam.connector.sapume.service.types;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProvisionObjectType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ProvisionObjectType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ROLE"/>
 *     &lt;enumeration value="USER"/>
 *     &lt;enumeration value="ADDRESS"/>
 *     &lt;enumeration value="EMAIL"/>
 *     &lt;enumeration value="PHONE"/>
 *     &lt;enumeration value="GROUP"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ProvisionObjectType")
@XmlEnum
public enum ProvisionObjectType {

    ROLE,
    USER,
    ADDRESS,
    EMAIL,
    PHONE,
    GROUP;

    public String value() {
        return name();
    }

    public static ProvisionObjectType fromValue(String v) {
        return valueOf(v);
    }

}
