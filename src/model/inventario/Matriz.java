/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.inventario;
import java.util.List;

/**
 *
 * @author josie
 */
public class Matriz {
    private String id;
    private String tipo;
    private String direccion;
    private List<Stock> stocks;
    
    public Matriz(String id, String direccion, String tipo){
        this.id = id;
        this.direccion = direccion;
        this.tipo = tipo;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public List<Stock> getStocks() {
        return stocks;
    }  
}
