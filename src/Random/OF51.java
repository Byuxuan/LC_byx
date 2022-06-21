package Random;

/**
 * DATE: 2022/6/7
 * CREATE BY: Byx
 */
public class OF51 {
    int []temp;
    int res = 0;
    public int reversePairs(int[] nums) {
        temp = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            temp[i] = nums[i];
        }

        find(nums, 0, nums.length -1);
        return res;

    }

    public void find(int []nums, int begin, int end){
        if(begin >= end) return;
        int mid = (begin + end) >> 1;
        find(nums, begin, mid);
        find(nums, mid+1, end);
        int first = begin;
        int second = mid + 1;
        int k = begin;
        for(int i = begin; i <= end; i++){
            temp[i] = nums[i];
        }
        while(k <= end){

            if(first == mid + 1){
                nums[k++] = temp[second++];
            }else if(second == end + 1){
                nums[k++] = temp[first++];
            }else if(temp[first] <= temp[second]){
                nums[k++] = temp[first++];
            }else{
                nums[k++] = temp[second++];
                res += mid + 1 - first;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new OF51().reversePairs(new int[]{7, 5, 6, 4}));
    }
}
