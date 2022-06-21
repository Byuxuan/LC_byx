package Random;

/**
 * DATE: 2022/5/6
 * CREATE BY: Byx
 */
import java.util.*;
public class LC337 {
    public boolean increasingTriplet(int[] nums) {
        if(nums.length < 2) return false;
        int min = Integer.MAX_VALUE;
        int mid = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] < min) min = nums[i];
            if(mid != Integer.MAX_VALUE && nums[i] > mid) return true;
            if(nums[i] > min) mid = nums[i];
        }
        return false;

    }

    public static void main(String[] args) {
        System.out.println(new LC337().increasingTriplet(new int[]{2,0,3,4}));
    }
}
