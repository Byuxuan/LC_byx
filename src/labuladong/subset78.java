package labuladong;

import java.util.*;

/**
 * DATE: 2022/4/23
 * CREATE BY: Byx
 */
public class subset78 {

    public int subarraySum(int[] nums, int k) {
        int result=0;
        int sum = 0;
        HashMap<Integer, Integer> hash = new HashMap<>();
        hash.put(0, 1);
        for (int i = 0; i <nums.length ; i++) {
            sum += nums[i];
            int sum_0 = nums[i] - k;
            if(hash.containsKey(sum_0)){
                sum += hash.get(sum_0);
            }
            hash.put(sum, hash.getOrDefault(sum, 0)  + 1);
        }
        return result;
    }
    List<List<Integer>> res;
    public List<List<Integer>> subsets(int[] nums) {
        res = new ArrayList<>();
        List<Integer> trace = new ArrayList<>();
        find(trace, nums, 0);
        return res;
    }

    public void find(List<Integer> trace, int []nums, int j){
        res.add(new ArrayList<>(trace));
        for(int i = j; i < nums.length; i++){
            trace.add(nums[i]);
            find(trace, nums, i + 1);
            trace.remove(trace.size() -1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new subset78().subsets(new int[]{1,2,3}));
    }
}
