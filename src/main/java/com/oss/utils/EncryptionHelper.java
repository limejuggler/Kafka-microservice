/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oss.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author g46737
 */
public class EncryptionHelper {

//    String key = "ThisIsOSecretKey";
//    Key aesKey;
//
//    public EncryptionHelper() {
//        try {
//            // aesKey = buildKey(key);
//        } catch (Exception ex) {
//            GuiHelper.exitWithError("Unable to get key!");
//        }
//
//    }
//
//    public String encryptAes(String in) {
//        try {
//            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
//            Cipher cipher = Cipher.getInstance("AES");
//            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
//            byte[] encrypted = cipher.doFinal(in.getBytes());
//            return new String(encrypted);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//        }
//        return null;
//    }
//
//    public String decryptAes(String in) {
//        try {
//            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
//            Cipher cipher = Cipher.getInstance("AES");
//            cipher.init(Cipher.DECRYPT_MODE, aesKey);
//            String decrypted = new String(cipher.doFinal(in.getBytes()));
//            return decrypted;
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//        }
//        return null;
//    }
//
//    private Key buildKey(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//        byte[] salt = "ThisIsOSecretKey".getBytes("UTF8");
//        Key key = new SecretKeySpec(salt, 0, 16, "AES");
//        return key;
//    }
//
//    private Key buildKeyOld(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//        MessageDigest digester = MessageDigest.getInstance("SHA-256");
//        digester.update(password.getBytes("UTF-8"));
//        byte[] key = digester.digest();
//        SecretKeySpec spec = new SecretKeySpec(key, "AES");
//        return spec;
//    }

//     public String encrypt(String in) {   
//              return new BASE64Encoder().encode(in.getBytes());
//     }
//     
//     public String decrypt(String in) {   
//         BASE64Decoder decoder = new BASE64Decoder();
//        try {
//            return new String(decoder.decodeBuffer(in));
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        return null;
//     }
    private static final String ALGORITHM = "AES";
    private static final String myEncryptionKey = "ThisIsFoundation";
    private static final String UNICODE_FORMAT = "UTF8";

    public static String encrypt(String valueToEnc) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encValue = c.doFinal(valueToEnc.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encValue);
        return encryptedValue;
    }

    public static String decrypt(String encryptedValue) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedValue);
        byte[] decValue = c.doFinal(decordedValue);//////////LINE 50
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }

    private static Key generateKey() throws Exception {
        byte[] keyAsBytes;
        keyAsBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
        Key key = new SecretKeySpec(keyAsBytes, ALGORITHM);
        return key;
    }

}
