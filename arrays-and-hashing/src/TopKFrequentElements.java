import java.util.*;

/**
 * <p>
 * Question:
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
 * </p>
 *
 * <p>
 * Example 1:
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * </p>
 *
 * <p>
 * Example 2:
 * Input: nums = [1], k = 1
 * Output: [1]
 * </p>
 *
 * <p>
 * Constraint:
 * a) 1 <= nums.length <= 10^5;
 * b) -10^4 <= nums[i] <= 10^4;
 * c) k is in the range [1, the number of unique elements in the array].;
 * d) It is guaranteed that the answer is unique.; (probably means no repetition of the frequency of diff nums)
 * e) Your algorithm's time complexity must be better than O(n log n), where n is the array's size.;
 * </p>
 */

/*
[Before reference to the answer]
Idea
HashMap? when got number then add one counter as the value?
OR
Array sort -- cannot use this since must be better than O(N logN) , arrays.sort (quick sort) is O(N log N)

3 main things:
1. to be able to count the similar element (to be unique) and record them -- hashMap better
2. able to identify the k highest counter
3, able to extract the corresponded key
*/

/*
[After reference]
one of the main ideas:
1. figure out a way to sort without quickSort (O (N log N))
2. a way to sort based on the counter instead of the values of the numbers

 */

public class TopKFrequentElements {
    public static void main(String[] args) {
        TopKFrequentElements obj = new TopKFrequentElements();
        System.out.print(Arrays.toString(obj.topKFrequentBucketSort(new int[]{1, 2, 2, 2, 3, 3, 3}, 3)));
    }

    @Deprecated
    // My work
    // General Idea -- HashMap and ArrayList: trying to match the index and frequency in two different ArryayList
    // THIS DOESN'T WORK
    public int[] topKFrequent(int[] nums, int k) {
        int numsLen = nums.length;
        int curr;
        int[] topKFrequentArr = new int[k];
        if (k > numsLen) {
            throw new IndexOutOfBoundsException("k is larger than the array length");
        }
        HashMap<Integer, Integer> latestIndexMap = new HashMap<>();
        ArrayList<Integer> counters = new ArrayList<>();
//        ArrayList<Integer> topKFrequentNums = new ArrayList<>();
        for (int i = 0; i < numsLen; i++) {
            curr = nums[i];
            if (latestIndexMap.containsKey(curr)) {
                counters.add(counters.get(latestIndexMap.get(curr)) + 1);
            } else {
                counters.add(1);
            }
            latestIndexMap.put(curr, i); // update to the latest index
        }

        int largestCounter = numsLen;
        int topKFrequentArrIndex = 0;
        HashMap<Integer, Integer> test = new HashMap<>();
        // HOW TO GET THE MAX NUMBER IN THE ARRAYLIST
        // to add the corresponded index num from nums that starts from max counters arraylist
        while (topKFrequentArrIndex < k) {
            if (counters.contains(largestCounter)) { // if there are two same counters, then it will not work

                test.put(nums[counters.indexOf(largestCounter)], largestCounter);
                topKFrequentArr[topKFrequentArrIndex] = nums[counters.indexOf(largestCounter)];
//                topKFrequentNums.add(nums[counters.indexOf(j)]);
                topKFrequentArrIndex++;
            }
            largestCounter--;
        }
        System.out.println(latestIndexMap);
        System.out.println(counters);
        System.out.println(Arrays.toString(topKFrequentArr));
        return topKFrequentArr;// turn this to array
    }

    /*
     General Idea -- Bucket Sort -- with List<Integer> returned:
     1. init an array of List
     2. init a freqMap: key = num; value = counter
     3. for each key, we will be based on the freq to put into the corresponded index of the array, thus it is easier for us to sort in this way
     4. then transfer the latest index (which has the largest freq) in the array of list to the resulted array

     O(T) = O(N) = (O(N) for hashmap + O(U) to get and put the U elements + O(K) for k elements
     where U = number of unique elements

     Note: this solution can't be submitted as the data type is not an array, let's move on to make it int[] returned type
     */
    public List<Integer> topKFrequenBucketSort(int[] nums, int k) {
        List<Integer>[] bucket = new List[nums.length + 1];  // inistantiate array of List<Intger> as a bucket
        Map<Integer, Integer> counterMap = new HashMap<>();
        for (int n : nums) {
            counterMap.put(n, counterMap.getOrDefault(n, 0) + 1);
        }

        // bucket sort based on counter
        for (int key : counterMap.keySet()) {
            int counter = counterMap.get(key);
            if (bucket[counter] == null) { // as the lists in the array have not instantiated
                bucket[counter] = new ArrayList<>();
            }
            bucket[counter].add(key);
        }

        List<Integer> res = new ArrayList<>();
        for (int freq = bucket.length - 1, resIndex = 0; freq >= 0 && resIndex < k; freq--) {
            if (bucket[freq] != null) {
                res.addAll(bucket[freq]);
            }
        }
        return res.subList(0, k); // to ensure only k element is returned in the case of multiple numbers with same frequency
    }

