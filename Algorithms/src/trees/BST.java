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
    public Node<T> root;
    
    public BST insert(T key){
        Node n = new Node(key);
        if(root==null){
            root = n;
            return this;
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
        return this;
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
    
    public void printIn(Node<T> n){
        if(n!=null){
            printIn(n.left);
            System.out.println(n.value);
            printIn(n.right);
        }
    }
    
    public void prettyPrint(){
        BTreePrinter.printNode(this.root);
    }
    
    public void printIn(){
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
