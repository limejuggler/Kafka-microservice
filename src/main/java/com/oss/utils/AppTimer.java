/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oss.utils;

import com.oss.app.GenericApp;
import com.oss.app.TopMainPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Tore
 */
public class AppTimer {

    public AppTimer() {
        startTimer();
    }

    public Date appTime = new Date();

    private void startTimer() {
        final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        int interval = 1000; // 1s

        new Timer(interval, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appTime = new Date();
                TopMainPanel topMainPanel = (TopMainPanel) GenericApp.getPanelByName("TopMainPanel");
                if (topMainPanel != null) {
                    topMainPanel.setTimerText(dateFormat.format(appTime.getTime()));
                }
            }
        }).start();
   // Thread.currentThread().join();
    }

}
