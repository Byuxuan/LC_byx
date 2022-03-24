package labuladong.day18LRU;

import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * DATE: 2022/2/21
 * CREATE BY: Byx
 */
public class LFU {
    HashMap<Integer, Integer> keyToval;
    HashMap<Integer, Integer> keyToFreq;
    HashMap<Integer, LinkedHashSet<Integer>> freqTokey;
    int minFreq;
    int cap;

    LFU(int capacity) {
        this.cap = capacity;
        this.minFreq = 0;
        keyToFreq = new HashMap<>();
        keyToval = new HashMap<>();
        freqTokey = new HashMap<>();
    }

    public int get(int key) {
        if (!keyToval.containsKey(key)) {
            return -1;
        }
        increaseFreq(key);
        return keyToval.get(key);
    }

    public void put(int key, int val){
        if (this.cap <= 0) {
            return ;
        }
        if (keyToval.containsKey(key)) {
            keyToval.put(key, val);
            increaseFreq(key);
            return;
        }
        if (this.cap <= keyToval.size()) {
            removeMinFreqKey();
        }
        keyToval.put(key, val);
        keyToFreq.put(key, 1);
        freqTokey.putIfAbsent(1, new LinkedHashSet<>());
        freqTokey.get(1).add(key);
        this.minFreq = 1;
    }

    private void increaseFreq(int key) {
        int freq = keyToFreq.get(key);
        keyToFreq.put(key, freq + 1);
        freqTokey.get(freq).remove(key);
        freqTokey.putIfAbsent(freq + 1, new LinkedHashSet<>());
        freqTokey.get(freq + 1).add(key);
        if (freqTokey.get(key).isEmpty()) {
            freqTokey.remove(freq);
            if (freq == this.minFreq) {
                this.minFreq++;
            }
        }
    }

    private void removeMinFreqKey() {
        LinkedHashSet<Integer> keyList = freqTokey.get(this.minFreq);
        int deletedKey = keyList.iterator().next();
        keyList.remove(deletedKey);
        if (keyList.isEmpty()) {
            freqTokey.remove(this.minFreq);
        }
        keyToval.remove(deletedKey);
        keyToFreq.remove(deletedKey);

    }

}
