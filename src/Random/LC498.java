package Random;

import java.util.Arrays;

/**
 * DATE: 2022/6/14
 * CREATE BY: Byx
 */
public class LC498 {
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int x = 0;
        int y = 0;
        int []res = new int[m * n];
        int index = 0;
        boolean flag = true;
        int k = 0;
        while(true){

            if(flag){
                x = k >= m ? m-1 : k;
                y = k - x;
            }else{
                y = k >= n ? n-1 : k;
                x = k - y;
            }


            while(x >= 0 && y >= 0 && x < m && y < n){
                res[index++] = mat[x][y];
                if(flag){
                    x--;
                    y++;
                }else{
                    x++;
                    y--;
                }
            }

            flag = !flag;
            k++;
            if(k > (m + n -1)) break;

        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new LC498().findDiagonalOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}})));
    }
}
