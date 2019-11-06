/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista.frames.event;

import Modelo.Commissioner;
import java.util.Objects;

/**
 * 
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba 
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class CommissionerComboView {

    private Commissioner commissioner;

    public CommissionerComboView(Commissioner commissioner) {
        this.commissioner = commissioner;
    }

    public Commissioner getCommissioner() {
        return commissioner;
    }

    public void setCommissioner(Commissioner commissioner) {
        this.commissioner = commissioner;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.commissioner);
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
        final CommissionerComboView other = (CommissionerComboView) obj;
        if (!Objects.equals(this.commissioner, other.commissioner)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return commissioner.getName();
    }
    
    
}
