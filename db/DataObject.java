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
import java.util.UUID;

/**
 *
 * @author 
 */
public class DataObject {
    
    protected final String dataTable = "";
    protected int id = 0;
    protected String uuid;
    
  
    public DataObject() {
        this.setUuid(generateUuid());
    }
 
    //Builds map of instance's current properties
    public Map<String, String> createMap(){
        Map<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(this.id));
        if(uuid != null)
            map.put("uuid", uuid);
        return map;
    }
    
    public String generateUuid(){
        return UUID.randomUUID().toString();
    }
    
    // ================================ GETTERS ====================================



    public String getUuid() {
        return this.uuid;
    }

    public int getId() {
        return this.id;
    }

// ================================ SETTERS ====================================


    public void setUuid(String _uuid) {
        this.uuid = _uuid;
    }

    public void setId(int _id) {
        this.id = _id;
    }
    
}
