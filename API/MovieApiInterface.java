package API;
/*
*Last updated on 10/24/20
*
*An interface that allows a given adapter to get a poster and description in a map of
*two strings by ID. Also allows getting similar posters to a specific movie by ID.
*
*
*Contributing authors
*@author Andy
*/
import java.util.Map;

public interface MovieApiInterface {

    public Map<String, String> getPosterTitleDescriptionById(int id) throws Exception;

    public String[] getPostersOfSimilarById(int id, int numPosters)throws Exception;


}
