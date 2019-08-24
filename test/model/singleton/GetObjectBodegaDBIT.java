/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.singleton;

import controller.CtrlGerenteIT;
import controller.CtrlJefeBodega;
import controller.CtrlMaster;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bodega.JefeBodega;
import model.inventario.Matriz;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Francisco
 */
public class GetObjectBodegaDBIT {
    private ConexionBD bd;
     private Connection conn;
   private  CtrlJefeBodega instance;
   
    public GetObjectBodegaDBIT() throws SQLException {
        bd=ConexionBD.getInstance();
        CtrlMaster.buscarUsuario("pbmoral", "pbmoral");
        instance=new CtrlJefeBodega((JefeBodega)CtrlMaster.getUser());
        try {
           conn=bd.conectarMySQLTest();
       } catch (SQLException ex) {
           Logger.getLogger(CtrlGerenteIT.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

   /**
     *Verifica que la matriz donde el jefe de bodega trabaja, de tal manera que verifica en la base de datos su existencia, se usa el assertEquals 
     * para saber si esos dos objetos son iguales de tal manera que se pueda verificar si el nombre y la direccion del lugar del trabajo 
     * del empleado es igual a la que se encuentra registrada en la base de datos.
     */
    
    @Test
    public void testObtenerMatriz() throws Exception {
        System.out.println("Obtener Matriz");
        String matriz = getMatriz("SELECT * FROM Usuario "
                + "JOIN Persona ON Usuario.cedula= Persona.cedula "
                + "Where usuario =\""+bd.getUser()+"\";");
        Matriz expResult = new Matriz(matriz,"Av. Francisco de Orellana y Calle Sexta","Sucursal");
        Matriz result = instance.obtenerMatriz(matriz);
        assertEquals(expResult, result);
    }
    
    private String getMatriz(String query) throws SQLException{
        ConexionBD bd = ConexionBD.getInstance();
        Connection conn = bd.conectarMySQL();
        try(Statement st = conn.createStatement()){
            try(ResultSet rs = st.executeQuery(query)){
                rs.next();
                return rs.getString("matriz_id");
            }
        }finally{
            bd.cerrarConexion(conn);
        }
    }
}
