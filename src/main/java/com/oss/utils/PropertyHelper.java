/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oss.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author g46737
 */
public class PropertyHelper {

    String propName = "adaptiv.prop";
    public String user;
    public String pw;
    public String endpoint;
    public String mailTo;
    

    EncryptionHelper encryptionHelper  = new EncryptionHelper();
    
    public void loadParams()  {
        Properties props = new Properties();
        InputStream is = null;
        try {
            File f = new File(propName);
            is = new FileInputStream(f);
        } catch (Exception e) {
            is = null;
        }
        try {
            if (is == null) {
                is = getClass().getResourceAsStream("propName");
            }
            props.load(is);
            endpoint = props.get("endpoint").toString();
            user = encryptionHelper.decrypt(encryptionHelper.decrypt(props.get("user").toString()));
            pw =  encryptionHelper.decrypt(encryptionHelper.decrypt(props.get("pw").toString()));    
            mailTo = props.get("mailTo").toString();
        } catch (Exception e) {
            System.err.println("Unable to load system properties " + e.getMessage());
        }
    }

    public void saveProp(String endpoint, String user, String pw, String mailTo) {
        try {
            Properties props = new Properties();
            props.setProperty("endpoint", endpoint);
            props.setProperty("user", encryptionHelper.encrypt(encryptionHelper.encrypt(user)));
            props.setProperty("pw", encryptionHelper.encrypt(encryptionHelper.encrypt(pw)));
            props.setProperty("mailTo", mailTo);
            File f = new File(propName);
            OutputStream out = new FileOutputStream(f);
            props.store(out, "Saved prop file.");
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
