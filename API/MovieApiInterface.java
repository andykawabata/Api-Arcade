package API;

/*
*Last updated on 11/30/20
*
*An interface that allows a given adapter to get a poster and description in a map of
*two strings by ID. Also allows getting similar posters to a specific movie by ID.
*
*
*Contributing authors
*@author Andy
*@author Francisco
*/
import java.util.Map;

public interface MovieApiInterface {

    //=================  GETTERS ===============//

    public String[] getPostersOfSimilarById(int id, int numPosters) throws Exception;

    public Map<String, String> getPosterTitleDescriptionById(int _id) throws Exception;

}
