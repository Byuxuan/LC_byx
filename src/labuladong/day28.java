package labuladong;

import java.util.*;

/**
 * DATE: 2022/3/20
 * CREATE BY: Byx
 */
public class day28 {
    public List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2) {
        List<List<Integer>> result = new ArrayList<>();
        int num1 = 0, num2 = 0;
        int cnt1 = 0, cnt2 = 0;
        int index1 = 0, index2 = 0;
        int num = 0;
        int cnt = 0;
        while(index1 < encoded1.length || index2 < encoded2.length){
            if(cnt1 == 0){
                cnt1 = encoded1[index1][1];
                num1 = encoded1[index1][0];
                index1++;
            }
            if(cnt2 == 0){
                cnt2 = encoded2[index2][1];
                num2 = encoded2[index2][0];
                index2++;
            }
            int newCnt = Math.min(cnt1, cnt2);
            cnt1 -= newCnt;
            cnt2 -= newCnt;
            int newNum = num1 * num2;
            if (newNum == num) {
                cnt += newCnt;
            } else {
                if (cnt != 0) {
                    result.add(Arrays.asList(num, cnt));
                }
                num = newNum ;
                cnt = newCnt;
            }
        }
        if (cnt != 0) {
            result.add(Arrays.asList(num, cnt));
        }
        return result;
    }

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if(s.length() <= 1) return s.length();
        int left = 0;
        int right = 1;
        int res = 1;
        HashMap<Character, Integer> hashSet = new HashMap<>();
        hashSet.put(s.charAt(0), 1);
        while(right < s.length()){
            if(hashSet.size() < 2 || hashSet.containsKey(s.charAt(right))){
                hashSet.put(s.charAt(right), hashSet.getOrDefault(s.charAt(right), 0) + 1);
                res = Math.max(res, right - left + 1);
                right++;
            }else{
                while (hashSet.size() > 1) {
                    if (hashSet.get(s.charAt(left)) > 1) {
                        hashSet.put(s.charAt(left), hashSet.get(s.charAt(left)) - 1);
                    }else {
                        hashSet.remove(s.charAt(left));
                    }
                    left++;

                }
            }

        }
        return res;
    }


    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int right = 0;
        int res = 0;
        int currK = k;
        while (right < nums.length) {
            while (right < nums.length && (nums[right] == 1 || currK > 0)) {
                if(nums[right] == 1){
                    right++;
                    continue;
                }
                currK--;
                right++;
            }
            res = Math.max(res, right - left);

            if(right == nums.length -1) break;

            while(left < nums.length){
                if(k > 0){

                }
                if(nums[left] != 0){

                    left++;
                }else {
                    left++;
                    right = left + 1;
                    currK = nums[left] == 1 ? k : k - 1;
                    break;
                }
            }

        }
        return res;
    }


    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if(s.length() <= k) return s.length();
        int left = 0;
        int right = 1;
        int res = 1;
        HashMap<Character, Integer> hashSet = new HashMap<>();
        hashSet.put(s.charAt(0), 1);
        while(right < s.length()){
            if(hashSet.size() < k || hashSet.containsKey(s.charAt(right))){
                hashSet.put(s.charAt(right), hashSet.getOrDefault(s.charAt(right), 0) + 1);
                res = Math.max(res, right - left + 1);
                right++;
            }else{
                while (hashSet.size() > k - 1) {
                    if (hashSet.get(s.charAt(left)) > 1) {
                        hashSet.put(s.charAt(left), hashSet.get(s.charAt(left)) - 1);
                    }else {
                        hashSet.remove(s.charAt(left));
                    }
                    left++;

                }
            }

        }
        return res;
    }



    public static void main(String[] args) {
//        System.out.println(new day28().findRLEArray(new int[][]{{1, 3}, {2, 1}, {3, 2}}, new int[][]
//                {{2, 3}, {3, 3}}));
        //System.out.println(new day28().lengthOfLongestSubstringTwoDistinct("abaccc"));
        //System.out.println(new day28().longestOnes(new int[]{0,0, 1, 1, 1, 0, 0}, 0));
        //System.out.println(new day28().maxSlidingWindow3(new int[]{1,3,-1,-3,2,3,6,7}, 3));
        System.out.println(new day28().minWindow("aaaaaabbbbbcdd", "abcdd"));
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int []result = new int[nums.length - k +1];
        int left= 0;
        int right = 0;
        int currMax = Integer.MIN_VALUE;
        int global = 0;
        while(right < k){
            currMax = Math.max(currMax, nums[right]);
            right++;
        }
        nums[global++] = currMax;
        while(right < nums.length){
            if(currMax == nums[left]){
                int index = left + 1;
                currMax = Integer.MIN_VALUE;
                while(index < left + k ){
                    currMax = Math.max(currMax, nums[index]);
                    index++;
                }
                nums[global++] = currMax;
            }else{
                currMax = Math.max(currMax, nums[right +1]);
                nums[global++] = currMax;
            }
            left++;
            right++;
        }

        PriorityQueue<int []> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return 0;
            }
        });
        return result;
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        PriorityQueue<int []> queue = new PriorityQueue<>(new Comparator<int []>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] != o2[0] ? o2[0] - o1[0]: o1[1] - o2[1];
            }
        });
        int []res = new int[nums.length - k +1];
        int global = 0;
        for(int i = 0; i < k; i++){
            queue.add(new int[]{nums[i], i});
        }
        res[global++] = queue.peek()[0];
        for(int i = k; i < nums.length; i++){
            while(queue.peek()[1] <= i - k){
                queue.poll();
            }
            queue.add(new int[]{nums[i], i});
            res[global++] = queue.peek()[0];
        }
        return res;

    }

    public int[] maxSlidingWindow3(int[] nums, int k) { //239
        if(nums.length==0||nums.length<k) return new int[0];
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < k-1; i++) {
            while (!arrayDeque.isEmpty()&&nums[i]>=nums[arrayDeque.peekLast()]){
                arrayDeque.pollLast();
            }
            arrayDeque.add(i);
        }
        for (int i = k - 1; i < nums.length; i++) {
            while (!arrayDeque.isEmpty()&&nums[i]>=nums[arrayDeque.peekLast()]){
                arrayDeque.pollLast();
            }
            arrayDeque.add(i);
            if (i - arrayDeque.peekFirst() >= k) {
                arrayDeque.pollFirst();
            }
            result[i-k+1] = nums[arrayDeque.peekFirst()];
        }
        return result;


    }



    public String minWindow(String s, String t){
        if(t.length() > s.length()) return "";
        HashMap<Character, Integer> target = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();
        int valid = 0;
        int left = 0;
        int right = 0;
        int currMin = Integer.MAX_VALUE;
        String res = "";
        for (int i = 0; i < t.length(); i++) {
            target.put(t.charAt(i), target.getOrDefault(t.charAt(i), 0) + 1);
        }
        while (right < s.length()) {
            if (target.containsKey(s.charAt(right))) {
                window.put(s.charAt(right), window.getOrDefault(s.charAt(right), 0) +1);
                if(window.get(s.charAt(right)) <= target.get(s.charAt(right)))
                {
                    valid++;
                }
            }
            while (valid == t.length()) {
                if(right - left + 1 < currMin){
                    res = s.substring(left, right + 1);
                    currMin = res.length();
                }
                if (target.containsKey(s.charAt(left))) {
                    window.put(s.charAt(left), window.get(s.charAt(left)) - 1);
                    if(window.get(s.charAt(left)) < target.get(s.charAt(left)))
                    {
                        valid--;
                    }
                }
                left++;
            }
            right++;
        }
        return res;
    }

}
