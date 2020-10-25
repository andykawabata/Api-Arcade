package db;
/*
*Last updated on 10/25/20
*
*implements CRUD methods on CSV files User and Score based on their
*table path string.
*
*Work In Progress
*
*Contributing authors
*@author Andy
*@author Ryan
*/
import com.opencsv.CSVWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class CSVConnector implements DBConnectorInterface {

    ///////////////////////////////////////////////////////////////////////////
    //    CRUD METHODS
    /////////////////////////////////////////////////////////////////////////


     /*
    *Takes in query as one or more key value pairs (ex "username": "user123")
    *Returns maps representing the rows that match the query
    *Returns null if no rows match query
    *for example {
    *id: 1
    *uuid: sg7e4s2
    *username: user123
    *password: 123445
    */
     @Override
     public Map<String, String> readObject(Map<String, String> _keyValuePairs, String _table)throws Exception {

        //INITIALIZE LIST FOR ROWS THAT MATCH QUERY
        ArrayList<String[]> matchingRows;
        Map<String, String> succeededLogin;
        String usernameKey, usernameValue;
        String[] columnNames;
        BufferedReader br = new BufferedReader(new FileReader(_table));

        //EXTRACT THE USERNAME[1] CONDITION FROM QUERY
        usernameKey = _keyValuePairs.keySet().toArray()[1].toString();
        usernameValue = _keyValuePairs.get(usernameKey);

        columnNames = getColumnNames(_table);
        int keyIndex = indexOf(columnNames, usernameKey);
        //if key not found in column names
        if(keyIndex == -1)
            return null;

        //skip first line (column names)
        //IF THERE IS MORE THAN ONE QUERY CONDITION (MORE THAN ONE KEY VALUE PAIR)
        //REMOVE ROWS THAT DON'T MATCH THE ADDITIONAL QUERYS
        br.readLine();
        matchingRows = populateMatchingRows(br, keyIndex, usernameValue);
        if(matchingRows.isEmpty())
            return null;
        succeededLogin = removeUnmatchedRows(_keyValuePairs, matchingRows);

        return succeededLogin;
     }

    @Override
    public int createObject(Map<String, String> _keyValuePairs, String _table) throws IOException {

        String newId = getNextId(_table);  //find value that the new id should be
        _keyValuePairs.put("id", newId);
        String[] columnNames = getColumnNames(_table);
        String[] newRow = buildRow(columnNames, _keyValuePairs);
        //write row to file
        try(CSVWriter writer = new CSVWriter(new FileWriter(_table, true))) {
            writer.writeNext(newRow, false);
        }catch(Exception e){
            newId = "0";
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

    @Override
    public boolean findObject(Map<String, String> _usernameKeyValue, String _table)throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(_table));
        String columnName = "username";
        String givenUsername = _usernameKeyValue.get(columnName);
        int keyIndex = indexOf(getColumnNames(_table), columnName);
        String[] currentRow;
        String line;
        //ignore first line (column names)
        br.readLine();
        while((line = br.readLine()) != null) {
            currentRow = line.split(",");
            if(currentRow.length-1 >= keyIndex)
                if(currentRow[keyIndex].equals(givenUsername))
                    return true;
        }
        return false;
    }

    /////////////////////////////////////////////////////////////////////////////
    //    HELPER METHODS
    ///////////////////////////////////////////////////////////////////////////

    private Map<String, String> removeUnmatchedRows(Map<String, String> _keyValuePairs, ArrayList<String[]> matchingRows) {
        //populate keyValuePairs with correct row using matchingRows
        String enteredPassword = _keyValuePairs.values().toArray()[0].toString();
        //take out "= _keyValuePairs"
        Map<String, String> succeededLogin =  _keyValuePairs;
        _keyValuePairs.clear();
        String[] matchedTuple;
        int correctTuple = -1;
        String[] keys = {"id", "uuid","username", "password"};
        for(int i = 0; i < matchingRows.size(); i++){
            matchedTuple = matchingRows.get(i);
            for (int j = 0; j < matchedTuple.length; j++) {
                //if password entered by user = matchedTuple password
                if(enteredPassword.equals(matchedTuple[3])){
                    _keyValuePairs.put(keys[j], matchedTuple[j]);
                    correctTuple = i;
                }
            }
        }
        if(correctTuple > -1)
            return succeededLogin;
        return null;
    }

    private String[] getColumnNames(String _table) throws FileNotFoundException, IOException {
        String line;
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
        while(sr.readLine()!= null){
             count++;
        }
        String nextId = Integer.toString(count);
        return nextId;
    }

    private static int indexOf(String[] arr, String targetString) {
        for(int i = 0; i<arr.length; i++){
            if(arr[i].equals(targetString))
                return i;
        }
        return -1;
    }

    private ArrayList<String[]> populateMatchingRows(BufferedReader br, int keyIndex, String firstValue) throws IOException {
        String line;
        String[] currentRow;
        ArrayList<String[]> matchingRows = new ArrayList<>();

        while((line = br.readLine()) != null) {
            currentRow = line.split(",");
            //if row matches query condition
            if(currentRow.length-1 >= keyIndex)
                if(currentRow[keyIndex].equals(firstValue))
                    matchingRows.add(currentRow);
        }
        return matchingRows;
    }

}
