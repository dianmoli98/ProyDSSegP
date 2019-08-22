/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bodega;

import java.util.LinkedList;
import java.util.List;
import model.pedido.Pedido;

/**
 *
 * @author josie
 */
public class Ruta {
    private int idRuta;
    private JefeBodega jefe;
    private Repartidor repartidor;
    private String status;
    private int cantidad;
    private List<Pedido> pedidos;
    private List<String> direcciones;

    public Ruta(int idruta, JefeBodega jefe, Repartidor repartidor, String status, int cantidad) {
        this.idRuta = idruta;
        this.jefe = jefe;
        this.repartidor = repartidor;
        this.status = status;
        this.cantidad = cantidad;
        pedidos = new LinkedList<>();
        direcciones = new LinkedList<>();
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
        direcciones.clear();
        pedidos.forEach(p -> direcciones.add(p.getDireccion()));
        cantidad = pedidos.size();
    }  
    
    public void agregarPedido(Pedido pedido){
        pedidos.add(pedido);
        direcciones.add(pedido.getDireccion());
        cantidad++;
    }

    public int getIdRuta() {
        return idRuta;
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

    public JefeBodega getJefe() {
        return jefe;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public List<String> getDirecciones() {
        return direcciones;
    }

    public void setIdRuta(int idruta) {
        this.idRuta = idruta;
    }
}
