/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.singleton;

import controller.CtrlGerenteIT;
import controller.CtrlMaster;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bodega.Repartidor;
import model.local.Cliente;
import model.local.Persona;
import model.local.Usuario;
import model.local.Vendedor;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author JOVEN EJEMPLAR
 */
public class GetObjectPersonaDBIT {
    
    private ConexionBD bd;
    private Connection conn;
    GetObjectPersonaDB instance;
    
    public GetObjectPersonaDBIT() {
        bd=ConexionBD.getInstance();
        String user="pbmoral";
        String password="pbmoral";
        try {
            instance=GetObjectPersonaDB.getInstance();
             CtrlMaster.buscarUsuario(user, password);
            conn=bd.conectarMySQLTest();
        } catch (SQLException ex) {
            Logger.getLogger(GetObjectPersonaDBIT.class.getName()).log(Level.SEVERE, null, ex);
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
     * Este test valida que un cliente exista en la base de datos con el numero de cedula 
     * pasado como parametro, si el usuario no es nulo es por que esta guardado en la base caso contrario
     * no se encuentra registrado o la base de datos no se encuentra disponibles. 
     */
    @Test
    public void testObtenerCliente() throws Exception {
        System.out.println("Obtener Cliente");
        String cedula = "0946274823";
        Cliente result = instance.obtenerCliente(cedula);
        if(result!=null) assertNotNull("Valida que el cliente exista en la BD ",result);
        else System.out.println("El numero de cedula no pertenece a un cliente");
    }



    /**
     * Este test verifica que ademas que el metodo Obtener Vendedor sea diferente de null, 
     * tambien eta validando que este registrado como usuario en la base de datos, dando true a 
     * la afirmacion del enunciado pasandole como  parametro la cedula del vendedor, 
     * este test trabaja con un funcion auxiliar llamada validarUsuario(Vendedor v).
     */
    @Test
    public void testObtenerVendedor() throws Exception {
        System.out.println("Obtener Vendedor");
        String cedula = "0950165811";
        Vendedor result = instance.obtenerVendedor(cedula);
        assertTrue(validarUsuario(result));
    }
    
    private boolean validarUsuario(Usuario usuario) throws SQLException, Exception{
        if(usuario==null) return false;
        String query = "SELECT * FROM Usuario "
                + "JOIN Persona ON Usuario.cedula= Persona.cedula "
                + "Where Persona.cedula =\""+usuario.getId()+"\";";
        try(Statement st = conn.createStatement()){
            try(ResultSet rs = st.executeQuery(query)){
                if(rs!=null) return true;
            }
        }
        return false;
    }


    /**
     * Este test valida que el controlador master del sistema sea la persona apropiada de quien 
     * esta ingresando en el sistema, es decir que no haya incordancia en el inicio de sesion con el usuario 
     * principal, recordando que para ingresar a la base de datos, el usuario debe estar registrado 
     * entonces con este tes validamos que ambos esten referenciado al mismo usuario. 
     */
    @Test
    public void testObtenerPersona() throws Exception {
        System.out.println("Obtener Persona");
        String query = "SELECT * FROM Persona "
                + "JOIN Usuario ON Usuario.cedula= Persona.cedula "
                + "Where Persona.cedula =\"" + CtrlMaster.getUser().getId() + "\";";
        try (Statement st = conn.createStatement()) {
            try (ResultSet rs = st.executeQuery(query)) {
                rs.first();
                Persona result = instance.obtenerPersona(rs);
                Persona expResult = CtrlMaster.getUser();
                assertNotNull("Valida que no sea nulo",result);
            }
        } 

    }

    /**
     *Este test valida que haya repartidores disponible en la cola de espera y poderle asignarle una nueva ruta de espera.
     */
    @Test
    public void testObtenerRepartidores() throws Exception {
        System.out.println("Obtener Repartidores");
        List<Repartidor> result = instance.obtenerRepartidores();
        assertNotNull("Verifica que haya repartidores disponibles ", result);
    }
    
    
}
