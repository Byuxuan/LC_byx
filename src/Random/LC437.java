package Random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * DATE: 2022/6/1
 * CREATE BY: Byx
 */
public class LC437 {
    boolean res = false;
    int []size;
    int edge;
    public boolean makesquare(int[] matchsticks) {
        int sum = 0 ;
        for(int curr : matchsticks){
            sum += curr;
        }
        if(sum % 4 != 0) return false;
        size = new int[4];
        edge = sum / 4;
        Arrays.sort(matchsticks);
        return find(matchsticks, 0);

    }

    public boolean find(int[] matchsticks, int index){

        if(index == matchsticks.length){
            if(size[0] == size[1] && size[1] == size[2] && size[2] == size[3]) return true;
            else return false;
        }

        for(int i = 0 ; i < size.length; i++){
            if(size[i] + matchsticks[index] > edge || (i > 0 && size[i] == size[i -1])) continue;
            size[i] += matchsticks[index];
            if(find(matchsticks, index + 1)) return true;
            size[i] -= matchsticks[index];
        }
        return false;

    }


    public static void main(String[] args) {
        System.out.println(new LC437().makesquare(new int[]{1, 1, 2, 2, 2}));
    }
}
