package labuladong.day15Tree;

import labuladong.TreeNode;

/**
 * DATE: 2022/2/16
 * CREATE BY: Byx
 */
public class maxPathSum {
    int result = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        preOrder(root);
        return result;
    }
    public int preOrder(TreeNode root){
        if(root == null) return 0;
        int left = preOrder(root.left);
        int right = preOrder(root.right);
        int res = root.val + right + left;
        result = Math.max(res, result);
        return root.val + Math.max(left, right);
    }

    public int maxSumBST(TreeNode root) {
        return postOrder(root)[3];
    }

    public int[] postOrder(TreeNode root){
        if(root == null){
            return new int[]{1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        }
        int []left = postOrder(root.left);
        int []right = postOrder(root.right);
        int []res = new int[4];
        if(left[0] == 1 && right[0] == 1 && root.val > left[2] && root.val < right[1]){
            res[0] = 1;
            res[1] = Math.min(root.val, Math.min(left[1], right[1]));
            res[2] = Math.max(root.val, Math.max(left[2], right[2]));
            res[3] = root.val + left[3] + right[3];
            result = Math.max(res[3], result);
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        TreeNode root2 = new TreeNode(4);
        TreeNode root3 = new TreeNode(3);
        TreeNode root4 = new TreeNode(2);
        TreeNode root5 = new TreeNode(4);
        TreeNode root6 = new TreeNode(2);
        TreeNode root7 = new TreeNode(5);
        root1.left = root2;
        root1.right = root3;
        root2.left = root4;
        root2.right = root5;
        root3.left = root6;
        root3.right = root7;


        System.out.println(new maxPathSum().maxSumBST(root1));
    }
}
