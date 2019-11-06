/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista.frames.event;

import Exceptions.DAOException;
import Modelo.Equipment;
import Modelo.DAO.EquipmentDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 * 
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba 
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class EquipmentComboModel extends DefaultComboBoxModel<EquipmentComboView>{

    private EquipmentDAO equip;
    
    private List<Equipment> list;

    public EquipmentComboModel(EquipmentDAO equip) {
        this.equip = equip;
        this.list = new ArrayList<>();
    }
    public void update() throws DAOException{
        if (equip != null) {
            list = equip.getAll();
            removeAllElements();
            for (Equipment a : list) {
                addElement( new EquipmentComboView(a));
            }
        }
        
    }
    
    
    
}
