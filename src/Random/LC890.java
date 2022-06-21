package Random;

import java.util.*;

/**
 * DATE: 2022/6/12
 * CREATE BY: Byx
 */
public class LC890 {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        HashMap<Character, Integer> patt = new HashMap<>();
        StringBuilder string = new StringBuilder();
        int curr = 0;
        for(int i = 0; i < pattern.length(); i++){
            if(patt.containsKey(pattern.charAt(i))){
                string.append(patt.get(pattern.charAt(i)));
            }else{
                patt.put(pattern.charAt(i), curr);
                string.append(curr);
                curr++;
            }
        }
        List<String> res = new ArrayList<>();
        for(String word: words){
            if(word.length() != pattern.length()) continue;
            HashMap<Character, Integer> map = new HashMap<>();
            StringBuilder match = new StringBuilder();
            int index = 0;
            for(int i = 0; i < word.length(); i++){
                if(map.containsKey(word.charAt(i))){
                    match.append(map.get(word.charAt(i)));
                }else{
                    map.put(word.charAt(i), index);
                    index++;
                }
            }
            if(string.toString().equals(match.toString())){
                res.add(word);
            }


        }
        return res;
    }

    public List<String> findAndReplacePattern2(String[] ws, String pe) {
        List<String> ans = new ArrayList<>();
        int[] map = new int[26], vis = new int[26];
        for(String s: ws){
            Arrays.fill(map, -1);
            Arrays.fill(vis, -1);
            boolean flag = true;
            for(int i = 0; flag && i < s.length(); i++){
                int first = s.charAt(i) - 'a';
                int second = pe.charAt(i) - 'a';
                if(map[first] == -1 && vis[second] == -1){
                    map[first] = second;
                    vis[second] = 1;
                }else{
                    if(map[first] != second) flag = false;
                }
            }
            if(flag) ans.add(s);

        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new LC890().findAndReplacePattern2(new String[]{"abc","deq","mee","aqq","dkd","ccc"}, "abb"));
    }
}
