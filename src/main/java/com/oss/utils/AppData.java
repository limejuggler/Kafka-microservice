/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oss.utils;


import com.oss.app.GenericApp;
import java.util.ArrayList;
import java.util.HashMap;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.Root;

/**
 *
 * @author Tore
 */
@Root
public class AppData {
    private static String VersionNr = "1.0";
    
    public AppData() {
        inititiateSettingsKey();
    }
    static AppData appData;
    public AppTimer apptimer = new AppTimer();

    public static AppData getInst() {
        if (appData == null) {
            appData = new AppData();
            appData.addToHistLog("Application data started.");
        }
        return appData;
    }
    
        public ArrayList<HistoryItem> historyLog = new ArrayList();

    public void addToHistLog(String event) {
//        GenericApp.addLogMessage(event);
//        HistoryItem hisItem = new HistoryItem(Helper.getDateWithTime(new Date()), event);
//        historyLog.add(hisItem);
//        JPanel histPage = GenericApp.getPanelByName("History");
//        if (histPage != null) {
//            ((HistoryPage) histPage).updateTable();
//        }
    }
    
    public void reset() {
      
    }
    
  
    
   
    static String idName = "AppData";

    public void serialize() throws Exception {
        try {
          
        } catch (Exception ex) {
            GenericApp.addLogMessage("Unable to load saved data from file. :" + ex.getMessage());
        }
    }

    public void deSerialize() {
        try {
            
        }        
         catch (Exception ex) {
            GenericApp.addLogMessage("Unable to load saved data from file. :" + ex.getMessage());
        }
    }
    
    
    
    public String getVersion() {
        return VersionNr;
    }
    
    @ElementMap(entry = "key", value = "value", attribute = true, inline = true)
    public HashMap<String, String> settings = new HashMap();
    
    public void inititiateSettingsKey() {
        settings.put("Test", "1000");       
    }

    public void setSetting(String key, String value) {
        settings.put(key, value);
        GenericApp.addLogMessage("Setting " + key + " ->" + value);
        performResetApp();
    }

    public void performResetApp() {
    }


    
     

}
