package labuladong.day17Graph;

/**
 * DATE: 2022/2/20
 * CREATE BY: Byx
 * 785
 */
public class isBipartition {
    private boolean ok = true;
    private boolean[] color;
    private boolean[] visit;
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        color = new boolean[n];
        visit = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                traverse(graph, i);
            }
        }
        return ok;

    }

    private void traverse(int[][] graph, int i) {
        if(!ok) return;
        visit[i] = true;
        for (int w : graph[i]) {
            if (!visit[w]) {
                color[w] = !color[i];
                traverse(graph, w);
            }else {
                if (color[w] == (color[i])) {
                    ok = false;
                }
            }
        }
    }
}
