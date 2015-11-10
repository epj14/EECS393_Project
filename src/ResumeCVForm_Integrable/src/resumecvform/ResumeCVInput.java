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
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;


//import java.util.logging.Level;
//import java.util.logging.Logger;
public class ResumeCVInput extends javax.swing.JFrame {

    /**
     * Creates new form ResumeCVInput
     */
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextPane jTextPane1;
    boolean isSaved = false;
    private String filePath = "";
    private String args[] = {};
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton jButton4;
    private String fileText;
    private String fileName;
    public ResumeCVInput(String fileText, String fileName) {
        initComponents();
        this.fileText = fileText;
        this.fileName = fileName;
        jTextArea1.setText(fileText);
        jTextField1.setText(fileName);
    }
    public ResumeCVInput() {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Resume Input");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTextPane1.setText("Help Pane");
        jScrollPane2.setViewportView(jTextPane1);

        jButton1.setText("Save");
        jButton1.setToolTipText("");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton2.setText("Share");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jButton3.setText("Help");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.setText("File Name: ");

        jButton4.setText("Output File as PDF");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                    .addComponent(jTextField1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addComponent(jButton1)
                            .addComponent(jButton3)
                            .addComponent(jButton4))
                        .addContainerGap(107, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)))
                .addGap(10, 10, 10)
                .addComponent(jButton4)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //help button clicked
    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:
        jTextPane1.setText("The large, editiable text pane on the left will "
                + "contains information about the fields in the template.  You can "
                + "add information next to each field.  Please make sure "
                + "there is a line of white space between the end of each "
                + "field and the beginning of the next.");
    }//GEN-LAST:event_jButton3MouseClicked

    //Share Button Clicked
    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        if (isSaved = false){
            JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, "File must be saved first to be shared.  Please save your file.", "Alert", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        ResumeCVShareForm.main(args);
    }//GEN-LAST:event_jButton2MouseClicked
    //Save Button Clicked
    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked

        JFileChooser saveChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("HTML Files", "html", "html");
        saveChooser.setFileFilter(filter);
        int returnValue = saveChooser.showSaveDialog(ResumeCVInput.this);
        String saveName = saveChooser.getSelectedFile().getName();
        if (returnValue != JFileChooser.APPROVE_OPTION) {
            JPanel panel = new JPanel();
           JOptionPane.showMessageDialog(panel, "File could not be saved, try again", "Save Error", JOptionPane.INFORMATION_MESSAGE);
        } else {
        isSaved = true;
        jTextField1.setText(saveChooser.getSelectedFile().getAbsolutePath());
        File saveFile = new File(saveChooser.getSelectedFile() + ".html");
        String contents = jTextArea1.getText();
        FileWriter fw;
            try {
                fw = new FileWriter(saveFile);
                fw.write(contents);
                //Evan's class will help you here.
            } catch (Exception e) {
                JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "File could not be saved, try again", "Save Error", JOptionPane.INFORMATION_MESSAGE);
                e.printStackTrace();
            }
        
        }
        
    }//GEN-LAST:event_jButton1MouseClicked
    //Output Button Clicked
    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        // TODO add your handling code here:
        if (isSaved == false){
            JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, "File must be saved to output.  Please try again!", "Output Error", JOptionPane.INFORMATION_MESSAGE);
        } else {
           try{
                File inputFile = new File(jTextField1.getText());
                JFileChooser pdfChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Files", "pdf", "pdf");
                pdfChooser.setFileFilter(filter);
                int returnVal = pdfChooser.showSaveDialog(ResumeCVInput.this);
           if (returnVal == JFileChooser.APPROVE_OPTION){
//                //File outputFile = pdfChooser.getSelectedFile();
//                String outputName = pdfChooser.getSelectedFile().getAbsolutePath();
//                String inputName = inputFile.getAbsolutePath();
//
//                //String outputFile = pdfChooser.getSelectedFile().getAbsolutePath();
//                createPdf(outputName + ".pdf");
//                String inputArgs[] = {inputName, outputName};
//                pdfOutput.main(inputArgs);
           }
        }
           catch(Exception e){
               JPanel panel = new JPanel();
               JOptionPane.showMessageDialog(panel, "An error occurred, most likely because your file couldn't be found.  Please try again!", "Output Error", JOptionPane.INFORMATION_MESSAGE);
               e.printStackTrace();
           }
        }
    }//GEN-LAST:event_jButton4MouseClicked
    //create the output PDF File
    public void createPdf(String outputFile) throws DocumentException, IOException{
        Document outputDoc = new Document();
        PdfWriter.getInstance(outputDoc, new FileOutputStream(outputFile));
        outputDoc.open();
        outputDoc.add(new Paragraph("test"));
        outputDoc.close();
    } 
//    
//    public String getHTMLText(){
//     return "blah";
//     //Ask Evan how to extract this.
//    }
    
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
            java.util.logging.Logger.getLogger(ResumeCVInput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ResumeCVInput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ResumeCVInput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ResumeCVInput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            if (args.length > 0)
                new ResumeCVInput(args[0], args[1]).setVisible(true);
            else
                new ResumeCVInput().setVisible(true);
            }
        });
    }
/*
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
*/}
