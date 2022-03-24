package labuladong.day25;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * DATE: 2022/3/14
 * CREATE BY: Byx
 */
public class dailyTemperature {
    public int[] dailyTemperatures(int[] temperatures) {
        if(temperatures.length == 0 ) return null;
        LinkedList<Integer> arry = new LinkedList<>();
        int []result = new int[temperatures.length];
        result[temperatures.length -1] = 0;
        arry.add(temperatures.length);
        for(int i = temperatures.length -2; i>=0; i--){
            while(arry.isEmpty() &&  temperatures[i] > arry.get(0)){
                arry.remove(0);
            }
            if(arry.isEmpty()) result[i] = 0;
            else{
                result[i] = arry.get(0) - i;
            }
            arry.add(0, i);
        }
        return result;

    }

    public String[] findRestaurant(String[] list1, String[] list2) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        int res = Integer.MAX_VALUE;
        int result = 0;
        ArrayList<Integer> arry = new ArrayList<>();
        for(int i = 0; i< list1.length; i++){
            hashMap.put(list1[i], i);
        }
        for(int i = 0; i<list2.length; i++){
            if(hashMap.containsKey(list2[i])){
                int temp = hashMap.get(list2[i]) + i;
                if(res == temp) arry.add(i);
                else if(res > temp){
                    arry.clear();
                    arry.add(i);
                }
                res = Math.min(res, temp);
            }
        }
        String []res1 = new String[arry.size()];
        for(int i = 0; i< arry.size(); i++){
            res1[i] = list2[arry.get(i)];
        }
        return res1;
    }


    int findMax(int mid, int [][]mat){
        if(mid < 0 || mid > mat.length -1) return -1;
        int res = 0;
        for(int i = 0; i< mat[mid].length; i++){
            if(mat[mid][i] > mat[mid][res]){
                res = i;
            }
        }
        return res;
    }
    public int[] findPeakGrid(int[][] mat) {
        int rowRight = mat.length -1;
        int rowLeft = 0;
        while(rowLeft <= rowRight){
            int mid = rowLeft + (rowRight - rowLeft) / 2;
            int currMax = findMax(mid, mat);
            int upMax = findMax(mid -1, mat);
            int downMax = findMax(mid +1, mat);
            if(upMax == -1 && downMax == -1) return new int[]{mid, currMax};
            if(upMax == -1){
                if(mat[mid +1][downMax] > mat[mid][currMax]) rowLeft++;
                else{
                    return new int[]{mid, currMax};
                }
            }
            else if(downMax == -1){
                if(mat[mid - 1][upMax] > mat[mid][currMax]) rowRight--;
                else{
                    return new int[]{mid, currMax};
                }
            }else{
                if (mat[mid + 1][downMax] > mat[mid][currMax]) {
                    rowLeft = mid + 1;
                } else if (mat[mid - 1][upMax] > mat[mid][currMax]) {
                    rowRight = mid - 1;
                }
                else {
                    return new int[]{mid, currMax};
                }
            }

        }
        return new int []{-1, -1};


    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new dailyTemperature().findPeakGrid(new int[][]{{41,8,2,48,18},{16,15,9,7,44},{48,35,6,38,28},{3,2,14,15,33},{39,36,13,46,42}})));
    }
}
