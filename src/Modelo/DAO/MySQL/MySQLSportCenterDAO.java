/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo.DAO.MySQL;

import Exceptions.DAOException;
import Modelo.DAO.SportCenterDAO;
import Modelo.SportCenter;
import Modelo.SportComplex;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba 
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class MySQLSportCenterDAO implements SportCenterDAO{
    
    final String INSERT = "INSERT INTO sportcenter(id_sportcomplex,sport,information) VALUES(?,?,?)";
    final String UPDATE = "UPDATE sportcenter SET sport=?, information=? WHERE id_sportcomplex=?";
    final String DELETE = "DELETE FROM sportcenter WHERE id_sportcomplex=?";
    final String GETALL = "SELECT * FROM sportcenter";
    final String GETONE = "SELECT * FROM sportcenter WHERE id_sportcomplex=?";
    
    private Connection conn;

    public MySQLSportCenterDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(SportCenter a) throws DAOException {
        PreparedStatement stat = null;
        try{
            stat = conn.prepareStatement(INSERT);
            stat.setInt(1, a.getId());
            stat.setString(2, a.getSport());
            stat.setString(3, a.getInformation());
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
    public void modify(SportCenter a) throws DAOException {
        PreparedStatement stat = null;
        try{
            stat = conn.prepareStatement(UPDATE);
            stat.setString(1, a.getSport());
            stat.setString(2, a.getInformation());
            stat.setInt(3, a.getId());
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
    public void delete(SportCenter a) throws DAOException {
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
    
    private SportCenter convert(ResultSet rs) throws SQLException, DAOException{
        MySQLSportComplexDAO aux = new MySQLSportComplexDAO(conn);
        SportComplex sportComplex = aux.get(rs.getInt("id_sportcomplex"));
        String sport = rs.getString("sport");
        String location = rs.getString("information");
        SportCenter sportCenter = new SportCenter(sportComplex, sport, location);
        return sportCenter;
    }

    @Override
    public List<SportCenter> getAll() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<SportCenter> sportCenters = new ArrayList<>();
        try{
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()){
                sportCenters.add(convert(rs));
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
        return sportCenters;
    }

    @Override
    public SportCenter get(Integer id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        SportCenter sportCenter = null;
        try{
            stat = conn.prepareStatement(GETONE);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if(rs.next()){
                sportCenter = convert(rs);
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
        return sportCenter;
    }
}
