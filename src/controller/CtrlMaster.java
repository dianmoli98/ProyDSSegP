package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javafx.scene.control.Label;
import java.sql.Statement;
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
        
        ConexionBD bd = ConexionBD.getInstance();
        Connection conn = bd.conectarMySQL();

        try (Statement st = conn.createStatement()) {
            String query = "SELECT * FROM Usuario JOIN Persona ON "
                    + "Usuario.cedula= Persona.cedula Where usuario =\""+bd.getUser()+"\";";
        
            try(ResultSet rs = st.executeQuery(query)){
                if (rs == null || rs.isClosed() || !rs.next()) {
                    throw new SQLException("Usuario no encontrado.\nInténtelo más tarde. ");
                }
                setUser(rs);
            }          
        } catch (SQLException ex) {
            throw new SQLException("La base de datos se desconectó inesperadamente.");
        }
    }
    
    private static void setUser(ResultSet rs) throws SQLException{
        Usuario u = new Usuario(rs.getBoolean("isAdmin"),rs.getString("nombre"),
                 rs.getString("apellido"),rs.getString("cedula"));
        
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
    
    
    public static Usuario getUser(){
        return user;
    }
    
    public static String cambiarPantalla(){
        return null;
    }
    
    public static void validarResult(ResultSet rs) throws SQLException{
        if (rs == null || rs.isClosed() || !rs.next()) {
            throw new SQLException("Usuario no encontrado.\nInténtelo más tarde. ");
        }
    }
    public static void callCalender(Label fecha,Label empleado){
        Date date=Calendar.getInstance().getTime();
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        fecha.setText(sdf.format(date));
        user = CtrlMaster.getUser();
        empleado.setText(user.getNombre() + " " + user.getApellido());
    }
}

