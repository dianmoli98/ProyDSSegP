/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tecnoimport;

import controller.CtrlJefeBodega;
import emergentes.Emergentes;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import model.singleton.ConexionBD;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.pedido.Pedido;

/**
 * FXML Controller class
 *
 * @author User-pc
 */
public class PantallaCrearRutasController implements Initializable {

    @FXML
    private TableView<Pedido> tablaRutasgeneral;
    
    @FXML
    private TableColumn<Pedido, String> idped;
    @FXML
    private TableColumn<Pedido, String> dir;
    @FXML
    private TableView<Pedido> tablaRutasAsignadas;
    @FXML
    private TableColumn<Pedido, String> idpedido;
    @FXML
    private TableColumn<Pedido, String> direccion;
    
    private ObservableList<String> data;
    
    private static CtrlJefeBodega control = FXMLVistaTController.getControlJefe();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        agregarColumasRuta();
        try {
            llenar();
        } catch (SQLException ex) {
           Emergentes.mostrarDialogo(ex.getMessage(), "Error de conexión.", "Error");
        }
        accionDoubleClickTabla(tablaRutasAsignadas, tablaRutasgeneral);
        accionDoubleClickTabla(tablaRutasgeneral, tablaRutasAsignadas);
    }   
      
    private void llenar() throws SQLException{
        ConexionBD bd = ConexionBD.getInstance();
        Connection conn = bd.conectarMySQL();
        ResultSet rs = control.obtenerRSPedidos(conn);
        celdas(conn,rs);
    }
    
    private void celdas(Connection st,ResultSet rs) throws SQLException{
        tablaRutasgeneral.setVisible(true);
        ObservableList<Pedido> datos = FXCollections.observableArrayList();
        while (rs.next()) {
            Pedido p = control.obtenerPedido(rs);
            datos.add(p);
        }
        tablaRutasgeneral.setItems(datos);
        tablaRutasgeneral.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ConexionBD.getInstance().cerrarConexion(st);
     } 
    
    private void agregarColumasRuta(){
        idped.setCellValueFactory(new PropertyValueFactory<>("id_pedido"));
        dir.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        idpedido.setCellValueFactory(new PropertyValueFactory<>("id_pedido"));
        direccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
    }
    
    public static void accionDoubleClickTabla( TableView<Pedido> eliminado,  TableView<Pedido> agregado){
        eliminado.setRowFactory(tv -> {
            TableRow<Pedido> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
                     && event.getClickCount() == 1) {

                    Pedido clickedRow = row.getItem();
                    agregado.getItems().add(clickedRow);
                    eliminado.getItems().remove(clickedRow);
                }
            });
            return row ;
        });
    }
    
    @FXML
    private void regreso(MouseEvent event) {
    }
    
     @FXML
    private void btnGuardar (MouseEvent event) {
        ObservableList<Pedido> pedidos =tablaRutasAsignadas.getItems();
        if(pedidos.isEmpty()){
            Emergentes.mostrarDialogo("Debe seleccionar al menos un pedido para crear una ruta.",
                    "No hay Pedidos Asignados.", "Aviso");
        }else if(Emergentes.comfirm("¿Desea guardar los cambios?")){
            try {
                control.guardarRuta(pedidos);
                //TODO: datos del repartidor
                Emergentes.mostrarDialogo("", "Se ha guardado correctamente", "Aviso");
                Stage stage = (Stage) tablaRutasAsignadas.getScene().getWindow();
                PantallaRutasController.getController().llenar();
                stage.close();
                if(PantallaDeEsperaController.getPantalla() != null){
                   PantallaDeEsperaController.getPantalla().llenar();
                }
            } catch (SQLException ex) {
                Emergentes.mostrarDialogo(ex.getMessage(), "Error de Guardado", "Error");
            }
        }
    }
    
     @FXML
    private void btnCancelar (MouseEvent event) {
        Stage stage = (Stage) tablaRutasAsignadas.getScene().getWindow();
        stage.close();
    }
}
