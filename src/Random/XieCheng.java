package Random;

/**
 * DATE: 2022/5/5
 * CREATE BY: Byx
 */
public class XieCheng {

    public int  find(int []nums){
        if(nums.length <= 2) return -1;
        int left = 0;
        int right = nums.length -1;
        while (left <= right){
            int mid = left + (right - left)/2;
            if(mid > 0 && mid < nums.length -1 && nums[mid] > nums[mid -1] && nums[mid] > nums[mid +1]){
                return mid; // 1,2,1
            }else if(mid > 0 && mid < nums.length -1  && nums[mid] < nums[mid -1] && nums[mid] > nums[mid+1]){
                right = mid - 1; // 3,2,1
            }else if (mid > 0 && mid < nums.length -1  && nums[mid] > nums[mid -1] && nums[mid] < nums[mid+1]){

                left = mid + 1; // 1,2,3
            }
            else {
                left = mid + 1; //
            }
        }
        return  -1;
    }

    public static void main(String[] args) {
        System.out.println(new XieCheng().find(new int[]{1, 2, 1, 3, 5, 6, 4}));
    }

}



//










