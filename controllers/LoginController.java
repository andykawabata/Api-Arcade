/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import apiarcade.RunApp;
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

/**
 * FXML Controller class
 *
 * @author RyanC
 */
public class LoginController implements Initializable {

    private RunApp run;
    
    @FXML
    private Label _lblXOut;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void _btnSignIn(ActionEvent event) {
        System.out.println("Clicked Sign In");
        //transfer to main game view if user exists
    }

    @FXML
    private void _hyplnkForgotPassword(ActionEvent event) throws IOException{
        run.showPassRecovery();
        System.out.println("Clicked Forgot Password");
    }

    /*
    *This method changes the login scene to the register scene
    */
    @FXML
    private void _btnSignUp(ActionEvent event) throws IOException{
        run.showRegister();
        System.out.println("Clicked Sign Up");
    }

    @FXML
     private void guestLogin(ActionEvent event) throws IOException{
          run.showMainGame();
          System.out.println("Clicked Guest login");
    }
}
