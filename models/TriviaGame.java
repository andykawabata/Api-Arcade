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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
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
    private final int SCORE_UP = 100;
    private final int SCORE_DOWN = 50;
    public static int counter = 0;
    TriviaApiAdapter api = new TriviaApiAdapter();

    public TriviaGame() {
        this.totalQuestions = 5;
        this.gameTitle = "Trivia Game: Text Edition";
    }

    @Override
    public void initialize(){
        try {
            setGameQuestions();
            setNewQuestion();
        } catch (Exception ex) {
            Logger.getLogger(TriviaGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setGameQuestions() throws Exception{

        api.getGameQuestions();
    }

    public void setNewQuestion() {

        String question = "";
        String answer = "";

        try {
            //System.out.print(game);
            question = api.getCurrentQuestion(counter);
            answer = api.getCurrentAnswer(counter);
            countUp();
        } catch (Exception ex) {
            Logger.getLogger(TriviaGame.class.getName()).log(Level.SEVERE, null, ex);
        }

        //SET FIELDS
        this.correctAnswer = answer;
        this.questionText = question;
    }

    @Override
    public void newQuestion() {

        setNewQuestion();
        this.currentQuestionNumber += 1 ;
    }

    @Override
    public void processAnswer(String _givenAnswer) {
        if(_givenAnswer.equals(this.correctAnswer)) {
            this.result = "correct";
            this.currentScore += SCORE_UP;
        }
        else {
            this.result = "Incorrect";
            this.currentScore -= SCORE_DOWN;
        }

        //IF GAMES IS OVER APPEND "GAMEOVER" TO RESULT
        //ELSE INCRAMENT  QUESTION NUMBER
        if(checkGameOver()){
            this.gameOver = true;
            this.result = this.result + " - GAME OVER";
//            Score finalScore = new Score(this.currentScore, this.totalQuestions);
//            try {
//                finalScore.save();
//            } catch (Exception ex) {
//                System.out.println("Score Not Saved!");
//            }
        }
    }

    private boolean checkGameOver(){
        //RETURN TRUE IF GAME IS OVER
        return !(this.currentQuestionNumber < this.totalQuestions);
    }

    public int countUp() {
        return counter++;
    }

}
