package org.openiam.connector.sapume.core.connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.connector.sapume.core.common.SAPUMEConfiguration;
import org.openiam.connector.sapume.core.common.SAPUMEConnectorException;
import org.openiam.connector.sapume.core.common.SAPUMEConstants;
import org.openiam.connector.sapume.core.common.SAPUMEFilterGenerator;
import org.openspml.message.AddRequest;
import org.openspml.message.Attribute;
import org.openspml.message.DeleteRequest;
import org.openspml.message.Filter;
import org.openspml.message.Modification;
import org.openspml.message.ModifyRequest;
import org.openspml.message.SearchRequest;
import org.openspml.message.SpmlRequest;
import org.openspml.message.SpmlResponse;

public class SAPUMEConnection
{
	/*************************************************************
     **********          OBJETOS INTERNOS              ***********
     *************************************************************/
	
	private static Log logger = LogFactory.getLog(SAPUMEConnection.class);
	
	protected SAPUMEConfiguration sapConfiguration;
		
	
	/*************************************************************
     ***********             CONSTRUCTORES             *********** 
     *************************************************************/

	public SAPUMEConnection(SAPUMEConfiguration configuration)
	{
		this.sapConfiguration = configuration;
	}

	
	/*************************************************************
     ***********           MÉTODOS PÚBLICOS            ***********
     *************************************************************/
	
	public SpmlResponse sendSPMLRequest(SpmlRequest spmlRequest, boolean traceSPMLRequest) throws SAPUMEConnectorException
	{
		SpmlResponse spmlResponse = null;
		HttpURLConnection httpUrlConnection = null;
		OutputStream os = null;
		BufferedReader bufferedreader = null;
				
		try
		{
			logger.debug("Start sendSPMLRequest() method");
			
			String spmlResquestForLog = "";
			if (traceSPMLRequest)
			{
				if (spmlRequest instanceof AddRequest)
				{
					AddRequest spmlAddRequestForLog = new AddRequest();
					spmlAddRequestForLog.setIdentifier(spmlRequest.getIdentifierString());
					List<Attribute> reqAtts = ((AddRequest) spmlRequest).getAttributes();
					for(Attribute oneSpmlAtt : reqAtts)
					{
						if (oneSpmlAtt.getName().equals(SAPUMEConstants.SAPUME_FIELD_PASSWORD) || oneSpmlAtt.getName().equals(SAPUMEConstants.SAPUME_FIELD_OLDPASSWORD))
							spmlAddRequestForLog.setAttribute(oneSpmlAtt.getName(), "xxxxx");
						else
							spmlAddRequestForLog.setAttribute(oneSpmlAtt.getName(), oneSpmlAtt.getValue());
					}
					logger.info("SPML Add request to send to SAPUME: " + System.getProperty("line.separator") + spmlAddRequestForLog.toXml());
					spmlResquestForLog = spmlAddRequestForLog.toXml();
					
				} else if (spmlRequest instanceof ModifyRequest) {
					
					ModifyRequest spmlModifyRequestForLog = new ModifyRequest();
					spmlModifyRequestForLog.setIdentifier(spmlRequest.getIdentifierString());
					List<Modification> modifListreqAtts = ((ModifyRequest) spmlRequest).getModifications();								
					for(Modification oneSpmlModif : modifListreqAtts)
					{
						if (oneSpmlModif.getName().equals(SAPUMEConstants.SAPUME_FIELD_PASSWORD) || oneSpmlModif.getName().equals(SAPUMEConstants.SAPUME_FIELD_OLDPASSWORD))
							spmlModifyRequestForLog.addModification(oneSpmlModif.getName(), "xxxxx");
						else
							spmlModifyRequestForLog.addModification(oneSpmlModif);
					}
					logger.info("SPML Modify request to send to SAPUME: " + System.getProperty("line.separator") + spmlModifyRequestForLog.toXml());
					spmlResquestForLog = spmlModifyRequestForLog.toXml();
					
				} else if (spmlRequest instanceof SearchRequest) {
					
					logger.info("SPML Search request to send to SAPUME: " + System.getProperty("line.separator") +  spmlRequest.toXml());
					spmlResquestForLog = spmlRequest.toXml();
					
				} else if (spmlRequest instanceof DeleteRequest) {
					
					logger.info("SPML Delete request to send to SAPUME: " + System.getProperty("line.separator") +  spmlRequest.toXml());
					spmlResquestForLog = spmlRequest.toXml();
					
				} else {
					logger.info("Unknown SPML operation request to send to SAPUME: " + System.getProperty("line.separator") +  spmlRequest.toXml());
					spmlResquestForLog = spmlRequest.toXml();
				}
			}
			
			httpUrlConnection = this.getHTTPConnection();
			logger.info("HTTP connection establiished with SAPUME: " + httpUrlConnection);
					
			String soapRequest = this.constructRequest(spmlRequest.toXml(), spmlResquestForLog);
			logger.debug("SOAP request message obtained");
			
			byte[] soapRequestBytes = soapRequest.getBytes();
			httpUrlConnection.setRequestProperty("Content-Length", String.valueOf(soapRequestBytes.length));
			httpUrlConnection.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
			httpUrlConnection.setRequestProperty("X-Requested-With", "XMLHttpRequest");
			httpUrlConnection.setRequestProperty("SOAPAction", "POST");
			
			httpUrlConnection.setRequestMethod("POST");
			httpUrlConnection.setDoOutput(true);
			httpUrlConnection.setDoInput(true);

			os = httpUrlConnection.getOutputStream();
			logger.debug("OutputStream obtained from HttpURLConnection");
			os.write(soapRequestBytes);
			logger.debug("SOAP message sended!");
			
			// Obtenemos el stream para leer la respuesta
			bufferedreader = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));
			logger.debug("Stream to read response obtained from HttpURLConnection");

