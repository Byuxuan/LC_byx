package labuladong.day17Graph;

import java.util.*;

/**
 * DATE: 2022/2/20
 * CREATE BY: Byx
 */
public class maxProbality {
    public double maxProbability1514(int n, int[][] edges, double[] succProb, int start, int end) {
        ArrayList[] graph = new ArrayList[n];
        for (int i = 0; i <n; i++) {
            graph[i] = new ArrayList<double []>();
        }
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            double weight = succProb[i];
            graph[from].add(new double[]{to, weight});
            graph[to].add(new double[]{from, weight});
        }
        double res = find(start, end, graph);
        return res;
    }

    private double find(int start, int end, ArrayList<double []>[] graph) {
        double[] distTo = new double[graph.length];
        Arrays.fill(distTo, -1);
        Queue<State> queue = new PriorityQueue<>(((o1, o2) -> Double.compare(o2.weightFromStrat, o1.weightFromStrat)));
        queue.add(new State(start, 1));
        while (!queue.isEmpty()) {
            State curr = queue.poll();
            int currNode = curr.x;
            double currWeight = curr.weightFromStrat;
            if (currNode == end) {
                return currWeight;
            }
            if (currWeight < distTo[currNode]) {
                continue;
            }
            for (double[] neighbor : graph[currNode]) {
                int nextNode = (int) neighbor[0];
                double nextWeight = neighbor[1];
                double nextNodeFromStart = currWeight * nextWeight;
                if (distTo[nextNode] < nextNodeFromStart) {
                    distTo[nextNode] = nextNodeFromStart;
                    queue.offer(new State(nextNode, nextNodeFromStart));
                }
            }
        }
        return -1;
    }

    class State{
        int x;
        double weightFromStrat;

        State(int x, double weightFromStrat) {
            this.x = x;
            this.weightFromStrat = weightFromStrat;
        }
    }
}
