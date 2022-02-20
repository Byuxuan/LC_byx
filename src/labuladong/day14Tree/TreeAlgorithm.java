package labuladong.day14Tree;

import labuladong.TreeNode;
import labuladong.Node;

/**
 * DATE: 2022/2/9
 * CREATE BY: Byx
 */
public class TreeAlgorithm {
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return null;
        TreeNode temp = root.left;
        root.right = root.left;
        root.left = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    public Node connect116(Node root) {
        if (root == null || root.left == null) {
            return root;
        }
        connectTwoNode(root.left, root.right);
        return root;

    }

    private void connectTwoNode(Node left, Node right) {
        if (left == null || right == null) {
            return;
        }

        left.next = right;

        connectTwoNode(left.left, left.right);
        connectTwoNode(right.left, right.right);
        connectTwoNode(left.right, right.left);

    }

    public void flatten114(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten114(root.left);
        flatten114(root.right);


        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = null;
        root.right = left;

        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;
    }

    public TreeNode constructMaximumBinaryTree654(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int index = -1;
        int maxValue = Integer.MIN_VALUE;
        for (int i = left; i <= right; i++) {
            if (nums[i] > maxValue) {
                index = i;
                maxValue = nums[i];
            }
        }
        TreeNode root = new TreeNode(maxValue);
        root.left = build(nums, left, index - 1);
        root.right = build(nums, index + 1, right);
        return root;
    }

    public TreeNode buildTree115(int[] preorder, int[] inorder) {
        return buildTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int[] inorder, int preorderLeft, int preorderRight, int inorderLeft, int inorderRight) {
        if (preorderLeft > preorderRight || inorderLeft > inorderRight) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preorderLeft]);

        int preOrderLeftChildLeft = preorderLeft + 1;
        int preOrderLeftChildRight = -1;
        int inorderLeftChildLeft = inorderLeft;
        int inorderLeftChildRight = -1;


        int preOrderRightChildLeft = -1;
        int preOrderRightChildRight = preorderRight;
        int inorderRightChildLeft = -1;
        int inorderRightChildRight = inorderRight;

        int index = -1;
        for (int i = 0; i < inorder.length; i++) {
            if (preorder[preorderLeft] == inorder[i]) {
                index = i;
                break;
            }
        }
        int size = index - inorderLeft;
        preOrderLeftChildRight = size + preorderLeft;
        inorderLeftChildRight = index - 1;
        inorderRightChildLeft = index + 1;
        preOrderRightChildLeft = size + preorderLeft + 1;

        root.left = buildTree(preorder, inorder, preOrderLeftChildLeft, preOrderLeftChildRight, inorderLeftChildLeft, inorderLeftChildRight);
        root.right = buildTree(preorder, inorder, preOrderRightChildLeft, preOrderRightChildRight, inorderRightChildLeft, inorderRightChildRight);
        return root;
    }

    public TreeNode buildTree106(int[] inorder, int[] postorder) {
        TreeNode root = buildTree106(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
        return root;
    }

    public TreeNode buildTree106(int[] inorder, int[] postorder, int inBegin, int inEnd, int postBegin, int postEnd) {
        if (inBegin > inEnd || postBegin > postEnd) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[postEnd]);

        int leftChildInBegin = inBegin;
        int leftChildInEnd = -1;
        int leftChildPostBegin = postBegin;
        int leftChildPostEnd = -1;

        int rightChildInBegin = -1;
        int rightChildInEnd = inEnd;
        int rightChildPostBegin = -1;
        int rightChildPostEnd = postEnd - 1;

        int index = -1;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == postorder[postEnd]) {
                index = i;
                break;
            }
        }
        int size = index - inBegin;
        leftChildInEnd = index - 1;
        leftChildPostEnd = inBegin + size;

        rightChildInBegin = index + 1;
        rightChildPostBegin = inBegin + size + 1;

        root.left = buildTree106(inorder, postorder, leftChildInBegin, leftChildInEnd, leftChildPostBegin, leftChildPostEnd);
        root.right = buildTree106(inorder, postorder, rightChildInBegin, rightChildInEnd, rightChildPostBegin, rightChildPostEnd);
        return root;
    }


}
