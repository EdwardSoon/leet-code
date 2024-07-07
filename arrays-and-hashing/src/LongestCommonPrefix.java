import java.util.Arrays;

/** EASY:
 <p>Question:
    Write a function to find the longest common prefix string amongst an array of strings.
    If there is no common prefix, return an empty string "".
 </p>
 <p>Example 1:
     Input: strs = ["flower","flow","flight"]
     Output: "fl"
 </p>
 <p>Example 2:
     Input: strs = ["dog","racecar","car"]
     Output: ""
     Explanation: There is no common prefix among the input strings.
 </p>
 */

/*
Idea: Sort and compare the first and last element
(the strings in between first and last will have the same common prefix if first and last have it in a sorted strings array)
 */

public class LongestCommonPrefix {
    /*
    My method: O (N * M) -- quite bad..
    Implementation: pointer
    1. first get the first str
    2. initiate right = 1 as pointer
    3. in the for loop  it loops through strs array where it started from second str (to compare with 1st str)
    4. while 1st str's substr is equal to 2nd str's substr, right pointer ++
    5. then store the longestIndex = right -1 as it will +1 more at the end of not equal in while loop
    6. assign temp to first str with the longestCommonIndex (so can save resources on the third onwards when compare 1st str to them)
     */
    public String longestCommonPrefix(String[] strs){
        int right;
        String temp = strs[0];
        int len = strs.length;

        int longestIndex = strs[0].length();
        for (int i = 1; i < len; i ++){
            temp = strs[0].substring(0, longestIndex);
            right = 1;
            int strLen = strs[i].length();
            while (right <= strLen
                    && right <= temp.length()
                    && temp.substring(0, right).equals(strs[i].substring(0,right))){
                right++;
            }
            if (right == 1){
                return "";
            }
            longestIndex = right - 1;
            temp = strs[0].substring(0, longestIndex);
        }
        return temp;
    }

    // my exploration: brute force implementation
    public String longestCommonPrefix2 (String[] strs){
        String result = "";
        String first = strs[0];
        String firstSubstr;
        int len = strs.length;
        for (int i = 0; i< len ; i++){
            firstSubstr = first.substring(0,i);
            for (int j = 1; j< len ; j++){
                if (!firstSubstr.equals(strs[j].substring(0,i))){
                   return result;
                }
                result = firstSubstr;
            }
        }
        return result;
    }

    /*
    Get hint: SORT the strs array then compare only the last and 1st, as all the between will definitely have more commons
    T(O) = O(n log n)
    1. sort the strs array first
    2. initiate a pointer for traversing to longer common prefix
    3. ensure pointer is equal or less than the firsst and last strs' length so that substring can be used and wont result error
    */
    public String longestCommonPrefix3 (String[] strs){
        Arrays.sort(strs);
        int lenArr = strs.length;
        int pointer = 1;
        String result = "";
        while(pointer <= Math.min(strs[0].length(), strs[lenArr - 1].length())
            && strs[0].substring(0, pointer).equals(strs[lenArr - 1].substring(0, pointer))){
            pointer ++;
        }
        result = strs[0].substring(0, pointer-1);
        return result;
    }

    /*
    [GOOD ANSWER] Reference answer: NO SORT, just reduce the length of substring
    O(n * m), where n is the number of strings in the array, and m is the length of the longest string.
    1. set the base case , where no element in array will return ""
    2. set prefix as the first str from the array first
    3. loop through each string in array
    4. while loop: use `indexOf` function to return 0 if any of the substring exist in the string we are checking
        4.1 when != 0 means the substring doesn't exist, thus we shorten the substring by one character using `substring`
        4.2 = 0 when there is a substring matched and it returns the first index of the substring occurrence
     */
    public String longestCommonPrefix4 (String[] strs){
        if (strs == null || strs.length == 0){
            return "";
        }
        String prefix = strs[0];
        for (String s : strs) {
            while(s.indexOf(prefix) != 0){
                prefix = prefix.substring(0, prefix.length() - 1);
            }
        }
        return prefix;
    }

    public static void main(String[] args) {
        LongestCommonPrefix obj = new LongestCommonPrefix();
        String[] strs = {"flower","flow","flight"};
        System.out.println(obj.longestCommonPrefix4(strs));
    }
}