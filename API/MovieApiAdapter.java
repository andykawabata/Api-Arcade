package API;
/*
*Last updated on 10/24/20
*
*Implements the MovieApiInterface and returns an omdb Translator using such methods.
*
*Contributing authors
*@author Andy
*/
import java.util.Map;

public class MovieApiAdapter implements MovieApiInterface {

    public static final MovieApiInterface movieApiTranslator = new OmdbTranslator();

    @Override
    public Map<String, String> getPosterAndDescriptionById(int id) throws Exception {

        return movieApiTranslator.getPosterAndDescriptionById(id);
    }

    @Override
    public String[] getPostersOfSimilarById(int id, int numPosters) throws Exception {

        return movieApiTranslator.getPostersOfSimilarById(id, numPosters);
    }

}
