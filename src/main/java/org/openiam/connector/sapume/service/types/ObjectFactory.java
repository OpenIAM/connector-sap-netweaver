
package org.openiam.connector.sapume.service.types;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import org.openiam.connector.sapume.service.wrapper.BaseAttributeContainer;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.openiam.connector.sapume.service.types package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ExtensibleObject_QNAME = new QName("http://www.openiam.org/service/types", "extensibleObject");
    private final static QName _ExtensibleAttributeAttributeContainer_QNAME = new QName("http://www.openiam.org/service/types", "attributeContainer");
    private final static QName _ExtensibleAttributeName_QNAME = new QName("http://www.openiam.org/service/types", "name");
    private final static QName _ExtensibleAttributeOperation_QNAME = new QName("http://www.openiam.org/service/types", "operation");
    private final static QName _ExtensibleAttributeValueAsByteArray_QNAME = new QName("http://www.openiam.org/service/types", "valueAsByteArray");
    private final static QName _ExtensibleAttributeDataType_QNAME = new QName("http://www.openiam.org/service/types", "dataType");
    private final static QName _ExtensibleAttributeMetadataElementId_QNAME = new QName("http://www.openiam.org/service/types", "metadataElementId");
    private final static QName _ExtensibleAttributeObjectType_QNAME = new QName("http://www.openiam.org/service/types", "objectType");
    private final static QName _ExtensibleAttributeMultivalued_QNAME = new QName("http://www.openiam.org/service/types", "multivalued");
    private final static QName _ExtensibleAttributeValue_QNAME = new QName("http://www.openiam.org/service/types", "value");
    private final static QName _ExtensibleAttributeValueList_QNAME = new QName("http://www.openiam.org/service/types", "valueList");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.openiam.connector.sapume.service.types
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ExtensibleObject }
     * 
     */
    public ExtensibleObject createExtensibleObject() {
        return new ExtensibleObject();
    }

    /**
     * Create an instance of {@link ExtensibleAttribute }
     * 
     */
    public ExtensibleAttribute createExtensibleAttribute() {
        return new ExtensibleAttribute();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExtensibleObject }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.openiam.org/service/types", name = "extensibleObject")
    public JAXBElement<ExtensibleObject> createExtensibleObject(ExtensibleObject value) {
        return new JAXBElement<ExtensibleObject>(_ExtensibleObject_QNAME, ExtensibleObject.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BaseAttributeContainer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.openiam.org/service/types", name = "attributeContainer", scope = ExtensibleAttribute.class)
    public JAXBElement<BaseAttributeContainer> createExtensibleAttributeAttributeContainer(BaseAttributeContainer value) {
        return new JAXBElement<BaseAttributeContainer>(_ExtensibleAttributeAttributeContainer_QNAME, BaseAttributeContainer.class, ExtensibleAttribute.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.openiam.org/service/types", name = "name", scope = ExtensibleAttribute.class)
    public JAXBElement<String> createExtensibleAttributeName(String value) {
        return new JAXBElement<String>(_ExtensibleAttributeName_QNAME, String.class, ExtensibleAttribute.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.openiam.org/service/types", name = "operation", scope = ExtensibleAttribute.class)
    public JAXBElement<Integer> createExtensibleAttributeOperation(Integer value) {
        return new JAXBElement<Integer>(_ExtensibleAttributeOperation_QNAME, Integer.class, ExtensibleAttribute.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.openiam.org/service/types", name = "valueAsByteArray", scope = ExtensibleAttribute.class)
    public JAXBElement<byte[]> createExtensibleAttributeValueAsByteArray(byte[] value) {
        return new JAXBElement<byte[]>(_ExtensibleAttributeValueAsByteArray_QNAME, byte[].class, ExtensibleAttribute.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.openiam.org/service/types", name = "dataType", scope = ExtensibleAttribute.class)
    public JAXBElement<String> createExtensibleAttributeDataType(String value) {
        return new JAXBElement<String>(_ExtensibleAttributeDataType_QNAME, String.class, ExtensibleAttribute.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.openiam.org/service/types", name = "metadataElementId", scope = ExtensibleAttribute.class)
    public JAXBElement<String> createExtensibleAttributeMetadataElementId(String value) {
        return new JAXBElement<String>(_ExtensibleAttributeMetadataElementId_QNAME, String.class, ExtensibleAttribute.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.openiam.org/service/types", name = "objectType", scope = ExtensibleAttribute.class)
    public JAXBElement<String> createExtensibleAttributeObjectType(String value) {
        return new JAXBElement<String>(_ExtensibleAttributeObjectType_QNAME, String.class, ExtensibleAttribute.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.openiam.org/service/types", name = "multivalued", scope = ExtensibleAttribute.class)
    public JAXBElement<Boolean> createExtensibleAttributeMultivalued(Boolean value) {
        return new JAXBElement<Boolean>(_ExtensibleAttributeMultivalued_QNAME, Boolean.class, ExtensibleAttribute.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.openiam.org/service/types", name = "value", scope = ExtensibleAttribute.class)
    public JAXBElement<String> createExtensibleAttributeValue(String value) {
        return new JAXBElement<String>(_ExtensibleAttributeValue_QNAME, String.class, ExtensibleAttribute.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.openiam.org/service/types", name = "valueList", scope = ExtensibleAttribute.class)
    public JAXBElement<String> createExtensibleAttributeValueList(String value) {
        return new JAXBElement<String>(_ExtensibleAttributeValueList_QNAME, String.class, ExtensibleAttribute.class, value);
    }

}
