package labuladong.day3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * DATE: 2022/1/3
 * CREATE BY: Byx
 */
public class twoSum {
    public int[] twoSum(int[] nums, int target) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length -1 ;
        boolean flag = false;
        while (left <= right){
            if(nums[left] + nums[right] == target){
                flag = true;
                break;
            }
            else if(nums[left] + nums[right] < target){
                left = target > 0 ? left + 1: right --;
            }
            else if(nums[left] + nums[right] > target){
                right = target > 0 ? right -1 : left ++;
            }
        }

        return new int[]{left, right};

    }

    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for(int i= 0; i< nums.length;i++){
            if (hashtable.containsKey(target - nums[i])){
                return new int[]{i, hashtable.get(target - nums[i])};
            }
            hashtable.put(nums[i],i);
        }
        return new int[]{-1,-1};
    }

    public static void main(String[] args) {
        System.out.println(new twoSum().twoSum(new int[]{-1,7,11,13},9));
    }
}
