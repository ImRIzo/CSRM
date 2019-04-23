/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXComboBox;
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
public class NewserviceController implements Initializable {

    @FXML
    private TableView<?> serviceList;
    @FXML
    private TableColumn<?, ?> service;
    @FXML
    private TableColumn<?, ?> demand;
    @FXML
    private JFXTextField serviceName;
    @FXML
    private JFXComboBox<?> choosePriority;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
