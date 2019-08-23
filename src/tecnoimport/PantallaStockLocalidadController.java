/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import model.inventario.Dpro;
import model.local.Usuario;
import model.singleton.ConexionBD;

/**
 * FXML Controller class
 *
 * @author User-pc
 */
public class PantallaStockLocalidadController implements Initializable {
    ConexionBD bd = ConexionBD.getInstance();
    Connection conn = null;
    @FXML
    private Label fechaactual;
    @FXML
    private Label empleado;
     @FXML
    private ImageView insertar;
    @FXML
    private ImageView act;
    @FXML
    private ImageView regreso;
    @FXML
    private TextField busqueda;
    @FXML
    private ComboBox<?> comboLugar;
    @FXML
    private ComboBox idenlocal;
    @FXML
    private TableView<Dpro> tablaStock;
    @FXML
    private TableColumn<String, Dpro> idpro;
    @FXML
    private TableColumn<String, Dpro> nombrePro;
    @FXML
    private TableColumn<String, Dpro> stock;
    @FXML
    private TableColumn<String, Dpro> idlocal;
    @FXML
    private TableColumn<String, Dpro> direccion;
    @FXML
    private TableColumn<String, Dpro> lugar;
     @FXML
    private Label lblstock;
    @FXML
    private TextField txtstock;
    
    private static final String NOMB = "nombre";
    private static final String BODEGA = "Bodega";
    private static final String SUC = "Sucursal";
    private static final String MATRIZ = "Matriz";
    
    private static final String QUERYIN = "select p.id_producto,p.nombre,s.Stock,m.tipoLocalidad,m.direccion,m.id_matriz\n"
                + "from Producto  p\n"
                + "join Stock s on p.id_producto=s.id_producto\n"
                + "join Matriz m on m.id_matriz=s.id_matriz ";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        busqueda.setDisable(true);
        insertar.setVisible(false);
        act.setVisible(false);
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("     dd/MM/yyyy");
        ocultar();
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

    private void ocultar(){
        lblstock.setVisible(false);
        txtstock.setVisible(false);
    }
        
    private void mostrar(){
        lblstock.setVisible(true);
        txtstock.setVisible(true);
    }
    
    public void llenar() throws SQLException {
        llenarTablas(QUERYIN);
    }

    public void llenarMa() throws SQLException {
        llenarTablas(QUERYIN
                + "where tipoLocalidad=\"Matriz\";");
    }

    public void llenarSu() throws SQLException {
        llenarTablas(QUERYIN
                + "where tipoLocalidad=\"Sucursal\";");
    }

    public void llenarBo() throws SQLException {
        llenarTablas(QUERYIN
                + "where tipoLocalidad=\"Bodega\";");
    }
    
    private void llenarTablas(String query) throws SQLException{
        conn = bd.conectarMySQL();
        try (Statement st = conn.createStatement()) {
            try(ResultSet rs = st.executeQuery(query)){
                llenartable(conn, rs);
            }
        } catch (SQLException ex) {
            ConexionBD.lanzarException();
        }
        bd.cerrarConexion(conn);
    }
    
    public Object[] poblarCombox(String tabla, String nombrecol) throws SQLException {
        int registros = poblarComboxCount(tabla);
        Object[] datos = new Object[registros];
        String query = "SELECT id_matriz FROM Matriz where tipoLocalidad='"+comboLugar.getValue().toString()+"'";
        conn = bd.conectarMySQL();
        try (Statement st = conn.createStatement()) {
            try(ResultSet rs = st.executeQuery(query)){
                int i = 0;
                while (rs.next()) {
                    datos[i] = rs.getObject(nombrecol);
                    i++;
                }
            }
        } catch (SQLException ex) {
            ConexionBD.lanzarException();
        }finally{
            bd.cerrarConexion(conn);
        }
        return datos;
    }
    
    private int poblarComboxCount(String tabla) throws SQLException{
        conn = bd.conectarMySQL();
        String query = "SELECT count(*) as total FROM " + tabla;
        try (Statement st = conn.createStatement()) {
            try(ResultSet rs = st.executeQuery(query)){
                rs.next();
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            ConexionBD.lanzarException();
        }finally{
            bd.cerrarConexion(conn);
        }
        return 0;
    }
    
    public void llenartable(Connection conn, ResultSet rs) {
        nombrePro.setCellValueFactory(new PropertyValueFactory<>(NOMB));
        stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        idlocal.setCellValueFactory(new PropertyValueFactory<>("tipoLocalidad"));
        direccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        lugar.setCellValueFactory(new PropertyValueFactory<>("idMatriz"));
        idpro.setCellValueFactory(new PropertyValueFactory<>("idProducto"));
        tablaStock.setVisible(true);
        celdas(rs);
    }

    private void celdas(ResultSet rs) {
        tablaStock.setVisible(true);
        try {
            ObservableList<Dpro> datos = FXCollections.observableArrayList();
            while (rs.next()) {
                if (!rs.getString(NOMB).equalsIgnoreCase("0")) {
                    String nom = rs.getString(NOMB);
                    String sto = rs.getString("stock");
                    String tloc = rs.getString("tipoLocalidad");
                    String dir = rs.getString("direccion");
                    String lug = rs.getString("id_matriz");
                    String idprod = rs.getString("id_producto");
                    Dpro dv = new Dpro(nom, tloc, Integer.parseInt(sto), dir, lug,Integer.parseInt(idprod));
                    datos.add(dv);
                }
            }
            tablaStock.setItems(datos);
            tablaStock.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLVistaTProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setCentercopy() throws SQLException { 
        idenlocal.setOnAction(l -> {
            try {
                actionidLocal();
            } catch (SQLException ex) {
                Logger.getLogger(PantallaStockLocalidadController.class.getName()).log(Level.SEVERE, null, ex);
            }});

        busqueda.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue args0, Object o1, Object o2) {
                busquedaChanged();
            }
        });
    }

