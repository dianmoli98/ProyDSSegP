package tecnoimport;

import controller.CtrlMaster;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import model.inventario.Producto;
import model.local.Usuario;
import model.singleton.ConexionBD;
import static model.singleton.ConexionBD.lanzarException;

/**
 * FXML Controller class
 *
 * @author User-pc
 */
public class FXMLVistaTProductoController implements Initializable {

    @FXML
    private TableView<Producto> tablaProductos;
    @FXML
    private TableColumn<Producto,String> idProducto;
    @FXML
     private TableColumn<Producto,String> nombre;
    @FXML
     private TableColumn<Producto,String> descripcion;
    @FXML
     private TableColumn<Producto,String>precioVenta;
    @FXML
     private TableColumn<Producto,String> ccategoria;
    @FXML
    private TextField busqueda;
    @FXML
    private ComboBox<?> comboxbus;
    @FXML
    private ImageView regreso;
    @FXML
    private Label lblprecio;
    @FXML
    private TextField txtprecio;
    @FXML
    private Label fecha;
    @FXML
    private Label nomE;
    @FXML
    private ImageView insertar;
    @FXML
    private ImageView act;
    
    private static final String NOM = "Nombre";
    private static final String IDPROD = "id_producto";
    private static final String CAT = "Categoria";
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("     dd/MM/yyyy");
        fecha.setText(sdf.format(date));
        Usuario user = CtrlMaster.getUser();
        nomE.setText(user.getNombre() + " " + user.getApellido());
        ocultar();
        setCenter();
        try {
            llenar();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLVistaTProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void  generarFactories(){
        idProducto.setCellValueFactory(new PropertyValueFactory<>("idProducto"));
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        precioVenta.setCellValueFactory(new PropertyValueFactory<>("precio"));
        ccategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        
    }
    
    public void llenar() throws SQLException{
        generarFactories();
        tablaProductos.setVisible(true);    
        
        ConexionBD bd = ConexionBD.getInstance();
        Connection conn = bd.conectarMySQL();
        String query = "select * from Producto";  

        try (Statement st = conn.createStatement()) {
            try(ResultSet rs = st.executeQuery(query)){
                celdas(rs);
            }
        } catch (SQLException ex) {
            lanzarException();
        }
     }
    
    private void ocultar(){
        lblprecio.setVisible(false);
        txtprecio.setVisible(false);
    }
    
    public void setCenter(){
        busqueda.setPromptText("Ingrese su búsqueda");
        ObservableList ob = FXCollections.observableArrayList(NOM,CAT);
        comboxbus.setItems(ob);
        comboxbus.setPromptText("Filtrar");
        comboxbus.setOnAction( action ->{
            if(comboxbus.getValue().equals(NOM)){
                busqueda.setPromptText(NOM);
            }else if(comboxbus.getValue().equals(CAT)){
                busqueda.setPromptText(CAT);
            }else{
                busqueda.setPromptText("Ingrese su búsqueda"); 
            }
        });
        
        busqueda.textProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue args0,Object o1,Object o2){
                    try {
                    actionChange();
                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLVistaTProductoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        });
    }
    
    private void actionChange() throws SQLException{
        String comboText=(String)comboxbus.getValue();
        if (comboText != null && !comboText.equals("") && !comboText.equals(" ")) {
            ConexionBD bd = ConexionBD.getInstance(); 
            Connection conn = bd.conectarMySQL(); 
            String stbuscar = " ";
            if (comboxbus.getValue().equals(NOM)) {
                stbuscar = "select * from Producto where nombre like " + " \'" + busqueda.getText() + "%\' ;";
            }else if (comboxbus.getValue().equals(CAT)) {
                stbuscar = "select * from Producto where categoria like " + " \'" + busqueda.getText() + "%\' ;";
            }
            
            try (Statement st = conn.createStatement()) {
                try(ResultSet rs = st.executeQuery(stbuscar)){
                    celdas(rs); 
                }
                if(busqueda.getText().equals("")){ 
                    stbuscar = "select * from Producto;";
                    try(ResultSet rs = st.executeQuery(stbuscar)){
                        celdas(rs);
                    }
                }
            } catch (SQLException ex) {
                throw new SQLException("La base de datos se desconectó inesperadamente.");
            }
        }
    }
      
    private void celdas(ResultSet rs){
        tablaProductos.setVisible(true);
        try {
            ObservableList<Producto> datos = FXCollections.observableArrayList();
            while (rs.next()) {
                if (!rs.getString(IDPROD).equalsIgnoreCase("0")) {
                    String idProducto1 = rs.getString(IDPROD);
                    String nombre1 = rs.getString("nombre");
                    String descri = rs.getString("descripcion");
                    String precio1 = rs.getString("precio");
                    String categoria1 = rs.getString("categoria");
                    Producto p1 = new Producto(Integer.parseInt(idProducto1),
                            nombre1,descri,Double.parseDouble(precio1),categoria1);
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
    private void inicio(MouseEvent event) {
        //no se usa el boton
    }
    
     @FXML
    private void modificar(MouseEvent event) {
        if(CtrlMaster.getUser().isIsAdmin()){
            try{
                mostrar();
                Producto p = tablaProductos.getSelectionModel().getSelectedItem();
                txtprecio.setText(String.valueOf(p.getPrecio()));  
            }catch (Exception e) {
                ocultar();
                mostrarEmergente();
            }
        }  
    }
    
    private void mostrar(){
        lblprecio.setVisible(true);
        txtprecio.setVisible(true);
    }
    
      @FXML
    private void actualizar(MouseEvent event) throws SQLException {
        Producto p = tablaProductos.getSelectionModel().getSelectedItem();
        if(CtrlMaster.getUser().isIsAdmin() && p != null){
            String modify = "update Producto set precio= '" + txtprecio.getText() 
                    + "' where id_producto= '" + p.getIdProducto() + "' ; ";

            ConexionBD bd = ConexionBD.getInstance();
            Connection conn = bd.conectarMySQL();
            
            try (Statement st = conn.createStatement()) {
                st.execute(modify);
            } catch (SQLException ex) {
                lanzarException();
            }
            obtenerProductos();
            bd.cerrarConexion(conn);
        }
    }
       
    private void obtenerProductos() throws SQLException{        
        ConexionBD bd = ConexionBD.getInstance();
        Connection conn = bd.conectarMySQL();
        
        String query = "select * from Producto";
        try (Statement st = conn.createStatement()) {
            try(ResultSet rs = st.executeQuery(query)){
                celdas(rs);
                ocultar();
            }
        } catch (SQLException ex) {
             ocultar();
            mostrarEmergente();
            }
    }
    
    private void mostrarEmergente(){
        emergentes.Emergentes.mostrarDialogo("No se ha seleccinado ninguna celda.",
                        "Falta de selccion", "Error");
    }
    
    @FXML
    private void regreso(MouseEvent event) {
        //no habilitado
    }
}
