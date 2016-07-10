package org.hyj.algorithm.bplustree;

import java.util.List;

public class BPlusTree<K extends Comparable> {
    // Defined according to wikipedia https://en.wikipedia.org/wiki/B%2B_tree

    // The branching factor "b" in wikipedia.
    public static int N_MAX_BRANCHES = 10;

    private Node<K> rootNode = new Node<K>(true);

    public Node<K> search(Node node, K key) {
        if (node.isLeaf) {
            // TODO need assert key lies in the key range of this returned node.
            return node;
        }
        Entry<K> entry = node.searchForEntry(key, 0, node.entries.size());

        // If the given key is larger than all entries of node.
        if (null == entry) {
            return null;
        } else {
            return search(entry.node, key);
        }
    }

    public void insert(Node node, K key) {
        if (rootNode.isLeaf) {
            // TODO
            return;
        }

        Entry<K> entry = node.searchForEntry(key, 0, node.entries.size());
        if (null == entry) {

        } else {
            insert(entry.node, key);
        }
    }

    // Add key into node, and split node if it is full.
    // child will be added into node.entries.
    private void add(Node node, K key, Node child) {
        // If node is leaf and is not full
        if (node.isLeaf && node.entries.size() < N_MAX_BRANCHES - 1) {
            Utils.insert(node.entries, key, null);
            return;
        }
        // If node is leaf and is full, split it
        else if (node.isLeaf) {
            Utils.insert(node.entries, key, null);
            Node newSibling = Utils.split(node);
            if (newSibling.parent!=null) {
                add(newSibling.parent, ((List<Entry<K>>)(newSibling.entries)).get(0).key, newSibling);
            } else {
                // TODO
            }
        }
        // If node is not leaf, and is not full, split it as well
        else if (!node.isLeaf && node.entries.size() < N_MAX_BRANCHES - 1) {
            Utils.insert(node.entries, key, child);
        }
        else {
            Utils.insert(node.entries, key, child);
            Node newSibling = Utils.split(node);
            if (newSibling.parent!=null) {
                add(newSibling.parent, ((List<Entry<K>>)(newSibling.entries)).get(0).key, newSibling);
            } else {
                // TODO
            }
        }

    }
}
