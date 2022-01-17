package labuladong.day7DynamicProgramming;


import javafx.util.Pair;

import java.util.HashMap;

/**
 * DATE: 2022/1/9
 * CREATE BY: Byx
 */
public class superEggDrop887 {
    HashMap<Integer, Integer> hashMap = new HashMap<>();
    public int superEggDrop(int K, int N) {

        return dp(K, N);
    }

    public int dp(int K, int N) {
        if (K == 1) return N;
        if (N == 0) return 0;
        if (hashMap.containsKey(N*100+K)) {
            return hashMap.get(N*100+K);
        }
        int res = Integer.MAX_VALUE;

//        for (int i = 1; i < N + 1; i++) {
//            res = Math.min(res, Math.max(dp(K, N - i, hashMap), dp(K - 1, i - 1, hashMap)) + 1);
//        }
        int left = 1;
        int right = N;
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            //
            int t1 = dp(K - 1, mid - 1);
            int t2 = dp(K, N - mid);
            if (t1 < t2) {
                left = mid;
            } else if (t1 > t2) {
                right = mid;
            } else {
                left = mid;
                right = mid;
            }
        }
        res = 1 + Math.min(Math.max(dp(K - 1, left - 1), dp(K, N - left)), Math.max(dp(K - 1, right - 1), dp(K, N - right)));

        hashMap.put(N * 100+ K, res);
        // System.out.println(N+ "   "+  K+ "   "+ res );
        return res;

    }

    public static void main(String[] args) {
        System.out.println(new superEggDrop887().superEggDrop(3, 200));
    }

}
