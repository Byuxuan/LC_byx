package labuladong.day20;

import java.util.Arrays;

/**
 * DATE: 2022/2/24
 * CREATE BY: Byx
 */
public class exist79 {
    boolean res = false;
    int[][] flag = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public boolean exist(char[][] board, String word) {

        int[][] visit = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (word.charAt(0) == board[i][j]) {
                    DFS(visit, board, word, 0, i, j);
                }
            }
        }
        return res;
    }

    private void DFS(int[][] visit, char[][] board, String word, int currNum, int i, int j) {
        if (res || currNum == word.length() -1 ) {
            res = true;
            return;
        }
        visit[i][j] = 1;
        for (int k = 0; k < flag.length; k++) {
            int currX = i + flag[k][0];
            int currY = j + flag[k][1];
            if (currX < 0 || currX >= board.length || currY < 0 || currY >= board[0].length || visit[currX][currY] == 1) {
                continue;
            }
            if (board[currX][currY] == word.charAt(currNum + 1 )) {
                DFS(visit, board, word, currNum + 1, currX, currY);
            }
        }
        visit[i][j] = 0;

    }


    public int[] numSquares(int n) {
        int[] f = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int minn = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                minn = Math.min(minn, f[i - j * j]);
            }
            f[i] = minn + 1;
        }
        return f;
    }


    public void moveZeroes(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                continue;
            }
            int index = i;
            for (int j = index + 1; j < nums.length; j++) {
                if (nums[j] != 0) {
                    int temp = nums[index];
                    nums[index] = nums[j];
                    nums[j] = temp;
                    index = j;
                }
            }
        }
    }
    public static void main(String[] args) {
        //System.out.println(new exist79().exist(new char[][]{{'C','B','C'},{'S','F','C'}},"BCCF"));
        System.out.println(Arrays.toString(new exist79().numSquares(19)));

    }

}
