/*
*Last updated on 11/20/20
*
*Logic for the leaderboard controller
*
*
*Contributing authors
*@author Ryan
*/
package models;

import db.DataStoreAdapter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author RyanFromSchool
 */
public class Leaderboard {
    private final int game1ID = 1;
    private final int game2ID = 2;

    public Leaderboard() {
    }

    public int getGame1ID() {
        return game1ID;
    }

    public int getGame2ID() {
        return game2ID;
    }

    public HashMap<String, Integer> getHighScores(int _gameID, int _howMany) throws IOException {
        ArrayList<String> allTableRows = DataStoreAdapter.getTableRows(Score.TABLE);
        HashMap<String, Integer> HighScores = new HashMap<>();
        //take off first row (columnNames)
        allTableRows.remove(0);

        //split up the row and rearrange into
        //SCORE, USERNAME, GAMEID
        int indexOfEntry;
        for(String entry : allTableRows){
            indexOfEntry = allTableRows.indexOf(entry);
            if(!(entry.equals(""))){
                String[] row = entry.split(",");
                entry = row[4] + "," + row[2] + "," + row[3];
                allTableRows.set(indexOfEntry, entry);
            }
            else
                allTableRows.set(indexOfEntry, "error, error, error");
        }

        //sort by highest score first
        Collections.sort(allTableRows, Collections.reverseOrder());

        //Fills hashmap with valid {user, score} combos
        //if it cant make enough combos, use filler data
        int iterator = 0;
        for(String entry : allTableRows){
            indexOfEntry = allTableRows.indexOf(entry);
            if(entry.substring(entry.length()-1).equals("" + _gameID)){
                HighScores.put(entry.substring(entry.indexOf(',')+1, entry.lastIndexOf(',')),
                               Integer.parseInt(entry.substring(0, entry.indexOf(','))));
                iterator++;
                allTableRows.set(indexOfEntry, "");
            }
            else
                allTableRows.set(indexOfEntry, "");
            if(iterator == _howMany)
                break;
        }

        if(iterator < _howMany){
            while (iterator < _howMany){
                HighScores.put("[empty]" + iterator, 0);
                iterator++;
            }
        }
        System.out.println(HighScores.toString());
        return HighScores;
    }
}
