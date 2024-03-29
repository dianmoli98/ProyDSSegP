package tecnoimport;

import controller.CtrlJefeBodega;
import emergentes.Emergentes;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.bodega.Ruta;
import model.pedido.Pedido;
import model.singleton.ConexionBD;
import static tecnoimport.PantallaCrearRutasController.accionDoubleClickTabla;

/**
 * FXML Controller class
 *
 * @author User-pc
 */
public class FinalizaRutaController implements Initializable {

    @FXML
    private ImageView regreso;
    @FXML
    private TableView<Pedido> tablaRutasgeneral;
    @FXML
    private TableColumn<Pedido,String> idped;
    @FXML
    private TableColumn<Pedido,String> dir;
    @FXML
    private TableView<Pedido> tablaRutasAsignadas;
    @FXML
    private TableColumn<Pedido,String> idpedido;
    @FXML
    private TableColumn<Pedido,String> direccion;
    @FXML
    private TextField observaciones;
    private static CtrlJefeBodega control = FXMLVistaTController.getControlJefe();
    private Ruta ruta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ruta = PantallaRutasController.getRuta();
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
        
        try(Statement st = conn.createStatement()){
            celdas(conn,control.obtenerRSPedidos(st, ruta.getIdRuta()));
        }
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
    
    @FXML
    private void regreso(MouseEvent event) {
        //no se usa
    }

    @FXML
    private void btnGuardar(MouseEvent event) {
        ObservableList<Pedido> pedidosCancel =tablaRutasgeneral.getItems();
        ObservableList<Pedido> pedidosConfirm =tablaRutasAsignadas.getItems();
        String confirm = "¿Desea guardar los cambios?\nLas rutas no seleccionadas regresarán a pedidos por enviar."; 
        
        if(Emergentes.comfirm(confirm)){
            try{
                actionGuardar(pedidosConfirm, pedidosCancel);
            } catch (SQLException ex) {
                Emergentes.mostrarDialogo("No se pudo guardar los cambios.","Error de guardado", "Error");
            }
        }
    }
    
    private void actionGuardar(ObservableList<Pedido> pedidosConfirm, ObservableList<Pedido> pedidosCancel ) throws SQLException{
        String texto = observaciones.getText();
        boolean isFinalizada = (texto.equals("") && !pedidosConfirm.isEmpty()) || (!pedidosCancel.isEmpty() && !texto.equals(""));
        if(!pedidosCancel.isEmpty() && !texto.equals("")){
            for(Pedido p : pedidosCancel){
            cancelarPedidoBD(p);
            }
            actualizarObservaciones();
        }if(isFinalizada){
            finalizarRuta();
            control.actualizarRepartidores();
            PantallaRutasController.setRuta(null);
            PantallaRutasController.getController().llenar();
            if(PantallaDeEsperaController.getPantalla() != null)
                PantallaDeEsperaController.getPantalla().llenar();
            Stage stage = (Stage) tablaRutasAsignadas.getScene().getWindow();
            stage.close();
        }else{
            Emergentes.mostrarDialogo("Si hubieron inconvenientes con los pedidos deben ser escritos en observaciones.",
                        "", "Faltan Campos por llenar");
        }
    }
    
    private void cancelarPedidoBD(Pedido p) throws SQLException{
        String query = 
            "UPDATE Pedido SET id_ruta = NULL\n" +
            "WHERE id_pedido = " + p.getIdpedido() + ";";
        
        ConexionBD.getInstance().hacerQuery(query); 
    }
    
    private void actualizarObservaciones() throws SQLException{
        String query = 
            "UPDATE Ruta SET observaciones = \""+observaciones.getText()+"\"\n" +
            "WHERE id_ruta = "+ ruta.getIdRuta()+";";
        
        ConexionBD.getInstance().hacerQuery(query); 
    }
    
    private void finalizarRuta() throws SQLException{
        String query = 
            "UPDATE Ruta SET Realizado = \"V\"\n" +
            "WHERE id_ruta = "+ ruta.getIdRuta()+";";
        
        ConexionBD.getInstance().hacerQuery(query); 
    }
    
    @FXML
    private void btnCancelar(MouseEvent event) {
        PantallaRutasController.setRuta(null);
        Stage stage = (Stage) tablaRutasAsignadas.getScene().getWindow();
        stage.close();
    }
    
    private void agregarColumasRuta(){
        idped.setCellValueFactory(new PropertyValueFactory<>("idpedido"));
        dir.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        idpedido.setCellValueFactory(new PropertyValueFactory<>("idpedido"));
        direccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
    }
}
