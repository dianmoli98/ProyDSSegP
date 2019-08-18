/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Bodega;

import java.util.LinkedList;
import java.util.List;
import model.Pedido.Pedido;
import model.Local.Usuario;

/**
 *
 * @author josie
 */
public class Jefe_Bodega extends Usuario {
    protected List<Pedido> pedidos;

    public Jefe_Bodega(String user, String password, boolean isAdmin, String nombre, String apellido, String id) {
        super(user, password, isAdmin, nombre, apellido, id);
        pedidos = new LinkedList<>();
    }
    
}
