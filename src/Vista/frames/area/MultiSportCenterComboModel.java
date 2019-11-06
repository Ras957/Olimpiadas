/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.frames.area;

import Exceptions.DAOException;
import Modelo.DAO.MultiSportCenterDAO;
import Modelo.MultiSportCenter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class MultiSportCenterComboModel extends DefaultComboBoxModel<MultiSportCenterComboView>{
    
    private MultiSportCenterDAO msc;
    
    private List<MultiSportCenter> list;

    public MultiSportCenterComboModel(MultiSportCenterDAO msc) {
        this.msc = msc;
        this.list = new ArrayList<>();
    }
    
    public void update() throws DAOException{
        if (msc != null) {
            list = msc.getAll();
            removeAllElements();
            for (MultiSportCenter m : list) {
                addElement( new MultiSportCenterComboView(m));
            }
        }  
    }   
}


