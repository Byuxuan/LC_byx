package Random;

/**
 * DATE: 2022/6/7
 * CREATE BY: Byx
 */
public class LC875 {
    public int minEatingSpeed(int[] piles, int h) {
        int max = Integer.MIN_VALUE;
        for(int pile: piles){
            max = Math.max(max, pile);
        }
        int left = 1;
        int right = max;
        int res = max;
        while(left < right){
            int mid = (left + right) >> 1;
            int need = calculate(piles, mid);
            if(need <= h){
                right = mid;
                res = need;
            }else{
                left = mid + 1;
            }
        }
        return res;
    }

    public  int calculate(int []piles, int speed){
        int res = 0;
        for(int pile: piles){
            res += (pile + speed - 1) / speed;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new LC875().minEatingSpeed(new int[]{30,11,23,4,20},5));
    }
}
