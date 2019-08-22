/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.local;

/**
 *
 * @author josie
 */
public class Cliente extends Persona {
    protected String direccion;
    protected String telefono;

    public Cliente(String nombre, String apellido, String id, String direccion, String telefono) {
        super(nombre, apellido, id);
        this.direccion = direccion;
        this.telefono = telefono;
    }
    
    public Cliente(Persona p,String direccion, String telefono){
        super(p.getNombre(), p.getApellido(), p.getId());
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
}
