/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    
    ///////////////////////////////////////////////////////////////////////////
    //    CRUD METHODS
    /////////////////////////////////////////////////////////////////////////
    
    
     /*
    *Takes in query as one or more key value pairs (ex "username": "user123")
    *Returns maps representing the rows that match the query
    *Returns null if no rows match query
    *for example:
    *{
    *id: 3
    *uuid: 12343
    *username: user123
    *password: 123445
    */
     public List<Map<String, String>> readObject(Map<String, String> _keyValuePairs, String _table)throws Exception{
         
         //INITIALIZE LIST FOR ROWS THAT MATCH QUERY
        ArrayList<String[]> matchingRows = new ArrayList<>();
        
        //EXTRACT THE FIRST CONDITION FROM QUERY AND REMOVE FROM MAP
        String firstKey = (String) _keyValuePairs.keySet().toArray()[0];
        String firstValue = _keyValuePairs.get(firstKey);
        _keyValuePairs.remove(firstKey);
        
        
        
        //GET ROWS THAT MATCH FIRST CONDITION AND ADD TO ARRAYLIST
        String[] columnNames = getColumnNames(_table);
        int keyIndex = indexOf(columnNames, firstKey);
        if(keyIndex == -1){
            //KEY NOT FOUND IN COLUMN NAMES
            return null;
        }
        BufferedReader br = new BufferedReader(new FileReader(_table));
        String line = "";
        String[] currentRow;
        br.readLine(); //skip first line (column names)
        while((line = br.readLine()) != null) {
            currentRow = line.split(",");
            if(currentRow[keyIndex].equals(firstValue)) //if row matches query condition
                matchingRows.add(currentRow);   
        }
        if(matchingRows.size() == 0) //if no rows matched query
            return null;
        
        
        
        //IF THERE IS MORE THAN ONE QUERY CONDITION (MORE THAN ONE KEY VALUE PAIR)
        //REMOVE ROWS THAT DON'T MATCH THE ADDITIONAL QUERYS
        for (Map.Entry<String, String> entry : _keyValuePairs.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            keyIndex = indexOf(columnNames, key);
            //Iterate over array list
            ArrayList<Integer> indiciesToRemove = new ArrayList<>();
            int index = 0;
            for(String[] row: matchingRows){
                if(row[keyIndex] != value){
                    indiciesToRemove.add(index);
                    index++;
                }
            }
            for(Integer idx : indiciesToRemove){
                matchingRows.remove(idx);
            }
         }
        
        
        
        
        //CREATE MAPS THAT REPRESENT THE MATCHING ROWS AND PUT THEM INTO ARRAY LIST
        List<Map<String,String>> maps = new ArrayList<>();
        for(String[] row : matchingRows){
            Map<String, String> map = new HashMap<>();
            int index = 0;
            for(String value : row){
                String key = columnNames[index];
                map.put(key, value);
                index++;
            }
            maps.add(map);
        }
        return maps;
         
     }
     
 

    @Override
    public int createObject(Map<String, String> _keyValuePairs, String _table) throws IOException{
       
      
        String newId = getNextId(_table);  //find value that the new id should be
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
    
     @Override
    public Boolean updateObject(Map<String, String> _keyValuePairs, String _uuid, String _table) {
        return true;
    }

    @Override
    public Boolean deleteObject(String uuid) {
        return true;
    }
    
    
    
    
    /////////////////////////////////////////////////////////////////////////////
    //    HELPER METHODS
    ///////////////////////////////////////////////////////////////////////////
    
    
    
    
    
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
    private String getNextId(String _table) throws FileNotFoundException, IOException {
        int count = 0;
        BufferedReader sr = new BufferedReader(new FileReader(_table));
        String line;
        while((line = sr.readLine())!= null){
             count++;
        }
        String nextId = Integer.toString(count);
        return nextId;
    }

    private static int indexOf(String[] arr, String targetString){
        for(int i = 0; i<arr.length; i++){
            if(arr[i].equals(targetString))
                return i;
        }
        return -1;
    }
   
   

    

    
    
}
