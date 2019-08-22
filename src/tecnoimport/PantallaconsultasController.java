/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tecnoimport;

import controller.CtrlMaster;
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
import model.bodega.JefeBodega;
import model.local.Usuario;
import model.local.Vendedor;

/**
 * FXML Controller class
 *
 * @author User-pc
 */
public class PantallaconsultasController implements Initializable {

    @FXML
    private Button cVenta;
    @FXML
    private Button cPedido;
    @FXML
    private Button cCliente;
    @FXML
    private Button cStock;
    @FXML
    private Button cUsuario;
    @FXML
    private Button cProductos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Usuario user = CtrlMaster.getUser();
        
        if(user instanceof JefeBodega){
            cVenta.setDisable(true);
            cCliente.setDisable(true);
            cStock.setDisable(true);
            cProductos.setDisable(true);
            
        }else if(user instanceof Vendedor){
            cUsuario.setDisable(true);
            cPedido.setDisable(true);   
        }
    }    

    @FXML
    private void consultarVentas(MouseEvent event)throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("pantalla_construccion.fxml"));
        Stage stage= new Stage();
            stage.setScene(new Scene(root));
            stage.show();
    }

    @FXML
    private void consultarPedidos(MouseEvent event)throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("pantalla_construccion.fxml"));
        Stage stage= new Stage();
            stage.setScene(new Scene(root));
            stage.show();
    }

    @FXML
    private void consultarClientes(MouseEvent event)throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("pantalla_construccion.fxml"));
        Stage stage= new Stage();
            stage.setScene(new Scene(root));
            stage.show();
    }

    @FXML
    private void consultarStock(MouseEvent event) throws IOException{
    Parent root = FXMLLoader.load(getClass().getResource("PantallaStockLocalidad.fxml"));
        Stage stage= new Stage();
            stage.setScene(new Scene(root));
            stage.show();
    }

    @FXML
    private void consultarUsuario(MouseEvent event)throws IOException  {
        Parent root = FXMLLoader.load(getClass().getResource("PantallaUsuarios.fxml"));
        Stage stage= new Stage();
            stage.setScene(new Scene(root));
            stage.show();
    }


    @FXML
    private void consultarProductos(MouseEvent event)throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("FXMLVistaTProducto.fxml"));
        Stage stage= new Stage();
            stage.setScene(new Scene(root));
            stage.show();
    }
    
}
