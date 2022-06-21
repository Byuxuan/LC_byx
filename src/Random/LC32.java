package Random;

/**
 * DATE: 2022/6/5
 * CREATE BY: Byx
 */
public class LC32 {
    public int longestValidParentheses(String s) {
        int res = 0;
        int right= 0;
        int left = 0;
        int curr = 0;
        int subRes = 0;
        while(right < s.length()){
            right = left;
            curr = 0;
            subRes = 0;
            while(right < s.length()){
                if(s.charAt(right) == '(') {
                    curr++;
                }
                if(s.charAt(right) == ')'){
                    curr--;
                    if(curr >= 0){
                        subRes += 2;
                        res = Math.max(res, subRes);
                    }
                }
                right++;
                if(curr < 0){

                    left = right;
                    break;
                }



            }
        }
        return res;

    }

    public static void main(String[] args) {
        System.out.println(new LC32().longestValidParentheses(")()())"));
    }
}
