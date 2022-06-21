package Random;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * DATE: 2022/6/13
 * CREATE BY: Byx
 */
public class LC1051 {
    public int heightChecker(int[] heights) {
        int[] copy = Arrays.copyOf(heights, heights.length);
        int res = 0;
        Arrays.sort(copy);
        for(int i = 0; i< heights.length; i++){
            if(copy[i] != heights[i]) res++;
        }
        return res;
    }
}
