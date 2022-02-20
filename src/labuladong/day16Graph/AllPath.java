package labuladong.day16Graph;
import java.util.*;
/**
 * DATE: 2022/2/17
 * CREATE BY: Byx
 */
public class AllPath {
    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> allPathsSourceTarget797(int[][] graph) {
        int[] visit = new int[graph.length];

        ArrayList<Integer> curr = new ArrayList<>();
        for (int i = 0; i < graph[0].length; i++) {
            DFS(graph, graph[0][i], visit, curr);
        }
        return result;
    }

    private void DFS(int[][] graph, int i, int[] visit, ArrayList<Integer> curr) {
        if(visit[i] == 1) return;
        visit[i] = 1;
        curr.add(i);
        if (i == graph.length - 1) {
            List<Integer> temp = (List<Integer>) curr.clone();
            temp.add(0, 0);
            result.add(temp);
            visit[i] = 0;
            curr.remove(curr.size() - 1);
            return;
        }
        for (int j = 0; j < graph[i].length; j++) {
            DFS(graph, graph[i][j], visit, curr);
        }
        visit[i] = 0;
        curr.remove(curr.size() - 1);
    }

    public boolean canFinish207(int numCourses, int[][] prerequisites) {
        ArrayList[] graph = new ArrayList[numCourses];
        int []flag = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList();
        }
        for (int i = 0; i < prerequisites.length; i++) {
            if (prerequisites[i][0] == prerequisites[i][1]) {
                return false;
            }
            graph[prerequisites[i][0]].add(prerequisites[i][1]);
            flag[prerequisites[i][1]]++;
        }
        int[] visit = new int[numCourses];

        while (true) {
            int index1 = -1;
            int index2 = 0;
            for (int i = 0; i < flag.length; i++) {
                if (flag[i] == 0 && visit[i] == 0) {
                    index1 = i;
                }
                index2 += flag[i];
            }
            if (index2 == 0) {
                return true;
            }
            if (index1 == -1) {
                return false;
            }
            for (int i = 0; i < graph[index1].size(); i++) {
                flag[(int) graph[index1].get(i)]--;
            }
            graph[index1].clear();
            visit[index1] = 1;

        }

    }

    public int[] findOrder210(int numCourses, int[][] prerequisites) {
        ArrayList[] graph = new ArrayList[numCourses];
        int[] result = new int[numCourses];
        int[] flag = new int[numCourses];
        int[] visit = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList();
        }
        for (int i = 0; i < prerequisites.length; i++) {
            if (prerequisites[i][0] == prerequisites[i][1]) {
                return new int[]{};
            }
            graph[prerequisites[i][0]].add(prerequisites[i][1]);
            flag[prerequisites[i][1]]++;
        }
        ArrayList<Integer> queue = new ArrayList<>();
        int currNum = 0;
        for (int i = 0; i < flag.length; i++) {
            if (flag[i] == 0) {
                queue.add(i);
                result[currNum++] = i;
                visit[i] = 1;
            }
        }
        while (!queue.isEmpty()) {
            int curr = queue.remove(queue.size() - 1);
            for (int i = 0; i < graph[curr].size(); i++) {
                flag[(int) graph[curr].get(i)]--;
                int temp = flag[(int) graph[curr].get(i)];
                if (temp == 0) {
                    queue.add((int) graph[curr].get(i));
                    result[currNum++] = (int) graph[curr].get(i);
                }
            }
        }
        if (currNum == numCourses) {
            return result;
        } else return new int[]{};
    }


    public static void main(String[] args) {
        //System.out.println(new AllPath().allPathsSourceTarget797(new int[][]{{1,2}, {3}, {3}, {}}));
        System.out.println(Arrays.toString(new AllPath().findOrder210(5, new int[][]{{1, 4}, {2, 4}, {3, 1}, {3, 2}})));
    }
}
