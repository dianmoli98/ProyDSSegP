/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tecnoimport;

import controller.CtrlGerente;
import controller.CtrlJefeBodega;
import controller.CtrlMaster;
import emergentes.Emergentes;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.bodega.JefeBodega;
import model.local.Gerente;
import model.local.Usuario;
import model.local.Vendedor;
import static tecnoimport.TecnoImport.ventsegunda;

/**
 * FXML Controller class
 *
 * @author User-pc
 */
public class FXMLVistaTController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Label nomE;
    @FXML
    private Button administracion;
    @FXML
    private Button ventas;
    @FXML
    private Button clientes;
    @FXML
    private Button consultas;
    @FXML
    private Button ruta;
    @FXML
    private Button imprimir;
    @FXML
    private Label fechaactual;
    @FXML
    private Label empleado;
    
    private static CtrlGerente controlGerente = null;
    private static CtrlJefeBodega controlJefe = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Date date=Calendar.getInstance().getTime();
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        fechaactual.setText(sdf.format(date));
        
        Usuario user = CtrlMaster.getUser();
        empleado.setText(user.getNombre() + " " + user.getApellido());
        if(user instanceof Gerente){
            ruta.setDisable(true);
            imprimir.setDisable(true);
            ventas.setDisable(true);
            clientes.setDisable(true);
            
        }else if(user instanceof JefeBodega){
            ventas.setDisable(true);
            clientes.setDisable(true);
            setJefe();
            try {
                Parent root3 = FXMLLoader.load(getClass().getResource("/tecnoimport/Pantalla de Espera.fxml"));
                Scene scene3 = new Scene(root3);
                ventsegunda.setScene(scene3);
                ventsegunda.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMLVistaTController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else if(user instanceof Vendedor){
            ruta.setDisable(true);
            
        }if(!user.isIsAdmin()){
            administracion.setDisable(true);
        }
    }

    private static void setJefe(){
        controlJefe = new CtrlJefeBodega((JefeBodega)CtrlMaster.getUser());
    }
    
    @FXML
    private void cerrarSesion(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("PantallaAcceso.fxml"));
        Stage stage= new Stage();
        if (Emergentes.comfirm("¿Está seguro que desea cerrar sesión?")){
            stage.setScene(new Scene(root));
            stage.show();
            final Node source = (Node) event.getSource();
            final Stage stage1 = (Stage) source.getScene().getWindow();
            stage1.close();
            ventsegunda.close();
        }
    }

    @FXML
    private void administrar(MouseEvent event) throws IOException {
        mostrarPantallaConstruccion();
    }

    @FXML
    private void realizarVenta(MouseEvent event) throws IOException{
        mostrarPantallaConstruccion();
    }

    @FXML
    private void agregarClientes(MouseEvent event)throws IOException {
        mostrarPantallaConstruccion();
    }

    @FXML
    private void realizarConsultas(MouseEvent event)throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Pantallaconsultas.fxml"));
         Stage stage= new Stage();
         stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(label.getScene().getWindow());
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void rutas(MouseEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("PantallaRutas.fxml"));
        Stage stage= new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(label.getScene().getWindow());
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void imprimirDoc(MouseEvent event) throws IOException {
        mostrarPantallaConstruccion();
    }
    
    private void mostrarPantallaConstruccion() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource(PantallaConstruccionController.PCONSTRUCCION));
        Stage stage= new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(label.getScene().getWindow());
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    public static CtrlJefeBodega getControlJefe(){
        return controlJefe;
    }
    
    public static CtrlGerente getControlGerente(){
        return controlGerente;
    }
    
}
