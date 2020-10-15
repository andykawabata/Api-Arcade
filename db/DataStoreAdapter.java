/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

/**
 *
 * @author andyk
 */
public class DataStoreAdapter {
    
    private static final DBConnectorInterface connector = new CSVConnector();
    
    public static Boolean createObject(Map<String, String> map, String _table) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, Exception {
        // Send name-value pairs to the connector class. This class should
        // return a generated id number.
        int id = connector.createObject(map, _table);
        String idString = String.valueOf(id);
        map.put("id", idString);
        return (id != 0);
    }
    
    public static List<Map<String, String>> readObject(Map<String, String> _map, String _table) throws Exception {
        
        return connector.readObject(_map, _table);
    }

    
    
    
}
