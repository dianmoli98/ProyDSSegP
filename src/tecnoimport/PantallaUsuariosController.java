/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tecnoimport;

import controller.CtrlGerente;
import controller.CtrlMaster;
import emergentes.Emergentes;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.local.Persona;
import model.local.Usuario;
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
    private static CtrlGerente control = new CtrlGerente(CtrlMaster.getUser());
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
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
    private void convertirdmin(MouseEvent event) throws SQLException {
        Persona p=tablaUsuarios.getSelectionModel().getSelectedItem();
        if(p == null){
            Emergentes.mostrarDialogo("Debe seleccionar el usuario que desea "
                    + "habilitar la opción de admin.", "Falta de Selección","Error");
        }else if(Emergentes.comfirm("Estas seguro de asignarlo como administrador")){
            control.asignarAdministrador(p.getId(), true);
            Stage stage = (Stage) ((Node)(event.getSource())).getScene().getWindow();
            stage.close();
        }
    }
    
    @FXML
    private void desabilitar(MouseEvent event) throws SQLException {
        Persona p=tablaUsuarios.getSelectionModel().getSelectedItem();
        if(p == null){
            Emergentes.mostrarDialogo("Debe seleccionar el usuario que desea deshabilitar la opción de admin.", "Falta de Selección","Error");
        }else if(Emergentes.comfirm("Lo eliminarás de administrador, estas seguro?")){
            control.asignarAdministrador(p.getId(), false);
            Stage stage = (Stage) ((Node)(event.getSource())).getScene().getWindow();
            stage.close();
        }
    }
    
    public void llenar() throws SQLException{
        ConexionBD bd = ConexionBD.getInstance();
        Connection conn = bd.conectarMySQL();
        
        nombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        apellido.setCellValueFactory(new PropertyValueFactory<>("Apellido"));
        
        String matriz = getMatriz("SELECT * FROM Usuario "
                + "JOIN Persona ON Usuario.cedula= Persona.cedula "
                + "Where usuario =\""+bd.getUser()+"\";");
        
        if (matriz == null) {
            throw new SQLException("Usuario no encontrado.\nInténtelo más tarde. ");
        }
        String query = "SELECT Persona.cedula,Persona.nombre,Persona.apellido "
                + "FROM Usuario JOIN Persona On Persona.cedula=Usuario.cedula "
                + " Where Usuario.matriz_id= \""+matriz+"\"";
        try(Statement st = conn.createStatement()){
            try(ResultSet rs = st.executeQuery(query)){
                celdas(conn,rs);
            }
        }finally{
            bd.cerrarConexion(conn);
        }
    }
    
    private String getMatriz(String query) throws SQLException{
        ConexionBD bd = ConexionBD.getInstance();
        Connection conn = bd.conectarMySQL();
        try(Statement st = conn.createStatement()){
            try(ResultSet rs = st.executeQuery(query)){
                rs.next();
                return rs.getString("matriz_id");
            }
        }finally{
            bd.cerrarConexion(conn);
        }
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
