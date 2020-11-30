package factories;
/*
*Last updated on 11/29/20
*
*
*Sets the game being playted to either the movie game
*or the trivia game
*
*Contributing authors
*@author Andy
 */
import models.Game;
import models.MovieGame;
import models.TriviaGame;

public class GameFactory {

    public static final int GAME_ONE = 1;
    public static final int GAME_TWO = 2;
    public static final int GAME_THREE = 3;
    public static int currentGame;

    //=================  GETTERS ===============//

    public static void setCurrentGame(int _gameNumber){
        switch (_gameNumber) {
            case GAME_ONE:
                currentGame = GAME_ONE;
                break;
            case GAME_TWO:
                currentGame = GAME_TWO;
                break;
            case GAME_THREE:
                currentGame = GAME_THREE;
                break;
            default:
                break;
        }
    }

    public static Game getCurrentGameInstance(){
        switch (currentGame) {
            case GAME_ONE:
                return new MovieGame();
            case GAME_TWO:
                return new TriviaGame();
            case GAME_THREE:
                return null;
            default:
                break;
        }
        return null;
    }

}
