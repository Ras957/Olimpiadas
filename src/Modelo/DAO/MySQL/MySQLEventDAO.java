/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo.DAO.MySQL;

import Exceptions.DAOException;
import Modelo.Area;
import Modelo.DAO.EventDAO;
import Modelo.Event;
import Modelo.SportComplex;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba 
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class MySQLEventDAO implements EventDAO{
    
    final String INSERT = "INSERT INTO event(name,date,id_sportcomplex,id_area) VALUES(?,?,?,?)";
    final String UPDATE = "UPDATE event SET name=?, date=?, id_sportcomplex=?, id_area=? WHERE id=?";
    final String DELETE = "DELETE FROM event WHERE id=?";
    final String GETALL = "SELECT * FROM event";
    final String GETONE = "SELECT * FROM event WHERE id=?";

    private Connection conn;

    public MySQLEventDAO(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public void insert(Event a) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        try{
            stat = conn.prepareStatement(INSERT);
            stat.setString(1, a.getName());
            stat.setDate(2, (Date) a.getDate());
            stat.setInt(3, a.getComplex().getId());
            if (a.getArea() != null) {
                stat.setInt(4, a.getArea().getId());
            }else {
                stat.setNull(4, Types.INTEGER);
            }
            if (stat.executeUpdate() == 0){
                throw new DAOException("Puede que no se haya guardado");
            }
            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                a.setId(rs.getInt(1));
            }else{
                throw new DAOException("No puedo asignar ID a este Evento");
            }
        }catch(SQLException ex){
            throw new DAOException("Error en SQL", ex);
        }finally{
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
        try{
            stat = conn.prepareStatement(UPDATE);
            stat.setString(1, a.getName());
            stat.setDate(2, (Date) a.getDate());
            stat.setInt(3, a.getComplex().getId());
            stat.setInt(4, a.getArea().getId());
            stat.setInt(5, a.getId());
            if (stat.executeUpdate() == 0){
                throw new DAOException("Puede que no se haya guardado");
            }
        }catch(SQLException ex){
            throw new DAOException("Error en SQL", ex);
        }finally{
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
        try{
            stat = conn.prepareStatement(DELETE);
            stat.setInt(1, a.getId());
            if (stat.executeUpdate() == 0){
                throw new DAOException("Puede que no se haya guardado");
            }
        }catch(SQLException ex){
            throw new DAOException("Error en SQL", ex);
        }finally{
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                throw new DAOException("Error en SQL", ex);
            }
        }
    }
    
    private Event convert(ResultSet rs) throws SQLException, DAOException{
        String name = rs.getString("name");
        Date date = (Date)rs.getDate("date");
        MySQLSportComplexDAO aux = null;
        SportComplex sportComplex = aux.get(rs.getInt("id_sportcomplex"));
        //area es distinto porque puede ser null
        MySQLAreaDAO aux2 = null;
        Area area = aux2.get(rs.getInt("id_area"));
        if (rs.wasNull()) {
            area=null;
        }
        Event event = new Event(name, date, sportComplex, area);
        event.setId(rs.getInt("id"));
        return event;
    }

    @Override
    public List<Event> getAll() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Event> events = new ArrayList<>();
        try{
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()){
                events.add(convert(rs));
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        }finally{
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
        try{
            stat = conn.prepareStatement(GETONE);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if(rs.next()){
                event = convert(rs);
            }else{
                throw new DAOException("No se ha encontrado ese registro");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        }finally{
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

}
