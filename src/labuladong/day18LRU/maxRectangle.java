package labuladong.day18LRU;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * DATE: 2022/2/23
 * CREATE BY: Byx
 * 85
 */
public class maxRectangle {
    public int largestRectangleArea(int[] heights) {
        int res = 0;

        int []left = new int[heights.length];
        int []right = new int[heights.length];
        Stack<Integer> leftMax = new Stack<>();
        Stack<Integer> rightMax = new Stack<>();


        for(int i = 0; i < heights.length; i++){

            while (!leftMax.isEmpty() && heights[i] < heights[leftMax.peek()]) {
                leftMax.pop();
            }
            left[i] = leftMax.isEmpty()? -1 : leftMax.peek();
            leftMax.push(i);
        }

        for(int i = heights.length -1; i >= 0; i--){

            while(!rightMax.isEmpty() && heights[i] < heights[rightMax.peek()]){
                rightMax.pop();
            }
            right[i] = rightMax.isEmpty()? heights.length : rightMax.peek();
            rightMax.push(i);
        }
        for(int i = 0; i < heights.length; i++){
            res = Math.max(res, (right[i] - left[i] -1) * heights[i]);
        }
        return res;

    }


    public int largestRectangleArea2(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];

        Deque<Integer> mono_stack = new ArrayDeque<Integer>();
        for (int i = 0; i < n; ++i) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                mono_stack.pop();
            }
            left[i] = (mono_stack.isEmpty() ? -1 : mono_stack.peek());
            mono_stack.push(i);
        }

        mono_stack.clear();
        for (int i = n - 1; i >= 0; --i) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                mono_stack.pop();
            }
            right[i] = (mono_stack.isEmpty() ? n : mono_stack.peek());
            mono_stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new maxRectangle().largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));

    }

}
