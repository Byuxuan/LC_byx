package Random;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.Arrays;

/**
 * DATE: 2022/6/12
 * CREATE BY: Byx
 */
public class LC5289 {
    int []bucket;
    int []visit;
    int piles;
    boolean flag = false;
    public int distributeCookies(int[] cookies, int k) {
        int sum = 0;
        for (int cookie : cookies) {
            sum += cookie;
        }
        piles = sum / k;
        bucket = new int[k];
        visit = new int[cookies.length];
        find(0, cookies, k);
        int res = 0;

        Arrays.sort(bucket);

        int left = 0;
        int right = 0;
        while (right < cookies.length) {
            while (right < cookies.length && visit[right] == 1) {
                right++;
            }
            if(right == cookies.length) break;
            bucket[left] += cookies[right];
            visit[right] = 1;
            left = (left + 1) % k;
        }
        for (int i = 0; i < k; i++) {
            res = Math.max(res, bucket[i]);
        }


        return res;

    }

    private void find(int index, int[] cookies, int k) {
        if(index == cookies.length) return;

        for (int i = 0; i < k; i++) { // 当前饼干分配给第k个桶
            if(bucket[i] + cookies[index] > piles) {
                continue;
            }
            bucket[i] += cookies[index];
            visit[index] = 1;
            find(index + 1, cookies, k);
        }

    }

    public static void main(String[] args) {
        System.out.println(new LC5289().distributeCookies(new int[]{1,8,16,5,6,14},
        6));
    }
}
