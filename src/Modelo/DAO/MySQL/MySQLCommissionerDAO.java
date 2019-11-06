package Modelo.DAO.MySQL;

import Modelo.Commissioner;
import Modelo.DAO.CommissionerDAO;
import Exceptions.DAOException;
import Exceptions.DNIException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba 
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class MySQLCommissionerDAO implements CommissionerDAO{
    
    final String INSERT = "INSERT INTO commissioner(dni,name) VALUES(?,?)";
    final String UPDATE = "UPDATE commissioner SET dni=?, name=? WHERE id=?";
    final String DELETE = "DELETE FROM commissioner WHERE id=?";
    final String GETALL = "SELECT * FROM commissioner";
    final String GETONE = "SELECT * FROM commissioner WHERE id=?";
    
    private Connection conn;

    public MySQLCommissionerDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Commissioner a) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        try{
            stat = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stat.setString(1, a.getDni());
            stat.setString(2, a.getName());
            if (stat.executeUpdate() == 0){
                throw new DAOException("Puede que no se haya guardado");
            }
            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                a.setId(rs.getInt(1));
            }else{
                throw new DAOException("No puedo asignar ID a este Comisario");
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
    public void modify(Commissioner a) throws DAOException {
        PreparedStatement stat = null;
        try{
            stat = conn.prepareStatement(UPDATE);
            stat.setString(1, a.getDni());
            stat.setString(2, a.getName());
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
    public void delete(Commissioner a) throws DAOException {
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
    
    private Commissioner convert(ResultSet rs) 
            throws SQLException, DNIException{
        String dni = rs.getString("dni");
        String name = rs.getString("name");
        Commissioner commissioner = new Commissioner(dni, name);
        commissioner.setId(rs.getInt("id"));
        return commissioner;
    }

    @Override
    public List<Commissioner> getAll() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Commissioner> comissioners = new ArrayList<>();
        try{
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()){
                try {
                    comissioners.add(convert(rs));
                } catch (DNIException ex) {
                    ex.getMessage();
                }
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
        return comissioners;
    }

    @Override
    public Commissioner get(Integer id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Commissioner commissioner = null;
        try{
            stat = conn.prepareStatement(GETONE);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if(rs.next()){
                try {
                    commissioner = convert(rs);
                } catch (DNIException ex) {
                    ex.getMessage();
                }
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
        return commissioner;
    }

}
