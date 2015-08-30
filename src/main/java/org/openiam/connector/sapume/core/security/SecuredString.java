package org.openiam.connector.sapume.core.security;

import java.io.UnsupportedEncodingException;
import java.lang.management.ManagementFactory;
import java.security.GeneralSecurityException;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;


public class SecuredString
{
	/*************************************************************
	 **********          OBJETOS INTERNOS              ***********
	 *************************************************************/

	private static final String SECRET_KEY_MD5_AND_DES = "PBEWithMD5AndDES";

	private byte[] salt = null;
	private byte[] cipheredBytes = null;


	/*************************************************************
	 ***********             CONSTRUCTORES             *********** 
	 *************************************************************/

	public SecuredString()throws UnsupportedEncodingException
	{
		this.salt = new byte[8];
		new Random(System.currentTimeMillis()).nextBytes(salt);
	}

	public SecuredString(char[] value) throws UnsupportedEncodingException, GeneralSecurityException
	{
		this.salt = new byte[8];
		new Random(System.currentTimeMillis()).nextBytes(salt);
		encrypt(charToByte(value));
	}

	/*************************************************************
	 ***********           MÉTODOS PÚBLICOS            ***********
	 *************************************************************/

	/*
	 * Get the content of this string as char[]
	 */
	public void setValue(char[] value) throws GeneralSecurityException
	{
		encrypt(charToByte(value));
	}

	/*
	 * Get the content of this string as char[]
	 */
	public char[] getClearValue() throws GeneralSecurityException
	{
		return byteToChar(decrypt());
	}


	/*************************************************************
	 ***********           MÉTODOS PRIVADOS            ***********
	 *************************************************************/

	/*
	 * Meta password is used to encrypt the content of this object with.  It must be hard to guess
	 * by an intruder who can dump memory and has access to source code.  We cannot store it.
	 * 
	 */
	private static char[] getMetaPassword()
	{
		long jvmStartMillis = (ManagementFactory.getRuntimeMXBean().getStartTime());
		char[] result = LongToChar.convert(jvmStartMillis);
		return result;
	}

	/*
	 * Convert char array to byte array without charset interpretation.
	 */
	private static byte[] charToByte(char[] chars)
	{
		byte[] result = new byte[chars.length*2];
		for (int i=0; i < chars.length; i++) {
			result[i*2] = (byte) (chars[i] >> 8);
			result[i*2+1] = (byte) chars[i];
		}
		return result;
	}

	/*
	 * Convert byte array to char array without charset interpretation.
	 */
	private static char[] byteToChar(byte[] bytes)
	{
		char[] result = new char[bytes.length/2];
		for (int i=0; i < result.length; i++) {
			result[i] = (char) ((bytes[i*2] << 8) + bytes[i*2+1]);
		}
		return result;
	}

	/*
	 * Encode with 2-way password protected PBE algorithm.
	 */
	private void encrypt(byte[] cleartext) throws GeneralSecurityException
	{
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(SECRET_KEY_MD5_AND_DES);
		SecretKey key = keyFactory.generateSecret(new PBEKeySpec(getMetaPassword()));
		Cipher pbeCipher = Cipher.getInstance(SECRET_KEY_MD5_AND_DES);
		pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(salt, 20));
		cipheredBytes = pbeCipher.doFinal(cleartext);
	}

	/*
	 * Decode with 2-way password protected PBE algorithm.
	 * Symmetric algorithm to encryptPbe() above.
	 */
	private byte[] decrypt() throws GeneralSecurityException
	{
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(SECRET_KEY_MD5_AND_DES);
		SecretKey key = keyFactory.generateSecret(new PBEKeySpec(getMetaPassword()));
		Cipher pbeCipher = Cipher.getInstance(SECRET_KEY_MD5_AND_DES);
		pbeCipher.init(Cipher.DECRYPT_MODE, key, new PBEParameterSpec(salt, 20));
		return pbeCipher.doFinal(cipheredBytes);
	}

}
