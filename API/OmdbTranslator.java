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
    public String[] removePunctuation(String[] _array) {
        for(int i = 0; i < _array.length; i++){
            _array[i] = _array[i].replaceAll("\\p{Punct}", "");
        }
        return _array;
    }

    //Get rid of small words then sort array
    public String[] trimSmallWords(String[] _array) {
    for (int i = 0; i < _array.length; i++)
        _array[i] = _array[i].replaceAll("\\b\\w{1,4}\\b\\s?", "");

    return _array;
}

    //returns true if array has only empty elements
    public boolean isStringArrayEmpty(String[] _array) {
        for (String i : _array)
            if(i.length() > 0)
                return false;
        return true;
    }

    @Override
    public Map<String, String> getPosterTitleDescriptionById(int _id) throws Exception {

        String movieID = String.valueOf(_id);
        String requestUrl = baseURL + movieID + "?api_key=" + apiKey;
        JSONObject obj;

        obj = getResponse(requestUrl, movieID, false);

        String overview = (String) obj.get("overview");
        String imgPath = (String) obj.get("poster_path");
        String title = (String) obj.get("title");
        String imgUrl = imageBaseURL + size + imgPath;

        //Create datamap to return
        Map<String, String> data = new HashMap<>();
        data.put("description", overview);
        data.put("posterUrl", imgUrl);
        data.put("title", title);
        return data;
    }

    @Override
    public String[] getPostersOfSimilarById(int _id, int _numPosters) throws IOException, InterruptedException, JSONException {

        String movieID = String.valueOf(_id);
        String[] posterUrls = new String[_numPosters];
        String requestUrl = baseURL + movieID + "/similar" + "?api_key=" + apiKey;
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse response;
        boolean success = false;
        JSONObject obj;

        obj = getResponse(requestUrl, movieID, true);
        JSONArray results = obj.getJSONArray("results");

        for(int i=0; i < posterUrls.length; i++){
            JSONObject result = results.getJSONObject(i);
            String posterPath = result.getString("poster_path");
            String posterURL = imageBaseURL + size + posterPath;
            posterUrls[i] = posterURL;
        }
        return posterUrls;
    }

    public String sanitizedDescription(String _title, String _description) throws JSONException {
        //first get title to major words then replace

        boolean cleanTitleEmpty = true;

        //clean title to remove punctuation and small words (the, a, as)
        String[] titleArray = _title.split(" ");
        removePunctuation(titleArray);
        trimSmallWords(titleArray);
        if (isStringArrayEmpty(titleArray))
            return _description;

        String[] descArray = _description.split(" ");
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

    private JSONObject getResponse(String _requestUrl, String _movieID, boolean _similar) throws IOException, InterruptedException, JSONException {

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse response;
        boolean success = false;
        JSONObject obj = new JSONObject();

        while(!success){
                response = httpClient.send(HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(_requestUrl))
                    .build(), HttpResponse.BodyHandlers.ofString());
                obj = new JSONObject(response.body().toString());
                //Increment movieID in case this movie didn't have description or poster
                success = !obj.has("success");
                _movieID = String.valueOf(Integer.valueOf(_movieID) + 1);

                //Determine whether we need that movie's URL or a similar movie's URL
                if(_similar)
                    _requestUrl = baseURL + _movieID + "/similar" + "?api_key=" + apiKey;
                else
                    _requestUrl = baseURL + _movieID + "?api_key=" + apiKey;
            }
        return obj;
    }
}
