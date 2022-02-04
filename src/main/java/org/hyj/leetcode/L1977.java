package org.hyj.leetcode;

// https://leetcode-cn.com/problems/number-of-ways-to-separate-numbers/
public class L1977 {
    /*
       Each time make one choice of put a comma after one character
       Recurse with current choice into next depth of decision tree.
       At each iteration, if found the array is decreasing, abort.
       At each iteration, if found the array is completely non-decreasing, collect it.
   */
    private int count = 0;

    public static void main(String[] args) {
        System.out.println("result is: " +
                new L1977().numberOfCombinations("9999999999999"));
    }

    public int numberOfCombinations(String num) {
        backtrace(new StringBuilder(num), 1);
        return count;
    }

    private void backtrace(StringBuilder path, int offset) {
        if (!isValidArray(path)) {
            return;
        } else {
            count++;
        }
        for (int i = offset ; i <= path.length() - 1; i++) {
            // If we haven't insert a comma at current location,
            // insert at current locaiton, and recurse
            if (path.charAt(i)!=',' && path.charAt(i-1)!=',') {
                path.insert(i, ",");

                //System.out.println("path after insert at i: " + i + " : " + path);

                // If it is in creasing, collect
                //count++;

                // recurse
                // System.out.println("recurse into " + path);
                backtrace(path, i+1);
                // cancel this choice
                path.deleteCharAt(i);
                // System.out.println("restored into " + path);
            }
        }
    }

    private boolean isValidArray(StringBuilder path) {
        String[] tokens = path.toString().split(",");
        long prev = 0;
        for (String token : tokens) {
            if (token.startsWith("0")) {
                return false;
            }
            long current = Long.parseLong(token);
            if (current < prev) {
                return false;
            }
            prev = current;
        }
        System.out.println("is valid array. path: " + path);
        return true;
    }
}
