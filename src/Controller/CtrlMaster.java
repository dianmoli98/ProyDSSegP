/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Emergentes.Emergentes;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Local.Usuario;
import model.singleton.ConexionBD;

/**
 *
 * @author josie
 */
public class CtrlMaster {
    
    public static Connection validarLogin(String usuario, String password){
        ConexionBD bd = ConexionBD.getInstance();
        bd.setLogIn(usuario, password);
        Connection conn;
        conn = bd.conectarMySQL();
        if(conn==null) Emergentes.mostrarDialogo("La base de datos no se encuentra disponible", "Error de conexión", "Error");
        
        return conn;
    }
    
    public static Usuario buscarUsuario(String username, String password) throws SQLException{
        Usuario usuario;
        if(validarLogin(username,password)==null){
           throw new SQLException("El usuario o contraseña es incorrecto.");
        }
        ResultSet rs=buscarTipoUsuario();
        usuario=new Usuario(rs.getString("usuario"),rs.getString("clave"),
                rs.getBoolean("isAdmin"),rs.getString("nombre"),rs.getString("apellido"),rs.getString("cedula"));
        
        return usuario;
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
}
