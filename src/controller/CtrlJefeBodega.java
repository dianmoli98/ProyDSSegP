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
import java.util.LinkedList;
import java.util.Queue;
import javafx.collections.ObservableList;
import model.bodega.JefeBodega;
import model.bodega.Repartidor;
import model.bodega.Ruta;
import model.local.Usuario;
import model.pedido.Pedido;
import model.singleton.ConexionBD;
import model.singleton.GetObjectBodegaDB;
import model.singleton.GetObjectPersonaDB;

/*
 * @author josie
 */
public class CtrlJefeBodega extends GetObjectBodegaDB {
    
    private JefeBodega jefe;
    private Queue<Repartidor> repartidores;
    private GetObjectPersonaDB obP = GetObjectPersonaDB.getInstance();
    
    public CtrlJefeBodega(JefeBodega jefe){
        super(jefe);
        try {
            actualizarRepartidores();
        } catch (SQLException ex) {
           emergentes.Emergentes.mostrarDialogo("No fue posible conectarse al servidor.", "Error de conexión", "Error");
        }
        this.jefe = jefe;
    }

    public boolean agregarRepartidor(Repartidor r){
        if(repartidores.add(r)) return true;
        return false;
    }
    
    public  Ruta crearRuta(ObservableList<Pedido> pedidos) throws SQLException{
        Repartidor repart = obtenerRepartidor();
        if(repart == null){
            throw new SQLException("No hay repartidores Disponible");
        }
        Ruta r = new Ruta(0, jefe, repart, "F", 0);
        LinkedList<Pedido> pedidosL = new LinkedList<>(pedidos);
        r.setPedidos(pedidosL);
        return r;
    }
    
    public void guardarRuta(ObservableList<Pedido> pedidos) throws SQLException{
        Ruta r = crearRuta(pedidos);
        insertarRutaBD(r);
        r.setIdRuta(obtenerLastRuta());
        for(Pedido p: pedidos){
            asignarRuta(r, p);
        }
    }
    
    private void insertarRutaBD(Ruta r) throws SQLException{
        String query = 
            "INSERT INTO Ruta(id_ruta,Realizado,id_repartidor,id_jefeBodega) VALUES\n" +
            "(default,\"F\",\""+r.getRepartidor().getId()+"\",\""+jefe.getId()+"\")";
        
        ConexionBD.getInstance().hacerQuery(query); 
    }
    
    public boolean asignarRuta(Ruta r, Pedido p) throws SQLException{
        String query = 
            "UPDATE  Pedido \n" +
            "SET id_ruta = " + r.getIdRuta()+"\n" +
            "Where Pedido.id_pedido = " + p.getIdpedido() + ";";
        return ConexionBD.getInstance().hacerQuery(query); 
    }
    
    public int obtenerLastRuta() throws SQLException{
        ConexionBD bd = ConexionBD.getInstance();
        Connection conn = bd.conectarMySQL();
        String query = 
            "SELECT max(r.id_ruta) as \"id\"\n" +
            "FROM Ruta r\n" +
            "WHERE r.id_jefeBodega = \""+jefe.getId()+"\";";
        int id = 0;
        try (Statement st = conn.createStatement()) {
            try(ResultSet rs = st.executeQuery(query)){
                if(rs.next()){
                    id =  rs.getInt("id");
                }else{
                    throw new SQLException("Hubo un problema al guardar la Ruta");
                }
            }
        } catch (SQLException ex) {
            throw new SQLException("La base de datos se desconectó inesperadamente.");
        }
        bd.cerrarConexion(conn);
        return id; 
    }
    
    private Repartidor obtenerRepartidor(){
       return (repartidores.isEmpty())? null: repartidores.poll();
    }
    
    public final void actualizarRepartidores() throws SQLException{
        repartidores = (LinkedList<Repartidor>) obP.obtenerRepartidores();
    }

    public JefeBodega getJefe() {
        return jefe;
    }

    public void setJefe(JefeBodega jefe) {
        this.jefe = jefe;
    }
    
}
