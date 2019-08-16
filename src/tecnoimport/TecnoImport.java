/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tecnoimport;

import java.sql.Connection;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author User-pc
 */
public class TecnoImport extends Application{
   public static Stage ventPrincipal = new Stage();
    public static Stage ventincio = new Stage();
    public static Scene scene;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("PantallaAcceso.fxml"));
        scene = new Scene(root);
        
        ventPrincipal.setScene(scene);
        ventPrincipal.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
  
