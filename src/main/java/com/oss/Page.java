/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oss;

import com.oss.utils.Helper;
import java.util.ArrayList;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

/**
 *
 * @author g46737
 */
public class Page {
    
    @Element (required=false)
    public String id;
    
    @Element (required=false)
    public String pageName;
        
    @Element (required=false)
    public String pageViewClass;
    
    @Element (required=false)
    public String parent;
    
    @Element (required=false)
    public String loadAtStartUp;
    
    @Element (required=false)
    public String doShow;
    
   // The pages
    @ElementList(required=false, inline = true)
    public ArrayList<String> parameters = new ArrayList();

    public Page() {
    }

    public Page(String id, String pageName, String pageViewClass) {
        this.pageName = id;
        this.id = pageName;
        this.pageViewClass = pageViewClass;
    }
    
    public Page(String id, String pageName, String pageViewClass, String parent, String firstParameter, boolean loadAtStartUp) {
        this.pageName = id;
        this.id = pageName;
        this.pageViewClass = pageViewClass;
        this.parent = parent;
        this.parameters.add(firstParameter);
        this.loadAtStartUp = Helper.mapBooleanToString(loadAtStartUp);
    }

    
    
    public ArrayList<String> getParameters() {
        return parameters;
    }
    
    
    public String getParameter(int index) {
        if ((parameters!=null) && (parameters.size()>index))
            return parameters.get(index);
        else return "";
    }

   public void setParameter(int index, String in) {
       if (parameters!=null)  
            parameters.set(index, in);
    }

    
    @Override
    public String toString() {
        return id+", "+pageName;
    }


    

    
    
    
}
