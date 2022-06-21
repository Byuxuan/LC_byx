package Random;

/**
 * DATE: 2022/5/31
 * CREATE BY: Byx
 */
public class LC14 {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 1) return strs[0];
        StringBuilder string = new StringBuilder();

        for(int i = 0; i < strs[0].length(); i++){

            char chars = strs[0].charAt(i);
            boolean flag = true;
            for(int j = 1; j < strs.length; j++){
                if(i >= strs[j].length() || strs[j].charAt(i) != chars){
                    flag = false;
                    break;
                }
            }
            if(!flag) break;
            string.append(chars);
            i++;

        }
        return string.toString();
    }

    public static void main(String[] args) {
        System.out.println(new LC14().longestCommonPrefix(new String[]{"flower","flow","flight"}));

    }
}
