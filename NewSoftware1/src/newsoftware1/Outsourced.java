/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newsoftware1;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Dwils
 */
public class Outsourced extends Part {
    private StringProperty companyName;

    public StringProperty getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }
    

    public Outsourced() {
        super();
        companyName = new SimpleStringProperty();
    }

}
