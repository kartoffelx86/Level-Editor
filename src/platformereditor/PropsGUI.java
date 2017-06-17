/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformereditor;

import java.awt.Color;
import javax.swing.JColorChooser;

/**
 *
 * @author leonb
 */
public class PropsGUI extends javax.swing.JFrame {

    /**
     * Creates new form PropsGUI
     */
    public PropsGUI() {
        initComponents();
    }
    boolean error = false;
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblWidth = new javax.swing.JLabel();
        lblHeight = new javax.swing.JLabel();
        txtWidth = new javax.swing.JTextField();
        txtHeight = new javax.swing.JTextField();
        lblColor = new javax.swing.JLabel();
        btnColor = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Eigenschaften");
        setType(java.awt.Window.Type.UTILITY);
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 20, 0, 20, 0};
        layout.rowHeights = new int[] {0, 1, 0, 1, 0};
        getContentPane().setLayout(layout);

        lblWidth.setText("Breite");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        getContentPane().add(lblWidth, gridBagConstraints);

        lblHeight.setText("Höhe");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        getContentPane().add(lblHeight, gridBagConstraints);

        txtWidth.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtWidth.setText("10");
        txtWidth.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtWidthFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(txtWidth, gridBagConstraints);

        txtHeight.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtHeight.setText("10");
        txtHeight.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtHeightFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(txtHeight, gridBagConstraints);

        lblColor.setText("Farbe");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        getContentPane().add(lblColor, gridBagConstraints);

        btnColor.setBackground(new java.awt.Color(0, 0, 0));
        btnColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColorActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 12;
        getContentPane().add(btnColor, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColorActionPerformed
        Color color = JColorChooser.showDialog(this, "Farbe wählen..", btnColor.getBackground());
        if(color!=null){
            btnColor.setBackground(color);
        } 
    }//GEN-LAST:event_btnColorActionPerformed

    private void txtWidthFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtWidthFocusLost
        if(isNumber(txtWidth.getText())){
            txtWidth.setBackground(Color.white);
            error = false;
        }else{
            txtWidth.setBackground(Color.red);
            error = true;
        }
    }//GEN-LAST:event_txtWidthFocusLost

    private void txtHeightFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHeightFocusLost
        if(isNumber(txtHeight.getText())){
            txtHeight.setBackground(Color.white);
            error = false;
        }else{
            txtHeight.setBackground(Color.red);
            error = true;
        }
    }//GEN-LAST:event_txtHeightFocusLost
    
    public int getWidthProp(){
        return Integer.valueOf(txtWidth.getText());
    }
    
    public int getHeightProp(){
        return Integer.valueOf(txtHeight.getText());
    }
         
    public Color getColor(){
        return btnColor.getBackground();
    }
    public static boolean isNumber(String string){
        return string.matches("\\d+");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnColor;
    private javax.swing.JLabel lblColor;
    private javax.swing.JLabel lblHeight;
    private javax.swing.JLabel lblWidth;
    private javax.swing.JTextField txtHeight;
    private javax.swing.JTextField txtWidth;
    // End of variables declaration//GEN-END:variables
}