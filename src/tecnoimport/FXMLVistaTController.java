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
import java.util.ResourceBundle;
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
    private Usuario user = CtrlMaster.getUser();
    private AuxiliarVistaT auxiliar=new AuxiliarVistaT();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CtrlMaster.callCalender(fechaactual, empleado);
        if(!user.isIsAdmin()){
            administracion.setDisable(true);
        }
        asignarUsuario();
        if(user instanceof JefeBodega){
            ventas.setDisable(true);
            clientes.setDisable(true);
            setJefe();
            auxiliar.crearPantallaEspera(); 
        }
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
        auxiliar.mostrarPantallaConstruccion(label);
    }

    @FXML
    private void realizarVenta(MouseEvent event) throws IOException{
        auxiliar.mostrarPantallaConstruccion(label);
    }

    @FXML
    private void agregarClientes(MouseEvent event)throws IOException {
        auxiliar.mostrarPantallaConstruccion(label);
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
        auxiliar.mostrarPantallaConstruccion(label);
    }
    
    private void asignarUsuario() {
        if (user instanceof Vendedor) {
            ruta.setDisable(true);
        } else {
            ventas.setDisable(true);
            clientes.setDisable(true);
            if (user instanceof Gerente) {
                ruta.setDisable(true);
                imprimir.setDisable(true);
            }
        }
    }
    public static CtrlJefeBodega getControlJefe(){
        return controlJefe;
        }
    
    public static CtrlGerente getControlGerente(){
        return controlGerente;
    }
    
    private static void setJefe(){
        controlJefe = new CtrlJefeBodega((JefeBodega)CtrlMaster.getUser());
    }
}
