import java.util.ArrayList;
import java.util.Objects;

/**
 Question: Given an integer x, return true if x is a palindrome, and false otherwise.
 Example 1:
     Input: x = 121
     Output: true
     Explanation: 121 reads as 121 from left to right and from right to left.
 Example 2:
     Input: x = -121
     Output: false
     Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
 */

/*
Idea -- two pointers
1. able to extract the int into single digit separately (without converting to String -- cannot use Substring or toCharArray)
    1.1 by using % 10 to get the remainder so that it is the last digit extracted
    1.2 renew the number divided by 10 (as it will still return int without decimal pt) until it is 0
2. use pointer to scan through left and right to become O(n)
    2.1 use left < len/2 as the stop loop condition because
        len / 2: as right is doing its work moving towards the left side too
        < : not <= it is index, should stop before reaching half
*/
public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        ArrayList<Integer> reversed = new ArrayList<>();
        if (x < 0){
            return false;
        }
        while (x != 0){
            reversed.add(x % 10);
            x /= 10;
        }
        int len = reversed.size();
        for (int l=0, r= len-1; l < len/2 ; l++, r--){
            if (!Objects.equals(reversed.get(l), reversed.get(r))){ // Integer is object, so use .equals method better
                return false;
            }
        }
        return true;
    }

    // reference from best time complexity: extraction only half of the digits of the int to compare
    public boolean isPalindrome2 (int x){
        // -ve int and whatever int with % 10 remainder = 0 (e.g: 100, 3000, 3200) except 0 not satisfied
        if (x < 0 || (x % 10 == 0 && x != 0)){
            return false;
        }
        int sum = 0;
        /*
        `x > sum` so that as long as half of the digits are extracted, then is enough already
            even: half of the digits will be extracted = 10
            odd: half of the digits + 1 will be extracted = 10
         */
        while (x > sum){
            // extracting digit in reversed manner
            sum = sum * 10 + x % 10;
            // reduce the x by one digit extracted
            x /= 10;
        }
        // even number of digits: `x == sum` ; odd number of digits: `x == sum / 10`
        return x == sum || x == sum / 10;
    }

    public static void main(String[] args) {
        PalindromeNumber obj = new PalindromeNumber();
        System.out.println(obj.isPalindrome(123));
    }

}
