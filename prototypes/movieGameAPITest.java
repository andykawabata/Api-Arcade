/*
 *
 * API Prototype
 *
 */
package prototypes;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Francisco
 */
public class movieGameAPITest {

    /**
     *
     * Open Trivia Database API, pulls truth or false questions
     *
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws JSONException
     */
    
    
    public static final int NUMOFQUESTIONS = 1;
    public static final String DIFFICULTY_EASY = "easy";
    public static final String DIFFICULTY_MED = "medium";
    public static final String DIFFICULTY_HARD = "hard";
    
        
    public static String movieQuestion() throws IOException, InterruptedException, JSONException {
        String questionDifficulty = movieGameAPITest.DIFFICULTY_EASY;
        int numQuestions = movieGameAPITest.NUMOFQUESTIONS;
        
        String baseUrl = "https://opentdb.com/api.php";
        String numOfQuestions = "?amount=" + numQuestions;
        String questionCategory = "&category=11";
        String questionDiff = "&difficulty="+ questionDifficulty;
        String qustionType = "&type=boolean";
        String urlString = baseUrl + numOfQuestions + questionCategory + questionDiff + qustionType + "&encode=base64";
        String triviaQuestion = "";

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse response = httpClient.send(HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(urlString))
                .build(), HttpResponse.BodyHandlers.ofString());

        //System.out.println(response.body());
        /**
         *
         * JSON object is created, then a JSON array is used on the "result" to pull out the "question"
         *
         */
        JSONObject obj = new JSONObject(response.body().toString());
        JSONArray arr = obj.getJSONArray("results");
        for (int i = 0; i < arr.length(); i++) {
            triviaQuestion = arr.getJSONObject(i).getString("question");
        }
        String movieQuest = triviaQuestion;

        /**
         * movieTitle is being decoded
         */
        byte[] actualByte = Base64.getDecoder()
                                .decode(movieQuest);
        String actualString = new String(actualByte);
        movieQuest = actualString;

        return movieQuest;
    }

    /**
     *
     * The Movie Database API, pulls the description of a movie
     *
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws JSONException
     */
    public static String movieDescription() throws IOException, InterruptedException, JSONException {

        String baseUrl = "https://api.themoviedb.org";
        String callAction = "/3/movie/";
        String movieId = "tt6723592";
        String apiKey = "3ea3fbf8dfc520bf33fb4156aca6d42f";
        String urlString = baseUrl + callAction + movieId + "?api_key=" + apiKey + "&language=en-US";

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse response = httpClient.send(HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(urlString))
                .build(), HttpResponse.BodyHandlers.ofString());
        //System.out.println(response.body());
        JSONObject obj = new JSONObject(response.body().toString());
        String movieDescri = obj.getString("overview");
        return movieDescri;
    }
}
