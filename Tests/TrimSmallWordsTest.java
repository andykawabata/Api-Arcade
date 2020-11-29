package Tests;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import models.Sanitizer;
/**
 * The purpose of this class is to test the "trimSmallWords()" function found
 * in models.Sanitizer - The purpose of this function  in our application is
 * to remove small words from a movie title so the important words can be
 * compared to and removed from the description.
 *
 * The main method initializes the 3 normal, 2 edge, and 1 error cases,
 * puts them in a list and iterates over that list, passing each input into the
 * function then comparing the results with the expected output.
 *
 * Run this file to test model.Sanitize.trimSmallWords()
 *
 * @author andyk
 */
public class TrimSmallWordsTest {

    public static void main(String[] args) {

        //initialize list of test cases
        ArrayList<HashMap<String, String[]>> caseList = new ArrayList<HashMap<String, String[]>>();

        //NORMAL CASES contain a mix of short and long words
        String[] inputN1 = {"One", "Flew", "Over", "the", "Cuckoos", "Nest" };
        String[] expectedN1 = {"", "Flew", "Over", "", "Cuckoos", "Nest" };
        caseList.add(buildMap(inputN1, expectedN1));

        String[] inputN2 = {"Me", "and", "you", "and", "Everyone", "we", "know"};
        String[] expectedN2 = {"", "", "", "", "Everyone", "", "know"};
        caseList.add(buildMap(inputN2, expectedN2));

        String[] inputN3 = {"Monty", "Python", "and", "the", "Holy", "Grail"};
        String[] expectedN3 = {"Monty", "Python", "", "", "Holy", "Grail"};
        caseList.add(buildMap(inputN3, expectedN3));

        //EDGE CASES contain only short or only long words
        String[] inputEd1 = {"Sweet", "Home", "Alabama"};
        String[] expectedEd1 = {"Sweet", "Home", "Alabama"};
        caseList.add(buildMap(inputEd1, expectedEd1));

        String[] inputEd2 = {"I", "Am", "You"};
        String[] expectedEd2 = {"", "", ""};
        caseList.add(buildMap(inputEd2, expectedEd2));

        //ERROR CASES contains punctuation
        String[] inputEr1 = {"Fantatic", "Mr.", "Fox"};
        String[] expectedEr1 = {"", "", ""};
        caseList.add(buildMap(inputEr1, expectedEr1));

        for(int i = 0; i < caseList.size(); i++){
            //Get input and feed into function, save actual output
            String[] input = caseList.get(i).get("input");
            String[] actual = Sanitizer.trimSmallWords(input);

            //get expected output
            String[] expected = caseList.get(i).get("expected");

            //print Input, Expected and Actual output, and if they matched/passed
            System.out.println("Input:      " + Arrays.toString(input));
            System.out.println("Exp Output: " + Arrays.toString(expected));
            System.out.println("Act Output: " + Arrays.toString(actual));
            boolean matches = Arrays.equals(actual, expected);
            System.out.println("PASSED: " + matches);
            System.out.println();

        }
    }

    /**
     * Helps put test case's input and expected output into a HashMap
     * @param input String[]
     * @param expected String[]
     * @return HashMap containing input string arrays
     */
    private static HashMap<String, String[]> buildMap(String[] _input, String[] _expected){
       HashMap<String, String[]> map = new HashMap<>();
       map.put("input", _input);
       map.put("expected", _expected);
       return map;

   }

}
