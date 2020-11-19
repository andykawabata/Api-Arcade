package models;
/*
*Last updated on 11/18/20
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
    private final int ZERO = 0;
    private final int ONE = 1;
    public static int counter = 0;
    TriviaApiAdapter api = new TriviaApiAdapter();

    public TriviaGame() {
        this.totalQuestions = 10;
        this.gameTitle = "Trivia Game: Text Edition";
    }

    @Override
    public void initialize(){
        try {
            resetGame();
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
            Score finalScore = new Score(this.currentScore);
            try {
                finalScore.save();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private boolean checkGameOver(){
        //RETURN TRUE IF GAME IS OVER
        return !(this.currentQuestionNumber < this.totalQuestions);
    }

    public void resetGame() {
        counter = ZERO;
        this.currentScore = ZERO;
        this.currentQuestionNumber = ONE;
    }

    public int countUp() {
        return counter++;
    }

}
