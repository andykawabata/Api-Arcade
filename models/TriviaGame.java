package models;
/*
*Last updated on 10/28/20
*
*Game based on the OTDB API.
*
*Contributing authors
*@author Francisco
*@author Ryan
*@author Andy
*/
import API.TriviaApiAdapter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

public class TriviaGame extends Game {

    /*
    PARENT FEILDS:
    String gameTitle
    String questionText
    ArrayList<HashMap<String, String> imageLabelPairs
    String result

    static Integer currentScore = 0
    static String correctAnswer
    static Integer currentQuestionNumber = 1
    static Integer totalQuestions
    */
    TriviaApiAdapter api = new TriviaApiAdapter();
    Scores scr = new Scores();

    public TriviaGame() {
        this.totalQuestions = 5;
        this.gameTitle = "Trivia Game: Text Edition";
    }

    @Override
    public void initialize() {
        newQuestion();
    }

    @Override
    public void newQuestion() {

        JSONObject obj;
        String question = "";
        String answer = "";

        try {
            obj = api.getResponseBody();
            question = api.getQuestion(obj);
            answer = api.getAnswer(obj);
        } catch (Exception ex) {
            Logger.getLogger(TriviaGame.class.getName()).log(Level.SEVERE, null, ex);
        }

        //SET FIELDS
        this.correctAnswer = answer;
        this.questionText = question;
    }

    @Override
    public void processAnswer(String _givenAnswer) {
        if(_givenAnswer == this.correctAnswer) {
            this.currentScore = scr.updateScore(true);
        }
    }

}
