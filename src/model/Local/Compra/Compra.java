/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Local.Compra;

import model.Local.Cliente;
import java.util.Date;
import java.util.LinkedList;
import model.Strategy.FormaPago;
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
    
    //todo: métodos y constructor
}
