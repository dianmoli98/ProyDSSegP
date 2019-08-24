/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.local.Gerente;
import model.local.Persona;
import model.local.Usuario;
import model.singleton.ConexionBD;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Francisco
 */
public class CtrlGerenteIT {
    
     private ConexionBD bd;
     private Connection conn;
     private CtrlGerente instance;
     
    public CtrlGerenteIT() throws SQLException {
        bd=ConexionBD.getInstance();
        instance = new CtrlGerente((Gerente)CtrlMaster.getUser());
       try {
           conn=bd.conectarMySQLTest();
       } catch (SQLException ex) {
           Logger.getLogger(CtrlGerenteIT.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    

    /**
     * Este test valida que el objeto de tipo persona que quiero obtener mediante 
     * el query sea el correcto, validando que el ResultSet este abierto y que la conexcion 
     * sea la adecuada, utilizamos un assertEquals de tal manera que me indique si la data de ambos 
     * objetos son iguales, previo a esto se debio implementar el equals y el hashcode dentro de la clase Persona. 
     */
    @Test
    public void testObtenerPersona() throws Exception {
        System.out.println("Obtener Persona");
        String cedula="0950165811";
        ResultSet rs = null;
        try (Statement st = conn.createStatement()) {
            String query = "SELECT Persona.cedula,Persona.nombre,Persona.apellido "
                    + "FROM Usuario JOIN Persona On Persona.cedula=Usuario.cedula "
                    + " Where Usuario.cedula= '"+cedula+"'";
            rs = st.executeQuery(query);
            CtrlMaster.validarResult(rs);
            Persona exp = instance.obtenerPersona(rs);
            Persona expResult = new Persona("Francisco", "Morales", "0950165811");
            assertEquals(expResult, exp);
            System.out.println("Test con exito");
        }
    }   
}
