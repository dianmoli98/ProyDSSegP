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
public class Usuario extends Persona{
    protected String user;
    protected String password;
    protected boolean isAdmin;

    public Usuario( boolean isAdmin, String nombre, String apellido, String id) {
        super(nombre, apellido, id);
        this.isAdmin = isAdmin;
        user = "";
        password = "";
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
