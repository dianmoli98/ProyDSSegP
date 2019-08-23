/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tecnoimport;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static tecnoimport.TecnoImport.ventsegunda;

/**
 *
 * @author Francisco
 */
public class AuxiliarVistaT {
    
    public AuxiliarVistaT(){
        //no se requiere
    }
    
    protected void crearPantallaEspera(){
        try {
                Parent root3 = FXMLLoader.load(getClass().getResource("/tecnoimport/Pantalla de Espera.fxml"));
                Scene scene3 = new Scene(root3);
                ventsegunda.setScene(scene3);
                ventsegunda.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMLVistaTController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    protected void mostrarPantallaConstruccion(Label label) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource(PantallaConstruccionController.PCONSTRUCCION));
        Stage stage= new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(label.getScene().getWindow());
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }
    
}
