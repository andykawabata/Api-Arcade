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
    public static String movieQuestion() throws IOException, InterruptedException, JSONException {

        String baseUrl = "https://opentdb.com/api.php";
        String numOfQuestions = "?amount=1";
        String questionCategory = "&category=11";
        String questionDiff = "&difficulty=easy";
        String qustionType = "&type=boolean";
        String urlString = baseUrl + numOfQuestions + questionCategory + questionDiff + qustionType + "&encode=base64";
        String quest = "";

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
            quest = arr.getJSONObject(i).getString("question");
        }
        String movieQuest = quest;

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
