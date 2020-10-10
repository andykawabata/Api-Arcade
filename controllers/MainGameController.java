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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Francisco
 */
public class MainGameController implements Initializable {

    @FXML
    private Label userID;
    @FXML
    private Label highScore;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
<<<<<<< HEAD
    private void _userLogOut(ActionEvent event) throws IOException {
=======
    private void _logOut(ActionEvent event) throws IOException {
>>>>>>> fb4d296c8ef316ca4d9d66dda6a1433f629eee48
        RunApp.showLoginView();
    }

    @FXML
    private void _gameStart(ActionEvent event) throws IOException {
        RunApp.showgenericGame();
    }
    
}
