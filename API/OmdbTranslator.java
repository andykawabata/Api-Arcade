package API;
/*
*Last updated on 10/24/20
*
*A translator that implements the Movie Api Interface.
*
*@getPostersOfSimilarById
*returns and array of URL's that link to images of posters
*whose movies are similar to that of the given ID
*
*@getSanitizedDescription
*replaces identifying information in the description with censored info
*
*@getPosterAndDescriptionById
*finds movie with a description and a poster and returns them in a map
*
*@generateValidJsonObject
*finds a JSON object with a description and a poster and possibly is just similar
*
*Contributing authors
*@author Andy
*@author Ryan
*/
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OmdbTranslator implements MovieApiInterface {

    String baseURL = "https://api.themoviedb.org/3/movie/";
    String apiKey = "3ee7fedb5506448980b60224316cdcd0";
    String imageBaseURL = "https://image.tmdb.org/t/p/";
    String size = "w154";



    //delete punctuation from elements in String array using regex
    public String[] removePunctuation(String[] array) {
        for(int i = 0; i < array.length; i++){
            array[i] = array[i].replaceAll("\\p{Punct}", "");
        }
        return array;
    }

    //Get rid of small words then sort array
    public String[] trimSmallWords(String[] array) {
    for (int i = 0; i < array.length; i++)
        array[i] = array[i].replaceAll("\\b\\w{1,4}\\b\\s?", "");

    return array;
}

    //returns true if array has only empty elements
    public boolean isStringArrayEmpty(String[] array) {
        for (String i : array)
            if(i.length() > 0)
                return false;
        return true;
    }

    private JSONObject generateValidJsonObject(HttpClient httpClient, String requestUrl, String movieID, boolean similar) throws IOException, InterruptedException, JSONException {
        HttpResponse response;
        boolean success = false;
        JSONObject obj = new JSONObject();

        while(!success){
                response = httpClient.send(HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(requestUrl))
                    .build(), HttpResponse.BodyHandlers.ofString());
                obj = new JSONObject(response.body().toString());
                //Increment movieID in case this movie didn't have description or poster
                success = !obj.has("success");
                movieID = String.valueOf(Integer.valueOf(movieID) + 1);

                //Determine whether we need that movie's URL or a similar movie's URL
                if(similar)
                    requestUrl = baseURL + movieID + "/similar" + "?api_key=" + apiKey;
                else
                    requestUrl = baseURL + movieID + "?api_key=" + apiKey;
            }
        return obj;
    }

                        //=================  GETTERS ===============//
    @Override
    public Map<String, String> getPosterAndDescriptionById(int id) throws Exception {

            String movieID = String.valueOf(id);
            String requestUrl = baseURL + movieID + "?api_key=" + apiKey;
            System.out.println(requestUrl);
            HttpClient httpClient = HttpClient.newHttpClient();
            JSONObject obj;

            obj = generateValidJsonObject(httpClient, requestUrl, movieID, false);

            //Create datamap to return
            Map<String, String> data = new HashMap<>();
            data.put("description", getSanitizedDescription(obj));
            data.put("posterUrl", imageBaseURL + size + obj.getString("poster_path"));
            return data;
    }

    @Override
    public String[] getPostersOfSimilarById(int id, int numPosters) throws IOException, InterruptedException, JSONException {

        String movieID = String.valueOf(id);
        String[] posterUrls = new String[numPosters];
        String requestUrl = baseURL + movieID + "/similar" + "?api_key=" + apiKey;
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse response;
        boolean success = false;
        JSONObject obj;

        obj = generateValidJsonObject(httpClient, requestUrl, movieID, true);
        JSONArray results = obj.getJSONArray("results");

        for(int i=0; i < posterUrls.length; i++){
            JSONObject result = results.getJSONObject(i);
            String posterPath = result.getString("poster_path");
            String posterURL = imageBaseURL + size + posterPath;
            posterUrls[i] = posterURL;
        }
        return posterUrls;
    }

    public String getSanitizedDescription(JSONObject obj) throws JSONException {
        //first get title to major words then replace
        String description, title;
        boolean cleanTitleEmpty = true;

        description = (String) obj.get("overview");
        title = (String) obj.get("title");

        //clean title to remove punctuation and small words (the, a, as)
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
}
