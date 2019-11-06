package Modelo;

import java.util.List;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class MultiSportCenter extends SportComplex{
    protected String information;
    protected List<Area> areas;

    public MultiSportCenter(SportComplex sc, String information) {
        super(sc.getLocation(), sc.getBoss(), sc.getHeadquarter());
        this.id = sc.getId();
        this.information = information;
    }

    public MultiSportCenter() {
        super("", "", null);
        this.id = null;
        this.information = "";
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

    /**
     * @return the areas
     */
    public List<Area> getAreas() {
        return areas;
    }

    /**
     * @param areas the areas to set
     */
    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }
}








