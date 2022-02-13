package org.hyj.leetcode;

import org.hyj.common.TreeNode;

// https://leetcode-cn.com/problems/invert-binary-tree/
public class L226 {
    public TreeNode invertTree(TreeNode root) {
        if (null == root) return root;
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
