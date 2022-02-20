package labuladong.day17Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * DATE: 2022/2/20
 * CREATE BY: Byx
 */
public class minnumEffortPath {
    int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int minimumEffortPath1631(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        int[][] effortTo = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(effortTo[i], Integer.MAX_VALUE);
        }
        effortTo[0][0] = 0;
        Queue<State> queue = new PriorityQueue<State>(((o1, o2) -> (o1.effortFromStart - o2.effortFromStart)));
        queue.offer(new State(0, 0, 0));
        while (!queue.isEmpty()) {
            State curr = queue.poll();
            int currX = curr.x;
            int currY = curr.y;
            int currEffortFromStart = curr.effortFromStart;
            if (currX == m - 1 && currY == n - 1) {
                return currEffortFromStart;
            }
            if (currEffortFromStart > effortTo[currX][currY]) {
                continue;
            }
            for (int[] neighbor : adj(heights, currX, currY)) {
                int nextX = neighbor[0];
                int nextY = neighbor[1];
                int effortToNextNode = Math.max(effortTo[currX][currY],
                        Math.abs(heights[currX][currY] - heights[nextX][nextY]));
                System.out.println(effortToNextNode);
                effortToNextNode = Math.abs(heights[currX][currY] - heights[nextX][nextY]);
                System.out.println(effortToNextNode);
                System.out.println("***************");
                if (effortTo[nextX][nextY] > effortToNextNode) {
                    effortTo[nextX][nextY] = effortToNextNode;
                    queue.offer(new State(nextX, nextY, effortToNextNode));
                }
            }
        }
        return -1;
    }

    ArrayList<int[]> adj(int[][] matrix, int x, int y) {
        int m = matrix.length;
        int n = matrix[0].length;
        ArrayList<int[]> neighbors = new ArrayList<>();
        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >= m || nx < 0 || ny >= n || ny < 0) {
                continue;
            }
            neighbors.add(new int[]{nx, ny});
        }
        return neighbors;
    }


    class State{
        int x, y;
        int effortFromStart;

        State(int x, int y, int effortFromStart) {
            this.x = x;
            this.y = y;
            this.effortFromStart = effortFromStart;
        }
    }

    public static void main(String[] args) {
        System.out.println(new minnumEffortPath().minimumEffortPath1631(new int[][]{{1, 10, 6}}));
    }
}
