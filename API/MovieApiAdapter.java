/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import java.util.Map;

/**
 *
 * @author andyk
 */
public class MovieApiAdapter implements MovieApiInterface {
    
    public static final MovieApiInterface movieApiTranslator = new OmdbTranslator();
    
    /**
     * 
     * @param id
     * @return HashMap
     * {
     *  description: String,
     *  posterUrl: String
     * }
     * @throws Exception 
     */
    public Map<String, String> getPosterAndDescriptionById(int id) throws Exception {
        
        return movieApiTranslator.getPosterAndDescriptionById(id);
        
    }
    
    
    /**
     * 
     * @param id
     * @param numPosters
     * @return String[]: list of poster image URLs
     * @throws Exception 
     */
    public String[] getPostersOfSimilarById(int id, int numPosters) throws Exception {
        
        return movieApiTranslator.getPostersOfSimilarById(id, numPosters);
    }
    
}
