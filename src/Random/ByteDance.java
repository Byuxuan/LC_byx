package Random;

/**
 * DATE: 2022/4/27
 * CREATE BY: Byx
 */
import java.util.*;
public class ByteDance {
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        if (v1.length != v2.length) {
            return 0;
        }
        for (int i = 0; i < v1.length; i++) {
            if (Integer.parseInt(v1[i]) > Integer.parseInt(v2[i])) {
                return 1;
            }
            if (Integer.parseInt(v1[i]) < Integer.parseInt(v2[i])) {
                return -1;
            }
        }
        return 0;
    }
    public int findPeakElement(int[] nums) {

        int left = 0;
        int right = nums.length -1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(mid > 0 && mid < nums.length -1 && nums[mid] > nums[left] && nums[mid -1] > nums[mid+1]) {return mid;}
            else if(mid > 0 && nums[mid] < nums[mid -1]){
                right--;
            }
            else if(mid < nums.length -1 && nums[mid] < nums[mid -1]){
                left++;
            }else{ // 剩下2个元素以内
                if(left == right) return left;
                else return nums[left] > nums[right]? left: right;
            }
        }
        return -1;

    }

    public List<Integer> spiralOrder(int[][] matrix) {
        int m1 = 0;
        int m2 = matrix.length -1; // hang
        int n1 = 0;
        int n2 = matrix[0].length -1; // lie
        List<Integer> res = new ArrayList<>();

        while(n1 <= n2 && m1 <= m2){
            int i = n1;
            while(i <= n2){
                res.add(matrix[m1][i]);
                i++;
            }
            i = m1 + 1;
            while(i <= m2){
                res.add(matrix[i][n2]);
                i++;
            }
            i = n2 -1;
            while(m1 != m2 && i >= n1){
                res.add(matrix[m2][i]);
                i--;
            }
            i = m2 -1;
            while(n1 != n2 && i >= m1){
                res.add(matrix[i][n1]);
                i--;
            }
            m1++;
            m2--;
            n1++;
            n2--;
        }
        return res;
    }
    public int maximumGap(int[] nums) {
        if(nums.length < 2) return 0;
        boolean [] map = new boolean[100000 + 1];
        for(int i = 0; i < nums.length; i++){
            map[nums[i]] = true;
        }
        int res = 0;
        int curr = 0;
        int i = 0;
        while(i <= 100001){

            while(i < 100001 && !map[i]) i++;
            curr = i;
            i++;
            while(i < 100001 && !map[i]) i++;
            res = Math.max(i - curr, res);
            i++;
        }
        return res;
    }


    Set<List<Integer>> res = new HashSet<>();
    List<List<Integer>> res2 = new ArrayList<>();
    List<Integer> curr = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if(candidates.length <= 0) return res2;
        Arrays.sort(candidates);
        DFS(0, 0, candidates, target);
        return (List<List<Integer>>) res;
    }

    public void DFS(int currSum, int index, int []candidates, int target){
        if(currSum == target){
            res.add((List)new ArrayList<>(curr));
            return;
        }
        if(currSum > target) return;
        for(int i = index; i <candidates.length; i++){
            currSum += candidates[i];
            curr.add(candidates[i]);
            DFS(currSum, i+1, candidates, target);
            currSum -= candidates[i];
            curr.remove(curr.size()-1);
        }
    }
    public static void main(String[] args) {
        //System.out.println(new ByteDance().findPeakElement(new int[]{1, 2, 1}));
        //System.out.println(new ByteDance().maximumGap(new int[]{3,6,9,1}));
        //System.out.println(new ByteDance().compareVersion("1.0","0.1"));
        System.out.println(new ByteDance().spiralOrder(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}}));



    }
}
