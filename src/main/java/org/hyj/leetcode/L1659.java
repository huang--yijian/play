package org.hyj.leetcode;

// https://leetcode-cn.com/problems/maximize-grid-happiness/
public class L1659 {
    // 0: non
    // 1: place intro
    // 2: place extro

    private int M;
    private int N;
    private int CURRENT_HAPPINESS = 0;
    private int MAX_HAPPINESS = 0;

    public static void main(String[] args) {

        System.out.println("test " + new L1659().getMaxGridHappiness(2,3,1,2));
    }

    public int getMaxGridHappiness(int m, int n, int introvertsCount, int extrovertsCount) {
        M = m;
        N = n;
        int[] board = new int[M*N];
        // offset: non-empty elements so far
        next(board, -1, introvertsCount, extrovertsCount);

        //System.out.println("hello world");
        return MAX_HAPPINESS;
    }

    private void next(int[] board, int offset, int introvertsCount, int extrovertsCount) {
        //System.out.println("calling next. board: " + Arrays.toString(board) +  " offset: " + offset + " intro count " + introvertsCount + " extro count " + extrovertsCount + " current: " + CURRENT_HAPPINESS);
        int newOffset = offset +1;
        if (newOffset >= M * N) {
            return;
        }
        int oldHappiness = CURRENT_HAPPINESS;
        // Place extro at newOffset
        if (introvertsCount > 0) {
            board[newOffset] = 1;
            CURRENT_HAPPINESS += 120;
            int leftValue = leftValue(board, newOffset);
            //System.out.println("left value is: " + leftValue + " current: " + CURRENT_HAPPINESS + " newOffset: " + newOffset);
            if (leftValue > 0) {
                CURRENT_HAPPINESS -= 30;
            }
            if (leftValue == 1) {
                CURRENT_HAPPINESS -= 30;
                //System.out.println("left value is: " + leftValue + " minus 30. current: " + CURRENT_HAPPINESS);
            } else if (leftValue == 2) {
                CURRENT_HAPPINESS += 20;
                //System.out.println("left value is: " + leftValue + " add 20. current: " + CURRENT_HAPPINESS);
            }

            int upperValue = upperValue(board, newOffset);
            if (upperValue > 0) {
                CURRENT_HAPPINESS -= 30;
            }
            if (upperValue == 1) {
                CURRENT_HAPPINESS -= 30;
            } else if (upperValue == 2) {
                CURRENT_HAPPINESS += 20;
            }
            printHappiness(board);
            //System.out.println("next1. newOffset: " + newOffset);
            next(board, newOffset, introvertsCount - 1, extrovertsCount);
        }
        CURRENT_HAPPINESS = oldHappiness;
        board[newOffset] = 0;
        // Place extro at newOffset
        if (extrovertsCount > 0) {
            board[newOffset] = 2;
            CURRENT_HAPPINESS += 40;

            int leftValue = leftValue(board, newOffset);
            //System.out.println("left value is: " + leftValue + " current: " + CURRENT_HAPPINESS + " newOffset: " + newOffset);
            if (leftValue > 0) {
                CURRENT_HAPPINESS += 20;
            }
            if (leftValue == 1) {
                CURRENT_HAPPINESS -= 30;
            } else if (leftValue == 2) {
                CURRENT_HAPPINESS += 20;
            }
            //System.out.println("current in place extro, after check left: " + CURRENT_HAPPINESS);

            int upperValue = upperValue(board, newOffset);
            if (upperValue > 0) {
                CURRENT_HAPPINESS += 20;
                //System.out.println("Found upper value " + upperValue + " current: " + CURRENT_HAPPINESS + ". newOffset:" + newOffset + " M: " + M + " N: " + N);
            }
            if (upperValue == 1) {
                CURRENT_HAPPINESS -= 30;
            } else if (upperValue == 2) {
                CURRENT_HAPPINESS += 20;
            }
            //System.out.println("current in place extro, after check upper: " + CURRENT_HAPPINESS);
            printHappiness(board);
            next(board, newOffset, introvertsCount, extrovertsCount - 1);
        }
        CURRENT_HAPPINESS = oldHappiness;
        board[newOffset] = 0;

        next(board, newOffset , introvertsCount, extrovertsCount);
        printHappiness(board);
        CURRENT_HAPPINESS = oldHappiness;
        return;
    }

    private int leftValue(int[] board, int index) {
        if (index % N > 0) {
            return board[index-1];
        }
        return -1;
    }

    private int upperValue(int[] board, int index) {
        if (index - N >= 0) {
            return board[index - N];
        }
        return -1;
    }

    private void printHappiness(int[] board) {
        //System.out.println("Happiness: " + CURRENT_HAPPINESS + " board: " + Arrays.toString(board));
        if (CURRENT_HAPPINESS > MAX_HAPPINESS) {
            MAX_HAPPINESS = CURRENT_HAPPINESS;
        }
    }
}
