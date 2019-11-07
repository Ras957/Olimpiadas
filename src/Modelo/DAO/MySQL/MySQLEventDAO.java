/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO.MySQL;

import Exceptions.DAOException;
import Modelo.Area;
import Modelo.Commissioner;
import Modelo.DAO.EventDAO;
import Modelo.Equipment;
import Modelo.Event;
import Modelo.SportComplex;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class MySQLEventDAO implements EventDAO {

    final String INSERT = "INSERT INTO event(name,date,id_sportcomplex,id_area) VALUES(?,?,?,?)";
    final String INSERT_CE = "INSERT INTO comissioner_event(id_event,id_comissioner,rol) VALUES(?,?,?)";
    final String INSERT_EE = "INSERT INTO equipment_event(id_equipment,id_event) VALUES(?,?)";
    final String UPDATE = "UPDATE event SET name=?, date=?, id_sportcomplex=?, id_area=? WHERE id=?";
    final String UPDATE_CE = "UPDATE comissioner_event SET id_event=?, id_comissioner=?, rol=? WHERE id=?";
    final String UPDATE_EE = "UPDATE equipment_event SET id_equipment=?, id_event=? WHERE id=?";
    final String DELETE = "DELETE FROM event WHERE id=?";
    final String DELETE_CE = "DELETE FROM comissioner_event WHERE id=?";
    final String DELETE_EE = "DELETE FROM equipment_event WHERE id=?";
    final String GETALL = "SELECT * FROM event";
    final String GETALL_CE = "SELECT * FROM comissioner_event WHERE id_event=?";
    final String GETALL_EE = "SELECT * FROM equipment_event WHERE id_event=?";
    final String GETONE = "SELECT * FROM event WHERE id=?";
    final String GETONE_CE = "SELECT id FROM comissioner_event WHERE id_event=7 AND id_comissioner=1 AND rol like ?";
    //final String GETONE_CE = "SELECT * FROM comissioner_event WHERE id=?";
    final String GETONE_EE = "SELECT id FROM equipment_event WHERE id_equipment=? AND id_event=?";
    //final String GETONE_EE = "SELECT * FROM equipment_event WHERE id=?";

    private Connection conn;

    public MySQLEventDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Event a) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stat.setString(1, a.getName());
            stat.setDate(2, (Date) a.getDate());
            stat.setInt(3, a.getComplex().getId());
            if (a.getArea() != null) {
                stat.setInt(4, a.getArea().getId());
            } else {
                stat.setNull(4, Types.INTEGER);
            }
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Puede que no se haya guardado");
            }
            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                a.setId(rs.getInt(1));
            } else {
                throw new DAOException("No puedo asignar ID a este Evento");
            }
            if (a.getEquip() != null) {
                for (Equipment e : a.getEquip()) {
                    stat = conn.prepareStatement(INSERT_EE);
                    stat.setInt(1, e.getId());
                    stat.setInt(2, a.getId());
                    if (stat.executeUpdate() == 0) {
                        throw new DAOException("Puede que no se haya guardado");
                    }
                } 
            }
            if (a.getCommissioners() != null) {
                for (Entry<Commissioner,String> e : a.getCommissioners().entrySet()) {
                    stat = conn.prepareStatement(INSERT_CE);
                    stat.setInt(1, a.getId());
                    stat.setInt(2, e.getKey().getId());
                    stat.setString(3, e.getValue());
                    if (stat.executeUpdate() == 0) {
                        throw new DAOException("Puede que no se haya guardado");
                    }
                } 
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                throw new DAOException("Error en SQL", ex);
            }
        }
    }

    @Override
    public void modify(Event a) throws DAOException {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(UPDATE);
            stat.setString(1, a.getName());
            stat.setDate(2, (Date) a.getDate());
            stat.setInt(3, a.getComplex().getId());
            stat.setInt(4, a.getArea().getId());
            stat.setInt(5, a.getId());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Puede que no se haya guardado");
            }
            if (a.getEquip() != null) {
                for (Equipment e : a.getEquip()) {
                    stat = conn.prepareStatement(UPDATE_EE);
                    stat.setInt(1, e.getId());
                    stat.setInt(2, a.getId());
                    //id de la tubla equipement_event
                    stat.setInt(3, getIdEquEve(e.getId(), a.getId()));
                    if (stat.executeUpdate() == 0) {
                        throw new DAOException("Puede que no se haya guardado");
                    }
                } 
            }
            if (a.getCommissioners() != null) {
                for (Entry<Commissioner,String> e : a.getCommissioners().entrySet()) {
                    stat = conn.prepareStatement(UPDATE_CE);
                    stat.setInt(1, a.getId());
                    stat.setInt(2, e.getKey().getId());
                    stat.setString(3, e.getValue());
                    //id de la tabla comissioner_event
                    stat.setInt(4, getIdComEve(a.getId(), e.getKey().getId(), e.getValue()));
                    if (stat.executeUpdate() == 0) {
                        throw new DAOException("Puede que no se haya guardado");
                    }
                } 
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                throw new DAOException("Error en SQL", ex);
            }
        }
    }

    @Override
    public void delete(Event a) throws DAOException {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(DELETE);
            stat.setInt(1, a.getId());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Puede que no se haya guardado");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                throw new DAOException("Error en SQL", ex);
            }
        }
    }

    private Event convert(ResultSet rs) throws SQLException, DAOException {
        String name = rs.getString("name");
        Date date = (Date) rs.getDate("date");
        MySQLSportComplexDAO aux = new MySQLSportComplexDAO(conn);
        SportComplex sportComplex = aux.get(rs.getInt("id_sportcomplex"));
        //area es distinto porque puede ser null
        MySQLAreaDAO aux2 = new MySQLAreaDAO(conn);
        Area area = aux2.get(rs.getInt("id_area"));
        if (rs.wasNull()) {
            area = null;
        }
        Event event = new Event(name, date, sportComplex, area);
        event.setId(rs.getInt("id"));
        MySQLEquipmentDAO aux3 = new MySQLEquipmentDAO(conn);
        MySQLCommissionerDAO aux4 = new MySQLCommissionerDAO(conn);
        PreparedStatement stat = conn.prepareStatement(GETALL_EE);
        stat.setInt(1, event.getId());
        rs = stat.executeQuery();
        while (rs.next()) {
            event.getEquip().add(aux3.get(rs.getInt("id_equipment")));
        }
        stat = conn.prepareStatement(GETALL_CE);
        stat.setInt(1, event.getId());
        rs = stat.executeQuery();
        while (rs.next()){
            event.getCommissioners().put(aux4.get(rs.getInt("id_comissioner")),
                    rs.getString("rol"));
        }
        stat.close();
        return event;
    }

    @Override
    public List<Event> getAll() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Event> events = new ArrayList<>();
        try {
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                events.add(convert(rs));
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                throw new DAOException("Error en SQL", ex);
            }
        }
        return events;
    }

    @Override
    public Event get(Integer id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Event event = null;
        try {
            stat = conn.prepareStatement(GETONE);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                event = convert(rs);
            } else {
                throw new DAOException("No se ha encontrado ese registro");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                throw new DAOException("Error en SQL", ex);
            }
        }
        return event;
    }
    
    public int getIdComEve(int id_event, int id_comissioner, String rol) throws DAOException{
        PreparedStatement stat = null;
        ResultSet rs = null;
        int id = 0;
        try{
            stat = conn.prepareStatement(GETONE_CE);
            stat.setInt(1, id_event);
            stat.setInt(2, id_comissioner);
            stat.setString(3, rol);
            rs = stat.executeQuery();
            id = rs.getInt("id");
        }catch(SQLException ex){
            throw new DAOException("Error en SQL", ex);
        }finally {
            try {
                if (stat != null) {
                    stat.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                throw new DAOException("Error en SQL", ex);
            }
        }
        return id;
    }
    
    public int getIdEquEve(int id_equip, int id_event) throws DAOException{
        PreparedStatement stat = null;
        ResultSet rs = null;
        int id = 0;
        try{
            stat = conn.prepareStatement(GETONE_EE);
            stat.setInt(1, id_equip);
            stat.setInt(2, id_event);
            rs = stat.executeQuery();
            id = rs.getInt("id");
        }catch(SQLException ex){
            throw new DAOException("Error en SQL", ex);
        }finally {
            try {
                if (stat != null) {
                    stat.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                throw new DAOException("Error en SQL", ex);
            }
        }
        return id;
    }

}
