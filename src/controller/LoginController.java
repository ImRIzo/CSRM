/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import static csrm.CSRM.loginstage;
import csrm.SQLiteConnector;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author R
 */
public class LoginController implements Initializable {
    
    @FXML private JFXTextField username;
    @FXML private JFXPasswordField password;
    
    Connection con;
    PreparedStatement pst;
    public static Stage homestage;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void loginAction(javafx.event.ActionEvent event) throws SQLException, IOException {
        String un = username.getText();
        String up = password.getText();
        con = SQLiteConnector.connect();
        String SQL = "SELECT * from user where name=? and password=?";
        pst = con.prepareStatement(SQL);
        pst.setString(1, un);
        pst.setString(2, up);
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            pst.close();
            con.close();
            Parent pane = FXMLLoader.load(getClass().getResource("/scene/frame.fxml"));
            Scene scene = new Scene(pane);
            homestage = new Stage(); 
            homestage.setMinWidth(1100);
            homestage.setMinHeight(600);
            homestage.setScene(scene);
            homestage.getIcons().add(new Image("/scene/css.png"));
            homestage.setTitle("Customer Service Record and Module");
            //homestage.setResizable(false);
            loginstage.close();
            homestage.show();
            homestage.setMaximized(true);
        }else{
            pst.close();
            con.close();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Failed!");
            alert.setHeaderText("Wrong Username or Password");
            alert.setContentText("Please input valid Username and Password");
            alert.show();
        }
    }
    
    
}
