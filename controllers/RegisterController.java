/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import models.User;

/**
 * FXML Controller class
 *
 * @author RyanC
 * @author AndyK
 */
public class RegisterController implements Initializable {
    
    @FXML
    JFXTextField username;
    
    @FXML
    JFXPasswordField password;
    
    @FXML
    JFXPasswordField confirmPassowrd;
    
    @FXML
    Label errorMessage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    @FXML
    private void _btnRegister(ActionEvent event) throws Exception {
        errorMessage.setText("");
        if(this.username == null || this.password == null){
            errorMessage.setText("Enter username and password");
            return;
        }
        
        String newUsername = this.username.getText();
        String newPassword = this.password.getText();
        String confirmPassword = this.confirmPassowrd.getText();
        
        User user = new User();
        user.setUsername(newUsername);
        user.setPassword(newPassword);
        user.save();
        
    }
    
    
    
    
   
    @FXML
    private void _btnSignUp(ActionEvent event) {
    }
    
}