/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import db.DataObject;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author andyk
 */
public class User extends DataObject {
    
    private String username;
    private String password = "abc123";
    public static String USER_TABLE = "src/storage/users.csv";

    
    public User(){}

    /*
    //make better exception
    public boolean exists() throws Exception{
        try{
            return DB.keyExists(DB.USER_TABLE, this.username);
        }catch(Exception e){
            throw new Exception();
        }
            
    }
    
    public void addNewUser(){
        Map<String, String> userInfo = new HashMap<>();
        
        //keys in map should not be hard coded
        //keys in map must match column names in table
        userInfo.put("username", this.username);
        userInfo.put("password",this.password);
        try{
            DB.addRow(DB.USER_TABLE, userInfo);
        }catch(Exception e){
            System.out.println(e);
        }
        
    }
    
    public boolean passwordsMatch(){
        //if(this.password.equals(this.confirmPassword))
          //  return true;
        return false;
    }
    /**
     * user DB's "query" method to log user in
     * creates session file??
     */
    
   

    public String getUsername() {
        return username;
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
