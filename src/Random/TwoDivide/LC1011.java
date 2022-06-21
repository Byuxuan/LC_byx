package Random.TwoDivide;

/**
 * DATE: 2022/6/12
 * CREATE BY: Byx
 */
public class LC1011 {
    public int shipWithinDays(int[] weights, int days) {
        int sum = 0;
        int left  = 0;
        for(int weight: weights){
            sum += weight;
            left = Math.max(left, weight);
        }

        int right = sum;
        int res = right;
        while(left < right){
            int mid = (left + right) >> 1;
            if(find(mid, weights) <= days){
                res = Math.min(res, mid);
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return res;
    }

    public int find(int mid, int []weights){
        int curr = 0;
        int res = 0;
        int index = 0;
        while(index < weights.length){
            curr = 0;
            while(index < weights.length && curr + weights[index] <= mid){
                curr += weights[index];
                index++;
            }
            res++;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new LC1011().shipWithinDays(new int[]{1, 2, 3, 1,1}, 4));
    }
}
