/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oss.utils;

import com.oss.app.GenericApp;

/**
 *
 * @author g46737
 */
public class GuiHelper {
   
    public static void addLogMessage(String in)
    {
       GenericApp.addLogMessage(Helper.getCurrTime() + "   " +  in);  
    }
    
     public static void exitWithError(String in)
    {
        System.out.println(in);
        System.exit(1);
    }
}
