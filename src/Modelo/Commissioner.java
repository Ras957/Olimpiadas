/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Exceptions.DNIException;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfrancisidlosrios.es>
 */
public class Commissioner {

    protected Integer id = null;
    protected String dni;
    protected String name;

    /*public Commissioner(String dni, String name) throws DNIException {
        if (validateDNI(dni)) {
            this.dni = dni.toUpperCase();
            this.name = name;
        }else{
            throw new DNIException("El DNI no es válido");
        }
    }*/

    public Commissioner(String dni, String name) {
        this.dni = dni;
        this.name = name;
    }
    

    public Commissioner() {
        this.dni = "";
        this.name = "";
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    private boolean validateDNI(String dni) {
        String capLetter = "";
        if (dni.length() != 9 || Character.isLetter(dni.charAt(8)) == false) {
            return false;
        }
        capLetter = (dni.substring(8)).toUpperCase();
        if (onlyNums(dni) == true && letterDNI(dni).equals(capLetter)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean onlyNums(String dni) {
        int i, j = 0;
        String num = ""; 
        String myDNI = "";
        String[] nums = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        for (i = 0; i < dni.length() - 1; i++) {
            num = dni.substring(i, i + 1);

            for (j = 0; j < nums.length; j++) {
                if (num.equals(nums[j])) {
                    myDNI += nums[j];
                }
            }
        }
        if (myDNI.length() != 8) {
            return false;
        } else {
            return true;
        }
    }

    private String letterDNI(String dni) {
        int myDNI = Integer.parseInt(dni.substring(0, 8));
        int rest = 0;
        String myLetter = "";
        String[] letters = {"T", "R", "W", "A", "G", "M", "Y", 
            "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V",
                "H", "L", "C", "K", "E"};
        rest = myDNI % 23;
        myLetter = letters[rest];
        return myLetter;
    }
}











