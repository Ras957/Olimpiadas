/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista.frames.event;

import Modelo.Equipment;
import java.util.Objects;

/**
 * 
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba 
 * <afcarrasquilla@iesfranciscodelosrios.es>
 */
public class EquipmentComboView {

    private Equipment equip;

    public EquipmentComboView(Equipment equip) {
        this.equip = equip;
    }

    public Equipment getEquipment() {
        return equip;
    }

    public void setEquipment(Equipment equip) {
        this.equip = equip;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.equip);
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
        final EquipmentComboView other = (EquipmentComboView) obj;
        if (!Objects.equals(this.equip, other.equip)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return equip.getName();
    }
    
    
}
