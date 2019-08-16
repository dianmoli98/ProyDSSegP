/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tecnoimport;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User-pc
 */
public class PantallaRutasController implements Initializable {

    @FXML
    private TableColumn<?, ?> IdRuta;
    @FXML
    private TableColumn<?, ?> repartidor;
    @FXML
    private TableColumn<?, ?> pedido;
    @FXML
    private TableColumn<?, ?> status;
    @FXML
    private Button CrearRuta;
    @FXML
    private Button FinRuta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void CrearRutass(MouseEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("PantallaCrearRutas.fxml"));
        Stage stage= new Stage();
            stage.setScene(new Scene(root));
            stage.show();
    }

    @FXML
    private void FinalizarRutas(MouseEvent event) {
    }
    
}
