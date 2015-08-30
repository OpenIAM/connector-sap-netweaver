package org.openiam.connector.sapume.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import org.openiam.connector.sapume.service.wrapper.BaseRequestType;
import org.openiam.connector.sapume.service.wrapper.BaseResponseType;
import org.openiam.connector.sapume.service.wrapper.CrudRequest;
import org.openiam.connector.sapume.service.wrapper.LookupAttributeResponse;
import org.openiam.connector.sapume.service.wrapper.LookupRequest;
import org.openiam.connector.sapume.service.wrapper.ObjectFactory;
import org.openiam.connector.sapume.service.wrapper.ObjectResponse;
import org.openiam.connector.sapume.service.wrapper.PasswordRequest;
import org.openiam.connector.sapume.service.wrapper.ReconciliationConfig;
import org.openiam.connector.sapume.service.wrapper.SearchRequest;
import org.openiam.connector.sapume.service.wrapper.SearchResponse2;
import org.openiam.connector.sapume.service.wrapper.SuspendResumeRequest;

/**
 * This class was generated by Apache CXF 2.7.15
 * 2015-06-12T13:46:01.839+02:00
 * Generated source version: 2.7.15
 * 
 */
@WebService(targetNamespace = "http://www.openiam.org/service/connector", name = "SAPUMEConnectorService")
@XmlSeeAlso({org.openiam.connector.sapume.service.types.ObjectFactory.class, ObjectFactory.class})
public interface SAPUMEConnectorService
{
	
	@WebMethod(action = "http://www.openiam.org/service/connector/SAPUMEConnectorService/add")
	@WebResult(name = "return", targetNamespace = "")
	@RequestWrapper(localName = "add", targetNamespace = "http://www.openiam.org/service/connector", className = "org.openiam.connector.sapume.service.wrapper.Add")
	@ResponseWrapper(localName = "addResponse", targetNamespace = "http://www.openiam.org/service/connector", className = "org.openiam.connector.sapume.service.wrapper.AddResponse")
	public ObjectResponse add(
			@WebParam(name = "reqType", targetNamespace = "") CrudRequest reqType);

	
	@WebMethod(action = "http://www.openiam.org/service/connector/SAPUMEConnectorService/modify")
	@WebResult(name = "return", targetNamespace = "")
	@RequestWrapper(localName = "modify", targetNamespace = "http://www.openiam.org/service/connector", className = "org.openiam.connector.sapume.service.wrapper.Modify")
	@ResponseWrapper(localName = "modifyResponse", targetNamespace = "http://www.openiam.org/service/connector", className = "org.openiam.connector.sapume.service.wrapper.ModifyResponse")
	public ObjectResponse modify(
			@WebParam(name = "reqType", targetNamespace = "") CrudRequest reqType);


	@WebMethod(action = "http://www.openiam.org/service/connector/SAPUMEConnectorService/suspend")
	@WebResult(name = "return", targetNamespace = "")
	@RequestWrapper(localName = "suspend", targetNamespace = "http://www.openiam.org/service/connector", className = "org.openiam.connector.sapume.service.wrapper.Suspend")
	@ResponseWrapper(localName = "suspendResponse", targetNamespace = "http://www.openiam.org/service/connector", className = "org.openiam.connector.sapume.service.wrapper.SuspendResponse")
	public BaseResponseType suspend(
			@WebParam(name = "request", targetNamespace = "") SuspendResumeRequest request);


	@WebMethod(action = "http://www.openiam.org/service/connector/SAPUMEConnectorService/resume")
	@WebResult(name = "return", targetNamespace = "")
	@RequestWrapper(localName = "resume", targetNamespace = "http://www.openiam.org/service/connector", className = "org.openiam.connector.sapume.service.wrapper.Resume")
	@ResponseWrapper(localName = "resumeResponse", targetNamespace = "http://www.openiam.org/service/connector", className = "org.openiam.connector.sapume.service.wrapper.ResumeResponse")
	public BaseResponseType resume(
			@WebParam(name = "request", targetNamespace = "") SuspendResumeRequest request);


	@WebMethod(action = "http://www.openiam.org/service/connector/SAPUMEConnectorService/delete")
	@WebResult(name = "return", targetNamespace = "")
	@RequestWrapper(localName = "delete", targetNamespace = "http://www.openiam.org/service/connector", className = "org.openiam.connector.sapume.service.wrapper.Delete")
	@ResponseWrapper(localName = "deleteResponse", targetNamespace = "http://www.openiam.org/service/connector", className = "org.openiam.connector.sapume.service.wrapper.DeleteResponse")
	public ObjectResponse delete(
			@WebParam(name = "reqType", targetNamespace = "") CrudRequest reqType);


	@WebMethod(action = "http://www.openiam.org/service/connector/SAPUMEConnectorService/resetPassword")
	@WebResult(name = "return", targetNamespace = "")
	@RequestWrapper(localName = "resetPassword", targetNamespace = "http://www.openiam.org/service/connector", className = "org.openiam.connector.sapume.service.wrapper.ResetPassword")
	@ResponseWrapper(localName = "resetPasswordResponse", targetNamespace = "http://www.openiam.org/service/connector", className = "org.openiam.connector.sapume.service.wrapper.ResetPasswordResponse")
	public BaseResponseType resetPassword(
			@WebParam(name = "request", targetNamespace = "") PasswordRequest request);


