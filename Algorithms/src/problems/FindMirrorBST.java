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
 * @author anubhav
 */
public class FindMirrorBST {

    public static void findNuminRange(Node root, Range range) {
        if (root == null) {
            return;
        }
        if (root.value.compareTo(range.min) < 0) {
            findNuminRange(root.right, range);
        } else if (root.value.compareTo(range.max) > 0) {
            findNuminRange(root.left, range);
        } else {
            findNuminRange(root.right, range);
            findNuminRange(root.left, range);
        }
    }

    public static void mirror(Node root) {
        if (root == null) {
            return;
        } else {
            mirror(root.left);
            mirror(root.right);
        }
        Node tmp = root.left;
        root.left = root.right;
        root.right = tmp;
    }

    public static void main(String[] args) {
        BST bst = new BST();
        bst.insert(5).insert(3).insert(7).insert(1).insert(4).insert(6);
        bst.prettyPrint();
        mirror(bst.root);
        bst.prettyPrint();
        System.out.println(Integer.valueOf('s'));

    }
}

class Range {

    Comparable min;
    Comparable max;

    Range(int min, int max) {
        this.min = min;
        this.max = max;
    }
}
