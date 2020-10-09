/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 
 */
public class DataObject extends DataFactory {
    
    protected final String dataTable = "";
    protected int id = 0;
    protected String uuid;
    
    public DataObject() {
        this.setUuid(generateUuid());
    }
    
    public Boolean save() throws Exception {
        // Has this object already been created?
        if (this.id == 0) {
            try {
                return DataStoreAdapter.createObject(this);
            } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchFieldException ex) {

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
    
    
    
    
    
    
    
    public String generateUuid(){
        return "sg7e4s2";
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
