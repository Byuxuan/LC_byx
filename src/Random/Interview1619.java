package Random;

import javax.sound.midi.Soundbank;
import java.util.Arrays;
import java.util.HashMap;

/**
 * DATE: 2022/5/28
 * CREATE BY: Byx
 */
public class Interview1619 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution2().pondSizes(new int[][]{
                {0, 2, 1, 0},
                {0, 1, 0, 1},
                {1, 1, 0, 1},
                {0, 1, 0, 1}
        })));
    }
}

class Solution2 {
    public int[] pondSizes(int[][] land) {
        int n = 0;
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0; i < land.length; i++){
            for(int j = 0; j < land[0].length; j++){
                if(land[i][j] == 0){
                    map.put(""+i+"&"+j, n);
                    n++;
                }
            }
        }
        Union2 union = new Union2(n);
        for(int i = 0; i < land.length; i++){
            for(int j = 0; j < land[0].length; j++){
                if(land[i][j] == 0){
                    if( i - 1 >= 0){
                        if(land[i-1][j] == 0) union.union(map.get(""+i+"&"+j), map.get(""+(i-1)+"&"+(j)));
                        if(j -1 >= 0 && land[i-1][j-1] == 0) union.union(map.get(""+i+"&"+j), map.get(""+(i-1)+"&"+(j-1)));
                        if(j + 1 < land[0].length && land[i-1][j + 1] == 0) union.union(map.get(""+i+"&"+j), map.get(""+(i-1)+"&"+(j + 1)));

                    }
                    if( j -1 >= 0 && land[i][j-1] == 0) union.union(map.get(""+i+"&"+j), map.get(""+(i)+"&"+(j)));
                    if( j + 1 < land[0].length && land[i][j+1] == 0) union.union(map.get(""+i+"&"+j), map.get(""+(i)+"&"+(j+1)));

                }

            }
        }
        return union.pondSizes();
    }
}


class Union2{
    int count;
    int []parent;
    int []size;

    Union2(int n){
        this.count = n;
        parent = new int[n];
        size = new int[n];
        Arrays.fill(size, 1);
        for(int i= 0; i < n; i++){
            parent[i] = i;
        }
    }
    public int find(int x){
        while(parent[x] != x){
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    public void union(int p, int q){
        int rootP = find(p);
        int rootQ = find(q);
        if(rootP == rootQ) return;
        if(size[rootP] > size[rootQ]){
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }else{
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        count--;
    }


    public int[] pondSizes(){
        int []res = new int[count];
        int index = 0;
        for(int i = 0; i < parent.length; i++){
            if(parent[i] == i){
                res[index++] = size[i];
            }
        }
        Arrays.sort(res);
        return res;
    }
}
