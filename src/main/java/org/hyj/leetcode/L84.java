package org.hyj.leetcode;

public class L84 {
    public static void main(String[] args) {
        System.out.println("Result is " + new L84().largestRectangleArea(new int[]{0,0}));
    }

    private int[] HEIGHTS;
    // max of three independant possibilities, i.e:
    // largest(0,i+1) = max(
    //      do not include i+1: largest(0,i),
    //      include i+1: max of for j=0 to i area(j,i+1),
    //      i+1 alone: area(i+1))
    // )

    public int largestRectangleArea(int[] heights) {
        HEIGHTS = heights;

        return largest(heights.length - 1);
    }

    private int largest(int i) {
        if (i == 0) {
            return HEIGHTS[i];
        }
        int oldLargest = largest(i-1);

        int mergedMax = 0;
        for (int j = 0; j < i; j++) {

            int merged = merge(j,i);
            if (merged > mergedMax) {
                mergedMax = merged;
            }
        }

        int alone = HEIGHTS[i]*1;

        return max(oldLargest, mergedMax, alone);
    }

    // Forcibly include column j with existing columns 0-j
    // The height is min of all
    // The width is j + 1
    private int merge(int i, int j) {
        if (i > j) {
            throw new RuntimeException("i: " + i + " is over j: " + j + ", invalid");
        }
        int height = minHeight(i, j);
        int width = j - i + 1;
        return height * width;
    }

    private int minHeight(int start, int end) {
        int minHeight = 999999;
        for (int i = start; i <= end; i++) {
            if (HEIGHTS[i] < minHeight) {
                minHeight = HEIGHTS[i];
            }
        }
        return minHeight;
    }

    private int max(int ...values) {
        int max = 0;
        for (int v:values) {
            if (v > max) {
                max = v;
            }
        }
        return max;
    }
}
