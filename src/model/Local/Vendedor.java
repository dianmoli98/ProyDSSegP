/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Local;

import model.Local.Compra.Compra;

/**
 *
 * @author josie
 */
public class Vendedor extends Usuario {
    
    public Vendedor(String user, String password, boolean isAdmin, String nombre, String apellido, String id) {
        super(user, password, isAdmin, nombre, apellido, id);
    }
    
    public Cliente agregarCliente(){
        return null;
        //todo: agrega un cliente
    }
    
    public Cotizacion realizarCotizacion(){
        return null;
        //todo: realiza cotizacion
    }
    
    public Compra realizarVenta(){
        return null;
        //todo: realizar compra
    }
}
