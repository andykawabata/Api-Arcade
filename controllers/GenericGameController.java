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
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;


/**
 * FXML Controller class
 *
 * @author cisco
 */
public class GenericGameController implements Initializable {


    @FXML
    private Button _nxtQuestion;
    @FXML
    private Button _submitBtn;
    @FXML
    private TextField _answerInput;
    @FXML
    private Label _gameTitle;
    @FXML
    private Label _questionText;
    @FXML
    private Label _resultDisplay;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void _backBtn(ActionEvent event) throws IOException {
        RunApp.showMainGame();
    }

    
}
