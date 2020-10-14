//ADD ERROR MESSAGE BUTTON TO VIEW
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
import models.LoginSession;
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
    
    /**
     * Signs in user given username and password
     * or prints error message if unable to sign in
     * @param event
     * @throws Exception 
     */
    @FXML
    private void _btnSignIn(ActionEvent event) throws Exception {
        
        
        String givenUsername = username.getText();
        String givenPassword = password.getText();
        User user = User.loadByUsername(givenUsername); //if succesful, sets uuid
        if(user == null){
            System.out.println("Username doesn't exist"); //Should update view instead of printing
            return;
        }
        if(user.passwordMatches(givenPassword)){
            user.login();
            System.out.println("User Logged in");
        }
        else
            System.out.println("Incorrect Password"); //Should update view instead of printing
        
        System.out.println("id: " + LoginSession.currentUser.getId());
        System.out.println("uuid: " + LoginSession.currentUser.getUuid());
        System.out.println("username: " + LoginSession.currentUser.getUsername());
        System.out.println("password: " + LoginSession.currentUser.getPassword());
        
        
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
