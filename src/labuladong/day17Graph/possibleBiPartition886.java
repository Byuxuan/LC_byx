package labuladong.day17Graph;

import java.util.ArrayList;

/**
 * DATE: 2022/2/20
 * CREATE BY: Byx
 */
public class possibleBiPartition886 {
    private boolean[] color;
    private boolean[] visited;
    private boolean ok = true;

    public boolean possibleBipartition(int n, int[][] dislikes) {
        ArrayList[] disGraph = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            disGraph[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < dislikes.length; i++) {
            disGraph[dislikes[i][0]].add(dislikes[i][1]);
            disGraph[dislikes[i][1]].add(dislikes[i][0]);
        }
        color = new boolean[n];
        visited = new boolean[n];
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                tranverse(disGraph, i);
            }
        }
        return ok;


    }

    private void tranverse(ArrayList<Integer>[] disGraph, int i) {
        if(!ok) return;
        visited[i] = true;
        for (int w : disGraph[i]) {
            if (!visited[w]) {
                color[w] = !color[i];
                visited[w] = true;
            }else {
                if (color[w] == color[i]) {
                    ok = false;
                }
            }
        }

    }
}
