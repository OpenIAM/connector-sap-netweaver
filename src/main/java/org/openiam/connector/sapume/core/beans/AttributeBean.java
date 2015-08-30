package org.openiam.connector.sapume.core.beans;


/**
 * Clase que representa a un atributo de un usuario.
 * <br>
 * @author SIA
 */
public class AttributeBean {
	
	
	/*************************************************************
     **********          OBJETOS INTERNOS              ***********
     *************************************************************/
	
    /** 
     * Nombre del atributo 
     */
    private String attName = null;
    
    /**
     * Valor del atributo. Utilizar para establecer el valor cuando es un atributo simple
     */
    private Object attValue = null;
        
    
    /**********************************************************
     **********          CONSTRUCTORES              ***********
	 **********************************************************/
	
	/**
	 * Constructor por defecto de la clase.
	 */
	public AttributeBean()
	{
	
	}
		
	/**
	 * Constructor compuesto para atributos simples. 
	 * @param name Nombre del atributo
	 * @param value Valor del atributo
	 */
	public AttributeBean(String name, Object value)
	{
		this.attName = name;
		this.attValue = value;
	}
    	
	
	/*************************************************************
     ***********           MÉTODOS PÚBLICOS            ***********
     *************************************************************/

	/**
	 * @return the attName
	 */
	public String getAttName() {
		return attName;
	}


	/**
	 * @param attName the attName to set
	 */
	public void setAttName(String attName) {
		this.attName = attName;
	}


	/**
	 * @return the attValue
	 */
	public Object getAttValue() {
		return attValue;
	}


	/**
	 * @param attValue the attValue to set
	 */
	public void setAttValue(Object attValue) {
		this.attValue = attValue;
	}
		
}