    /*
    General Idea -- Bucket Sort return int[]
    1. init and create freq map with HashMap
    2. bucket the keys based on the freq
    3. based on k, init array
    4. assign arr[k-1] to store the answer

    O(T) = O(N) = O(N) for freqMap + O(U) for bucketing all unique nums  + O(K) for storing into array
     */
    public int[] topKFrequentBucketSort(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        List<Integer>[] bucket = new List[nums.length + 1]; // +1 because in the case of nums == 0 then this should be initiated too to return empty array
        for (int key : freqMap.keySet()) {
            int freq = freqMap.get(key);
            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();
            }
            bucket[freq].add(key);
        }

        int[] result = new int[k];
        for (int f = bucket.length - 1; k > 0 && f >= 0; f--) {
            if (bucket[f] != null) {
                for (int i = 0; i < bucket[f].size() && k > 0; i++) {
                    result[k - 1] = bucket[f].get(i);
                    k--;
                }
            }
        }

        return result;

    }

    /*
    General Idea -- maxHeap:
    1. Using HashMap (FreqMap)
    2. using PriorityQueue (maxHeap)
    3. poll from the maxHeap to add to array

    Implementation
    1. init a Hashmap that put num as key, freq as value
    2. init a PriorityQueue (a heap)
        2.1 define a comparator that defines the order of the queue BASED ON the frequency (map's value instead of key)
    3. then addAll key inside , when add inside the priorityQ, it will add based on the comparator function defined
    4. then poll to the output array based on each index

    O(T) = O(N + K log U) = O(N) + O(UlogU) + O(K Log U)  where u <= n and k <= u
    O(S) = O(N) = O(N) + O(U) + O(K)
     */
    public int[] topKFrequentMaxHeap(int[] nums, int k) {
        if (nums.length == 0 || k == 0) {
            return new int[]{};
        }

        // O(T) = O(N); O(S) = O(N)
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // O(T) = O(U log U) = O(log U) for insertion to Heap * O(U) the number of unique element ;
        // O(S) = O(U) where U = unique number of elements
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((n1, n2) -> freqMap.get(n2) - freqMap.get(n1)); // this comparator function means
//        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder()); // this doesnt work because it is not taking the freq to order, it is taking the num to order
        maxHeap.addAll(freqMap.keySet());

        // O(T) = O(K log U) = O(K) for k elements (k <= u) * O(log U) for poll operation; O(S) = O(K)
        int[] arr = new int[k];
        for (int i = 0; i < k; i++) {
            arr[i] = maxHeap.poll();
        }

        return arr;
    }

    /*
    (actually slower than MaxHeap: because it add all then poll the smallest rather than directly poll the largest)
    General Idea -- HashMap and MinHeap
    1. init freqMap
    2. init PriorityQueue based on Comparator
        2.1 Comparator function defined based on get freq n1 - n2
    3. for each key from the freqMap keys, insert the key,
        3.1 poll out when the heap size > k, means it will poll the least freq items in the heap and maintain k size
    4. assign array based on k

    O(T) = O(N+KlogK) = O(N) for freqMap + (O(U) for adding unique keys to minheap * O(logU) polling keys) + O(K log K)
     */
    public int[] topKFrequentMinHeap(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        Queue<Integer> minHeap = new PriorityQueue<>(
                (n1, n2) -> freqMap.get(n1) - freqMap.get(n2)
        );

        for (int key : freqMap.keySet()) {
            minHeap.add(key);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        int[] arr = new int[k];
        for (int i = k - 1; i >= 0; i--) { // since array answer order doesn't matter, we can start from i= 0 or i = k-1
            arr[i] = minHeap.poll();
        }
        return arr;
    }

}
