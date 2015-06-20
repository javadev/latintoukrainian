/*
 * $Id$
 *
 * Copyright 2015 Valentyn Kolesnikov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.latintoukrainian;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.WindowEvent;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * LatintoukrainianApp.
 *
 * @author javadev
 * @version $Revision$ $Date$
 */
public class LatintoukrainianApp extends javax.swing.JFrame {

    private void generateResult() throws NumberFormatException {
        jTextArea6.setText(LatinToUkrainian.generateUkr(jTextArea1.getText()));
    }

    /** Creates new form */
    public LatintoukrainianApp() {
        initComponents();
        XMLDecoder d;
        String x = null;
        String y = null;
        String height = null;
        String width = null;
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        try {
           d = new XMLDecoder(new BufferedInputStream(new FileInputStream("Latintoukrainian.xml")));
           jTextArea1.setText((String) d.readObject());
           jTextArea6.setText((String) d.readObject());
           x = (String) d.readObject();
           y = (String) d.readObject();
           height = (String) d.readObject();
           width = (String) d.readObject();
           model = (DefaultComboBoxModel) d.readObject();
           d.close();
        } catch (Exception ex) {
            ex.getMessage();
        }

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent winEvt) {
                XMLEncoder e;
                try {
                    e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("Latintoukrainian.xml")));
                    e.writeObject(jTextArea1.getText());
                    e.writeObject(jTextArea6.getText());
                    e.writeObject("" + getLocation().x);
                    e.writeObject("" + getLocation().y);
                    e.writeObject("" + getSize().height);
                    e.writeObject("" + getSize().width);
                    e.writeObject(jComboBox1.getModel());
                    e.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(LatintoukrainianApp.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);
            }
        });
        final java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        if (x == null) {
            x = "" + ((screenSize.width - getWidth()) / 2);
        }
        if (y == null) {
            y = "" + ((screenSize.height - getHeight()) / 2);
        }
        if (height == null) {
            height = "" + getPreferredSize().height;
        }
        if (width == null) {
            width = "" + getPreferredSize().width;
        }
        jComboBox1.setModel(model);
        generateResult();
        setLocation(Integer.valueOf(x), Integer.valueOf(y));
        setSize(new java.awt.Dimension(Integer.valueOf(width), Integer.valueOf(height)));
        convertStreamToString(jTextPane3, "LatinToUkrainian.java");
        convertStreamToString(jTextPane4, "latintoukrainian.js");
    }
    
    private void convertStreamToString(JTextPane jTextPane, String fileName) {
        InputStreamReader is;
        try {
            InputStream ins = LatinToUkrainian.class.getResourceAsStream(fileName);
            if (ins != null) {
                is = new InputStreamReader(ins, "utf8");
                java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
                jTextPane.setText(s.hasNext() ? s.next() : "");
                jTextPane.setCaretPosition(0);
                is.close();
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(LatinToUkrainian.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LatinToUkrainian.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea6 = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPane3 = new javax.swing.JTextPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextPane4 = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Latin to Ukrainian converter");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setMaximumSize(new java.awt.Dimension(610, 444));
        jPanel2.setPreferredSize(new java.awt.Dimension(662, 447));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextArea1KeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jTextArea1);

        jTabbedPane1.addTab("Latin text:", jScrollPane3);

        jButton3.setText("Clear");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/github/latintoukrainian/copy.png"))); // NOI18N
        jButton4.setToolTipText("Copy to clipboard");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTextArea6.setColumns(20);
        jTextArea6.setRows(5);
        jScrollPane6.setViewportView(jTextArea6);

        jTabbedPane5.addTab("Ukrainian text:", jScrollPane6);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jTabbedPane1)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(jButton3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 197, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButton4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 34, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jComboBox1, 0, 508, Short.MAX_VALUE))
                    .add(jTabbedPane5))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 150, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jButton4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jComboBox1)
                    .add(jButton3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jTabbedPane5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Converter", jPanel1);

        jTabbedPane3.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jTextPane3.setEditable(false);
        jTextPane3.setBorder(null);
        jTextPane3.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jScrollPane4.setViewportView(jTextPane3);

        jTabbedPane3.addTab("java", jScrollPane4);

        jTextPane4.setEditable(false);
        jTextPane4.setBorder(null);
        jTextPane4.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jScrollPane5.setViewportView(jTextPane4);

        jTabbedPane3.addTab("js", jScrollPane5);

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jTabbedPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 761, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jTabbedPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane3.getAccessibleContext().setAccessibleName("groovy");

        jTabbedPane2.addTab("Source code", jPanel3);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jTabbedPane2)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jTabbedPane2)
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextArea1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea1KeyReleased
        generateResult();
    }//GEN-LAST:event_jTextArea1KeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (!jTextArea1.getText().isEmpty()) {
            if (jComboBox1.getModel().getSize() > 20) {
                ((DefaultComboBoxModel) jComboBox1.getModel()).removeElementAt(0);
            }
            int index = ((DefaultComboBoxModel) jComboBox1.getModel()).getIndexOf(jTextArea1.getText());
            if (index == -1 ) {
                ((DefaultComboBoxModel) jComboBox1.getModel()).addElement(jTextArea1.getText());
            } else {
                ((DefaultComboBoxModel) jComboBox1.getModel()).setSelectedItem(
                    ((DefaultComboBoxModel) jComboBox1.getModel()).getElementAt(index));
            }
        }
        jTextArea1.setText("");
        jTextArea6.setText("");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        StringSelection ss = new StringSelection(jTextArea6.getText());
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        jTextArea1.setText(((JComboBox) evt.getSource()).getModel().getSelectedItem().toString());
        generateResult();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private static void setLookAndFeel()
        throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
    {
        javax.swing.UIManager.LookAndFeelInfo infos[] = UIManager.getInstalledLookAndFeels();
        String firstFoundClass = null;
        for (javax.swing.UIManager.LookAndFeelInfo info : infos) {
            String foundClass = info.getClassName();
            if ("Nimbus".equals(info.getName())) {
                firstFoundClass = foundClass;
                break;
            }
            if (null == firstFoundClass) {
                firstFoundClass = foundClass;
            }
        }

        if(null == firstFoundClass)  {
            throw new IllegalArgumentException("No suitable Swing looks and feels");
        } else {
            UIManager.setLookAndFeel(firstFoundClass);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception {
        setLookAndFeel();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LatintoukrainianApp().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea6;
    private javax.swing.JTextPane jTextPane3;
    private javax.swing.JTextPane jTextPane4;
    // End of variables declaration//GEN-END:variables
    
}
