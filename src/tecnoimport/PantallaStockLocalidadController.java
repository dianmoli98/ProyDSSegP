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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    private TableView<?> tablaStock;
    @FXML
    private TableColumn<?, ?> nombrePro;
    @FXML
    private TableColumn<?, ?> stock;
    @FXML
    private TableColumn<?, ?> idlocal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    Calendar calendar= GregorianCalendar.getInstance();
    Date date=Calendar.getInstance().getTime();
    SimpleDateFormat sdf=new SimpleDateFormat("     dd/MM/yyyy");
    fechaactual.setText(sdf.format(date));
    Usuario user = CtrlMaster.getUser();
    empleado.setText(user.getNombre() + " " + user.getApellido());
    }    

    public void setCenter(){
        busqueda.setPromptText("Ingrese su búsqueda");
        ObservableList ob=FXCollections.observableArrayList("Matriz","Sucursal","Bodega");
        ComboLugar.setItems(ob);
        ComboLugar.setPromptText("Filtrar");
        ComboLugar.setOnAction((l)->{
            if(((String)ComboLugar.getValue()).equals("Matriz")){
                busqueda.setPromptText("Matriz");
            }else if(((String)ComboLugar.getValue()).equals("Sucursal")){
                busqueda.setPromptText("Sucursal");
            }
            else if(((String)ComboLugar.getValue()).equals("Bodega")){
                busqueda.setPromptText("Bodega");}
                    else{
                busqueda.setPromptText("Ingrese su búsqueda"); 
            }
        });
        
        busqueda.textProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue args0,Object o1,Object o2){
                /*String comboText=(String)ComboLugar.getValue();
                if (comboText != null && !comboText.equals("") && !comboText.equals(" ")) {
                try {
                    Connection st = null;
                    ResultSet rs = null;
                    String stbuscar = "";

                        String stringActual = (String) o2;
                        if (((String) ComboLugar.getValue()).equals("Matriz")) {
                            ConexionBD bd = ConexionBD.getInstance();      
                            stbuscar = "select * from matriz where tipoLocalidad="+busqueda.getText() + "%\' ;";
                            st = bd.conectarMySQL();
                            rs = bd.seleccionarDatos(stbuscar,st);
                            celdas(st,rs);

                        } else if (((String) comboxbus.getValue()).equals("Categoria")) {
                            ConexionBD bd = ConexionBD.getInstance();    
                            stbuscar = "select * from producto where Categoria like " + " \'" + busqueda.getText() + "%\' ;";
                            st = bd.conectarMySQL();
                            rs = bd.seleccionarDatos(stbuscar,st);
                            celdas(st,rs);
                        }
                        
                       if(busqueda.getText().equals("")){
                        ConexionBD bd = ConexionBD.getInstance();       
                        stbuscar = "select * from producto;"; 
                        st = bd.conectarMySQL();
                            rs = bd.seleccionarDatos(stbuscar,st);
                            celdas(st,rs);}
                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLVistaTProductoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            }
        });*/
     }
                });}
    
    
    
    @FXML
    private void regreso(MouseEvent event) {
    }
    
        }