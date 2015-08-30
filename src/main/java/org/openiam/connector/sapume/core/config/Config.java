package org.openiam.connector.sapume.core.config;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.connector.sapume.core.common.SAPUMEConnectorException;
import org.openiam.connector.sapume.core.security.SymmetricEncryption;


/**
 * Clase que lee el fichero de propiedades de la aplicación.
 * <br>
 * La clase permite trabajar con parametros cifrados, para ello en el fichero de propiedades identificaremos 
 * aquellos parametros que deseemos vayan cifrados con un nombre que empiece por la cadena definida en la
 * constante CONIG_PREFIX_PARAM_CIPHER. Por ejemplo "X_PwdBBDD". 
 * Definimos en el fichero el valor sin cifrar que deseemos.Esta clase al leer las propiedades, identificara 
 * aquellas que deben almacenarse cifradas, y en caso de ser necesario las cifra y almacena cifradas en el fichero.
 * De esta forma el parametro se introduce en claro y es la propia aplicación la que se encarga de cifrarlo 
 * la primera vez que lo lee.
 * <br>
 */
public class Config 
{
	/*************************************************************
     **********          OBJETOS INTERNOS              ***********
     *************************************************************/
	
	/**
	 * El logger
	 */
	private static Log logger = LogFactory.getLog(Config.class);
	
	/**
     * Constante que indica el prefijo que tienen los parametros del fichero de propiedades que deben almacenarse cifrados 
     */
    private static final String CONFIG_PREFIX_PARAM_CIPHER = "CIPHER_";
	
    /**
     * Constante que indica el caracter separador de los elementos un array 
     */
	private static final String CONFIG_ARRAY_SEPARATOR = ",";
			
	/**
	 * Instancia estática de la clase
	 */
	private static final Config config = new Config();
	
    /**
     * Continene las propiedades del fichero de configuración
     */
    private Properties props = null;
    
    /**
     * Fichero de propiedades
     */
    private String file = null;
    
        
    /*************************************************************
     ***********           MÉTODOS PÚBLICOS            ***********
     *************************************************************/
    
