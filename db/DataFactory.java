/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andyk
 */
public class DataFactory {
    
    public Map getProperties() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
       
        Map<String,String> pairs = new HashMap<>();
        Class<?> c = this.getClass();
        
        Field[] fields = c.getDeclaredFields();
            for(Field f : fields) {
                String fieldName = f.getName();
                int mods = f.getModifiers();
                if (Modifier.isPublic(mods) || Modifier.isStatic(mods)|| Modifier.isFinal(mods)) {
                    
                    continue;
                }
                if (f.getType() == boolean.class) {
      
                    String fieldValue = (f.get(this).equals(true) ? "1" : "0");
                    pairs.put(fieldName, fieldValue);
                } else {
                    f.setAccessible(true);
                    String fieldValue = f.get(this).toString();
                    pairs.put(fieldName, fieldValue);
                }
            }
        c = c.getSuperclass();
        
        fields = c.getDeclaredFields();
        for(Field f : fields) {
            String fieldName = f.getName();
            int mods = f.getModifiers();
            if (Modifier.isPublic(mods) || Modifier.isStatic(mods)|| Modifier.isFinal(mods)) {
                continue;
            }
            if (f.getType() == boolean.class) {

                String fieldValue = (f.get(this).equals(true) ? "1" : "0");
                pairs.put(fieldName, fieldValue);
            } else {
                f.setAccessible(true);
                String fieldValue = f.get(this).toString();
                pairs.put(fieldName, fieldValue);
            }
        }
        
        //print pairs
        /*
        for (String name: pairs.keySet()){
            String key = name.toString();
            String value = pairs.get(name).toString();  
            System.out.println(key + " " + value);  
}       */
           
        return pairs;
    }
    
    
    public static HashMap<String, Object> loadByCondition(HashMap<String, Object> _data, String _table) {
        try {
            // Given an empty object, this method fills this object with data from the database.
            return DataStoreAdapter.readObject(_data, _table);
        } catch (Exception e){
        }
        return null;

    }
    
    
    
}
