package labuladong.day10;

import java.util.HashMap;
import java.util.HashSet;

/**
 * DATE: 2022/1/19
 * CREATE BY: Byx
 */
public class LongestPalindrome {
    public int longestPalindrome409(String s){
        HashSet<Character> hashSet = new HashSet<>();
        int maxOdds = 0;
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            if (hashSet.contains(s.charAt(i))) {
                hashSet.remove(s.charAt(i));
                sum += 2;
            }
            else {
                hashSet.add(s.charAt(i));
            }
        }


        return sum + (hashSet.isEmpty() ? 0 : 1);

    }

    public int minDistance(String word1, String word2) {
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
        return dp[word1.length()][word2.length()] / 2;
    }
    public int longestPalindromeSubseq516(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            stringBuilder.insert(0, s.charAt(i));
        }
        String sReverse = stringBuilder.toString();
        return s.length() - minDistance(s, sReverse);

    }


    public int longestPalindromeSubseq5162(String s) {
        int[][] dp = new int[s.length()][s.length()];
        /**
         * dp[i][j] = s[i] == s[j] ? dp[i+1][j-1] + 2 : Math.max(dp[i+1][j], dp[i][j-1])
         */

        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = 1;
        }
        for (int i = s.length() -2; i >=0 ; i--) {
            for (int j = i + 1; j < s.length(); j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) ? dp[i + 1][j - 1] + 2 : Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }
        return dp[0][s.length() - 1];

    }



    public static void main(String[] args) {
        //System.out.println(new LongestPalindrome().longestPalindrome409("civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth"));
        System.out.println(new LongestPalindrome().longestPalindromeSubseq5162("bbbab"));
    }
}
