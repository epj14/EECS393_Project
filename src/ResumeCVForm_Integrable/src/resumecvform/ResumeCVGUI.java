/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resumecvform;

/**
 *
 * @author Sean
 */
import javax.swing.*;
import java.io.*;
import java.util.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class ResumeCVGUI extends javax.swing.JFrame {

    /**
     * Creates new form ResumeCVGUI
     */
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    
    public ContentWriter cw;
    String args[];
    public ResumeCVGUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Main Menu");

        jButton1.setText("New Resume");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });

        jButton2.setText("New Template");
        jButton2.setToolTipText("");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jButton3.setText("Open Resume");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jButton4.setText("Help");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        jButton5.setText("Open Template");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });

        jButton6.setText("Share File");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Resume/CV Writer System 1.0\n\nPlease select an action from \nthe choices below:");
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton3)
                                    .addComponent(jButton4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton6)
                                    .addComponent(jButton5)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton6))
                .addGap(71, 71, 71))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
    
    }//GEN-LAST:event_jButton1MousePressed
    //create a new resume
    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        JFileChooser templateChooser = new JFileChooser();
        JFileChooser outputChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("HTML Files", "html", "html");
        templateChooser.setDialogTitle("Choose a template file to open.");
        outputChooser.setDialogTitle("Choose where to save your file.");
        templateChooser.setFileFilter(filter);
        outputChooser.setFileFilter(filter);
        int outputReturn = outputChooser.showSaveDialog(ResumeCVGUI.this);
        int returnValue = templateChooser.showOpenDialog(ResumeCVGUI.this);
//        if (returnValue == JFileChooser.APPROVE_OPTION && outputReturn == JFileChooser.APPROVE_OPTION) {
//            //System.out.println("Okay genius, parse the template");
            try {
            //String templateName = templateChooser.getSelectedFile().getName();
            String templatePath = templateChooser.getSelectedFile().getAbsolutePath();
            //String outputName = outputChooser.getSelectedFile().getName();
            String outputPath = outputChooser.getSelectedFile().getAbsolutePath();
            //ContentWriter cw = new ContentWriter(templatePath, outputPath);
//            
//            //change this so the text can change.  
//            cw.writeContent("My First Heading", "My first paragraph");
              File outputFile = outputChooser.getSelectedFile();
//            Document outputDoc = Jsoup.parse(outputFile, "UTF-8");  
//            Elements htags = outputDoc.select("h1");
              String resumeText = "Integrate HTML Stuff";
              String fileName = outputFile.getAbsolutePath();
              String inputArgs[] = {resumeText, fileName};
             ResumeCVInput.main(inputArgs);
            }
            catch (Exception e) {
                JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "An error occured due to a problem parsing the template.  Please try again!", "Error!", JOptionPane.INFORMATION_MESSAGE);
                e.printStackTrace();
            }
            
        
    }//GEN-LAST:event_jButton1MouseClicked
    //open an existing resume
    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:
        JFileChooser resumeChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("HTML Files", "html", "html");
        resumeChooser.setFileFilter(filter);
        resumeChooser.setDialogTitle("Choose a resume file to open.");
        int returnValue = resumeChooser.showOpenDialog(ResumeCVGUI.this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            ResumeCVInput.main(args);
            //System.out.println("Ok, now display the resume and its fields.");
//            try{
//            File resumeFile = resumeChooser.getSelectedFile();
//            Document openDoc = Jsoup.parse(resumeFile, "UTF-8");
//            Elements htags = openDoc.select("h1");
//            String resumeText = htags.text();
//            String fileName = resumeChooser.getSelectedFile().getAbsolutePath();
//            String inputArgs[] = {resumeText, fileName};
//            ResumeCVInput.main(inputArgs);
//        }
//            catch (Exception e){
//                e.printStackTrace();
//            }
        }
    }//GEN-LAST:event_jButton3MouseClicked
    //open an existing template
    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        // TODO add your handling code here:
        JFileChooser templateChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("HTML Files", "html", "html");
        templateChooser.setFileFilter(filter);
        templateChooser.setDialogTitle("Please choose a template file to open.");
        int returnValue = templateChooser.showOpenDialog(ResumeCVGUI.this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            System.out.println("Okay, now display the template's fields");
            //I haven't created that yet.  One piece at a time.
            ResumeCVTemplate.main(args);
        }
    }//GEN-LAST:event_jButton5MouseClicked
    //share a file from the start state
    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
        // TODO add your handling code here:
        JFileChooser shareChooser = new JFileChooser();
        int returnValue = shareChooser.showOpenDialog(ResumeCVGUI.this);
        shareChooser.setDialogTitle("Choose the file you wish to share.");
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            System.out.println("Okay, now ask for necessary info");
            String fileString = shareChooser.getSelectedFile().getAbsolutePath();
            String args2[] = {fileString};
            ResumeCVShareForm.main(args2);
        }
    }//GEN-LAST:event_jButton6MouseClicked
    //make a new template
    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        ResumeCVTemplate.main(args);
    }//GEN-LAST:event_jButton2MouseClicked
    //help button clicked
    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        // TODO add your handling code here:
        ResumeCVHelp.main(args);
    }//GEN-LAST:event_jButton4MouseClicked

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
            java.util.logging.Logger.getLogger(ResumeCVGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ResumeCVGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ResumeCVGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ResumeCVGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ResumeCVGUI().setVisible(true);
            }
        });
    }
/*
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
*/
    }
