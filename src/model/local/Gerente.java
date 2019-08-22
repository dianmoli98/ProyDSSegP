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
public class Gerente extends Usuario {

    public Gerente(boolean isAdmin, String nombre, String apellido, String id) {
        super(isAdmin, nombre, apellido, id);
    }
    
    public Gerente(Usuario u){
        super(u.isAdmin, u.nombre, u.apellido, u.id);
    }
}
