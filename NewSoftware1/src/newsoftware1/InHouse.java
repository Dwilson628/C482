/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newsoftware1;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Dwils
 */
public class InHouse extends Part {
    private IntegerProperty machineId;

    public IntegerProperty getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId.set(machineId);
    }

    public InHouse() {
        super();
        machineId = new SimpleIntegerProperty();
    }
    
    
}
