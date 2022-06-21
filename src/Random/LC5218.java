package Random;

import java.util.ArrayList;

/**
 * DATE: 2022/6/19
 * CREATE BY: Byx
 */
public class LC5218 {
    ArrayList<Integer> nums = new ArrayList<>();
    int res = Integer.MAX_VALUE;
    boolean flag = false;
    public int minimumNumbers(int num, int k) {
        int curr = k;
        int pre = 0;
        while(curr <= num){
            nums.add(curr);
            curr = (pre + 1) * 10 + 9;
            pre++;
        }
        find(0, num, 0, 0);
        return res == Integer.MAX_VALUE? -1: res;

    }

    public void find(int index, int num, int currSum, int currRes){
        if(currSum == num){
            flag = true;
            res = Math.min(res, currRes);
        }


        for (int i = index; i < nums.size() ; i++) {
            if(currSum + nums.get(i) > num) break;
            find(index + 1, num, currSum + nums.get(i), currRes + 1);
        }

    }

    public static void main(String[] args) {
        System.out.println(new LC5218().minimumNumbers(38, 9));
    }
}
