/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class SportCenter extends SportComplex{
    protected String sport;
    protected String information;

    public SportCenter(SportComplex sc, String sport,
            String information) {
        super(sc.getLocation(), sc.getBoss(), sc.getHeadquarter());
        this.id = sc.getId();
        this.sport= sport;
        this.information = information;
    }

    public SportCenter() {
        super("", "", null);
        this.id = null;
        this.sport= "";
        this.information = "";
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
     * @return the information
     */
    public String getInformation() {
        return information;
    }

    /**
     * @param information the information to set
     */
    public void setInformation(String information) {
        this.information = information;
    }
    
    @Override
    public String toString(){
        return "ID: "+this.id+" Deporte: "+this.sport+
                " Información: "+this.information;
    }
}









