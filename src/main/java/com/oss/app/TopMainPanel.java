/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oss.app;

/**
 *
 * @author g46737
 */
public class TopMainPanel extends javax.swing.JPanel {

    /**
     * Creates new form TopMainPanel
     */
    public TopMainPanel(String appName) {
        initComponents();
        appNameLabel.setText(appName);    
    }
    
    
 public void setTimerText(String time) 
 {
     timejLabel.setText(time);
 }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        appNameLabel = new javax.swing.JLabel();
        timejLabel = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(20, 20));
        setPreferredSize(new java.awt.Dimension(20, 20));
        setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.5;
        add(appNameLabel, gridBagConstraints);

        timejLabel.setText("time");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        add(timejLabel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel appNameLabel;
    private javax.swing.JLabel timejLabel;
    // End of variables declaration//GEN-END:variables
}