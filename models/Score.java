package models;

import db.DataObject;
import db.DataStoreAdapter;
import factories.GameFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class Score extends DataObject {


    //IF GAME DOES NOT HAVE MAX SCORE, MAXSCORE AND PERCENT ARE -1
    private Integer score;
    private Integer maxScore;
    private Integer percent;

    public static String TABLE = "src/storage/scores.csv";


    //CONSTRUCTOR FOR ANY GAME
    public Score(){
      score = 0;
      maxScore = -1;
      percent = -1;
    }

    //CONSTRUCTOR FOR SCORE WHERE GAME HAS NO MAX SCORE
    public Score(Integer _score) {
        this.score = _score;
    }

    //CONSTRUCTOR FOR SCORE WHERE GAME HAS MAX SCORE
    public Score(Integer _score, Integer _maxScore) {
        this.score = _score;
        this.maxScore = _maxScore;
        this.percent = (int) ((_score * 100) / _maxScore);
    }

    public boolean save() throws Exception {
        Map<String, String> scoreProperties = new HashMap<>();
        Map<String, String> parentProperties = new HashMap<>();
        scoreProperties.put("score", String.valueOf(this.score));
        scoreProperties.put("maxscore", String.valueOf(this.maxScore));
        scoreProperties.put("percent", String.valueOf(this.percent));
        scoreProperties.put("game", String.valueOf(GameFactory.currentGame));
        scoreProperties.put("username", String.valueOf(LoginSession.currentUser.getUsername()));
        parentProperties = super.createMap();
        scoreProperties.putAll(parentProperties);

        return DataStoreAdapter.createObject(scoreProperties, Score.TABLE);

    }

///////////////////////////////////// GETTERS ///////////////////////////////////////////

    public static int getScore(String _uuid, int gameID) throws Exception {
        int resultingScore = -1;
        Map<String, String> keyValue = new HashMap<>();
        keyValue.put("uuid", _uuid);
        List<Map<String, String>> response = DataStoreAdapter.readObject(keyValue, Score.TABLE);
        if (response == null)
            return resultingScore;

        //Score thisScore = response;
        return resultingScore;
    }

}
