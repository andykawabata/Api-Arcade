package models;
/*
*Last updated on 10/28/20
*
*Game based on the OMDB API.
*
*Contributing authors
*@author Francisco
*@author Ryan
*@author Andy
*/
import API.MovieApiAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public final int NUM_FALSE_MOVIES = 4;
    MovieApiAdapter api = new MovieApiAdapter();

    public MovieGame() {
        this.totalQuestions = 5;
        this.gameTitle = "Movie Game";
    }

    @Override
    public void initialize() {
        
        this.setNewQuestion();
    }

    private int shufflePosters(String[] _posterUrls) {
        Random rand = new Random();
        int correctIndex = rand.nextInt(this.NUM_FALSE_MOVIES)+1;
        swap(_posterUrls, correctIndex, 0);
        return correctIndex + 1;

    }

    public void swap(String[] _posters, int i, int j) {
        String temp = _posters[i];
        _posters[i] = _posters[j];
        _posters[j] = temp;
    }
    
    public void setNewQuestion(){
        int movieId = generateRandomId(2000);
        String description = "";
        String posterUrl = "";
        int correctIndexInt = 0;
        String[] similarPosterUrls = new String[NUM_FALSE_MOVIES];
        String[] allPosterUrls = new String[NUM_FALSE_MOVIES + 1];
        ArrayList<HashMap<String ,String>> posterList = new ArrayList<>();

        try {
            //GET CORRECT POSTER AND DESCRIPTION
            Map<String, String> descriptionAndPoster = api.getPosterAndDescriptionById(movieId);
            description = descriptionAndPoster.get("description");
            posterUrl = descriptionAndPoster.get("posterUrl");

            //EXTRACT FIRST SENTENCE OF DESCRIPTION
            String[] sentences = description.split("\\.");
            String firstSentence = sentences[0] + ".";
            description = firstSentence;

            //GET SIMILAR POSTERS
            similarPosterUrls = api.getPostersOfSimilarById(movieId, this.NUM_FALSE_MOVIES);

            //GET ALL POSTERS INTO SINGLE ARRAY
            allPosterUrls[0] = posterUrl;
            for(int i = 0; i < allPosterUrls.length -1; i++) {
                allPosterUrls[i+1] = similarPosterUrls[i];
            }

            //SHUFFLE ARRAY, GET CORRECT INDEX, ADD TO ARRAYLIST
            correctIndexInt = this.shufflePosters(allPosterUrls);
            for(int i = 0; i < allPosterUrls.length; i++) {
                HashMap<String,String> imageAndLabel = new HashMap<>();
                imageAndLabel.put("image", allPosterUrls[i]);
                imageAndLabel.put("label", String.valueOf(i+1));
                posterList.add(imageAndLabel);
            }

            //SET FIELDS
            this.correctAnswer = String.valueOf(correctIndexInt);
            this.questionText = description;
            this.imageLabelPairs = posterList;

        } catch (Exception ex) {
            System.out.println("exception!");
        }
    }
    
    @Override
    public void newQuestion() {
        this.currentQuestionNumber += 1;
        setNewQuestion();
    }

    @Override
    public void processAnswer(String _givenAnswer)  {
        
        //CHECK IF ANSWER IS CORRECT AND UPDATE RESULT/SCORE
        if(_givenAnswer.equals(this.correctAnswer)){
            this.result = "Correct";
            this.currentScore += 1;
        }
        else{
            this.result = "Incorrect";
        }
        
        //IF GAMES IS OVER APPEND "GAMEOVER" TO RESULT
        //ELSE INCRAMENT  QUESTION NUMBER
        if(checkGameOver()){
            this.gameOver = true;
            this.result = this.result + " - GAME OVER";
            Score finalScore = new Score(this.currentScore, this.totalQuestions);
            try {
                finalScore.save();
            } catch (Exception ex) {
                System.out.println("Score Not Saved!");
            }
        }
        
    }
    
    private boolean checkGameOver(){
        //RETURN TRUE IF GAME IS OVER
        return !(this.currentQuestionNumber < this.totalQuestions);
        
    }

    private int generateRandomId(int _range) {
        Random rand = new Random();
        int id = rand.nextInt(_range) + 1;
        return id;
    }

}
