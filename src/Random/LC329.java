package Random;

import java.util.HashMap;
import java.util.HashSet;

/**
 * DATE: 2022/5/19
 * CREATE BY: Byx
 */
public class LC329 {
    int [][]dir = new int[][]{{1,0}, {-1, 0},{0, 1},{0,-1}};
    int [][]visit;
    int res = 0;
    public int longestIncreasingPath(int[][] matrix) {
        visit = new int[matrix.length][matrix[0].length];

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                res = Math.max(DFS(i, j, matrix),res);
            }
        }

        return res;
    }

    public int DFS(int i, int j, int [][]matrix){

        if(visit[i][j] != 0) return visit[i][j];

        visit[i][j] = visit[i][j] + 1;


        for(int k = 0; k < 4; k++){
            int nx = i + dir[k][0];
            int ny = j + dir[k][1];
            if(nx < 0 || nx >= matrix.length || ny < 0 || ny >= matrix[0].length || matrix[nx][ny] <= matrix[i][j] ) continue;
            visit[i][j] = Math.max(1 + DFS(nx, ny, matrix), visit[i][j]);
        }
        return visit[i][j];

    }


    public static void main(String[] args) {
        //System.out.println(new LC329().longestIncreasingPath(new int[][]{{1,2,3},{8,9,4},{7,6,5}}));

        System.out.println(String.valueOf('A'));

    }
}
