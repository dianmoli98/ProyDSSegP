/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Pedido;

import model.Local.Cliente;
import model.Local.Vendedor;

/**
 *
 * @author josie
 */
public class PedidoCliente extends Pedido{
    protected Cliente cliente;
    protected Vendedor vendedor;
    
    @Override
    public String getDireccion() {
        return cliente.getDireccion();
    }
    
}
