/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.frames.sportcomplex;

import Modelo.Headquarter;
import java.util.Objects;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class HeadquarterComboView {
    
    private Headquarter headquarter;

    public HeadquarterComboView(Headquarter headquarter) {
        this.headquarter = headquarter;
    }

    public Headquarter getHeadquarter() {
        return headquarter;
    }

    public void setHeadquarter(Headquarter headquarter) {
        this.headquarter = headquarter;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.headquarter);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HeadquarterComboView other = (HeadquarterComboView) obj;
        if (!Objects.equals(this.headquarter, other.headquarter)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return headquarter.getName()+" (ID: "+headquarter.getId()+")";
    }
    
    
    
}






