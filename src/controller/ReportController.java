/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.TextFlow;
import model.ReportList;

/**
 * FXML Controller class
 *
 * @author R
 */
public class ReportController implements Initializable {

    @FXML
    private TableView<ReportList> reportTable;
    @FXML
    private TableColumn<ReportList, String> issueType;
    @FXML
    private TableColumn<ReportList, String> customerID;
    @FXML
    private TableColumn<ReportList, String> customerName;
    @FXML
    private TableColumn<ReportList, String> reportTime;
    @FXML
    private TableColumn<ReportList, String> reportDate;
    @FXML
    private TableColumn<ReportList, String> issueStatus;    
    @FXML
    private TextArea issueDetailsBox;
    @FXML
    private CheckBox isSolved;
    @FXML
    private Button update;
    
    private String reportdetails;
    private String newprogress;
    private String issueid;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
     ObservableList<ReportList> oblist = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            showReport();
        } catch (SQLException ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    private void showReport() throws SQLException {
        con = SQLiteConnector.connect();
        String sql = "SELECT * FROM report";
        try{
            rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
            oblist.add(new ReportList(rs.getString("id"),rs.getString("issue"),rs.getString("customerid"),rs.getString("customername"),rs.getString("time"),rs.getString("date"),rs.getString("status"),rs.getString("issuedetails")));
            }
        }catch(SQLException e){
            System.out.println(e);
        }finally{
            rs.close();
            con.close();
        }
        issueType.setCellValueFactory(new PropertyValueFactory<>("issue"));
        customerID.setCellValueFactory(new PropertyValueFactory<>("customerid"));
        customerName.setCellValueFactory(new PropertyValueFactory<>("customername"));
        reportTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        reportDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        issueStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        reportTable.setItems(oblist);
        
            reportTable.setOnMouseClicked(e->{
            ReportList report = reportTable.getSelectionModel().getSelectedItem();
            issueid = report.getIssueid();
            reportdetails = report.getIssuedetails();
            issueDetailsBox.setText(reportdetails);
        });
    }
    
    @FXML
    void updateReport(ActionEvent event) throws SQLException {
        if(isSolved.isSelected()){
            newprogress = "Solved";
        }
        con = SQLiteConnector.connect();
        String sql = "UPDATE report SET status=? WHERE id=?";
        pst = con.prepareStatement(sql);
        pst.setString(1, newprogress);
        pst.setString(2, issueid);
        pst.executeUpdate();
        pst.close();
        isSolved.setSelected(false);
        oblist.clear();
        showReport();
    }
}
