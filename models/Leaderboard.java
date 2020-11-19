/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

    public HashMap<String, Integer> getHighScores(int gameID, int howMany) throws IOException {
        ArrayList<String> allTableRows = DataStoreAdapter.getTableRows(Score.TABLE);
        HashMap<String, Integer> HighScores = new HashMap<>();
        //take off first row (columnNames)
        allTableRows.remove(0);

        //id + uuid + two commas = username starts at character 39 ALWAYS
        //cut down each row into just username, game, score
        //then move score to beginning
        //SCORE, USERNAME, GAMEID
        int indexOfEntry;
        for(String entry : allTableRows){
            indexOfEntry = allTableRows.indexOf(entry);
            entry = entry.substring(39);
            entry = entry.substring(entry.lastIndexOf(',') + 1) + "," + entry.substring(0, entry.lastIndexOf(','));
            allTableRows.set(indexOfEntry, entry);
        }

        //sort by highest score first
        Collections.sort(allTableRows, Collections.reverseOrder());

        //Fills hashmap with valid {user, score} combos
        //if it cant make enough combos, use filler data
        int iterator = 0;
        for(String entry : allTableRows){
            indexOfEntry = allTableRows.indexOf(entry);
            if(entry.substring(entry.length()-1).equals("" + gameID)){
                HighScores.put(entry.substring(entry.indexOf(',')+1, entry.lastIndexOf(',')),
                               Integer.parseInt(entry.substring(0, entry.indexOf(','))));
                iterator++;
                allTableRows.set(indexOfEntry, "");
            }
            else
                allTableRows.set(indexOfEntry, "");
            if(iterator == howMany)
                break;
        }

        if(iterator < howMany){
            while (iterator < howMany){
                HighScores.put("[empty]" + iterator, 0);
                iterator++;
            }
        }
        System.out.println(HighScores.toString());
        return HighScores;
    }
}
