package org.openiam.connector.sapume.core.security;

import javax.crypto.Cipher;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Clase nativa para la criptografía de datos. 
 */
public class SIACrypt
{
    protected final String ALGORITHM = "DES/CBC/PKCS5Padding";
    protected final String CIPHER_ALG = "DES";
    protected final int lenBloq = 8;
    private byte[] bKey;
    private IvParameterSpec oIv;

    public SIACrypt(byte[] abyte0) throws Exception {
        bKey = "0".getBytes();
        oIv = null;

        try {
            bKey = abyte0;

            byte[] abyte1 = new byte[8];

            for (int i = 0; i < 8; i++)
                abyte1[i] = 0;

            oIv = new IvParameterSpec(abyte1);
        } catch (Exception exception) {
            throw exception;
        }
    }

    public byte[] cifradoDatos(byte[] abyte0) throws Exception {
        Object obj = null;
        Object obj2 = null;
        Object obj4 = null;
        byte[] abyte2 = null;
        Object obj6 = null;

        try {
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            DESKeySpec deskeyspec = new DESKeySpec(bKey);
            SecretKeySpec secretkeyspec = new SecretKeySpec(deskeyspec.getKey(),
                    "DES");
            int i = abyte0.length % 8;
            i = 8 - i;

            byte[] abyte3 = new byte[abyte0.length + i];
            abyte3[(abyte0.length + i) - 1] = (byte) i;

            for (int j = 0; j < abyte0.length; j++)
                abyte3[j] = abyte0[j];

            cipher.init(1, secretkeyspec, oIv);

            abyte2 = cipher.doFinal(abyte3);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        } finally {
            Object obj1 = null;
            Object obj3 = null;
            Object obj5 = null;
            Object obj7 = null;
        }

        return abyte2;
    }

    public byte[] descifradoDatos(byte[] abyte0) throws Exception {
        Object obj = null;
        Object obj2 = null;
        Object obj4 = null;
        Object obj6 = null;
        Object obj8 = null;
        byte[] abyte3 = null;

        try {
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            DESKeySpec deskeyspec = new DESKeySpec(bKey);
            SecretKeySpec secretkeyspec = new SecretKeySpec(deskeyspec.getKey(),
                    "DES");
            byte[] abyte2 = abyte0;
            cipher.init(2, secretkeyspec, oIv);

            byte[] abyte1 = cipher.doFinal(abyte2);
            int i = abyte1.length - abyte1[abyte1.length - 1];
            abyte3 = new byte[i];

            for (int j = 0; j < i; j++)
                abyte3[j] = abyte1[j];
        } catch (Exception exception) {
            throw exception;
        } finally {
            Object obj1 = null;
            Object obj3 = null;
            Object obj5 = null;
            Object obj7 = null;
            Object obj9 = null;
        }

        return abyte3;
    }
}