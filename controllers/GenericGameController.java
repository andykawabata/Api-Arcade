/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

<<<<<<< HEAD
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
=======
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
>>>>>>> fb4d296c8ef316ca4d9d66dda6a1433f629eee48

/**
 * FXML Controller class
 *
 * @author cisco
 */
public class GenericGameController implements Initializable {

<<<<<<< HEAD
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

=======
>>>>>>> fb4d296c8ef316ca4d9d66dda6a1433f629eee48
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
<<<<<<< HEAD

    @FXML
    private void _backBtn(ActionEvent event) throws IOException {
        RunApp.showMainGame();
    }
=======
>>>>>>> fb4d296c8ef316ca4d9d66dda6a1433f629eee48
    
}
