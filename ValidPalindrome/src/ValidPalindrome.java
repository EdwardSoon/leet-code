/**
 * TWO POINTER SOLUTION
 */

/**
QUESTION:
 A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward.
Alphanumeric characters include letters and numbers.

Given a string s, return true if it is a palindrome, or false otherwise.
**/

/*
Steps
1. implement two pointers one from left one from right
2. if meet non-alphanumeric, throw it
    2.1. remove space: left ++ or right -- ;
    2.2. convert all alphaberts to lowercase by using ASCII -32
3. left != right then return false, otherwise true
4. loop until it is n/2
 */

public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        String cleanedStr = s.toLowerCase().trim();
        char[] sArr = cleanedStr.toCharArray();
        int len = sArr.length;
        int left = 0;
        int right = len-1;
        int ascii_a = 'a';
        int ascii_z = 'z';
        int ascii_0 = '0';
        int ascii_9 = '9';

        while(left < right && right > 0){
            if(((sArr[left] < ascii_0 || sArr[left] > ascii_9) && (sArr[left] < ascii_a || sArr[left] > ascii_z))
                    || (((sArr[right] < ascii_0 || sArr[right] > ascii_9) && (sArr[right] < ascii_a || sArr[right] > ascii_z)))){
                if((sArr[left] < ascii_0 || sArr[left] > ascii_9) && (sArr[left] < ascii_a || sArr[left] > ascii_z)){
                    left++;
                }
                if((sArr[right] < ascii_0 || sArr[right] > ascii_9) && (sArr[right] < ascii_a || sArr[right] > ascii_z)){
                    right--;
                }
            }
            else {
                if(sArr[left] != sArr[right]){
                    return false;
                }
                else{
                    left++;
                    right--;
                }
            }
        }
        return true;
    }

    // after referring to the solution of others, i found that there are methods in Character class i could use
    public boolean isPalindrome2(String s){
        char[] sArr = s.toCharArray();
        int len = sArr.length;
        int left = 0;
        int right = len-1;

        while(left < right){
            if(!Character.isLetterOrDigit(sArr[left]) || !Character.isLetterOrDigit(sArr[right])){
                if(!Character.isLetterOrDigit(sArr[left])){
                    left ++;
                }
                if(!Character.isLetterOrDigit(sArr[right])){
                    right --;
                }
            }
            else {
                if(Character.toLowerCase(sArr[left]) != Character.toLowerCase(sArr[right])){
                    return false;
                }
                else{
                    left++;
                    right--;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "!!!::vrTTr::v!!!";
        ValidPalindrome test = new ValidPalindrome();

        System.out.println(test.isPalindrome(s));
    }
}