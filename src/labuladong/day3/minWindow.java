package labuladong.day3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.*;

/**
 * DATE: 2022/1/3
 * CREATE BY: Byx
 */
public class minWindow {
    public String minWindow(String s, String t){
        if(s.length() < t.length()){
            return "";
        }


        HashMap<Character, Integer> hashMap = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();
        for (int i = 0 ;i< t.length() ; i++){
            hashMap.put(t.charAt(i), hashMap.getOrDefault(t.charAt(i), 0) + 1);
        }
        int right = 0;
        int left = 0;
        int valid = 0;
        int start = 0;
        int LEN = Integer.MAX_VALUE;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (hashMap.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(hashMap.get(c))) {
                    valid++;
                }
            }

            while (valid == hashMap.size()){
                if (right - left < LEN) {
                    start = left;
                    LEN = right - left;
                }
                char d = s.charAt(left);
                left++;
                if (hashMap.containsKey(d)) {
                    if (window.get(d).equals(hashMap.get(d))) {
                        valid --;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return LEN == Integer.MAX_VALUE ? "" : s.substring(start, start + LEN);


    }


    public boolean checkInclusion(String s1, String s2) {
        if (s2.length() < s1.length()) {
            return false;
        }
        HashMap<Character, Integer> needs = new HashMap<>();
        HashMap<Character, Integer> windows = new HashMap<>();
        int left = 0, right = 0, start = 0;
        int LEN = Integer.MAX_VALUE;
        int valid = 0;
        for (int i = 0; i < s1.length(); i++) {
            needs.put(s1.charAt(i), needs.getOrDefault(s1.charAt(i), 0) + 1);
        }
        while (right < s2.length()) {
            char c = s2.charAt(right);
            right++;
            if (needs.containsKey(c)) {
                windows.put(c, windows.getOrDefault(c, 0) + 1);
                if (windows.get(c).equals(needs.get(c))) {
                    valid++;
                }
            }
            while (right - left >= s1.length()) {
                if (valid == needs.size()) {
                    return true;
                }
                char d = s2.charAt(left);
                left++;
                if (needs.containsKey(d)) {
                    if (needs.get(d).equals(windows.get(d))) {
                        valid--;
                    }
                    windows.put(d, windows.get(d) - 1);
                }

            }

        }
        return false;
    }
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) {
            return result;
        }
        HashMap<Character, Integer> needs = new HashMap<>();
        HashMap<Character, Integer> windows = new HashMap<>();
        int left = 0, right = 0, start = 0;
        int LEN = Integer.MAX_VALUE;
        int valid = 0;
        for (int i = 0; i < p.length(); i++) {
            needs.put(p.charAt(i), needs.getOrDefault(p.charAt(i), 0) + 1);
        }
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (needs.containsKey(c)) {
                windows.put(c, windows.getOrDefault(c, 0) + 1);
                if (windows.get(c).equals(needs.get(c))) {
                    valid++;
                }
            }
            while (right - left >= p.length()) {
                if (valid == needs.size()) {
                    result.add(left);

                }
                char d = s.charAt(left);
                left++;
                if (needs.containsKey(d)) {
                    if (needs.get(d).equals(windows.get(d))) {
                        valid--;
                    }
                    windows.put(d, windows.get(d) - 1);
                }

            }

        }
        return result;
    }

    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        int left = 0;
        int right = 0;
        int maxLength = Integer.MIN_VALUE;
        while (right < s.length()) {
            if (!hashMap.containsKey(s.charAt(right))) {
                hashMap.put(s.charAt(right), right);
                maxLength = Math.max(maxLength, hashMap.size());
                right++;
            }
            else {
                int index = hashMap.get(s.charAt(right));
                for (int i = left; i <= index; i++) {
                    hashMap.remove(s.charAt(i));
                }
                left = index + 1;
            }

        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(new minWindow().lengthOfLongestSubstring("aab"));
    }
}
