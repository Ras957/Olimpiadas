/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.frames.event;

import Exceptions.DAOException;
import Modelo.DAO.DAOManager;
import Modelo.DAO.MySQL.MySQLAreaDAO;
import Modelo.DAO.MySQL.MySQLDAOManager;
import Modelo.Event;
import Vista.frames.sportcenter.SportComplexComboModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class ListEventFrame extends javax.swing.JFrame {
    
    private DAOManager manager;
    private EventTableModel model;

    public ListEventFrame(DAOManager manager) throws DAOException {
        initComponents();
        this.manager = manager;
        this.model = new EventTableModel(manager.getEventDAO());
        getData();
        this.tabla.setModel(model);
        this.Details.setEdit(false);
        this.Details.setEvent(null);
        this.Details.setComboComplex(
                new SportComplexComboModel(manager.getSportComplexDAO()));
        this.Details.setComboArea(new AreaComboModel((MySQLAreaDAO) manager.getAreaDAO()));
        this.Details.setComboEquip(
                new EquipmentComboModel(manager.getEquipmentDAO()));
        this.Details.setComboCommissioner(
                new CommissionerComboModel(manager.getCommissionerDAO()));
        activateButtonsCRUD(false);
        activateButtonsSave(false);
        this.tabla.getSelectionModel().addListSelectionListener(e -> {
            activateButtonsCRUD(tabla.getSelectedRow() != -1);
        });
    }
    public final void getData() throws DAOException {
        Registros.setText("Actualizando tabla...");
        model.updateModel();
        model.fireTableDataChanged();
        Registros.setText(model.getRowCount() + " eventos visibles");
    }

    /**
     * This method is called from within the constructor to initialize
     * the form. WARNING: Do NOT modify this code. The content of this
     * method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        BAdd = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        BEdit = new javax.swing.JButton();
        BDelete = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        BSave = new javax.swing.JButton();
        BCancel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        Details = new Vista.frames.event.EventDetailsPanel();
        Registros = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro de eventos");

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        BAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/add.png"))); // NOI18N
        BAdd.setText("Nuevo");
        BAdd.setToolTipText("");
        BAdd.setBorderPainted(false);
        BAdd.setContentAreaFilled(false);
        BAdd.setFocusable(false);
        BAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BAdd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAddActionPerformed(evt);
            }
        });
        jToolBar1.add(BAdd);
        jToolBar1.add(jSeparator1);

        BEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/edit.png"))); // NOI18N
        BEdit.setText("Editar");
        BEdit.setBorderPainted(false);
        BEdit.setContentAreaFilled(false);
        BEdit.setEnabled(false);
        BEdit.setFocusable(false);
        BEdit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BEdit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEditActionPerformed(evt);
            }
        });
        jToolBar1.add(BEdit);

        BDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/delete.png"))); // NOI18N
        BDelete.setText("Eliminar");
        BDelete.setBorderPainted(false);
        BDelete.setContentAreaFilled(false);
        BDelete.setEnabled(false);
        BDelete.setFocusable(false);
        BDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDeleteActionPerformed(evt);
            }
        });
        jToolBar1.add(BDelete);
        jToolBar1.add(jSeparator2);

        BSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/save.png"))); // NOI18N
        BSave.setText("Guardar");
        BSave.setBorderPainted(false);
        BSave.setContentAreaFilled(false);
        BSave.setEnabled(false);
        BSave.setFocusable(false);
        BSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSaveActionPerformed(evt);
            }
        });
        jToolBar1.add(BSave);

        BCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/cancelar.png"))); // NOI18N
        BCancel.setText("Cancelar");
        BCancel.setBorderPainted(false);
        BCancel.setContentAreaFilled(false);
        BCancel.setEnabled(false);
        BCancel.setFocusable(false);
        BCancel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BCancel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCancelActionPerformed(evt);
            }
        });
        jToolBar1.add(BCancel);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabla);

        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.add(Details, java.awt.BorderLayout.CENTER);

        Registros.setText("numero de registros");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Registros)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 951, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 364, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Registros))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private Event getSelectedEvent() throws DAOException{
        int id = (int) tabla.getValueAt(tabla.getSelectedRow(), 0);
        return manager.getEventDAO().get(id);
    }
    
    private void BAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAddActionPerformed
        tabla.clearSelection();
        Details.setEvent(null);
        Details.loadData();
        Details.setEdit(true);
        activateButtonsSave(true);
    }//GEN-LAST:event_BAddActionPerformed

    private void BEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEditActionPerformed
        try {
            Event event = getSelectedEvent();
            Details.setEvent(event);
            Details.setEnabled(true);
            Details.setEdit(true);
            Details.loadData();
            activateButtonsSave(true);
        } catch (DAOException ex) {
            ex.getMessage();
        }
    }//GEN-LAST:event_BEditActionPerformed

    private void BDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDeleteActionPerformed
        if (JOptionPane.showConfirmDialog(rootPane, "¿Seguro que quieres borrar "
            + "este evento?", "Borrar Evento", JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
        try {
            Event event = getSelectedEvent();
            manager.getEventDAO().delete(event);
            getData();
        } catch (DAOException ex) {
            ex.getMessage();
        }
        }
    }//GEN-LAST:event_BDeleteActionPerformed

    private void BSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSaveActionPerformed
        try {
            if (Details.checkData()) {
                Details.saveData();
                Event event = Details.getEvent();
                if (event.getId() == null) {
                    manager.getEventDAO().insert(event);
                } else {
                    manager.getEventDAO().modify(event);
                }
                Details.setEvent(null);
                Details.setEdit(false);
                Details.loadData();
                tabla.clearSelection();
                activateButtonsSave(false);
                getData();
            }else{
                JOptionPane.showMessageDialog(rootPane, "Por favor rellene "
                    + "todos los campos", "Error datos incompletos",
                    JOptionPane.OK_OPTION);
            }
        } catch (DAOException ex) {
            ex.getMessage();
        }
    }//GEN-LAST:event_BSaveActionPerformed

    private void BCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCancelActionPerformed
        Details.setEvent(null);
        Details.setEdit(false);
        Details.loadData();
        tabla.clearSelection();
        activateButtonsSave(false);
    }//GEN-LAST:event_BCancelActionPerformed

    /**
     * @param args the command line arguments
     
    public static void main(String args[]) throws Exception {
     DAOManager manager = new MySQLDAOManager("localhost", "root", "", "olympics");
        java.awt.EventQueue.invokeLater(() -> {
         try {
             new ListEventFrame(manager).setVisible(true);
         } catch (DAOException ex) {
             ex.printStackTrace();
         }
     });
    }*/
    
    private void activateButtonsCRUD(boolean active) {
        BEdit.setEnabled(active);
        BDelete.setEnabled(active);
    }

    private void activateButtonsSave(boolean active) {
        BSave.setEnabled(active);
        BCancel.setEnabled(active);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BAdd;
    private javax.swing.JButton BCancel;
    private javax.swing.JButton BDelete;
    private javax.swing.JButton BEdit;
    private javax.swing.JButton BSave;
    private Vista.frames.event.EventDetailsPanel Details;
    private javax.swing.JLabel Registros;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}

