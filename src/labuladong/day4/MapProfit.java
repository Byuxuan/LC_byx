package labuladong.day4;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * DATE: 2022/1/5
 * CREATE BY: Byx
 */
public class MapProfit {
    public int maxProfit(int[] prices){ // k= 1
        if(prices.length==0) return 0;
        int [][]dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for(int i = 1; i< prices.length;i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], - prices[i]);
        }


        return  dp[prices.length-1][0] ;

    }

    public int maxProfit2(int[] prices) { // k = + nifiny
        if(prices.length==0) return 0;
        int [][]dp=new int[prices.length][2];
        dp[0][0]=0;
        dp[0][1]=-prices[0];

        for(int i=1;i<prices.length;i++){
            dp[i][0]=Math.max( dp[i-1][0], dp[i-1][1]+prices[i]);
            dp[i][1]=Math.max(dp[i-1][1], dp[i-1][0]-prices[i]);
        }
        return dp[prices.length-1][0];
    }

    public int maxProfit3(int[] prices) { // K= 2

        int[][][] dp = new int[prices.length][3][2];

        for (int i = 0; i < prices.length; i++) {
            for (int j = 1; j <= 2; j++) {
                if(i == 0){
                    dp[i][j][0] = 0;
                    dp[i][j][1] = -prices[i];
                    continue;
                }
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[prices.length - 1][2][0];



    }

    public int maxProfit4(int k, int[] prices) {
        int[][][] dp = new int[prices.length][k + 1][2];


        for (int i = 0; i < prices.length; i++) {
            for (int j = 1; j <=k ; j++) {
                if (i == 0) {
                    dp[i][j][0] = 0;
                    dp[i][j][1] = -prices[i];
                    continue;
                }
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[prices.length - 1][k][0];
    }

    public int maxProfit5(int[] prices) {
        if (prices.length == 0) return 0;
        int dp[][] = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);

            dp[i][1] = i >= 2 ? Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]) : Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[prices.length - 1][0];
    }


    public long getDescentPeriods(int[] prices) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for(int i= 1;i<prices.length;i++){
            if(prices[i] - prices[i-1] == -1) continue;
            arrayList.add(i);
        }
        int sum = 0;

        if (arrayList.isEmpty()){
            return jieCheng(prices.length);
        }
        sum += jieCheng(arrayList.get(0));
        for(int i = 1; i < arrayList.size() ; i++){
            sum += jieCheng(arrayList.get(i) - arrayList.get(i-1));
        }
        if(arrayList.get(arrayList.size()-1) != prices.length -1){
            sum += jieCheng(prices.length - 1 - arrayList.get(arrayList.size() - 1));
        }
        else {
            sum += 1;
        }
        return sum;

    }

    public int jieCheng(int n){
        if (n==1) return 1;
        return n * jieCheng(n-1);
    }
    public static void main(String[] args) {
//        System.out.println(new MapProfit().maxProfit4(2,new int[]{3,2,6,5,0,3}));
//        System.out.println("****************");
//        System.out.println(new MapProfit().maxProfit3(new int[]{3,2,6,5,0,3}));
//        System.out.println(new MapProfit().jieCheng(3));

        System.out.println(new MapProfit().getDescentPeriods(new int[]{8,6,7,7}));
    }
}
