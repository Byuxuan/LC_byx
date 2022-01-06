package labuladong.day5;

import labuladong.TreeNode;

import java.util.HashMap;

/**
 * DATE: 2022/1/6
 * CREATE BY: Byx
 */
public class rob {

    public int rob1(int[] nums) {
        if(nums.length == 0) return  0;
        if(nums.length <= 2 ) {
            return nums.length == 1 ? nums[0] : Math.max(nums[0], nums[1]);
        }
        int dp[] = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], dp[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[nums.length - 1];

    }

    public int rob2(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        if(nums.length == 2) return Math.max(nums[0], nums[1]);

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        int result1 = 0;
        int result2 = 0;
        for (int i = 2; i < nums.length - 1; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        result1 = dp[nums.length - 2];
        dp[1] = nums[1];
        dp[2] = Math.max(nums[1], nums[2]);
        for (int i = 3; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        result2 = dp[nums.length - 1];
        return Math.max(result1, result2);


    }

    HashMap<TreeNode, Integer> hashMap = new HashMap<>();

    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }

//        if (hashMap.containsKey(root)) {
//            return hashMap.get(root);
//        }
//        int do_it = root.val + (root.left == null ? 0 : rob(root.left.left) + rob(root.left.right))
//                + (root.right == null ? 0 : rob(root.right.left) + rob(root.right.right));
//
//        int not_do = rob(root.left) + rob(root.right);
//
//        int res = Math.max(do_it, not_do);
//        hashMap.put(root, res);
//        return res;

        if (hashMap.containsKey(root)) {
            return hashMap.get(root);
        }
        int money = root.val;
        if(root.left != null){
            money +=  rob(root.left.left) + rob(root.left.right);
        }
        if(root.right != null){
            money +=  rob(root.right.left) + rob(root.right.right);
        }



        int res = Math.max(money, rob(root.left) + rob(root.right));
        hashMap.put(root, res);
        return res;
    }

    int rob2(TreeNode root) {
        int[] res = dp(root);
        return Math.max(res[0], res[1]);
    }

    /* 返回一个大小为 2 的数组 arr
    arr[0] 表示不抢 root 的话，得到的最大钱数
    arr[1] 表示抢 root 的话，得到的最大钱数 */
    int[] dp(TreeNode root) {
        if (root == null)
            return new int[]{0, 0};
        int[] left = dp(root.left);
        int[] right = dp(root.right);
        // 抢，下家就不能抢了
        int rob = root.val + left[0] + right[0];
        // 不抢，下家可抢可不抢，取决于收益大小
        int not_rob = Math.max(left[0], left[1])
                + Math.max(right[0], right[1]);

        return new int[]{not_rob, rob};
    }
}
