package org.hyj.leetcode;
import java.util.ArrayList;

// https://leetcode-cn.com/problems/edit-distance/
public class L72EditDistanceTrialTwo {
    private ArrayList<StringBuilder> queue = new ArrayList<StringBuilder>();
    private String target;
    int depth = 0;

    public int minDistance(String word1, String word2) {
        target = word2;
        // System.out.println(" a is " + (int)'a' + " z is " + (int)'z');
        StringBuilder sb = new StringBuilder(word1);
        queue.add(sb);
        transform(sb);
        return depth;
    }

    private int transform(StringBuilder word) {
        // for the current word
        // make each single-char possible transform
        // add each transformed result into queue. add depth + 1
        // get next from queue, transform at depth i + 1

        while(queue.size() > 0 ) {

            int sizeOfCurrentDepth = queue.size();
            for (int k = 0; k < sizeOfCurrentDepth; k++) {
                // Get first sizeOfCurrentDepth elements of the same depth

                StringBuilder top = (StringBuilder)(queue.remove(0));

                if (top.toString().equals(target)) {
                    return depth;
                }

                // Alter each one letter with each new letter
                for (int index = 0; index < top.length(); index++) {
                    for (int i=0; i<target.length(); i++) {
                        StringBuilder sb = new StringBuilder(top.toString());
                        sb.setCharAt(index, target.charAt(i));
                        queue.add(sb);
                    }
                }

                if (top.length() < target.length()) {
                    // Insert a new letter after current letter
                    for (int index = 0; index< top.length(); index++) {
                        for (int i=0; i<target.length(); i++) {
                            StringBuilder sb = new StringBuilder(top.toString());
                            sb.insert(index, target.charAt(i));
                            queue.add(sb);
                        }
                    }
                }

                for (int index = 0; index < top.length(); index++) {
                    StringBuilder sb = new StringBuilder(top.toString());
                    sb.deleteCharAt(index);
                    queue.add(sb);
                }
            }
            depth++;
        }
        return 99999999;
    }
}
