package Modelo.DAO.MySQL;

import Modelo.Area;
import Modelo.DAO.AreaDAO;
import Exceptions.DAOException;
import Modelo.MultiSportCenter;
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
public class MySQLAreaDAO implements AreaDAO {

    final String INSERT = "INSERT INTO area(id_multisportcenter,location,sport) VALUES(?,?,?)";
    final String UPDATE = "UPDATE area SET id_multisportcenter=?, location=?, sport=? WHERE id=?";
    final String DELETE = "DELETE FROM area WHERE id=?";
    final String GETALL = "SELECT * FROM area";
    final String GETONE = "SELECT * FROM area WHERE id=?";

    private Connection conn;

    public MySQLAreaDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Area a) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stat.setInt(1, a.getMsc().getId());
            stat.setString(2, a.getLocation());
            stat.setString(3, a.getSport());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Puede que no se haya guardado");
            }
            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                a.setId(rs.getInt(1));
            } else {
                throw new DAOException("No puedo asignar ID a esta Area");
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
    public void modify(Area a) throws DAOException {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(UPDATE);
            stat.setInt(1, a.getMsc().getId());
            stat.setString(2, a.getLocation());
            stat.setString(3, a.getSport());
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
    public void delete(Area a) throws DAOException {
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

    private Area convert(ResultSet rs)
            throws SQLException, DAOException {
        Integer id = rs.getInt("id");
        Integer id_multisportcenter = rs.getInt("id_multisportcenter");
        String location = rs.getString("location");
        String sport = rs.getString("sport");
        MySQLMultiSportCenterDAO aux = new MySQLMultiSportCenterDAO(conn);
        MultiSportCenter msc = aux.get(id_multisportcenter);
        Area area = new Area(location, sport, msc);
        area.setId(id);
        return area;
    }

    @Override
    public List<Area> getAll() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Area> areas = new ArrayList<>();
        try {
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                areas.add(convert(rs));
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
        return areas;
    }

    @Override
    public Area get(Integer id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Area area = null;
        if (id.intValue() != 0) {
            try {
                stat = conn.prepareStatement(GETONE);
                stat.setInt(1, id);
                rs = stat.executeQuery();
                if (rs.next()) {
                    area = convert(rs);
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
        }
        return area;
    }

}
