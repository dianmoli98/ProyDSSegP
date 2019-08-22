/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Local.Gerente;
import model.Local.Persona;
import model.Local.Usuario;
import model.singleton.ConexionBD;

/**
 *
 * @author josie
 */
public class CtrlGerente {
    
    private final Usuario usuario;
    
    public CtrlGerente(Usuario jefe){
        this.usuario = jefe;
    }
    public void AssignarAdministrador(String cedula,boolean asignar) throws SQLException{
        ConexionBD bd = ConexionBD.getInstance();
        Connection conn = bd.conectarMySQL();
        Statement st = conn.createStatement();
        if(asignar){
        String query="update Usuario set Usuario.isAdmin='1' WHERE (Usuario.cedula=\""+cedula+"\") ";
        st.execute(query);
        }else{
        String query="update Usuario set Usuario.isAdmin='0'WHERE (Usuario.cedula=\""+cedula+"\") ";
        st.execute(query);
        }
       
    }
    
    public  ResultSet UsuarioByLocalidad() throws SQLException{
        ConexionBD bd = ConexionBD.getInstance();
        Connection conn = bd.conectarMySQL();
        ResultSet resul=CtrlMaster.buscarTipoUsuario();
        String query = "SELECT Persona.cedula,Persona.nombre,Persona.apellido "
                + "FROM Usuario JOIN Persona On Persona.cedula=Usuario.cedula  Where Usuario.matriz_id= \""+resul.getString("matriz_id")+"\"";
        ResultSet rs = ConexionBD.getInstance().seleccionarDatos(query, conn);
        if (rs == null || rs.isClosed() || !rs.next()) {
            throw new SQLException("Usuario no encontrado.\nInténtelo más tarde. ");
        }
        return rs;
    }
    
     public Persona obtenerPersona(ResultSet rs) throws SQLException{
         Persona persona=null;
        if (!rs.getString("cedula").equalsIgnoreCase("0")) {
        String nombre = rs.getString("nombre");
        String apellido = rs.getString("apellido");
        String cedula = rs.getString("cedula");
        persona=new Persona(nombre,apellido,cedula);
        }
        return persona;
    }
    
     public void peticionAbastecimiento(){
         
     }
}
