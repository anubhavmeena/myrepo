/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

import java.util.logging.Level;
import java.util.logging.Logger;
import queues.Stack;

/**
 *
 * @author ANMEENA
 */
public class QueueUsing2Stacks<K> {
    Stack<K> s1;
    Stack<K> s2;
    
    public QueueUsing2Stacks(int size){
        s1 = new Stack<>(size);
        s2 = new Stack<>(size);
    }
    
    public void enqueue(K key){
        try {
            s1.push(key);
        } catch (Exception ex) {
            Logger.getLogger(QueueUsing2Stacks.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public K dequeue(){
        while(!s1.isEmpty()){
            try {
                s2.push(s1.pop());
            } catch (Exception ex) {
                Logger.getLogger(QueueUsing2Stacks.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            K top = s2.pop();
            while(!s2.isEmpty()){
                s1.push(s2.pop());
            }
            return top;
        } catch (Exception ex) {
            Logger.getLogger(QueueUsing2Stacks.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean isEmpty(){
        if(s1.isEmpty()){
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) {
        Integer[] k = {4, 11,0, 99, 3, 2, 16, 9, 10, 14, 8, 7,1,14,88};
        QueueUsing2Stacks<Integer> q = new QueueUsing2Stacks<>(k.length);
        for(int i : k){
            q.enqueue(i);
        }
        
        while(!q.isEmpty()){
            System.out.println(q.dequeue());
        }
        
    }
}
