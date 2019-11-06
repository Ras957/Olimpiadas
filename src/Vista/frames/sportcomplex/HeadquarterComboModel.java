/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista.frames.sportcomplex;

import Exceptions.DAOException;
import Modelo.DAO.HeadquarterDAO;
import Modelo.Headquarter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 * 
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba 
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class HeadquarterComboModel  extends DefaultComboBoxModel<HeadquarterComboView>{
    
    private HeadquarterDAO headquarters;
    
    private List<Headquarter> list;

    public HeadquarterComboModel(HeadquarterDAO headquarters) {
        this.headquarters = headquarters;
        this.list = new ArrayList<>();
    }
    
    public void update() throws DAOException{
        if (headquarters != null) {
            list = headquarters.getAll();
            removeAllElements();
            for (Headquarter hq : list) {
                addElement( new HeadquarterComboView(hq));
            }
        }
        
    }
    
    
}
