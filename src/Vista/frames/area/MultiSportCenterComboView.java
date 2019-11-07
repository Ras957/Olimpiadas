/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.frames.area;

import Modelo.MultiSportCenter;
import java.util.Objects;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
class MultiSportCenterComboView {
    
    private MultiSportCenter msc;
    
    public MultiSportCenterComboView(MultiSportCenter msc){
        this.msc = msc;
    }

    public MultiSportCenter getMsc() {
        return msc;
    }

    public void setMsc(MultiSportCenter msc) {
        this.msc = msc;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.msc);
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
        final MultiSportCenterComboView other = (MultiSportCenterComboView) obj;
        if (!Objects.equals(this.msc, other.msc)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return msc.getLocation()+" (ID:"+msc.getId()+")";
    }
    
    
    
}




