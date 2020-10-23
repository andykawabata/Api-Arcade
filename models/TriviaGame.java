/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author andyk
 */
public class TriviaGame extends Game {
    
    /*
    PARENT FEILDS:
    String gameTitle
    String questionText
    ArrayList<String> images
    String result
    
    static Integer currentScore = 0
    static String correctAnswer
    static Integer currentQuestionNumber = 1
    static Integer totalQuestions
    */
    
    public TriviaGame(){
        this.totalQuestions = 5;
        this.gameTitle = "Trivia Game";
    }

    @Override
    public void initialize() {
    }

    @Override
    public void newQuestion() {
    }

    @Override
    public void processAnswer(String givenAnswer) {
    }
    
}
