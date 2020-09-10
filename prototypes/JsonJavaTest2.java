package http.request.test;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class JsonJavaTest2 {

    public static void main(String[] args) throws IOException, InterruptedException, JSONException {
    
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse response = httpClient.send(HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://sv443.net/jokeapi/v2/joke/Any?amount=10"))  
                .build(), HttpResponse.BodyHandlers.ofString());
         
         // NOW THAT WE HAVE THE HTTP RESPONSE
         // Parse http response into JSONObject
         // loop through each of the 10 jokes in the array
         // if the joke is a "twopart" joke, print the values for "setup" and "delivery"
         // if the joke is not a "twopart" joke, the setup and delivery fields don't exists
        
        JSONObject obj = new JSONObject(response.body().toString());
        JSONArray jokesArray = (JSONArray) obj.get("jokes");
        
        for(int i = 0; i < jokesArray.length(); i++){
            JSONObject joke = (JSONObject) jokesArray.get(i);
            String category = (String) joke.get("category");
                    
            if(category.equals("twopart")){
                String setup = (String) joke.get("setup");
                String delivery = (String) joke.get("delivery");
                System.out.println(setup);
                System.out.println(delivery);
                System.out.println();
            }
        }
    } 
}