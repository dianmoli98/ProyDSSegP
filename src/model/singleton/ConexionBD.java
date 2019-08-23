package model.singleton;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Jocellyn Luna
 */
public class ConexionBD {

    // Librería de MySQL
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    // Datos de la BD
    private static final String DB = "TecnoImport";
    private static final String HOST = "127.0.0.1";
    private static final String PUERTO = "3306";

    private static final String URL = "jdbc:mysql://" + HOST + ":" + PUERTO + "/" + DB
            + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    
    private String user;
    private String pass;
    private static ConexionBD conexion = new ConexionBD();
    
    private ConexionBD(){}
    
    public static ConexionBD getInstance(){
        return conexion;
    }

    public void setLogIn(String user,String pass) {
        this.user = user;
        this.pass = pass;
    }
    
    public static void setConexion(ConexionBD conexion) {
        ConexionBD.conexion = conexion;
    }
    
    
    public Connection conectarMySQL() throws SQLException {
        Connection conn;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, user,pass);
            return conn;
        } catch (ClassNotFoundException ex) {
            throw new SQLException("Error de Base de Datos.\nNo conectada");
        }
    }
    
    public static void lanzarException() throws SQLException{
        throw new SQLException("La base de datos se desconectó inesperadamente.");
    }

    public void cerrarConexion(Connection conn) throws SQLException {
        try {
            conn.close();
        } catch (SQLException ex) {
            throw new SQLException("Fallo al cerrar conexión a base de datos");
        }
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }
    
    public void hacerQuery(String query) throws SQLException{
        ConexionBD bd = ConexionBD.getInstance();
        try(Connection conn = bd.conectarMySQL()){
            try(CallableStatement cl = conn.prepareCall(query)) {
                cl.execute();
            } 
            bd.cerrarConexion(conn); 
        } catch (SQLException ex) {
            throw new SQLException("No se pudo realizar la acción");
        }
    }
}
