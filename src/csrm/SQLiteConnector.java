/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csrm;

import java.sql.Connection;
import java.sql.DriverManager;
import javafx.scene.control.Alert;

/**
 *
 * @author R
 */
public class SQLiteConnector {
        static Connection con;
    
    public static Connection connect()
    {
        
        Connection connection = null;
        try
        {   
            Class.forName("org.sqlite.JDBC");
            con=DriverManager.getConnection("jdbc:sqlite:CSRM.db"); 
        }
        catch(Exception e)
        { 
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Database Error!");
            alert.setHeaderText(null);
            alert.setContentText("Please Check Database Connection! "+e);
            alert.show();
        }
        return con;
    }
}
