/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtable.generic;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
public @interface DynamicAnnotation {
    int order();
    boolean turnedOn();
    String heading();
}
    
