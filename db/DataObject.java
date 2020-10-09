/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.UUID;


/**
 *
 * @author 
 */
public class DataObject extends DataFactory {
    
    protected final String dataTable = "";
    protected static String TABLE = "";
    protected int id = 0;
    protected UUID uuid;
    
    public DataObject() {
        this.setUuid();
        this.setId(-1);
    }
    
    public Boolean save() throws Exception {
        // Has this object already been created?
        if (this.id == 0) {
            try {
                return DataStoreAdapter.createObject(this);
            } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchFieldException ex) {

            }
        } else {
            try {
                // This is an exisitng object in the database, just update the object.
                return DataStoreAdapter.updateObject(this, this.TABLE);
            } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchFieldException ex) {
                
            }
        }
        return false;
    }
    // ================================ GETTERS ====================================



    public String getUuid() {
        return this.uuid.toString();
    }

    public int getId() {
        return this.id;
    }

// ================================ SETTERS ====================================


    public void setUuid() {
        uuid = UUID.randomUUID();
    }

    public void setId(int _id) {
        this.id = _id;
    }
    
}
