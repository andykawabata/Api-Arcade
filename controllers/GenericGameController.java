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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
        if(currentGame == null)
            return;
        currentGame.initialize();
        Map<String, Object> data = currentGame.createMap();
        updateView(data);
        this._resultDisplay.setText("");
        this._nxtQuestion.setDisable(true);
        this._submitBtn.setDisable(false);
    }

   @FXML
    private void _newQuestion(ActionEvent event) {
        Game currentGame = GameFactory.getCurrentGameInstance();
        currentGame.newQuestion();
        Map<String, Object> data = currentGame.createMap();
        updateView(data);
        this._resultDisplay.setText("");
        this._nxtQuestion.setDisable(true);
        this._submitBtn.setDisable(false);
    }

    @FXML
    private void _submitAnswer(ActionEvent event) {
        //ADD CODE TO CHECK IF GAME OVER
        Game currentGame = GameFactory.getCurrentGameInstance();
        currentGame.processAnswer(_answerInput.getText());
        Map<String, Object> data = currentGame.createMap();
        updateView(data);
        this._answerInput.setText("");
        if(!currentGame.gameOver){
            this._submitBtn.setDisable(true);
            this._nxtQuestion.setDisable(false);
        }
        else{
            this._submitBtn.setDisable(true);
            this._nxtQuestion.setDisable(true);
        }
        
        
       
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
        if(_data.containsKey("imageLabelPairs")){
            ArrayList<HashMap<String,String>>  imageLabelPairs = (ArrayList<HashMap<String,String>>) _data.get("imageLabelPairs");
            this.addImagesToView(imageLabelPairs);
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

    private void addImagesToView(ArrayList<HashMap<String,String>> _imageLabelPairs){
        this._imageContainer.getChildren().clear();
        int index = 0;
        for(HashMap<String, String> imageLabelaPair : _imageLabelPairs){

            //CREATE JAVAFX ELEMENTS
            VBox vbox = new VBox();
            vbox.setAlignment(Pos.CENTER);
            ImageView imageView = new ImageView();
            Label label = new Label();
            Image image = new Image(_imageLabelPairs.get(index).get("image"));

            //INSERT CONTENT
            label.setText(_imageLabelPairs.get(index).get("label"));
            imageView.setImage(image);
            vbox.getChildren().add(imageView);
            vbox.getChildren().add(label);

            //ADD NEW IMAGE WITH LABEL TO VIEW
            this._imageContainer.getChildren().add(vbox);

            index++;
        }
    }

    @FXML
    private void _backBtn(ActionEvent event) throws IOException {
        RunApp.showMainGameView();
    }


}
