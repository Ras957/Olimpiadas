/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.frames.area;

import Exceptions.DAOException;
import Modelo.Area;
import Modelo.DAO.AreaDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class AreaTableModel extends AbstractTableModel{
    
    private AreaDAO areas;
    
    private List<Area> datos = new ArrayList<>();

    public AreaTableModel(AreaDAO areas) {
        this.areas = areas;
    }
    
    public void updateModel() throws DAOException {
        datos = areas.getAll();
    }
    
    @Override
    public String getColumnName(int column) {
        String n = "";
        switch (column){
            case 0: n = "ID"; break;
            case 1: n = "Localizacion"; break;
            case 2: n = "Deporte"; break;
            case 3: n = "Polideportivo"; break;
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
        Area a = datos.get(rowIndex);
        switch (columnIndex){
            case 0: o = a.getId(); break;
            case 1: o = a.getLocation(); break;
            case 2: o = a.getSport(); break;
            case 3: o = a.getMsc().getId(); break;
            default: o = "";
        }
        return o;
    }
    
}



