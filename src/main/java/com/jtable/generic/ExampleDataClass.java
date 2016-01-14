/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtable.generic;

/**
 *
 * @author Tore
 */
public class ExampleDataClass {   
    String field1;    
    String field2;

    public ExampleDataClass(String field1, String field2) {
        this.field1 = field1;
        this.field2 = field2;
    }

    
    @DynamicAnnotation(heading="Field1", order=1, turnedOn=true)
    public String getField1() {
        return field1;
    }
    
    @DynamicAnnotation(heading="Field1", order=2, turnedOn=true)
    public String getField2() {
        return field2;
    }    
    
}
