/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.frames.sportcomplex;

import Exceptions.DAOException;
import Modelo.DAO.SportComplexDAO;
import Modelo.SportComplex;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class SportComplexTableModel extends AbstractTableModel{
    
    private SportComplexDAO sportComplexes;
    
    private List<SportComplex> datos = new ArrayList<>();

    public SportComplexTableModel(SportComplexDAO sportComplexes) {
        this.sportComplexes = sportComplexes;
    }
    
    public void updateModel() throws DAOException {
        datos = sportComplexes.getAll();
    }
    
    @Override
    public String getColumnName(int column) {
        String n = "";
        switch (column){
            case 0: n = "ID"; break;
            case 1: n = "Localizacion"; break;
            case 2: n = "Jefe"; break;
            case 3: n = "Sede"; break;
            default: n = "[no]";
        }
        return n;
    }

    @Override
    public int getRowCount() {
        return datos.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object o = null;
        SportComplex sc = datos.get(rowIndex);
        switch (columnIndex){
            case 0: o = sc.getId(); break;
            case 1: o = sc.getLocation(); break;
            case 2: o = sc.getBoss(); break;
            case 3: o = sc.getHeadquarter().getId(); break;
            default: o = "";
        }
        return o;
    }
    
}





