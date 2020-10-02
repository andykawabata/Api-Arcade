/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import db.DataObject;
import java.util.HashMap;
import java.util.Map;

//Get Rid of hardcoded password
public class User extends DataObject {
    
    private String username;
    private String password = "abc123";
    public static String USER_TABLE = "src/storage/users.csv";

    
    public User(){}


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
