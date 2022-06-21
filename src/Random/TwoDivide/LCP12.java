package Random.TwoDivide;

/**
 * DATE: 2022/6/12
 * CREATE BY: Byx
 */
public class LCP12 {
    public int minTime(int[] time, int m) {
        if(time.length <= m) return 0;
        int right = 0;

        for(int t: time){
            right += t;
        }
        int res = right;
        int left = 0;
        while(left < right){
            int mid = (left + right) >> 1;
            if(find(mid, time) <= m){
                right = mid;
                res = Math.min(res, mid);
            }else{
                left = mid + 1;
            }
        }
        return res;
    }
    public int find(int mid, int []time){
        int res = 0;
        int curr = 0;
        int index = 0;

        while(index < time.length){
            curr = 0;
            int max = 0;
            while (index < time.length) {

                curr += time[index];
                max = Math.max(max, time[index]);
                if (curr - max > mid) {
                    break;
                }
                index++;
            }
            res++;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new LCP12().minTime(new int[]{82,35,6,53,37,75,69,69,53,18}
        ,4));

        //82,35,6,53,37,75,69,
    }
}
