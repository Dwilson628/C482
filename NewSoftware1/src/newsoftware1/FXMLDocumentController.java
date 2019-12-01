package newsoftware1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author Dwils
 */
public class FXMLDocumentController implements Initializable {
   
    //Assigns buttons on the main page
    @FXML private Button addPartButton;
    @FXML private Button modifyPartButton;
    @FXML private Button searchPartButton;
    @FXML private Button deletePartButton;
    
    @FXML private Button addProductButton;
    @FXML private Button modifyProductButton;
    @FXML private Button searchProductButton;
    @FXML private Button deleteProductButton;
    
    @FXML private Button exitButton;
    
    @FXML private TableView<Part> partsTable;
    @FXML private TableView<Product> productsTable;
    
    @FXML private TableColumn<Part, Integer> partID;
    @FXML private TableColumn<Part, String> partName;
    @FXML private TableColumn<Part, Integer> partInv;
    @FXML private TableColumn<Part, Double> partPrice;
    
    @FXML private TableColumn<Product, Integer> productID;
    @FXML private TableColumn<Product, String> productName;
    @FXML private TableColumn<Product, Integer> productInv;
    @FXML private TableColumn<Product, Double> productPrice;
    
    @FXML private TextField partsTextField;
    @FXML private TextField productsTextField;
    
    //Method to change scene to Add Part
    public void addPartButtonPushed(ActionEvent event) throws IOException
    {
        Parent addPartParent = FXMLLoader.load(getClass().getResource("AddPartStage.fxml"));
        Scene addPartScene = new Scene(addPartParent);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(addPartScene);
        window.show();
    }
    
    public void modifyPartButtonPushed(ActionEvent event) throws IOException
    {
        FXMLLoader modifyLoader = new FXMLLoader();
        modifyLoader.setLocation(getClass().getResource("ModifyPartStage.fxml"));
        Parent modifyPartParent = modifyLoader.load();
        
        Scene modifyPartScene = new Scene(modifyPartParent);
        
        ModifyPartStageController controller = modifyLoader.getController();
        controller.initData(partsTable.getSelectionModel().getSelectedItem());
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(modifyPartScene);
        window.show();
    }
    
    public void searchPartButtonPushed()
    {
        if (partsTextField.getText().equals("")){
            partsTable.setItems(updatePartsList());
        }
        else {
            ObservableList<Part> searchedList = FXCollections.observableArrayList();
            String searcher = partsTextField.getText();
            int index = Inventory.lookupPart(searcher);
            if (index != -1){
                searchedList.add(Inventory.getAllParts().get(index));
                partsTable.setItems(searchedList);
            }
        }
    }
    
    public void deletePartButtonPushed()
    {
        Part deleting = partsTable.getSelectionModel().getSelectedItem();
        Inventory.deletePart(deleting);
        partsTable.setItems(updatePartsList());
    }
    
    
    public void addProductButtonPushed(ActionEvent event) throws IOException
    {
        Parent addProductParent = FXMLLoader.load(getClass().getResource("AddProductStage.fxml"));
        Scene addProductScene = new Scene(addProductParent);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(addProductScene);
        window.show();
    }
    
    public void modifyProductButtonPushed(ActionEvent event) throws IOException
    {
        FXMLLoader modifyLoader = new FXMLLoader();
        modifyLoader.setLocation(getClass().getResource("ModifyProductStage.fxml"));
        Parent modifyProductParent = modifyLoader.load();
        
        Scene modifyProductScene = new Scene(modifyProductParent);
        
        ModifyProductStageController controller = modifyLoader.getController();
        controller.initData(productsTable.getSelectionModel().getSelectedItem());
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(modifyProductScene);
        window.show();
    }
    
    public void searchProductButtonPushed()
    {
        if (productsTextField.getText().equals("")){
            productsTable.setItems(updateProductsList());
        }
        else {
            ObservableList<Product> searchedList = FXCollections.observableArrayList();
            String searcher = productsTextField.getText();
            int index = Inventory.lookupProduct(searcher);
            if (index != -1){
                searchedList.add(Inventory.getAllProducts().get(index));
                productsTable.setItems(searchedList);
            }
        }
    }
    
    public void deleteProductButtonPushed()
    {
        Product deleting = productsTable.getSelectionModel().getSelectedItem();
        Inventory.deleteProduct(deleting);
        productsTable.setItems(updateProductsList());
    }
    
    
    public void exitButtonPushed()
    {
        Platform.exit();
    }
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        partID.setCellValueFactory(cellData -> cellData.getValue().getId().asObject());
        partName.setCellValueFactory(cellData -> cellData.getValue().getName());
        partInv.setCellValueFactory(cellData -> cellData.getValue().getStock().asObject());
        partPrice.setCellValueFactory(cellData -> cellData.getValue().getPrice().asObject());
        
        productID.setCellValueFactory(cellData -> cellData.getValue().getId().asObject());
        productName.setCellValueFactory(cellData -> cellData.getValue().getName());
        productInv.setCellValueFactory(cellData -> cellData.getValue().getStock().asObject());
        productPrice.setCellValueFactory(cellData -> cellData.getValue().getPrice().asObject());

        
        partsTable.setItems(updatePartsList());
        productsTable.setItems(updateProductsList());
    }
    
    
    public ObservableList<Part> updatePartsList(){
        return Inventory.getAllParts();
    }
    
    public ObservableList<Product> updateProductsList(){
        return Inventory.getAllProducts();
    }
}
