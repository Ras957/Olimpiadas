/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.frames.sportcenter;

import Exceptions.DAOException;
import Modelo.DAO.SportCenterDAO;
import Modelo.SportCenter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class SportCenterTableModel extends AbstractTableModel {

    private SportCenterDAO sportCenters;

    private List<SportCenter> datos = new ArrayList<>();

    public SportCenterTableModel(SportCenterDAO sportCenters) {
        this.sportCenters = sportCenters;
    }

    public void updateModel() throws DAOException {
        datos = sportCenters.getAll();
    }

    @Override
    public String getColumnName(int column) {
        String n = "";
        switch (column) {
            case 0:
                n = "ID";
                break;
            case 1:
                n = "Deporte";
                break;
            case 2:
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
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object o = null;
        SportCenter sc = datos.get(rowIndex);
        switch (columnIndex){
            case 0: o = sc.getId(); break;
            case 1: o = sc.getSport(); break;
            case 2: o = sc.getInformation(); break;
            default: o = "";
        }
        return o;
    }

}

