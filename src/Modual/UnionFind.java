package Modual;

/**
 * DATE: 2022/5/28
 * CREATE BY: Byx
 */
public class UnionFind {
    private int count; // 连通分量
    private int []parent;
    private int []size;

    public UnionFind(int n){
        this.count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }
        if (size[rootP] > size[rootQ]) {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }else{
            parent[rootQ] = rootP;
            size[rootQ] += size[rootP];
        }
        count--;

    }

    private int find(int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    public int count(){
        return count;
    }

    public boolean connected(int p, int q){
        int rootP = find(p);
        int rootQ = find(q);
        return rootP == rootQ;
    }
}
