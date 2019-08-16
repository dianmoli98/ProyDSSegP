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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author User-pc
 */
public class PantallaStockLocalidadController implements Initializable {

    @FXML
    private Label fechaactual;
    @FXML
    private Label empleado;
    @FXML
    private ImageView regreso;
    @FXML
    private TextField busqueda;
    @FXML
    private ComboBox<?> ComboLugar;
    @FXML
    private TableView<?> tablaStock;
    @FXML
    private TableColumn<?, ?> nombrePro;
    @FXML
    private TableColumn<?, ?> stock;
    @FXML
    private TableColumn<?, ?> idlocal;

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
    
}
