package org.hyj.leetcode;

/*
 https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray/submissions/

 L717Trial one didn't pass:
 [0,0,1,0,0,0,0,0,1,1]
 [0,1,0,1,1,0,0,0,0,0]

  dp[i,j] = max common tail subarray between nums1 ending at i, and nums2 ending at j

  dp[i,j] =
    if (nums[i]==nums[j]) {
      dp[i-1][j-1]+1
    } else {
      0;
    }
*/
public class L718TrialTwo {

    private int M = 0, N = 0;
    private int[][] dp = null;

    public int findLength(int[] nums1, int[] nums2) {
        M = nums1.length;
        N = nums2.length;
        dp = new int[M][N];

        for (int i = 0 ; i <= M-1; i++) {
            for (int j = 0 ; j <= N-1; j++) {
                if (nums1[i]==nums2[j]) {
                    dp[i][j] = dp(i-1,j-1) + 1;
                } else {
                    dp[i][j] = 0;
                }
            }
        }


        int max = 0;
        for (int i = 0 ; i <= M-1; i++) {
            for (int j = 0 ; j <= N-1; j++) {
                if (dp(i,j) > max) {
                    max = dp(i,j);
                }
            }
        }


        return max;
    }

    private int dp(int i,int j) {
        if (i<0 || j<0) {
            return 0;
        }
        return dp[i][j];
    }
}
