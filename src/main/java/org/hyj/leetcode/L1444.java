package org.hyj.leetcode;


class CutResult {
    public String[] removed;
    public int applesInRemoved;
    public String[] remain;
    public int applesInRemain;
}

// https://leetcode-cn.com/problems/number-of-ways-of-cutting-a-pizza/
public class L1444 {
    private int count = 0;
    private int K;

    public int ways(String[] pizza, int k) {
        K = k;
        backtrace(pizza,0);
        return count;
    }

    private void backtrace(String[] remain, int cutNumber) {
        //print(remain);
        // abort on empty pizza
        if (remain.length == 0) {
            return;
        }
        else if (remain[0].length() == 0) {
            return;
        }
        if (cutNumber == K-1) {
            //System.out.println("Reach K limit " + K + " has apple " + hasApple(remain) + " remain");
            //print(remain);
            if (hasApple(remain)) {
                count++;
            }
            return;
        }

        int width = remain[0].length();
        int height = remain.length;

        // try all vertial cut
        // No need to cancel after making each choice,
        // because the choiced path is duplicated as a separate pizza.
        for (int cutColumn = 0; cutColumn < width - 1;cutColumn++) {
            CutResult r = cutV(remain, cutColumn);
            if (r.applesInRemain == 0) {
                continue;
            } else if (r.applesInRemoved == 0) {
                continue;
            } else { // (r.applesInRemoved > 0) {
                //System.out.println("Cut V at column: " + cutColumn);
                backtrace(r.remain, cutNumber + 1);
            }
        }

        // try all horizontal cut
        for (int cutRow = 0; cutRow < height - 1;cutRow++) {
            CutResult r = cutH(remain, cutRow);
            if (r.applesInRemain == 0) {
                continue;
            } else if (r.applesInRemoved == 0) {
                continue;
            } else { // (r.applesInRemoved > 0) {
                //System.out.println("Cut H at row: " + cutRow);
                backtrace(r.remain, cutNumber + 1);
            }
        }
    }

    private void print(String[] pizza) {
        System.out.println("======");
        for (String row : pizza) {
            System.out.println(row);
        }
        System.out.println("======");
    }

    private boolean hasApple(String[] pizza) {
        for (String row : pizza) {
            if (row.contains("A")) {
                return true;
            }
        }
        return false;
    }

    // cut vertically
    // cut between cutColumn and cutColumn + 1
    private CutResult cutV(String[] pizza, int cutColumn) {
        int width = pizza[0].length();
        int height = pizza.length;
        if (cutColumn < 0) {
            throw new RuntimeException("Cut column " + cutColumn + " <0, invalid. width: " + width );
        }
        if (cutColumn >= width - 1) {
            throw new RuntimeException("Cut column " + cutColumn + " >= width - 1, invalid. width: " + width );
        }

        String[] removed = new String[height];
        int applesInRemoved = 0;
        String[] remain = new String[height];
        int applesInRemain = 0;
        for (int i = 0; i < height; i ++) {
            removed[i] = pizza[i].substring(0, cutColumn + 1);
            // Find one applye in each row, not full apple count
            if (removed[i].contains("A")) {
                applesInRemoved++;
            }
            remain[i] = pizza[i].substring(cutColumn + 1);
            if (remain[i].contains("A")) {
                applesInRemain++;
            }
        }
        CutResult r = new CutResult();
        r.removed = removed;
        r.applesInRemoved = applesInRemoved;
        r.remain = remain;
        r.applesInRemain = applesInRemain;
        return r;
    }

    // cut horizontally
    // cut between cutRow and cutRow + 1
    private CutResult cutH(String[] pizza, int cutRow) {
        int width = pizza[0].length();
        int height = pizza.length;
        //System.out.println("Before cutH. cutRow: " + cutRow + " width " + width +
        // " height" + height + " pizza: ");
        //print(pizza);
        if (cutRow < 0) {
            throw new RuntimeException("Cut row " + cutRow + " <0, invalid. height: " + height );
        }
        if (cutRow >= height - 1) {
            throw new RuntimeException("Cut row " + cutRow + " >= height - 1, invalid. height: " + height );
        }

        String[] removed = new String[cutRow + 1];
        int applesInRemoved = 0;
        String[] remain = new String[height - cutRow - 1];
        int applesInRemain = 0;
        for (int i = 0; i < cutRow + 1; i ++) {
            removed[i] = pizza[i];
            if (removed[i].contains("A")) {
                applesInRemoved++;
            }

        }
        for (int i = cutRow + 1; i < height; i++) {
            // System.out.println("cutRow " + cutRow + " height " + height + " i " + i);
            remain[i - cutRow - 1] = pizza[i];
            if (remain[i - cutRow - 1].contains("A")) {
                applesInRemain++;
            }
        }

        CutResult r = new CutResult();
        r.removed = removed;
        r.applesInRemoved = applesInRemoved;
        r.remain = remain;
        r.applesInRemain = applesInRemain;

        //System.out.println("After cutH. cutRow: " + cutRow + " remain: ");
        //print(remain);
        return r;
    }
}