	@WebMethod(action = "http://www.openiam.org/service/connector/SAPUMEConnectorService/setPassword")
	@WebResult(name = "return", targetNamespace = "")
	@RequestWrapper(localName = "setPassword", targetNamespace = "http://www.openiam.org/service/connector", className = "org.openiam.connector.sapume.service.wrapper.SetPassword")
	@ResponseWrapper(localName = "setPasswordResponse", targetNamespace = "http://www.openiam.org/service/connector", className = "org.openiam.connector.sapume.service.wrapper.SetPasswordResponse")
	public BaseResponseType setPassword(
			@WebParam(name = "request", targetNamespace = "") PasswordRequest request);


	@WebMethod(action = "http://www.openiam.org/service/connector/SAPUMEConnectorService/expirePassword")
	@WebResult(name = "return", targetNamespace = "")
	@RequestWrapper(localName = "expirePassword", targetNamespace = "http://www.openiam.org/service/connector", className = "org.openiam.connector.sapume.service.wrapper.ExpirePassword")
	@ResponseWrapper(localName = "expirePasswordResponse", targetNamespace = "http://www.openiam.org/service/connector", className = "org.openiam.connector.sapume.service.wrapper.ExpirePasswordResponse")
	public BaseResponseType expirePassword(
			@WebParam(name = "request", targetNamespace = "") PasswordRequest request);


	@WebMethod(action = "http://www.openiam.org/service/connector/SAPUMEConnectorService/validatePassword")
	@WebResult(name = "return", targetNamespace = "")
	@RequestWrapper(localName = "validatePassword", targetNamespace = "http://www.openiam.org/service/connector", className = "org.openiam.connector.sapume.service.wrapper.ValidatePassword")
	@ResponseWrapper(localName = "validatePasswordResponse", targetNamespace = "http://www.openiam.org/service/connector", className = "org.openiam.connector.sapume.service.wrapper.ValidatePasswordResponse")
	public BaseResponseType validatePassword(
			@WebParam(name = "request", targetNamespace = "") PasswordRequest request);

	
	@WebMethod(action = "http://www.openiam.org/service/connector/SAPUMEConnectorService/lookup")
	@WebResult(name = "return", targetNamespace = "")
	@RequestWrapper(localName = "lookup", targetNamespace = "http://www.openiam.org/service/connector", className = "org.openiam.connector.sapume.service.wrapper.Lookup")
	@ResponseWrapper(localName = "lookupResponse", targetNamespace = "http://www.openiam.org/service/connector", className = "org.openiam.connector.sapume.service.wrapper.LookupResponse")
	public SearchResponse2 lookup(
			@WebParam(name = "request", targetNamespace = "") LookupRequest request);


	@WebMethod(action = "http://www.openiam.org/service/connector/SAPUMEConnectorService/lookupAttributeNames")
	@WebResult(name = "return", targetNamespace = "")
	@RequestWrapper(localName = "lookupAttributeNames", targetNamespace = "http://www.openiam.org/service/connector", className = "org.openiam.connector.sapume.service.wrapper.LookupAttributeNames")
	@ResponseWrapper(localName = "lookupAttributeNamesResponse", targetNamespace = "http://www.openiam.org/service/connector", className = "org.openiam.connector.sapume.service.wrapper.LookupAttributeNamesResponse")
	public LookupAttributeResponse lookupAttributeNames(
			@WebParam(name = "reqType", targetNamespace = "") LookupRequest reqType);


	@WebMethod(action = "http://www.openiam.org/service/connector/SAPUMEConnectorService/search")
	@WebResult(name = "return", targetNamespace = "")
	@RequestWrapper(localName = "search", targetNamespace = "http://www.openiam.org/service/connector", className = "org.openiam.connector.sapume.service.wrapper.Search")
	@ResponseWrapper(localName = "searchResponse", targetNamespace = "http://www.openiam.org/service/connector", className = "org.openiam.connector.sapume.service.wrapper.SearchResponse")
	public SearchResponse2 search(
			@WebParam(name = "searchRequest", targetNamespace = "") SearchRequest searchRequest);


	@WebMethod(action = "http://www.openiam.org/service/connector/SAPUMEConnectorService/reconcileResource")
	@WebResult(name = "return", targetNamespace = "")
	@RequestWrapper(localName = "reconcileResource", targetNamespace = "http://www.openiam.org/service/connector", className = "org.openiam.connector.sapume.service.wrapper.ReconcileResource")
	@ResponseWrapper(localName = "reconcileResourceResponse", targetNamespace = "http://www.openiam.org/service/connector", className = "org.openiam.connector.sapume.service.wrapper.ReconcileResourceResponse")
	public BaseResponseType reconcileResource(
			@WebParam(name = "config", targetNamespace = "") ReconciliationConfig config);


	@WebMethod(action = "http://www.openiam.org/service/connector/SAPUMEConnectorService/testConnection")
	@WebResult(name = "return", targetNamespace = "")
	@RequestWrapper(localName = "testConnection", targetNamespace = "http://www.openiam.org/service/connector", className = "org.openiam.connector.sapume.service.wrapper.TestConnection")
	@ResponseWrapper(localName = "testConnectionResponse", targetNamespace = "http://www.openiam.org/service/connector", className = "org.openiam.connector.sapume.service.wrapper.TestConnectionResponse")
	public BaseResponseType testConnection(
			@WebParam(name = "reqType", targetNamespace = "") BaseRequestType reqType);

}