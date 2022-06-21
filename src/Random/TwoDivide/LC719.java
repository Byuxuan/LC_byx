package Random.TwoDivide;

import java.util.Arrays;

/**
 * DATE: 2022/6/16
 * CREATE BY: Byx
 */
public class LC719 {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int l = 0, r = nums[nums.length -1];
        while (l < r) {
            int mid = l + r >> 1;
            if (check(nums, mid) >= k) r = mid;
            else l = mid + 1;
        }
        return r;
    }
    int check(int[] nums, int x) {
        int n = nums.length, ans = 0;
        for (int i = 0, j = 1; i < n; i++) {
            while (j < n && nums[j] - nums[i] <= x) j++;
            ans += j - i - 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new LC719().smallestDistancePair(new int[]{1,3,1},1));
    }
}
