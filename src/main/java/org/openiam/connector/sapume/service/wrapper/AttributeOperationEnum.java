
package org.openiam.connector.sapume.service.wrapper;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

import org.openiam.connector.sapume.service.wrapper.AttributeOperationEnum;


/**
 * <p>Java class for AttributeOperationEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AttributeOperationEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="nochange"/>
 *     &lt;enumeration value="add"/>
 *     &lt;enumeration value="replace"/>
 *     &lt;enumeration value="delete"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AttributeOperationEnum")
@XmlEnum
public enum AttributeOperationEnum {

    @XmlEnumValue("nochange")
    NOCHANGE("nochange"),
    @XmlEnumValue("add")
    ADD("add"),
    @XmlEnumValue("replace")
    REPLACE("replace"),
    @XmlEnumValue("delete")
    DELETE("delete");
    private final String value;

    AttributeOperationEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AttributeOperationEnum fromValue(String v) {
        for (AttributeOperationEnum c: AttributeOperationEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
