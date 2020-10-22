/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factories;

import models.Game;
import models.MovieGame;

/**
 *
 * @author andyk
 */
public class GameFactory {
    
    public static final int GAME_ONE = 1;
    public static final int GAME_TWO = 2;
    public static final int GAME_THREE = 3;
    public static int currentGame;
    
    public static void setCurrentGame(int gameNumber){
        if(gameNumber == GAME_ONE)
            currentGame = GAME_ONE;
        else if(gameNumber == GAME_TWO)
            currentGame = GAME_TWO;
        else if(gameNumber == GAME_THREE)
            currentGame = GAME_THREE;
    }
    
    public static Game getCurrentGameInstance(){
        if(currentGame == GAME_ONE)
            return new MovieGame();
        else if(currentGame == GAME_TWO)
            return null;
        else if(currentGame == GAME_THREE)
            return null;
        return null;
    }
    
}
