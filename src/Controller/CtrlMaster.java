/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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
    
    public boolean validarLogin(String usuario, String password){
        ConexionBD bd = ConexionBD.getInstance();
        bd.setLogIn(usuario, password);
        
        try {
            Connection conn = bd.conectarMySQL();
            bd.cerrarConexion(conn);
        } catch (SQLException ex) {
           return false;
        }
        return true;
    }
    
    public Usuario buscarUsuario(String username, String password) throws SQLException{
        if(!validarLogin(username, password)){
           throw new SQLException("El usuario o contraseña es incorrecto.");
        }
        
        
    }
    
    private ResultSet buscarTipoUsuario(String username, String password) throws SQLException {
        ConexionBD bd = ConexionBD.getInstance();
        Connection conn = bd.conectarMySQL();
        String query = "SELECT * FROM `usuario` u Where usuario = '" + username+"'";

        ResultSet rs = bd.seleccionarDatos(query, conn);

        if (rs == null || rs.isClosed() || !rs.next()) {
            throw new SQLException("Usuario no encontrado.\nInténtelo más tarde. ");
        }
        int tipo = rs.getInt("tipo");
        bd.cerrarConexion(conn);
        return rs;
    }
}
