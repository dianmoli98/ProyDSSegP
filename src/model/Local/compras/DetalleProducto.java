/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.local.compras;

import model.inventario.Producto;

/**
 *
 * @author josie
 */
public class DetalleProducto {
    private Producto producto;
    private Compra compra;
    private int cantidad;
    
    public double calcularTotal(){
        return producto.getPrecio() * cantidad;
    }
}
