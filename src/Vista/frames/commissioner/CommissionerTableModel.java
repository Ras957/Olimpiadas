/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.frames.commissioner;

import Exceptions.DAOException;
import Modelo.Commissioner;
import Modelo.DAO.CommissionerDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class CommissionerTableModel extends AbstractTableModel{

    private CommissionerDAO commissioners;

    private List<Commissioner> datos = new ArrayList<>();

    public CommissionerTableModel(CommissionerDAO commissioners) {
        this.commissioners = commissioners;
    }
    
    public void updateModel() throws DAOException {
        datos = commissioners.getAll();
    }
    
    @Override
    public String getColumnName(int column) {
        String n = "";
        switch (column){
            case 0: n = "ID"; break;
            case 1: n = "DNI"; break;
            case 2: n = "Nombre"; break;
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
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object o = null;
        Commissioner com = datos.get(rowIndex);
        switch (columnIndex){
            case 0: o = com.getId(); break;
            case 1: o = com.getDni(); break;
            case 2: o = com.getName(); break;
            default: o = "";
        }
        return o;
    }
    
}


