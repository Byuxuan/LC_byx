package Random;

/**
 * DATE: 2022/6/20
 * CREATE BY: Byx
 */
public class LC2311 {
    public int longestSubsequence(String s, int k) {
        int n = s.length(), m = 32 - Integer.numberOfLeadingZeros(k);
        if (n < m) return n;
        int ans = Integer.parseInt(s.substring(n - m), 2) <= k ? m : m - 1;
        return ans + (int) s.substring(0, n - m).chars().filter(c -> c == '0').count();
    }

    public static void main(String[] args) {
        System.out.println(new LC2311().longestSubsequence("1001010", 5));
    }

}
