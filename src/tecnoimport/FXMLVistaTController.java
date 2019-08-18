/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tecnoimport;

import Controller.CtrlGerente;
import Controller.CtrlJefeBodega;
import Controller.CtrlMaster;
import Controller.CtrlVendedor;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;
import model.Bodega.Jefe_Bodega;
import model.Local.Gerente;
import model.Local.Usuario;
import model.Local.Vendedor;
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
    private Button Ventas;
    @FXML
    private Button Clientes;
    @FXML
    private Button Consultas;
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
    private static CtrlVendedor controlVendedor = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Calendar calendar = GregorianCalendar.getInstance();
        Date date=Calendar.getInstance().getTime();
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        fechaactual.setText(sdf.format(date));
        
        Usuario user = CtrlMaster.getUser();
        empleado.setText(user.getNombre() + " " + user.getApellido());
        if(user instanceof Gerente){
            ruta.setDisable(true);
            imprimir.setDisable(true);
            Ventas.setDisable(true);
            Clientes.setDisable(true);
            
        }else if(user instanceof Jefe_Bodega){
            Ventas.setDisable(true);
            Clientes.setDisable(true);
            controlJefe = new CtrlJefeBodega((Jefe_Bodega)CtrlMaster.getUser());
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

    @FXML
    private void CerrarSesion(MouseEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Diálogo de confirmación");
        alert.setContentText ("¿Está seguro que desea cerrar sesión?");
        Optional <ButtonType> result = alert.showAndWait ();
        if (result.get () == ButtonType.OK){   
            Parent root = FXMLLoader.load(getClass().getResource("PantallaAcceso.fxml"));
            Stage stage= new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            final Node source = (Node) event.getSource();
            final Stage stage1 = (Stage) source.getScene().getWindow();
            stage1.close();
            ventsegunda.close();
        }
        
    }
    
    
    
    @FXML
    private void Administrar(MouseEvent event) {
    }

    @FXML
    private void RealizarVenta(MouseEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("pantalla_construccion.fxml"));
        Stage stage= new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(label.getScene().getWindow());
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void AgregarClientes(MouseEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("pantalla_construccion.fxml"));
        Stage stage= new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(label.getScene().getWindow());
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void RealizarConsultas(MouseEvent event)throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Pantallaconsultas.fxml"));
         Stage stage= new Stage();
         stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(label.getScene().getWindow());
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void Rutas(MouseEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("PantallaRutas.fxml"));
        Stage stage= new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(label.getScene().getWindow());
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void ImprimirDoc(MouseEvent event) {
    }
    
    public static CtrlJefeBodega getControlJefe(){
        return controlJefe;
    }
    
    public static CtrlGerente getControlGerente(){
        return controlGerente;
    }
    
    public static CtrlVendedor getControlVendedor(){
        return controlVendedor;
    }
}
