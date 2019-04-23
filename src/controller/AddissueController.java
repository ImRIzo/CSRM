/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author R
 */
public class AddissueController implements Initializable {

    @FXML
    private JFXTextField customerID;
    @FXML
    private JFXTextField newIssueName;
    @FXML
    private JFXComboBox<?> chooseService;
    @FXML
    private JFXCheckBox isNewIssue;
    @FXML
    private JFXTimePicker pickTime;
    @FXML
    private JFXDatePicker pickDate;
    @FXML
    private TextArea issueDetails;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
