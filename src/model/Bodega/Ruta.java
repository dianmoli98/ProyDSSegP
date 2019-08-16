/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Bodega;

import java.util.LinkedList;
import model.Pedido.Pedido;

/**
 *
 * @author josie
 */
public class Ruta {
    private Jefe_Bodega jefe;
    private Repartidor repartidor;
    private boolean realizado;
    private LinkedList<Pedido> pedidos;
    private LinkedList<String> direcciones;
    
}
