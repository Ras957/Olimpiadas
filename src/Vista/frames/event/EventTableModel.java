/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.frames.event;

import Exceptions.DAOException;
import Modelo.Commissioner;
import Modelo.DAO.EventDAO;
import Modelo.Equipment;
import Modelo.Event;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class EventTableModel extends AbstractTableModel {

    private EventDAO events;

    private List<Event> datos = new ArrayList<>();

    public EventTableModel(EventDAO events) {
        this.events = events;
    }

    public void updateModel() throws DAOException {
        datos = events.getAll();
    }

    @Override
    public String getColumnName(int column) {
        String n = "";
        switch (column) {
            case 0:
                n = "ID";
                break;
            case 1:
                n = "Nombre";
                break;
            case 2:
                n = "Complejo";
                break;
            case 3:
                n = "Fecha";
                break;
            case 4:
                n = "Area";
                break;
            case 5:
                n = "Materiales";
                break;
            case 6:
                n = "Comisarios";
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
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object o = null;
        Event e = datos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                o = e.getId();
                break;
            case 1:
                o = e.getName();
                break;
            case 2:
                o = e.getComplex();
                break;
            case 3:
                DateFormat df = DateFormat.getDateInstance();
                o = df.format(e.getDate());
                break;
            case 4:
                o = e.getArea();
                break;
            case 5:
                String materiales ="";
                if (!e.getEquip().isEmpty()) {
                    for (int i = 0; i < e.getEquip().size(); i++) {
                        materiales += e.getEquip().get(i).getName();
                        if (i != e.getEquip().size()-1) {
                            materiales += ",";
                        }
                    }
                }
                o = materiales;
                break;
            case 6:
                String comisarios ="";
                if (!e.getCommissioners().isEmpty()) {
                   for (Map.Entry<Commissioner,String> com : e.getCommissioners().entrySet()) {
                        comisarios += com.getKey().getName()+"("+com.getValue()+"), ";
                    }
                   comisarios = comisarios.substring(0, comisarios.length() - 2);
                }
                o = comisarios;
                break;
            default:
                o = "";
        }
        return o;
    }
}
