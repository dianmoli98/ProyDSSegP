/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tecnoimport;

import Controller.CtrlMaster;
import java.net.URL;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Inventario.Producto;

/**
 * FXML Controller class
 *
 * @author User-pc
 */
public class FXMLVistaTProductoController implements Initializable {

    @FXML
    private TableView<Producto> tablaProductos;
    @FXML
    private TableColumn<Producto,String> id_producto;
    @FXML
     private TableColumn<Producto,String> nombre;
    @FXML
     private TableColumn<Producto,String> descripcion;
    @FXML
     private TableColumn<Producto,String>precio_Venta;
    @FXML
     private TableColumn<Producto,String> ccategoria;
    @FXML
    private TextField busqueda;
    @FXML
    private ComboBox<?> comboxbus;
    @FXML
    private ImageView regreso;
    @FXML
    private TextField txtnombre;
    @FXML
    private TextField txtmarca;
    @FXML
    private TextField txtstock;
    @FXML
    private TextField txtprecio;
    @FXML
    private Label fecha;
    @FXML
    private Label nomE;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    Calendar calendar= GregorianCalendar.getInstance();
    Date date=Calendar.getInstance().getTime();
    SimpleDateFormat sdf=new SimpleDateFormat("     dd/MM/yyyy");
    fecha.setText(sdf.format(date));
        try {
            llenar();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLVistaTProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void llenar() throws SQLException{
            ResultSet rs = CtrlMaster.llenarTablaProductos();
            id_producto.setCellValueFactory(new PropertyValueFactory<>("id_producto"));
            nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            precio_Venta.setCellValueFactory(new PropertyValueFactory<>("precio"));
            ccategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
            tablaProductos.setVisible(true);
        try {
            ObservableList<Producto> datos = FXCollections.observableArrayList();
            while (rs.next()) {
                if (!rs.getString("id_producto").equalsIgnoreCase("0")) {
                    String id_producto1 = rs.getString("id_producto");
                    String nombre1 = rs.getString("nombre");
                    String descri = rs.getString("descripcion");
                    String precio1 = rs.getString("precio");
                    String categoria1 = rs.getString("categoria");
                    Producto p1 = new Producto(Integer.parseInt(id_producto1),nombre1,descri,Double.parseDouble(precio1),categoria1);
                    datos.add(p1);
                }
            }
            
            tablaProductos.setItems(datos);
            tablaProductos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLVistaTProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    
        
    @FXML
    private void Inicio(MouseEvent event) {
    }
    
     @FXML
    private void Modificar(MouseEvent event) {
    }
    
     @FXML
    private void Eliminar(MouseEvent event) {
    }

    @FXML
    private void regreso(MouseEvent event) {
    }
    
}
