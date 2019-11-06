/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.frames.multisportcenter;

import Exceptions.DAOException;
import Modelo.DAO.MultiSportCenterDAO;
import Modelo.MultiSportCenter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class MultiSportCenterTablaModel extends AbstractTableModel{
    
    private MultiSportCenterDAO multiSportCenter;
    
    private List<MultiSportCenter> datos = new ArrayList<>();

    public MultiSportCenterTablaModel(MultiSportCenterDAO multiSportCenter) {
        this.multiSportCenter = multiSportCenter;
    }
    
    public void updateModel() throws DAOException {
        datos = multiSportCenter.getAll();
    }
    
    @Override
    public String getColumnName(int column) {
        String n = "";
        switch (column) {
            case 0:
                n = "ID";
                break;
            case 1:
                n = "Informacion";
                break;
            default:
                n = "[no]";
        }
        return n;
    }

    @Override
    public int getRowCount() {
        return datos.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object o = null;
        MultiSportCenter msc = datos.get(rowIndex);
        switch (columnIndex){
            case 0: o = msc.getId(); break;
            case 1: o = msc.getInformation(); break;
            default: o = "";
        }
        return o;
    }
}



