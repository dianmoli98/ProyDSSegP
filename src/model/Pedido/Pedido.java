/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Pedido;

import model.Local.Compra.DetalleProducto;
import java.util.List;

/**
 *
 * @author josie
 */
public abstract class Pedido {
    protected List<DetalleProducto> productos;
    
    public abstract String getDireccion();
}
