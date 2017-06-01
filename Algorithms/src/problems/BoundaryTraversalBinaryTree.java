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
public class BoundaryTraversalBinaryTree {
    public static int printBoundary(Node root){
        Node n = root;
        while(n!=null){
            System.out.println(n);
            n = n.left;
        }
    }
    
    public static void main(String[] args) {
        BST bst = new BST();
        bst.insert(20);
        bst.insert(8);
        bst.insert(4);
        bst.insert(12);
        bst.insert(10);
        bst.insert(14);
        bst.insert(22);
        bst.insert(25);
        bst.prettyPrint();
        printBoundary(bst.root,bst.root,0);
        
    }
}