    private void busquedaChanged(){
        String comboText = (String) idenlocal.getValue();
        if (comboText != null && !comboText.equals("") && !comboText.equals(" ")) {
            try {
                conn = bd.conectarMySQL();
                llenarTablas(QUERYIN
                    + "where m.tipoLocalidad='"+ comboLugar.getValue().toString()
                    +"' and m.id_matriz='"+ idenlocal.getValue().toString()
                    +"' and p.nombre like " + " \'" + busqueda.getText() + "%\' ;");
            } catch (SQLException ex) {
                Logger.getLogger(PantallaStockLocalidadController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void actionidLocal() throws SQLException {
        insertar.setVisible(true);
        act.setVisible(true);
        busqueda.setDisable(false);
        Object o = idenlocal.getValue();
        String numlocal = "";
        if(o !=null){
            numlocal = o.toString();
        }else{
            throw new SQLException("No se tiene el número de local.");
        }
        if (numlocal != null && !numlocal.equals("") && !numlocal.equals(" ")) {
            conn = bd.conectarMySQL();
            llenarTablas(QUERYIN
                    + "where m.tipoLocalidad='"+ comboLugar.getValue().toString()+"' and  m.id_matriz='" + numlocal + "';");
        }    
    }
    
    public void setCenter() {
        busqueda.setPromptText("Ingrese su búsqueda");
        ObservableList ob = FXCollections.observableArrayList(MATRIZ,SUC,BODEGA);
        comboLugar.setItems(ob);
        comboLugar.setPromptText("Filtrar");
        comboLugar.setOnAction(l ->{
            try{
                actionCombo();
            } catch (SQLException ex) {
                Logger.getLogger(PantallaStockLocalidadController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    private void actionCombo() throws SQLException{
        idenlocal.getItems().clear();
        if (comboLugar.getValue().equals(MATRIZ)) {
            busqueda.setPromptText(MATRIZ);
            llenarMa();
            com();
        } else if (comboLugar.getValue().equals(SUC)) {
            busqueda.setPromptText(SUC);
            llenarSu();
            com();
        } else if (comboLugar.getValue().equals(BODEGA)) {
            busqueda.setPromptText(BODEGA);
            llenarBo();
            com();
        }else {
            busqueda.setPromptText("Ingrese su búsqueda");
        }
    }
    
    public void com() throws SQLException{
        Object[] idarticulo = poblarCombox(MATRIZ, "id_matriz");
        for (int i = 0; i < idarticulo.length; i++) {
            idenlocal.getItems().add(idarticulo[i]);
        }
        setCentercopy();
    }
  
    @FXML
    private void regreso(MouseEvent event) {
        //no habilitado
    }

    @FXML
    private void modificar(MouseEvent event) {
        if(CtrlMaster.getUser().isIsAdmin()){
            try{
        mostrar();
        Dpro p = tablaStock.getSelectionModel().getSelectedItem();
        txtstock.setText(String.valueOf(p.getStock()));  
        }catch (Exception e) {
                    ocultar();
                          Alert mensajeExp = new Alert(Alert.AlertType.CONFIRMATION);
        mensajeExp.setHeaderText("Diálogo de confirmación");
        mensajeExp.setContentText ("No has seleccionado ninguna celda");
        mensajeExp.showAndWait();
                }
        }
        
    }
    
    @FXML
    private void actualizar(MouseEvent event) throws SQLException {
        if(CtrlMaster.getUser().isIsAdmin()){
            Dpro p = tablaStock.getSelectionModel().getSelectedItem();
            if(p == null){
                emergentes.Emergentes.mostrarDialogo("Debe seleccionar un producto"
                    + " en el que cambiará el stock.", "Falta Selección", "Error");
                
            }else if (comboLugar.getValue() != null&& idenlocal.getValue() != null){
                actualizarCombo(p);
            }else{
                emergentes.Emergentes.mostrarDialogo("Debe seleccionar el código "
                    + "de la instalación que se desee modificar.", "Falta Selección", "Error");
            }
        }
    }
    
    private void actualizarCombo(Dpro p) throws SQLException{
        bd = ConexionBD.getInstance();
        conn = bd.conectarMySQL();
        String modify= "update Stock set stock= '" + txtstock.getText() 
                + "' where id_producto= '" + p.getIdProducto() 
                + "' and id_matriz='"+p.getIdMatriz()+"' ; ";
        try (Statement st = conn.createStatement()) {
            st.execute(modify);
        } catch (SQLException ex) {
            throw new SQLException("La base de datos se desconectó inesperadamente.");
        }

        String show = QUERYIN
                + "where m.tipoLocalidad='"+comboLugar.getValue().toString()
                +"' and m.id_matriz='"+ idenlocal.getValue().toString()+"' ;";
        try (Statement st = conn.createStatement()) {
            try(ResultSet rs = st.executeQuery(show)){
                celdas(rs);
                ocultar();
            }
        } catch (SQLException ex) {
            throw new SQLException("La base de datos se desconectó inesperadamente.");
        }
        bd.cerrarConexion(conn);
    }
}
