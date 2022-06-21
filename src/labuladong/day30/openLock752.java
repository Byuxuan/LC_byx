package labuladong.day30;

import labuladong.TreeNode;

import java.util.*;

/**
 * DATE: 2022/3/27
 * CREATE BY: Byx
 */
public class openLock752 {
    String plusOne(String s, int j) {
        String temp;
        if (s.charAt(j) == '9') {
            temp = s.substring(0, j) + '0' + s.substring(j + 1);
        } else {
            temp = s.substring(0, j) + (s.charAt(j) - 47) + s.substring(j + 1);
        }
        return temp;
    }

    String minusOne(String s, int j) {
        String temp;
        if (s.charAt(j) == '0') {
            temp = s.substring(0, j) + '9' + s.substring(j + 1);
        } else {
            temp = s.substring(0, j) + (s.charAt(j) - 49) + s.substring(j + 1);
        }
        return temp;
    }

    int openLock2(String[] deadends, String target) {
        HashSet<String> dead = new HashSet<>();
        for (String deadend : deadends) {
            dead.add(deadend);
        }
        HashSet<String> visit = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                if(dead.contains(curr)) continue;
                if (curr.equals(target)) {
                    return res;
                }
                for (int j = 0; j < 4; j++) { // 每个位置
                    String up = plusOne(curr, j);
                    if (!visit.contains(up)) {
                        queue.offer(up);
                        visit.add(up);
                    }
                    String down = minusOne(curr, j);
                    if (!visit.contains(down)) {
                        queue.offer(down);
                        visit.add(up);
                    }
                }
            }
            res++;
        }
        return -1;
    }

    int openLock(String[] deadends, String target) {

        Set<String> dead = new HashSet<>(Arrays.asList(deadends));
        Set<String> visited = new HashSet<>();

        Set<String> q1 = new HashSet<>();
        Set<String> q2 = new HashSet<>();
        q1.add("0000");
        q2.add(target);
        int step = 0;
        while (!q1.isEmpty() && !q2.isEmpty()) {
            Set<String> temp = new HashSet<>();
            int size = q1.size();
            for (String curr : q1) {
                if (dead.contains(curr)) {
                    continue;
                }
                if (q2.contains(curr)) {
                    return step;
                }
                visited.add(curr);
                Queue<Integer> queue = new LinkedList<>();
                for (int j = 0; j < 4; j++) {
                    String upString = plusOne(curr, j);
                    String miString = minusOne(curr, j);
                    if (!visited.contains(upString)) {
                        temp.add(upString);
                    }
                    if (!visited.contains(miString)) {
                        temp.add(miString);
                    }

                }
            }
            step++;
            q1 = q2;
            q2 = temp;

        }
        return -1;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        boolean flag = true;
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for(int i = 0; i < size; i++){
                TreeNode currNode = queue.poll();
                if(flag){
                    temp.add(currNode.val);
                }else{
                    temp.add(0, currNode.val);
                }
                if(currNode.left != null) queue.offer(currNode.left);
                if(currNode.right != null) queue.offer(currNode.right);
            }
            flag = !flag;
            result.add(temp);
        }
        return result;

    }

    public static void main(String[] args) {
        System.out.println(new openLock752().openLock(new String[]{"0201","0101","0102","1212","2002"}, "0202"));
    }
}
