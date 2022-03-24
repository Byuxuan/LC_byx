package labuladong.day26;

import java.util.*;

/**
 * DATE: 2022/3/15
 * CREATE BY: Byx
 */
public class ShortestDistanceColor {
    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        HashSet<Integer> hash = new HashSet<>();
        int [][]dict = new int[colors.length][3];
        for(int i = 0; i<dict.length; i++){
            Arrays.fill(dict[i], Integer.MAX_VALUE);
        }

        for(int i = 0, a =-1, b = -1, c= -1; i < colors.length; i++){
            if(colors[i] == 1) a = i;
            else if (colors[i] == 2) {
                b = i;
            }else {
                c = i;
            }
            if(a != -1) dict[i][0] = Math.min(dict[i][0], i - a);
            if(b != -1) dict[i][1] = Math.min(dict[i][1], i - b);
            if(c != -1) dict[i][2] = Math.min(dict[i][2], i - c);
        }

        for(int i = colors.length -1, a =-1, b = -1, c= -1; i >=0; i--){
            if(colors[i] == 1) a = i;
            else if (colors[i] == 2) {
                b = i;
            }else {
                c = i;
            }
            if(a != -1) dict[i][0] = Math.min(dict[i][0], a - i);
            if(b != -1) dict[i][1] = Math.min(dict[i][1], b - i);
            if(c != -1) dict[i][2] = Math.min(dict[i][2], c - i);
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            if (dict[queries[i][0]][queries[i][1] - 1] != Integer.MAX_VALUE) {
                result.add(dict[queries[i][0]][queries[i][1] - 1]);
            }else{
                result.add(-1);
            }
        }
        return  result;
    }


    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        int point1 = 0;
        int point2 = 0;
        List<Integer> result = new ArrayList<>();
        Arrays.sort(slots1, (o1, o2) -> o1[0] - o2[0]);
        Arrays.sort(slots2,(o1, o2) -> o1[0] - o2[0]);
        while(point1 < slots1.length && point2 < slots2.length){
            int maxBegin = Math.max(slots1[point1][0], slots2[point2][0]);
            int minEnd = Math.min(slots1[point1][1], slots2[point2][1]);
            if(minEnd - maxBegin >= duration){
                result.add(maxBegin);
                result.add(maxBegin + duration);
                return result;
            }
            else{
                if(maxBegin == slots1[point1][0]) {
                    if(minEnd == slots1[point1][1]) point1++;
                    else{
                        point2++;
                    }
                }
                else{
                    if(minEnd == slots1[point2][1]) point2++;
                    else{
                        point1++;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new ShortestDistanceColor().minAvailableDuration(
                new int[][]{{10, 60}}, new int[][]{{12, 17}, {21, 50}}, 8));
    }


}
