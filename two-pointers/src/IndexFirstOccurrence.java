/*
Given two strings needle and haystack,
return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

haystack and needle consist of only lowercase English characters.
 */

/*
Using Sliding Window Algorithm
Pseudocode:
1. turn both haystack and needle to char array
2. create a for loop that initialise left and right
3. init left (outer pointer), right (inner pointer)
4. for each outer pointer, check the inner pointer until it doesnt match
5. when inner pointer matches all then we will return the outer pointer index (the beginning of the index)
 */


public class IndexFirstOccurrence {
    // method 1: Sliding Window Algorithm
    public int strStr(String haystack, String needle) {
        int needleLen = needle.length();
        int strLen = haystack.length();
        char[] hChars = haystack.toCharArray();
        char[] nChars = needle.toCharArray();
        for (int left = 0, right; left <= strLen - needleLen; left++) { // strLen - needleLen, it will stop the loop at when the remaining < needle as it is impossible to match the substring in that case
            right = left;
            while (hChars[right] == nChars[right - left]) { // right - left to get the needle's right always start at left and progress through
                right++;
                if (right - left == needleLen) {
                    return left;
                }
            }
        }
        return -1;
    }

    // method 2: using String method `indexOf`
    public int strStr2(String haystack, String needle) {
        return haystack.indexOf(needle);
    }
    public static void main(String[] args) {
        IndexFirstOccurrence obj = new IndexFirstOccurrence();
        System.out.println(obj.strStr("mississippi", "issip"));
    }
}