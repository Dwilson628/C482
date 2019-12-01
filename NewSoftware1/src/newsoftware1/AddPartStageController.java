/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newsoftware1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dwils
 */



public class AddPartStageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private Label companyNameLabel;
    
    @FXML private Button saveButton;
    @FXML private Button cancelButton;
    
    @FXML private TextField idTextField;
    @FXML private TextField nameTextField;
    @FXML private TextField invTextField;
    @FXML private TextField priceTextField;
    @FXML private TextField maxTextField;
    @FXML private TextField minTextField;
    @FXML private TextField compNameTextField;
    
    @FXML private RadioButton inHouseButton;
    @FXML private RadioButton outsourcedButton;
    
    @FXML private ToggleGroup radioButtons;
    
    
    public void cancelButtonPressed(ActionEvent event) throws IOException
    {
        Parent mainParent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene mainScene = new Scene(mainParent);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(mainScene);
        window.show();
    }
    
    public void outsourcedButtonPressed()
    {
        this.companyNameLabel.setText("Company Name");
        this.compNameTextField.setPromptText("Company Name");
    }
    
    public void inHouseButtonPressed()
    {
        this.companyNameLabel.setText("Machine ID");
        this.compNameTextField.setPromptText("Machine ID");
    }
    
    public void saveButtonPressed(ActionEvent event) throws IOException
    {
        if (outsourcedButton.isSelected()) {
            Outsourced newOut = new Outsourced();
            newOut.setId(Inventory.numParts + 1);
            newOut.setName(nameTextField.getText());
            newOut.setStock(Integer.parseInt(invTextField.getText()));
            newOut.setPrice(Double.parseDouble(priceTextField.getText()));
            newOut.setMax(Integer.parseInt(maxTextField.getText()));
            newOut.setMin(Integer.parseInt(minTextField.getText()));
            newOut.setCompanyName(compNameTextField.getText());
            
            Inventory.addPart(newOut);
            Inventory.updatePartCount();
        }
        else if (inHouseButton.isSelected()) {
            InHouse newPart = new InHouse();
            newPart.setId(Inventory.numParts + 1);
            newPart.setName(nameTextField.getText());
            newPart.setStock(Integer.parseInt(invTextField.getText()));
            newPart.setPrice(Double.parseDouble(priceTextField.getText()));
            newPart.setMax(Integer.parseInt(maxTextField.getText()));
            newPart.setMin(Integer.parseInt(minTextField.getText()));
            newPart.setMachineId(Integer.parseInt(compNameTextField.getText()));
            
            Inventory.addPart(newPart);
            Inventory.updatePartCount();
        }
        
        cancelButtonPressed(event);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        radioButtons = new ToggleGroup();
        this.inHouseButton.setToggleGroup(radioButtons);
        this.outsourcedButton.setToggleGroup(radioButtons);
    }    
    
}
