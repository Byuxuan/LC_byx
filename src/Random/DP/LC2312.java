package Random.DP;

/**
 * DATE: 2022/6/20
 * CREATE BY: Byx
 */
public class LC2312 {
    public long sellingWood(int m, int n, int[][] prices) {
        /**
         *  dp[i][j] 表示 切割大小为i * j 是最大的price
         *  dp[i][j] = price[i][j]
         *  dp[i][j] = Math.max(dp[k][j] + dp[i-k][j]) for k in (1, i)
         *  dp[i][j] = Math.max(dp[i][k] + dp[i][j - k]) for k in (1, j)
         */

        int[][] pri = new int[m + 1][n + 1];
        for (int[] price : prices) {
            pri[price[0]][price[1]] = price[2];
        }
        long[][] dp = new long[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = pri[i][j];
                for (int k = 1; k < i; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[k][j] + dp[i - k][j]);
                }
                for (int k = 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[i][j - k]);
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        System.out.println("abcdeeee".chars().filter(c -> c == 'e').count());

    }
}
