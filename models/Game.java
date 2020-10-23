/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author andyk
 */
public abstract class Game {
    
    //Instance Variables
    public String gameTitle;
    public String questionText;
    public ArrayList<String> images;
    public String result;
    
    //State variables
    public static Integer currentScore = 0;
    public static String correctAnswer;
    public static Integer currentQuestionNumber = 1;
    public static Integer totalQuestions;
    
    public abstract void initialize();
    
    public abstract void newQuestion();
    
    public abstract void processAnswer(String givenAnswer);
    
    public Map<String, Object> createMap(){
        Map<String, Object> data = new HashMap();
        
        if(this.gameTitle != null)
            data.put("gameTitle", this.gameTitle);
        if (this.questionText != null)
            data.put("questionText", this.questionText);
        if(this.images != null)
            data.put("images", this.images);
        if(this.result != null)
            data.put("result", this.result);
        if(this.currentScore != null) {
            String scoreString = String.valueOf(this.currentScore);
            data.put("currentScore", scoreString);
        }
        if(this.currentQuestionNumber != null) {
            String questionNumberString = String.valueOf(this.currentQuestionNumber);
            data.put("currentQuestionNumber", questionNumberString);
        }
        if(this.totalQuestions != null) {
            String totalQuestionsString = String.valueOf(this.totalQuestions);
            data.put("totalQuestions", totalQuestionsString);
        }
        
        return data;
        
    }
    
}
