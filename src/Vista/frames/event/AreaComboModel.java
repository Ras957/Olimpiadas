/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista.frames.event;

import Exceptions.DAOException;
import Modelo.Area;
import Modelo.DAO.AreaDAO;
import Modelo.DAO.MySQL.MySQLAreaDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 * 
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba 
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class AreaComboModel extends DefaultComboBoxModel<AreaComboView>{

    private MySQLAreaDAO areas;
    
    private List<Area> list;

    public AreaComboModel(MySQLAreaDAO areas) {
        this.areas = areas;
        this.list = new ArrayList<>();
    }
    public void update() throws DAOException{
        if (areas != null) {
            list = areas.getAll();
            removeAllElements();
            for (Area a : list) {
                addElement( new AreaComboView(a));
            }
        }
        
    }
    
    public void update(int n) throws DAOException{
        if (areas != null) {
            list = areas.getByMSC(n);
            removeAllElements();
            for (Area a : list) {
                addElement( new AreaComboView(a));
            }
        } 
    }
    
    
    
}
