/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Inventario;

/**
 *
 * @author josie
 */
public class Producto {
    private String nombre;
    private String descripcion;
    private double precio;
    
    public Producto(String nombre, String descript, double precio){
        this.nombre = nombre;
        this.descripcion = descript;
        this.precio = precio;
    }
    
    public boolean cambiarPrecio(double n){
        precio = n;
        //todo: actualizar base de datos
        return true;
    }
    
    public double getPrecio(){
        return precio;
    }
}
