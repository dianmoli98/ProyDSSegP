/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.singleton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import model.bodega.Repartidor;
import model.local.Cliente;
import model.local.Persona;
import model.local.Usuario;
import model.local.Vendedor;

/**
 *
 * @author josie
 */
public class GetObjectPersonaDB {
    private static GetObjectPersonaDB ob = new GetObjectPersonaDB();
    private GetObjectPersonaDB(){}
    
    public static GetObjectPersonaDB getInstance(){
        return ob;
    }
    
    public Cliente obtenerCliente(String cedula) throws SQLException{
        ConexionBD bd = ConexionBD.getInstance();
        Connection conn = bd.conectarMySQL();
        Cliente c = null;
        String query = 
            "SELECT *\n" +
            "FROM Cliente c\n" +
            "JOIN Persona p On p.cedula = c.cedula\n" +
            "WHERE p.cedula = \"" + cedula + "\";";
        
        try (Statement st = conn.createStatement()) {
            try(ResultSet rs = st.executeQuery(query)){
                if(rs.next()){
                    c = new Cliente(obtenerPersona(rs), rs.getString("direccion"), rs.getString("telefono"));
                }
            }
        } catch (SQLException ex) {
            ConexionBD.lanzarException();
        }
        bd.cerrarConexion(conn);
        return c;
    }
    
    public Persona obtenerPersona(ResultSet rs) throws SQLException {
            Persona persona = new Persona(rs.getString("nombre"), rs.getString("apellido"), rs.getString("cedula"));
            return persona;
        
    }
    
    public Repartidor obtenerRepartidor(String cedula) throws SQLException{
        ConexionBD bd = ConexionBD.getInstance();
        Connection conn = bd.conectarMySQL();
        Repartidor r = null;
        String query = 
            "Select r.cedula, p.nombre, p.apellido\n" +
            "From Repartidor r \n" +
            "Join Persona p On r.cedula = p.cedula\n" +
            "Where r.cedula = \"" + cedula + "\" ;";
        try (Statement st = conn.createStatement()) {
            try(ResultSet rs = st.executeQuery(query)){
                if(rs.next()){
                     r = new Repartidor(obtenerPersona(rs),0);
                }
            }
        } catch (SQLException ex) {
            ConexionBD.lanzarException();
        }
        bd.cerrarConexion(conn);
        return r;
    }
    
    public Usuario obtenerUsuario(ResultSet rs) throws SQLException{
        return new Usuario(rs.getBoolean("isAdmin"), rs.getString("nombre"),
                    rs.getString("apellido"), rs.getString("cedula"));
    }
    
    public Vendedor obtenerVendedor(String cedula) throws SQLException{
        ConexionBD bd = ConexionBD.getInstance();
        Connection conn = bd.conectarMySQL();
        Vendedor v = null;
        String query = 
            "SELECT * \n" +
            "FROM Usuario u\n" +
            "JOIN Persona p ON p.cedula = u.cedula\n" +
            "WHERE p.cedula = \"" + cedula + "\";";
        try (Statement st = conn.createStatement()) {
            try(ResultSet rs = st.executeQuery(query)){
                if(rs.next()){
                    v = new Vendedor(obtenerUsuario(rs));
                }
            }
        } catch (SQLException ex) {
            ConexionBD.lanzarException();
        }
        bd.cerrarConexion(conn);
        return v;
    }
    
    public List<Repartidor> obtenerRepartidores() throws SQLException{
        LinkedList<Repartidor> repartidores = new LinkedList<>();
        ConexionBD bd = ConexionBD.getInstance();
        Connection conn = bd.conectarMySQL();
        String query = 
            "SELECT * \n" +
            "FROM Repartidor r\n" +
            "JOIN Persona p On r.cedula = p.cedula\n" +
            "WHERE r.cedula NOT IN\n" +
            "	(SELECT r1.id_repartidor\n" +
            "	 FROM  Ruta r1  WHERE  r1.Realizado = \"F\");";
        try (Statement st = conn.createStatement()) {
            try(ResultSet rs = st.executeQuery(query)){
                while(rs.next()){
                    repartidores.add(new Repartidor(obtenerPersona(rs), 0));
                }
            }
        } catch (SQLException ex) {
            ConexionBD.lanzarException();
        }

        bd.cerrarConexion(conn);
        return repartidores;
    }
}
