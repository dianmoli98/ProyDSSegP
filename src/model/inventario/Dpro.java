/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.inventario;

/**
 *
 * @author User-pc
 */
public class Dpro {
    private String nombre;
    private String tipoLocalidad;
    private String direccion;
    private String idMatriz;
    private int stock;
    private int idProducto;

    public Dpro(String nombre, String tipoLocalidad, int stock,String direccion,String idMatriz,int idProducto) {
        this.nombre = nombre;
        this.tipoLocalidad = tipoLocalidad;
        this.stock = stock;
        this.direccion=direccion;
        this.idMatriz=idMatriz;
        this.idProducto=idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoLocalidad() {
        return tipoLocalidad;
    }

    public void setTipoLocalidad(String tipoLocalidad) {
        this.tipoLocalidad = tipoLocalidad;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int Stock) {
        this.stock = Stock;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getIdMatriz() {
        return idMatriz;
    }

    public void setIdmatriz(String idmatriz) {
        this.idMatriz = idmatriz;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idproducto) {
        this.idProducto = idproducto;
    }
}
