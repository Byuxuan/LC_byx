package Random;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * DATE: 2022/6/4
 * CREATE BY: Byx
 */
public class LC239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        ArrayDeque<Integer> queue = new ArrayDeque();
        int []res = new int[nums.length - k +1];
        int index = 0;
        int right = k -1;
        for(int i = 0; i< k -1; i++){
            while(!queue.isEmpty() && nums[i] > nums[queue.peekLast()]) queue.pollLast();
            queue.offer(i);
        }
        while(index < res.length){
            while(!queue.isEmpty() && right - queue.peekFirst() + 1 > k) queue.poll();
            while(!queue.isEmpty() && nums[right] > nums[queue.peekLast()]) queue.pollLast();
            queue.offer(right);
            res[index++] = nums[queue.peekFirst()];
            right++;
        }
        return res;

    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new LC239().maxSlidingWindow(new int[]{9,11}, 2)));
    }
}
