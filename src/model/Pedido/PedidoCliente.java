/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pedido;

import model.local.Cliente;
import model.local.Vendedor;

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

    public PedidoCliente(int idpedido, Cliente cliente, Vendedor vendedor) {
        super.idpedido = idpedido;
        this.cliente = cliente;
        this.vendedor = vendedor;
    }

    @Override
    public int getIdpedido() {
        return idpedido;
    }
}
