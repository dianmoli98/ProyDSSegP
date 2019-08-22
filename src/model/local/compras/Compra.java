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

    public String getNumeracion() {
        return numeracion;
    }

    public void setNumeracion(String numeracion) {
        this.numeracion = numeracion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LinkedList<DetalleProducto> getProductos() {
        return productos;
    }

    public void setProductos(LinkedList<DetalleProducto> productos) {
        this.productos = productos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
}
