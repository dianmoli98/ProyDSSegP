/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tecnoimport;

import Controller.CtrlJefeBodega;
import Controller.CtrlMaster;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Bodega.Jefe_Bodega;
import model.Bodega.Repartidor;
import model.Bodega.Ruta;
import model.singleton.ConexionBD;

/**
 * FXML Controller class
 *
 * @author User-pc
 */
public class PantallaRutasController implements Initializable {
    
    private TableView<Ruta> tablaRutas;
    @FXML
    private TableColumn<Ruta, Integer> IdRuta;
    @FXML
    private TableColumn<Ruta, Repartidor> repartidor;
    @FXML
    private TableColumn<Ruta, Integer> pedido;
    @FXML
    private TableColumn<Ruta, String> status;
    @FXML
    private Button CrearRuta;
    @FXML
    private Button FinRuta;
    
    private static CtrlJefeBodega control = new CtrlJefeBodega((Jefe_Bodega)CtrlMaster.getUser());;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tablaRutas =IdRuta.getTableView();
        try {
            llenar();
        } catch (SQLException ex) {
            Logger.getLogger(PantallaRutasController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    public void llenar() throws SQLException{
        ConexionBD bd = ConexionBD.getInstance();
        Connection conn = bd.conectarMySQL();
        ResultSet rs = control.obtenerRSRutas(conn);
        
        IdRuta.setCellValueFactory(new PropertyValueFactory<>("id_ruta"));
        repartidor.setCellValueFactory(new PropertyValueFactory<>("repartidor"));
        pedido.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        celdas(conn,rs);
    }
    
    private void celdas(Connection st,ResultSet rs) throws SQLException{
        tablaRutas.setVisible(true);
        ObservableList<Ruta> datos = FXCollections.observableArrayList();
        while (rs.next()) {
            Ruta r = control.obtenerRuta(rs);
            datos.add(r);
        }
        tablaRutas.setItems(datos);
        tablaRutas.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ConexionBD.getInstance().cerrarConexion(st);
    }
    
    public static CtrlJefeBodega getControl(){
        return control;
    }
    
}
