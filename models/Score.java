package models;
/*
*Last updated on 10/28/20
*
*Generic game score tracker
*
*Contributing authors
*@author Francisco
*@author Ryan
*@author Andy
 */
import db.DataObject;
import db.DataStoreAdapter;
import factories.GameFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Score extends DataObject {


    //IF GAME DOES NOT HAVE MAX SCORE, MAXSCORE AND PERCENT ARE -1
    private Integer score;
    public static final int DEFAULT_HIGH_SCORE = -1;

    public static String TABLE = "src/storage/scores.csv";


    //CONSTRUCTOR FOR HIGH AND LOW SCORE
    public Score(Integer _score) {
        this.score = _score;
    }

    /**
     *
     * @return true if a new high score was saved, false if the score was not high
     * @throws Exception
     */
    public boolean save() throws Exception {

        //get list of rows where the user is currentUser and game is currentGame
        Map<String, String> usernameAndGame = new HashMap<>();
        usernameAndGame.put("username", LoginSession.currentUser.getUsername());
        usernameAndGame.put("game", String.valueOf(GameFactory.currentGame));
        List<Map<String, String>> currentHighScoreRows = DataStoreAdapter.readObject(usernameAndGame, Score.TABLE);

        //if user doesn't have a score on this game yet, create one with current score
        if(currentHighScoreRows == null){

            Map<String, String> scoreProperties = new HashMap<>();
            Map<String, String> parentProperties = new HashMap<>();
            scoreProperties.put("highscore", String.valueOf(this.score));
            scoreProperties.put("game", String.valueOf(GameFactory.currentGame));
            scoreProperties.put("username", String.valueOf(LoginSession.currentUser.getUsername()));
            parentProperties = super.createMap();
            scoreProperties.putAll(parentProperties);
            DataStoreAdapter.createObject(scoreProperties, TABLE);
            return true;
        }
        //else, check to see if the score they just got is higher than their highscore
        else{
            Map<String, String>  currentHighScoreRow = currentHighScoreRows.get(0);
            int currentHighScore = Integer.valueOf(currentHighScoreRow.get("highscore"));
            //if they got a new highscore, update their current entry
            if(currentHighScore < this.score){
                String uuid = currentHighScoreRow.get("uuid");
                Map<String, String> newScoreMap = new HashMap<>();
                newScoreMap.put(("highscore"), String.valueOf(this.score));
                DataStoreAdapter.updateObject(newScoreMap, uuid, TABLE);
                return true;
            }
            //if the score they just got was not the high score return false
            return false;
        }
    }

    public static int getScoreByUuid(String _uuid, int gameID) throws Exception {
        int resultingScore = -1;
        Map<String, String> keyValue = new HashMap<>();
        keyValue.put("uuid", _uuid);
        List<Map<String, String>> response = DataStoreAdapter.readObject(keyValue, Score.TABLE);
        if (response == null)
            return resultingScore;

        //Score thisScore = response;
        return resultingScore;
    }

    public static int getScoreByUsername(String _username, int gameID) throws Exception {
        int resultingScore = -1;
        Map<String, String> keyValue = new HashMap<>();
        keyValue.put("username", _username);
        List<Map<String, String>> response = DataStoreAdapter.readObject(keyValue, Score.TABLE);
        if (response == null)
            return resultingScore;

         resultingScore = Integer.parseInt(response.get(0).get("highscore"));
        return resultingScore;
    }

}
