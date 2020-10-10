/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import apiarcade.RunApp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;

import javafx.scene.control.Button;

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
    
    JFXPasswordField confirmPassword;
    
    Label lblErrorMessage;
    @FXML
    private JFXPasswordField confirmPassowrd;
    @FXML
    private Label errorMessage;
    @FXML
    private Label _lblXOut;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    
    /*
    First checks password == confirmPassword and sets the error message
    Then checks if either username or password have not been entered because
        that message is more important
    
    @param event - click on button and check fields entered
    */
    @FXML
    private void _btnRegister(ActionEvent event) throws Exception {

        
        String newUsername = username.getText();
        String newPassword = password.getText();
        String newConfirmPassword = confirmPassword.getText();
        //Checks nulls
        lblErrorMessage.setText("");
        if(newConfirmPassword == null){
            lblErrorMessage.setText("Confirm password");
            return;
        }
        if(newUsername == null || newPassword == null){
            lblErrorMessage.setText("Enter username and password");
            return;
        }
        
        
        //Checks if password is confirmed before making User
        //if user exists --> error
        //If user is new --> create
        if(!newConfirmPassword.equals(newPassword)){
            lblErrorMessage.setText("passwords don't match");
            return;
        }
        //check if user already exists
        if(true){
            User user = new User();
            user.setUsername(newUsername);
            user.setPassword(newPassword);
            user.save();
        }
        else{
            lblErrorMessage.setText("Username already taken");
        }
            
    }

    @FXML

    private void _backBtn(ActionEvent event) throws IOException {
        
    }

    private void _btnBack(ActionEvent event) throws IOException {
        RunApp.showLoginView();
    }
    
}