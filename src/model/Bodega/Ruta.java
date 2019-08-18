/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Bodega;

import java.util.LinkedList;
import model.Pedido.Pedido;

/**
 *
 * @author josie
 */
public class Ruta {
    private int id_ruta;
    private Jefe_Bodega jefe;
    private Repartidor repartidor;
    private String status;
    private int cantidad;
    private LinkedList<Pedido> pedidos;
    private LinkedList<String> direcciones;

    public Ruta(int id_ruta, Jefe_Bodega jefe, Repartidor repartidor, String status, int cantidad) {
        this.id_ruta = id_ruta;
        this.jefe = jefe;
        this.repartidor = repartidor;
        this.status = status;
        this.cantidad = cantidad;
        pedidos = new LinkedList<>();
        direcciones = new LinkedList<>();
    }

    public void setPedidos(LinkedList<Pedido> pedidos) {
        this.pedidos = pedidos;
        direcciones.clear();
        pedidos.forEach((p) -> {
            direcciones.add(p.getDireccion());
        });
    }  

    public int getId_ruta() {
        return id_ruta;
    }

    public Repartidor getRepartidor() {
        return repartidor;
    }

    public String getStatus() {
        return status;
    }

    public int getCantidad() {
        return cantidad;
    }
    
    
}
