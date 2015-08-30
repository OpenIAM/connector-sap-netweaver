
package org.openiam.connector.sapume.service.wrapper;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

import org.openiam.connector.sapume.service.wrapper.ErrorCode;


/**
 * <p>Java class for ErrorCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ErrorCode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="persistException"/>
 *     &lt;enumeration value="malformedRequest"/>
 *     &lt;enumeration value="unsupportedOperation"/>
 *     &lt;enumeration value="unsupportedIdentifierType"/>
 *     &lt;enumeration value="noSuchIdentifier"/>
 *     &lt;enumeration value="customError"/>
 *     &lt;enumeration value="otherError"/>
 *     &lt;enumeration value="directoryError"/>
 *     &lt;enumeration value="namingException"/>
 *     &lt;enumeration value="operationNotSupportedException"/>
 *     &lt;enumeration value="sqlError"/>
 *     &lt;enumeration value="connectorError"/>
 *     &lt;enumeration value="invalidConfiguration"/>
 *     &lt;enumeration value="invalidAttribute"/>
 *     &lt;enumeration value="unsupportedExecutionMode"/>
 *     &lt;enumeration value="invalidContainment"/>
 *     &lt;enumeration value="noSuchRequest"/>
 *     &lt;enumeration value="noSuchObject"/>
 *     &lt;enumeration value="unsupportedSelectionType"/>
 *     &lt;enumeration value="resultSetToLarge"/>
 *     &lt;enumeration value="unsupportedProfile"/>
 *     &lt;enumeration value="invalidIdentifier"/>
 *     &lt;enumeration value="alreadyExists"/>
 *     &lt;enumeration value="invalidManagedSysId"/>
 *     &lt;enumeration value="userLimitReached"/>
 *     &lt;enumeration value="operationError"/>
 *     &lt;enumeration value="protocolError"/>
 *     &lt;enumeration value="timeLimitExceeded"/>
 *     &lt;enumeration value="sizeLimitExceeded"/>
 *     &lt;enumeration value="compareFailed"/>
 *     &lt;enumeration value="authenticationFailed"/>
 *     &lt;enumeration value="sessionInvalid"/>
 *     &lt;enumeration value="constraintViolation"/>
 *     &lt;enumeration value="parseException"/>
 *     &lt;enumeration value="noSuchAttribute"/>
 *     &lt;enumeration value="unDefinedType"/>
 *     &lt;enumeration value="insufficientRights"/>
 *     &lt;enumeration value="systemUnavailable"/>
 *     &lt;enumeration value="objectClassViolation"/>
 *     &lt;enumeration value="noResultsReturned"/>
 *     &lt;enumeration value="containerNotEmpty"/>
 *     &lt;enumeration value="csv error"/>
 *     &lt;enumeration value="ldap error"/>
 *     &lt;enumeration value="script name not defined"/>
 *     &lt;enumeration value="command type not defined"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ErrorCode")
@XmlEnum
public enum ErrorCode {

    @XmlEnumValue("persistException")
    PERSIST_EXCEPTION("persistException"),
    @XmlEnumValue("malformedRequest")
    MALFORMED_REQUEST("malformedRequest"),
    @XmlEnumValue("unsupportedOperation")
    UNSUPPORTED_OPERATION("unsupportedOperation"),
    @XmlEnumValue("unsupportedIdentifierType")
    UNSUPPORTED_IDENTIFIER_TYPE("unsupportedIdentifierType"),
    @XmlEnumValue("noSuchIdentifier")
    NO_SUCH_IDENTIFIER("noSuchIdentifier"),
    @XmlEnumValue("customError")
    CUSTOM_ERROR("customError"),
    @XmlEnumValue("otherError")
    OTHER_ERROR("otherError"),
    @XmlEnumValue("directoryError")
    DIRECTORY_ERROR("directoryError"),
    @XmlEnumValue("namingException")
    NAMING_EXCEPTION("namingException"),
    @XmlEnumValue("operationNotSupportedException")
    OPERATION_NOT_SUPPORTED_EXCEPTION("operationNotSupportedException"),
    @XmlEnumValue("sqlError")
    SQL_ERROR("sqlError"),
    @XmlEnumValue("connectorError")
    CONNECTOR_ERROR("connectorError"),
    @XmlEnumValue("invalidConfiguration")
    INVALID_CONFIGURATION("invalidConfiguration"),
    @XmlEnumValue("invalidAttribute")
    INVALID_ATTRIBUTE("invalidAttribute"),
    @XmlEnumValue("unsupportedExecutionMode")
    UNSUPPORTED_EXECUTION_MODE("unsupportedExecutionMode"),
    @XmlEnumValue("invalidContainment")
    INVALID_CONTAINMENT("invalidContainment"),
    @XmlEnumValue("noSuchRequest")
    NO_SUCH_REQUEST("noSuchRequest"),
    @XmlEnumValue("noSuchObject")
    NO_SUCH_OBJECT("noSuchObject"),
    @XmlEnumValue("unsupportedSelectionType")
    UNSUPPORTED_SELECTION_TYPE("unsupportedSelectionType"),
    @XmlEnumValue("resultSetToLarge")
    RESULT_SET_TO_LARGE("resultSetToLarge"),
    @XmlEnumValue("unsupportedProfile")
    UNSUPPORTED_PROFILE("unsupportedProfile"),
    @XmlEnumValue("invalidIdentifier")
    INVALID_IDENTIFIER("invalidIdentifier"),
    @XmlEnumValue("alreadyExists")
    ALREADY_EXISTS("alreadyExists"),
    @XmlEnumValue("invalidManagedSysId")
    INVALID_MANAGED_SYS_ID("invalidManagedSysId"),
    @XmlEnumValue("userLimitReached")
    USER_LIMIT_REACHED("userLimitReached"),
    @XmlEnumValue("operationError")
    OPERATION_ERROR("operationError"),
    @XmlEnumValue("protocolError")
    PROTOCOL_ERROR("protocolError"),
    @XmlEnumValue("timeLimitExceeded")
    TIME_LIMIT_EXCEEDED("timeLimitExceeded"),
    @XmlEnumValue("sizeLimitExceeded")
    SIZE_LIMIT_EXCEEDED("sizeLimitExceeded"),
    @XmlEnumValue("compareFailed")
    COMPARE_FAILED("compareFailed"),
    @XmlEnumValue("authenticationFailed")
    AUTHENTICATION_FAILED("authenticationFailed"),
    @XmlEnumValue("sessionInvalid")
    SESSION_INVALID("sessionInvalid"),
    @XmlEnumValue("constraintViolation")
    CONSTRAINT_VIOLATION("constraintViolation"),
    @XmlEnumValue("parseException")
    PARSE_EXCEPTION("parseException"),
    @XmlEnumValue("noSuchAttribute")
    NO_SUCH_ATTRIBUTE("noSuchAttribute"),
    @XmlEnumValue("unDefinedType")
    UN_DEFINED_TYPE("unDefinedType"),
    @XmlEnumValue("insufficientRights")
    INSUFFICIENT_RIGHTS("insufficientRights"),
    @XmlEnumValue("systemUnavailable")
    SYSTEM_UNAVAILABLE("systemUnavailable"),
    @XmlEnumValue("objectClassViolation")
    OBJECT_CLASS_VIOLATION("objectClassViolation"),
    @XmlEnumValue("noResultsReturned")
    NO_RESULTS_RETURNED("noResultsReturned"),
    @XmlEnumValue("containerNotEmpty")
    CONTAINER_NOT_EMPTY("containerNotEmpty"),
    @XmlEnumValue("csv error")
    CSV_ERROR("csv error"),
    @XmlEnumValue("ldap error")
    LDAP_ERROR("ldap error"),
    @XmlEnumValue("script name not defined")
    SCRIPT_NAME_NOT_DEFINED("script name not defined"),
    @XmlEnumValue("command type not defined")
    COMMAND_TYPE_NOT_DEFINED("command type not defined");
    private final String value;

    ErrorCode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ErrorCode fromValue(String v) {
        for (ErrorCode c: ErrorCode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
