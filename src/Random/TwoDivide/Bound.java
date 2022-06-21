package Random.TwoDivide;

/**
 * DATE: 2022/6/12
 * CREATE BY: Byx
 */
public class Bound {
    public int left_bound(int []nums, int target){
        if(nums.length == 0) return -1;
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] > target) {
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        return left;
    }

    public int right_bound(int []nums, int target){
        if(nums.length == 0) return -1;
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] == target) {
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        return left - 1;
    }

    public static void main(String[] args) {
        System.out.println(new Bound().left_bound(new int[]{1, 2, 2, 2}, 2));
        System.out.println(new Bound().right_bound(new int[]{1, 2, 2, 2}, 2));
    }
}
