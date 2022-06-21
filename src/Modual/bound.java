package Modual;



import java.util.*;

/**
 * DATE: 2022/5/11
 * CREATE BY: Byx
 */
public class bound {
    /**
     * 第一个大于目标值的下标
     * @param nums
     * @param left
     * @param right
     * @param target
     * @return
     */
    public int up_bound(int []nums, int left, int right, int target){
        while (left <= right){
            int mid = (left + right) >> 1;
            if(nums[mid] <= target){
                left = mid + 1;
            }else {
                right = mid - 1;
                /**
                 * [1] target = 0, right = 0, left = 1;
                 */
            }
        }
        return left;

    }

    /*
    第一个大于等于目标值
     */
    public int down_bound(int []nums, int left, int right, int target){
        while (left <= right){
            int mid = (left + right) >> 1;
            if(nums[mid] < target){
                left = mid + 1;
            }else {
                right = mid - 1; // 虽然m有可能是目标解，直接m-1会错过，但是最后如果在 l 和 m -1 里面找不到， l会取“m+1”，跳出循环，这里的“m+1“其实就是错过的目标解
            }
        }
        return left;
    }
    public String largestNumber(int[] nums) {
        String[] strings = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strings[i] = nums[i] + "";
        }
        Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String string1 = o1 + o2;
                String string2 = o2+ o1;
                return string2.compareTo(string1);
            }
        });
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            res.append(strings[i]);
        }
        String finalRes = res.toString();
        int k = 0;
        while (k < finalRes.length() - 1 && finalRes.charAt(k) == '0') {
            k++;
        }
        return finalRes.substring(k);

    }


    public int lengthOfLongestSubstring(String s) {
        if(s.length() <= 0) return 0;
        HashMap<Character, Integer> hash = new HashMap<>();
        int right = 0;
        int left = 0;
        int res = 0;
        while(right < s.length()){
            if(hash.containsKey(s.charAt(right))){
                // int curr =
                // for(int i = left; i <= curr; i++){
                //     hash.remove(s.charAt(i));
                // }
                left = hash.get(s.charAt(right)) + 1;
            }
            hash.put(s.charAt(right), right);
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }
    public void rotate(int[] nums, int k) {
        int index  = 0;
        int i = 0;
        int curr = nums[i];


        while(index < nums.length){
            int target = nums[(i % nums.length + k) % nums.length];
            nums[(i % nums.length + k) % nums.length] = curr;
            curr = target;
            i = (i % nums.length + k) % nums.length;
            index++;
        }
    }


    public static void main(String[] args) {
        //new bound().rotate(new int[]{1,2,3,4},2);
        StringBuilder stringBulilder = new StringBuilder();
        System.out.println(new bound().up_bound(new int[]{1,2,3,1}, 0, 3, 2));
        //System.out.println(new bound().lengthOfLongestSubstring("abba"));
    }
}
