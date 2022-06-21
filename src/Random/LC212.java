package Random;

import java.util.*;

/**
 * DATE: 2022/5/20
 * CREATE BY: Byx
 */
public class LC212 {
    List<String> res;
    HashMap<Character, Integer> hash;
    public List<String> findWords(char[][] board, String[] words) {
        hash = new HashMap<>();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                hash.put(board[i][j], hash.getOrDefault(board[i][j],0) + 1);
            }
        }


        res = new ArrayList<>();
        for(String word: words){
            if(!judge(word)) continue;
            boolean flag = false;
            for(int i = 0; !flag && i < board.length; i++){
                for(int j = 0; !flag && j < board[i].length; j++){
                    if(find(board, word, i, j, 0)){
                        res.add(word);
                        flag = true;
                    }
                }
            }

        }
        return res;


    }

    public boolean find(char[][] board, String word, int i, int j, int k){
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || word.charAt(k) != board[i][j]) return false;
        if(k == word.length() -1) return true;
        board[i][j] = '\0';
        boolean res = find(board, word, i + 1, j, k+1) || find(board, word, i - 1, j, k+1) ||
                find(board, word, i, j + 1, k+1) || find(board, word, i, j -1, k+1);
        board[i][j] = word.charAt(k);
        return res;
    }

    public boolean judge(String word){
        int []nums = new int[26];
        for(int i = 0; i < word.length(); i++){
            nums[word.charAt(i) - 'a']++;
        }
        for(int i = 0; i < 26; i++){
            if(nums[i] == 0) continue;
            if(nums[i] > hash.getOrDefault((char)(i + 'a'),0)) return false;
        }
        return true;
    }

    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public List<String> findWords2(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        Set<String> ans = new HashSet<String>();
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                dfs(board, trie, i, j, ans);
            }
        }

        return new ArrayList<String>(ans);
    }

    public void dfs(char[][] board, Trie now, int i1, int j1, Set<String> ans) {
        if (!now.children.containsKey(board[i1][j1])) {
            return;
        }
        char ch = board[i1][j1];
        now = now.children.get(ch);
        if (!"".equals(now.word)) {
            ans.add(now.word);
        }

        board[i1][j1] = '#';
        for (int[] dir : dirs) {
            int i2 = i1 + dir[0], j2 = j1 + dir[1];
            if (i2 >= 0 && i2 < board.length && j2 >= 0 && j2 < board[0].length) {
                dfs(board, now, i2, j2, ans);
            }
        }
        board[i1][j1] = ch;
    }
}

class Trie {
    String word;
    Map<Character, Trie> children;
    boolean isWord;

    public Trie() {
        this.word = "";
        this.children = new HashMap<Character, Trie>();
    }

    public void insert(String word) {
        Trie cur = this;
        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            if (!cur.children.containsKey(c)) {
                cur.children.put(c, new Trie());
            }
            cur = cur.children.get(c);
        }
        cur.word = word;
    }




    public static void main(String[] args) {
        System.out.println(new LC212().findWords(new char[][]{{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}},new String[]{"oath"}));
    }
}
