package labuladong.day2;

import com.sun.org.apache.xerces.internal.xs.ItemPSVI;

import javax.crypto.spec.PSource;
import java.util.*;

/**
 * DATE: 2022/1/2
 * CREATE BY: Byx
 */
public class OpenLock752 {
    String plusOne(String s, int j) {
        String temp;
        if (s.charAt(j) == '9') {
            temp = s.substring(0, j) + '0' + s.substring(j + 1);
        } else {
            temp = s.substring(0, j) + (s.charAt(j) - 47) + s.substring(j + 1);
        }
        return temp;
    }

    String minusOne(String s, int j) {
        String temp;
        if (s.charAt(j) == '0') {
            temp = s.substring(0, j) + '9' + s.substring(j + 1);
        } else {
            temp = s.substring(0, j) + (s.charAt(j) - 49) + s.substring(j + 1);
        }
        return temp;
    }

    int openLock(String[] deadends, String target) {

        Set<String> dead = new HashSet<>(Arrays.asList(deadends));
        Set<String> visited = new HashSet<>();

        Set<String> q1 = new HashSet<>();
        Set<String> q2 = new HashSet<>();
        q1.add("0000");
        q2.add(target);
        int step = 0;
        while (!q1.isEmpty() && !q2.isEmpty()) {
            Set<String> temp = new HashSet<>();
            for (String curr : q1) {
                if (dead.contains(curr)) {
                    continue;
                }
                if (q2.contains(curr)) {
                    return step;
                }
                visited.add(curr);
                for (int j = 0; j < 4; j++) {
                    String upString = plusOne(curr, j);
                    String miString = minusOne(curr, j);
                    if (!visited.contains(upString)) {
                        temp.add(upString);
                    }
                    if (!visited.contains(miString)) {
                        temp.add(miString);
                    }

                }
            }
            step++;
            q1 = q2;
            q2 = temp;

        }
        return -1;
    }


    public static void main(String[] args) {
        System.out.println(new OpenLock752().openLock(new String[]{"8887","8889","8878","8898","8788","8988","7888","9888"},
        "8888"));
    }


}
