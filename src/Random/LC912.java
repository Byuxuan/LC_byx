package Random;

import java.util.Arrays;

/**
 * DATE: 2022/5/31
 * CREATE BY: Byx
 */
public class LC912 {
    public int[] sortArray(int[] nums) {
        quickSort(0, nums.length -1, nums);
        return nums;
    }
    public void quickSort(int begin, int end, int []nums){
        if(begin >= end) return;
        int part = partition(begin, end, nums);
        quickSort(begin, part -1, nums);
        quickSort(part + 1, end, nums);
    }

    public int partition(int begin, int end, int []nums){
        int dummy = begin;
        int left = begin + 1;
        int right = end;
        while(left <= right){
            while(left <= right && nums[left] <= nums[dummy]) left++;
            while(left <= right && nums[right] >= nums[dummy]) right--;
            if(left < right){
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
        }

        int temp = nums[dummy];
        nums[dummy] = nums[right];
        nums[right] = temp;


        return right;


    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new LC912().sortArray(new int[]{0, 0, 1, 1})));
    }
}
