package Modelo;

import java.util.List;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class MultiSportCenter extends SportComplex{
    protected String information;
    protected int nAreas;

    public MultiSportCenter(SportComplex sc, String information) {
        super(sc.getLocation(), sc.getBoss(), sc.getHeadquarter());
        this.id = sc.getId();
        this.information = information;
        this.nAreas=0;
    }

    public MultiSportCenter() {
        super("", "", null);
        this.id = null;
        this.information = "";
        this.nAreas=0;
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

    public int getnAreas() {
        return nAreas;
    }

    public void setnAreas(int nAreas) {
        this.nAreas = nAreas;
    }
    
    @Override
    public String toString(){
        return "ID: "+this.id+" Información: "+this.information;
    }
}










