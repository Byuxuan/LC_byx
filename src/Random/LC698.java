package Random;

/**
 * DATE: 2022/5/5
 * CREATE BY: Byx
 */
public class LC698 {
    int subSum;
    boolean flag;
    int []visit;
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        if(sum % k != 0) return false;
        subSum = sum / k;
        visit = new int[nums.length];
        find(0,0,0,k,nums);
        return flag;
    }
    public void find(int index, int currSum, int currFinish, int k, int []nums){
        if(flag) return;
        if(currSum == subSum){
            currSum = 0;
            currFinish++;
        }
        if(currFinish == k){
            flag = true;
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(visit[i] == 1 || currSum + nums[i] > subSum) continue;
            visit[i] = 1;
            find(index+1, currSum + nums[i], currFinish, k , nums);
            visit[i] = 0;
        }

    }



    public static void main(String[] args) {
        System.out.println(new LC698().canPartitionKSubsets(new int[]{1,1,2,2},2));
    }
}
