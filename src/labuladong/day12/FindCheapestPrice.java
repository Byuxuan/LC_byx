package labuladong.day12;

import labuladong.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * DATE: 2022/2/7
 * CREATE BY: Byx
 */
public class FindCheapestPrice {
    public int minDepth111(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int res = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode currNode = queue.poll();
                if(currNode.left == null && currNode.right == null){
                    return res;
                }
                if (currNode.left != null) queue.offer(currNode.left);
                if (currNode.right != null) queue.offer(currNode.right);
            }
            res++;

        }
        return res -1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);
        root.left = node1;
        root.right = node2;
        node2.left = node3;
        node2.right = node4;
        System.out.println(new FindCheapestPrice().minDepth111(root));
    }
}
