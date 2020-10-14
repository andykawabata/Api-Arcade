/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import apiarcade.RunApp;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.User;

/**
 * FXML Controller class
 *
 * @author RyanC
 */
public class LoginController implements Initializable {

    private RunApp run;
    
    @FXML
    private Label _lblXOut;
    @FXML
    private JFXButton logGuest;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void _btnSignIn(ActionEvent event) {
        
        //HOW TO SET UUID???
        String givenUsername = this.username.getText();
        String givenPassword = this.password.getText();
        User user = User.loadByUsername(givenUsername);
        if(user == null)
            System.out.println("bad");
        if(user.passwordMatches(givenPassword)){
            user.login();
        }
            
        else
            System.out.println("bad");
        
        
    }

    @FXML
    private void _hyplnkForgotPassword(ActionEvent event) throws IOException{
        RunApp.showPassRecovery();
        System.out.println("Clicked Forgot Password");
    }

    /*
    *This method changes the login scene to the register scene
    */
    @FXML
    private void _btnSignUp(ActionEvent event) throws IOException{
        RunApp.showRegister();
        System.out.println("Clicked Sign Up");
    }

    @FXML
     private void guestLogin(ActionEvent event) throws IOException{
          RunApp.showMainGame();
          System.out.println("Clicked Guest login");
    }
}