    /**
     * Inicializa la clase a partir del fichero de configuración indicado en la ruta pasada.
     * Simulación de clase estática.
     * @param file
     * @throws SAPUMEConnectorException
     */
    public static synchronized void initialize(String file) throws SAPUMEConnectorException
    {
    	config.load(file);
    }
    
    
    /**
     * @return Returns the file.
     */
    public static String getFile() 
    {
        return config.file;
    }
    
 
    /**
     * Metodo que permite verificar si la  configuracion ya ha sido inicializada
     * @return
     */
    public static boolean isInitialized() 
    {
    	// Comprobamos si se han asociado las propiedades
    	if (config.props!=null) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    /**
     * Devuelve el valor establecido para la propiedad indicada del fichero de propiedades. 
     * @param propertyKey Nombre de clave que identifica la propiedad en el fichero
     * @return Valor de la propiedad o null en caso de no encontrarla 
     */
    public static String get(String propertyKey)
    {
    	String value = null;
    	
    	if (propertyKey != null)
    	{
        	if (isInitialized())
        	{
        		// Antes de recuperar el valor comprobamos que la clave existe, en caso de no 
        		//	existir y no estar marcada como cifrada, buscamos si existe la misma propiedad
        		//	marcada como cifrada
        		if (!config.props.containsKey(propertyKey) && config.props.containsKey(CONFIG_PREFIX_PARAM_CIPHER + propertyKey)) {
        			propertyKey = CONFIG_PREFIX_PARAM_CIPHER + propertyKey;
        		}
        		// Obtenemos el valor
        		value = config.props.getProperty(propertyKey);
        		
        		// Si es un campo cifrado se descifra antes de devolverse
        		if (propertyKey.startsWith(CONFIG_PREFIX_PARAM_CIPHER) && value != null && value.trim().length() >= 2)
        		{        			
        			// Obtenemos el valor cifrado sin los caracteres '{' y '}'
        			String pwdCifrada = value.trim().substring(1, value.length() - 1);
        			try {
	                    // Se decodifica el Base64 y se descifra
        				byte[] pwdDescifrada = SymmetricEncryption.decrypt(Base64.decodeBase64(pwdCifrada));
        				// Lo convertimos a String
	            		value = new String(pwdDescifrada);
        			} catch (Exception e) {
						// Error al descifrar el valor
        				logger.error("Error trying to decrypt the value of the param " + propertyKey + " with value " + pwdCifrada + ": " + e.getMessage(), e);
        				value = null;
       				}
        		}
        		
        	} else {
        		logger.error("Configuration class is not initialized");
        	}
    	}
    	
    	return (value != null) ? value.trim() : "";
    }
    
    /**
     * Metodo que devuelve el valor de una propiedad como entero, al metodo se le indicara si ese
     * parametro es opcional y se permite por tanto que no exista o valga nulo o no
     * @param propertyKey Nombre de clave que identifica la propiedad en el fichero 
     * @return Valor de la propiedad como entero
     * @throws SIAException En caso de producirse algún error
     */
    public static Integer getAsInteger(String propertyKey) throws SAPUMEConnectorException
    {	
    	String valor = get(propertyKey);
    	if (valor==null) {
    		return null;
    	} else {
    		try {
    			return Integer.parseInt(valor);
    		} catch (NumberFormatException e) {
    			logger.error("The param '" +  propertyKey + "' must have a numeric value (Integer)", e);
    			throw new SAPUMEConnectorException("The param requested not have numeric value (Integer)", e);
    		}
    	}
    }
    
    /**
     * Metodo que devuelve el valor de una propiedad como entero largo, al metodo se le 
     * indicara si ese parametro es opcional y se permite por tanto que no exista o 
     * valga nulo o no
     * @param propertyKey Nombre de clave que identifica la propiedad en el fichero 
     * @return Valor de la propiedad como entero
     * @throws SAPUMEConnectorException En caso de producirse algún error
     */
    public static Long getAsLong(String propertyKey) throws SAPUMEConnectorException 
    {	
    	String valor = get(propertyKey);
    	if (valor==null) {
    		return null;
    	} else {
    		try {
    			return Long.parseLong(valor);
    		} catch (NumberFormatException e) {
    			logger.error("The param '" +  propertyKey + "' must have a numeric value (Long)", e);
    			throw new SAPUMEConnectorException("The param requested not have numeric value (Long)", e);
    		}
    	}
    }    
    
    /**
     * Metodo que devuelve una propiedad no como una cadena sino como un array de elementos,
     * este metodo es utilizado sobre aquellas propiedades cuyo valor esta formado por varios
     * elementos separados por el valor de CONFIG_ARRAY_SEPARATOR.
     * Si por ejemplo este valor fuese la coma ',' un ejemplo seria:
     * 		- varXXX = elem1,elem2,elem3
     * @param propertyKey Nombre de clave que identifica la propiedad en el fichero
     * @return Array con los valores de la propiedad o null en caso de no encontrarla 
     */
    public static String[] getAsArray(String propertyKey) 
    {	
    	// Obtengo la propiedad
    	String valPrp = Config.get(propertyKey);
    	// Si posee valor
    	if (valPrp!=null) {
    		// Convertimos la cadena en array
    		String[] valores = valPrp.split(CONFIG_ARRAY_SEPARATOR);
    		// Eliminamos los espacios en blanco
    		if (valores!=null) {
        		String[] valTrim = new String[valores.length];
        		for (int i = 0; i < valores.length; i++) {
        			if (valores[i]!=null) {
        				valTrim[i] = valores[i].trim(); 
        			} else {
        				valTrim[i] = null;
        			}
        		}
        		return valTrim;
    		}
    		return null;
    	}
    	// Devovemos un valor nulo
    	return null;
    }
    
    /**
     * Metodo que devuelve una propiedad no como una cadena sino como un array de elementos.
     * En este caso la propiedad contiene uno o mas valores separados por la cadena indicada en el parámetro 'separador'.
     * Si por ejemplo este valor fuese la coma ',' un ejemplo seria:
     * 		- varXXX = elem1,elem2,elem3
     * @param propertyKey Nombre de clave que identifica la propiedad en el fichero
     * @return Array con los valores de la propiedad o null en caso de no encontrarla 
     */
    public static String[] getAsArray(String propertyKey, String separador) 
    {	
    	// Obtengo la propiedad
    	String valPrp = Config.get(propertyKey);
    	// Si posee valor
    	if (valPrp!=null) {
    		// Convertimos la cadena en array
    		String[] valores = valPrp.split(CONFIG_ARRAY_SEPARATOR);
    		// Eliminamos los espacios en blanco
    		if (valores!=null) {
        		String[] valTrim = new String[valores.length];
        		for (int i = 0; i < valores.length; i++) {
        			if (valores[i]!=null) {
        				valTrim[i] = valores[i].trim(); 
        			} else {
        				valTrim[i] = null;
        			}
        		}
        		return valTrim;
    		}
    		return null;
    	}
    	// Devovemos un valor nulo
    	return null;
    }
       
    /**
     * Devuelve el valor establecido para la propiedad indicada del fichero de propiedades. En este caso
     * 	la propiedad contiene uno o mas valores de tipo integer separados por la cadena 'separador'
     * @param propertyKey Nombre de clave que identifica la propiedad en el fichero
     * @param separador Caracter o cadena que sirve de separador a los elementos
     * @return Listado con los valores de la propiedad o null en caso de no encontrarla
     */
    public static Integer[] getAsArrayInteger(String propertyKey, String separador)
    {	
    	if (config.props != null) {
    		String value = config.props.getProperty(propertyKey);
    		if (value!=null) {
    			String[] valStrSep = value.split(config.props.getProperty(separador));
    			Integer[] valInt = new Integer[valStrSep.length];
    			// Vamos validando que sean enteros
    			int i = 0;
    			for (String unVal: valStrSep) {
        			try {
        	    		valInt[i] = Integer.parseInt(unVal);
        	    	} catch (NumberFormatException e) {
        	    		// Error de configuracion el parametro no es un entero
        	    		logger.error("Configuration error: the param '" +  propertyKey + "' must have a numeric value (Integer)", e);
        			    return null;
        			}
        			i++;
    			}
    			return valInt; 
    		}
            return null;
    	} else {
			return null;
    	}        
    }    
 
    /**
     * Metodo que devuelve una propiedad no como una cadena sino como una lista de elementos.
     * Este metodo es utilizado sobre aquellas propiedades cuyo valor esta formado por varios 
     * elementos separados por el valor de CONFIG_ARRAY_SEPARATOR.
     * Si por ejemplo este valor fuese la coma ',' un ejemplo seria:
     * 		- varXXX = elem1,elem2,elem3
     * @param propertyKey Nombre de clave que identifica la propiedad en el fichero
     * @return Array con los valores de la propiedad o null en caso de no encontrarla 
     */
    public static List<String> getAsList(String propertyKey) 
    {	
    	// Obtengo la propiedad
    	String valPrp = Config.get(propertyKey);
    	// Si posee valor
    	if (valPrp!=null) {
    		// Convertimos la cadena en array
    		String[] valores = valPrp.split(CONFIG_ARRAY_SEPARATOR);
    		// Eliminamos los espacios en blanco
    		if (valores!=null) {
        		
    			List<String> list = new ArrayList<String>();
        		for (int i = 0; i < valores.length; i++) {
        			if (valores[i]!=null) {
        				list.add(valores[i].trim()); 
        			} else {
        				list.add(null);
        			}
        		}
        		return list;
    		}
    		return null;
    	}
    	// Devovemos un valor nulo
    	return null;
    }
    
    /**
     * Método que comprueba si coincide el valor pasado para la propiedad indicada con el valor que se tiene
     * en cargada en la configuración.
	 * Si se trata de una propiedad multi-valuada, se comprobar� si el valor coincide con alguno de los valores definidos. 
     * @param propertyKey Nombre de la propiedad
     * @param propertyValue Valor de la propiedad
     * @return Devuelve true si el valor coincide/est� contenido en la lista multi-valudada. 
     * 		   False en caso contrario.
     */
    public static boolean checkMatchingConfigValue(String propertyKey, String propertyValue)
    {	
    	boolean matchingValue = false;
		
    	String configPtyValue = Config.get(propertyKey);
		if (configPtyValue != null)
		{
			// Comprobamos si hay coincidencia directa de los valores
			if (configPtyValue.equals(propertyValue))
			{
				matchingValue = true;
				
			} else {
				// Vemos si es una configuraci�n multi-valuada
				if (configPtyValue.contains(CONFIG_ARRAY_SEPARATOR))
				{
					// Y comprobamos si existe el valor indicado
					String[] ptyValues = configPtyValue.split(CONFIG_ARRAY_SEPARATOR);
					for (String oneValue : ptyValues)
					{
						if (propertyValue.equals(oneValue))
						{
							matchingValue = true;
							break;
						}
					}
				}
			}
		}
    	
		return matchingValue;
    }
    
    
    /*************************************************************
     ***********           MÉTODOS PRIVADOS            ***********
     *************************************************************/
 
    /**
     * Método de inicialización de esta clase. Se inicializa la clase estableciendo
     * como fichero de configuración el que se indique en el parámetro de entrada.
     * El metodo contempla la existencia de parametros cifrados dentro del fichero de propiedades,
     * estos parametros, la primera vez que se introducen en el fichero, se introducen en claro, y 
     * es ete propio método de inicializaci�n el encargado de cifrarlos y almacenarlos como
     * cifrados en dicho fichero.
     * @param file Nombre del fichero con el que se inicializará la clase (el fichero deberá estar 
     * 			   en el classpath de la aplicación). También se podrá indicar una ruta al fichero.
     * @throws SAPUMEConnectorException En caso de producirse algún error
     */
    private void load(String file) throws SAPUMEConnectorException 
    {	
    	BufferedInputStream is = null;
    	try 
    	{
    		logger.debug("Configuration class will we initialized using file: " + file);
    		config.file = file;
			
    		// **************************************************************
    		// 1. Se crea un stream a partir del fichero de propiedades, el fichero debera estar en el classpath de la aplicacion
    		// **************************************************************
    		config.props = new Properties();
			try 
			{
				// Lo intentamos obtener como fichero
				is = new BufferedInputStream(new FileInputStream(file));
				
			} catch (Exception e) { // No se encontró como fichero
				config.props = null;
				logger.error("Error at get configuration file and build a Stream to read it: " + e.getMessage(), e);
				//throw new SAPUMEConnectorException("Error at get configuration file and build a Stream to read it: " + e.getMessage(), e);
				try 
				{
					is = new BufferedInputStream(Config.class.getClassLoader().getResourceAsStream(file));
				} catch (Throwable t) {
					config.props = null;
					logger.error("Error try to get configuration file as a class: " + e.getMessage(), t);
					throw new SAPUMEConnectorException("Error try to get configuration file as a class:" + e.getMessage(), t);
				}
			}
						
			// Si quisiesemos tratar el fichero como clase se leeria asi.
			// is = Config.class.getClassLoader().getResourceAsStream(nomFichero);
			
			// Cargamos el fichero de propiedades
			config.props.load(is);
			logger.debug("The configuration file was load in memory successfully");
			
			// **************************************************************
			// 2. Se cifran aquellas propiedades que lo requieran
			// **************************************************************

			// El proceso consistira en los siguientes pasos:
			// 	- Se lee la propiedad que debe estar cifrada y se analiza si ya se encuentra cifrada
			//	- Para saber si esta o no cifrada comprobamos si su valor está entre llaves, lo cual significará que está cifrada
			// 		- Si lo está, se recupera sin mas
			// 		- Si no lo está, la propiedad se cifra y se almacena en el fichero cifrada
			// 	- Las propiedades que deben cifrarse dentro del fichero seran aquellas que comiencen por la cadena definida
			//	  por la constante CONIG_PREFIX_PARAM_CIPHER
			
			// Comenzamos recorriendo todas las propiedades
			boolean actConfFile = false;
			Enumeration<Object> properties = config.props.keys();
			while (properties.hasMoreElements())
			{
				// Recuperamos una a una las propiedades
				String propertyName = (String) properties.nextElement();
				
				// Comprobamos si es objeto de cifrado
				if (propertyName != null && propertyName.startsWith(CONFIG_PREFIX_PARAM_CIPHER))
				{
					// Obtenemos el valor del parametro
					String value = config.props.getProperty(propertyName);
                    if (value != null && !value.equals(""))
                    {
                    	// Se comprueba si está cifrada
                    	if (!SymmetricEncryption.isEncrypted(value.getBytes()))
                    	{
                    		// No está cifrado => Cifrarlo, calcular Base64 y guardarlo en fichero de config con el valor entre llaves
                            
                    		// Se obtiene el valor cifrado
                    		byte[] bytValCifrado = SymmetricEncryption.encrypt(value.getBytes());
                    		// Se codifica en Base64 y se añaaden los caracteres '{' y '}'
                    		String valCifrado = "{" + Base64.encodeBase64String(bytValCifrado) + "}";
                    		// Modificamos la propiedad para guardarlo cifrado
                    		config.props.setProperty(propertyName, new String(valCifrado));
                            // Se activa la modificacó�n del fichero (se ha de sobreescribir el valor del parámetro por el valor cifrado)
                            actConfFile = true;
                            
                    	} else {
                    		
                    		// Ya está cifrado => Decodficar Base64, y comprobar que puedes descifrar el valor
                    		
                    		// Obtenemos el valor cifrado sin los caracteres '{' y '}'
                			String pwdCifrada = value.trim().substring(1, value.length() - 1);
                			try {
        	                    // Se decodifica el Base64 y se descifra, para comprobar que esta Ok
        	            		SymmetricEncryption.decrypt(Base64.decodeBase64(pwdCifrada));
                			} catch (Exception e) {
        						// Error al descifrar el valor
                				logger.error("Error trying to decrypt value of config param " + propertyName + " with value " + pwdCifrada + ": " + e.getMessage(), e);
                				throw new SAPUMEConnectorException("Error at configuration initialization caused by not possible decrypt a config param: " + e.getMessage(), e);
               				}
                    	}
                    	
                    } else {
                    	// Si no posee valor no se haca nada con el parametro
                    }
				}
			}
			
			// **************************************************************
			// 3. Si se cifraron contraseñas, se modifica el fichero de propiedades para almacenarlas correctamente cifradas
			// **************************************************************
			
			OutputStream loOutFile = null;
			BufferedReader loIN = null;
            try
            {
            	if (actConfFile)
            	{
            		logger.debug("File will be updated with encrypted fields value");
                	// Declaramos el separador
                	final String LINE_SEPARATOR = System.getProperty("line.separator");
                	StringBuffer lsBuffer = new StringBuffer();
                	// Obtenemos el fichero
                    loIN = new BufferedReader(new FileReader(file));
                    String lsLinea = loIN.readLine();
                    // Iremos recorriendo line a linea el fichero
                    while (lsLinea != null)
                    {
                    	// Comprobamos si la linea es de un parámetro que está cifrado para actualizar el valor
                    	if (lsLinea.trim().startsWith(CONFIG_PREFIX_PARAM_CIPHER))
                    	{
                    		// Obtenemos el nombre de la propiedad, sera el nombre hasta el valor =
                    		int posIgual = lsLinea.trim().indexOf("=");
                    		String param = lsLinea.trim().substring(0, posIgual).trim();
                    		// Creamos la nueval linea a escribir, formada por el valor cifrado 
                    		lsLinea = param + "=" + config.props.getProperty(param);
                        }

                        // Escribimos linea en fichero
                        lsBuffer.append(lsLinea).append(LINE_SEPARATOR);
                        
                        // Pasamos a la linea siguiente
                        lsLinea = loIN.readLine();
                    }

                    // Guardamos el fichero
                    loOutFile = new FileOutputStream(file);
                    loOutFile.write(lsBuffer.toString().getBytes());
                    loOutFile.flush();
                }
            	
            } catch (Exception e) {
            	// Se produce un error al modificar el fichero
            	logger.error("Error trying to update configuration properties file: " + e.getMessage(), e);
				/*// No lanzamos excepcion, puede que falle si el fichero se ley� como una clase*/
				throw new SAPUMEConnectorException("Error trying to update configuration properties file: " + e.getMessage(), e);
				
            } finally {
               	// Cerrar Outpustream
               	try { loOutFile.close(); } catch (Throwable e) {}
             // Cerrar BufferedReader
               	try { loIN.close(); } catch (Throwable e) {}
            }
			            
            // Auditamos el fin del proceso
            logger.debug("End of initializacion configuration file process");
			
    	} catch (SAPUMEConnectorException e) {
    		config.props = null;
    		throw e;
		  	
    	} catch (Exception e) {
    		config.props = null;
    		logger.error("Generic error at initialize and load configuration params from properties file: " + e.getMessage(), e);
			throw new SAPUMEConnectorException("Generic error at initialize and load configuration params from properties file: " + e.getMessage(), e);
    		
        } finally {
        	try { if(is != null) is.close(); } catch (Exception e) {	}
        }
    }
    
}