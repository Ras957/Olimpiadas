/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo.DAO.MySQL;

import Exceptions.DAOException;
import Modelo.DAO.MultiSportCenterDAO;
import Modelo.MultiSportCenter;
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
public class MySQLMultiSportCenterDAO implements MultiSportCenterDAO{
    
    final String INSERT = "INSERT INTO multisportcenter(id_sportcomplex, information) VALUES(?,?)";
    final String UPDATE = "UPDATE multisportcenter SET information=? WHERE id_sportcomplex=?";
    final String DELETE = "DELETE FROM multisportcenter WHERE id_sportcomplex=?";
    final String GETALL = "SELECT * FROM multisportcenter";
    final String GETONE = "SELECT * FROM multisportcenter WHERE id_sportcomplex=?";
    
    private Connection conn;

    public MySQLMultiSportCenterDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(MultiSportCenter a) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        try{
            stat = conn.prepareStatement(INSERT);
            stat.setInt(1, a.getId());
            stat.setString(2, a.getInformation());
            if (stat.executeUpdate() == 0){
                throw new DAOException("Puede que no se haya guardado");
            }
            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                a.setId(rs.getInt(1));
            }else{
                throw new DAOException("No puedo asignar ID a este Polideportivo");
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
    public void modify(MultiSportCenter a) throws DAOException {
        PreparedStatement stat = null;
        try{
            stat = conn.prepareStatement(UPDATE);
            stat.setString(1, a.getInformation());
            stat.setInt(2, a.getId());
            if (stat.executeUpdate() == 0){
                throw new DAOException("Puede que no se haya guardado");
            }
        }catch(SQLException ex){
            throw new DAOException("Error en SQL", ex);
        }finally{
            try {
                stat.close();
            } catch (SQLException ex) {
                throw new DAOException("Error en SQL", ex);
            }
        }
    }

    @Override
    public void delete(MultiSportCenter a) throws DAOException {
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
    
    private MultiSportCenter convert(ResultSet rs) throws SQLException, DAOException{
        MySQLSportComplexDAO aux = new MySQLSportComplexDAO(conn);
        SportComplex sportComplex = aux.get(rs.getInt("id_sportcomplex"));
        String information = rs.getString("information");
        MultiSportCenter msc = new MultiSportCenter(sportComplex, information);
        return msc;
    }

    @Override
    public List<MultiSportCenter> getAll() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<MultiSportCenter> multiSportCenters = new ArrayList<>();
        try{
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()){
                multiSportCenters.add(convert(rs));
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
        return multiSportCenters;
    }

    @Override
    public MultiSportCenter get(Integer id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        MultiSportCenter multiSportCenter = null;
        try{
            stat = conn.prepareStatement(GETONE);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if(rs.next()){
                multiSportCenter = convert(rs);
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
        return multiSportCenter;
    }
}
