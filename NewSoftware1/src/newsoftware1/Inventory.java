/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newsoftware1;

import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Dwils
 */
public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    
    public static int numParts = allParts.size();
    public static int numProducts = allProducts.size();
    

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
    
    //
    
    public static void addPart (Part newPart) {
        allParts.add(newPart);
    }
    
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }
    
    public static int lookupPart(String searchTerm){
        boolean found = false;
        int index = 0;
        try {
            Integer.parseInt(searchTerm);
            for (Part part : allParts) {
                if (part.getId().getValue() == Integer.parseInt(searchTerm))
                {
                    index = allParts.indexOf(part);
                    found = true;
                }
            }
        }
        catch (Exception e){
             for (Part part : allParts) {
                 if (part.getName().getValue().equals(searchTerm))
                 {
                     index = allParts.indexOf(part);
                     found = true;
                 }
             }
        }
        if (found == true){
            return index;
        }
        else 
        {
            return -1;
        }
    }
    
    public static int lookupProduct(String searchTerm){
        boolean found = false;
        int index = 0;
        try {
            Integer.parseInt(searchTerm);
            for (Product product : allProducts) {
                if (product.getId().getValue() == Integer.parseInt(searchTerm))
                {
                    index = allProducts.indexOf(product);
                    found = true;
                }
            }
        }
        catch (Exception e){
             for (Product product : allProducts) {
                 if (product.getName().getValue().equals(searchTerm))
                 {
                     index = allProducts.indexOf(product);
                     found = true;
                 }
             }
        }
        if (found == true){
            return index;
        }
        else 
        {
            return -1;
        }
    }

    

    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }
    
    public static void updateProduct(int index, Product selectedProduct){
        allProducts.set(index, selectedProduct);
    }
    
    public static void deletePart (Part selectedPart){
        allParts.remove(selectedPart);
    }
    
    public static void deleteProduct (Product selectedProduct){
        allProducts.remove(selectedProduct);
    }
    
    public static void updatePartCount(){
        numParts = allParts.size();
    }
    public static void updateProductCount(){
        numProducts = allProducts.size();
    }
}
