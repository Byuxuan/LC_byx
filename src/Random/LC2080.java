package Random;

import java.util.*;
import java.util.HashMap;

/**
 * DATE: 2022/5/10
 * CREATE BY: Byx
 */
public class LC2080 {

    public static void main(String[] args) {
        RangeFreqQuery rangeFreqQuery = new RangeFreqQuery(new int[]{12,33,4,56,22,2,34,33,22,12,34,56});
        System.out.println(rangeFreqQuery.query(1,12,33));
    }
}

class RangeFreqQuery {
    HashMap<Integer, ArrayList<Integer>> hash = new HashMap<>();

    public RangeFreqQuery(int[] arr) {
        for(int i = 0; i < arr.length; i++){
            if(hash.containsKey(arr[i])){
                hash.get(arr[i]).add(i);
            }else{
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(i);
                hash.put(arr[i], temp);
            }
        }
    }

    public int query(int left, int right, int value) {
        if(!hash.containsKey(value)) return 0;
        List<Integer> now = hash.get(value);
        // 第一次二分找左端点下标
        int a = binarySearch(now, 0, now.size() - 1, left);
        // 不存在这样的左端点
        if (now.get(a) > right || now.get(a) < left) return 0;

        // 第二次二分，找右端点的下标
        int b = binarySearch(now, a, now.size() - 1, right);
        if (now.get(b) > right) {
            b--;
        }
        return b - a + 1;

    }
    public int binarySearch(List<Integer> nums, int l , int r, int target) {
        while (l < r) {
            int mid = (r - l) / 2 + l;
            if (nums.get(mid) < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }


}

/**
 * Your RangeFreqQuery object will be instantiated and called as such:
 * RangeFreqQuery obj = new RangeFreqQuery(arr);
 * int param_1 = obj.query(left,right,value);
 */
