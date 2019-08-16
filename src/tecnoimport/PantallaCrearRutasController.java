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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author User-pc
 */
public class PantallaCrearRutasController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
