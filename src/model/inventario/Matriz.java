/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.inventario;
import java.util.List;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Matriz other = (Matriz) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        if (!Objects.equals(this.direccion, other.direccion)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Matriz{" + "id=" + id + ", tipo=" + tipo + ", direccion=" + direccion + '}';
    }
    
}
