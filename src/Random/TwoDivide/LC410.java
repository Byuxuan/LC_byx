package Random.TwoDivide;

/**
 * DATE: 2022/6/12
 * CREATE BY: Byx
 */
public class LC410 {
    public int splitArray(int[] nums, int m) {
        int left = Integer.MIN_VALUE;
        int right = 0;

        for(int num: nums){
            left = Math.max(left, num);
            right += num;
        }
        int res = right;
        while(left < right){
            int mid = (left + right) >> 1;
            if(find(mid, nums) <= m){
                res = Math.min(res, mid);
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return res;

    }
    public int find(int mid, int []nums){
        int res = 0;
        int curr = 0;
        int index = 0;
        while(index < nums.length){
            curr = 0;
            while(index < nums.length && curr + nums[index] <= mid){
                curr += nums[index];
                index++;
            }
            res++;

        }
        return res;

    }
}
