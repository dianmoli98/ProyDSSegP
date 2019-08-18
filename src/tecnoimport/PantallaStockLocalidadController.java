/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tecnoimport;

import Controller.CtrlMaster;
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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Inventario.Dpro;
import model.Inventario.Producto;
import model.Local.Usuario;
import model.singleton.ConexionBD;

/**
 * FXML Controller class
 *
 * @author User-pc
 */
public class PantallaStockLocalidadController implements Initializable {

    @FXML
    private Label fechaactual;
    @FXML
    private Label empleado;
    @FXML
    private ImageView regreso;
    @FXML
    private TextField busqueda;
    @FXML
    private ComboBox<?> ComboLugar;
    @FXML
    private TableView<Dpro> tablaStock;
    @FXML
    private TableColumn<String,Dpro> nombrePro;
    @FXML
    private TableColumn<String,Dpro> stock;
    @FXML
    private TableColumn<String,Dpro> idlocal;
    @FXML
    private TableColumn<String,Dpro> direccion;
    @FXML
    private TableColumn<String,Dpro> lugar;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    Calendar calendar= GregorianCalendar.getInstance();
    Date date=Calendar.getInstance().getTime();
    SimpleDateFormat sdf=new SimpleDateFormat("     dd/MM/yyyy");
    
        try {
            llenar();
        } catch (SQLException ex) {
            Logger.getLogger(PantallaStockLocalidadController.class.getName()).log(Level.SEVERE, null, ex);
        }
     setCenter();   
    fechaactual.setText(sdf.format(date));
    Usuario user = CtrlMaster.getUser();
    empleado.setText(user.getNombre() + " " + user.getApellido());
    }    

    public void llenar() throws SQLException{
             ConexionBD bd = ConexionBD.getInstance();
             Connection conn = bd.conectarMySQL();
             String query = "select p.nombre,s.Stock,m.tipoLocalidad,m.direccion,m.id_matriz\n" +
"from producto  p\n" +
"join stock s on p.id_producto=s.id_producto\n" +
"join matriz m on m.id_matriz=s.id_matriz ;";
    ResultSet rs = bd.seleccionarDatos(query, conn);
    llenartable(conn,rs);}
    
    
    public void llenarMa() throws SQLException{
             ConexionBD bd = ConexionBD.getInstance();
             Connection conn = bd.conectarMySQL();
             String query = "select p.nombre,s.Stock,m.tipoLocalidad,m.direccion,m.id_matriz\n" +
"from producto  p\n" +
"join stock s on p.id_producto=s.id_producto\n" +
"join matriz m on m.id_matriz=s.id_matriz\n" +
"where tipoLocalidad=\"Matriz\";";
    ResultSet rs = bd.seleccionarDatos(query, conn);
    llenartable(conn,rs);}
    
    public void llenarSu() throws SQLException{
             ConexionBD bd = ConexionBD.getInstance();
             Connection conn = bd.conectarMySQL();
             String query = "select p.nombre,s.Stock,m.tipoLocalidad,m.direccion,m.id_matriz\n" +
"from producto  p\n" +
"join stock s on p.id_producto=s.id_producto\n" +
"join matriz m on m.id_matriz=s.id_matriz\n" +
"where tipoLocalidad=\"Sucursal\";";
    ResultSet rs = bd.seleccionarDatos(query, conn);
    llenartable(conn,rs);}
    
     public void llenarBo() throws SQLException{
             ConexionBD bd = ConexionBD.getInstance();
             Connection conn = bd.conectarMySQL();
             String query = "select p.nombre,s.Stock,m.tipoLocalidad,m.direccion,m.id_matriz\n" +
"from producto  p\n" +
"join stock s on p.id_producto=s.id_producto\n" +
"join matriz m on m.id_matriz=s.id_matriz\n" +
"where tipoLocalidad=\"Bodega\";";
    ResultSet rs = bd.seleccionarDatos(query, conn);
    llenartable(conn,rs);}
    

    public void llenartable(Connection conn,ResultSet rs){
             nombrePro.setCellValueFactory(new PropertyValueFactory<>("nombre"));
             stock.setCellValueFactory(new PropertyValueFactory<>("Stock"));
            idlocal.setCellValueFactory(new PropertyValueFactory<>("tipoLocalidad"));
            direccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
            lugar.setCellValueFactory(new PropertyValueFactory<>("id_matriz"));
            tablaStock.setVisible(true);
            celdas(conn,rs);
     }
    
    private void celdas(Connection st,ResultSet rs){
        tablaStock.setVisible(true);
        try {
            ObservableList<Dpro> datos = FXCollections.observableArrayList();
            while (rs.next()) {
                if (!rs.getString("nombre").equalsIgnoreCase("0")) {
                    String nom = rs.getString("nombre");
                    String sto = rs.getString("Stock");
                    String tloc = rs.getString("tipoLocalidad");
                    String dir = rs.getString("direccion");
                    String lug = rs.getString("id_matriz");
                    Dpro dv = new Dpro(nom,tloc,Integer.parseInt(sto),dir,lug);
                    datos.add(dv);
                }}
            tablaStock.setItems(datos);
            tablaStock.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLVistaTProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }  
    
    
    public void setCenter(){
        busqueda.setPromptText("Ingrese su búsqueda");
        ObservableList ob=FXCollections.observableArrayList("Matriz","Sucursal","Bodega");
        ComboLugar.setItems(ob);
        ComboLugar.setPromptText("Filtrar");
        ComboLugar.setOnAction((l)->{
            Connection st = null;
                    ResultSet rs = null;
                    String stbuscar = "";
            if(((String)ComboLugar.getValue()).equals("Matriz")){
                try {
                    llenarMa();
                } catch (SQLException ex) {
                    Logger.getLogger(PantallaStockLocalidadController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if(((String)ComboLugar.getValue()).equals("Sucursal")){
                busqueda.setPromptText("Sucursal");
                 try {
                    llenarSu();
                } catch (SQLException ex) {
                    Logger.getLogger(PantallaStockLocalidadController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(((String)ComboLugar.getValue()).equals("Bodega")){
                busqueda.setPromptText("Bodega");
            try {
                    llenarBo();
                } catch (SQLException ex) {
                    Logger.getLogger(PantallaStockLocalidadController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            
            }
                    else{
                busqueda.setPromptText("Ingrese su búsqueda"); 
            }
        });

        };
    
    
    
    @FXML
    private void regreso(MouseEvent event) {
    }
    
        }