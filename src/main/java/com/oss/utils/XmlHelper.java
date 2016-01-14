/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oss.utils;

/**
 *
 * @author g46737
 */
public class XmlHelper {

    public static String simpleXMLFind(String xml, String tag) {
        String split1 = xml.split("<" + tag + ">")[1];
        String spit2 = split1.split("</" + tag + ">")[0];
        return spit2;
    }

    public static String simpleXMLReplace(String xml, String tag, String newValue) {
        return null;
    }

    public static String[] multipleXMLFind(String xml, String tag) {
        String startTag = "<" + tag + ">";
        String endTag = "</" + tag + ">";
        final String[] split = xml.split(startTag);
        for (String part : split) {
            part = part.replace(part, startTag);
            part = part.replace(part, endTag);
        }
        return split;
    }

}
