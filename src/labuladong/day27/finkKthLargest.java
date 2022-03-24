package labuladong.day27;

import java.util.Stack;

/**
 * DATE: 2022/3/18
 * CREATE BY: Byx
 */
public class finkKthLargest {
    public int findKthLargest(int[] nums, int k) {
        int begin = 0;
        int end = nums.length - 1;
        while(begin <= end){
            int p = partition(nums, begin, end);
            if(p < k-1){
                begin = p + 1;
            }else if(p > k-1){
                end = p - 1;
            }else{
                return nums[p];
            }
        }
        return -1;
    }

    public int partition(int []nums, int begin, int end){
        int left = begin + 1;
        int right = end;
        while(left <= right){
            while(left <= right && nums[left] >= nums[begin]){
                left++;
            }
            while(left <= right && nums[right] <= nums[begin]){
                right--;
            }
            if(left < right){
                swap(nums, left, right);
                left++;
                right--;
            }
        }
        swap(nums, begin, right);
        return right;
    }

    public void swap(int []nums, int begin, int end){
        int temp = nums[begin];
        nums[begin] = nums[end];
        nums[end] = temp;
    }

    public static void
    main(String[] args) {
        System.out.println(new finkKthLargest().findKthLargest(new int[]{3,2,1,5,6,4},2));
    }
}
