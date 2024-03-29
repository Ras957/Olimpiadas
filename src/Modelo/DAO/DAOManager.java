/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public interface DAOManager {
    
    HeadquarterDAO getHeadquarterDAO();
    
    SportComplexDAO getSportComplexDAO();
    
    SportCenterDAO getSportCenterDAO();
    
    MultiSportCenterDAO getMultiSportCenterDAO();
    
    AreaDAO getAreaDAO();
    
    CommissionerDAO getCommissionerDAO();
    
    EquipmentDAO getEquipmentDAO();
    
    EventDAO getEventDAO();
}



