/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo.DAO;

import Exceptions.DAOException;
import java.util.List;

/**
 * 
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba 
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public interface DAO<T, K> {
    
    void insert(T a) throws DAOException;
    
    void modify(T a) throws DAOException;
    
    void delete (T a) throws DAOException;
    
    List<T> getAll() throws DAOException;
    
    T get(K id) throws DAOException;
}
