/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oss;


import java.util.ArrayList;

/**
 *
 * @author g46737
 */
public class GenericAppUtil {

    static public GenericAppDescriptor loadAppDescriptor(String appName) {
        GenericAppDescriptor fromXMLFile = GenericAppDescriptor.fromXMLFile(appName);
        return fromXMLFile;
    }

    public static GenericAppDescriptor createNewApplication(String appName) {
        GenericAppDescriptor appDescriptor = new GenericAppDescriptor();
        appDescriptor.appName = appName;
        appDescriptor.width = 1200;
        appDescriptor.height = 800;
    
        // Pages.
        ArrayList<Page> pages = new ArrayList();
        Page page = new Page("1","OraclePage", "com.oss.pages.SqlPage");
    //    Page ResetGroupFixer = new Page("Reset Group Fixer", "com.oss.pages.ResetGroupFixer");
        Page JdbcPage = new Page("2","Jdbc", "com.oss.pages.Jdbc", "Start", "select * from", false);
 
//        Page settingsPage = new Page("Settings", "com.oss.pages.SettingsPage");
//        Page historyPage = new Page("History", "com.oss.pages.HistoryPage");
                
        pages.add(page);
        pages.add(JdbcPage);
        //pages.add(ResetGroupFixer);
       // pages.add(historyPage);
       // pages.add(settingsPage);

        appDescriptor.pages=pages;
        
        appDescriptor.startPage = page;
        return appDescriptor;
        
        // TO Persist:
        //appDescriptor.toXMLFile(appName);   
    }
}
