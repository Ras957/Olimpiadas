/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfrancisidlosrios.es>
 */
public class Event {

    protected Integer id;
    protected String name;
    protected SportComplex complex;
    protected Date date;
    protected Area area;
    protected List<Equipment> equip;
    protected HashMap<Commissioner, String> commissioners;

    public Event(String name, Date date, SportComplex complex,
            Area area) {
        this.name = name;
        this.complex = complex;
        this.date = date;
        this.area = area;
        this.equip = new ArrayList<>();
        this.commissioners = new HashMap<>();
    }

    public Event(String name, SportComplex complex, Date date, Area area, List<Equipment> equip, HashMap<Commissioner, String> commissioners) {
        this.name = name;
        this.complex = complex;
        this.date = date;
        this.area = area;
        this.equip = equip;
        this.commissioners = commissioners;
    }

    public Event() {
        this.name = "";
        this.complex = null;
        this.date = null;
        this.area = null;
        this.equip = new ArrayList<>();
        this.commissioners = new HashMap<>();
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
     * @return the complex
     */
    public SportComplex getComplex() {
        return complex;
    }

    /**
     * @param complex the complex to set
     */
    public void setComplex(SportComplex complex) {
        this.complex = complex;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the area
     */
    public Area getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(Area area) {
        this.area = area;
    }

    /**
     * @return the equip
     */
    public List<Equipment> getEquip() {
        return equip;
    }

    /**
     * @param equip the equip to set
     */
    public void setEquip(List<Equipment> equip) {
        this.equip = equip;
    }

    /**
     * @return the commissars
     */
    public HashMap<Commissioner, String> getCommissioners() {
        return commissioners;
    }

    /**
     * @param commissars the commissars to set
     */
    public void setCommissioners(HashMap<Commissioner, String> commissioners) {
        this.commissioners = commissioners;
    }
    
    @Override
    public String toString(){
        String aux = "ID: "+this.id+" Nombre: "+this.name+" Fecha "+this.date+
                " Complejo: "+this.complex.getId();
        if (this.area != null) {
            aux += " Area: "+this.area.getId();
        }
        if (!this.equip.isEmpty()){
            aux += "\n Materiales: \n";
            for (Equipment e : this.equip) {
                aux += e.toString()+"\n";
            }
        }
        if(!this.commissioners.isEmpty()){
            aux += " Comisarios: \n";
            for (Entry<Commissioner,String> e : this.getCommissioners().entrySet()) {
                aux += e.toString();
            }
        }
        return aux;
    }
}







