/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newsoftware1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dwils
 */


public class ModifyProductStageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML private Button saveButton;
    @FXML private Button cancelButton;
    @FXML private Button searchButton;
    @FXML private Button addButton;
    @FXML private Button deleteButton;
    
    @FXML private TextField idTextField;
    @FXML private TextField nameTextField;
    @FXML private TextField invTextField;
    @FXML private TextField priceTextField;
    @FXML private TextField maxTextField;
    @FXML private TextField minTextField;
    @FXML private TextField partSearchField;
    
    @FXML private TableView<Part> allPartsTable;
    @FXML private TableView<Part> associatedPartsTable;
    
    @FXML private TableColumn<Part, Integer> allPartsIdColumn;
    @FXML private TableColumn<Part, String> allPartsNameColumn;
    @FXML private TableColumn<Part, Integer> allPartsInvColumn;
    @FXML private TableColumn<Part, Double> allPartsPriceColumn;
    
    @FXML private TableColumn<Part, Integer> associatedPartsIdColumn;
    @FXML private TableColumn<Part, String> associatedPartsNameColumn;
    @FXML private TableColumn<Part, Integer> associatedPartsInvColumn;
    @FXML private TableColumn<Part, Double> associatedPartsPriceColumn;
    
    Product selectedProduct = new Product();
    ObservableList<Part> associatedList = FXCollections.observableArrayList();
    
    public void initData(Product product){
        selectedProduct = product;
        ObservableList<Part> placeholderList = FXCollections.observableArrayList();
        idTextField.setText(selectedProduct.getId().getValue().toString());
        nameTextField.setText(selectedProduct.getName().getValue());
        invTextField.setText(selectedProduct.getStock().getValue().toString());
        priceTextField.setText(selectedProduct.getPrice().getValue().toString());
        maxTextField.setText(selectedProduct.getMax().getValue().toString());
        minTextField.setText(selectedProduct.getMin().getValue().toString());
        
        
        placeholderList = selectedProduct.getAllAssociatedParts();
        while (placeholderList.size() > 0) {
            associatedList.add(placeholderList.get(0));
            placeholderList.remove(0);
        }
    }
    
    public void addButtonPressed(){
        associatedList.add(allPartsTable.getSelectionModel().getSelectedItem());
    }
    
    public void deleteButtonPressed(){
        associatedList.remove(associatedPartsTable.getSelectionModel().getSelectedItem());
    }
    
    public void saveButtonPressed(ActionEvent event) throws IOException 
    {   
        try {
            if (associatedList.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error modifying product.");
                alert.setContentText("Products must have at least one associated part.");
            
                alert.showAndWait();
            }
            
            else if (nameTextField.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error modifying product.");
                alert.setContentText("Product must have a name.");
                
                alert.showAndWait();
            }

            else {
                Product newProduct = new Product();
                newProduct.setId(Inventory.numProducts + 1);
                newProduct.setName(nameTextField.getText());
                newProduct.setStock(Integer.parseInt(invTextField.getText()));
                newProduct.setPrice(Double.parseDouble(priceTextField.getText()));
                newProduct.setMax(Integer.parseInt(maxTextField.getText()));
                newProduct.setMin(Integer.parseInt(minTextField.getText()));
        
                while (associatedList.size() > 0){
                    newProduct.addAssociatedPart(associatedList.get(0));
                    associatedList.remove(0);
                }

            Inventory.updateProduct(Inventory.getAllProducts().indexOf(selectedProduct), newProduct);
        
            cancelButtonPressed(event);
            }
            
        }
        
        catch(NumberFormatException exception){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error modifying product.");
            alert.setContentText("Please correct empty fields.");
            
            alert.showAndWait();
            
        }
    }
        
    public void cancelButtonPressed(ActionEvent event) throws IOException
    {
        Parent mainParent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene mainScene = new Scene(mainParent);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(mainScene);
        window.show();
    }
    
    public void searchButtonPressed()
    {
        if (partSearchField.getText().equals("")){
            allPartsTable.setItems(updatePartsList());
        }
        else {
            ObservableList<Part> searchedList = FXCollections.observableArrayList();
            String searcher = partSearchField.getText();
            int index = Inventory.lookupPart(searcher);
            if (index != -1){
                searchedList.add(Inventory.getAllParts().get(index));
                allPartsTable.setItems(searchedList);
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        allPartsIdColumn.setCellValueFactory(cellData -> cellData.getValue().getId().asObject());
        allPartsNameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        allPartsInvColumn.setCellValueFactory(cellData -> cellData.getValue().getStock().asObject());
        allPartsPriceColumn.setCellValueFactory(cellData -> cellData.getValue().getPrice().asObject());
        
        associatedPartsIdColumn.setCellValueFactory(cellData -> cellData.getValue().getId().asObject());
        associatedPartsNameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        associatedPartsInvColumn.setCellValueFactory(cellData -> cellData.getValue().getStock().asObject());
        associatedPartsPriceColumn.setCellValueFactory(cellData -> cellData.getValue().getPrice().asObject());
        
        allPartsTable.setItems(updatePartsList());
        associatedPartsTable.setItems(associatedList);
    }    
    
    public ObservableList<Part> updatePartsList(){
        return Inventory.getAllParts();
    }
    
}
