package org.openiam.connector.sapume.core.security;


/**
 * Clase que se encarga de realizar un cifrado/descifrado simétrico. 
 */
public class SymmetricEncryption
{
    private static final byte[] CLAVE_DES = {(byte)0x67, (byte)0x17, (byte)0x031, (byte)0xC2, (byte)0xAE, (byte)0x91, (byte)0xA7, (byte)0x0E,
		(byte)0x92, (byte)0x23, (byte)0x321, (byte)0xf5, (byte)0xBC, (byte)0x43, (byte)0x67, (byte)0x4C,
		(byte)0x46, (byte)0x76, (byte)0x453, (byte)0xD2, (byte)0xAE, (byte)0x54, (byte)0xD7, (byte)0x5F,
		(byte)0x78, (byte)0x78, (byte)0x546, (byte)0xA6, (byte)0xAC, (byte)0x16, (byte)0xC8, (byte)0x6A
    };
    
    public static boolean isEncrypted(byte[] datos) {
    	if (new String(datos).startsWith("{")) {
    		return true;
    	}
    	
    	return false;
    }
    
	public static byte[] encrypt(byte[] datos) throws Exception {
		SIACrypt loSIACrypt = new SIACrypt(CLAVE_DES);
		return loSIACrypt.cifradoDatos(datos);
	}
	
	public static byte[] decrypt(byte[] datos) throws Exception {
		SIACrypt loSIACrypt = new SIACrypt(CLAVE_DES);
		return loSIACrypt.descifradoDatos(datos);
	}
}
