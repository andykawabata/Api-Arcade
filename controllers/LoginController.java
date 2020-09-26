/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

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
        System.out.println("Clicked Forgot Password");
        Parent regViewParent =
                FXMLLoader.load(getClass().getResource("/views/forgotPassword.fxml"));
        Scene regViewScene = new Scene(regViewParent);
        
        //get Stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(regViewScene);
        window.show();
    }

    /*
    *This method changes the login scene to the register scene
    */
    @FXML
    private void _btnSignUp(ActionEvent event) throws IOException{
        System.out.println("Clicked Sign Up");
        Parent regViewParent =
                FXMLLoader.load(getClass().getResource("/views/register.fxml"));
        Scene regViewScene = new Scene(regViewParent);
        
        //get Stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(regViewScene);
        window.show();
        
    }
    
}
