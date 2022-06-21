package Random;

/**
 * DATE: 2022/6/5
 * CREATE BY: Byx
 */
public class LC6090 {
    public int minMaxGame(int[] nums) {
        while (nums.length > 1) {
            int n = nums.length;
            int[] newN = new int[n / 2];
            for (int i = 0; i < n / 2; i++) {
                if (i % 2 == 0) newN[i] = Math.min(nums[i * 2], nums[2 * i + 1]);
                else newN[i] = Math.max(nums[i * 2], nums[2 * i + 1]);
            }
            nums = newN;
        }
        return nums[0];


    }
    public static void main(String[] args) {
        System.out.println(new LC6090().minMaxGame(new int[]{1, 3, 5, 2}));
    }
}
