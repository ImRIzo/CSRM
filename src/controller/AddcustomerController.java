/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author R
 */
public class AddcustomerController implements Initializable {

    @FXML
    private TableView<?> customerList;
    @FXML
    private TableColumn<?, ?> customerName;
    @FXML
    private TableColumn<?, ?> customerID;
    @FXML
    private TableColumn<?, ?> customerPhone;
    @FXML
    private TableColumn<?, ?> customerEmail;
    @FXML
    private TableColumn<?, ?> customerAddress;
    @FXML
    private JFXTextField cName;
    @FXML
    private JFXTextField cPhone;
    @FXML
    private JFXTextField cEmail;
    @FXML
    private JFXTextField cAddress;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
