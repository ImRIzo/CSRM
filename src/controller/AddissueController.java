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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    private JFXComboBox<String> chooseService;
    @FXML
    private JFXCheckBox isNewIssue;
    @FXML
    private JFXTimePicker pickTime;
    @FXML
    private JFXDatePicker pickDate;
    @FXML
    private TextArea issueDetails;
    
    ObservableList<String> service = FXCollections.observableArrayList();
    private String customerid;
    private String customername;
    private String issue;
    private String time;
    private String date;
    private String issuedetails;
    private int totalservice = 0;
    private String oldetotalservice;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = SQLiteConnector.connect();
        String sql = "SELECT * FROM service";
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                service.add(rs.getString("sname"));
            }
            pst.close();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AddissueController.class.getName()).log(Level.SEVERE, null, ex);
        }
        chooseService.setItems(service);
    }  
    
    
    @FXML
    void addIssue(ActionEvent event) throws SQLException {
        customerid = customerID.getText();
        time = pickTime.getEditor().getText();
        date = pickDate.getEditor().getText();
        issuedetails = issueDetails.getText();
        if(isNewIssue.isSelected()){
            issue = newIssueName.getText();
        }else{
            issue = chooseService.getValue();
        }
        
        try{
        con = SQLiteConnector.connect();
        String sql = "SELECT * FROM customer where id=?";
        pst = con.prepareStatement(sql);
        pst.setString(1, customerid);
        rs = pst.executeQuery();
        if(rs.next()){
            customername = rs.getString("name");
            oldetotalservice = rs.getString("tservice");
            totalservice = Integer.parseInt(oldetotalservice);
            totalservice++;
            rs.close();
            pst.close(); 
            saveIssueToDatabase();
        }else{
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Error!");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter a valid customer ID! ");
            alert.show();
        }
        }catch(Exception e){
            System.out.println("We are fucked here 1 "+e);
        }        
    }

    private void saveIssueToDatabase() throws SQLException {
        //check if the service exist or not??//////
        
        try{
        con = SQLiteConnector.connect();
        String sql = "SELECT * FROM service where sname=?";
        pst = con.prepareStatement(sql);
        pst.setString(1, issue);
        rs = pst.executeQuery();
        if(!rs.next()){
            rs.close();
            pst.close();
            
            try{
                con = SQLiteConnector.connect();
                String SQL1 = "INSERT INTO service (sname,priority) VALUES (?, ?)";
                pst = con.prepareStatement(SQL1);
                pst.setString(1, issue);
                pst.setString(2, "Low");
                pst.executeUpdate();
            }catch(Exception e){
                System.out.println("not working "+e);
            }finally{
                pst.close();
            }
        }      
        }catch(Exception e){
            System.out.println(e);
        }
        finally{
            rs.close();
            pst.close();
        }
        
        ////////////////save rport count for per customer to customer table//////////////
        con = SQLiteConnector.connect();
        String SQL = "UPDATE customer SET tservice=? WHERE id=?";
        try{
            pst = con.prepareStatement(SQL);
            pst.setString(1, Integer.toString(totalservice));
            pst.setString(2, customerid);
            pst.executeUpdate();
            pst.close();
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
        //add data to report 
        
        try{
        con = SQLiteConnector.connect();
        String SQL2 = "INSERT INTO report (issue,customerid,customername,time,date,status,issuedetails) VALUES (?, ?, ?, ?, ?, ?, ?)";
        pst = con.prepareStatement(SQL2);
        pst.setString(1, issue);
        pst.setString(2, customerid);
        pst.setString(3, customername);
        pst.setString(4, time);
        pst.setString(5, date);
        pst.setString(6, "In Progress");
        pst.setString(7, issuedetails);
        pst.executeUpdate();
        pst.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        
        
        customerID.clear();
        newIssueName.clear();
        chooseService.getEditor().clear();
        isNewIssue.setSelected(false);
        pickTime.getEditor().clear();
        pickDate.getEditor().clear();
        issueDetails.clear();
    }
    
}
