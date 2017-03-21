/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trees;

/**
 *
 * @author anubhav
 * @param <T>
 */
public class Node<T extends Comparable<T>> {
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
