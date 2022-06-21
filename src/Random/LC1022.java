package Random;

import labuladong.TreeNode;

import javax.swing.*;

/**
 * DATE: 2022/5/30
 * CREATE BY: Byx
 */
public class LC1022 {
    int res = 0;
    public int sumRootToLeaf(TreeNode root) {

        String string = "";
        find(root, string);
        return res;
    }
    public void find(TreeNode root, String string){
        if(root == null) return;
        string = string + root.val;
        if(root.left == null && root.right == null){
            res += Integer.parseInt(string,2);
            return;
        }

        if(root.left != null){
            find(root.left, string);
        }

        if(root.right != null){
            find(root.right, string);
        }
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        System.out.println(new LC1022().sumRootToLeaf(node));

    }
}
