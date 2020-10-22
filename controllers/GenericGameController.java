/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import apiarcade.RunApp;
import factories.GameFactory;
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
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import models.Game;


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
    @FXML
    private Text _currentQuestionNumber;
    @FXML
    private Text _totalQuestions;
    @FXML
    private Text _currentScore;
    @FXML
    private HBox _imageContainer;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Game currentGame = GameFactory.getCurrentGameInstance();
        currentGame.initialize();
        Map<String, Object> data = currentGame.createMap();
        updateView(data);
    }    
    
   @FXML
    private void _newQuestion(ActionEvent event) {
        Game currentGame = GameFactory.getCurrentGameInstance();
        currentGame.newQuestion();
        Map<String, Object> data = currentGame.createMap();
        updateView(data);
       
    }
    
    @FXML
    private void _submitAnswer(ActionEvent event) {
        //ADD CODE TO CHECK IF GAME OVER
        Game currentGame = GameFactory.getCurrentGameInstance();
        currentGame.processAnswer(_answerInput.getText());
        Map<String, Object> data = currentGame.createMap();
        updateView(data);
    }
    

       
    /*
    data:
        {
        gameTitle: String,
        questionText: String,
        images: ArrayList<String>,
        result: String,
        currentScore: String,
        currentQuestionNumber: String
        }
     */
    private void updateView(Map<String, Object> _data){
        if(_data.containsKey("gameTitle")){
            String title = (String) _data.get("gameTitle");
            this._gameTitle.setText(title);
        }
        if(_data.containsKey("questionText")){
            String text = (String) _data.get("questionText");
            this._questionText.setText(text);
         }
        if(_data.containsKey("images")){
            ArrayList<String> images = (ArrayList<String>) _data.get("images");
            for(String image : images){
                //append images to container
            }
        }
        if(_data.containsKey("result")){
            String result = (String) _data.get("result");
            this._resultDisplay.setText(result);
        }
        if(_data.containsKey("currentScore")){
            String currentScore = (String) _data.get("currentScore");
            this._currentScore.setText(currentScore);
        }
        if(_data.containsKey("currentQuestionNumber")){
            String currentQuestionNumber = (String) _data.get("currentQuestionNumber");
            this._currentQuestionNumber.setText(currentQuestionNumber);
        }
        if(_data.containsKey("totalQuestions")){
            String currentQuestionNumber = (String) _data.get("totalQuestions");
            this._totalQuestions.setText(currentQuestionNumber);
        }
    }
    
    @FXML
    private void _backBtn(ActionEvent event) throws IOException {
        RunApp.showMainGame();
    }

    
}
