package labuladong.day19;

import java.util.Stack;

/**
 * DATE: 2022/2/23
 * CREATE BY: Byx
 */
public class day19SingleStack {
    public int trap3(int[] height) {
        int []left = new int[height.length];
        int []right = new int[height.length];
        Stack<Integer> arr = new Stack<>();
        left[0] = 0;
        arr.push(height[0]);
        for(int i = 1; i< height.length; i++){
            if(arr.isEmpty()){
                left[i] = 0;
                arr.push(height[i]);
                continue;
            }
            if(height[i] > arr.peek()){
                left[i] = arr.peek();
                arr.push(height[i]);
            }else{
                left[i] = arr.peek();
            }
        }
        right[height.length -1 ] = 0;
        arr.clear();
        arr.push(height[height.length - 1]);
        for(int i = height.length -1 ; i >= 0; i--){
            if(arr.isEmpty()){
                right[i] = 0;
                arr.push(height[i]);
                continue;
            }
            if(height[i] > arr.peek()){
                right[i] = arr.peek();
                arr.push(height[i]);
            }else{
                right[i] = arr.peek();
            }
        }
        int res = 0;
        for(int i= 0; i < height.length; i++){
            res += Math.max(Math.min(left[i], right[i]) - height[i], 0);
        }
        return res;
    }
    public int trap(int[] height) {
        if(height.length <= 1) return 0;
        int []left = new int[height.length];
        int []right = new int[height.length];
        int leftMax = 0;
        int rightMax = 0;
        for(int i = 0; i < height.length; i++){
            left[i] = leftMax;
            leftMax = Math.max(leftMax, height[i]);
        }
        for(int i = height.length - 1;  i >= 0; i--){
            right[i] = rightMax;
            rightMax = Math.max(rightMax, height[i]);
        }

        int sum = 0;
        for(int i = 0; i < height.length; i++){
            sum += Math.min(left[i], right[i]) - height[i];
        }
        return sum;


    }

    public int trap42(int[] height) {
        int left = 0, right = height.length - 1;
        int l_max = 0, r_max = 0;
        int res = 0;
        while (left < right) {
            l_max = Math.max(l_max, height[left]);
            r_max = Math.max(r_max, height[right]);

            if (l_max < r_max) {
                res += l_max - height[left];
                left++;
            }else {
                res += r_max - height[right];
                right--;
            }
        }
        return res;

    }

    public int maxArea11(int[] height) {
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int area = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (leftMax < rightMax) {
                area = Math.max(area, leftMax * (right - left + 1));
                left++;
            }else {
                area = Math.max(area, rightMax * (right - left + 1));
                right--;
            }
        }
        return area;
    }


    public static void main(String[] args) {
        System.out.println(new day19SingleStack().trap3(new int[]{4,2,0,3,2,5}));
    }
}
