/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import API.MovieApiAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andyk
 */
public class MovieGame extends Game {
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
    MovieApiAdapter api = new MovieApiAdapter();

    public MovieGame(){
        this.totalQuestions = 5;
        this.gameTitle = "Movie Game";
    }
    
    @Override
    public void initialize() {
        
        //GENERATE RANDOM ID
        int movieId = generateRandomId(2000);
        try {
            Map<String, String> descriptionPoster = api.getPosterAndDescriptionById(movieId);
            System.out.println(descriptionPoster.get("posterUrl"));
            
        } catch (Exception ex) {
            System.out.println("exception!");
        }
        
    }

    @Override
    public void newQuestion() {
    }

    @Override
    public void processAnswer(String _givenAnswer) {
    }

    private int generateRandomId(int _range) {
        Random rand = new Random();
        int id = rand.nextInt(_range) + 1;
        return id;
    }
    
    
    
}
