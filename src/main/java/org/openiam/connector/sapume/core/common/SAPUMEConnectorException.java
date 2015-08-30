package org.openiam.connector.sapume.core.common;

/**
 * Clase que representa el tipo de excepciones generales de la aplicación.
 */
public class SAPUMEConnectorException extends Exception {

	/*************************************************************
     **********          OBJETOS INTERNOS              ***********
     *************************************************************/
	
	private static final long serialVersionUID = 1L;

	/**
	 * Código de error
	 */
	private int errorCode;
	
	
	/**********************************************************
     **********          CONSTRUCTORES              ***********
	 **********************************************************/
	
	/**
	 * Constructor por defecto.
	 */
	public SAPUMEConnectorException() 
	{
		super();
	}	
	
	/**
	 * Constructor compuesto.
	 * <br>
	 * @param msg Mensaje de error
	 */
	public SAPUMEConnectorException(String msg) 
	{
		super(msg);
	}
	
	/**
	 * Constructor compuesto.
	 * <br>
	 * @param code Código de error
	 * @param msg Mensaje de error
	 */
	public SAPUMEConnectorException(int code, String msg) 
	{
		super(msg);
		this.errorCode = code;
	}
	
	/**
	 * Constructor compuesto.
	 * <br>
	 * @param ex Objeto que representa una excepción
	 */
	public SAPUMEConnectorException(Throwable ex) 
	{
		super(ex);
	}
	
	/**
	 * Constructor compuesto.
	 * <br>
	 * @param msg Mensaje de error
	 * @param ex Objeto que representa una excepción
	 */
	public SAPUMEConnectorException(String msg, Throwable ex) 
	{
		super(msg, ex);
	}
	
	/**
	 * Constructor compuesto.
	 * <br>
	 * @param code Código de error
	 * @param msg Mensaje de error
	 * @param ex Objeto que representa una excepción
	 */
	public SAPUMEConnectorException(int code, String msg, Throwable ex) 
	{
		super(msg, ex);
		this.errorCode = code;
	}

	
	/*************************************************************
     ***********           MÉTODOS PÚBLICOS            ***********
     *************************************************************/
	
	/**
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * @param wsCode the errorCode to set
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
