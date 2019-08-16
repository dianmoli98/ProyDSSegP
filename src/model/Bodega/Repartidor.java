/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Bodega;

import java.util.LinkedList;
import model.Bodega.Ruta;
import model.Inventario.Matriz;
import model.Local.Persona;

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
}
