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
public class Area {
    protected Integer id = null;
    protected String location;
    protected String sport;
    protected MultiSportCenter msc;
    
    public Area(String location, String sport, MultiSportCenter msc) {
        this.location = location;
        this.sport = sport;
        this.msc = msc;
    }

    public Area() {
        this.location = "";
        this.sport = "";
        this.msc = null;
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
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the sport
     */
    public String getSport() {
        return sport;
    }

    /**
     * @param sport the sport to set
     */
    public void setSport(String sport) {
        this.sport = sport;
    }

    /**
     * @return the msc
     */
    public MultiSportCenter getMsc() {
        return msc;
    }

    /**
     * @param msc the msc to set
     */
    public void setMsc(MultiSportCenter msc) {
        this.msc = msc;
    }
    
    /*@Override
    public String toString(){
        return "ID: "+this.id+" Localización: "+this.location+" Deporte: "+this.sport;
    }*/

    @Override
    public String toString() {
        return this.location+" ("+this.id+")";
    }
    
}













