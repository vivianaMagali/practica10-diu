/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practica10;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static java.util.Locale.filter;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author vivianabenitez
 */
public class Frame extends javax.swing.JFrame {
    Compress tarea;
    JFileChooser fc = new JFileChooser();
    String ruta;
    FileNameExtensionFilter filter = null;
    String nombre;
    DefaultListModel model1 = new DefaultListModel();
    DefaultListModel model2 = new DefaultListModel();
    List<String> files = new ArrayList();
   
    
    private class Compress extends SwingWorker<Void, Integer> {
        @Override
        protected Void doInBackground() throws Exception { 
            panelLoad.setVisible(true);
            progressBar.setVisible(true);
            int progreso = 0;
            progressBar.setValue(progreso);
            
            try{
                // Objeto para referenciar a los archivos que queremos comprimir
                BufferedInputStream origin = null;
                // Objeto para referenciar el archivo zip de salida
                FileOutputStream dest = new FileOutputStream(ruta + "/" + nombre + ".zip");
                ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
                // Buffer de transferencia para almacenar datos a comprimir
                byte[] data = new byte[1024];


            Iterator i = files.iterator();
            while(i.hasNext()){
                progreso++;
                File filename = (File)i.next();
                FileInputStream fi = new FileInputStream(filename);
                origin = new BufferedInputStream(fi, 1024);
                ZipEntry entry = new ZipEntry( filename.getName() );
                out.putNextEntry( entry );
                // Leemos datos desde el archivo origen y se envían al archivo destino
                int count;
                while((count = origin.read(data, 0, 1024)) != -1){
                    out.write(data, 0, count);
                }
                // Cerramos el archivo origen, ya enviado a comprimir
                origin.close();
                Thread.sleep(1000);
                progressBar.setValue((progreso * 100) / files.size());
            }
            // Cerramos el archivo zip
            out.close();
            
            } catch (Exception e ){
                e.printStackTrace();
            }
            
            return null;
        }
        
        @Override
        public void done(){
            System.out.println("Tarea terminada");
            panelLoad.setVisible(false);
        }
        
    }

    
    public Frame() {
        
        initComponents();
        panelLoad.setVisible(false);
        progressBar.setStringPainted(true);
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        list = new javax.swing.JList<>();
        buttonCompressed = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        panelLoad = new javax.swing.JPanel();
        cancelButton = new javax.swing.JButton();
        progressBar = new javax.swing.JProgressBar();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        open = new javax.swing.JMenuItem();
        close = new javax.swing.JMenuItem();
        edit = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        list.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Select Files for Compressed", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Lucida Grande", 3, 13))); // NOI18N
        list.setToolTipText("");
        jScrollPane1.setViewportView(list);

        buttonCompressed.setBackground(new java.awt.Color(102, 204, 255));
        buttonCompressed.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        buttonCompressed.setText("Compress files");
        buttonCompressed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCompressedActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 3, 13)); // NOI18N
        jLabel1.setText("Created by Viviana Benítez");

        cancelButton.setBackground(new java.awt.Color(255, 255, 255));
        cancelButton.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        cancelButton.setText("X");
        cancelButton.setToolTipText("Cancel ");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        progressBar.setBackground(new java.awt.Color(51, 51, 255));

        javax.swing.GroupLayout panelLoadLayout = new javax.swing.GroupLayout(panelLoad);
        panelLoad.setLayout(panelLoadLayout);
        panelLoadLayout.setHorizontalGroup(
            panelLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoadLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelLoadLayout.setVerticalGroup(
            panelLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoadLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jMenu1.setText("File");

        open.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.SHIFT_MASK));
        open.setText("Open");
        open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openActionPerformed(evt);
            }
        });
        jMenu1.add(open);

        close.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_MASK));
        close.setText("Close");
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });
        jMenu1.add(close);

        jMenuBar1.add(jMenu1);

        edit.setText("Edit");
        jMenuBar1.add(edit);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(panelLoad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(buttonCompressed)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonCompressed, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelLoad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openActionPerformed
        //filter = new FileNameExtensionFilter("archivo", "*");
        //fc.addChoosableFileFilter(filter);
        int res = fc.showOpenDialog(null);
        
        if( res == JFileChooser.APPROVE_OPTION ){
            model1.clear();
            File file = fc.getSelectedFile();
            
            
            //obteniendo la ruta y el nombre del fichero para saber cuales seran por defecto a la hora de comprimir
            ruta = file.getPath();
            nombre = file.getName();
            
            File[] yourFileList = file.listFiles();
            for(File f : yourFileList) {
                model1.addElement(f);
            }
            list.setModel(model1);

        }
        if( res == JFileChooser.CANCEL_OPTION ){
            System.out.println(" se dio a cancelar");
        }
    }//GEN-LAST:event_openActionPerformed

    private void buttonCompressedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCompressedActionPerformed
        if( model1.getSize() == 0 ){
            JOptionPane.showMessageDialog(null, "No file has been selected ");
            return;
        }
        final int YES = 0;
        int result = JOptionPane.showConfirmDialog(null, "Are you sure compressed files ? ");
        
        if (result == YES){
            
            int path = fc.showSaveDialog(null);
            
            if(path == JFileChooser.APPROVE_OPTION) {
                ruta = fc.getSelectedFile().getAbsolutePath();
            }
            
            //comprimir los archivos seleccionado
            files = list.getSelectedValuesList();
            
            tarea = new Compress();
            tarea.execute();


        }
       
    }//GEN-LAST:event_buttonCompressedActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        JOptionPane.showMessageDialog(null, "Compression has been canceled");
        panelLoad.setVisible(false);
        tarea.cancel(true);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
         int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure exit Program?","EXIT",JOptionPane.YES_NO_OPTION);
        if(confirmed == JOptionPane.YES_OPTION)
        {
            dispose();
        }
    }//GEN-LAST:event_closeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCompressed;
    private javax.swing.JButton cancelButton;
    private javax.swing.JMenuItem close;
    private javax.swing.JMenu edit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> list;
    private javax.swing.JMenuItem open;
    private javax.swing.JPanel panelLoad;
    private javax.swing.JProgressBar progressBar;
    // End of variables declaration//GEN-END:variables
}