			// Leemos la respuesta
			StringBuffer sbResponse = new StringBuffer();
			String line = null;
			logger.debug("Read response...");
			while ((line = bufferedreader.readLine()) != null)   
			{
				sbResponse.append(line);
			}
			String responseContent = sbResponse.length() == 0 ? null : sbResponse.toString();
			logger.debug("This is the received plain response content: " + responseContent);
			
			logger.debug("Now response will be parsed to build a SPML message response");
			spmlResponse = SpmlResponse.parseResponse(responseContent);
			logger.debug("SpmlResponse obtained after parse: " + System.getProperty("line.separator") + spmlResponse.toXml());
			
		} catch (SAPUMEConnectorException e) {
			throw e;
			
		} catch (Exception e) {
			logger.error("Generic error sending SPML Request to SAP UME: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error sending SPML Request to SAP UME: " + e.getMessage(), e);
			
		} finally {
			try { bufferedreader.close(); } catch (Exception ex) {}
			try { os.close(); } catch (Exception ex) {}
			try { httpUrlConnection.disconnect(); } catch (Exception ex) {}
			bufferedreader = null;
			os = null;
			httpUrlConnection = null;
		}

		return spmlResponse;
	}

	
	public void test() throws SAPUMEConnectorException
	{
		try
		{
			logger.debug("Start test() method");
			
			SearchRequest spmlSearchReq = new SearchRequest();
			// Definimos tipo objetos a buscar
			spmlSearchReq.setSearchBase(SAPUMEConstants.SAPUME_TARGETOBJCLASS_USER);
			// Construimos el filtro de búsqueda para buscar a un usuario concreto
			Filter filter = SAPUMEFilterGenerator.buildSPMLFiltertoMatchUserByLogonName("sapume test user"); 
			spmlSearchReq.setFilter(filter);
			spmlSearchReq.addAttribute(SAPUMEConstants.SAPUME_FIELD_LOGONNAME);
			spmlSearchReq.addAttribute(SAPUMEConstants.SAPUME_FIELD_FIRSTNAME);
			
			logger.debug("SPML request will be send to SAPUME...");
			this.sendSPMLRequest(spmlSearchReq, this.sapConfiguration.isTraceSPMLRequest());
			logger.debug("SPML response received from SAPUME");
			
			logger.debug("Test connection was success!");
			
		} catch (Exception e) {
			logger.error("Generic error while try to test SAPUME connection: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error while try to test SAPUME connection: " + e.getMessage(), e);
		}
	}
	
	
	/*************************************************************
     ***********           MÉTODOS PRIVADOS            *********** 
     *************************************************************/
	
	private HttpURLConnection getHTTPConnection() throws SAPUMEConnectorException
	{
		URLConnection httpConnection = null;

		try
		{
			logger.debug("Start getHTTPConnection() method");
			
			String url = this.sapConfiguration.getSapUmeUrl();
			logger.debug("SapUmeUrl: " + url);
			final String user = this.sapConfiguration.getUser();
			logger.debug("user: " + user);
			final String pwd = String.valueOf((this.sapConfiguration.getPassword().getClearValue()));
			logger.debug("pwd: " + "xxxxx");
			
			URL urlObj = new URL(url);
			URLConnection connection = urlObj.openConnection();
			logger.debug("HTTP connection opened");
			httpConnection = connection;

			String connectStr = user + ":" + pwd;
			String encoding = Base64.encodeBase64String(connectStr.getBytes());
			connection.setRequestProperty("Authorization", "Basic " + encoding);

			if ((user != null) && (pwd != null))
			{
				Authenticator.setDefault(new Authenticator()
				{
					public PasswordAuthentication getPasswordAuthentication()
					{
						return new PasswordAuthentication(user, pwd.toCharArray());
					}
				});
				
				logger.debug("Basic authentication credentials set in HTTP connection");
			}
			
			return (HttpURLConnection)httpConnection;
		
		} catch (MalformedURLException e) {
			logger.error("URL to connect to SAPUME is malformed: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("URL to connect to SAPUME is malformed: " + e.getMessage(), e);
			
		} catch (Exception e) {
			logger.error("Generic error while try to create HttpURLConnection: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error while try to create HttpURLConnection: " + e.getMessage(), e);
		}
	}

	private String constructRequest(String request, String resquestForLog)
	{
		logger.debug("Start constructRequest() method");
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='UTF-8'?> \n")
		  .append("<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"> \n")
		  .append("<SOAP-ENV:Header/> \n")
		  .append("<SOAP-ENV:Body> \n")
		  .append(request)
		  .append("</SOAP-ENV:Body> \n")
		  .append("</SOAP-ENV:Envelope> \n");
		
		if (resquestForLog != null && ! resquestForLog.equals(""))
		{
			StringBuffer sbForLog = new StringBuffer();
			sbForLog.append("<?xml version='1.0' encoding='UTF-8'?> \n")
			  .append("<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"> \n")
			  .append("<SOAP-ENV:Header/> \n")
			  .append("<SOAP-ENV:Body> \n")
			  .append(resquestForLog)
			  .append("</SOAP-ENV:Body> \n")
			  .append("</SOAP-ENV:Envelope> \n");
			logger.debug("SOAP request message obtained: " + System.getProperty("line.separator") + sbForLog.toString());
		}
		
		return sb.toString();
	}
	
}
