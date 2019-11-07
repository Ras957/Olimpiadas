/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.frames.headquarter;

import Exceptions.DAOException;
import Modelo.DAO.HeadquarterDAO;
import Modelo.Headquarter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class HeadquarterTableModel extends AbstractTableModel{
    
    private HeadquarterDAO headquarters;
    
    private List<Headquarter> datos = new ArrayList<>();
    
    public HeadquarterTableModel(HeadquarterDAO headquarters){
        this.headquarters = headquarters;
        
    }
    
    public void updateModel() throws DAOException {
        datos = headquarters.getAll();
    }

    @Override
    public String getColumnName(int column) {
        String n = "";
        switch (column){
            case 0: n = "ID"; break;
            case 1: n = "Nombre"; break;
            case 2: n = "Presupuesto"; break;
            case 3: n = "Número de Complejos"; break;
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
        Headquarter hq = datos.get(rowIndex);
        switch (columnIndex){
            case 0: o = hq.getId(); break;
            case 1: o = hq.getName(); break;
            case 2: o = hq.getBudget(); break;
            case 3: o = hq.getNumComplexes(); break;
            default: o = "";
        }
        return o;
    }
}









