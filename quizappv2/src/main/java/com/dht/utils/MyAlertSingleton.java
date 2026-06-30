/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.utils;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author admin
 */
public class MyAlertSingleton {
    private static MyAlertSingleton instance;
    private final Alert alert;
    
    private MyAlertSingleton() {
        this.alert = new Alert(Alert.AlertType.INFORMATION);
        this.alert.setTitle("quizapp");
    }
    
    public static MyAlertSingleton getInstance() {
        if (instance == null)
            instance = new MyAlertSingleton();
        
        return instance;
    }
    
    public void showAlert(String content) {
        this.alert.setContentText(content);
        this.alert.show();
    }
    
    public Optional<ButtonType> showAlert(String content, Alert.AlertType type) {
        this.alert.setContentText(content);
        this.alert.setAlertType(type);
        return this.alert.showAndWait();
    }
}
