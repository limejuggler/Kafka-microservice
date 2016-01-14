/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oss.utils;

/**
 *
 * @author Tore
 */
public class Converter {

    public static Double stringToDouble(String in) {
        if (in==null) return null;
        Double aDouble =null;
        try {
            aDouble = Double.parseDouble(in);
        } catch (Exception ex) {
        }
        return aDouble;
    }

    public Double roundDouble(Double in) {
        if (in==null) return null;
        return Math.round(in * 100) / 100.0d;
    }
}
