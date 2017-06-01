/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

import trees.BST;
import trees.Node;

/**
 *
 * @author ANMEENA
 */
public class BSTFindNodesInRange {

    public static int getRangeNodes(Node root, Range range) {
        if (root == null) {
            return 0;
        }
        if (root.value.compareTo(range.min) < 0) {
            return getRangeNodes(root.right, range);
        } else if (root.value.compareTo(range.max) > 0) {
            return getRangeNodes(root.left, range);
        } else {
            System.out.println(root.value);
            return getRangeNodes(root.left, range)
                    + getRangeNodes(root.right, range) + 1;
        }
    }

    public static void main(String[] args) {
        BST<Integer> bst = new BST();
        bst.insert(9);
        bst.insert(7);
        bst.insert(8);
        bst.insert(1);
        bst.insert(5);
        bst.insert(20);
        bst.insert(87);
        bst.insert(33);
        bst.prettyPrint();
        System.out.println(getRangeNodes(bst.root, new Range(0, 10)));
    }
}

class Range {

    Comparable min;
    Comparable max;

    Range(Comparable min, Comparable max) {
        this.min = min;
        this.max = max;
    }
}
