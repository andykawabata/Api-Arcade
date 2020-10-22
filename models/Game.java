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
    public static Integer currentScore;
    public static String correctAnswer;
    public static Integer currentQuestionNumber = 1;
    
    public abstract void initialize();
    
    public abstract void newQuestion();
    
    public abstract void processAnswer(String givenAnswer);
    
    public Map<String, Object> createMap(){
        
        Map<String, Object> data = new HashMap();
        
        if(gameTitle != null)
            data.put("gameTitle", gameTitle);
        if (questionText != null)
            data.put("questionText", questionText);
        if(images != null)
            data.put("images", images);
        if(result != null)
            data.put("result", result);
        if(currentScore != null)
            data.put("currentScore", currentScore);
        if(currentQuestionNumber != null)
            data.put("currentQuestionNumber", data);
        
        return data;
        
    }
    
}
