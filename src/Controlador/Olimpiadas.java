/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.DAOManager;
import Modelo.DAO.MySQL.MySQLDAOManager;
import Vista.MainMenu;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class Olimpiadas {
    
    public static MainMenu menu;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        DAOManager manager = new MySQLDAOManager("localhost", "root", "", "olympics");
        menu = new MainMenu();
        menu.setVisible(true);
    }
    
}


