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
            String description;
            String requestUrl = baseURL + movieID + "?api_key=" + apiKey;
            System.out.println(requestUrl);
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse response;
            
            boolean success = false;
            JSONObject obj = new JSONObject();
            
            //retrieve movie by id until it works
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
            
            //Create datamap to return
            Map<String, String> data = new HashMap<>();
            data.put("description", getSanitizedDescription(obj));
            data.put("posterUrl", imageBaseURL + size + (String) obj.get("poster_path"));
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
    
    /*
    * replaces identifying information in the description with censored info
    * @param String description
    * @param JSONObject obj
    */
    public String getSanitizedDescription(JSONObject obj) throws JSONException{
        //first get title to major words then replace
        String description, title;
        boolean cleanTitleEmpty = true;
        
        description = (String) obj.get("overview");
        title = (String) obj.get("title");
        
        //clean title
        String[] titleArray = title.split(" ");
        removePunctuation(titleArray);
        trimSmallWords(titleArray);
        if (isStringArrayEmpty(titleArray))
            return description;
        
        String[] descArray = description.split(" ");
        for(String t : titleArray){
            for (int i = 0; i < descArray.length; i++) {
                    descArray[i] = descArray[i].replaceAll("\\p{Punct}", "");
                if (descArray[i].toLowerCase().equals(t.toLowerCase())) {
                    descArray[i] = "[CENSORED]";
                }
            }
        }
        
        String copy = "";
        for(String i : descArray)
            copy += i + " ";
        return copy.trim();
    }
    
    //delete punctuation from elements in String array using regex
    public String[] removePunctuation(String[] array){
        for(int i = 0; i < array.length; i++){
            array[i] = array[i].replaceAll("\\p{Punct}", "");
        }
        return array;
    }
    
    //Get rid of small words then sort array
    public String[] trimSmallWords(String[] array){
    for (int i = 0; i < array.length; i++)
        array[i] = array[i].replaceAll("\\b\\w{1,4}\\b\\s?", "");
        
    return array;
}
    
    //returns true if array has only empty elements
    public boolean isStringArrayEmpty(String[] array){
        for (String i : array)
            if(i.length() > 0)
                return false;
        return true;
    }
}
