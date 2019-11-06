/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Exceptions;

/**
 * 
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class DNIException extends Exception{

    public DNIException(String message) {
        super(message);
    }

    public DNIException(String message, Throwable cause) {
        super(message, cause);
    }

    public DNIException(Throwable cause) {
        super(cause);
    }
}
