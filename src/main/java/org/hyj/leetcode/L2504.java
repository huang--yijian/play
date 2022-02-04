package org.hyj.leetcode;

import java.util.Arrays;

public class L2504 {
    /*
   A: Number of missing the left = min(nums) - 1
   B: Number of missing in the array = max(nums) - min(nums) - array length + 1;
   C: Number of missing after the end of array = 2 - B - A
*/
    private int missingA1 = 0;
    private int missingA2 = 0;

    private int missingBMax = 0;
    private int missingBMin = 0;

    private int missingC1 = 0;
    private int missingC2 = 0;

    private int[] result = new int[2];
    private int offset = 0;

    public static void main(String[] args) {
        System.out.println("Result is " + Arrays.toString(
                new L2504().missingTwo(new int[]{1, 2, 3, 4, 6, 7, 9, 10})));
    }

    public int[] missingTwo(int[] nums) {
        if (nums.length == 0) {
            return new int[] {1,2};
        }

        int max = getMax(nums);
        int min = getMin(nums);

        // Find A number of missing values before the start of array
        int A = min - 1;
        for (int i = 1; i < min; i++) {
            addA(i);
        }
        System.out.println("A " + A + " min " + min);
        if (A >= 2) {
            return new int[] {missingA1, missingA2};
        }

        // Find B number of missing values within the array
        int B = max - min - nums.length + 1;
        System.out.println("B is " + B + " max: " + max + " min: " + min + " length: " + nums.length);
        if (B > 0) {
            searchInArray(nums);
        }

        // Find C number of missing values after the end of the array
        int C = 2 - B - A;
        System.out.println("C is " + C);
        for (int i = 0; i < C; i++) {
            addC(max + i + 1);
        }

        collect();
        return result;
    }


    // keep the min max missing we have seen so far
    // finish the traversal, and return the min max missing values.
    private void searchInArray(int[] nums) {
        int offset = 0;
        int value = nums[0];
        int rightLimit = nums.length - 1;
        while (offset < rightLimit) {
            int nextOffset = offset + 1;
            value++;

            System.out.println("offset: " + offset
                    + " next offset"  + nextOffset + " next expected value " + value);
            if (nums[nextOffset]==value) {
                offset++;
            } else {
                addB(value);
            }
        }
    }

    private int getMax(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        return max;
    }

    private int getMin(int[] nums) {
        int min = 9999999;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }
        }
        return min;
    }

    private void addA(int a) {
        if (missingA1 == 0) {
            missingA1 = a;
        } else {
            missingA2 = a;
        }
    }

    private void addB(int a) {
        if (missingBMin == 0 || missingBMin > a) {
            missingBMin = a;
        } else if (missingBMax == 0 || missingBMax < a){
            missingBMax = a;
        }
    }

    private void addC(int a) {
        System.out.println("addC " + a);
        if (missingC1 == 0) {
            missingC1 = a;
        } else {
            missingC2 = a;
        }
    }

    private void collect() {
        int offset = 0;
        addResult(missingA1);
        addResult(missingA2);
        addResult(missingBMax);
        addResult(missingBMin);
        addResult(missingC1);
        addResult(missingC2);
    }

    private void addResult(int value) {
        if (offset == 0 && value > 0 && result[offset]==0) {
            result[offset] = value;
            offset++;
        } else if (offset == 1 && value > 0 && result[offset]==0 && result[0]!=value) {
            result[offset] = value;
            offset++;
        }

    }
}
