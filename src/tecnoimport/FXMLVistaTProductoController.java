/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tecnoimport;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author User-pc
 */
public class FXMLVistaTProductoController implements Initializable {

    @FXML
    private TableView<?> tablaProductos;
    @FXML
    private TableColumn<?, ?> Producto_ID;
    @FXML
    private TableColumn<?, ?> nombre;
    @FXML
    private TableColumn<?, ?> descripcion;
    @FXML
    private TableColumn<?, ?> stock;
    @FXML
    private TableColumn<?, ?> precio_Venta;
    @FXML
    private TableColumn<?, ?> ccategoria;
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
    fecha.setText(sdf.format(date));}
    
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
