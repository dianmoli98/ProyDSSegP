/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tecnoimport;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import static tecnoimport.TecnoImport.ventincio;

/**
 * FXML Controller class
 *
 * @author User-pc
 */
public class FXMLAccesoController implements Initializable {

    @FXML
    private Label usuario;
    @FXML
    private TextField txtusuario;
    @FXML
    private Label contra;
    @FXML
    private Button button;
    @FXML
    private PasswordField txtcontra;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
          try {
            Parent root = FXMLLoader.load(getClass().getResource("/tecnoimport/FXMLVistaPrincipal.fxml"));
            Scene scene = new Scene(root);
            ventincio.setScene(scene);
            ventincio.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();}
        catch (IOException ex) {
            Logger.getLogger(FXMLAccesoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    }
    

