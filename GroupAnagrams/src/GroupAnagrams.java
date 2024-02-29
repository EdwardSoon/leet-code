/*
1. if meet similar case then add into the existed arraylist
2. else, create a new arraylist
 */

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupAnagrams {
    public static List<List<String>> groupAnagrams(String[] strs) {

        HashMap<String, ArrayList<String>> anagramGroupedStrMap = new HashMap<>();
        ArrayList<String> uniqueAnagramList = new ArrayList<>();

        for (String str : strs){
            char[] anagramCharArr = str.toCharArray();  // convert each string to charArray so that we can sort
            Arrays.sort(anagramCharArr);
            String anagram = String.valueOf(anagramCharArr);  //convert back the sorted charArray into String
            if (!anagramGroupedStrMap.containsKey(anagram)){
                uniqueAnagramList.add(anagram);  // store the anagram into a list for later to iterate over to the hashMap value into finalList
                anagramGroupedStrMap.put(anagram, new ArrayList<String>(List.of(str))); // for each unique anagram as key, we create an ArrayList to group the string with similar anagram together.
                // To initialise ArrayList, it takes a collection of lists
                // we need ArrayList because List is just an interface without concrete implementation.
            }
            else { // if the map contains the anagram
                anagramGroupedStrMap.get(anagram).add(str); // get the ArrayList (Value), then add the str inside.
            }
        }
        List<List<String>> finalList = new ArrayList<>();  // needs to return as List, but List is the interface of ArrayList, so can be initiated as ArrayList, but must be List Type
        for (String anagram : uniqueAnagramList){
            finalList.add(anagramGroupedStrMap.get(anagram));
        }
        return finalList;
    }

    // i commented out the redundant part, but the logic was the samee
    public static List<List<String>> groupAnagramsRevised(String[] strs) {

        HashMap<String, ArrayList<String>> anagramGroupedStrMap = new HashMap<>();
//        ArrayList<String> uniqueAnagramList = new ArrayList<>();

        for (String str : strs){
            char[] anagramCharArr = str.toCharArray();  // convert each string to charArray so that we can sort
            Arrays.sort(anagramCharArr);
            String anagram = String.valueOf(anagramCharArr);  //convert back the sorted charArray into String
            if (!anagramGroupedStrMap.containsKey(anagram)){
//                uniqueAnagramList.add(anagram);  // store the anagram into a list for later to iterate over to the hashMap value into finalList
                anagramGroupedStrMap.put(anagram, new ArrayList<String>(List.of(str))); // for each unique anagram as key, we create an ArrayList to group the string with similar anagram together.
                // To initialise ArrayList, it takes a collection of lists
                // we need ArrayList because List is just an interface without concrete implementation.
            }
            else { // if the map contains the anagram
                anagramGroupedStrMap.get(anagram).add(str); // get the ArrayList (Value), then add the str inside.
            }
        }
//        List<List<String>> finalList = new ArrayList<>();  // needs to return as List, but List is the interface of ArrayList, so can be initiated as ArrayList, but must be List Type
//        for (String anagram : uniqueAnagramList){
//            finalList.add(anagramGroupedStrMap.get(anagram));
//        }
        return new ArrayList<>(anagramGroupedStrMap.values()); // .values return a collections which can be used in ArrayList to initialise a List type to be returned !
        // THIS WAY WE DO NOT HAVE TO ITERATE ONE MORE TIME TO EXTRACT THE LIST.
    }


    public static void main(String[] args) {
        String[] x = {"eat","tea","tan","ate","nat","bat"};
        System.out.print(groupAnagrams(x));

    }
}
