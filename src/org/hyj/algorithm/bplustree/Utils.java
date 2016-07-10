package org.hyj.algorithm.bplustree;

import java.util.List;

/**
 * Created by hyj on 16-7-10.
 */
public class Utils {
    public static int halfAndCell(int value) {
        return (value % 2 == 0) ? value / 2 : (value + 1) / 2;
    }

    public static Entry insert(List<Entry> entries, Comparable key, Node childNode) {
        Entry entry = new Entry(key, childNode);
        for (int i = 0; i < entries.size(); i++) {
            if (key.compareTo(entries.get(i)) <= 0) {
                entries.add(i, entry);
                return entry;
            }
        }
        entries.add(entries.size() - 1, entry);
        return entry;
    }

    public static Node split(Node node) {
        // If node is full, split it (and split its parents recursively)
        Node newSibling = new Node(node.isLeaf);
        newSibling.parent = node.parent;
        int middleIndex = node.entries.size() / 2;
        int size = node.entries.size();
        for (int i = middleIndex; i < size; i++) {
            Entry movedEntry = (Entry)node.entries.remove(middleIndex);
            newSibling.entries.add(movedEntry);
        }
        return newSibling;
    }
}
