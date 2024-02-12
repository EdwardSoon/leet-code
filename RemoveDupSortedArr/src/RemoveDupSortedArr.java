/*
Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same. Then return the number of unique elements in nums.

Consider the number of unique elements of nums to be k, to get accepted, you need to do the following things:
* Change the array nums such that the first k elements of nums contain the unique elements in the order they were present in nums initially. The remaining elements of nums are not important as well as the size of nums.
* Return k.
 */

// [1, 2, 2, 3, 4] -> [1, 2, 3, 4, 4]

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class RemoveDupSortedArr {
    public int removeDuplicates(int[] nums){

        int k = nums.length;

        for (int i=1; i<k; i++){
            if(nums[i] == nums[i-1]){
                for (int j=i; j<k; j++){
                    nums[j-1] = nums[j];
                }
                k--;
                i--; // because all the element moved front one step
            }
        }
//        System.out.println(Arrays.toString(nums));
        return k;
    }

    public int removeDuplicates2(int[] nums){

        int repeat = 100000;
        int lenCounter = 0;

        for (int i=1; i<nums.length; i++){
            if(nums[i-1] == nums[i] && nums[i-1] != repeat){
                repeat = nums[i-1];
            }
            if (nums[i-1] != nums[i]){
                lenCounter++;
                nums[lenCounter] = nums[i];
            }
        }
//        System.out.println(Arrays.toString(nums));
        return lenCounter+1;
    }

    public int removeDuplicatesIdeal(int[] nums){

        int lenCounter = 1;
        for (int i=1; i<nums.length; i++){
            if (nums[i-1] != nums[i]){
                nums[lenCounter] = nums[i];
                lenCounter++;
            }
        }
//        System.out.println(Arrays.toString(nums));
        return lenCounter;
    }


    public static void main(String[] args) {
        int[] arr = {0,0,1,1,1,2,2,3,3,4};
        RemoveDupSortedArr obj = new RemoveDupSortedArr();
        System.out.print(obj.removeDuplicatesIdeal(arr));



    }
}