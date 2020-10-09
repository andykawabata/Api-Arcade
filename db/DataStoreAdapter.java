/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
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
    
    public static Boolean createObject(DataObject obj) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, Exception {
        // Send name-value pairs to the connector class. This class should
        // return a generated id number.
        int id = connector.createObject(obj.getProperties(), User.TABLE);
        obj.setId(id);
        return (id != 0);
    }
    
    public static HashMap<String, Object> readObject(Map <String, Object> _map, String _table) {
        return connector.readObject(_map, _table);
    }
    
    public static Boolean updateObject(DataObject obj, String _table)throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, Exception {
        /*Use CSVConnector to change info
          What are the strings in Map<String, String>
          If the second one is the table (_table aka "src/storage/users.csv") 
          Then why is there a _table
        */
        return CSVConnector.updateObject((new Map<"password", _table>), obj.getUUID(), new String("Fix This Line"));
            
    }
    
    
    
}
