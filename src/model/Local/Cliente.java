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
public class Cliente extends Persona {
    protected String direccion;
    protected String telefono;
    
    //todo:  buscar lib para telefono

    public Cliente(String nombre, String apellido, String id, String direccion, String telefono) {
        super(nombre, apellido, id);
    }

    public String getDireccion() {
        return direccion;
    }
}
