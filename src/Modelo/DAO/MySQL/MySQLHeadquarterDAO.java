/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo.DAO.MySQL;

import Exceptions.DAOException;
import Modelo.DAO.HeadquarterDAO;
import Modelo.Headquarter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBException;

/**
 * 
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba 
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class MySQLHeadquarterDAO implements HeadquarterDAO{
    
    public static final String INSERT = "INSERT INTO headquarter(name,budget) VALUES(?,?)";
    public static final String UPDATE = "UPDATE headquarter SET name=?, budget=? WHERE id=?";
    public static final String DELETE = "DELETE FROM headquarter WHERE id=?";
    public static final String GETALL = "SELECT * FROM headquarter";
    public static final String GETONE = "SELECT * FROM headquarter WHERE id=?";

    private Connection conn;

    public MySQLHeadquarterDAO(Connection conn) {
        this.conn = conn;
    } 

    @Override
    public void insert(Headquarter a) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        try{
            stat = conn.prepareStatement(INSERT , Statement.RETURN_GENERATED_KEYS);
            stat.setString(1, a.getName());
            stat.setFloat(2, a.getBudget());
            if (stat.executeUpdate() == 0){
                throw new DAOException("Puede que no se haya guardado");
            }
            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                a.setId(rs.getInt(1));
            }else{
                throw new DAOException("No puedo asignar ID a esta Sede");
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
    public void modify(Headquarter a) throws DAOException {
        PreparedStatement stat = null;
        try{
            stat = conn.prepareStatement(UPDATE);
            stat.setString(1, a.getName());
            stat.setFloat(2, a.getBudget());
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
    public void delete(Headquarter a) throws DAOException {
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
    
    private Headquarter convert(ResultSet rs) throws SQLException{
        String name = rs.getString("name");
        float budget = rs.getFloat("budget");
        Headquarter headquarter = new Headquarter(name, budget);
        headquarter.setId(rs.getInt("id"));
        return headquarter;
    }

    @Override
    public List<Headquarter> getAll() throws DAOException{
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Headquarter> headquarters = new ArrayList<>();
        try{
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()){
                headquarters.add(convert(rs));
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
        return headquarters;
    }

    @Override
    public Headquarter get(Integer id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Headquarter headquarter = null;
        try{
            stat = conn.prepareStatement(GETONE);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if(rs.next()){
                headquarter = convert(rs);
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
        return headquarter;
    }
    
    /*
    public static void main(String[] args) throws SQLException, DAOException{
        Connection conn = null;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost/olympics", "root", "");
            HeadquarterDAO dao = new MySQLHeadquarterDAO(conn);
            Headquarter myHq = new Headquarter("Valencia", 5000000);
            dao.insert(myHq);
            System.out.println("La sede se ha generado con ID " + myHq.getId());
         }finally{
            if (conn != null){
                conn.close();
            }
        }
    }*/

}
