/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bodega;

import java.util.LinkedList;
import model.inventario.Matriz;
import model.local.Persona;

/**
 *
 * @author josie
 */
public class Repartidor extends Persona{
    protected Matriz matriz;
    protected LinkedList<Ruta> rutas;
    protected int cantRutas;

    public Repartidor(String nombre, String apellido, String id, int cant) {
        super(nombre, apellido, id);
        cantRutas = cant;
    }
    
    public Repartidor(Persona p, int cant){
        super(p.getNombre(), p.getApellido(),p.getId());
        cantRutas = cant;
    }
    
    @Override
    public String toString(){
        return this.nombre + " " + this.apellido;
    }

    public Matriz getMatriz() {
        return matriz;
    }

    public void setMatriz(Matriz matriz) {
        this.matriz = matriz;
    }

    public int getCantRutas() {
        return cantRutas;
    }

    public void setCantRutas(int cantRutas) {
        this.cantRutas = cantRutas;
    }
    
}
