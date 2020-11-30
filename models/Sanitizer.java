/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Pattern;

/**
 *
 * @author andyk
 */
public class Sanitizer {

    /**
     * @param _array an array of strings obtained from a sentence split by spaces
     * @return true if array has only empty elements
     */
    public static boolean isStringArrayEmpty(String[] _array) {
        for (String i : _array)
            if(i.length() > 0)
                return false;
        return true;
    }

    /**
     * @param _array an array of strings obtained from a sentence split by spaces
     * punctuation should have been removed from any of the strings prior to
     * to this method being called
     * @return an array of strings that contains no strings smaller than 4 letters
     * returns null if any string contains punctuation
     */
    public static String[] trimSmallWords(String[] _array) {

        //initialize array to hold the new strings
        String[] trimmedArray = new String[_array.length];

        for (int i = 0; i < _array.length; i++){
            //If any string contains punctuation, return array of empty strings
            if(Pattern.matches(".*?\\p{Punct}.*?", _array[i])){
                for(int j = 0; j < trimmedArray.length; j++)
                    trimmedArray[j] = "";
                return trimmedArray ;
            }
            //if string contains less than 3 charecters, replace w/ empty string
            trimmedArray[i] = _array[i].replaceAll("\\b\\w{1,3}\\b\\s?", "");
        }
        return trimmedArray;
    }

    //delete punctuation from elements in String array using regex
    public static String[] removePunctuation(String[] _array) {
        for(int i = 0; i < _array.length; i++){
            _array[i] = _array[i].replaceAll("\\p{Punct}", "");
        }
        return _array;
    }

}
