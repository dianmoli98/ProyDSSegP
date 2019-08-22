/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.singleton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    public ResultSet obtenerRSRutas(Connection conn) throws SQLException{
            String query = 
            "Select r.id_ruta, count(p.id_pedido) as \"cantidad\" , r.Realizado, r.id_repartidor " +
            "from Ruta r join Repartidor re On r.id_repartidor = re.cedula " +
            "join Pedido p On p.id_ruta = r.id_ruta " +
            "where r.id_jefeBodega = \""+jefe.getId()+"\"  and r.Realizado = \"F\"" +
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
        Repartidor r = obP.obtenerRepartidor(id_repartidor);
        return new Ruta(id_ruta, jefe, r, realizado, cantidad);
    }
    
    public ResultSet obtenerRSPedidos(Connection conn) throws SQLException{
        String query = 
            "SELECT p.id_pedido, p.id_cliente, p.id_matriz, p.id_vendedor  \n" +
            "FROM Pedido p  \n" +
            "Where p.id_ruta is NULL and p.id_jefeBodega = \""+jefe.getId()+"\";";
        return ConexionBD.getInstance().seleccionarDatos(query, conn); 
    }
    
    public ResultSet obtenerRSPedidos(Connection conn, int id_ruta) throws SQLException{
        String query = 
            "SELECT p.id_pedido, p.id_cliente, p.id_matriz, p.id_vendedor  \n" +
            "FROM Pedido p  \n" +
            "Where p.id_ruta= "+id_ruta+" and p.id_jefeBodega = \""+jefe.getId()+"\";";
        ResultSet rs = ConexionBD.getInstance().seleccionarDatos(query, conn);
        return rs; 
    }
    
    public Pedido obtenerPedido(ResultSet rs) throws SQLException{
        int id_pedido = rs.getInt("id_pedido");
        String id_matriz = rs.getString("id_matriz");
        String id_cliente = rs.getString("id_cliente");
        String id_vendedor = rs.getString("id_vendedor");
        Pedido pedido;
        
        if(id_matriz != null){
            pedido = new PedidoMatriz(obtenerMatriz(id_matriz), obP.obtenerVendedor(id_vendedor), id_pedido);
        }else{
            pedido = new PedidoCliente(id_pedido,obP.obtenerCliente(id_cliente), obP.obtenerVendedor(id_vendedor));
        }
        
        return pedido;
    } 
    
    private Matriz obtenerMatriz(String id) throws SQLException{
        ConexionBD bd = ConexionBD.getInstance();
        Connection conn = bd.conectarMySQL();
        String query = 
            "SELECT * \n" +
            "FROM Matriz m\n" +
            "WHERE m.id_matriz = \"" + id + "\";";
        ResultSet rs = ConexionBD.getInstance().seleccionarDatos(query, conn);
        Matriz m = null;
        if(rs.next()){
            m = new Matriz(rs.getString("id_matriz"),rs.getString("direccion"),rs.getString("tipoLocalidad"));
        }
        bd.cerrarConexion(conn);
        return m;
    }
}
