/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Local;

/**
 *
 * @author josie
 */
public class Persona {
    protected String nombre;
    protected String apellido;
    protected String id;
    
    public Persona(String nombre, String apellido, String id){
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getId() {
        return id;
    }
    
}
