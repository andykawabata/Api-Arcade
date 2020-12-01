package API;
/*
*Last updated on 11/30/20
*
*Implements the MovieApiInterface and returns an omdb Translator using such methods.
*
*Contributing authors
*@author Andy
*@author Francisco
*/
import java.util.Map;

public class MovieApiAdapter implements MovieApiInterface {

    public static final MovieApiInterface movieApiTranslator = new OmdbTranslator();

    //=================  GETTERS ===============//

    @Override
    public Map<String, String> getPosterTitleDescriptionById(int _id) throws Exception {

        return movieApiTranslator.getPosterTitleDescriptionById(_id);
    }

    @Override
    public String[] getPostersOfSimilarById(int _id, int _numPosters) throws Exception {

        return movieApiTranslator.getPostersOfSimilarById(_id, _numPosters);
    }

}
