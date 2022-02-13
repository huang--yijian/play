package org.hyj.algoritm.tree;

import org.hyj.common.TreeNode;

public class LeastCommonAncestor {

    public static void main(String[] args) {
        // The tree is drawn in Page 262 of labuladong book.
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        System.out.println("Result: " +
                new LeastCommonAncestor().lowestCommonAncestor(root,
                        new TreeNode(6), new TreeNode(2)).val);
        System.out.println("Result: " +
                new LeastCommonAncestor().lowestCommonAncestor(root,
                        new TreeNode(6), new TreeNode(8)).val);
        System.out.println("Result: " +
                new LeastCommonAncestor().lowestCommonAncestor(root,
                        new TreeNode(4), new TreeNode(6)).val);
        System.out.println("Result: " +
                new LeastCommonAncestor().lowestCommonAncestor(root,
                        new TreeNode(4), new TreeNode(0)).val);
    }

    private TreeNode answer;

    private TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        score(root, p, q);
        return answer;
    }

    // If root is common ancestor of p and q, score is 2
    // If root tree contains p or q, score is 1
    private int score(TreeNode root, TreeNode p, TreeNode q) {
        if (null != answer) {
            return -1;
        }
        if (root == null) {
            return 0;
        }

        if (root.val == p.val || root.val == q.val) {
            return 1;
        }

        int leftScore = score(root.left, p, q);
        int rightScore = score(root.right, p, q);
        if (leftScore + rightScore == 2) {
            answer = root;
            return -1;
        }
        if (leftScore == 1 && rightScore == 0) {
            return 1;
        }
        if (leftScore == 0 && rightScore == 1) {
            return 1;
        }
        // left and right scores are both zero0
        return 0;
    }
}
