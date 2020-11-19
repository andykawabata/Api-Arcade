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
import org.json.JSONArray;

public interface TriviaApiInterface {

    //=================  GETTERS ===============//


    public JSONArray getGameQuestions() throws Exception;

    public String getCurrentQuestion(int _counter) throws Exception;

    public String getCurrentAnswer(int _counter) throws Exception;
}
