/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo.DAO.MySQL;

import Exceptions.DAOException;
import Modelo.Equipment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Modelo.DAO.EquipmentDAO;
import java.sql.Statement;

/**
 * 
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba 
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class MySQLEquipmentDAO implements EquipmentDAO{
    
    final String INSERT = "INSERT INTO equipment(name) VALUES(?)";
    final String UPDATE = "UPDATE equipment SET name=? WHERE id=?";
    final String DELETE = "DELETE FROM equipment WHERE id=?";
    final String GETALL = "SELECT * FROM equipment";
    final String GETONE = "SELECT * FROM equipment WHERE id=?";

    private Connection conn;

    public MySQLEquipmentDAO(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public void insert(Equipment a) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        try{
            stat = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stat.setString(1, a.getName());
            if (stat.executeUpdate() == 0){
                throw new DAOException("Puede que no se haya guardado");
            }
            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                a.setId(rs.getInt(1));
            }else{
                throw new DAOException("No puedo asignar ID a este Equipamiento");
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
    public void modify(Equipment a) throws DAOException {
        PreparedStatement stat = null;
        try{
            stat = conn.prepareStatement(UPDATE);
            stat.setString(1, a.getName());
            stat.setInt(2, a.getId());
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
    public void delete(Equipment a) throws DAOException {
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
    
    private Equipment convert(ResultSet rs) throws SQLException{
        String name = rs.getString("name");
        Equipment equip = new Equipment(name);
        equip.setId(rs.getInt("id"));
        return equip;
    }

    @Override
    public List<Equipment> getAll() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Equipment> equipements = new ArrayList<>();
        try{
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()){
                equipements.add(convert(rs));
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
        return equipements;
    }

    @Override
    public Equipment get(Integer id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Equipment equip = null;
        try{
            stat = conn.prepareStatement(GETONE);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if(rs.next()){
                equip = convert(rs);
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
        return equip;
    }
}
