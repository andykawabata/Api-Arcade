package prototypes.gameDesign;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class gameController  implements Initializable  {
    
    @FXML
    private Text title;
    
    @FXML
    private Text questionText;

    @FXML
    private HBox imageContainer;
    
    @FXML
    private Text currentQuestionNumber;
    
    @FXML
    private Text totalQuestions;
    
    @FXML
    private Text currentScore;
    
    
    @FXML
    private TextField givenAnswer;


    
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
        Game currentGame = GameFactory.getInstanceOfCurrentGame();
        currentGame.initialize();
        Map<String, Object> data = currentGame.createMap();
        updateView(data);
       
    }

    @FXML
    private void submitAnswer(ActionEvent event) {
        
        //ADD CODE TO CHECK IF GAME OVER
        Game currentGame = GameFactory.getInstanceOfCurrentGame();
        currentGame.processAnswer(givenAnswer);
        Map<String, Object> data = currentGame.createMap();
        updateView(data);
        
        
    }
    
    @FXML
    private void newQuestion(ActionEvent event) {
        
        Game currentGame = GameFactory.getInstanceOfCurrentGame();
        currentGame.setNewQuestion();
        Map<String, Object> data = currentGame.createMap();
        updateView(data);
       
    }
    
    private void updateView(Map<String, Object> data){
        
    }
    
   

    

}
