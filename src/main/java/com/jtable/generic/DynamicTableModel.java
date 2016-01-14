/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtable.generic;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Tore
 */
public class DynamicTableModel extends AbstractTableModel {
    
    Class type;
    List<Object> typeList;
    
    SortedMap<Integer, Method> declaredGetMethodsOrdered = new TreeMap();
     public  ArrayList<Method> columnValues = new ArrayList();
    public     ArrayList<String> columnStringValues = new ArrayList();

    public synchronized void setModel(List<Object> typeList, Class ofType) {   
        this.typeList =  typeList;
        this.type = ofType;
        boolean usingAnnotations = false;
        boolean clearFlag = true;
        
                
  //      if (typeList != null) {
            declaredGetMethodsOrdered.clear();
            columnValues.clear();
            columnStringValues.clear();

            // List of get methods, which will be shown in order by annotations.
            final Method[] declaredMethods = ofType.getDeclaredMethods();
            for (Method method : declaredMethods) {
                if (method.getName().startsWith("get")) {
                    com.jtable.generic.DynamicAnnotation annotation = method.getAnnotation(com.jtable.generic.DynamicAnnotation.class);
                    
                    if (annotation != null) {
                        usingAnnotations = true;
                        if (clearFlag) {
                            declaredGetMethodsOrdered.clear();
                            clearFlag = false; // Only clear once if using annotations.
                        }
                        if (annotation.turnedOn()) {
                            declaredGetMethodsOrdered.put(annotation.order(), method);
                        }                     
                    }
                   
                   // NO annotation
                   else 
                        {  
                           if (!(usingAnnotations)) {
                            int i = 0;
                            if (!(declaredGetMethodsOrdered.isEmpty())) i = declaredGetMethodsOrdered.lastKey()+1;
                            declaredGetMethodsOrdered.put(i, method);
                            }
                        }
                }
    //        }
        }

        for (Map.Entry<Integer, Method> entry : declaredGetMethodsOrdered.entrySet()) {
            if (entry.getValue() != null) {
                Method value = entry.getValue();
                columnValues.add(value);
                com.jtable.generic.DynamicAnnotation annotation = value.getAnnotation(com.jtable.generic.DynamicAnnotation.class);
                if (annotation!=null) columnStringValues.add(annotation.heading());
                else columnStringValues.add(value.getName().split("get")[1]);
            }
        }
    }

    @Override
    public int getRowCount() {
        int rows = 0;
        try {
            if (typeList.size() > 0) {
                rows = typeList.size();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rows;
    }

    @Override
    public int getColumnCount() {
        int cols = 0;
        try {
            if (columnValues.size() > 0) {
                cols = columnValues.size();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return cols;
    }

    @Override
    public String getColumnName(int i) {
        String name = "";
        try {
            if (columnValues.size() > 0) {
                name = columnStringValues.get(i);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            name = "Not Found";
        }
        return name;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object invoke = null;
        if (rowIndex>-1) {
        Object objectLine = typeList.get(rowIndex);
        try {
            invoke = (columnValues.get(columnIndex)).invoke(objectLine, new Object[0]);
        } catch (Exception ex) {
            IO("Unable to invoke: " +  columnValues.get(columnIndex) + " on object" + objectLine);
            //ex.printStackTrace();
        }
        }
        return invoke;
    }
    
    public void updateTable () 
    {
        fireTableDataChanged();
    }
    
    public void IO(String in) 
    {
        System.out.println(in);
    }
    
}
