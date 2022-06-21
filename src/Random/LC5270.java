package Random;

import java.util.Arrays;
import java.util.HashMap;

/**
 * DATE: 2022/6/12
 * CREATE BY: Byx
 */
public class LC5270 {
    public int minPathCost(int[][] grid, int[][] moveCost) {
        int[][] dp = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid[0].length; i++) {
            dp[0][i] = grid[0][i];
        }

        for (int i = 1; i < grid.length; i++) {

            for (int j = 0; j < grid[i].length; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k <grid[i].length ; k++) {
                    int curr1 = dp[i-1][k];
                    int cost = moveCost[grid[i - 1][k]][j];
                    dp[i][j] = Math.min(dp[i][j], curr1 + cost);
                }
                dp[i][j] += grid[i][j];
            }
        }

        int res = Integer.MAX_VALUE;

        for (int i = 0; i < grid[0].length; i++) {
            res = Math.min(res, dp[grid.length - 1][i]);
        }

        return  res;



    }

    public static void main(String[] args) {
        System.out.println(new LC5270().minPathCost(new int[][]{{5,3},{4,0},{2,1}},new int[][] {{9,8},{1,5},{10,12},{18,6},{2,4},{14,3}}));
    }
}
