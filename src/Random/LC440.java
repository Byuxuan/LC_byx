package Random;

/**
 * DATE: 2022/6/13
 * CREATE BY: Byx
 */
public class LC440 {

        public int findKthNumber(int n, int k) {
            int cur=1;//第一字典序小的(就是1)
            int prefix=1;// 前缀从1开始
            while(cur<k) {
                int tmp=count(n,prefix); //当前prefix峰的值
                if(tmp+cur>k) {// 找到了
                    prefix*=10; //往下层遍历
                    cur++;//一直遍历到第K个推出循环
                }else {
                    prefix++;//去下个峰头(前缀)遍历
                    cur+=tmp;//跨过了一个峰(前缀)
                }
            }//退出循环时 cur==k 正好找到
            return prefix;
        }

        private int count(int n, int prefix) {
            //不断向下层遍历可能一个乘10就溢出了, 所以用long
            long cur=prefix;
            long next=cur+1;//下一个前缀峰头
            int count=0;
            while(cur<=n) {
                count+=Math.min(n+1,next)-cur;
                /**
                 * N + 1 是因为从0 开始， (13+1) - 10 = 4
                 */
                cur*=10;
                next*=10; //往下层走
            }
            return count;
        }


    public static void main(String[] args) {
        System.out.println(new LC440().findKthNumber(25, 15));
    }
}
