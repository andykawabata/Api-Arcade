package models;

/*
*Last updated on 10/28/20
*
*Main framework for all games
*
*Contributing authors
*@author Francisco
*@author Andy
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Game {

    //Instance Variables
    public String gameTitle;
    public String questionText;
    public ArrayList<HashMap<String, String>> imageLabelPairs;
    public String result;
    public boolean gameOver = false;

    //State variables
    public static Integer currentScore = 0;
    public static String correctAnswer;
    public static Integer currentQuestionNumber = 1;
    public static Integer totalQuestions;

    public abstract void initialize();

    public abstract void newQuestion();

    public abstract void processAnswer(String _givenAnswer);

    public Map<String, Object> createMap() {
        Map<String, Object> data = new HashMap();

        if (this.gameTitle != null) {
            data.put("gameTitle", this.gameTitle);
        }
        if (this.questionText != null) {
            data.put("questionText", this.questionText);
        }
        if (this.imageLabelPairs != null) {
            data.put("imageLabelPairs", this.imageLabelPairs);
        }
        if (this.result != null) {
            data.put("result", this.result);
        }
        if (this.currentScore != null) {
            String scoreString = String.valueOf(this.currentScore);
            data.put("currentScore", scoreString);
        }
        if (this.currentQuestionNumber != null) {
            String questionNumberString = String.valueOf(this.currentQuestionNumber);
            data.put("currentQuestionNumber", questionNumberString);
        }
        if (this.totalQuestions != null) {
            String totalQuestionsString = String.valueOf(this.totalQuestions);
            data.put("totalQuestions", totalQuestionsString);
        }

        return data;

    }

}
