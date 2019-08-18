/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Bodega.Jefe_Bodega;
import model.Local.Gerente;
import model.Local.Usuario;
import model.Local.Vendedor;
import model.singleton.ConexionBD;

/**
 *
 * @author josie
 */
public class CtrlMaster {
    private static Usuario userLogin = null; 
    
    private static Connection validarLogin(String usuario, String password) throws SQLException{
        ConexionBD bd = ConexionBD.getInstance();
        bd.setLogIn(usuario, password);
        Connection conn;
        try{
        conn = bd.conectarMySQL();
        }catch(SQLException ex){
            throw new SQLException("Usuario o contraseña equivocada.\nIntente nuevamente.");
        }
        return conn;
    }
    
    public static void buscarUsuario(String username, String password) throws SQLException{
        if(validarLogin(username,password)==null){
           throw new SQLException("El usuario o contraseña es incorrecto.");
        }
        ResultSet rs=buscarTipoUsuario();
        int tipo = rs.getInt("TipoUsuario");
        switch(tipo){
            case 1:
                System.out.println("vendedor");
                userLogin = new Vendedor(rs.getString("usuario"),rs.getString("clave"),
                rs.getBoolean("isAdmin"),rs.getString("nombre"),rs.getString("apellido"),rs.getString("cedula"));
                break;
            case 2:
                System.out.println("Jefe");
                userLogin = new Jefe_Bodega(rs.getString("usuario"),rs.getString("clave"),
                rs.getBoolean("isAdmin"),rs.getString("nombre"),rs.getString("apellido"),rs.getString("cedula"));
                break;
            case 3:
                System.out.println("gerente");
                userLogin = new Gerente(rs.getString("usuario"),rs.getString("clave"),
                rs.getBoolean("isAdmin"),rs.getString("nombre"),rs.getString("apellido"),rs.getString("cedula"));
                break;
        }
    }
    
    private static ResultSet buscarTipoUsuario() throws SQLException {
        ConexionBD bd = ConexionBD.getInstance();
        Connection conn = bd.conectarMySQL();
        String query = "SELECT * FROM Usuario JOIN Persona ON Usuario.cedula= Persona.cedula Where usuario =\""+bd.getUser()+"\";";

        ResultSet rs = bd.seleccionarDatos(query, conn);

        if (rs == null || rs.isClosed() || !rs.next()) {
            throw new SQLException("Usuario no encontrado.\nInténtelo más tarde. ");
        }
        return rs;
    }
    
    public static Usuario getUser(){
        return userLogin;
    }

    
    public static ResultSet llenarTablaProductos() throws SQLException {
        ConexionBD bd = ConexionBD.getInstance();
        Connection conn = bd.conectarMySQL();
        String query = "select * from producto";
        ResultSet rs = bd.seleccionarDatos(query, conn);
        return rs;}
    
    


    public static String cambiarPantalla(){
        return null;

    }
}
