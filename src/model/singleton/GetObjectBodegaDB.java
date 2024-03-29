/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.singleton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.bodega.JefeBodega;
import model.bodega.Repartidor;
import model.bodega.Ruta;
import model.inventario.Matriz;
import model.pedido.Pedido;
import model.pedido.PedidoCliente;
import model.pedido.PedidoMatriz;

/**
 *
 * @author josie
 */
public class GetObjectBodegaDB {
    private GetObjectPersonaDB obP = GetObjectPersonaDB.getInstance();
    private JefeBodega jefe;
    
    protected GetObjectBodegaDB(JefeBodega jefe){
        this.jefe = jefe;
    }
    
    public ResultSet obtenerRSRutas(Statement st) throws SQLException{
        String query = 
        "Select r.id_ruta, count(p.id_pedido) as \"cantidad\" , r.Realizado, r.id_repartidor " +
        "from Ruta r join Repartidor re On r.id_repartidor = re.cedula " +
        "join Pedido p On p.id_ruta = r.id_ruta " +
        "where r.id_jefeBodega = \""+jefe.getId()+"\"  and r.Realizado = \"F\"" +
        "group by r.id_ruta;";
        return st.executeQuery(query);
    }
    
    public Ruta obtenerRuta(ResultSet rs) throws SQLException{
        int idruta = rs.getInt("id_ruta");
        int cantidad = rs.getInt("cantidad");
        String realizado = rs.getString("Realizado");
        String idRepartidor = rs.getString("id_repartidor");
        if(realizado.equals("F")){
            realizado = "En proceso";
        }else{
            realizado = "Finalizado";
        }
        Repartidor r = obP.obtenerRepartidor(idRepartidor);
        return new Ruta(idruta, jefe, r, realizado, cantidad);
    }
    
    public ResultSet obtenerRSPedidos(Statement st) throws SQLException{
        String query = 
            "SELECT p.id_pedido, p.id_cliente, p.id_matriz, p.id_vendedor  \n" +
            "FROM Pedido p  \n" +
            "Where p.id_ruta is NULL and p.id_jefeBodega = \""+jefe.getId()+"\";";
        return st.executeQuery(query); 
    }
    
    public ResultSet obtenerRSPedidos(Statement st, int idRuta) throws SQLException{
        String query = 
            "SELECT p.id_pedido, p.id_cliente, p.id_matriz, p.id_vendedor  \n" +
            "FROM Pedido p  \n" +
            "Where p.id_ruta= "+idRuta+" and p.id_jefeBodega = \""+jefe.getId()+"\";";
        return st.executeQuery(query);
    }
    
    public Pedido obtenerPedido(ResultSet rs) throws SQLException{
        int idpedido = rs.getInt("id_pedido");
        String idmatriz = rs.getString("id_matriz");
        String idcliente = rs.getString("id_cliente");
        String idvendedor = rs.getString("id_vendedor");
        Pedido pedido;
        
        if(idmatriz != null){
            pedido = new PedidoMatriz(obtenerMatriz(idmatriz), obP.obtenerVendedor(idvendedor), idpedido);
        }else{
            pedido = new PedidoCliente(idpedido,obP.obtenerCliente(idcliente), obP.obtenerVendedor(idvendedor));
        }
        
        return pedido;
    } 
    
    public Matriz obtenerMatriz(String id) throws SQLException{
        ConexionBD bd = ConexionBD.getInstance();
        Connection conn = bd.conectarMySQL();
        String query = 
            "SELECT * " +
            "FROM Matriz m\n" +
            "WHERE m.id_matriz = \"" + id + "\";";
        try (Statement st = conn.createStatement()) {
            try(ResultSet rs = st.executeQuery(query)){
                Matriz m=null;
                if(rs.next()){
                    m = new Matriz(rs.getString("id_matriz"),rs.getString("direccion"),rs.getString("tipoLocalidad"));
                }
                bd.cerrarConexion(conn);
                return m;
            }
        } catch (SQLException ex) {
            ConexionBD.lanzarException();
        }
        return null;
    }
}
