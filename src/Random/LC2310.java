package Random;

/**
 * DATE: 2022/6/20
 * CREATE BY: Byx
 */
public class LC2310 {
    public int minimumNumbers(int num, int k) {
        /**
         *  candidate = 10 * m + k
         *  10 * m` * n + n * k = num
         *  (num - n * k ) % 10 == 0
         *
         *  num % 10 => n*k % 10 => (n % 10) * (k %10)
         */
        if(num == 0) return 0;
        for (int i = 0; i < num && i * k <= num; i++) {
            if((num - i * k)% 10 == 0) return i;
        }
        return -1;
    }
}
