package labuladong.day1;

/**
 * DATE: 2021/12/26
 * CREATE BY: Byx
 */
public class Fib {
    public static int Fib1(int n) {
        if (n <= 2) return 1;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static int Fib2(int n) {
        if (n <= 2) return 1;
        int curr = 1, prev = 1;
        int sum;
        for (int i = 3; i <= n; i++) {
            sum = curr + prev;
            prev = curr;
            curr = sum;
        }

        return curr;
    }

    public static void main(String[] args) {
        System.out.println(Fib1(10));
        System.out.println(Fib2(10));
    }
}
