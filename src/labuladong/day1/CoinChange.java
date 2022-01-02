package labuladong.day1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * DATE: 2021/12/26
 * CREATE BY: Byx
 */
public class CoinChange {
    public static int coinChage(int[] coins, int amount) {
        if (coins.length <= 0 || amount < 0) {
            return -1;
        }
        if (amount == 0) return 0;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        Arrays.sort(coins);
        for (int i = 0; i <= amount; i++) {
            boolean flag = true;
            for (int j = coins.length - 1; flag && j >= 0; j--) {
                if (i < coins[j]) {
                    continue;
                } else if (i == coins[j]) {
                    dp[i] = 1;
                    flag = false;
                }
                else {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                    flag = false;
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];

    }

    public static void main(String[] args) {
        System.out.println(coinChage(new int[]{1,2,5}, 11));
    }
}
