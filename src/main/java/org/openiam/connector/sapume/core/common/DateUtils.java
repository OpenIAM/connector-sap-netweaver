package org.openiam.connector.sapume.core.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Clase con metodos para utilidades de fechas.
 * <br>
 * @author SIA
 */
public class DateUtils 
{	
	/*************************************************************
     **********          OBJETOS INTERNOS              ***********
     *************************************************************/
	
	public static final String DATE_MASK_DDMMYYYY = "dd/MM/yyyy";
	public static final String DATE_MASK_YYYYMMDD = "yyyy-MM-dd";
	public static final String DATE_MASK_DDMMYYYYHHMMSS = "dd/MM/yyyy HH:mm:ss";
	public static final String DATE_MASK_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_MASK_YYYYMMDDHHMMSSZ = "yyyyMMddHHmmss'Z'";
	
	
	/*************************************************************
     ***********           MÉTODOS PÚBLICOS            *********** 
     *************************************************************/

	/**
	 * Obtiene la fecha en forma de string segun la mascara que se le asigne.
	 * @param date Fecha en forma de objeto Date
	 * @param mask Formato en la que se quiere la fecha
	 * @return String con la fecha en el formato requerido
	 */
	public static String getStrFromDate(Date date, String mask)
	{
		if (date != null && mask != null)
		{
			SimpleDateFormat sdfEnt = new SimpleDateFormat(mask);
			return sdfEnt.format(date);
			
		} else {
			return "";
		}
	}

	
	/**
	 * Obtiene la fecha en forma de objeto Date a partit de una fecha en forma de string y su formato.
	 * @param date String con la fecha
	 * @param mask String con el formato en el que viene la fecha
	 * @return Fecha en el formato requerido
	 * @throws ParseException En caso de producirse alg�n error
	 */
	public static Date getDateFromStr(String date, String mask) throws ParseException
	{
		if (date != null && mask != null)
		{
			SimpleDateFormat sdfEnt = new SimpleDateFormat(mask);
			return sdfEnt.parse(date);
			
		} else {
			return new Date();
		}
	}
	
	
	/**
	 * Cambia una fecha de una máscara a otra.
	 * @param date String con la fecha en la mascara de entrada
	 * @param maskIn Mascara en la que viene la fecha de entrada
	 * @param maskOut Mascara en la que se devuelve la fecha
	 * @return String con la fecha con la nueva máscara
	 * @throws ParseException En caso de producirse algún error
	 */
	public static String changeDateMask(String date, String maskIn, String maskOut) throws ParseException
	{
		Date temp = DateUtils.getDateFromStr(date, maskIn);
		if (temp!=null)
		{
			return DateUtils.getStrFromDate(temp, maskOut);
			
		} else {
			return "";
		}
	}
	
	
	/**
	 * Obtiene un objeto Date con la fecha actual.
	 * @param exact Indica si se quiere la fecha exacta, es decir, afinando las horas,minutos,segundos y
	 * milisegundos. Si se pasa false, obtiene la fecha de hoy pero con hora 00:00'00''00'''
	 * @return Objeto Date con la fecha actual
	 */
	public static Date getCurrentDate(boolean exact)
	{
		Calendar calendar = GregorianCalendar.getInstance();
		if (!exact)
		{
			// si no se pide la fecha exacta, devolvemos la fecha con hora: 00:00'00''00'''
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
		}
		return new Date(calendar.getTimeInMillis());
	}
	
	
	/**
	 * Obtiene un objeto Date con la fecha actual mas/menos el número de días pasados.
	 * @param exact Indica si se quiere la fecha exacta, es decir, afinando las horas,minutos,segundos y
	 * milisegundos. Si se pasa false, obtiene la fecha de hoy pero con hora 00:00'00''00'''
	 * @param numDays Número de días que se desean añadir o quitar a la fecha actual
	 * @return Objeto Date con la fecha actual
	 */
	public static Date getCurrentDateModByDays(boolean exact, int numDays)
	{
		Calendar calendar = GregorianCalendar.getInstance();
		if (!exact)
		{
			// si no se pide la fecha exacta, devolvemos la fecha con hora: 00:00'00''00'''
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
		}
		calendar.add(Calendar.DATE, numDays);
		return new Date(calendar.getTimeInMillis());
	}
	
}