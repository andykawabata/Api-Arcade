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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private void _btnCancel(ActionEvent event) throws IOException {
        System.out.println("Clicked Forgot Password");
        Parent regViewParent =
                FXMLLoader.load(getClass().getResource("/views/login.fxml"));
        Scene regViewScene = new Scene(regViewParent);
        
        //get Stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(regViewScene);
        window.show();
    }


    
}
