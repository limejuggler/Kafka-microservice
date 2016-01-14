/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oss;

import com.oss.utils.Helper;
import com.oss.utils.ObjectRecorder;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

/**
 *
 * @author g46737
 */
@Root
public class GenericAppDescriptor {

    @Element(required = false)
    public String appName;

    @Element(required = false)
    public int width;

    @Element(required = false)
    public int height;

    @Element(required = false)
    public Page startPage;

    // The pages
    @ElementList(required = false, inline = true)
    public ArrayList<Page> pages = new ArrayList();

    public ArrayList<Page> getPages() {
        return pages;
    }

    public ArrayList<String> getPagesId() {
        ArrayList list = new ArrayList();
        for (Page page : pages) {
            list.add(page.id);
        }
        return list;
    }

    public ArrayList<String> getPagesMappings() {
        ArrayList<String> mappings = new ArrayList();
        for (Page page : pages) {
            if (!mappings.contains(page.pageViewClass)) {
                mappings.add(page.pageViewClass);
            }
        }
        return mappings;
    }

    public void addPage(String id, String name, String pageViewClass, String parent, String firstParameter, boolean loadAtStartUp) {
        Page page = new Page(id, name, pageViewClass, parent, firstParameter, loadAtStartUp);
        pages.add(page);
    }

    public Page getPageFromId(String name) {
        for (Page page : pages) {
            if (page.id.equals(name)) {
                return page;
            }
        }
        return null;
    }

    public String toXML() {
        String xmlString;
        Serializer serializer = new Persister();
        try {
            StringWriter writer = new StringWriter();
            serializer.write(this, writer);
            xmlString = writer.getBuffer().toString();
        } catch (Exception ex) {
            xmlString = "Unable to generate XML. " + ex.getMessage();
            JOptionPane.showMessageDialog(null, xmlString, "XML Error", JOptionPane.ERROR_MESSAGE);
        }
        return xmlString;
    }

    public void toXMLFile(String fileName) {
        String xmlString;
        Serializer serializer = new Persister();
        try {
            File resultFile = new File(fileName + ".xml");
            serializer.write(this, resultFile);
        } catch (Exception ex) {
            xmlString = "Unable to save XML file. " + ex.getMessage();
            JOptionPane.showMessageDialog(null, xmlString, "XML Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static GenericAppDescriptor fromXML(String xml) {
        GenericAppDescriptor genericAppDescriptor = null;
        try {
            // Check if the XML is empty:
            if (xml.startsWith("</>")) {
                return new GenericAppDescriptor();
            }
            Serializer serializer = new Persister();
            StringReader reader = new StringReader(xml);
            genericAppDescriptor = serializer.read(GenericAppDescriptor.class, reader);
        } catch (Exception ex) {
            String err = "Unable to parse XML. " + ex.getMessage();
            JOptionPane.showMessageDialog(null, err, "XML Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return genericAppDescriptor;
    }

    public static GenericAppDescriptor fromXMLFile(String fileName) {
        fileName = fileName+".xml";
        GenericAppDescriptor genericAppDescriptor = null;
        try {
            Serializer serializer = new Persister();
            System.out.println("Loading app descriptor " + fileName);
            String fileString = ObjectRecorder.getStringFileFromClassPath(fileName);         
//            File source = new File(fileName);
//            if (!source.exists()) {
//                //load from classpath.
//                System.out.println("Loading app descriptor from classpath");
//                String stringSource = ObjectRecorder.readStringFromJARFile(fileName);
//                if (Helper.isEmptyString(stringSource)) {
//                    System.err.println("Unable to load config file");
//                    System.exit(1);
//                }
//                System.out.println("!!!! Found Config file from classpath");
//                genericAppDescriptor = serializer.read(GenericAppDescriptor.class, stringSource);
//            }
//            else
                genericAppDescriptor = serializer.read(GenericAppDescriptor.class, fileString);
        } catch (Exception ex) {
            String err = "Unable to parse XML. " + ex.getMessage();
            JOptionPane.showMessageDialog(null, err, "XML Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return genericAppDescriptor;
    }

    @Override
    public String toString() {
        return "GenericAppDescriptor{" + "appName=" + appName + ", width=" + width + ", height=" + height + ", startPage=" + startPage + ", pages=" + pages + '}';
    }

}
