/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Bodega.Jefe_Bodega;
import model.Bodega.Repartidor;
import model.Bodega.Ruta;
import model.Pedido.Pedido;
import model.singleton.ConexionBD;

/**
 *
 * @author josie
 */
public class CtrlJefeBodega {
    
    private final Jefe_Bodega jefe;
    
    public CtrlJefeBodega(Jefe_Bodega jefe){
        this.jefe = jefe;
    }

    public ResultSet obtenerRSRutas(Connection conn) throws SQLException{
            String query = 
            "Select r.id_ruta, count(p.id_pedido) as \"cantidad\" , r.Realizado, r.id_repartidor " +
            "from ruta r join repartidor re On r.id_repartidor = re.cedula " +
            "join pedido p On p.id_ruta = r.id_ruta " +
            "where r.id_jefeBodega = \""+jefe.getId()+"\" " +
            "group by r.id_ruta;";
            ResultSet rs = ConexionBD.getInstance().seleccionarDatos(query, conn);
            return rs;
    }
    
    public Ruta obtenerRuta(ResultSet rs) throws SQLException{
        int id_ruta = rs.getInt("id_ruta");
        int cantidad = rs.getInt("cantidad");
        String realizado = rs.getString("Realizado");
        String id_repartidor = rs.getString("id_repartidor");
        if(realizado.equals("F")){
            realizado = "En proceso";
        }else{
            realizado = "Finalizado";
        }
        Repartidor r = obtenerRepartidor(id_repartidor);
        return new Ruta(id_ruta, jefe, r, realizado, cantidad);
    }
    
    private Repartidor obtenerRepartidor(String cedula) throws SQLException{
        ConexionBD bd = ConexionBD.getInstance();
        Connection conn = bd.conectarMySQL();
        String query = 
            "Select r.cedula, p.nombre, p.apellido\n" +
            "From repartidor r \n" +
            "Join Persona p On r.cedula = p.cedula\n" +
            "Where r.cedula = \"" + cedula + "\" ;";
        ResultSet rs = ConexionBD.getInstance().seleccionarDatos(query, conn);
        Repartidor r = null;
        if(rs.next()){
            r = new Repartidor(rs.getString("nombre"), rs.getString("apellido"), rs.getString("cedula"), 0);
        }
        bd.cerrarConexion(conn);
        return r;
    }
    
    private ResultSet obtenerRSPedidos(Connection conn) throws SQLException{
        String query = 
            "use tecnoimport;\n" +
            "SELECT * \n" +
            "FROM pedido p  \n" +
            "Where p.id_ruta is NULL and id_jefeBodega = \""+jefe.getId()+"\";";
        ResultSet rs = ConexionBD.getInstance().seleccionarDatos(query, conn);
        return rs; 
    }
    
    private boolean guardarRuta(Ruta ruta){
        return false;
    }
}
