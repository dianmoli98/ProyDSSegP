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
public class Stock {
    private Producto nombre;
    private int idProducto;
    private int idStock;
    private String idMatriz; 

    public Stock(Producto nombre, int idStock, int idProducto, String idMatriz) {
        this.nombre = nombre;
        this.idStock = idStock;
        this.idProducto = idProducto;
        this.idMatriz = idMatriz;
    }

    public Producto getNombre() {
        return nombre;
    }

    public void setNombre(Producto nombre) {
        this.nombre = nombre;
    }

    public int getIdStock() {
        return idStock;
    }

    public void setIdStock(int idStock) {
        this.idStock = idStock;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getIdMatriz() {
        return idMatriz;
    }

    public void setIdMatriz(String idMatriz) {
        this.idMatriz = idMatriz;
    }
}
