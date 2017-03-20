/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 *
 * @author ANMEENA
 * @param <T>
 */
public class BST<T extends Comparable<T>> {
    Node<T> root;
    
    void insert(T key){
        Node n = new Node(key);
        if(root==null){
            root = n;
            return;
        }
        Node t = root;
        Node y = root;
        while(t!=null){
            y = t;
            if(n.value.compareTo(t.value) < 0){
                t = t.left;
            }
            else{
                t = t.right;
            }
        }
        n.parent = y;
        if(n.value.compareTo(y.value)<0){
            y.left = n;
        }
        else{
            y.right = n;
        }
    }
    
    public Node<T> min(Node n){
        while(n.left!=null){
            n = n.left;
        }
        return n;
    }
    
    public Node<T> treeMax(){
        return max(this.root);
    }
    
    public Node<T> treeMin(){
        return min(this.root);
    }

    
    public Node<T> max(Node n){
        while(n.right!=null){
            n = n.right;
        }
        return n;
    }
    
    public Node<T> search(T key){
        Node t = root;
        while(t!=null){
            if(t.value.compareTo(key)==0){
                return t;
            }
            if(t.value.compareTo(key)< 0){
                t = t.right;
            }
            else{
                t = t.left;
            }
        }
        return null;
    }
    
    public Node<T> succesor(T key){
        Node n = search(key);
        if(n.right!=null){
            return min(n.right);
        }
        Node y = n.parent;
        while(y!=null && n == y.right){
            n = y;
            y = n.parent;
        }
        return y;
    }
    
    public Node<T> predeccesor(T key){
        Node n = search(key);
        if(n.left!=null){
            return max(n.left);
        }
        Node y = n.parent;
        while(y!=null && n == y.left){
            n = y;
            y = n.parent;
        }
        return y;
    }
    
    void printIn(Node<T> n){
        if(n!=null){
            printIn(n.left);
            System.out.println(n.value);
            printIn(n.right);
        }
    }
    
    void printIn(){
        printIn(this.root);
    }
    
    void transplant(Node u, Node v){
        if(u.parent==null){
            root = v;
        }
        if(u.parent.left == u){
            u.parent.left = v;
        }
        else{
            u.parent.right = v;
        }
        if(v!=null){
            v.parent = u.parent;
        }
    }
    
    void delete(T key){
        Node n = search(key);
        if(n!=null){
            if(n.left==null){
                transplant(n, n.right);
            }
            else if(n.right==null){
                transplant(n, n.left);
            }
            else{
                Node y = min(n.right);
                if(y.parent!=n){
                    transplant(y, y.right);
                    y.right = n.right;
                    y.right.parent = y;
                }
                transplant(n, y);
                y.left = n.left;
                y.left.parent = y;
            }
        }
        
    }
    
    public static void main(String[] args) {
        Integer[] array = {12,5,18,2,9,15,19,13,17};
        
        BST<Integer> bst = new BST();
        
        for(int i=0; i<20; i++){
            //bst.insert(array[i]);
            bst.insert((int)(Math.random()*100));
        }
        
        bst.printIn();
//        System.out.println("Max:"+bst.treeMax().value);
//        System.out.println("Min:"+bst.treeMin().value);
//        System.out.println("Succesor of "+ 9 +" is:"+bst.succesor(9).value);
//        System.out.println("Predeccesor of " +6 +" is:"+bst.predeccesor(6).value);
       // bst.insert(100);
       // bst.delete(21);
//        System.out.println("----------\nMax:"+bst.treeMax().value);
//        System.out.println("Min:"+bst.treeMin().value);
//        System.out.println("Succesor of "+ 9 +" is:"+bst.succesor(9).value);
//        System.out.println("Predeccesor of " +6 +" is:"+bst.predeccesor(6).value);
        
        
        BTreePrinter.printNode(bst.root);
    }
}

class Node<T extends Comparable<T>> {
    Node<T> parent;
    Node<T> left;
    Node<T> right;
    T value;
    
    public Node(T key){
        this.value = key;
        this.left = null;
        this.right = null;
        this.parent = null;
    }
}

class BTreePrinter {

    public static <T extends Comparable<T>> void printNode(Node<T> root) {
        int maxLevel = BTreePrinter.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static <T extends Comparable<T>> void printNodeInternal(List<Node<T>> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        BTreePrinter.printWhitespaces(firstSpaces);

        List<Node<T>> newNodes = new ArrayList<Node<T>>();
        for (Node<T> node : nodes) {
            if (node != null) {
                System.out.print(node.value);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            BTreePrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                BTreePrinter.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null)
                    System.out.print("/");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null)
                    System.out.print("\\");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T extends Comparable<T>> int maxLevel(Node<T> node) {
        if (node == null)
            return 0;

        return Math.max(BTreePrinter.maxLevel(node.left), BTreePrinter.maxLevel(node.right)) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}