/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Emergentes;

import javafx.scene.control.Alert;

/**
 *
 * @author Francisco
 */
public class Emergentes {
    
    public static void mostrarDialogo(String info, String cabecera, String titulo) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(info);
        alert.setHeaderText(cabecera);
        alert.setTitle(titulo);
        alert.showAndWait();
    }
}
