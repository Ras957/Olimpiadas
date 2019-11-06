/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.frames.sportcenter;

import Exceptions.DAOException;
import Modelo.DAO.SportComplexDAO;
import Modelo.SportComplex;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class SportComplexComboModel extends DefaultComboBoxModel<SportComplexComboView>{
    
    private SportComplexDAO sportComplexes;
    
    private List<SportComplex> list;

    public SportComplexComboModel(SportComplexDAO sportComplexes) {
        this.sportComplexes = sportComplexes;
        this.list = new ArrayList<>();
    }
    
    public void update() throws DAOException{
        if (sportComplexes != null) {
            list = sportComplexes.getAll();
            removeAllElements();
            for (SportComplex sc : list) {
                addElement( new SportComplexComboView(sc));
            }
        }
        
    }
    
}



