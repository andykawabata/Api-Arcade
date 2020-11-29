package API;
/*
*Last updated on 11/10/20
*
*Sets up the httpClient link and finds the question and answer in the JSONObject
*
*Contributing authors
*@author Francisco
*@author Ryan
*/
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import org.json.JSONArray;
import org.json.JSONObject;

public class OtdbTranslator implements TriviaApiInterface {

    String baseUrl          = "https://opentdb.com/api.php";
    String numOfQuestions   = "?amount=" + 10;
    String questionCategory = ""; //&category=" + 11;
    String questionDiff     = "&difficulty=" + "easy";
    String questionType      = "&type=boolean";
    String sessionToken     = "";
    String urlString        = baseUrl + numOfQuestions + questionCategory + questionDiff + questionType +"&encode=base64" + "&token=" + sessionToken;
    String retriveToken     = "https://opentdb.com/api_token.php?command=request";
    String quest;
    JSONArray arr;

    public JSONObject apiCall(String _urlString) throws Exception {

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse response = httpClient.send(HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(_urlString))
                .build(), HttpResponse.BodyHandlers.ofString());

        return new JSONObject(response.body().toString());
    }
    // Need to implement to avoid getting the same question twice in one session
    public String getToken() throws Exception{

        JSONObject token = apiCall(retriveToken);
        sessionToken = token.getString("token");
        return sessionToken;
    }

    @Override
    public JSONArray getGameQuestions() throws Exception{
        if(sessionToken.isEmpty()) {
            getToken();
        }
        arr = apiCall(urlString).getJSONArray("results");
        return arr;
    }

    @Override
    public String getCurrentQuestion(int _counter) throws Exception {

        quest = arr.getJSONObject(_counter).getString("question");
        String TriviaQuestion = Decode(quest);
        return TriviaQuestion;
    }

    @Override
    public String getCurrentAnswer(int _counter) throws Exception {

        quest = arr.getJSONObject(_counter).getString("correct_answer");
        String TriviaAnswer = Decode(quest);
        return TriviaAnswer;
    }

    public static String Decode(String s){
        byte[] actualByte = Base64.getDecoder().decode(s);
        return new String(actualByte);
    }

}
