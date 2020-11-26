package prototypes;
/*
*Last updated on MM/DD/20
*
*
*
*Contributing authors
*@author Andy?
*@author Francisco?
*/
import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class JsonJavaTest {

    public static void getJokeCategoryAndPrint() throws IOException, InterruptedException, JSONException {

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse response = httpClient.send(HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://sv443.net/jokeapi/v2/joke/Any?amount=10"))
                .build(), HttpResponse.BodyHandlers.ofString());


         // NOW THAT WE HAVE THE HTTP RESPONSE
         // Parse http response into JSONObject
         // Extract the category of the first joke

        JSONObject obj = new JSONObject(response.body().toString());
        JSONArray jokesArray = (JSONArray) obj.get("jokes");
        JSONObject firstJoke = (JSONObject) jokesArray.get(0);
        String firstJokeCategory = (String) firstJoke.get("category");

        System.out.println("Categroy of first joke: " + firstJokeCategory);
    }
}