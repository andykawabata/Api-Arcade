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
    JFXPasswordField confirmPassword;
    
    @FXML
    Label lblErrorMessage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    @FXML
    /*
    First checks password == confirmPassword and sets the error message
    Then checks if either username or password have not been entered because
        that message is more important
    
    @param event - click on button and check fields entered
    */
    private void _btnRegister(ActionEvent event) throws Exception {
        //Checks nulls
        lblErrorMessage.setText("");
        if(this.confirmPassword == null)
            lblErrorMessage.setText("Confirm password");
        if(this.username == null || this.password == null){
            lblErrorMessage.setText("Enter username and password");
            return;
        }
        
        String newUsername = this.username.getText();
        String newPassword = this.password.getText();
        
        //Checks if password is confirmed before making User
        //if user exists --> error
        //If user is new --> create
        if(!confirmPassword.getText().equals(password.getText()))
            lblErrorMessage.setText("Confirm password");
        else if(User.findUsername(newUsername) == null){
            User user = new User();
            user.setUUID();
            user.setUsername(newUsername);
            user.setPassword(newPassword);
            user.save();
        }
        else{
            lblErrorMessage.setText("Username already taken");
        }
            
    }
    
    
    
    
   
    @FXML
    private void _btnSignUp(ActionEvent event) {
    }
    
}