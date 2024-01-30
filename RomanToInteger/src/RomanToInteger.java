/*
Given a roman numeral, convert it to an integer.
Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000

I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.
 */


/*
Conditions:
1. written from largest to smallest from left to right
    -- the next value should be smaller or equal
    -- if yes then get the value
2. exception: IV, IX , XL ...
    -- if not then check next character for this exception
    -- put in HashMap as one of the key
3. IIII cannot be 4 just like XXXX cannot be 40
    -- same character cannot repeat 4 times
4. cannot IVI as 5 is V
    -- once have the exception, the first character in the first exception cannot appear anymore
        -- e.g: IV I is wrong as it should be V
5. if sum (char[i] + char[i+1]) is inside map, then cannot OR only I X C M all the int with '1' can repeat 3 times, the rest cannot

 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class RomanToInteger {
    public static void main(String[] args) {

        HashMap<Character,Integer> roman = new HashMap<>();
        roman.put('I', 1);
        roman.put('V', 5);
        roman.put('X', 10);
        roman.put('L', 50);
        roman.put('C', 100);
        roman.put('D', 500);
        roman.put('M', 1000);

        Scanner sc = new Scanner(System.in);
        char[] input = sc.next().toCharArray();
        int sum = 0;
        int tempSum = 0;
        int prevTempSum = 4000; // to ensure it is bigger than the first TempSum first
        int counterRepeat = 1;
        char check = 0;

        // base case when char length is only 1
        if (input.length == 1){
            sum = roman.get(input[0]);
        }
        // char length > 1
        for (int i = 0; i < input.length - 1; i++){

            if (roman.get(input[i]) < roman.get(input[i+1])){  //for case like 'IV' , 'IX' and ensure larger to smaller
                tempSum = roman.get(input[i+1]) - roman.get(input[i]);
                if((tempSum % 4 != 0 && tempSum % 9 != 0)){     // to tackle cases like : 'IL'
                    System.out.println("Roman wrong.");
                    sc.close();
                    return;
                }
            }
            else{
                tempSum = roman.get(input[i]) + roman.get(input[i+1]);
            }

            if (!roman.containsValue(tempSum) && counterRepeat < 4 && ((prevTempSum + tempSum)/2 != roman.get(input[i]))){     // invalidate cases: 'VV' ; invalidate cases: 'IVI' as larger to smaller number; invalidate 'IIII' repeated 4 times
                if(prevTempSum == tempSum){
                    counterRepeat ++;
                }
                else{
                    counterRepeat = 1;
                }
                sum += tempSum;
                if(i>0){
                    sum -= roman.get(input[i]); // when it is second element onwards, minus back the current i element, if not double count
                }
                prevTempSum = tempSum;
            }
            else{
                System.out.println("Roman wrong.");
                sc.close();
                return;
            }
        }

        System.out.println("Roman: " + sc.nextLine() + "Int: " + sum);
        sc.close();

    }
}