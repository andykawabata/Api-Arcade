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
import org.json.JSONObject;

/**
 *
 * @author andyk
 */
public class OmdbTranslator implements movieApiInterface {
    
    String baseURL = "https://api.themoviedb.org/3/movie/";
    String apiKey = "3ee7fedb5506448980b60224316cdcd0";
    
    String imageBaseURL = "https://image.tmdb.org/t/p/";
    String size = "w154";

    @Override
    public Map<String, String> getPosterAndDescriptionById(int id) throws Exception {
          
            String movieID = String.valueOf(id);
            String description = "";
            
            String requestUrl = baseURL + movieID + "?api_key=" + apiKey;
            
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse response;
        
            response = httpClient.send(HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(requestUrl))
                    .build(), HttpResponse.BodyHandlers.ofString());
            JSONObject obj = new JSONObject(response.body().toString());
            String overview = (String) obj.get("overview");
            String posterPath = (String) obj.get("poster_path");
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
     */

    @Override
    public String[] getPostersOfSimilarById(int id, int numPosters) {
        
        String[] posters = {"www.picture.com", "www.dot.com"};
        return posters;
    }
    
    
    
}
