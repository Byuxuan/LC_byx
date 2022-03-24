package labuladong.SmallRedBook;

import labuladong.TreeNode;

/**
 * DATE: 2022/3/4
 * CREATE BY: Byx
 */
public class maximumAverageSubtree1120 {
        public double res = 0;
        public double maximumAverageSubtree(TreeNode root) {
            if(root == null) return 0.0;
            postOrder(root);
            return  res;
        }

        public double[] postOrder(TreeNode root) {
            if(root == null) return new double[]{0, 0};
            double[] left = postOrder(root.left);
            double[] right = postOrder(root.right);
            res = Math.max(res, (left[1] + right[1] + root.val) / (left[0] + right[0] + 1));
            return new double[]{left[0] + right[0] + 1, left[1] + right[1] + root.val};
        }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        TreeNode node1 = new TreeNode(1);
        root.right = node1;
        System.out.println(new maximumAverageSubtree1120().maximumAverageSubtree(root));
    }
}
