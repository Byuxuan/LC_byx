package labuladong.day1;

import java.util.*;

/**
 * DATE: 2021/12/26
 * CREATE BY: Byx
 */
public class NQueue {
    public static List<List<String>> solveNQueens(int n) {
        int[][] result = new int[n][n];
        ArrayList<Integer> arrayLists = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            nQueueDFS(0, j, n, result, arrayLists);
        }
        int num = arrayLists.size() / (n * n);
        int index = 0;

        List<List<String>> finalResult = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            ArrayList<String> stringslist = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                StringBuilder currString = new StringBuilder();
                for (int k = 0; k < n; k++) {
                    currString.append(arrayLists.get(index) == 1 ? "Q" : ".");
                    index++;
                }
                String temp = currString.toString();
                stringslist.add(temp);
            }
            finalResult.add((List<String>) stringslist.clone());

        }

        return finalResult;
    }

    public static void nQueueDFS(int i, int j, int n, int[][] result, ArrayList<Integer> arrayLists) {
        if (i == n || j == n) {
            return;
        }

        if (i > 0) {
            for (int k = 0; k < i; k++) {
                if (result[k][j] == 1) {
                    return;
                }
            }
            for (int  p = i-1, q= j-1; p>=0 && q>=0; p--,q--){
                if (result[p][q] == 1) return;
            }
            for (int  p = i - 1, q= j + 1; p>=0 && q<n; p--,q++){
                if (result[p][q] == 1) return;
            }
        }
        result[i][j] = 1;
        if (i == n - 1) {
            for (int i1 = 0; i1 < n; i1++) {
                for (int i2 = 0; i2 < n; i2++) {
                    arrayLists.add(result[i1][i2]);
                }
            }
            result[i][j] = 0;
        }

        for (int k = 0; k < n; k++) {
            nQueueDFS(i + 1, k, n, result, arrayLists);
        }
        result[i][j] = 0;
    }


    public static void main(String[] args) {
        System.out.println(solveNQueens(5));

    }
}
