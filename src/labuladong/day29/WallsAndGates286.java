package labuladong.day29;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * DATE: 2022/3/22
 * CREATE BY: Byx
 */
public class WallsAndGates286 {
    public static int [][]grid = new int[][]{{1,0},{-1, 0}, {0, 1}, {0, -1}};
    ArrayList[]node = new ArrayList[50000];

    public static void wallsAndGates(int[][] rooms) {
        LinkedList<int []> queue = new LinkedList<>();
        for(int i = 0; i < rooms.length; i++){
            for(int j = 0; j < rooms[i].length; j++){
                if(rooms[i][j] == 0){
                    queue.offer(new int[]{i, j});
                    BFS(queue, rooms);
                    queue.clear();
                }
            }
        }
    }
    public static void BFS( Queue<int []> queue, int[][] rooms){

        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            int currX = curr[0];
            int currY = curr[1];
            int currNum = rooms[currX][currY];
            for(int i = 0; i< grid.length; i++){
                int nextX = currX + grid[i][0];
                int nextY = currY + grid[i][1];
                if(nextX >= rooms.length || nextX < 0 || nextY >= rooms[0].length || nextY < 0) continue;
                if(rooms[nextX][nextY] == -1 || rooms[nextX][nextY] == 0) continue;
                rooms[nextX][nextY] = Math.min(rooms[nextX][nextY], currNum + 1);
                queue.offer(new int[]{nextX, nextY});
            }
        }
    }

    public static void main(String[] args) {
        wallsAndGates(new int[][]{{2147483647, -1, 0, 2147483647}, {2147483647, 2147483647, 2147483647, -1}, {2147483647, -1, 2147483647, -1}, {0, -1, 2147483647, 2147483647}});
    }
}
