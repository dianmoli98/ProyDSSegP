/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bodega;

import java.util.LinkedList;
import java.util.List;
import model.pedido.Pedido;
import model.local.Usuario;

/**
 *
 * @author josie
 */
public class JefeBodega extends Usuario {
    protected List<Pedido> pedidos;

    public JefeBodega(boolean isAdmin, String nombre, String apellido, String id) {
        super(isAdmin, nombre, apellido, id);
        pedidos = new LinkedList<>();
    }
    
    public JefeBodega(Usuario u){
        super(u.isIsAdmin(),u.getNombre() ,u.getApellido(), u.getId());
        pedidos = new LinkedList<>();
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
    
    
}
