/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import db.DataFactory;
import db.DataObject;
import db.DataStoreAdapter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//Get Rid of hardcoded password
public class User extends DataObject {
    
    private String username;
    private String password;
    public static String TABLE = "src/storage/users.csv";

    
    public User(){}

   

    public String getUsername() {
        return username;
    }
    
    public Map<String, String> loadByUsername(){
                
       if(this.username == null)
           return null;
       Map<String, String> keyValue = new HashMap<>();
       keyValue.put("username", username);
       
       Map<String, String> userInfo = DataStoreAdapter.readObject(keyValue, User.TABLE);
       return userInfo;
       
    }
    
    public Boolean save(){
        
        Map<String, String> userProperties = new HashMap<>();
        Map<String, String> parentProperties = new HashMap<>();
        if(this.username != null)
            userProperties.put("username", this.username);
        if(this.password != null)
            userProperties.put("password", this.password);
        parentProperties = super.createMap();
        userProperties.putAll(parentProperties);
        
        if (this.id == 0) {
            try {
                return DataStoreAdapter.createObject(userProperties, User.TABLE);
            } catch (Exception e) {
            }
        } else {/*
            try {
                // This is an exisitng object in the database, just update the object.
                return DataStoreAdapter.updateObject(this);
            } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchFieldException ex) {
            }*/
        }
        return false;
    }
    
    
    
    
        

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    } 
    
  
}
