package Random;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * DATE: 2022/6/8
 * CREATE BY: Byx
 */
public class LC135 {
    public int candy(int[] ratings) {
        int []left = new int[ratings.length];
        int []right = new int[ratings.length];
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for(int i = 0; i < ratings.length; i++){
            while(!queue.isEmpty() && ratings[i] <= ratings[queue.peekLast()]){
                queue.clear();
            }
            if(queue.isEmpty()){
                left[i] = 1;
            }
            else{
                left[i] = ratings[i] == ratings[queue.peek()]? left[queue.peekLast()] :  left[queue.peekLast()] + 1;
            }
            queue.offer(i);
        }
        queue.clear();
        for(int i = ratings.length - 1; i >= 0; i--){
            while(!queue.isEmpty() && ratings[i] <= ratings[queue.peekLast()]){
                queue.clear();
            }
            if(queue.isEmpty()){
                right[i] = 1;
            }else{
                right[i] = ratings[i] == ratings[queue.peek()]? right[queue.peekLast()] :  right[queue.peekLast()] + 1;
            }
            queue.offer(i);

        }
        int res = 0;
        for(int i = 0; i < ratings.length; i++){
            res += Math.max(left[i],right[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new LC135().candy(new int[]{1, 2, 5, 5, 5, 2, 1}));
    }
}
