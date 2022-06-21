package Random;

import java.util.TreeMap;

/**
 * DATE: 2022/6/12
 * CREATE BY: Byx
 */
public class LC5292 {
    public double calculateTax(int[][] brackets, int income) {
        int curr = income;

        if(income <= brackets[0][0]) return curr * brackets[0][1] * 0.01;

        curr -= brackets[0][0];
        int index = 1;
        double res = 0;
        res += brackets[0][0] * brackets[0][1] * 0.01;
        while (index < brackets.length) {
            if (curr <= brackets[index][0] - brackets[index - 1][0]) {
                res += curr * brackets[index][1] * 0.01;
                return res;
            }
            curr -= (brackets[index][0] - brackets[index - 1][0]);
            res += (brackets[index][0] - brackets[index - 1][0]) * brackets[index][1] * 0.01;
            index++;
        }
        return res;


    }

    public static void main(String[] args) {
        System.out.println(new LC5292().calculateTax(new int[][]{{3, 50}, {7, 10}, {12, 25}},10));
    }
}
