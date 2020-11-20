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
import org.json.JSONArray;

public class TriviaApiAdapter implements TriviaApiInterface {

    public static final TriviaApiInterface triviaApiTranslator = new OtdbTranslator();

    //=================  GETTERS ===============//

    @Override
    public JSONArray getGameQuestions() throws Exception {
        return triviaApiTranslator.getGameQuestions();
    }

    @Override
    public String getCurrentQuestion(int _counter) throws Exception {
        return triviaApiTranslator.getCurrentQuestion(_counter);
    }

    @Override
    public String getCurrentAnswer(int _counter) throws Exception {
        return triviaApiTranslator.getCurrentAnswer(_counter);
    }
}
