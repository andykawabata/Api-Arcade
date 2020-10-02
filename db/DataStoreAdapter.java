/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.lang.reflect.InvocationTargetException;
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
        int id = connector.createObject(obj.getProperties(), User.USER_TABLE);
        obj.setId(id);
        return (id != 0);
    }
    
}
