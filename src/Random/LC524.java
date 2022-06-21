package Random;

/**
 * DATE: 2022/5/5
 * CREATE BY: Byx
 */
import java.util.*;
public class LC524 {
    public String findLongestWord(String s, List<String> dictionary) {
        Collections.sort(dictionary, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() != o2.length()) {
                    return o2.length() - o1.length();
                }else {
                    return o1.compareTo(o2);
                }
            }
        });

        int n = s.length();
        for (int i = 0; i < dictionary.size(); i++) {
            String currString = dictionary.get(i);
            int left1 = 0;
            int left2 = 0;
            while (left1 < n && left2 < currString.length()){
                if (s.charAt(left1) != currString.charAt(left2)) left1++;
                else{
                    left1++;
                    left2++;
                }

            }
            if (left2 == currString.length()) {
                return currString;
            }


        }
        return "";
    }
}
