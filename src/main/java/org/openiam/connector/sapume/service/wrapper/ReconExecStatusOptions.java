
package org.openiam.connector.sapume.service.wrapper;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

import org.openiam.connector.sapume.service.wrapper.ReconExecStatusOptions;


/**
 * <p>Java class for ReconExecStatusOptions.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ReconExecStatusOptions">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="started"/>
 *     &lt;enumeration value="starting"/>
 *     &lt;enumeration value="stopped"/>
 *     &lt;enumeration value="stopping"/>
 *     &lt;enumeration value="finished"/>
 *     &lt;enumeration value="failed"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ReconExecStatusOptions")
@XmlEnum
public enum ReconExecStatusOptions {

    @XmlEnumValue("started")
    STARTED("started"),
    @XmlEnumValue("starting")
    STARTING("starting"),
    @XmlEnumValue("stopped")
    STOPPED("stopped"),
    @XmlEnumValue("stopping")
    STOPPING("stopping"),
    @XmlEnumValue("finished")
    FINISHED("finished"),
    @XmlEnumValue("failed")
    FAILED("failed");
    private final String value;

    ReconExecStatusOptions(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ReconExecStatusOptions fromValue(String v) {
        for (ReconExecStatusOptions c: ReconExecStatusOptions.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
