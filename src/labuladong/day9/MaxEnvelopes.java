package labuladong.day9;

import java.util.Arrays;
import java.util.Comparator;

/**
 * DATE: 2022/1/18
 * CREATE BY: Byx
 */
public class MaxEnvelopes {
    public int maxEnvelopes354(int[][] envelopes) {
        if(envelopes.length == 0) return 0;
        int n = envelopes.length;
        Arrays.sort(envelopes, (o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0]);

        int []dp = new int[n];
        Arrays.fill(dp, 1);
        int result = 1;
        for (int i = 1; i < envelopes.length; i++) {

            for (int k = 0; k < i ; k++) {
                dp[i] = envelopes[i][1] > envelopes[k][1] ? Math.max(dp[i], dp[k] + 1) : dp[i];
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    public int maxSubArray53(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int []dp = new int[nums.length];
        dp[0] = nums[0] > 0 ? nums[0] : dp[0];
        int result = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    public int longestCommonSubsequence1143(String text1, String text2) {
            if(text1.length() == 0 || text2.length() == 0) return 0;
            int[][] dp = new int[text1.length() + 1][text2.length() + 1];
            /**
             * dp[i][j] 以i,j 为结尾的最长公共子序列
             * dp[0][j] = 0 dp[i][0] = 0
             * dp[i][j] = s1[i] == s2[j] ? dp[i-1][j-1] + 1 : Math.max()
             */
            for (int i = 1; i <= text1.length(); i++) {
                for (int j = 1; j <= text2.length(); j++) {
                    int currMax = Math.max(dp[i - 1][j - 1], Math.max(dp[i - 1][j], dp[i][j - 1]));
                    dp[i][j] = text1.charAt(i - 1) == text2.charAt(j - 1) ? dp[i - 1][j - 1] + 1 : currMax;
                }
            }
            return dp[text1.length()][text2.length()];
        }

    public int minDistance583(String word1, String word2) {
        if (word1.length() == 0 || word2.length() == 0) {
            return word1.length() == 0 ? word2.length() : word1.length();
        }
        int [][]dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= word2.length(); i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                dp[i][j] = word1.charAt(i - 1) == word2.charAt(j - 1) ? dp[i - 1][j - 1] : Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
            }
        }
        return dp[word1.length()][word2.length()];
    }
    public int minimumDeleteSum712(String s1, String s2) {
        /**
         * a = 97
         */
        if (s1.length() == 0 || s2.length() == 0) {
            return s1.length() ==0? calSum(s2) : calSum(s1);
        }
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 1; i <= s1.length(); i++) {
            dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
        }
        for (int i = 1; i <= s2.length(); i++) {
            dp[0][i] = dp[0][i-1] + s2.charAt(i - 1);
        }
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                dp[i][j] = s1.charAt(i - 1) == s2.charAt(j - 1) ? dp[i - 1][j - 1] : Math.min(dp[i - 1][j] + s1.charAt(i - 1), dp[i][j - 1] + s2.charAt(j - 1));
            }
        }
        return dp[s1.length()][s2.length()];

    }

    private int calSum(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            sum += s.charAt(i);
        }
        return sum;
    }


    public static void main(String[] args) {
        //System.out.println(new MaxEnvelopes().maxEnvelopes354(new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}}));
        //System.out.println(new MaxEnvelopes().longestCommonSubsequence1143("bsb", "jkb"));
        System.out.println(new MaxEnvelopes().minDistance583("sea", "eat"));

    }
}
