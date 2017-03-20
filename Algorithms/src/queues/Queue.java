/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queues;

import java.util.Arrays;

/**
 *
 * @author ANMEENA
 */
public class Queue<K> {
    
    K[] Q = (K[]) new Object[10];
    
    int head;
    int tail;

    public Queue() {
        head = 0;
        tail = head;
    }
    
    public boolean isEmpty(){
        if(head==tail){
            return true;
        }
        return false;
    }
    
    public boolean isFull(){
        if(tail+1 == head){
            return true;
        }
        return false;
    }
    
    public void enqueue(K key){
        Q[tail] = key;
        tail++;
        if(tail==Q.length){
            tail = 0;
        }
    }
    
    public K dequeue(){
        head++;
        if(head==Q.length){
            head = 0;
        }
        return Q[head-1];
    }
    
    protected void print() {
        System.out.println(Arrays.toString(Q));
    }
    
    public static void main(String[] args) {
        Queue<Integer> s = new Queue<>();
        
             s.enqueue(5);
             s.enqueue(10);
             s.enqueue(1);
             s.enqueue(99);
             s.enqueue(3);
             s.enqueue(9);
             s.print();
             s.enqueue(12);
             s.print();
             s.enqueue(15);
             s.print();
             s.enqueue(23);
             s.print();
             s.enqueue(33);
             s.print();
             s.enqueue(43);
             s.print();
             s.enqueue(53);
             s.print();
             
             System.out.println(s.dequeue());
             s.print();
             System.out.println(s.dequeue());
             s.print();
             System.out.println(s.dequeue());
             s.print();
    }
    
    
}
