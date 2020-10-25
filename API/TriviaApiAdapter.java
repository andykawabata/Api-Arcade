package API;
/*
*Last updated on 10/24/20
*
*This is an adapter class that has an otdb translator object and uses the
*trivia interface to return a JSONObject, the answer, or the question
*
*Contributing authors
*@author Francisco
*@author Ryan
*/
import org.json.JSONObject;

public class TriviaApiAdapter implements TriviaApiInterface {

    public static final TriviaApiInterface triviaApiTranslator = new OtdbTranslator();

    //=================  GETTERS ===============//

    @Override
    public JSONObject getResponseBody() throws Exception {
        return triviaApiTranslator.getResponseBody();
    }

    @Override
    public String getQuestion(JSONObject obj) throws Exception {
        return triviaApiTranslator.getQuestion(obj);
    }

    @Override
    public String getAnswer(JSONObject obj) throws Exception {
        return triviaApiTranslator.getAnswer(obj);
    }
}
