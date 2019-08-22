/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.inventario;

/**
 *
 * @author josie
 */
public class Producto {
    private int idProducto;
    private String nombre;
    private String descripcion;
    private String categoria;
    private double precio;
    
    public Producto(int idProducto,String nombre, String descripcion, double precio,String categoria){
        this.idProducto=idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria=categoria;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idproducto) {
        this.idProducto = idproducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

}