package labuladong.day2;

import labuladong.TreeNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * DATE: 2022/1/2
 * CREATE BY: Byx
 */
public class minDepth111 {
    int minDepth(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return 0;
        }
        queue.offer(root);
        int currMin = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode currNode = queue.poll();
                if (currNode.left == null && currNode.right == null) {
                    return currMin;
                }
                if (currNode.left != null) {
                    queue.offer(currNode.left);
                }
                if (currNode.right != null) {
                    queue.offer(currNode.right);
                }
            }
            currMin++;
        }
        return currMin;
    }
}
