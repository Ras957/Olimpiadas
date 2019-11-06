/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo.DAO.MySQL;

import Modelo.DAO.AreaDAO;
import Modelo.DAO.CommissionerDAO;
import Modelo.DAO.DAOException;
import Modelo.DAO.DAOManager;
import Modelo.DAO.EquipementDAO;
import Modelo.DAO.EventDAO;
import Modelo.DAO.HeadquarterDAO;
import Modelo.DAO.MultiSportCenterDAO;
import Modelo.DAO.SportCenterDAO;
import Modelo.DAO.SportComplexDAO;
import Modelo.Headquarter;
import Modelo.SportComplex;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * 
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba 
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class MySQLDAOManager implements DAOManager{
    
    private Connection conn;
    
    private HeadquarterDAO headquarters = null;
    private SportComplexDAO sportComplexes = null;
    private SportCenterDAO sportCenters = null;
    private MultiSportCenterDAO multiSportCenters = null;
    private AreaDAO areas = null;
    private CommissionerDAO commissioners = null;
    private EquipementDAO equipements = null;
    private EventDAO events = null;

    public MySQLDAOManager(String host, String username, String password, String database) throws Exception {
        conn = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database, username, password);
    }
    
    @Override
    public HeadquarterDAO getHeadquarterDAO() {
        if (headquarters == null) {
            headquarters = new MySQLHeadquarterDAO(conn);
        }
        return headquarters;
    }

    @Override
    public SportComplexDAO getSportComplexDAO() {
        if (sportComplexes == null) {
            sportComplexes = new MySQLSportComplexDAO(conn);
        }
        return sportComplexes;
    }

    @Override
    public SportCenterDAO getSportCenterDAO() {
        if (sportCenters == null) {
            sportCenters = new MySQLSportCenterDAO(conn);
        }
        return sportCenters;
    }

    @Override
    public MultiSportCenterDAO getMultiSportCenterDAO() {
        if (multiSportCenters == null) {
            multiSportCenters = new MySQLMultiSportCenterDAO(conn);
        }
        return multiSportCenters;
    }

    @Override
    public AreaDAO getAreaDAO() {
        if (areas == null) {
            areas = new MySQLAreaDAO(conn);
        }
        return areas;
    }

    @Override
    public CommissionerDAO getCommissionerDAO() {
        if (commissioners == null) {
            commissioners = new MySQLCommissionerDAO(conn);
        }
        return commissioners;
    }

    @Override
    public EquipementDAO getEquipementDAO() {
        if (equipements == null) {
            equipements = new MySQLEquipementDAO(conn);
        }
        return equipements;
    }

    @Override
    public EventDAO getEventDAO() {
        if (events == null) {
            events = new MySQLEventDAO(conn);
        }
        return events;
    }
    
    /*
    public static void main(String[] args) throws SQLException, DAOException, Exception{
        MySQLDAOManager man = new MySQLDAOManager("localhost", "root", "", "olympics");
        List<SportComplex> hqs = man.getSportComplexDAO().getAll();
        System.out.println(hqs+"\n");
    }*/
}
