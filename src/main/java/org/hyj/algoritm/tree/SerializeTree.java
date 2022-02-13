package org.hyj.algoritm.tree;

import com.sun.org.apache.xpath.internal.operations.String;
import org.hyj.common.TreeNode;

public class SerializeTree {
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(3);

        serialize(root);
        System.out.println("Serialized as: " + sb.toString());

        TreeNode root2 = deserialize();
        serialize(root2);
        System.out.println("Serialized again as: " + sb.toString());
    }

    private static void serialize(TreeNode node) {
        if (null == node) {
            sb.append('#');
            return;
        }

        sb.append(node.val);

        serialize(node.left);
        serialize(node.right);
    }

    private static TreeNode deserialize() {
        if ( sb.length() == 0) {
            return null;
        }

        char r = sb.charAt(0);
        sb.deleteCharAt(0);
        if (r=='#') {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(r + ""));
        root.left = deserialize();
        root.right = deserialize();
        return root;
    }
}
