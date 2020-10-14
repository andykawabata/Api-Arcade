/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 *
 * @author Francisco
 */
public interface TriviaApiInterface {

    public JSONObject ResonseBody() throws Exception;

    public String Question(JSONObject obj) throws Exception;

    public String Answer(JSONObject obj) throws Exception;
    
}
