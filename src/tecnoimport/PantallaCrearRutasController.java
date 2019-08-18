/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tecnoimport;

import Controller.CtrlMaster;
import Emergentes.Emergentes;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.singleton.ConexionBD;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author User-pc
 */
public class PantallaCrearRutasController implements Initializable {

    @FXML
    private TableView tablaRutasgeneral;
    @FXML
    private TableView tablaRutasAsignadas;
    private ObservableList<String> data;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            cargarData();
        } catch (SQLException ex) {
            Logger.getLogger(PantallaCrearRutasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    public void cargarData() throws SQLException {
            ConexionBD bd = ConexionBD.getInstance();
             Connection conn = bd.conectarMySQL();
            String sql1 = "select * from Cliente"; //  este es pero queria ver si me salia con este en general --- "select * from Cliente join pedido on id_cliente=cedula"
            String sql2="select * from Matriz join pedido on Matriz.id_matriz=Pedido.id_matriz";
            ResultSet rs = bd.seleccionarDatos(sql1, conn);
            ResultSet rs2=bd.seleccionarDatos(sql2,conn);
            TableColumn column = new TableColumn<>();
            column.setText("Direcciones-Pedido");
            column.setCellValueFactory(new PropertyValueFactory<>("direccion"));
            tablaRutasgeneral.getColumns().add(column);
            dataBaseArrayList(conn,rs);
    }
    

    private void dataBaseArrayList(Connection st,ResultSet rs) throws SQLException{
        tablaRutasgeneral.setVisible(true);
        try {
            data=FXCollections.observableArrayList();
             while (rs.next()) {
                    String dat=rs.getString("direccion");
                    data.add(dat);
                }
            tablaRutasgeneral.setEditable(true);
            //tablaRutasgeneral.setFocusTraversable(true);
            tablaRutasgeneral.setItems(data);
            tablaRutasgeneral.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLVistaTProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }  

    @FXML
    private void regreso(MouseEvent event) {
    }
    
     @FXML
    private void btnGuardar (MouseEvent event) {
    }

     @FXML
    private void btnCancelar (MouseEvent event) {
    }
}
