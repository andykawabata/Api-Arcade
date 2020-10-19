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
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.User;

/**
 *
 * @author RyanFromSchool
 */
public class ForgotPasswordController implements Initializable {

    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField newPassword;
    @FXML
    private JFXTextField confirmPassword;
    @FXML
    private JFXButton _btnResetPassword;
    
    private RunApp run;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    
    @FXML
    private void _btnCancel(ActionEvent event) throws IOException {
        System.out.println("Returned to login view");
        RunApp.showLoginView();
    }

    //The reset password button
    @FXML
    private void _btnUpdateInfoAndReturn() throws IOException {
        System.out.println("Reset Password clicked");
        String newPass = "";
        String givenUsername = "";
        
        if(confirmPassword.getText().equals(newPassword.getText()))
            newPass = newPassword.getText();
        givenUsername = username.getText();
        
        User newUser = new User();
        newUser.setUsername(givenUsername);
        newUser.setPassword(newPass);
        
        //DataStoreAdapter.updateObject(newUser, User.TABLE);
        RunApp.showLoginView();
    }

        @FXML
    void _fieldConfPassword(KeyEvent event) {
        if (checkFieldsPopulated()){
            _btnResetPassword.setDisable(false);
        }
    }

    @FXML
    void _fieldPassword(KeyEvent event) {
        if (checkFieldsPopulated()){
            _btnResetPassword.setDisable(false);
        }
    }

    @FXML
    void _fieldUsername(KeyEvent event) {
        if (checkFieldsPopulated()){
            _btnResetPassword.setDisable(false);
        }
    }
    
    //returns true if all text fields have text in them
    boolean checkFieldsPopulated(){
        String [] fieldsPopulated = new String[3];
        fieldsPopulated[0] = username.getText();
        fieldsPopulated[1] = newPassword.getText();
        fieldsPopulated[2] = confirmPassword.getText();
        
        for (String i : fieldsPopulated)
            if (i == null || i.equals(""))
                return false;
        return true;
    }

    
}
