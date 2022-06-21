package Random;

/**
 * DATE: 2022/6/2
 * CREATE BY: Byx
 */
public class LC215 {
    public int findKthLargest(int[] nums, int k) {
        return findKthLargest(nums, 0, nums.length -1, k);
    }

    public int findKthLargest(int[]nums, int begin, int end, int k){
        int part = Partition(nums, begin, end);
        if(part == nums.length - k) return nums[nums.length - k];
        else if(part < nums.length - k) return findKthLargest(nums, part + 1, nums.length -1, k);
        else return findKthLargest(nums, 0, part -1, k);
    }

    public int Partition(int []nums, int begin, int end){
        if(begin == end) return begin;
        int left = begin + 1;
        int right = end;
        while(left <= right){
            while(left <= right && nums[left] <= nums[begin]) left++;
            while(left <= right && nums[right] >= nums[begin]) right--;
            if(left < right){
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
        }
        int temp = nums[begin];
        nums[begin] = nums[right];
        nums[right] = temp;
        return right;
    }

    public static void main(String[] args) {
        System.out.println(new LC215().findKthLargest(new int[]{2, 1}, 2));
    }
}
