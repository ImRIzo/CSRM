/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.LoginController.homestage;
import static csrm.CSRM.loginstage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author R
 */
public class FrameController implements Initializable {

    @FXML private BorderPane mainpain;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Parent pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("/scene/addissue.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FrameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainpain.setCenter(pane);
    }    
       

    @FXML
    private void showAddCustomer(javafx.event.ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("/scene/addcustomer.fxml"));
        mainpain.setCenter(pane);
    }

    @FXML
    private void addNewIssue(javafx.event.ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("/scene/addissue.fxml"));
        mainpain.setCenter(pane);
    }

    @FXML
    private void showReport(javafx.event.ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("/scene/report.fxml"));
        mainpain.setCenter(pane);
    }

    @FXML
    private void addNewService(javafx.event.ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("/scene/newservice.fxml"));
        mainpain.setCenter(pane);
    }

    @FXML
    private void logout(javafx.event.ActionEvent event) {
        homestage.close();
        loginstage.show();
    }

    @FXML
    private void about(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("About CSRM");
        alert.setHeaderText("Customer Service Record Module is a fun application made by group-Virtual Machine C-2019");
        alert.show();
    }
}
