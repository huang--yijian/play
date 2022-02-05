package org.hyj.leetcode;
// https://leetcode-cn.com/problems/qJnOS7/
public class L2378 {
    // dp[i][j] is the length of the Larget Common SubSequence
    // between text[0,i] and text2[0,j]
    private int[][] dp  = null;

    public int longestCommonSubsequence(String text1, String text2) {
        dp = new int[text1.length()][text2.length()];

        // System.out.println("text1 0: " + at(text1,0) + " text2 0: " + at(text2,0)
        //     + same + "");


        for (int i = 0; i <= text1.length()-1; i++) {
            for (int j = 0; j <= text2.length()-1; j++) {
                int sameChar = 0;
                if (at(text1,i)==at(text2,j)) {
                    sameChar = 1;
                }

                int lcsOfLeftTop = 0;
                // If the left top cell exists
                if (i>=1 && j>=1) {
                    if (at(text1,i)==at(text2,j)) {
                        lcsOfLeftTop = dp[i-1][j-1] + 1;
                    } else {
                        lcsOfLeftTop = dp[i-1][j-1];
                    }
                }

                int lcsOfLeft = 0;
                // If the left cell exists
                if (j >= 1) {
                    lcsOfLeft = dp[i][j-1];
                }

                int lcsOfTop = 0;
                // If the upper cell exists
                if (i >= 1) {

                    lcsOfTop = dp[i-1][j];
                }


                dp[i][j] = max(sameChar, lcsOfLeftTop, lcsOfLeft, lcsOfTop);


                // System.out.println("dp[" + i + "," + j + "] is " + dp[i][j]);
            }
        }

        return dp[text1.length()-1][text2.length()-1];
    }

    private char at(String str, int i) {
        return str.charAt(i);
    }

    private int max(int d, int a, int b, int c) {
        int max = a;
        if (b > max) {
            max = b;
        }
        if (c > max) {
            max = c;
        }
        if (d > max) {
            max = d;
        }
        return max;
    }
}
