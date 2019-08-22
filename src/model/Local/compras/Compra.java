/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.local.compras;

import model.local.Cliente;
import java.util.Date;
import java.util.LinkedList;
import model.strategy.FormaPago;
/**
 *
 * @author josie
 */
public class Compra {
    private String numeracion;
    private Cliente cliente;
    private LinkedList<DetalleProducto> productos;
    private double total;
    private FormaPago formaPago;
    private Date fecha;

}
