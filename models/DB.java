/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.opencsv.CSVWriter;
import java.io.FileWriter;

/**
 *
 * @author andyk
 */
public class DB {
    
    public static String USER_TABLE = "src/database/Users.csv";
    
    //Only works for keys that are strings at the moment
    //Assumes that key is in the first column
    public static <keyType> boolean keyExists(String _table, keyType _query) throws FileNotFoundException, IOException{
        
        String line = "";
        BufferedReader br = new BufferedReader(new FileReader(_table));
        while((line = br.readLine()) != null){
            String[] row = line.split(",");
            if(row[0].equals(_query))
                return true;
        }
        return false;
    }
    
    public static boolean addRow(String _table, Map<String, String> dataMap) throws FileNotFoundException, IOException, Exception{
        
        String line = "";
        String[] columnNames = new String[0];
        BufferedReader br = new BufferedReader(new FileReader(_table));
        
        if((line = br.readLine()) != null)
            columnNames = line.split(",");
        
        //build new row, making sure that the given keys match the actual keys in the table
        String[] newRow = new String[columnNames.length];
        for(int i = 0; i < columnNames.length; i++){
            String tableKey = columnNames[i];
            System.out.println(tableKey);
            System.out.println(dataMap.containsKey(tableKey));
            if(dataMap.containsKey(tableKey))
                newRow[i] = dataMap.get(tableKey);
            else
                throw new Exception();
        }
        br.close();
        
        //write row to file
        CSVWriter writer = new CSVWriter(new FileWriter(_table, true));
        try{
            writer.writeNext(newRow, false);
        }catch(Exception e){
        }finally{
            writer.close();
        }
        return false;
        
    }
    
    
    
}
