package labuladong.day8;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * DATE: 2022/1/17
 * CREATE BY: Byx
 */
public class lengthOfLIS300 {
    public int lengthOfLIS(int[] nums){
        if(nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        int result = Integer.MIN_VALUE;
        Arrays.fill(dp,1);
        for (int i = 1; i < nums.length; i++) {
            int j = i - 1;
            while (j>=0){
                if(nums[j] < nums[i]) dp[i] = Math.max(dp[i], dp[j] + 1);
                j--;
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    public int lengthOfLIS2(int[] nums){
        if (nums.length == 0) return 0;
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > arrayList.get(arrayList.size() - 1)) {
                arrayList.add(nums[i]);
            }else {
                int pos = binary(arrayList, nums[i]);
                arrayList.set(pos, nums[i]);
            }
        }
        return arrayList.size();
    }

    private int binary(ArrayList<Integer> arrayList, int num) {
        int left = 0;
        int right = arrayList.size() - 1;
        while (left <= right) { // left = right + 1
            int mid = left + (right - left) / 2;
            if (arrayList.get(mid) > num){
                right = mid - 1;
            } else if (arrayList.get(mid) < num) {
                left = mid + 1;
            }else {
                right = mid - 1; // 查找左边第一个比 num 大的数
            }
        }

        return left;

    }


    public static void main(String[] args) {
        System.out.println(new lengthOfLIS300().lengthOfLIS2(new int[]{0,1,0,2,3}));
    }
}
