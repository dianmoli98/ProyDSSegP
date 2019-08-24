/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bodega.JefeBodega;
import model.bodega.Repartidor;
import model.bodega.Ruta;
import model.local.Cliente;
import model.local.Vendedor;
import model.pedido.Pedido;
import model.pedido.PedidoCliente;
import model.singleton.ConexionBD;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author Francisco
 */
public class CtrlJefeBodegaIT {
    private ConexionBD bd;
     private Connection conn;
   private  CtrlJefeBodega instance;
   
    
    public CtrlJefeBodegaIT() throws SQLException {
        bd=ConexionBD.getInstance();
        CtrlMaster.buscarUsuario("pbmoral", "pbmoral");
        instance=new CtrlJefeBodega((JefeBodega)CtrlMaster.getUser());
        try {
           conn=bd.conectarMySQLTest();
       } catch (SQLException ex) {
           Logger.getLogger(CtrlGerenteIT.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    /**
     * Este metodo es util para obtener el maximo de rutas a cargo del jefe de bodega en cuestion a la cantidad de pedido
     * dado que para poder registrar una nueva ruta necesitamos conocer la cantidad de pedido que ha sido seleccionado 
     * para ese destino, para este caso la respuesta deberia ser 4, como no se trabaja con un Mock 
     */
    @Test
    public void testObtenerLastRuta() throws Exception {
        System.out.println("Obtener el maximo numero de rutas a cargo");
        int expResult=2;
        int result = instance.obtenerLastRuta();
        assertSame("Maximo de ruta debe ser 2: ",expResult,result);
    }
    /**
    * Este metodo determina si la asignacion de rutas dentro de la base de datos fue realizada con exito, es por esto que cuando queremos crear una ruta 
    * y le asignamos a un repartidor este se muestra en la sala de espera, se valida que este metodo retorna true en el sentido positivo.
     */
    @Test
    public void testAsignarRuta() throws Exception {
        System.out.println("Asignacion de rutas");
        Ruta r=new Ruta(instance.obtenerLastRuta(),new JefeBodega(true,"Julio","Raios","0974827488"),new Repartidor("Andres","Murillo","0950637588",3),"F",3);
        Pedido p= new PedidoCliente(4,new Cliente("Juvencio","Amarl","8572758433","Floresta 2","9846756733"),new Vendedor(true,"Juvencio","Muric","763482432"));
        int result = instance.obtenerLastRuta(); 
        assertTrue(instance.asignarRuta(r, p));
    }

    
}
