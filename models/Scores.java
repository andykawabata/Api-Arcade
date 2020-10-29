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
public class Scores {

    private int scores;
    // work in progress
    public int updateScore(boolean _ans) {
        if(_ans != false) {
            return scores++;
        }
        return scores;
    }

}
