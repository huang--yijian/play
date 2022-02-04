package org.hyj.leetcode;
import java.util.ArrayList;

// https://leetcode-cn.com/problems/edit-distance/
public class L72EditDistanceTrialOne {

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
            // for the letter at depth i
            // transform this letter, add each transformed string into queue, add depth by 1
            // get next from queue, transform at depth i + 1

            while(queue.size() > 0 ) {

                int sizeOfCurrentDepth = queue.size();
                for (int k = 0; k < sizeOfCurrentDepth; k++) {
                    // Get first sizeOfCurrentDepth elements of the same depth

                    StringBuilder top = (StringBuilder)(queue.remove(0));
                    if (depth >= top.length()) {
                        // System.out.println("depth 5 top: " + top);
                        continue;
                    }


                    if (top.toString().replaceAll("|","").equals(target)) {
                        return depth;
                    }

                    if (top.charAt(depth) == '|') {
                        continue;
                    }

                    // Replace current letter with a new letter
                    for (int i=0; i<target.length(); i++) {
                        StringBuilder sb = new StringBuilder(top.toString());
                        sb.setCharAt(depth, target.charAt(i));
                        queue.add(sb);

                        System.out.println("Adding altered: " + sb + " depth: " + depth);
                    }

                    if (top.length() < target.length()) {
                        // Insert a new letter after current letter
                        for (int i=0; i<target.length(); i++) {
                            StringBuilder sb = new StringBuilder(top.toString());
                            sb.append(target.charAt(i));
                            //System.out.println("Adding appended: " + sb + " depth: " + depth);
                            queue.add(sb);
                        }
                    }

                    // Delete current letter by replacing it with |
                    StringBuilder sb = new StringBuilder(top.toString());
                    //System.out.println("sb to delete: " + sb + " depth: " + depth );
                    sb.setCharAt(depth, '|');
                    System.out.println("Adding deleted: " + sb + " depth: " + depth);
                    queue.add(sb);
                }
                depth++;
            }
            return 99999999;
        }
}
