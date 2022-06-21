package Random;

/**
 * DATE: 2022/6/19
 * CREATE BY: Byx
 */
public class LC5452 {
    public String greatestLetter(String s) {
        int []word = new int[26];
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c > 'Z'){
                word[c - 'a']++;
            }else{
                word[c - 'A']++;
            }
        }
        for(int i = 25 ; i >= 0; i--){
            if(word[i] >= 2) return String.valueOf((char) i +'a');
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(new LC5452().greatestLetter("lEe"));
    }
}
