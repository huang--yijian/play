package org.hyj.algorithm.bplustree;


import java.util.ArrayList;
import java.util.List;

public class Node<K extends Comparable> {
    public boolean isLeaf = true;
    public BPlusTree tree;
    public Node parent;
    public List<Entry<K>> entries = new ArrayList<Entry<K>>(Utils.halfAndCell(tree.N_MAX_BRANCHES));

//    10, 20, 30, 40, 50
//
//    <20,30> 21, middle is 25
//    <21, index for 20, index for 20>, should return index for 30

    public Node(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public Entry<K> searchForEntry(K key, int start, int end) {
        if (entries.isEmpty()) {
            return null;
        }

        if (end == start) {
            int result = key.compareTo(start);
            // We reach the left end of the node
            if (result <= 0) {
                return entries.get(start);
            }
            else {
                // We reach the right end of the node
                // a new entry should be added into node.entries
                if (start + 1 >= entries.size())
                    return null;
                return entries.get(end + 1);
            }
        }

        int middleIndex = (start + end) / 2;
        Entry<K> middle = entries.get(middleIndex);
        int result = key.compareTo(middle.key);
        if (result == 0) {
            return middle;
        } else if (result > 0) {
            return searchForEntry(key, middleIndex + 1, end);
        } else {
            return searchForEntry(key, start, middleIndex);
        }
    }
}

class Entry<K extends Comparable> {
    public K key;
    public Node<K> node;

    public Entry(K k, Node n) {
        key = k;
        node = n;
    }
}
