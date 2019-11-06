/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista.frames.event;

import Exceptions.DAOException;
import Modelo.Commissioner;
import Modelo.DAO.CommissionerDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 * 
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba 
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class CommissionerComboModel extends DefaultComboBoxModel<CommissionerComboView>{

    private CommissionerDAO commissioner;
    
    private List<Commissioner> list;

    public CommissionerComboModel(CommissionerDAO commissioner) {
        this.commissioner = commissioner;
        this.list = new ArrayList<>();
    }
    public void update() throws DAOException{
        if (commissioner != null) {
            list = commissioner.getAll();
            removeAllElements();
            for (Commissioner c : list) {
                addElement( new CommissionerComboView(c));
            }
        }
        
    }
    
    
    
}
