/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trees;

/**
 *
 * @author anubhav
 */
public class RedBlackTree<T extends Comparable<T>> {

    RBNode root;

    void leftRotate(RBNode x) {
        RBNode y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    void rightRotate(RBNode x) {
        RBNode y = x.left;
        x.left = y.right;
        if (y.right != null) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.right = x;
        x.parent = y;
    }

    void insert(T key) {
        RBNode n = new RBNode(key);
        if (root == null) {
            root = n;
            return;
        }
        RBNode t = root;
        RBNode y = root;
        while (t != null) {
            y = t;
            if (n.value.compareTo(t.value) < 0) {
                t = t.left;
            } else {
                t = t.right;
            }
        }
        n.parent = y;
        if (n.value.compareTo(y.value) < 0) {
            y.left = n;
        } else {
            y.right = n;
        }
        n.color = RBNode.RED;
        rbInsertFixUp(n);
    }
    
    void rbInsertFixUp(RBNode n){
    }

}

class RBNode<T extends Comparable<T>> {

    public static int BLACK = 0;
    public static int RED = 1;

    int color;

    RBNode<T> parent;
    RBNode<T> left;
    RBNode<T> right;
    T value;

    public RBNode(T key) {
        this.value = key;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

}
