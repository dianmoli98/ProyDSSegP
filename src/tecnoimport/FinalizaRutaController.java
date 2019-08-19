/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tecnoimport;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author User-pc
 */
public class FinalizaRutaController implements Initializable {

    @FXML
    private ImageView regreso;
    @FXML
    private TableView<?> tablaRutasgeneral;
    @FXML
    private TableColumn<?, ?> idped;
    @FXML
    private TableColumn<?, ?> dir;
    @FXML
    private TableView<?> tablaRutasAsignadas;
    @FXML
    private TableColumn<?, ?> idpedido;
    @FXML
    private TableColumn<?, ?> direccion;
    @FXML
    private ComboBox<?> Comboobservaciones;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void regreso(MouseEvent event) {
    }

    @FXML
    private void btnGuardar(MouseEvent event) {
    }

    @FXML
    private void btnCancelar(MouseEvent event) {
    }
    
}
