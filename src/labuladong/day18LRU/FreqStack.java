package labuladong.day18LRU;

import java.util.HashMap;
import java.util.Stack;

/**
 * DATE: 2022/2/21
 * CREATE BY: Byx
 */
public class FreqStack {
    int maxFreq;
    HashMap<Integer, Integer> valToFreq;
    HashMap<Integer, Stack<Integer>> freqTovals;
    public FreqStack() {
        int maxFreq = 0;
        HashMap<Integer, Integer> valToFreq = new HashMap<>();
        HashMap<Integer, Stack<Integer>> freqTovals = new HashMap<>();
    }

    public void push(int val) {
        int freq = valToFreq.getOrDefault(val, 0) + 1;
        valToFreq.put(val, freq);
        freqTovals.putIfAbsent(freq, new Stack<>());
        freqTovals.get(freq).add(val);
        maxFreq = Math.max(maxFreq, freq);
    }

    public int pop() {
        Stack<Integer> vals = freqTovals.get(maxFreq);
        int v = vals.pop();
        int freq = valToFreq.get(v) - 1;
        valToFreq.put(v, freq);
        if (vals.isEmpty()) {
            maxFreq--;
        }
        return v;
    }
}
