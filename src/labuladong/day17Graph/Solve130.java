package labuladong.day17Graph;

/**
 * DATE: 2022/2/20
 * CREATE BY: Byx
 */
public class Solve130 {
    class UF{
        private int count;
        private int []parent;
        private int []size;

        public UF(int n){
            this.count = n;
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public void union(int p, int q){
            int rootP = find(p);
            int rootQ = find(q);
            if(rootP == rootQ) return;
            if (size[rootP] > size[rootQ]) {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            }else {
                parent[rootP] = parent[rootQ];
                size[rootQ] += size[rootP];
            }
            count --;
        }

        public boolean connected(int p, int q) {
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
        public int count(){
            return count;
        }

    }
    public void solve(char[][] board) {
        if(board.length == 0) return;
        int m = board.length;
        int n = board[0].length;
        UF uf = new UF(n * m + 1);
        int dummy = m * n;
        for (int i = 0; i < m; i++) {
            if(board[i][0] == 'O'){
                uf.union(i * n, dummy);
            }
            if(board[i][n-1] == 'O'){
                uf.union(i * n + n - 1, dummy);
            }
        }
        for (int i = 0; i < n; i++) {
            if (board[0][i] == 'O') {
                uf.union(i, dummy);
            }
            if(board[m-1][i] == 'O'){
                uf.union((m-1) * n + i, dummy);
            }
        }
        int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (board[i][j] == 'O') {
                    for (int k = 0; k < 4; k++) {
                        int x = i + dir[k][0];
                        int y = j + dir[k][1];
                        if (board[x][y] == 'O') {
                            uf.union(x * n + y, i * n + j);
                        }
                    }
                }
            }
        }
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (!uf.connected(dummy, i * n + j)) {
                    board[i][j] = 'X';
                }
            }
        }
    }
}
