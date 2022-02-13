package org.hyj.algoritm;

public class LargestCommonSubstring {
    private int[][] dp = null;
    private int M=0,N=0;

    public static void main(String[] args) {
        System.out.println("Result: " +
                new LargestCommonSubstring().longestCommonSubstring("eabcd", "dabceacdq"));
    }

    /*
    dp[i,j] = longest common substring of text1 ending at i and text2 ending at j
    dp[i,j] = max of:

    if (s1[i]==s2[j]) {
      dp[i-1][j-1]+1
    } else {
        0
    }

    base
    if (i< 0 || j<0) {
      dp[i][j] == 0
    }



    output = longest of (for i = 0 to M-1, j=0 to N-1,  dp[i,j])

  */
    public int longestCommonSubstring(String text1, String text2) {
        dp = new int[text1.length()][text2.length()];
        M = text1.length();
        N = text2.length();

        for (int i = 0; i <= M-1;i++) {
            for (int j=0;j <= N-1;j++) {
                // base
                if (text1.charAt(i)==text2.charAt(j)) {
                    dp[i][j] = dp(i-1, j-1) + 1;
                } else {
                    dp[i][j] = 0;
                }
            }

        }

        int max = 0;
        for (int i = 0; i <= M-1;i++) {
            for (int j=0;j <= N-1;j++) {
                if (dp(i,j) > max) {
                    max = dp(i,j);
                }
            }
        }

        return max;
    }

    private int dp(int i, int j) {
        if (i<0 || j<0) {
            return 0;
        }
        return dp[i][j];
    }
}
