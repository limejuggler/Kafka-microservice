/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oss.app;

import java.awt.Color;
import javax.swing.UIManager;

/**
 *
 * @author g46737
 */



public class GuiUtils {
    
    public static Color lightBrown = new Color(252, 242, 206);
    
    public static void setLookAndFeel() {
        int lookAndFeel = 1;
        try {
            if (lookAndFeel == 0) {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
               
            }
            if (lookAndFeel == 1) {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                
            }
        } catch (Exception e) {
            System.out.print("Error: Could not find look and feel\n " + e.getMessage());
        }
    }
}
