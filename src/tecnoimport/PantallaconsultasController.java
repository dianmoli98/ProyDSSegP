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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User-pc
 */
public class PantallaconsultasController implements Initializable {

    @FXML
    private Button Cventa;
    @FXML
    private Button CPedido;
    @FXML
    private Button CCliente;
    @FXML
    private Button CStock;
    @FXML
    private Button CUsuario;
    @FXML
    private Button CProductos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ConsultarVentas(MouseEvent event)throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("pantalla_construccion.fxml"));
        Stage stage= new Stage();
            stage.setScene(new Scene(root));
            stage.show();
    }

    @FXML
    private void ConsultarPedidos(MouseEvent event)throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("pantalla_construccion.fxml"));
        Stage stage= new Stage();
            stage.setScene(new Scene(root));
            stage.show();
    }

    @FXML
    private void ConsultarClientes(MouseEvent event)throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("pantalla_construccion.fxml"));
        Stage stage= new Stage();
            stage.setScene(new Scene(root));
            stage.show();
    }

    @FXML
    private void ConsultarStock(MouseEvent event) throws IOException{
    Parent root = FXMLLoader.load(getClass().getResource("PantallaStockLocalidad.fxml"));
        Stage stage= new Stage();
            stage.setScene(new Scene(root));
            stage.show();
    }

    @FXML
    private void ConsultarUsuario(MouseEvent event)throws IOException  {
        Parent root = FXMLLoader.load(getClass().getResource("pantalla_construccion.fxml"));
        Stage stage= new Stage();
            stage.setScene(new Scene(root));
            stage.show();
    }


    @FXML
    private void ConsultarProductos(MouseEvent event)throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("FXMLVistaTProducto.fxml"));
        Stage stage= new Stage();
            stage.setScene(new Scene(root));
            stage.show();
    }
    
}
