package Random;

import java.awt.geom.Line2D;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * DATE: 2022/5/16
 * CREATE BY: Byx
 */
public class LC161 {
    public String fractionToDecimal(int numerator, int denominator) {
        // 转 long 计算，防止溢出
        long a = numerator, b = denominator;

        if(a % b == 0) return String.valueOf(a / b);

        StringBuilder string = new StringBuilder();
        if(a * b < 0){
            string.append("-");
        }
        a = Math.abs(a);
        b = Math.abs(b);

        string.append( a / b);
        string.append(".");

        a = a % b;

        HashMap<Long, Integer> hash = new HashMap<>();

        while(a != 0){
            hash.put(a, string.toString().length());
            a = a * 10;
            string.append(a / b);
            a = a % b;
            if(hash.containsKey(a)){
                int u = hash.get(a);
                return String.format("%s(%s)", string.substring(0, u), string.substring(u));

            }



        }

        return string.toString();

    }
    public int evalRPN(String[] tokens) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        HashMap<String, Integer> hash = new HashMap<>();
        hash.put("+", 1);
        hash.put("-", 2);
        hash.put("*", 3);
        hash.put("/", 4);
        int i = 0;
        while(i < tokens.length){
            if(hash.containsKey(tokens[i])){
                int  second = linkedList.pop();
                int  first = linkedList.pop();
                int  res = 0;
                int  op = hash.get(tokens[i]);
                if(op == 1) res = first + second;
                else if(op == 2) res = first - second;
                else if(op == 3) res = first * second;
                else res = first / second;
                linkedList.push(res);

            }else{
                linkedList.push(Integer.parseInt(tokens[i]));

            }
            i++;

        }
        return linkedList.pop();
    }

    public static void main(String[] args) {
        //System.out.println(new LC161().fractionToDecimal(4,333));
        System.out.println(new LC161().evalRPN(new String[]{"2", "1", "+"}));
    }
}
