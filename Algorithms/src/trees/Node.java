/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trees;

/**
 *
 * @author ANMEENA
 */
public class Node<T extends Comparable<T>> {
    public Node<T> parent;
    public Node<T> left;
    public Node<T> right;
    public T value;
    
    public Node(T key){
        this.value = key;
        this.left = null;
        this.right = null;
        this.parent = null;
    }
}
