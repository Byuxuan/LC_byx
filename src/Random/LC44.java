package Random;

import java.util.Arrays;

/**
 * DATE: 2022/5/15
 * CREATE BY: Byx
 */
public class LC44 {
    int [][]memo;
    public boolean isMatch(String s, String p) {
        memo = new int[s.length() + 1][p.length() + 1];
        for (int i = 0; i < memo.length; i++) {
            Arrays.fill(memo[i], -1);
        }

        return find(s, 0, p, 0);
    }

    public boolean find(String s, int i, String p, int j){
        if(i == s.length() && j == p.length()) return true;
        if(i == s.length()){
            for(int k = j; k < p.length(); k++){
                if(p.charAt(k) != '*'){
                    return false;
                }
            }
            return true;
        }

        if(j == p.length()) return false;

        if(memo[i][j] != -1){
            return memo[i][j] == 1;
        }

        boolean res = false;
        if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '?'){
            res = find(s, i + 1, p, j +1);
        }else if(p.charAt(j) == '*'){
            res = find(s, i+1, p, j) || find(s, i, p, j+1);
        }

        memo[i][j] = res ? 1 : 0;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new LC44().isMatch("aa", "*"));
    }
}
