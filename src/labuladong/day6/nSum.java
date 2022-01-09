package labuladong.day6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.*;

/**
 * DATE: 2022/1/9
 * CREATE BY: Byx
 */
public class nSum {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(target - nums[i])) {
                return new int[]{i, hashMap.get(target - nums[i])};
            }
            hashMap.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    public List<List<Integer>> threeSum(int[] nums, int m , int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        int i = m;
        while (i < nums.length) {
            List<List<Integer>> curr = towSum(nums, -nums[i], i + 1);
            if (curr.size() > 0 ) {
                for (int j = 0; j < curr.size(); j++) {
                    ArrayList temp = new ArrayList<>();
                    for (int k = 0; k < curr.get(j).size(); k++) {
                        temp.add(curr.get(j).get(k));
                    }
                    temp.add(nums[i]);
                    result.add((List<Integer>) temp.clone());
                }
                while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                    i++;
                }
            }
            i++;
        }
        return result;

    }

    public List<List<Integer>> towSum(int[] nums, int target, int i) {
        int left = i;
        int right = nums.length - 1;
        List<List<Integer>> result = new ArrayList<>();
        while (left < right) {
            if (nums[left] + nums[right] < target) {
                left++;
            } else if (nums[left] + nums[right] > target) {
                right--;
            }else {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(nums[left]);
                temp.add(nums[right]);
                result.add((List<Integer>) temp.clone());
                while (left < nums.length - 1 && nums[left] == nums[right]) {
                    left++;
                }
                while (right >1 && nums[right] == nums[right -1]) {
                    right--;
                }
                left++;
                right--;
            }

        }

        return result;

    }


    public List<List<Integer>> fourSum(int[] nums, int target) {

        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int i = 0;
        while(i < nums.length){
            List<List<Integer>> curr =  threeSum(nums, i+1, target - nums[i]);
            if(curr.size()>0){
                for (int j = 0; j < curr.size(); j++) {
                    ArrayList temp = new ArrayList<>();
                    for (int k = 0; k < curr.get(j).size(); k++) {
                        temp.add(curr.get(j).get(k));
                    }
                    temp.add(nums[i]);
                    result.add((List<Integer>) temp.clone());
                }
                while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                    i++;
                }
            }
            i++;
        }
        return result;

    }


    public static void main(String[] args) {
        System.out.println(new nSum().threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }
}
