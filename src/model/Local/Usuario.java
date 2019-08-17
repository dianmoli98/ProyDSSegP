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
public class Usuario extends Persona{
    private String user;
    private String password;
    private boolean isAdmin;

    public Usuario(String user, String password, boolean isAdmin, String nombre, String apellido, String id) {
        super(nombre, apellido, id);
        this.user = user;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }
}
