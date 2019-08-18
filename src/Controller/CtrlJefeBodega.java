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
import model.singleton.ConexionBD;

/**
 *
 * @author josie
 */
public class CtrlJefeBodega {
    
    private Jefe_Bodega jefe;
    
    public CtrlJefeBodega(Jefe_Bodega jefe){
        this.jefe = jefe;
    }

    public ResultSet obtenerRSPedidos(Connection conn) throws SQLException{
            String query = 
            "Select r.id_ruta, pe.nombre, pe.apellido, count(p.id_pedido) as \"cantidad\" , r.Realizado, r.id_repartidor " +
            "from ruta r join repartidor re On r.id_repartidor = re.cedula " +
            "join persona pe On pe.cedula = re.cedula " +
            "join pedido p On p.id_ruta = r.id_ruta " +
            "where r.id_jefeBodega = \""+jefe.getId()+"\" " +
            "group by r.id_ruta;";
            ResultSet rs = ConexionBD.getInstance().seleccionarDatos(query, conn);
            return rs;
    }
    
    public Ruta obtenerRuta(ResultSet rs) throws SQLException{
        int id_ruta = rs.getInt("id_ruta");
        String nombre = rs.getString("nombre");
        String apellido = rs.getString("apellido");
        int cantidad = rs.getInt("cantidad");
        String realizado = rs.getString("Realizado");
        String id_repartidor = rs.getString("id_repartidor");
        if(realizado.equals("F")){
            realizado = "En proceso";
        }else{
            realizado = "Finalizado";
        }
        Repartidor r = new Repartidor(nombre, apellido, id_repartidor, 0);
        return new Ruta(id_ruta, jefe, r, realizado, cantidad);
    }

}
