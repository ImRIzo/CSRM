/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csrm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author R
 */
public class CSRM extends Application {
    public static Stage loginstage;
    @Override
    public void start(Stage loginstage) throws Exception {
        this.loginstage = loginstage;
        Parent root = FXMLLoader.load(getClass().getResource("/scene/login.fxml"));
        
        Scene scene = new Scene(root);
        
        loginstage.setScene(scene);
        loginstage.setResizable(false);
        loginstage.getIcons().add(new Image("/scene/css.png"));
        loginstage.setTitle("CSRM");
        loginstage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
