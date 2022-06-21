package Random;

import java.util.HashSet;

/**
 * DATE: 2022/6/8
 * CREATE BY: Byx
 */
public class LC1037 {
    public boolean isBoomerang(int[][] points) {
        HashSet<String> hash1 = new HashSet<>();
        HashSet<String> hash2 = new HashSet<>();
        for(int [] point: points){
            hash1.add(point[0]+"&&"+point[1]);
        }
        if(hash1.size() != 3) return false;
        for(int i = 1; i < 3; i++){
            if(points[i][0] - points[i-1][0] == 0){
                hash2.add(points[i][0]+"&&");
            }else if(points[i][1] - points[i-1][1] == 0) {
                hash2.add(points[i][1]+"**");
            }else{
                hash2.add((double)(points[i][1] - points[i-1][1]) / (points[i][0] - points[i-1][0])+"");
            }
        }
        return hash2.size() == 2;


    }

    public static void main(String[] args) {
        System.out.println(new LC1037().isBoomerang(new int[][]{{1, 0}, {0, 0}, {2, 0}}));
        System.out.println(-0.0 + "");
    }

}
