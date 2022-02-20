package labuladong.day17Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * DATE: 2022/2/20
 * CREATE BY: Byx
 */
public class day17Graph {

    public int networkDelayTime743(int[][] times, int N, int K){
        ArrayList[] graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<int []>();
        }
        for (int i = 0; i < times.length; i++) {
            int from = times[i][0];
            int to = times[i][1];
            int weight = times[i][2];
            graph[from].add(new int[]{to, weight});
        }

        int []disTo = dijkstra(K, graph);
        
        int res = 0;
        for (int i = 1; i < disTo.length; i++) {
            if (disTo[i] == Integer.MAX_VALUE) {
                return -1;
            }
            res = Math.max(res, disTo[i]);
        }
        return res;
    }


    private int[] dijkstra(int start, ArrayList<int []>[] graph) {
        int[] distTo = new int[graph.length];
        Arrays.fill(distTo, Integer.MAX_VALUE);
        distTo[start] = 0;
        Queue<State> queue = new PriorityQueue<>((a, b) -> (a.distFromStart - b.distFromStart));
        queue.offer(new State(start, 0));
        while (!queue.isEmpty()) {
            State curr = queue.poll();
            int curNodeId = curr.id;
            int curDistFromState = curr.distFromStart;
            if (curDistFromState > distTo[curNodeId]) {
                continue;
            }
            for (int[] neibor : graph[curNodeId]) {
                int nextNodeID = neibor[0];
                int disToNextNode = distTo[curNodeId] + neibor[1];
                if (distTo[nextNodeID] > disToNextNode) {
                    distTo[nextNodeID] = disToNextNode;
                    queue.offer(new State(nextNodeID, disToNextNode));
                }
            }
        }
        return distTo;
    }

    class State{
        int id;
        int distFromStart;

        State(int id, int distFromStart) {
            this.id = id;
            this.distFromStart = distFromStart;
        }
    }

    public static void main(String[] args) {
        System.out.println(new day17Graph().networkDelayTime743(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2));
    }
}
