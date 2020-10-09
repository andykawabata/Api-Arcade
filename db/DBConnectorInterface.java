package db;

import java.util.HashMap;
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
public interface DBConnectorInterface {
    
    public abstract int createObject(Map<String,String> _keyValuePairs, String _table) throws Exception;

    public abstract HashMap<String, Object> readObject(Map<String,Object> _keyValuePairs, String _table);

    public abstract Boolean updateObject(Map<String,String> _keyValuePairs, String _uuid, String _table);

    public abstract Boolean deleteObject(String uuid);
}
