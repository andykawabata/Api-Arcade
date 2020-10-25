package API;
/*
*Last updated on 10/24/20
*
*This is an interface that provides methods that obtain a JSON response body,
* a question object, and an answer.
*
*Contributing authors
*@author Francisco
*@author Ryan
*/
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public interface TriviaApiInterface {

    //=================  GETTERS ===============//

    public JSONObject getResponseBody() throws Exception;

    public String getQuestion(JSONObject obj) throws Exception;

    public String getAnswer(JSONObject obj) throws Exception;
}
