package org.hyj.leetcode;

// https://leetcode-cn.com/problems/longest-common-prefix/submissions/
public class L14 {


    private int[] dp = null;

    public static void main(String[] args) {
        System.out.println("Result: "
            +new L14().longestCommonPrefix(new String[] {"a", "abc", "adc"}));
    }

    /*
        dp[i] = longest common prefix ending at i;

        output = longest of dp[0], dp[1] ... dp[L-1]
    */
    public String longestCommonPrefix(String[] strs) {
        int length = minLength(strs);
        dp = new int[length];

        for (int i = 0; i <= length - 1; i++) {
            //System.out.println("is same char at " + i + " ?: " + isSameChar(strs, i));
            if (isSameChar(strs, i)) {
                if (i==0 || dp(i-1)>0) {
                    dp[i] = dp(i-1) + 1;
                }
            } else {
                dp[i] = 0;
            }
            //System.out.println("dp[" + i + "] is " + dp[i]);
        }

        int max = 0;
        for (int i = 0; i <= length - 1; i++) {
            if (max < dp[i]) {
                max = dp[i];
            }
        }
        //System.out.println("max is " + max);
        return strs[0].substring(0, max);
    }

    private int dp(int i) {
        if (i <0)
            return 0;
        return dp[i];
    }

    private boolean isSameChar(String[] strs, int offset) {
        Character c = null;
        for (int i = 0; i <= strs.length - 1; i++) {
            if (c == null) {
                c = strs[i].charAt(offset);
            } else if (c != strs[i].charAt(offset)) {
                return false;
            }
        }
        return true;
    }

    private int minLength(String[] strs) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < strs.length;i++) {
            if (min > strs[i].length()) {
                min = strs[i].length();
            }
        }
        return min;
    }
}
