/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Francisco
 */
public class OtdbTranslater implements MovieAndTriviaAPIInterface {

    String baseUrl = "https://opentdb.com/api.php";
    String numOfQuestions = "?amount=10";
    String questionCategory = "&category=11";
    String questionDiff = "&difficulty=easy";
    String qustionType = "&type=boolean";
    String urlString = baseUrl + numOfQuestions + questionCategory + questionDiff + qustionType + "&encode=base64";
    String quest = "";
    JSONObject obj;

    @Override
    public JSONObject ResonseBody() throws Exception{

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse response = httpClient.send(HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(urlString))
                .build(), HttpResponse.BodyHandlers.ofString());

        JSONObject temp = new JSONObject(response.body().toString());
        return temp;
    }

    @Override
    public String Question(JSONObject obj) throws Exception {
        this.obj = obj;
        JSONArray arr = obj.getJSONArray("results");
        quest = arr.getJSONObject(0).getString("question");
        String TriviaQuestion = Decode(quest);
        return TriviaQuestion;
    }

    @Override
    public String Answer(JSONObject obj) throws Exception {
        this.obj = obj;
        JSONArray arr = obj.getJSONArray("results");
        quest = arr.getJSONObject(0).getString("correct_anwser");
        String TriviaAnswer = Decode(quest);
        return TriviaAnswer;
    }

    public static String Decode(String S1){
        byte[] actualByte = Base64.getDecoder()
                                .decode(S1);
        String actualString = new String(actualByte);
        return actualString;
    }

}