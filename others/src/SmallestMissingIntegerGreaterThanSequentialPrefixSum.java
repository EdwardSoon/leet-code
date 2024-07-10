import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
/*
THIS QUESTION DESCRIPTION IS BAD, LET'S SKIP IT
 */
/**
 * Questions:
 * <p>
 * You are given a 0-indexed array of integers nums.
 * A prefix nums[0..i] is sequential if, for all 1 <= j <= i, nums[j] = nums[j - 1] + 1. In particular, the prefix consisting only of nums[0] is sequential.
 * Return the smallest integer x missing from nums such that x is greater than or equal to the sum of the longest sequential prefix.
 * </p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,2,5]
 * Output: 6
 * Explanation: The longest sequential prefix of nums is [1,2,3] with a sum of 6. 6 is not in the array, therefore 6 is the smallest missing integer greater than or equal to the sum of the longest sequential prefix.
 * </p>
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [3,4,5,1,12,14,13]
 * Output: 15
 * Explanation: The longest sequential prefix of nums is [3,4,5] with a sum of 12. 12, 13, and 14 belong to the array while 15 does not. Therefore 15 is the smallest missing integer greater than or equal to the sum of the longest sequential prefix.
 * </p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 50
 * 1 <= nums[i] <= 50
 * </p>
 */

/*
nums will not have no integer cases as shown in the constraint
 */
public class SmallestMissingIntegerGreaterThanSequentialPrefixSum {
    public static void main(String[] args) {
        SmallestMissingIntegerGreaterThanSequentialPrefixSum obj = new SmallestMissingIntegerGreaterThanSequentialPrefixSum();
        System.out.println(obj.missingInteger(new int[]{30, 4, 5, 1, 12, 14, 13}));
    }

    public int missingInteger(int[] nums) {
        int prevNum = nums[0];
        int i = 1;
        int prefixSum = prevNum;
        int lenNums = nums.length;
        if (lenNums == 1 || nums[i] != prevNum + 1) {
            return nums[0] + 1;
        }

        while (i < lenNums && nums[i] == prevNum + 1) {
            prefixSum += nums[i];
            prevNum = nums[i];
            i++;
        }

        ArrayList<Integer> remaining = new ArrayList<>();
        for (int j = i; j < lenNums; j++) {
            remaining.add(nums[j]);
        }
        while (remaining.contains(prefixSum)) {
            prefixSum += 1;
        }
        return prefixSum;
    }
}
