package Random;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * DATE: 2022/5/31
 * CREATE BY: Byx
 */
public class Offer114 {
    public String alienOrder(String[] words) {
        ArrayList<Integer>[] graph = new ArrayList[26];
        int []degree = new int[26];
        int []visit = new int[26];
        //Arrays.fill(degree, -1);

        for(String str: words){
            for(int j = 0 ; j < str.length(); j++){
                visit[str.charAt(j) - 'a'] = 1;
            }
        }

        for(int i = 0; i < words.length -1; i++){
            String firstWord = words[i];
            String secondWord = words[i+1];
            int minLength = Math.min(firstWord.length(), secondWord.length());
            int j = 0;
            for(j = 0; j < minLength; j++){
                if(firstWord.charAt(j) != secondWord.charAt(j)){
                    break;
                }
            }
            if (j == firstWord.length() || j == secondWord.length()) {
                if(firstWord.length() <= secondWord.length()) continue;
                else{
                    return "";
                }
            }

            int index1 = firstWord.charAt(j) - 'a';
            int index2 = secondWord.charAt(j) - 'a';
            if(graph[index1] == null) graph[index1] = new ArrayList<Integer>();
            graph[index1].add(index2);
            degree[index2]++;
        }

        ArrayDeque<Integer> arry = new ArrayDeque<>();
        boolean flag = false;
        for( int i = 0; i < 26; i++){
            if(visit[i] == 1 && degree[i] == 0){
                flag = true;
                arry.offer(i);
            }
        }
        if(!flag) return "";
        StringBuilder res = new StringBuilder();
        while(!arry.isEmpty()){
            int curr = arry.poll();
            res.append((char)(curr + 'a'));
            visit[curr] = 2;
            ArrayList nodes = graph[curr];
            if(nodes == null) continue;
            for(int k = 0; k < nodes.size(); k++){
                int next = (int) nodes.get(k);
                degree[next]--;
                if(degree[next] == 0) arry.offer(next);
            }
        }

        for (int i = 0; i < degree.length; i++) {
            if(visit[i] == 1) return "";
        }
        return res.toString();


    }

    public static void main(String[] args) {
        System.out.println(new Offer114().alienOrder(new String[]{"z","x","a","zb","zx"}));
    }
}
