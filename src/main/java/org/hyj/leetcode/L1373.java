package org.hyj.leetcode;

import org.hyj.common.TreeNode;

import java.util.Arrays;

// https://leetcode-cn.com/problems/maximum-sum-bst-in-binary-tree/
public class L1373 {
    private int maxSum = 0;

    public int maxSumBST(TreeNode root) {
        maxSum(root);
        return maxSum;
    }

    public void maxSum(TreeNode root) {
        if (null == root) {
            return;
        }
        if (isBST(root)) {
            collect(root);
        } else {
            maxSumBST(root.left);
            maxSumBST(root.right);
        }
    }

    private void collect(TreeNode node) {
        int sum = sum(node);
        if (sum > maxSum) {
            maxSum = sum;
        }
    }

    private boolean isBST(TreeNode root) {
        return null != getMinMaxForBSTOnly(root);
    }

    // If tree rooted at node is not a BST, return null
    // Otherwise, return max and min values among all nodes of this BST tree, including node.
    private int[] getMinMaxForBSTOnly(TreeNode node) {
        if (null == node) {
            return null;
        }
        if (null == node.left && null == node.right) {
            return new int[] {node.val, node.val};
        } else if (node.left != null && node.left.val >= node.val ) {
            return null;
        } else if (node.right != null && node.right.val <= node.val ) {
            return null;
        } else {
            int[] temp = getMinMaxForBSTOnly(node.left);
            Integer maxInLeft = null;
            // No left child
            if (null == node.left) {
                maxInLeft = null;
            }
            // Has left child, but left subtree is not a BST, then the entire tree is not a BST
            else if (null == temp) {
                return null;
            }
            else {
                maxInLeft = temp[1];
            }

            temp = getMinMaxForBSTOnly(node.right);
            Integer minInRight = null;
            if (null == node.right) {
                minInRight = null;
            }
            else if (null == temp) {
                return null;
            }
            else {
                minInRight = temp[0];
            }

            if (maxInLeft!=null && maxInLeft >= node.val) {
                return null;
            }
            if (minInRight!=null && minInRight <= node.val) {
                return null;
            }
            int[] result = getMinMax(node.val, maxInLeft, minInRight);
            System.out.println("Min max of node: " + node.val + " max in left: " + maxInLeft
                    + " min in right: " + minInRight + " result: " + Arrays.toString(result));
            return result;
        }
    }

    private int[] getMinMax(Integer... data) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < data.length; i++) {
            Integer value = data[i];
            if (null == value) {
                continue;
            }
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
        }
        return new int[] {min, max};
    }

    private int sum(TreeNode node) {
        if (null == node) {
            return 0;
        }
        if (null == node.left && null == node.right) {
            return node.val;
        }
        int result = node.val + sum(node.left) + sum(node.right);
        System.out.println("Sum of node: " + node.val + " left: " + sum(node.left)
                + " right: " + sum(node.right) + " result: " + result);

        if (result > maxSum) {
            maxSum = result;
        }

        return result;
    }

    private void print(TreeNode node) {
        if (null == node) {
            return;
        }
        System.out.println("Parent: " + node.val + "  Left child: " +
                (node.left == null ? null : node.left.val) + " Right child: " +
                (node.right == null ? null : node.right.val));
        print(node.left);
        print(node.right);
    }
}
