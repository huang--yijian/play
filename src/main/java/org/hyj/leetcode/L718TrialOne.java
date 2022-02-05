package org.hyj.leetcode;

// https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray/

class Result {
    int length = 0;
    boolean atTail = false;

    public Result() {
    }

    public Result(int l, boolean t) {
        length = l;
        atTail = t;
    }

    public Result(Result r) {
        length = r.length;
        atTail = r.atTail;
    }

    public String toString() {
        return String.format("(len: %s, atTail: %s)", length, atTail);
    }
}


// Does not pass
/*
                new int[] {0,1,0,0},
                new int[] {0,1,1,0,0}
 */
public class L718TrialOne {

    private int[][] dp = null;

    // [1,0,1,0,0,0,0,0,1,1]
    //[1,1,0,1,1,0,0,0,0,0]

    public static void main(String[] args) {
        System.out.println("Result is: " + new L718TrialOne().findLength(
                new int[] {0,1,0,0},
                new int[] {0,1,1,0,0}
        ));
    }

    public int findLength(int[] nums1, int[] nums2) {
        return dp(nums1, nums2, nums1.length -1, nums2.length - 1).length;

    }

    private Result dp(int[] nums1, int[] nums2, int i, int j) {
        if (i==0 && j==0 && nums1[i]==nums2[j]) {
            return new Result(1,true);
        }

        Result base = new Result();
        if (nums1[i]==nums2[j]) {
            base = new Result(1, true);
        }

        Result dpLeftTop = new Result();
        if (i >= 1 && j >= 1) {
            Result tmp = dp(nums1, nums2, i-1,j-1);
            if (nums1[i]!=nums2[j]) {
                dpLeftTop = new Result(tmp);
                dpLeftTop.atTail = false;
            } else {
                if (tmp.atTail) {
                    dpLeftTop = new Result(tmp);
                    dpLeftTop.length = tmp.length + 1;
                    dpLeftTop.atTail = true;
                } else {
                    if (tmp.length > 1) {
                        dpLeftTop = new Result(tmp);
                        dpLeftTop.atTail = false;
                    } else {
                        dpLeftTop =  new Result(1, true);
                    }
                }
            }
        }

        Result dpTop = new Result();
        if (i >= 1) {
            dpTop = new Result(dp(nums1, nums2, i-1,j));
            dpTop.atTail=false;
        }

        Result dpLeft = new Result();
        if (j >= 1) {
            dpLeft = new Result(dp(nums1, nums2, i,j-1));
            dpLeft.atTail=false;
        }

        Result m = max(base, dpLeftTop, dpTop, dpLeft);
        if (i==2 && j==4) {
          System.out.println(String.format("i:%s, j:%s, result:%s, base:%s, leftTop: %s, top:%s, left:%s", i, j, m, base, dpLeftTop, dpTop, dpLeft));
        }
        return m;
    }



    private Result max(Result a, Result b, Result c, Result d) {
        Result max = a;
        if (b.length > max.length) {
            max = b;
        }
        if (c.length > max.length) {
            max = c;
        }
        if (d.length > max.length) {
            max = d;
        }
        return max;
    }

}
