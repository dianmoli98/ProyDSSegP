/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.local.Persona;
import model.local.Usuario;
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
    public void asignarAdministrador(String cedula,boolean asignar) throws SQLException{
        ConexionBD bd = ConexionBD.getInstance();
        Connection conn = bd.conectarMySQL();
        Statement st = conn.createStatement();
        if(asignar){
        String query="update Usuario set Usuario.isAdmin="+asignar+" WHERE (Usuario.cedula=\""+cedula+"\") ";
        st.execute(query);
        }
    }
    
    public  ResultSet usuarioByLocalidad() throws SQLException{
        ConexionBD bd = ConexionBD.getInstance();
        Connection conn = bd.conectarMySQL();
        ResultSet resul=CtrlMaster.buscarTipoUsuario();
        String query = "SELECT Persona.cedula,Persona.nombre,Persona.apellido "
                + "FROM Usuario JOIN Persona On Persona.cedula=Usuario.cedula "
                + " Where Usuario.matriz_id= \""+resul.getString("matriz_id")+"\"";
        ResultSet rs = ConexionBD.getInstance().seleccionarDatos(query, conn);
        CtrlMaster.validarResult(rs);
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

    public Usuario getUsuario() {
        return usuario;
    }
}
