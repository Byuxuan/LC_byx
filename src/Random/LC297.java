package Random;

import labuladong.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * DATE: 2022/6/5
 * CREATE BY: Byx
 */
public class LC297 {
    StringBuilder treeString = new StringBuilder();
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        preOrder(root);
        return treeString.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String []strings = data.split(",");
        LinkedList<String> nodes = new LinkedList<>();

        for (int i = 1; i < strings.length ; i++) {
            nodes.add(strings[i]);
        }


        return preDecoder(nodes);

    }

    public void preOrder(TreeNode root){
        if(root == null){
            treeString.append(",");
            treeString.append("null");
            return;
        }
        treeString.append(",");
        treeString.append(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    public TreeNode preDecoder(LinkedList<String> nodes){
        if(nodes.isEmpty()) return null;
        String first = nodes.removeFirst();
        if(first.equals("null")) return null;
        TreeNode root = new TreeNode(Integer.parseInt(first));
        root.left = preDecoder(nodes);
        root.right = preDecoder(nodes);
        return root;
    }


    public String serialize2(TreeNode root) {
        StringBuilder strings = new StringBuilder();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode curr = queue.removeFirst();
            if(curr == null){
                strings.append(",");
                strings.append("null");
                continue;
            }else{
                strings.append(",");
                strings.append(curr.val);
            }
            queue.add(curr.left);
            queue.add(curr.right);

        }
        return strings.toString();
    }


    public TreeNode deserialize2(String data) {
        String []nodes = data.split(",");
        int index = 2;
        TreeNode root = new TreeNode(Integer.parseInt(nodes[1]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (index < nodes.length) {
            TreeNode curr = queue.poll();
            String left = nodes[index++];
            if (!left.equals("null")) {
                curr.left = new TreeNode(Integer.parseInt(left));
                queue.offer(curr.left);
            }else{
                curr.left = null;
            }

            String right = nodes[index++];
            if (!right.equals("null")) {
                curr.right = new TreeNode(Integer.parseInt(right));
                queue.offer(curr.right);
            }else {
                curr.right = null;
            }


        }
        return root;
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode root1 = new TreeNode(2);
        TreeNode root2 = new TreeNode(3);
        TreeNode root3 = new TreeNode(4);
        root.left = root1;
        root.right = root2;
        root1.left = root3;
        System.out.println(new LC297().serialize2(null));
        //System.out.println(new LC297().deserialize2(",1,2,3,4,null,null,null,null,null"));
    }

}
