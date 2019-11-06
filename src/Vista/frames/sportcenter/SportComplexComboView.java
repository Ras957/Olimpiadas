/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.frames.sportcenter;

import Modelo.SportComplex;
import java.util.Objects;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class SportComplexComboView {
    
    private SportComplex sportComplex;

    public SportComplexComboView(SportComplex sportComplex) {
        this.sportComplex = sportComplex;
    }

    public SportComplex getSportComplex() {
        return sportComplex;
    }

    public void setSportComplex(SportComplex sportComplex) {
        this.sportComplex = sportComplex;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.sportComplex);
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
        final SportComplexComboView other = (SportComplexComboView) obj;
        if (!Objects.equals(this.sportComplex, other.sportComplex)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SportComplexID: " + sportComplex.getId();
    }
    
    
    
}



