/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tecnoimport;

import Controller.CtrlGerente;
import Controller.CtrlMaster;
import Emergentes.Emergentes;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.Bodega.Ruta;
import model.Local.Gerente;
import model.Local.Persona;
import model.Local.Usuario;
import model.singleton.ConexionBD;

/**
 * FXML Controller class
 *
 * @author User-pc
 */
public class PantallaUsuariosController implements Initializable {

    @FXML
    private TableView<Persona> tablaUsuarios;
    @FXML
    private TableColumn<Persona, String> nombre;
    @FXML
    private TableColumn<Persona, String> apellido;
    @FXML
    private TableColumn<Persona, String> cedula;
    @FXML
    private TextField busqueda;
    @FXML
    private Label nomE;
    @FXML
    private Label fecha;
    @FXML
    private Button habilitar;
    @FXML
    private Button desabilitar;
    
    private static PantallaUsuariosController controller = null;
    private static CtrlGerente control = new CtrlGerente((Gerente)CtrlMaster.getUser());
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    Calendar calendar= GregorianCalendar.getInstance();
    Date date=Calendar.getInstance().getTime();
    SimpleDateFormat sdf=new SimpleDateFormat("     dd/MM/yyyy");
    fecha.setText(sdf.format(date));
    Usuario user = CtrlMaster.getUser();
    nomE.setText(user.getNombre() + " " + user.getApellido());
       controller = this;
        try {
            llenar();
        } catch (SQLException ex) {
            Logger.getLogger(PantallaRutasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void Convertirdmin(MouseEvent event) throws SQLException {
        Persona p=tablaUsuarios.getSelectionModel().getSelectedItem();
        control.AssignarAdministrador(p.getId(), true);
        Alert mensajeExp = new Alert(Alert.AlertType.CONFIRMATION);
        mensajeExp.setHeaderText("Diálogo de confirmación");
        mensajeExp.setContentText ("Estas seguro de asignarlo como administrador");
        mensajeExp.showAndWait();
        ((Node)(event.getSource())).getScene().getWindow().hide();
                
    }
    
    @FXML
    private void Desabilitar(MouseEvent event) throws SQLException {
        Persona p=tablaUsuarios.getSelectionModel().getSelectedItem();
        control.AssignarAdministrador(p.getId(), false);
        Alert mensajeExp = new Alert(Alert.AlertType.CONFIRMATION);
        mensajeExp.setHeaderText("Diálogo de confirmación");
        mensajeExp.setContentText ("Lo eliminaras de administrador, estas seguro?");
        mensajeExp.showAndWait();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
    public void llenar() throws SQLException{
        ConexionBD bd = ConexionBD.getInstance();
        Connection conn = bd.conectarMySQL();
        ResultSet rs = control.UsuarioByLocalidad();
        nombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        apellido.setCellValueFactory(new PropertyValueFactory<>("Apellido"));
        celdas(conn,rs);
    }
    
    private void celdas(Connection st,ResultSet rs) throws SQLException{
        tablaUsuarios.setVisible(true);
        ObservableList<Persona> datos = FXCollections.observableArrayList();
        while (rs.next()) {
            Persona r = control.obtenerPersona(rs);
            datos.add(r);
        }
        tablaUsuarios.setItems(datos);
        tablaUsuarios.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ConexionBD.getInstance().cerrarConexion(st);
    }
}
