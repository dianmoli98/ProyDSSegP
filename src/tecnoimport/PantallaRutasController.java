/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tecnoimport;

import controller.CtrlJefeBodega;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.bodega.Repartidor;
import model.bodega.Ruta;
import model.singleton.ConexionBD;

/**
 * FXML Controller class
 *
 * @author User-pc
 */
public class PantallaRutasController implements Initializable {
    
    private TableView<Ruta> tablaRutas;
    @FXML
    private TableColumn<Ruta, Integer> idRuta;
    @FXML
    private TableColumn<Ruta, Repartidor> repartidor;
    @FXML
    private TableColumn<Ruta, Integer> pedido;
    @FXML
    private TableColumn<Ruta, String> status;
    @FXML
    private Button crearRuta;
    @FXML
    private Button finRuta;
    
    private static PantallaRutasController controller = null;
    private static Ruta ruta = null;
    
    private CtrlJefeBodega control;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        control = FXMLVistaTController.getControlJefe();
        setController(this);
        tablaRutas =idRuta.getTableView();
        try {
            llenar();
        } catch (SQLException ex) {
            Logger.getLogger(PantallaRutasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tablaRutas.setRowFactory(tv -> {
            TableRow<Ruta> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
                     && event.getClickCount() == 1) {
                    setRuta(row.getItem());
                }
            });
            return row ;
        });
    }    
    
    public static void setController(PantallaRutasController p){
        controller = p;
    }
    @FXML
    private void crearRutass(MouseEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("PantallaCrearRutas.fxml"));
        Stage stage= new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(tablaRutas.getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void finalizarRutas(MouseEvent event)throws IOException {
        if(ruta != null && !ruta.getStatus().equals("Finalizado")){
        Parent root = FXMLLoader.load(getClass().getResource("FinalizaRuta.fxml"));
        Stage stage= new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(tablaRutas.getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.show();
        }else{
            emergentes.Emergentes.mostrarDialogo("Debe seleccionar una ruta En proceso de la tabla para"
                    + "\npoder finalizarla", "No hay ruta seleccionada", "Error");
        }
    }
    
    public void llenar() throws SQLException{
        ConexionBD bd = ConexionBD.getInstance();
        Connection conn = bd.conectarMySQL();
        
        try(Statement st = conn.createStatement()){
            celdas(conn,control.obtenerRSRutas(st));
        }
        idRuta.setCellValueFactory(new PropertyValueFactory<>("idRuta"));
        repartidor.setCellValueFactory(new PropertyValueFactory<>("repartidor"));
        pedido.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));

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
    public static PantallaRutasController getController() {
        return controller;
    }
    
    public static Ruta getRuta(){
        return ruta;
    }
    
    public static void setRuta(Ruta r){
        ruta = r;
    }
}
