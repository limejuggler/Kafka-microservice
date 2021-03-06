/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oss.pages;

import com.nordea.pubsubapi.Connector;
import com.nordea.pubsubapi.Subscriber;
import com.oss.Page;
import java.awt.Color;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.DefaultCaret;
//import com.nordea.pubsubmicroservice.

/**
 *
 * @author g46737
 */
public class RxReciever extends javax.swing.JPanel {

    /**
     * Creates new form RxReciever
     */
    public RxReciever(Page page) {
        initComponents();
        DefaultCaret caret = (DefaultCaret)jTextArea1.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        topicName.setText(Connector.default_topic); 
    }
    
    private volatile boolean running = false;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        topicName = new javax.swing.JTextField();

        setLayout(new java.awt.BorderLayout());

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jButton1.setText("Receive");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        topicName.setMinimumSize(new java.awt.Dimension(120, 24));
        topicName.setPreferredSize(new java.awt.Dimension(120, 24));
        jPanel1.add(topicName);

        add(jPanel1, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

  

        
    Subscriber sub =  new Subscriber();
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        running = !running;
        if (running)
                    jButton1.setBackground(Color.RED);
        else 
                    jButton1.setBackground(Color.GRAY);
                
        Thread t = new Thread()
        {
            @Override
            public void run()
            {           
                int nr =0;
                while(running)
                {                    
                    final ArrayList<String> messages = sub.getMessages(topicName.getText());
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(RxReciever.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    for (String message : messages)
                    {
                        nr++;
                        jTextArea1.append(nr + " : " + message + "\n");
                        jTextArea1.repaint();                        
                    }
                }
                sub.unSubScribe();
            }
        };
        t.start();
    }//GEN-LAST:event_jButton1ActionPerformed


    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField topicName;
    // End of variables declaration//GEN-END:variables
}
