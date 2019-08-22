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
import javafx.scene.control.Alert;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat sdf=new SimpleDateFormat("     dd/MM/yyyy");
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
        id_producto.setCellValueFactory(new PropertyValueFactory<>("id_producto"));
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        precio_Venta.setCellValueFactory(new PropertyValueFactory<>("precio"));
        ccategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        
    }
    
    public void llenar() throws SQLException{
        generarFactories();
        tablaProductos.setVisible(true);    
        
        ConexionBD bd = ConexionBD.getInstance();
        Connection conn = bd.conectarMySQL();
        String query = "select * from Producto";  
        ResultSet rs;

        try (Statement st = conn.createStatement()) {
            rs = st.executeQuery(query);
            celdas(conn,rs);
        } catch (SQLException ex) {
            throw new SQLException("La base de datos se desconectó inesperadamente.");
        }
     }
    
    private void ocultar(){
        lblprecio.setVisible(false);
        txtprecio.setVisible(false);
    }
    
    public void setCenter(){
        busqueda.setPromptText("Ingrese su búsqueda");
        ObservableList ob=FXCollections.observableArrayList("Nombre","Categoria");
        comboxbus.setItems(ob);
        comboxbus.setPromptText("Filtrar");
        comboxbus.setOnAction((action)->{
            if(comboxbus.getValue().equals("Nombre")){
                busqueda.setPromptText("Nombre");
            }else if(comboxbus.getValue().equals("Categoria")){
                busqueda.setPromptText("Categoria");
            }else{
                busqueda.setPromptText("Ingrese su búsqueda"); 
            }
        });
        
        busqueda.textProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue args0,Object o1,Object o2){
                String comboText=(String)comboxbus.getValue();
                if (comboText != null && !comboText.equals("") && !comboText.equals(" ")) {
                try {
                    Connection conn;
                    ResultSet rs;
                    String stbuscar = "";

                        String stringActual = (String) o2;
                        if (comboxbus.getValue().equals("Nombre")) {
                            ConexionBD bd = ConexionBD.getInstance();      
                            stbuscar = "select * from Producto where nombre like " + " \'" + busqueda.getText() + "%\' ;";
                            conn = bd.conectarMySQL();
                            
                            
                            rs = bd.seleccionarDatos(stbuscar,conn);
                            celdas(conn,rs);

                        } else if (comboxbus.getValue().equals("Categoria")) {
                            ConexionBD bd = ConexionBD.getInstance();    
                            stbuscar = "select * from Producto where categoria like " + " \'" + busqueda.getText() + "%\' ;";
                            conn = bd.conectarMySQL();
                            rs = bd.seleccionarDatos(stbuscar,conn);
                            celdas(conn,rs);
                        }
                        
                       if(busqueda.getText().equals("")){
                        ConexionBD bd = ConexionBD.getInstance();       
                        stbuscar = "select * from Producto;"; 
                        conn = bd.conectarMySQL();
                            rs = bd.seleccionarDatos(stbuscar,conn);
                            celdas(conn,rs);}
                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLVistaTProductoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            }
        });
     }
      
    private void celdas(Connection st,ResultSet rs){
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
                    Producto p1 = new Producto(Integer.parseInt(id_producto1),
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
    private void Inicio(MouseEvent event) {
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
                emergentes.Emergentes.mostrarDialogo("No ha seleccionado ninguna celda.", "Falla de selección", "Error");
            }
        }  
    }
    
    private void mostrar(){
        lblprecio.setVisible(true);
        txtprecio.setVisible(true);
    }
    
      @FXML
    private void actualizar(MouseEvent event) throws SQLException {
        if(CtrlMaster.getUser().isIsAdmin()){
            try{
        Producto p = tablaProductos.getSelectionModel().getSelectedItem();
        String modify = "update Producto set precio= '" + txtprecio.getText() 
                + "' where id_producto= '" + p.getIdProducto() + "' ; ";
        
        ConexionBD bd = ConexionBD.getInstance();
        Connection conn = bd.conectarMySQL();
        Statement st = conn.createStatement();
        st.execute(modify);
               
        String show = "select * from Producto";
        Connection connn = bd.conectarMySQL();
        ResultSet rs = bd.seleccionarDatos(show, connn);
        celdas(conn,rs);
            ocultar();
    }catch (Exception e) {
                    ocultar();
                          Alert mensajeExp = new Alert(Alert.AlertType.CONFIRMATION);
        mensajeExp.setHeaderText("Diálogo de confirmación");
        mensajeExp.setContentText ("No has seleccionado ninguna celda");
        mensajeExp.showAndWait();
                }}
        }  
        
        
 

    @FXML
    private void regreso(MouseEvent event) {
}
    
}
