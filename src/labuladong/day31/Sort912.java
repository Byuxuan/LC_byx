package labuladong.day31;

import java.util.*;

/**
 * DATE: 2022/3/28
 * CREATE BY: Byx
 */
public class Sort912 {
    public int[] sortArray(int[] nums) {
        //Merge(nums, 0, nums.length - 1);
        QuickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void QuickSort(int[] nums, int left, int right) {
        if(left >= right) return;
        int index = partition(nums, left, right);
        QuickSort(nums, left, index - 1);
        QuickSort(nums, index + 1, right);
    }

    private int partition(int[] nums, int begin, int end) {
        int left = begin + 1;
        int right = end;
        while (left <= right) {
            while (left <= right && nums[left] <= nums[begin]) left++;
            while (left <= right && nums[right] >= nums[begin]) right--;
            if(left < right){
                Swap(nums, left, right);
            }
        }
        Swap(nums, begin, right);
        return right;
    }

    private void Swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    private void Merge(int[] nums, int left, int right) {
        if(left == right) return;
        int mid = left + (right - left) / 2;
        Merge(nums, left, mid);
        Merge(nums, mid + 1, right);
        MergeTwo(nums, left, mid, right);
    }

    private void MergeTwo(int[] nums, int left, int mid, int right) {
        int[] temp = new int[nums.length];
        for (int i = left; i <= right; i++) {
            temp[i] = nums[i];
        }
        int index1 = left;
        int index2 = mid + 1;

        for (int i = left; i <= right; i++) {
            if (index1 == mid + 1) {
                nums[i] = temp[index2++];
            } else if (index2 == right + 1) {
                nums[i] = temp[index1++];
            }else if(temp[index1] > temp[index2]){
                nums[i] = temp[index2++];
            }else {
                nums[i] = temp[index1++];
            }
        }
    }


    private class Pair {
        int val, id;
        Pair(int val, int id) {
            // 记录数组的元素值
            this.val = val;
            // 记录元素在数组中的原始索引
            this.id = id;
        }
    }

    int[] res;
    Pair[] temp;
    public List<Integer> countSmaller(int[] nums) {
        res = new int[nums.length];
        temp = new Pair[nums.length];
        Pair []pairs = new Pair[nums.length];
        for (int i = 0; i < nums.length; i++) {
            pairs[i] = new Pair(nums[i], i);
        }
        MergeSort(pairs, 0, nums.length - 1);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < res.length; i++) {
            result.add(res[i]);
        }
        return result;
    }

    private void MergeSort(Pair []pairs, int begin, int end) {
        if(begin == end) return;
        int mid = begin + (end - begin) / 2;
        MergeSort(pairs, begin, mid);
        MergeSort(pairs, mid + 1, end);
        MergeTwoList(pairs, begin, mid, end);
    }

    private void MergeTwoList(Pair []pairs, int begin, int mid, int end) {

        for (int i = begin; i <= end; i++) {
            temp[i] = pairs[i];
        }
        int index1 = begin;
        int index2 = mid + 1;
        for (int i = begin; i <= end; i++) {

            if (index1 == mid + 1) {
                pairs[i]= temp[index2++];
            } else if (index2 == end + 1) {
                pairs[i]= temp[index1++];
                res[pairs[i].id] += index2 - mid - 1; // 当i 进行移动的时候赋值
            } else if (temp[index1].val > temp[index2].val) {
                pairs[i]= temp[index2++];
            }else {
                pairs[i]= temp[index1++];
                res[pairs[i].id] += index2 - mid - 1;
            }
        }

    }

    public static void main(String[] args) {
        System.out.println(new Sort912().countSmaller(new int[]{5, 2, 6, 1}));
    }
}
