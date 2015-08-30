package org.openiam.connector.sapume.init;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.connector.sapume.core.common.SAPUMEConstants;
import org.openiam.connector.sapume.core.config.Config;


/**
 * Clase que representa el servlet de inicialización de la aplicación.
 * Lo único que se hará será obtener la ruta del fichero de configuracion de la aplicación
 * de un parámetro definido en el web.xml e instanciará la clase 
 * <code>org.openiam.connector.sap.core.config.Config</code> de configuración con el fichero indicado.
 */
public class ServletInit extends HttpServlet
{

	private static final long serialVersionUID = 1L;
		
	/**
	 * El logger
	 */
	private static Log logger = LogFactory.getLog(ServletInit.class);
	
	
	 /*************************************************************
     ***********           MÉTODOS PÚBLICOS            ***********
     *************************************************************/
	
	/**
	 * Inicialización del servlet.
	 * Obtendrá el nombre del fichero de configuración de la aplicación de una variable
	 * de entorno definida en el web.xml e inicializará la clase de configuración.
	 * @throws ServletException En caso de producirse algún error
	 */
	public void init() throws ServletException {

		try 
		{
			logger.info("-----------------------------------------------------------");
			logger.info("-- Executing SAPUMEConnectorService initialization Servlet --");
						
			// Se inicializa la clase de configuración con el fichero properties definido como parámetro en el Servlet
	        String propertiesFile = this.getInitParameter(SAPUMEConstants.SERVLET_PARAM_CONFIG_PROPERTIES_FILE);	        
	        logger.info("Configuration properties will be loaded from file: " + propertiesFile);
	        Config.initialize(propertiesFile);
	        	        
			logger.info("Servlet finish initialization success.");
			
		} catch (Exception e) {
			logger.error("Error in SAPUMEConnectorService initialization Servlet: " + e.getMessage(), e);
			
		} finally {
			logger.info("-----------------------------------------------------------");
		}
	}
}
