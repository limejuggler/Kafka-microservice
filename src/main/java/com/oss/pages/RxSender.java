/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oss.pages;

import com.nordea.pubsubapi.Connector;
import com.nordea.pubsubapi.Publisher;
import com.oss.Page;
import java.awt.Color;
import java.util.Random;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author g46737
 */
public class RxSender extends javax.swing.JPanel {

    Publisher pub;

    public RxSender(Page page) {
        pub = Publisher.getInstance();
        initComponents();
        DefaultCaret caret = (DefaultCaret) jTextArea1.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        topicName.setText(Connector.default_topic);
    }

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

        jButton1.setText("Send");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        topicName.setMinimumSize(new java.awt.Dimension(6, 24));
        topicName.setPreferredSize(new java.awt.Dimension(120, 24));
        jPanel1.add(topicName);

        add(jPanel1, java.awt.BorderLayout.NORTH);
    }// </editor-fold>//GEN-END:initComponents

    private volatile boolean execute;

    public void startExecuting() {
        this.execute = true;
        Random rn = new Random();
        Thread thread = new Thread() {
            int i = 0;
            @Override
            public void run() {
                while (execute) {
                    i++;
                    try {
                        Thread.sleep(100);
                        //final int nextInt = rn.nextInt(10000);
                        pub.send(topicName.getText(), i + "");
                        jTextArea1.append(topicName.getText() + " : " + i + "\n");
                        jTextArea1.repaint();
                    } catch (InterruptedException ex) {
                        System.out.println("Exection sending message" + ex);
                    }
                }
            }
        };

        if (!thread.isAlive()) {
            thread.start();
        }
        
        jButton1.setBackground(Color.RED);
    }

    public void stopExecuting() {
        this.execute = false;
        jButton1.setBackground(Color.GRAY);
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (!execute) {
            startExecuting();

        } else {
            stopExecuting();
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField topicName;
    // End of variables declaration//GEN-END:variables

}
