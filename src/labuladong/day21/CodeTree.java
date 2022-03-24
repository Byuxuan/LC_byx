package labuladong.day21;

import labuladong.TreeNode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * DATE: 2022/2/28
 * CREATE BY: Byx
 */
public class CodeTree {

    // Encodes a tree to a single string.
    public String serialize2(TreeNode root) {
        if(root == null) return "null";

        return  layerOrder(root);
    }

    public TreeNode deserialize2(String data) {
        if (data.equals("null")) {
            return null;
        }
        String[] node = data.split(",");
        Queue<TreeNode> queue = new ArrayDeque<>();
        TreeNode root = new TreeNode(Integer.parseInt(node[0]));
        queue.offer(root);
        for (int i = 1; i < node.length;) {
            TreeNode currNode = queue.poll();
            String left = node[i++];
            if(!"null".equals(left)) {
                currNode.left = new TreeNode(Integer.parseInt(left));
                queue.add(currNode.left);
            }
            String right = node[i++];
            if(!"null".equals(right)) {
                currNode.right = new TreeNode(Integer.parseInt(right));
                queue.add(currNode.right);
            }

        }
        return root ;

    }

    private String layerOrder(TreeNode root) {
        StringBuilder res = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int currSize = queue.size();
            for (int i = 0; i < currSize; i++) {
                TreeNode curr = queue.poll();
                if (curr == null) {
                    res.append("null");
                    res.append(",");
                    continue;
                }
                res.append(curr.val);
                res.append(",");
                queue.offer(curr.left);
                queue.offer(curr.right);

            }
        }
        res.delete(res.length() - 1, res.length());
        return res.toString();


    }

    public String serialize(TreeNode root){
        if(root ==null) return "null";
        StringBuilder res = new StringBuilder();
        preOrder(res, root);
        return res.toString();

    }

    private void preOrder(StringBuilder res, TreeNode root) {
        if (root == null) {
            res.append("null");
            res.append(",");
            return;
        }
        res.append(root.val);
        res.append(",");
        preOrder(res,root.left);
        preOrder(res,root.right);
    }

    public TreeNode deserialize(String data){
        if(data.equals("null")) return null;
        LinkedList<String> nodes = new LinkedList<>();
        for(String string : data.split(",")){
            nodes.addLast(string);
        }
        return dePreOrder(nodes);

    }

    private TreeNode dePreOrder(LinkedList<String> nodes) {
        if (nodes.isEmpty()) {
            return null;
        }
        String currNode = nodes.removeFirst();
        if (currNode.equals("null")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(currNode));
        root.left = dePreOrder(nodes);
        root.right = dePreOrder(nodes);
        return root;
    }


    // Decodes your encoded data to tree.


    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);

        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;
        node5.left = node6;
        String string = new CodeTree().serialize(node1);
        System.out.println(string);
//        TreeNode treeNode = new CodeTree().deserialize(string);
//        System.out.println(string);
 //       System.out.println(new CodeTree().serialize(treeNode));

    }
}
