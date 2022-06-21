package labuladong.day32;

import java.util.*;

/**
 * DATE: 2022/3/30
 * CREATE BY: Byx
 */
public class splitArray410 {
    public List<List<String>> res = new ArrayList<>();
    public List<Integer> temp = new ArrayList<>();
    public  List<List<String>> solveNQueens(int n) {
        int []visit = new int[n];
        for(int i = 0; i < n; i++){
            DFS(i, visit, 0, n);
        }
        return res;
    }
    public void DFS(int index, int []visit, int layer, int n){
        visit[index] = 1;
        temp.add(index);

        if(layer == n - 1){
            List<String> result = new ArrayList<>();
            for(int i = 0; i < temp.size(); i++){
                String currString = copyString(n);
                currString = currString.substring(0, temp.get(i)) + "Q" + currString.substring(temp.get(i) +1);
                result.add(currString);
            }
            res.add(result);
        }else{
            for(int i = 0; i < n; i++){
                boolean flag = false;
                for(int j = 0; j < temp.size(); j++){
                    if ((layer + 1 - j == i - temp.get(j)) || (layer + 1 - j == temp.get(j) - i)) {
                        flag = true;
                    }
                }
                if (flag || visit[i] == 1) {
                    continue;
                }
                DFS(i, visit, layer + 1, n);
            }
        }
        visit[index] = 0;
        temp.remove(temp.size() -1);
    }

    private String copyString(int n) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            stringBuilder.append("*");
        }
        return stringBuilder.toString();
    }


    public List<List<String>> solveNQueens2(int n) {
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        List<List<String>> solutions = new ArrayList<List<String>>();
        solve(solutions, queens, n, 0, 0, 0, 0);
        return solutions;
    }

    public void solve(List<List<String>> solutions, int[] queens, int n, int row, int columns, int diagonals1, int diagonals2) {
        if (row == n) {
            List<String> board = generateBoard(queens, n);
            solutions.add(board);
        } else {
            int availablePositions = ((1 << n) - 1) & (~(columns | diagonals1 | diagonals2));
            while (availablePositions != 0) {
                int position = availablePositions & (-availablePositions);
                availablePositions = availablePositions & (availablePositions - 1);
                int column = Integer.bitCount(position - 1);
                queens[row] = column;
                solve(solutions, queens, n, row + 1, columns | position, (diagonals1 | position) << 1, (diagonals2 | position) >> 1);
                queens[row] = -1;
            }
        }
    }

    public List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }


    public int largestRectangleArea(int[] heights) {
        int []left = new int[heights.length];
        int []right = new int[heights.length];
        Stack<Integer> mome = new Stack<>(); // 递增
        for(int i = 0; i < heights.length; i++){
            while(!mome.isEmpty() && heights[i] < heights[mome.peek()]){
                mome.pop();
            }
            left[i] = mome.isEmpty()? -1 : mome.peek();
            mome.push(i);
        }
        mome.clear();

        for(int i = heights.length -1; i >=0; i--){
            while(!mome.isEmpty() && heights[i] < heights[mome.peek()]){
                mome.pop();
            }
            right[i] = mome.isEmpty()? heights.length : mome.peek();
            mome.push(i);
        }
        int res = 0;
        for(int i = 0; i < heights.length; i++){
            int curr = (right[i] - left[i]) * heights[i];
            res = Math.max(res, curr);
        }
        return res;
    }

    boolean[][] f;
    List<List<String>> ret = new ArrayList<List<String>>();
    List<String> ans = new ArrayList<String>();
    int n;

    public List<List<String>> partition(String s) {
        n = s.length();
        f = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(f[i], true);
        }

        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = (s.charAt(i) == s.charAt(j)) && f[i + 1][j - 1];
            }
        }

        dfs(s, 0);
        return ret;
    }

    public void dfs(String s, int i) {
        if (i == n) {
            ret.add(new ArrayList<String>(ans));
            return;
        }
        for (int j = i; j < n; ++j) {
            if (f[i][j]) {
                ans.add(s.substring(i, j + 1));
                dfs(s, j + 1);
                ans.remove(ans.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new splitArray410().partition("aab"));

    }
}
