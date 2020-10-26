package controllers;
/*
*Last updated on 10/25/20
*
*
*
*Contributing authors
*@author Andy
*@author Ryan
*/
import apiarcade.RunApp;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import models.LoginSession;
import models.User;

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
    @FXML
    private Label errorLabel;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
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
        
        //IF FIELDS ARE BLANK
        if(username.getText().isBlank() || password.getText().isBlank()){
            errorLabel.setText("Please fill out all text fields");
            return;
        }

        User user = User.loadByUsername(givenUsername);
        
        //IF USER NOT IN DATABASE   
        if(user == null){
            errorLabel.setText("Username doesn't exist");
            return;
        }
        
        //IF INCORRECT PASSWORD
        if(!user.passwordMatches(givenPassword)){
            errorLabel.setText("Incorrect Password");
            return;
        }
        
        user.login();
        System.out.println("User Logged in");
        RunApp.showMainGame();
        System.out.println("id: " + LoginSession.currentUser.getId());
        
    }

    @FXML
    private void _hyplnkForgotPassword(ActionEvent event) throws IOException{
        System.out.println("Clicked Forgot Password");
        RunApp.showPassRecovery();
    }

    /*
    *This method changes the login scene to the register scene
    */
    @FXML
    private void _btnSignUp(ActionEvent event) throws IOException{
        System.out.println("Clicked Sign Up");
        RunApp.showRegister();
    }

    @FXML
     private void guestLogin(ActionEvent event) throws IOException{
        System.out.println("Clicked Guest login");
        RunApp.showMainGame();
    }
}
