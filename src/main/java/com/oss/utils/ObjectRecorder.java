/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oss.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ObjectRecorder {

    public static String recordObject(Object in, String fileName) {
        fileName = "test/" + fileName + ".xml";
        File file = new File(fileName);
        int i = 0;
        while (file.exists()) {
            i++;
            fileName = fileName.replace(".ext", "");
            fileName = fileName + "-" + i + ".xml";
            file = new File(fileName);
        }
        String xml = getObjectXML(in);
        PrintWriter writer;
        try {
            writer = new PrintWriter(fileName, "UTF-8");
            writer.println(xml);
            writer.close();
        } catch (Exception ex) {
            System.out.println("Unable to record object: " + in.toString());
        }
        return xml;
    }

    public static String getObjectXML(Object obj) {
        XStream xstream = new XStream(new DomDriver());
        String xml = xstream.toXML(obj);
        return xml;
    }

    public static Object getObject(String filename) {
        filename = "test/" + filename + ".xml";
        String xml = readFile(filename);
        XStream xstream = new XStream(new DomDriver());
        final Object fromXML = xstream.fromXML(xml);
        return fromXML;
    }

    public static String readFile(String filename) {
        String content = null;
        File file = new File(filename); //for ex foo.txt

        try {
            FileReader reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            content = new String(chars);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

//    public static File getfileFromClassPath(String filename) throws IOException {
//        System.out.println("Looking for file in CLASSPATH file: " + filename);
//        InputStream resourceAsStream = ObjectRecorder.class.getClassLoader().getResourceAsStream(filename);
//        System.out.println("Ressourcestream : " + resourceAsStream);
//        Resource resource = new ClassPathResource(filename);
//        File file = resource.getFile();
//        if ((file == null) || (!file.exists())) {
//            System.out.println("UNABLE TO find FILE: " + filename);
//            throw new IOException("UNABLE TO find FILE: " + filename);
//        }
//        return file;
//    }

    public static String getStringFileFromClassPath(String filename) throws IOException {
        InputStream is = ObjectRecorder.class.getClassLoader().getResourceAsStream(filename);
        if (is==null)
                System.out.println("Unable to find file in CLASSPATH file: " + filename);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        isr.close();
        is.close();
        return sb.toString();
    }



}
