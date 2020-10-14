/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import org.json.JSONObject;

/**
 *
 * @author Francisco
 */
public class TriviaApiAdapter implements TriviaApiInterface {

    public static final TriviaApiInterface triviaApiTranslator = new OtdbTranslator();

    @Override
    public JSONObject ResonseBody() throws Exception {
        return triviaApiTranslator.ResonseBody();
    }

    @Override
    public String Question(JSONObject obj) throws Exception {
        return triviaApiTranslator.Question(obj);
    }

    @Override
    public String Answer(JSONObject obj) throws Exception {
        return triviaApiTranslator.Answer(obj);
    }
    
}
