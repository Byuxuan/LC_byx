package labuladong.day11;

/**
 * DATE: 2022/1/27
 * CREATE BY: Byx
 */
public class canPartition416 {
    public boolean canPartition2(int[] nums) {
        if (nums.length == 0) return true;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) return false;
        sum = sum / 2;
        boolean[][] dp = new boolean[nums.length + 1][sum + 1];

        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j <= sum; j++) {
                if (j < nums[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                    continue;
                }
                dp[i][j] = (dp[i - 1][j] || dp[i - 1][j - nums[i - 1]]);
            }
        }
        return dp[nums.length][sum];
    }

    public boolean canPartition(int[] nums) {
        if (nums.length == 0) return true;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) return false;
        sum = sum / 2;
        boolean[] dp = new boolean[sum + 1];

        dp[0] = true;
        for (int i = 1; i <= nums.length; i++) {
            for (int j = sum; j >= 0; j--) {
                if (j < nums[i - 1]) {
                    dp[j] = dp[j];
                    continue;
                }
                dp[j] = (dp[j] || dp[j - nums[i - 1]]);
            }
        }

        return dp[sum];
    }

    public int change(int amount, int[] coins) {
        if (coins.length == 0) return 0;
        int[][] dp = new int[coins.length + 1][amount + 1];
        for (int i = 0; i <= coins.length; i++) {
            dp[i][0] = 1;
        }
        /**
         * dp[i][j] 用第i 个硬币能凑成 j 的个数
         * dp[i][j] = dp[i-1][j - k*num[i] ]  + k*num[i]
         */
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                if (j - coins[i - 1] >= 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[coins.length][amount];
    }

    public int minPathSum64(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int[][] dp = new int[grid.length][grid[0].length];
        /**
         * dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j]
         */
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 || j == 0) {
                    if (i == 0 && j == 0) {
                        dp[i][j] = grid[i][j];
                        continue;
                    } else {
                        dp[i][j] = i == 0 ? dp[i][j - 1] + grid[i][j] : dp[i - 1][j] + grid[i][j];
                        continue;
                    }
                }
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[grid.length][grid[0].length];
    }

    public int calculateMinimumHP(int[][] dungeon) {
        /**
         * dp[i][j] 表示从 i,j 到终点至少需要多少
         */
        if (dungeon.length == 0) return 0;
        int[][] dp = new int[dungeon.length][dungeon[0].length];
        dp[dungeon.length - 1][dungeon[0].length - 1] = dungeon[dungeon.length - 1][dungeon[0].length - 1] > 0 ? 0 : -dungeon[dungeon.length - 1][dungeon[0].length - 1];
        for (int i = dungeon.length - 1; i >= 0; i--) {
            for (int j = dungeon[0].length - 1; j >= 0; j--) {
                if (i == dungeon.length - 1 || j == dungeon[0].length - 1) {
                    if (i == dungeon.length - 1 && j == dungeon[0].length - 1) {
                        continue;
                    }
                    dp[i][j] = i == dungeon.length - 1 ? Math.max(dp[i][j + 1] - dungeon[i][j], 0) : Math.max(dp[i + 1][j] - dungeon[i][j], 0);
                    continue;
                }
                System.out.println(i+ " "+ j);
                dp[i][j] = Math.max(Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j], 0);
            }
        }
        return dp[0][0] + 1;
    }

    public static void main(String[] args) {
        //System.out.println(new canPartition416().canPartition(new int[]{1,2,5}));
        //System.out.println(new canPartition416().change(5, new int[]{1, 2, 5}));
        System.out.println(new canPartition416().calculateMinimumHP(new int[][]{{2}, {1}}));

    }
}
