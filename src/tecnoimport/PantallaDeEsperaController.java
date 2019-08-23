/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tecnoimport;

import controller.CtrlJefeBodega;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.bodega.Ruta;
import model.singleton.ConexionBD;

/**
 * FXML Controller class
 *
 * @author User-pc
 */
public class PantallaDeEsperaController implements Initializable {

    @FXML
    private GridPane gridd;
    @FXML
    private ListView<String> codRepartidor;
    @FXML
    private ListView<Integer> codRuta;
    @FXML
    private ImageView imagenc;
    
    private CtrlJefeBodega control;
    
    private static PantallaDeEsperaController pantalla = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setPantalla(this);
        control = FXMLVistaTController.getControlJefe();
        try {
            llenar();
        } catch (SQLException ex) {
            Logger.getLogger(PantallaDeEsperaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    public static void setPantalla(PantallaDeEsperaController p){
        pantalla = p;
    }
    
    public void llenar() throws SQLException{
        codRepartidor.getItems().clear();
        codRuta.getItems().clear();
        ConexionBD bd = ConexionBD.getInstance();
        Connection conn = bd.conectarMySQL();
        
        try(Statement st = conn.createStatement()){
            ResultSet rs = control.obtenerRSRutas(st);
        while(rs.next()){
            Ruta r = control.obtenerRuta(rs);
            codRepartidor.getItems().add(r.getRepartidor().getId());
            codRuta.getItems().add(r.getIdRuta());
        }
        }
    }
    
    public static PantallaDeEsperaController getPantalla(){
        return pantalla;
    }
    
}
