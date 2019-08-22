/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.singleton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;
import model.bodega.Repartidor;
import model.local.Cliente;
import model.local.Persona;
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
        String query = 
            "SELECT *\n" +
            "FROM Cliente c\n" +
            "JOIN Persona p On p.cedula = c.cedula\n" +
            "WHERE p.cedula = \"" + cedula + "\";";
        ResultSet rs = ConexionBD.getInstance().seleccionarDatos(query, conn);
        Cliente c = null;
        if(rs.next()){
            c = new Cliente(obtenerPersona(rs), rs.getString("direccion"), rs.getString("telefono"));
        }
        bd.cerrarConexion(conn);
        return c;
    }
    
    public Persona obtenerPersona(ResultSet rs) throws SQLException{
        return new Persona(rs.getString("nombre"),  rs.getString("apellido"), rs.getString("cedula"));
    }
    
    public Repartidor obtenerRepartidor(String cedula) throws SQLException{
        ConexionBD bd = ConexionBD.getInstance();
        Connection conn = bd.conectarMySQL();
        String query = 
            "Select r.cedula, p.nombre, p.apellido\n" +
            "From Repartidor r \n" +
            "Join Persona p On r.cedula = p.cedula\n" +
            "Where r.cedula = \"" + cedula + "\" ;";
        ResultSet rs = ConexionBD.getInstance().seleccionarDatos(query, conn);
        Repartidor r = null;
        if(rs.next()){
            r = new Repartidor(rs.getString("nombre"), rs.getString("apellido"), rs.getString("cedula"), 0);
        }
        bd.cerrarConexion(conn);
        return r;
    }
     
    public Vendedor obtenerVendedor(String cedula) throws SQLException{
        ConexionBD bd = ConexionBD.getInstance();
        Connection conn = bd.conectarMySQL();
        String query = 
            "SELECT * \n" +
            "FROM Usuario u\n" +
            "JOIN Persona p ON p.cedula = u.cedula\n" +
            "WHERE p.cedula = \"" + cedula + "\";";
        ResultSet rs = ConexionBD.getInstance().seleccionarDatos(query, conn);
        Vendedor v = null;
        if(rs.next()){
            v = new Vendedor(rs.getBoolean("isAdmin"), rs.getString("nombre"),
                    rs.getString("apellido"), rs.getString("cedula"));
        }
        bd.cerrarConexion(conn);
        return v;
    }
    
    public LinkedList<Repartidor> obtenerRepartidores() throws SQLException{
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
        ResultSet rs = ConexionBD.getInstance().seleccionarDatos(query, conn);
        while(rs.next()){
            repartidores.add(new Repartidor(rs.getString("nombre"), rs.getString("apellido"), rs.getString("cedula"), 0));
        }
        bd.cerrarConexion(conn);
        return repartidores;
    }
}
