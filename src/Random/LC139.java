package Random;

/**
 * DATE: 2022/5/15
 * CREATE BY: Byx
 */
import java.util.*;
public class LC139 {
    public int []memo;

    public boolean wordBreak(String s, List<String> wordDict) {
        memo = new int[s.length() +1];
        Arrays.fill(memo, -1);
        return dp(s, 0, wordDict);
    }

    public boolean dp(String s, int i, List<String> wordDict){
        if(i == s.length()) return true;
        if(memo[i] != -1) return memo[i] == 1;
        for(String word : wordDict){
            int len = word.length();
            if(i + len > s.length()) continue;
            String subString = s.substring(i, i+len);
            if(!subString.equals(word)) continue;
            if(dp(s, i+len, wordDict)){
                memo[i] = 1;
                return true;
            }
        }
        memo[i] = 0;
        return false;
    }

    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
        input.add("leet");
        input.add("code");

        System.out.println(new LC139().wordBreak("leetcode", input));
    }
}

