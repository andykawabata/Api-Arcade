package API;
/*
*Last updated on 10/24/20
*
*Sets up the httpClient link and finds the question and answer in the JSONObject
*
*Contributing authors
*@author Francisco
*@author Ryan
*/
import java.io.IOException;
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
    String questionCategory = "&category=" + 11;
    String questionDiff     = "&difficulty=" + "easy";
    String qustionType      = "&type=boolean";
    //String sessionToken     = "&token=";
    String urlString        = baseUrl + numOfQuestions + questionCategory + questionDiff + qustionType + "&encode=base64";
    String quest;

    // Need to implement to avoid getting the same question twice in one session
    public String resetToken() throws Exception {
        return "";
    }
    // Same as above
    public String getToken() throws Exception{
        return "";
    }

    @Override
    public JSONObject getResponseBody() throws Exception {

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse response = httpClient.send(HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(urlString))
                .build(), HttpResponse.BodyHandlers.ofString());

        return new JSONObject(response.body().toString());
    }

    @Override
    public String getQuestion(JSONObject obj) throws Exception {

        JSONArray results = obj.getJSONArray("results");
        quest = results.getJSONObject(0).getString("question");
        String TriviaQuestion = Decode(quest);
        return TriviaQuestion;
    }

    @Override
    public String getAnswer(JSONObject obj) throws Exception {

        JSONArray results = obj.getJSONArray("results");
        quest = results.getJSONObject(0).getString("correct_answer");
        String TriviaAnswer = Decode(quest);
        return TriviaAnswer;
    }

    public static String Decode(String s){
        byte[] actualByte = Base64.getDecoder().decode(s);
        return new String(actualByte);
    }

}
