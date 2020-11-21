package controllers;
/*
*Last updated on 10/25/20
*
*FXML Controller class
*
*Contributing authors
*@author Andy
*@author Ryan
*/
import apiarcade.RunApp;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import models.User;

public class RegisterController implements Initializable {

    @FXML
    JFXTextField username;

    @FXML
    JFXPasswordField password;

    @FXML
    JFXPasswordField confirmPassword;

    @FXML
    private Label lblErrorMessage;

    @FXML
    private Label _lblXOut;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }


    /*
    First checks password == confirmPassword and sets the error message
    Then checks if either username or password have not been entered

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
        if(!newConfirmPassword.equals(newPassword)){
            lblErrorMessage.setText("passwords don't match");
            return;
        }

        //error message = "Username already taken" or "Illegal Username"
        String errorMessage = User.checkErrors(new User(newUsername, newPassword));
        if(!(errorMessage == null))
            lblErrorMessage.setText(errorMessage);
        else
            new User(newUsername, newPassword).save();

        //if error message exists, clear forms
        if(!(lblErrorMessage.getText().equals(""))){
            username.setText(null);
            password.setText(null);
            confirmPassword.setText(null);
        }


    }

    @FXML
    private void _backBtn(ActionEvent event) throws IOException {
        RunApp.showLoginView();
    }

}
