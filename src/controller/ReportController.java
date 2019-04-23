/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.TextFlow;

/**
 * FXML Controller class
 *
 * @author R
 */
public class ReportController implements Initializable {

    @FXML
    private TableView<?> reportTable;
    @FXML
    private TableColumn<?, ?> issueType;
    @FXML
    private TableColumn<?, ?> customerID;
    @FXML
    private TableColumn<?, ?> customerName;
    @FXML
    private TableColumn<?, ?> reportTime;
    @FXML
    private TableColumn<?, ?> reportDate;
    @FXML
    private TableColumn<?, ?> issueStatus;
    @FXML
    private TextFlow issueDetails;
    @FXML
    private CheckBox isSolved;
    @FXML
    private Button update;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
