/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Pedido;


import java.util.LinkedList;
import java.util.List;
import model.Inventario.Matriz;
import model.Local.Vendedor;

/**
 *
 * @author josie
 */
public class PedidoMatriz extends Pedido {
    private Matriz matriz;
    private Vendedor vendedor;

    public PedidoMatriz(Matriz matriz, Vendedor vendedor) {
        super.productos = new LinkedList<>();
        this.matriz = matriz;
        this.vendedor = vendedor;
    }
    
    

    @Override
    public String getDireccion() {
        return matriz.getDireccion();
    }
    
}
