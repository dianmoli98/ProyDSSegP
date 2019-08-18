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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author User-pc
 */
public class PantallaUsuariosController implements Initializable {

    @FXML
    private TableView<?> tablaUsuarios;
    @FXML
    private TableColumn<?, ?> nombre;
    @FXML
    private TableColumn<?, ?> apellido;
    @FXML
    private TableColumn<?, ?> cedula;
    @FXML
    private TextField busqueda;
    @FXML
    private Label nomE;
    @FXML
    private Label fecha;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Convertirdmin(MouseEvent event) {
    }
    
}
