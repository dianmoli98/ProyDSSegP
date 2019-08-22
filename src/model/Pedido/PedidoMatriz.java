/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pedido;


import java.util.LinkedList;
import model.inventario.Matriz;
import model.local.Vendedor;

/**
 *
 * @author josie
 */
public class PedidoMatriz extends Pedido {
    private Matriz matriz;
    private Vendedor vendedor;

    public PedidoMatriz(Matriz matriz, Vendedor vendedor, int id_pedido) {
        super.id_pedido = id_pedido;
        super.productos = new LinkedList<>();
        this.matriz = matriz;
        this.vendedor = vendedor;
    }
    
    @Override
    public int getId_pedido() {
        return id_pedido;
    }

    @Override
    public String getDireccion() {
        return matriz.getDireccion();
    }
    
}
