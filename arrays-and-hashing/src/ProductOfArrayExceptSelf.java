/** 
 * <p>
 * Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * You must write an algorithm that runs in O(n) time and without using the division operation
 * </p>
 
Example 1:
Input: nums = [1,2,3,4]
Output: [24,12,8,6]

Example 2:
Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]

Constraints:

2 <= nums.length <= 10^5
-30 <= nums[i] <= 30
The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * 
*/

import java.util.Arrays;

public class ProductOfArrayExceptSelf {
    public static void main(String[] args) {
        ProductOfArrayExceptSelf obj = new ProductOfArrayExceptSelf();
        System.out.println(Arrays.toString(obj.productExceptSelf(new int[]{0,0})));
    }

    /*
     * Pseudocode: products of every element first then divide it by loop
     * 1. products of all element in one loop -- O(N)
     * 2. loop one more time to divide each with its own index -- O(N) 
     * 3. use hasMultipleZeross boolean to detect if there are multiple 0s -- if yes, all answer = 0;
     * 
     * O(T) = O(N) ; O(S) = O(N) -- excluding output array
     * 
     * [archived] added hashmap to check if hasZero = true, then if the uniqueNum size (the key size) == 1 then it will be only all 0s which needs to be handled differently
     *  -- using hashmap cannot detect multiple 0s with non0 together.
     */
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int totalProduct = 1;
        int totalProductWithZero = 0;
        boolean hasZero = false;
        boolean hasMultipleZeros = false;
        int[] answer = new int[len];
        for (int i = 0 ; i < len ; i++){
            if (hasZero && nums[i] == 0){
                hasMultipleZeros = true;
            }
            
            if (nums[i] == 0){
                hasZero = true;
            }
            else {
                totalProduct *= nums[i];
            }
        }

        for (int i = 0; i < len ; i++){
            if (hasZero){
                if(nums[i] != 0 || hasMultipleZeros){
                    answer[i] = totalProductWithZero;
                }
                else{
                    answer[i] = totalProduct;
                }
            }
            else{
                answer[i] = totalProduct / nums[i];
            }
        }

        return answer;
    }

    /*
     * Reference: Without division: (as division needs to handle 0s scenarios)
     * 1. multiply all the left elements of the current index except itself, and store them in an array
     * 2. using the array just now, multiply all the right elements of the current index excpet itself
     * Note: this works as each element essentially is mutiplying to left and right except their own num, looping through two times able to accomplish that of multiplying left and right
     * O(T) = O(N) ; O(S) = O(1) -- excluding output array
     */
    public int[] productExceptSelf2(int[] nums){
        int len = nums.length;
        int[] answer = new int[len];
        // multiply all left elements of the current index: left = 1 as left is nothing at 0 index
        for (int i = 0, leftProduct = 1; i < len ; i++){ // if start from 0 then start multiply from left as left are the previous index (we can't multiply something we haven't reached yet)
            answer[i] = leftProduct;
            leftProduct *= nums[i]; // multiply the left index (as the current one = the left of the next index)
        } 

        // multiply all the right elements of the current index
        for (int i = len - 1, rightProduct = 1 ; i >= 0 ; i--){
            answer[i] *= rightProduct; // use the array that had multiplied the left elements of the current index, now to multiply the right elements of the current index to cover all nums product except self
            rightProduct *= nums[i];
        }
        return answer;
    }

}