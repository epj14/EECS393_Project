package resumecvform;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sean
 */
public class ResumeCVHelp extends javax.swing.JFrame {
     
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JButton jButton1;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    /**
     * Creates new form ResumeCVHelp
     */
    public ResumeCVHelp() {
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

        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Help Menu");

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Creating a New Resume", "Creating a Custom Template", "Opening an Existing Resume", "Opening an Existing Template", "Sharing a File from the Main Menu", "Sharing a File while Working", "Saving a File", "Generating a Resume", "HTML Formatting Reference" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jList1);

        jTextPane1.setText("Please choose the item with which you would like help.  Click Get Help to display the help below.");
        jScrollPane3.setViewportView(jTextPane1);

        jScrollPane1.setViewportView(jTextPane2);

        jButton1.setText("Get Help");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jButton1)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)
                        .addGap(1, 1, 1)))
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        String selectedItem = (jList1.getSelectedValue()).toString();
        if (selectedItem == "Creating a New Resume") {
            jTextPane2.setText("Click the New Resume Button on the main menu"
                    + "You will be asked to choose a template.  The templates conform to"
                    + "a particular format and will be marked so the sytem can"
                    + "check if the template is valid or not.  The system should "
                    + "open a resume form with the fields depicted on the template.");
        }
        
        if (selectedItem == "Creating a Custom Template") {
            jTextPane2.setText("Click the New Template Button on the main menu"
                    + "The create template dialog should open with a list of"
                    + "avaliable fields to add to a custom template. The user"
                    + "may select which fields they wish to include");
        }
        
        if (selectedItem == "Opening an Existing Resume"){
            jTextPane2.setText("Click on the Open Resume button on the main menu."
                    + "Then browse to the saved resume you wish to open.  The resumes"
                    + "conform to a specific format and are marked so the program"
                    + "can determime if the chosen file is valid.  Then, the system"
                    + "should open the resume input form pre-filled with the"
                    + "resume's information.");
        }
        
        if (selectedItem == "Open an Existing Template"){
           jTextPane2.setText("Click on the Open Template button on the main menu."
                    + "Then browse to the saved template you wish to open.  The templates"
                    + "conform to a specific format and are marked so the program"
                    + "can determime if the chosen file is valid.  Then, the system"
                    + "should open the template input form pre-filled with the"
                    + "resume's information.");
        }
        
        if (selectedItem == "Sharing a File from the Main Menu"){
            jTextPane2.setText("Click the Share File button on the main menu."
                    + "Then browse to the file you wish to share.  After choosing"
                    + "the file, you will then be asked for a recipient's email"
                    + "address.  Hit the send button to send the file in an"
                    + "email to the desired recipient.");
        }
        
        if (selectedItem == "Sharing a File while Working") {
            jTextPane2.setText("On the file creation dialog, there is a Share button."
                    + "Clicking on this button will save the file, then open the"
                    + "share file dialog to ask for a recipient email address."
                    + "Click send to send the file.");
        }
        
        if (selectedItem == "Saving a File"){
            jTextPane2.setText("On the working dialog, there should be a Save button."
                    + "Click on the button to save the file to a location of your choosing."
                    + "Note that the format will be restricted so the program can read the"
                    + "file when opening it later.");
        }
        
        if (selectedItem == "Generating a Resume"){
            jTextPane2.setText("On the form for creating a resume file, there"
                    + "is a Generate button.  When this is clicked, the system"
                    + "will check for errors such as missing fields and report"
                    + "them to the user if found.  If no errors are found, the "
                    + "file will be output to a PDF file and the user will be"
                    + "asked to name and save the file.");
        }
        
        if (selectedItem == "HTML Formatting Reference"){
            ;
        }
        
        if (selectedItem == null) {
         ; //do nothing.
        }
    }//GEN-LAST:event_jButton1MouseClicked

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
            java.util.logging.Logger.getLogger(ResumeCVHelp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ResumeCVHelp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ResumeCVHelp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ResumeCVHelp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ResumeCVHelp().setVisible(true);
            }
        });
    }
/*
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    // End of variables declaration//GEN-END:variables
*/}
