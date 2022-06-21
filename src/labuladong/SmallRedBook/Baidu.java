package labuladong.SmallRedBook;

import java.util.Arrays;

/**
 * DATE: 2022/4/18
 * CREATE BY: Byx
 */
public class Baidu {
    public int find(int []nums){
        Arrays.sort(nums);

        int res = 0;
        if(nums.length <= 2) return 0;
        for (int i = nums.length -1; i >= 2; i--) {
            int left = 0;
            int right = i - 1;

            while (left < right){
                if(nums[left] + nums[right] <= nums[i]){
                    left++;
                }
                else if(nums[left] + nums[right] > nums[i]){
                    res += right - left;
                    right--;
                }

            }

        }
        return res;
    }
    public static void main(String[] args) {
        System.out.println(new Baidu().find(new int[]{4,2,3,4}));
    }
}
