/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import model.Local.Usuario;
import model.singleton.ConexionBD;

/**
 *
 * @author josie
 */
public class CtrlGerente {
    
    public void AssignarAdministrador(String cedula) throws SQLException{
        ConexionBD bd = ConexionBD.getInstance();
        Connection conn = bd.conectarMySQL();
        String query="update usuario set usuario.isAdmin='0' WHERE (usuario.cedula=\""+cedula+"\") ;";
        bd.seleccionarDatos(query, conn);
    }
    
}
