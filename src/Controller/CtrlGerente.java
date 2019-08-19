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
import model.singleton.ConexionBD;

/**
 *
 * @author josie
 */
public class CtrlGerente {
    
    private final Gerente gerente;
    
    public CtrlGerente(Gerente jefe){
        this.gerente = jefe;
    }
    public void AssignarAdministrador(String cedula,boolean asignar) throws SQLException{
        ConexionBD bd = ConexionBD.getInstance();
        Connection conn = bd.conectarMySQL();
        if(asignar){
        String query="update usuario set usuario.isAdmin='"+asignar+"' WHERE (usuario.cedula=\""+cedula+"\") ";
        bd.hacerQuery(query);
        }
        String query="update usuario set usuario.isAdmin='0' WHERE (usuario.cedula=\""+cedula+"\") ";
        bd.hacerQuery(query);
    }
    
    public  ResultSet UsuarioByLocalidad() throws SQLException{
        ConexionBD bd = ConexionBD.getInstance();
        Connection conn = bd.conectarMySQL();
        ResultSet resul=CtrlMaster.buscarTipoUsuario();
        String query = "SELECT Persona.cedula,Persona.nombre,Persona.apellido "
                + "FROM Usuario JOIN Persona On Persona.cedula=Usuario.cedula  Where matriz_id= \""+resul.getString("matriz_id")+"\"";
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
    
}
