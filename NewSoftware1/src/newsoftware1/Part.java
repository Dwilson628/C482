/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newsoftware1;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Dwils
 */
public abstract class Part {
    private IntegerProperty id;
    private StringProperty name;
    private DoubleProperty price;
    private IntegerProperty stock;
    private IntegerProperty min;
    private IntegerProperty max;

    public Part() {
        id = new SimpleIntegerProperty();
        name = new SimpleStringProperty();
        price = new SimpleDoubleProperty();
        stock = new SimpleIntegerProperty();
        min = new SimpleIntegerProperty();
        max = new SimpleIntegerProperty();
    }

    
    public IntegerProperty getId() {
        return id;
    }
    
    public void setId(int newId){
        this.id.set(newId);
    }

    public StringProperty getName() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public DoubleProperty getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public IntegerProperty getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock.set(stock);
    }

    public IntegerProperty getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min.set(min);
    }

    public IntegerProperty getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max.set(max);
    }
    
}
