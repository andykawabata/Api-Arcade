/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import db.DataObject;
import java.util.UUID;

/**
 *
 * @author andyk
 */
public class Scores extends DataObject{
    
    private int gameOneScore;
    private int gameTwoScore;
    private int gameThreeScore;
    private UUID uuid;
    public static String TABLE = "src/storage/scores.csv";
    
    public Scores(){
        
    }

    public int getGameOneScore() {
        return gameOneScore;
    }

    public int getGameTwoScore() {
        return gameTwoScore;
    }

    public int getGameThreeScore() {
        return gameThreeScore;
    }
    
    public void setGameOneScore(int gameOneScore) {
        this.gameOneScore = gameOneScore;
    }

    public void setGameTwoScore(int gameTwoScore) {
        this.gameTwoScore = gameTwoScore;
    }

    public void setGameThreeScore(int gameThreeScore) {
        this.gameThreeScore = gameThreeScore;
    }
    
    
}
