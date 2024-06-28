import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;

/*
Question
Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
You may assume that each input would have exactly one solution, and you may not use the same element twice.
You can return the answer in any order.
 Constraint:
 - Only one valid answer exists.
 */

/*
Pseudocode
1.
 */
public class TwoSum {
    // method 1: brute force for loops , O(N^2)
    public int[] twoSum(int[] nums, int target){
        int len = nums.length;
        int sum = 99999;
        int[] result = new int[2];
        for (int i = 0; i < len; i++){
            for (int j = i+1; j < len; j ++){
                sum = nums[i] + nums[j];
                if(sum == target){
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return null;
    }

    // method 2: hashMap , O(2N)
    /*
    1. put all the numbers as key and indices as value into HashMap (so key will not duplicate)
    2. check what should be the secondNum to sum up to target
    3. check if the Map consists of second number and make sure the index is bigger than the current index (secondNum position is after firstNum)
    4. return int[] of the indices
     */
    public int[] twoSum2(int[] nums, int target){
        HashMap<Integer, Integer> map = new HashMap<>();
        int len = nums.length;
        for (int i = 0; i < len; i++){
            map.put(nums[i], i);
        }
        for (int i = 0; i < len; i++) {
            int secondNum = target - nums[i];
            if (map.containsKey(secondNum) && map.get(secondNum) > i) {
                return new int[]{i, map.get(secondNum)};
            }
        }
        return null;
    }

    // method 3: HashMap and one loop enough , O(N)
    /*
    1. instantiate HashMap
    2. in for loop, check if map contains key for complement number
    3. if yes means there is a number it can sum to reach target, then return array
    4. if not just add the map with key = num, value = index
    5. with that the next loop it will again scan the map that slowly has number, it is a bit like backward scanning
     */
    public int[] twoSum3 (int[]nums, int target){
        HashMap<Integer, Integer> map = new HashMap<>();
        int len = nums.length;
        for (int i = 0 ; i < len; i++){
            if(map.containsKey(target - nums[i])){
              return new int[]{map.get(target-nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        int[] arr = {4 ,2 ,3 ,1};
        int target = 4;
        TwoSum obj = new TwoSum();
        System.out.println(Arrays.toString(obj.twoSum3(arr, target)));
    }
}
