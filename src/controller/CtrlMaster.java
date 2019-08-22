package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.bodega.JefeBodega;
import model.local.Gerente;
import model.local.Usuario;
import model.local.Vendedor;
import model.singleton.ConexionBD;

/**
 *
 * @author josie
 */
public class CtrlMaster {
    
    private static Usuario user=null;
    
    private CtrlMaster(){} 
    
    private static Connection validarLogin(String usuario, String password) throws SQLException{
        ConexionBD bd = ConexionBD.getInstance();
        bd.setLogIn(usuario, password);
        Connection conn;
        try{
        conn = bd.conectarMySQL();
        }catch(SQLException ex){
            throw new SQLException("Sus credenciales son incorrectas.\nIntente nuevamente." + ex.getMessage());
        }
        return conn;
    }
    
    public static void buscarUsuario(String username,String password) throws SQLException{
        
        if(validarLogin(username,password)==null){
           throw new SQLException("El usuario o contraseña es incorrecto.");
        }
        ResultSet rs=buscarTipoUsuario();
        Usuario u = new Usuario(rs.getBoolean("isAdmin"),rs.getString("nombre"),rs.getString("apellido"),rs.getString("cedula"));
        int tipo = rs.getInt("TipoUsuario");
        
        switch(tipo){
            case 1:
                user = new Vendedor(u);
                break;
            case 2:
                user = new JefeBodega(u);
                break;
            case 3:
                user = new Gerente(u);
                break;
            default:
                user = u;
        }
    }
    
    public static ResultSet buscarTipoUsuario() throws SQLException {
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
        return user;
    }

    
    public static ResultSet llenarTablaProductos() throws SQLException {
        ConexionBD bd = ConexionBD.getInstance();
        Connection conn = bd.conectarMySQL();
        String query = "select * from producto";
        return bd.seleccionarDatos(query, conn);
    }
    
    public static String cambiarPantalla(){
        return null;
    }
}
