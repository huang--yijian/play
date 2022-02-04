package org.hyj.leetcode;

import java.util.HashSet;

// https://leetcode-cn.com/problems/count-number-of-homogenous-substrings/
public class L1759 {

    /*
        Make a choice by cut at a single place.
        Count all homo strings with all existing choices.
        Recurse into that choice

        Cancel that choice
    */

    //private int count = 0;

    private HashSet set = new HashSet();

    public static void main(String[] args) {
        System.out.println("Result: " + new L1759().countHomogenous("zzzzz"));
    }

    public int countHomogenous(String s) {
        backtrace(new StringBuilder(s), 0);
        return set.size();
    }

    private void backtrace(StringBuilder path, int offset) {
        findHomoTokens(path);
        for (int i = offset + 1 ; i <= path.length() - 1; i++) {
            // If we haven't insert a comma at current location,
            // insert at current locaiton, and recurse
            if (path.charAt(i)!=',' && path.charAt(i-1)!=',') {
                path.insert(i, ",");

                //System.out.println("path after insert at i: " + i + " : " + path);

                // If it is in creasing, collect
                //count++;

                // recurse
                // System.out.println("recurse into " + path);
                backtrace(path, +1);
                // cancel this choice
                path.deleteCharAt(i);
                // System.out.println("restored into " + path);
            }
        }
    }

    private void findHomoTokens(StringBuilder path) {
        String substring = path.toString();
        // System.out.println("Find homo tokens in: " + substring);
        String[] tokens = substring.split(",");

        int i = 0;
        for (String token : tokens) {
            if (isHomoString(token)) {
                System.out.println("is homo token: " + token + "-" + i);
                set.add(token + "-" + i);
                //count++;
            }
            i += token.length();
        }
    }

    private boolean isHomoString(String str) {
        if (null == str || "" == str) {
            return false;
        }
        char prev = '-';
        for (char c : str.toCharArray()) {
            if (prev != '-' && prev != c ) {
                return false;
            }
            prev = c;
        }
        return true;
    }
}