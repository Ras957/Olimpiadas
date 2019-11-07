/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfrancisidlosrios.es>
 */
public class Equipment {
    protected Integer id;
    protected String name;
    protected boolean maintenance;

    public Equipment(String name) {
        this.name = name;
    }

    public Equipment(String name, boolean maintenance) {
        this.name = name;
        this.maintenance = maintenance;
    }

    public Equipment() {
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

    /**
     * @return the maintenance
     */
    public boolean isMaintenance() {
        return maintenance;
    }

    /**
     * @param maintenance the maintenance to set
     */
    public void setMaintenance(boolean maintenance) {
        this.maintenance = maintenance;
    }
    
    /*@Override
    public String toString(){
        return "ID: "+this.id+" Nombre: "+this.name;
    }*/
    @Override
    public String toString() {
        return this.name+" ("+this.id+")";
    }
    
}









