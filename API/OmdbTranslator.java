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
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author andyk
 */
public class OmdbTranslator implements MovieApiInterface {
    
    String baseURL = "https://api.themoviedb.org/3/movie/";
    String apiKey = "3ee7fedb5506448980b60224316cdcd0";
    
    String imageBaseURL = "https://image.tmdb.org/t/p/";
    String size = "w154";

    @Override
    public Map<String, String> getPosterAndDescriptionById(int id) throws Exception {
          
            String movieID = String.valueOf(id);
            String description = null;
            String posterPath = null;
            String requestUrl = baseURL + movieID + "?api_key=" + apiKey;
            System.out.println(requestUrl);
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse response;
            
            boolean success = false;
            JSONObject obj = null;
            
            while(!success){
                response = httpClient.send(HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(requestUrl))
                    .build(), HttpResponse.BodyHandlers.ofString());
                obj = new JSONObject(response.body().toString());
                //INCREMENT MOVIEID IN CASES THIS MOVIE DIDN'T HAVE DESCRPTION OR POSTER
                success = !obj.has("success");
                movieID = String.valueOf(Integer.valueOf(movieID) + 1);
                requestUrl = baseURL + movieID + "?api_key=" + apiKey;
            }
            description = (String) obj.get("overview");
            posterPath = (String) obj.get("poster_path");
            String posterUrl = imageBaseURL + size + posterPath;
            
            Map<String, String> data = new HashMap<>();
            data.put("description", description);
            data.put("posterUrl", posterUrl);
            
            return data;
            
    }
    /**
     * Returns and array of URL's that link to images of posters
     * whose movies are similar to that of the given ID
     * @param id
     * @param numPosters
     * @return 
     * @throws java.io.IOException 
     */
    //STUB
    @Override
    public String[] getPostersOfSimilarById(int id, int numPosters) throws IOException, InterruptedException, JSONException {
        
        String movieID = String.valueOf(id);
        String[] posterUrls = new String[numPosters];
        String requestUrl = baseURL + movieID + "/similar" + "?api_key=" + apiKey;
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse response;
        
        boolean success = false;
        JSONObject obj = null;
        while(!success){
            response = httpClient.send(HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(requestUrl))
                        .build(), HttpResponse.BodyHandlers.ofString());

            obj = new JSONObject(response.body().toString());
            success = !obj.has("success");
            movieID = String.valueOf(Integer.valueOf(movieID) + 1);
            requestUrl = baseURL + movieID + "/similar" + "?api_key=" + apiKey;
        }
        JSONArray results = (JSONArray) obj.get("results");
        
        for(int i=0; i < posterUrls.length; i++){
            JSONObject result = (JSONObject) results.get(i);
            String posterPath = (String) result.get("poster_path");
            String posterURL = imageBaseURL + size + posterPath;
            posterUrls[i] = posterURL;
        }
        return posterUrls;
    }
    
}
