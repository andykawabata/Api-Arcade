package API;

import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andyk
 */
public interface movieApiInterface {
    
    public Map<String, String> getPosterAndDescriptionById(int id) throws Exception;
    
    public String[] getPostersOfSimilarById(int id, int numPosters)throws Exception;
    
    
    
}
