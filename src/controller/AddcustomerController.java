/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXTextField;
import csrm.SQLiteConnector;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CustomerList;

/**
 * FXML Controller class
 *
 * @author R
 */
public class AddcustomerController implements Initializable {

    @FXML
    private TableView<CustomerList> customerList;
    @FXML
    private TableColumn<CustomerList, String> customerName;
    @FXML
    private TableColumn<CustomerList, String> customerID;
    @FXML
    private TableColumn<CustomerList, String> customerPhone;
    @FXML
    private TableColumn<CustomerList, String> customerEmail;
    @FXML
    private TableColumn<CustomerList, String> customerAddress;
    @FXML
    private TableColumn<CustomerList, String> tService;
    
    @FXML
    private JFXTextField cName;
    @FXML
    private JFXTextField cPhone;
    @FXML
    private JFXTextField cEmail;
    @FXML
    private JFXTextField cAddress;
    
    private String customername;
    private String customerphone;
    private String customeremail;
    private String customeraddress;
    private String totalservice;
    private String ID;
        
    Connection con;
    PreparedStatement pst;
    ObservableList<CustomerList> oblist = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        viewCustomerList();
    }    
    

    private void viewCustomerList() {
        con = SQLiteConnector.connect();        
        try {
            String sql = "SELECT * FROM customer";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                  oblist.add(new CustomerList(rs.getString("id"), rs.getString("name"), rs.getString("phone"), rs.getString("email"), rs.getString("address"),rs.getString("tservice")));
            } //call constructor to intit data
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        customerID.setCellValueFactory(new PropertyValueFactory<>("id"));
        customerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        customerPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        customerEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        customerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tService.setCellValueFactory(new PropertyValueFactory<>("tservice"));
        
        customerList.setItems(oblist); //show items on the tableview 
        
        ////////////////show data at right side after row selected///////////////////////
        customerList.setOnMouseClicked(e->{
            CustomerList customerlist = customerList.getSelectionModel().getSelectedItem();
            //get all the values from customer model
            ID = customerlist.getId();
            customername = customerlist.getName();
            customerphone = customerlist.getPhone();
            customeremail = customerlist.getEmail();
            customeraddress = customerlist.getAddress();
            totalservice = customerlist.getTservice();
            //show data to textbox
            cName.setText(customername);
            cPhone.setText(customerphone);
            cEmail.setText(customeremail);
            cAddress.setText(customeraddress);
        });
    }

    @FXML
    private void addNewCustomer(javafx.event.ActionEvent event) throws SQLException {
        customername = cName.getText();
        customerphone = cPhone.getText();
        customeremail = cEmail.getText();
        customeraddress = cAddress.getText();
        con = SQLiteConnector.connect();
        String SQL = "INSERT INTO customer (name,phone,email,address,tservice) VALUES (?, ?, ?, ?, ?)";
        pst = con.prepareStatement(SQL);
        pst.setString(1, customername);
        pst.setString(2, customerphone);
        pst.setString(3, customeremail);
        pst.setString(4, customeraddress);
        pst.setString(5, "0");
        pst.executeUpdate();
        pst.close();
        con.close();
        refresh();
    }

    @FXML
    private void removeCustomer(javafx.event.ActionEvent event) throws SQLException {
        con = SQLiteConnector.connect();
        String sql = "DELETE FROM customer WHERE id = ?";
        pst = con.prepareStatement(sql);
        pst.setString(1, ID);
        pst.executeUpdate();
        pst.close();
        con.close();
        refresh();
    }

    @FXML
    private void updateCustomer(javafx.event.ActionEvent event) throws SQLException {
        customername = cName.getText();
        customerphone = cPhone.getText();
        customeremail = cEmail.getText();
        customeraddress = cAddress.getText();
        con = SQLiteConnector.connect();
        String SQL = "UPDATE customer SET name=?,phone=?,email=?,address=?,tservice=? WHERE id=?";
        pst = con.prepareStatement(SQL);
        pst.setString(1, customername);
        pst.setString(2, customerphone);
        pst.setString(3, customeremail);
        pst.setString(4, customeraddress);
        pst.setString(5, totalservice);
        pst.setString(6, ID);
        pst.executeUpdate();
        pst.close();
        con.close();
        refresh();
    }

    private void refresh() {
        oblist.clear();
        cName.clear();
        cPhone.clear();
        cEmail.clear();
        cAddress.clear();
        viewCustomerList();
    }
}
