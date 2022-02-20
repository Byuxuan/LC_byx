package labuladong.day17Graph;

import javax.swing.plaf.IconUIResource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Queue;

/**
 * DATE: 2022/2/20
 * CREATE BY: Byx
 */
public class minCost1584 {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        ArrayList<int[]> edges = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < n; j++) {
                int x1 = points[i][0];
                int y1 = points[i][1];
                int x2 = points[j][0];
                int y2 = points[j][1];
                edges.add(new int[]{i, j, Math.abs(x1 - x2) + Math.abs(y1 - y2)});
            }
        }
        Collections.sort(edges, Comparator.comparingInt(a -> a[2]));

        int mst = 0;
        UF uf = new UF(n);
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];
            if (uf.isConnect(u, v)) {
                continue;
            }
            mst += weight;
            uf.union(u, v);
        }


        return mst;
    }
    static class UF{
        private int[] parent;
        private int[] size;
        private int count;
        UF(int n){
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
            this.count = n;
        }
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (size[rootP] > size[rootQ]) {
                parent[rootQ] = rootP;
                size[rootQ] += size[rootP];
            }else {
                parent[rootP] = rootQ;
                size[rootP] += size[rootQ];
            }
            count --;
        }
        public boolean isConnect(int p, int q){
            int rootP = find(p);
            int rootQ = find(q);
            return rootP == rootQ;
        }
        private int find(int p) {
            while (parent[p] != p) {
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }

    }
}
