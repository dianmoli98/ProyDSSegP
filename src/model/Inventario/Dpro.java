/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Inventario;

/**
 *
 * @author User-pc
 */
public class Dpro {
    private String nombre,tipoLocalidad,direccion,id_matriz;
    private int Stock;

    public Dpro(String nombre, String tipoLocalidad, int Stock,String direccion,String id_matriz) {
        this.nombre = nombre;
        this.tipoLocalidad = tipoLocalidad;
        this.Stock = Stock;
        this.direccion=direccion;
        this.id_matriz=id_matriz;
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
        return Stock;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getId_matriz() {
        return id_matriz;
    }

    public void setId_matriz(String id_matriz) {
        this.id_matriz = id_matriz;
    }

    
    
    
}
