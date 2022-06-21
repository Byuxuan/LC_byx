package Modual;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import labuladong.ListNode;

import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * DATE: 2022/5/28
 * CREATE BY: Byx
 */


public class DynamicSegmentTree {
    public static void main(String[] args) {
        //System.out.println(new DynamicSegmentTree().largestWordCount(new String[]{"Hello userTwooo","Hi userThree","Wonderful day Alice","Nice day userThree"}, new String[]{"Alice","userTwo","userThree","Alice"}));
        //System.out.println(new DynamicSegmentTree().maxCoins(new int[]{2, 4, 1, 2, 7, 8}));
        //System.out.println(new DynamicSegmentTree().validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334:"));
        //System.out.println(new DynamicSegmentTree().rearrangeCharacters("ntxehfzzf","afxkznz"));
        //System.out.println(new DynamicSegmentTree().discountPrices("there are $1 $2 and 5$ candies in the shop", 50));
        //System.out.println(new DynamicSegmentTree().totalSteps(new int[]{7,2,3,4,5,1,2,3,6}));
        System.out.println(new DynamicSegmentTree().minimumObstacles(new int[][]{{0, 1, 1}, {1, 1, 0}, {1, 1, 0}}));

    }

    public int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int res = 0;
        int []bigger = new int[piles.length/3 * 2];
        for(int i= 0;i< bigger.length;i++){
            bigger[i] = piles[i + piles.length/3];
        }



        for(int i = bigger.length -2; i>=0; i = i-2){
            res += bigger[i];
        }

        return res;
    }


    public String validIPAddress(String queryIP) {
        if(queryIP.charAt(queryIP.length()-1) == '.'|| queryIP.charAt(queryIP.length()-1) == ':') return "Neither";
        String []strings1 = queryIP.split("\\.");
        String []strings2 = queryIP.split(":");
        if(strings1.length != 4 && strings2.length != 8) return "Neither";
        if(strings1.length == 4){

            for(int i = 0; i < strings1.length; i++){
                try {
                    int curr = Integer.parseInt(strings1[i]);
                    if(curr < 0 || curr> 255) return "Neither";
                    if(!(""+curr).equals(strings1[i])) return "Neither";
                } catch (Exception e) {
                    return "Neither";
                }
            }
            return "IPv4";
        }else{
            for (int i = 0; i < strings2.length; i++) {
                for (int j = 0; j < strings2[i].length(); j++) {
                    if( strings2[i].charAt(j) >= '0' && strings2[i].charAt(j) <= '9' ||
                        strings2[i].charAt(j) >= 'a' && strings2[i].charAt(j) <= 'f' ||
                        strings2[i].charAt(j) >= 'A' && strings2[i].charAt(j) <= 'F' ) continue;
                    return "Neither";
                }

            }
            return "IPv6";
        }
    }

    public int rearrangeCharacters(String s, String target) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        HashMap<Character, Integer> curr = new HashMap<>();
        for (int i = 0; i < target.length(); i++) {
            hashMap.put(target.charAt(i), hashMap.getOrDefault(target.charAt(i), 0) + 1);
            curr.put(target.charAt(i), 0);
        }
        int left = 0;
        int right = 0;
        int valid = 0;
        int res = 0;

        while (left <= s.length() - target.length()) {
            if (hashMap.containsKey(s.charAt(left))) {
                right = left;
                curr.clear();
                while (valid < target.length() && right < s.length()) {
                    if (hashMap.containsKey(s.charAt(right)) && curr.getOrDefault(s.charAt(right), 0) < hashMap.get(s.charAt(right))) {
                        curr.put(s.charAt(right), curr.getOrDefault(s.charAt(right), 0) + 1);
                        valid++;
                    }
                    right++;
                }
                if (valid == target.length()) {
                    res += 1;
                    left = right;
                    valid = 0;
                }else {
                    left++;
                }
            }else{
                left++;
            }
        }
        return res;



    }

    public String discountPrices(String sentence, int discount) {
        String[] sentences = sentence.split(" ");
        for (int i = 0; i < sentences.length; i++) {
            if (sentences[i].charAt(0) == '$') {
                String sub = sentences[i].substring(1);
                Double subNum = Double.parseDouble(sub);
                subNum = subNum * (100 - discount) / 100;
                sentences[i] = "$"+String.format("%.2f", subNum);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < sentences.length - 1; i++) {
            stringBuilder.append(sentences[i]);
            stringBuilder.append(" ");
        }
        stringBuilder.append(sentences[sentences.length - 1]);
        return stringBuilder.toString();
    }

    public int totalSteps2(int[] nums) {
        int []visit = new int[nums.length];
        int res = 0;

        while (true) {
            boolean flag = true;
            for (int i = 1; i < nums.length; i++) {
                if(visit[i] == 2) continue;
                for (int j = i-1; j >= 0 ; j--) {
                    if(visit[j] == 2) continue;
                    if (nums[j] > nums[i]) {
                        visit[i] = 1;
                        flag = false;
                    }
                    break;
                }

            }
            for (int j = 0; j < nums.length; j++) {
                visit[j] = visit[j] == 1 ? 2 : visit[j];
            }
            if(flag){
                break;
            }
            res++;
        }
        return  res;

    }

    public int totalSteps(int[] nums) {

        int res = 0;
        int []f = new int[nums.length];
        ArrayList<Integer> arry = new ArrayList<>();
        for(int i = 0; i< nums.length;i++){
            int cur = 0;

            while(!arry.isEmpty() && nums[arry.get(0)] <= nums[i]){
                cur = Math.max(cur, f[arry.get(0)]);
                arry.remove(0);
            }
            if(!arry.isEmpty()){
                res = Math.max(res, cur +1 );
                f[i] = cur +1;
            }
            arry.add(0, i);
        }
        return res;

    }

    int[][] direct = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int minimumObstacles(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        PriorityQueue<int[]> arrayList = new PriorityQueue<>((o1, o2) -> (dp[o1[0]][o1[1]] - dp[o2[0]][o2[1]]));
        int[][] visit = new int[grid.length][grid[0].length];
        arrayList.offer(new int[]{0, 0});
        visit[0][0] = 1;
        while (!arrayList.isEmpty()) {
            int[] curr = arrayList.poll();
            int x = curr[0];
            int y = curr[1];

            for (int i = 0; i < direct.length; i++) {
                int nx = direct[i][0] + x;
                int ny = direct[i][1] + y;
                if(nx < 0 || nx >= grid.length || ny < 0 || ny >= grid[0].length || visit[nx][ny] != 0) continue;
                dp[nx][ny] = dp[x][y] + grid[nx][ny];
                visit[nx][ny] = 1;
                arrayList.offer(new int[]{nx, ny});
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }

}

class Mypair{
    public String name;
    public int num;
    Mypair(String name, int num){
        this.name = name;
        this.num = num;
    }
}