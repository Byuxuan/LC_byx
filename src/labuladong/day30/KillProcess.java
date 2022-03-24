package labuladong.day30;

import java.util.*;

/**
 * DATE: 2022/3/23
 * CREATE BY: Byx
 */
public class KillProcess {
    class Node{
        public int val;
        public ArrayList<Integer> child;
        Node(int val){
            this.val = val;
            this.child = new ArrayList<>();
        }
    }
    public List < Integer > killProcess(List < Integer > pid, List < Integer > ppid, int kill) {
        if(kill == 0) return Arrays.asList(0);
        HashMap<Integer, Node>  hashMap = new HashMap<>();
        for(int val : ppid){
            if(!hashMap.containsKey(val)){
                Node node = new Node(val);
                hashMap.put(val, node);
            }
        }
        for(int i = 0; i < pid.size(); i++){
            if(!hashMap.containsKey(pid.get(i))){
                Node node = new Node(pid.get(i));
                hashMap.put(pid.get(i), node);
            }
            Node curr = hashMap.get(ppid.get(i));
            curr.child.add(pid.get(i));
        }

        List<Integer> result = new ArrayList<>();

        DeleteProcess(hashMap, result, kill);
        return result;
    }
    public void DeleteProcess(HashMap<Integer, Node> hashMap, List<Integer> result, int kill){
        result.add(kill);
        ArrayList<Integer> list = hashMap.get(kill).child;
        for(int i = 0; i < list.size(); i++){
            DeleteProcess(hashMap, result, list.get(i));
        }
    }

    public static void main(String[] args) {
        System.out.println(new KillProcess().killProcess(Arrays.asList(1,3,10,5), Arrays.asList(3,0,5,3),5));
    }
}
