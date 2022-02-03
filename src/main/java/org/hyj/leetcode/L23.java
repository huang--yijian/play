package org.hyj.leetcode;

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class L23 {
    private ListNode[] heads;

    public ListNode mergeKLists(ListNode[] lists) {
        heads = lists;

        ListNode result = null;
        ListNode tail = null;
        ListNode chosen = null;
        while ((chosen = popNextMinHead())!=null) {
            if (null == result) {
                result = chosen;
                tail = chosen;
            } else {
                tail.next = chosen;
                tail = tail.next;
            }
        }

        return result;
    }

    public ListNode popNextMinHead() {
        ListNode minHead = null;
        int chosen = -1;
        for (int i = 0; i < heads.length; i++)
        {
            ListNode head = heads[i];
            System.out.println("head is " + head);
            if (head == null) {
                continue;
            }
            if (minHead == null) {
                minHead = head;
                chosen = i;
            }
            else if (minHead.val > head.val) {
                minHead = head;
                chosen = i;
            }
        }
        if (-1 == chosen) {
            return null;
        }
        ListNode chosenHead = heads[chosen];
        heads[chosen] = heads[chosen].next;
        return chosenHead;
    }
}
