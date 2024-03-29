/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.frames.headquarter;

import Modelo.Headquarter;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class HeadquartersDetailsPanel extends javax.swing.JPanel {
    
    private Headquarter headquarter;
    
    private boolean edit;

    public HeadquartersDetailsPanel() {
        initComponents();
    }

    public Headquarter getHeadquarter() {
        return headquarter;
    }

    public void setHeadquarter(Headquarter headquarter) {
        this.headquarter = headquarter;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
        TextName.setEditable(edit);
        TextBudget.setEditable(edit);
    }
    
    public void loadData(){
        if (headquarter != null){
            TextName.setText(headquarter.getName());
            TextBudget.setText(Float.toString(headquarter.getBudget()));
        }else{
            TextName.setText("");
            TextBudget.setText("");
        }
        TextName.requestFocus();
    }
    
    public void saveData(){
        if (headquarter == null) {
            headquarter = new Headquarter();
        }
        headquarter.setName(TextName.getText());
        if (!TextBudget.getText().equals("")) {
            headquarter.setBudget(Float.parseFloat(TextBudget.getText()));
        }   
    }
    
    public boolean checkData(){
        boolean noEmpty = false;
        if (!TextBudget.getText().equals("") && !TextName.getText().equals("")) {
            noEmpty = true;
        }
        return noEmpty;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Name = new javax.swing.JLabel();
        Budget = new javax.swing.JLabel();
        TextName = new javax.swing.JTextField();
        TextBudget = new javax.swing.JTextField();

        Name.setText("Nombre:");

        Budget.setText("Presupuesto:");

        TextName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextNameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Name)
                    .addComponent(Budget))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TextName)
                    .addComponent(TextBudget, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Name)
                    .addComponent(TextName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Budget)
                    .addComponent(TextBudget, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void TextNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextNameActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Budget;
    private javax.swing.JLabel Name;
    private javax.swing.JTextField TextBudget;
    private javax.swing.JTextField TextName;
    // End of variables declaration//GEN-END:variables
}

