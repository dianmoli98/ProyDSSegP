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
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author User-pc
 */
public class PantallaDeEsperaController implements Initializable {

    @FXML
    private GridPane gridd;
    @FXML
    private ListView<?> CodRepartidor;
    @FXML
    private ListView<?> CodRuta;
    @FXML
    private ImageView imagenc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void btnVolver(MouseEvent event) {
    }
    
}
