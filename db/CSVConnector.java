/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import com.opencsv.CSVWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andyk
 */
public class CSVConnector implements DBConnectorInterface {
     /*
    *Takes in key and value (ex "username": "user123")
    *Returns all key value pairs in row
    *ex:
    *{
    *id: 3
    *uuid: 12343
    *username: user123
    *password: 123445
    */
 
     public HashMap<String, Object> readObject(Map<String,Object> _keyValuePair, String _table) {
         
         Set keySet = _keyValuePair.keySet();
         String key = keySet.toString();
         
         
         //DO SOME STUFF
         
         return new HashMap<String, Object>();
         
     }
    

    @Override
    public int createObject(Map<String, String> _keyValuePairs, String _table) throws IOException{
       
      
        String newId = getNextId(_table);
        _keyValuePairs.put("id", newId);
        String[] columnNames = getColumnNames(_table);
        String[] newRow = buildRow(columnNames, _keyValuePairs);
   
        //write row to file
        CSVWriter writer = new CSVWriter(new FileWriter(_table, true));
        try{
            writer.writeNext(newRow, false);
        }catch(Exception e){
            newId = "0";
        }finally{
            writer.close();
        }
        return Integer.valueOf(newId);
        
    }
    
    private String[] getColumnNames(String _table) throws FileNotFoundException, IOException{
        String line = "";
        String[] columnNames = new String[0];
        BufferedReader br = new BufferedReader(new FileReader(_table));
        if((line = br.readLine()) != null)
            columnNames = line.split(",");
        br.close();
        return columnNames;
    }
    
    private String[] buildRow(String[] _columnNames, Map<String, String> _keyValuePairs) {
        String[] newRow = new String[_columnNames.length];
        for(int i = 0; i < _columnNames.length; i++){
            newRow[i] = _keyValuePairs.get(_columnNames[i]);
        }
        return newRow;
    }
    
    
    /**
     * USE buffered reader to iterate over given table
     * @param _table - path to the csv table
     * @return  String representation of next user id number
     * for example if there are 10 entries in the table it should return "11"
     */
    private String getNextId(String _table) throws FileNotFoundException {
        int count = 1;
        Scanner br = new Scanner(new FileReader(_table));
        while(br.hasNextLine()){
            count++;
        }
        String nextId = Integer.toString(count);
        return nextId;
    }

   
    @Override
    public Boolean updateObject(Map<String, String> _keyValuePairs, String _uuid, String _table) {
        return true;
    }

    @Override
    public Boolean deleteObject(String uuid) {
        return true;
    }

    

    
    
}
