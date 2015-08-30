
package org.openiam.connector.sapume.service.wrapper;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import org.openiam.connector.sapume.service.wrapper.Add;
import org.openiam.connector.sapume.service.wrapper.AddResponse;
import org.openiam.connector.sapume.service.wrapper.Attribute;
import org.openiam.connector.sapume.service.wrapper.BaseAttribute;
import org.openiam.connector.sapume.service.wrapper.BaseAttributeContainer;
import org.openiam.connector.sapume.service.wrapper.BaseProperty;
import org.openiam.connector.sapume.service.wrapper.BaseRequestType;
import org.openiam.connector.sapume.service.wrapper.BaseResponseType;
import org.openiam.connector.sapume.service.wrapper.CrudRequest;
import org.openiam.connector.sapume.service.wrapper.Delete;
import org.openiam.connector.sapume.service.wrapper.DeleteResponse;
import org.openiam.connector.sapume.service.wrapper.ExpirePassword;
import org.openiam.connector.sapume.service.wrapper.ExpirePasswordResponse;
import org.openiam.connector.sapume.service.wrapper.Lookup;
import org.openiam.connector.sapume.service.wrapper.LookupAttributeNames;
import org.openiam.connector.sapume.service.wrapper.LookupAttributeNamesResponse;
import org.openiam.connector.sapume.service.wrapper.LookupAttributeResponse;
import org.openiam.connector.sapume.service.wrapper.LookupRequest;
import org.openiam.connector.sapume.service.wrapper.LookupResponse;
import org.openiam.connector.sapume.service.wrapper.Modify;
import org.openiam.connector.sapume.service.wrapper.ModifyResponse;
import org.openiam.connector.sapume.service.wrapper.ObjectResponse;
import org.openiam.connector.sapume.service.wrapper.ObjectValue;
import org.openiam.connector.sapume.service.wrapper.PasswordRequest;
import org.openiam.connector.sapume.service.wrapper.ReconcileResource;
import org.openiam.connector.sapume.service.wrapper.ReconcileResourceResponse;
import org.openiam.connector.sapume.service.wrapper.ReconciliationConfig;
import org.openiam.connector.sapume.service.wrapper.ReconciliationSituation;
import org.openiam.connector.sapume.service.wrapper.ResetPassword;
import org.openiam.connector.sapume.service.wrapper.ResetPasswordResponse;
import org.openiam.connector.sapume.service.wrapper.Resume;
import org.openiam.connector.sapume.service.wrapper.ResumeResponse;
import org.openiam.connector.sapume.service.wrapper.Search;
import org.openiam.connector.sapume.service.wrapper.SearchRequest;
import org.openiam.connector.sapume.service.wrapper.SearchResponse;
import org.openiam.connector.sapume.service.wrapper.SearchResponse2;
import org.openiam.connector.sapume.service.wrapper.SetPassword;
import org.openiam.connector.sapume.service.wrapper.SetPasswordResponse;
import org.openiam.connector.sapume.service.wrapper.Suspend;
import org.openiam.connector.sapume.service.wrapper.SuspendResponse;
import org.openiam.connector.sapume.service.wrapper.SuspendResumeRequest;
import org.openiam.connector.sapume.service.wrapper.TestConnection;
import org.openiam.connector.sapume.service.wrapper.TestConnectionResponse;
import org.openiam.connector.sapume.service.wrapper.ValidatePassword;
import org.openiam.connector.sapume.service.wrapper.ValidatePasswordResponse;
import org.openiam.connector.sapume.service.wrapper.obsolete.ArrayList;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.openiam.connector.sapume.service package. 
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

    private final static QName _ObjectResponse_QNAME = new QName("http://www.openiam.org/service/connector", "objectResponse");
    private final static QName _SearchResponse2_QNAME = new QName("http://www.openiam.org/service/connector", "searchResponse2");
    private final static QName _BaseResponseType_QNAME = new QName("http://www.openiam.org/service/connector", "baseResponseType");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.openiam.connector.sapume.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BaseRequestType }
     * 
     */
    public BaseRequestType createBaseRequestType() {
        return new BaseRequestType();
    }

    /**
     * Create an instance of {@link ModifyResponse }
     * 
     */
    public ModifyResponse createModifyResponse() {
        return new ModifyResponse();
    }

    /**
     * Create an instance of {@link SearchRequest }
     * 
     */
    public SearchRequest createSearchRequest() {
        return new SearchRequest();
    }

    /**
     * Create an instance of {@link TestConnectionResponse }
     * 
     */
    public TestConnectionResponse createTestConnectionResponse() {
        return new TestConnectionResponse();
    }

    /**
     * Create an instance of {@link Suspend }
     * 
     */
    public Suspend createSuspend() {
        return new Suspend();
    }

    /**
     * Create an instance of {@link BaseResponseType }
     * 
     */
    public BaseResponseType createBaseResponseType() {
        return new BaseResponseType();
    }

    /**
     * Create an instance of {@link TestConnection }
     * 
     */
    public TestConnection createTestConnection() {
        return new TestConnection();
    }

    /**
     * Create an instance of {@link Search }
     * 
     */
    public Search createSearch() {
        return new Search();
    }

    /**
     * Create an instance of {@link Resume }
     * 
     */
    public Resume createResume() {
        return new Resume();
    }

    /**
     * Create an instance of {@link DeleteResponse }
     * 
     */
    public DeleteResponse createDeleteResponse() {
        return new DeleteResponse();
    }

    /**
     * Create an instance of {@link ValidatePasswordResponse }
     * 
     */
    public ValidatePasswordResponse createValidatePasswordResponse() {
        return new ValidatePasswordResponse();
    }

    /**
     * Create an instance of {@link BaseProperty }
     * 
     */
    public BaseProperty createBaseProperty() {
        return new BaseProperty();
    }

    /**
     * Create an instance of {@link Add }
     * 
     */
    public Add createAdd() {
        return new Add();
    }

    /**
     * Create an instance of {@link Modify }
     * 
     */
    public Modify createModify() {
        return new Modify();
    }

    /**
     * Create an instance of {@link LookupAttributeNamesResponse }
     * 
     */
    public LookupAttributeNamesResponse createLookupAttributeNamesResponse() {
        return new LookupAttributeNamesResponse();
    }

    /**
     * Create an instance of {@link ObjectResponse }
     * 
     */
    public ObjectResponse createObjectResponse() {
        return new ObjectResponse();
    }

    /**
     * Create an instance of {@link CrudRequest }
     * 
     */
    public CrudRequest createCrudRequest() {
        return new CrudRequest();
    }

    /**
     * Create an instance of {@link ReconciliationConfig }
     * 
     */
    public ReconciliationConfig createReconciliationConfig() {
        return new ReconciliationConfig();
    }

    /**
     * Create an instance of {@link BaseAttributeContainer }
     * 
     */
    public BaseAttributeContainer createBaseAttributeContainer() {
        return new BaseAttributeContainer();
    }

    /**
     * Create an instance of {@link ReconcileResource }
     * 
     */
    public ReconcileResource createReconcileResource() {
        return new ReconcileResource();
    }

    /**
     * Create an instance of {@link SetPassword }
     * 
     */
    public SetPassword createSetPassword() {
        return new SetPassword();
    }

    /**
     * Create an instance of {@link LookupAttributeNames }
     * 
     */
    public LookupAttributeNames createLookupAttributeNames() {
        return new LookupAttributeNames();
    }

    /**
     * Create an instance of {@link SearchResponse2 }
     * 
     */
    public SearchResponse2 createSearchResponse2() {
        return new SearchResponse2();
    }

    /**
     * Create an instance of {@link ExpirePasswordResponse }
     * 
     */
    public ExpirePasswordResponse createExpirePasswordResponse() {
        return new ExpirePasswordResponse();
    }

    /**
     * Create an instance of {@link SuspendResumeRequest }
     * 
     */
    public SuspendResumeRequest createSuspendResumeRequest() {
        return new SuspendResumeRequest();
    }

    /**
     * Create an instance of {@link ValidatePassword }
     * 
     */
    public ValidatePassword createValidatePassword() {
        return new ValidatePassword();
    }

    /**
     * Create an instance of {@link ReconciliationSituation }
     * 
     */
    public ReconciliationSituation createReconciliationSituation() {
        return new ReconciliationSituation();
    }

    /**
     * Create an instance of {@link SuspendResponse }
     * 
     */
    public SuspendResponse createSuspendResponse() {
        return new SuspendResponse();
    }

    /**
     * Create an instance of {@link Delete }
     * 
     */
    public Delete createDelete() {
        return new Delete();
    }

    /**
     * Create an instance of {@link ReconcileResourceResponse }
     * 
     */
    public ReconcileResourceResponse createReconcileResourceResponse() {
        return new ReconcileResourceResponse();
    }

    /**
     * Create an instance of {@link SetPasswordResponse }
     * 
     */
    public SetPasswordResponse createSetPasswordResponse() {
        return new SetPasswordResponse();
    }

    /**
     * Create an instance of {@link ResumeResponse }
     * 
     */
    public ResumeResponse createResumeResponse() {
        return new ResumeResponse();
    }

    /**
     * Create an instance of {@link Attribute }
     * 
     */
    public Attribute createAttribute() {
        return new Attribute();
    }

    /**
     * Create an instance of {@link SearchResponse }
     * 
     */
    public SearchResponse createSearchResponse() {
        return new SearchResponse();
    }

    /**
     * Create an instance of {@link PasswordRequest }
     * 
     */
    public PasswordRequest createPasswordRequest() {
        return new PasswordRequest();
    }

    /**
     * Create an instance of {@link LookupResponse }
     * 
     */
    public LookupResponse createLookupResponse() {
        return new LookupResponse();
    }

    /**
     * Create an instance of {@link LookupRequest }
     * 
     */
    public LookupRequest createLookupRequest() {
        return new LookupRequest();
    }

    /**
     * Create an instance of {@link ExpirePassword }
     * 
     */
    public ExpirePassword createExpirePassword() {
        return new ExpirePassword();
    }

    /**
     * Create an instance of {@link Lookup }
     * 
     */
    public Lookup createLookup() {
        return new Lookup();
    }

    /**
     * Create an instance of {@link ResetPassword }
     * 
     */
    public ResetPassword createResetPassword() {
        return new ResetPassword();
    }

    /**
     * Create an instance of {@link BaseAttribute }
     * 
     */
    public BaseAttribute createBaseAttribute() {
        return new BaseAttribute();
    }

    /**
     * Create an instance of {@link ObjectValue }
     * 
     */
    public ObjectValue createObjectValue() {
        return new ObjectValue();
    }

    /**
     * Create an instance of {@link LookupAttributeResponse }
     * 
     */
    public LookupAttributeResponse createLookupAttributeResponse() {
        return new LookupAttributeResponse();
    }

    /**
     * Create an instance of {@link ResetPasswordResponse }
     * 
     */
    public ResetPasswordResponse createResetPasswordResponse() {
        return new ResetPasswordResponse();
    }

    /**
     * Create an instance of {@link AddResponse }
     * 
     */
    public AddResponse createAddResponse() {
        return new AddResponse();
    }

    /**
     * Create an instance of {@link ArrayList }
     * 
     */
    public ArrayList createArrayList() {
        return new ArrayList();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObjectResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.openiam.org/service/connector", name = "objectResponse")
    public JAXBElement<ObjectResponse> createObjectResponse(ObjectResponse value) {
        return new JAXBElement<ObjectResponse>(_ObjectResponse_QNAME, ObjectResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchResponse2 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.openiam.org/service/connector", name = "searchResponse2")
    public JAXBElement<SearchResponse2> createSearchResponse2(SearchResponse2 value) {
        return new JAXBElement<SearchResponse2>(_SearchResponse2_QNAME, SearchResponse2 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BaseResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.openiam.org/service/connector", name = "baseResponseType")
    public JAXBElement<BaseResponseType> createBaseResponseType(BaseResponseType value) {
        return new JAXBElement<BaseResponseType>(_BaseResponseType_QNAME, BaseResponseType.class, null, value);
    }

}
