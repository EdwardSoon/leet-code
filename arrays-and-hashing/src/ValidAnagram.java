/*
1. convert the s and t string to charArray
2. scan from left to right of t charArray, if contain s string then
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ValidAnagram {

    // use hashMap to track the counts
    // T(n) = O(n)
    // S(n) = O(n)
    public static boolean isAnagram(String s, String t) {

        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();

        if (sArr.length != tArr.length){
            return false;
        }

        HashMap<Character, Integer> sMap = new HashMap<>();
        for (char sChar : sArr){
            sMap.put(sChar, sMap.getOrDefault(sChar, 0)+1);
        }

        for (char tChar : tArr){
            if (!sMap.containsKey(tChar) || sMap.get(tChar) == 0){
                return false;
            }
            else{
                sMap.put(tChar, sMap.get(tChar) - 1);
            }
        }
        return true;
    }

    // Sorted String, check the equality might be more efficient
    // T(n) = O(n*Log(n))
    // S(n) = O(n)
    public static boolean isAnagram2(String s, String t){
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        Arrays.sort(sArr);
        Arrays.sort(tArr);

        return Arrays.equals(sArr, tArr); // check the element in the same order, and same element then only true
    }


    public static void main(String[] args) {
        System.out.println(isAnagram2("anagramms", "nagaram"));

    }
}