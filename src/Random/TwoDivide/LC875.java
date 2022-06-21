package Random.TwoDivide;

/**
 * DATE: 2022/6/12
 * CREATE BY: Byx
 */
public class LC875 {
    public int minEatingSpeed(int[] piles, int h) {
        int right = 0;
        int res = Integer.MAX_VALUE;
        for (int pile : piles) {
            right = Math.max(right, pile);
        }
        int left = 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if(find(piles, mid) <= h){
                res = mid;
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return res;
    }

    private int find(int[] piles, int mid) {
        int res = 0;
        for (int i = 0; i < piles.length; i++) {
            res += (piles[i] + mid - 1) / mid;
        }
        return res;
    }
}
