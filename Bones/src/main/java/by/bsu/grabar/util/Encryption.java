package by.bsu.grabar.util;

import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The type Encryption.
 */
public class Encryption {
    private final static Logger LOG = Logger.getLogger(Encryption.class);

    /**
     * Encrypt string.
     *
     * @param value the value
     * @return the string
     */
    public static String encrypt(String value) {
        String password = null;
        if(value == null) {return null;}
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(value.getBytes(), 0, value.length());
            password = new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            LOG.error("NoSuchAlgorithmException exception");
        }
        return password;
    }
}
