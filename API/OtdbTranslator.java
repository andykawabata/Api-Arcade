package API;

/*
*Last updated on 11/30/20
*
*Sets up the httpClient link and finds the question and answer in the JSONObject
*
*Contributing authors
*@author Francisco
 */
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import org.json.JSONArray;
import org.json.JSONObject;

public class OtdbTranslator implements TriviaApiInterface {

    String baseUrl = "https://opentdb.com/api.php";
    String numOfQuestions = "?amount=" + 10;
    String questionCategory = ""; //&category=" + 11;
    String questionDiff = "&difficulty=" + "easy";
    String questionType = "&type=boolean";
    String sessionToken = "";
    String urlString = baseUrl + numOfQuestions + questionCategory + questionDiff + questionType + "&encode=base64" + "&token=" + sessionToken;
    String retriveToken = "https://opentdb.com/api_token.php?command=request";
    String quest;
    JSONArray arr;

    //Reuseable api call
    public JSONObject apiCall(String _urlString) throws Exception {

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse response = httpClient.send(HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(_urlString))
                .build(), HttpResponse.BodyHandlers.ofString());

        return new JSONObject(response.body().toString());
    }
  
    //=================  GETTERS ===============//
    // generates a token to avoid repeat questions
    public String getToken() throws Exception {
        JSONObject token = apiCall(retriveToken);
        sessionToken = token.getString("token");
        return sessionToken;
    }

    // get the array with all questions for the game instance
    @Override
    public JSONArray getGameQuestions() throws Exception {
        if (sessionToken.isEmpty()) {
            getToken();
        }
        arr = apiCall(urlString).getJSONArray("results");
        return arr;
    }

    // parser to pull one quetions at a time
    @Override
    public String getCurrentQuestion(int _counter) throws Exception {

        quest = arr.getJSONObject(_counter).getString("question");
        String TriviaQuestion = Decode(quest);
        return TriviaQuestion;
    }

    // parser to pull the answer to the question above
    @Override
    public String getCurrentAnswer(int _counter) throws Exception {

        quest = arr.getJSONObject(_counter).getString("correct_answer");
        String TriviaAnswer = Decode(quest);
        return TriviaAnswer.toLowerCase();
    }

    // Decodes string using Base64
    public static String Decode(String _string) {
        byte[] actualByte = Base64.getDecoder().decode(_string);
        return new String(actualByte);
    }

}
