/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pedido;

import java.util.List;
import model.local.compras.DetalleProducto;

/**
 *
 * @author josie
 */
public abstract class Pedido {
    protected int idpedido;
    protected String direccion;
    protected List<DetalleProducto> productos;
    
    public abstract String getDireccion();
    
    public abstract int getIdpedido();
}
