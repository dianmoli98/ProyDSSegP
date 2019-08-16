/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import model.singleton.ConexionBD;

/**
 *
 * @author josie
 */
public class CtrlMaster {
    
    
    public boolean validarLogin(String usuario, String password){
        ConexionBD con = ConexionBD.getInstance();
        return false;
        //todo: 
    }
}
