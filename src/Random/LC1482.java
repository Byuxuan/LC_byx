package Random;

import java.util.Arrays;

/**
 * DATE: 2022/6/13
 * CREATE BY: Byx
 */
public class LC1482 {
    public int minDays(int[] bloomDay, int m, int k) {
        if(m * k > bloomDay.length) return -1;
        int left = Integer.MAX_VALUE;
        int right = 0;
        for(int bloom: bloomDay){
            left = Math.min(left, bloom);
            right = Math.max(right, bloom);
        }
        int res = right;
        while(left < right){
            int mid = (left + right) >> 1;
            if(find(mid, bloomDay, k) >= m){
                res = Math.min(res, mid);
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return res;

    }

    public int find(int wait, int []bloomDay, int k){



        int res = 0;
        int index = 0;
        while(index < bloomDay.length){
            while(index < bloomDay.length && bloomDay[index] - wait > 0) index++;
            if(index == bloomDay.length) break;
            int num = k;
            int curr = index;
            while(curr < bloomDay.length && num > 0 && bloomDay[curr] - wait <= 0){
                num--;
                curr++;
            }
            if(num == 0){
                res++;
            }
            index = curr;
        }
        return res;


    }

    public static void main(String[] args) {
        System.out.println(new LC1482().minDays(new int[]{1,10,2,9,3,8,4,7,5,6}, 4, 2));
    }
}
