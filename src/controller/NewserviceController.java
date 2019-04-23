/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import csrm.SQLiteConnector;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ServiceList;

/**
 * FXML Controller class
 *
 * @author R
 */
public class NewserviceController implements Initializable {

    @FXML
    private TableView<ServiceList> serviceList;
    @FXML
    private TableColumn<ServiceList, String> service;
    @FXML
    private TableColumn<ServiceList, String> demand;
    @FXML
    private JFXTextField serviceName;
    @FXML
    private JFXComboBox<String> choosePriority;
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    private String id;
    private String servicename;
    private String prio;
    ObservableList<ServiceList> oblist = FXCollections.observableArrayList();
    ObservableList<String> priority = FXCollections.observableArrayList("High", "Medium", "Low");
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choosePriority.setItems(priority);
        try {
            viewServiceList();
        } catch (SQLException ex) {
            Logger.getLogger(NewserviceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    private void viewServiceList() throws SQLException {
        con = SQLiteConnector.connect();
        String sql = "SELECT * FROM service";
        rs = con.createStatement().executeQuery(sql);
        while(rs.next()){
            oblist.add(new ServiceList(rs.getString("id"), rs.getString("sname"), rs.getString("priority")));
        }
        rs.close();
        con.close();
        service.setCellValueFactory(new PropertyValueFactory<>("sname"));
        demand.setCellValueFactory(new PropertyValueFactory<>("priority"));
        serviceList.setItems(oblist);
        
        serviceList.setOnMouseClicked(e->{
            ServiceList slist = serviceList.getSelectionModel().getSelectedItem();
            id = slist.getId();
        });
    }
    
    @FXML
    void addService(ActionEvent event) throws SQLException {
        servicename = serviceName.getText();
        prio = choosePriority.getValue();
        con = SQLiteConnector.connect();
        String sql = "INSERT INTO service (sname,priority) VALUES (?, ?)";
        pst = con.prepareStatement(sql);
        pst.setString(1, servicename);
        pst.setString(2, prio);
        pst.executeUpdate();
        pst.close();
        con.close();
        refresh();
    }

    @FXML
    void removeService(ActionEvent event) throws SQLException {
        con = SQLiteConnector.connect();
        String sql = "DELETE FROM service WHERE id = ?";
        pst = con.prepareStatement(sql);
        pst.setString(1, id);
        pst.executeUpdate();
        pst.close();
        con.close();
        refresh();
    }
    @FXML
    void updateService(ActionEvent event) throws SQLException {
        prio = choosePriority.getValue();
        con = SQLiteConnector.connect();
        String sql = "UPDATE service SET priority=? WHERE id=?";
        pst = con.prepareStatement(sql);
        pst.setString(1, prio);
        pst.setString(2, id);
        pst.executeUpdate();
        pst.close();
        con.close();
        refresh();
    }

    private void refresh() throws SQLException {
        oblist.clear();
        choosePriority.getEditor().clear();
        viewServiceList();
    }
    
}
