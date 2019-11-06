/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO.MySQL;

import Exceptions.DAOException;
import Modelo.DAO.HeadquarterDAO;
import Modelo.DAO.SportComplexDAO;
import Modelo.Headquarter;
import Modelo.SportComplex;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class MySQLSportComplexDAO implements SportComplexDAO {

    final String INSERT = "INSERT INTO SportComplex(location,boss,id_headquarter) VALUES(?,?,?)";
    final String UPDATE = "UPDATE SportComplex SET location=?, boss=?, id_headquarter=? WHERE id=?";
    final String DELETE = "DELETE FROM SportComplex WHERE id=?";
    final String GETALL = "SELECT * FROM SportComplex";
    final String GETONE = "SELECT * FROM SportComplex WHERE id=?";

    private Connection conn;

    public MySQLSportComplexDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(SportComplex a) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stat.setString(1, a.getLocation());
            stat.setString(2, a.getBoss());
            stat.setInt(3, a.getHeadquarter().getId());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Puede que no se haya guardado");
            }
            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                a.setId(rs.getInt(1));
            } else {
                throw new DAOException("No puedo asignar ID a este Complejo");
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
    public void modify(SportComplex a) throws DAOException {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(UPDATE);
            stat.setString(1, a.getLocation());
            stat.setString(2, a.getBoss());
            stat.setInt(3, a.getHeadquarter().getId());
            stat.setInt(4, a.getId());
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

    @Override
    public void delete(SportComplex a) throws DAOException {
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

    private SportComplex convert(ResultSet rs)
            throws SQLException, DAOException {
        String location = rs.getString("location");
        String boss = rs.getString("boss");
        Integer idHq = rs.getInt("id_headquarter");
        //MySQLDAOManager man = new MySQLDAOManager("localhost", "root", "", "olympics");
        MySQLHeadquarterDAO aux = new MySQLHeadquarterDAO(conn);
        //Headquarter hq = man.getHeadquarterDAO().get(idHq);
        Headquarter hq = aux.get(idHq);
        SportComplex sportComplex
                = new SportComplex(location, boss, hq);
        sportComplex.setId(rs.getInt("id"));
        return sportComplex;
    }

    @Override
    public List<SportComplex> getAll() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<SportComplex> sportComplexes = new ArrayList<>();
        try {
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                sportComplexes.add(convert(rs));
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
        return sportComplexes;
    }

    @Override
    public SportComplex get(Integer id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        SportComplex sportComplex = null;
        try {
            stat = conn.prepareStatement(GETONE);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                sportComplex = convert(rs);
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
        return sportComplex;
    }

    /*
    public static void main(String[] args) throws SQLException, DAOException{
        Connection conn = null;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost/olympics", "root", "");
            SportComplexDAO dao = new MySQLSportComplexDAO(conn);
            HeadquarterDAO daoH = new MySQLHeadquarterDAO(conn);
            Headquarter hq = daoH.get(1);
            SportComplex mySc = new SportComplex("Otro sitio", "Jose",hq);
            dao.insert(mySc);
            System.out.println("El complejo se ha generado con ID " + mySc.getId());
         }finally{
            if (conn != null){
                conn.close();
            }
        }
    }*/
}
