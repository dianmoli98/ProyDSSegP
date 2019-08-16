package model.singleton;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author Jocellyn Luna
 */
public class ConexionBD {

    // Librería de MySQL
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    // Datos de la BD
    private final String DB = "TecnoImport";
    private final String HOST = "127.0.0.1";
    private final String PUERTO = "3306";

    private final String URL = "jdbc:mysql://" + HOST + ":" + PUERTO + "/" + DB
            + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    
    private String user;
    private String pass;
    private static ConexionBD conexion = new ConexionBD();
    
    private ConexionBD(){
    }
    
    public static ConexionBD getInstance(){
        return conexion;
    }
    
    public Connection conectarMySQL(String user, String password) throws SQLException {
        Connection conn = null;
        this.user = user;
        this.pass = password;

        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Usuario o Contraseña incorrecto.");
        }

        return conn;
    }

    private ResultSet seleccionarDatos(String query, Connection conn) throws SQLException {
        if (conn == null) {
            throw new SQLException("Conexión con la base de datos fallida.\n Compruebe autentificación o"
                    + "Driver de conexión.");
        }
        //create the java statement
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
        } catch (SQLException ex) {
            throw new SQLException("La base de datos se desconectó inesperadamente.");
        }

        try {
            // execute the query, and get a java resultset
            rs = st.executeQuery(query);
        } catch (SQLException ex) {
            throw new SQLException("la Consulta no fue exitosa.\nInténtelo más tarde. " + ex.getMessage());
        }
        return rs;
    }

    private void cerrarConexion(Connection conn) throws SQLException {
        try {
            conn.close();
        } catch (SQLException ex) {
            throw new SQLException("Fallo al cerrar conexión a base de datos");
        }
    }

    public void hacerQuery(String query) throws SQLException {
        if(pass == null || user == null){
            throw new SQLException("Conexión fallida.");
        }
        Connection conn = conectarMySQL(user, pass);
        if (conn == null) {
            throw new SQLException("Conexión fallida.");
        }

        CallableStatement cl = conn.prepareCall(query);
        cl.execute();
    }
}
