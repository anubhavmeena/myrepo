/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queues;

/**
 *
 * @author ANMEENA
 * @param <K>
 */
public class FifoQ<K extends Comparable<K>> {
    
    PriorityQ queue;
    int priority = 999;
    
    public FifoQ(){
        queue = new PriorityQ();
    }
    
    public void insert(K key){
        MyObject<K> myObj = new MyObject<>(priority--,key);
        queue.insert(myObj);
    }
    
    public K remove(){
        MyObject<K> val = (MyObject) queue.removeMax();
        if(val!=null){
            priority++;
        }
        return (K)val.value;
    }
    
    public static void main(String[] args) {
        
        FifoQ fq = new FifoQ();
        
        fq.insert(5);
        fq.insert(9);
        fq.insert(11);
        fq.insert(54);
        fq.insert(19);
        fq.insert(35);
        fq.insert(79);
        fq.insert(25);
        fq.insert(99);
        
        System.out.println(fq.remove());
        System.out.println(fq.remove());
        
    }
}

class MyObject<K> implements Comparable<MyObject>{
    
    int priority;
    K value;

    public MyObject(int priority, K value) {
        this.priority = priority;
        this.value = value;
    }
    
    public MyObject(K value) {
        this.priority = 1;
        this.value = value;
    }

    @Override
    public int compareTo(MyObject o) {
        if(this.priority > o.priority){
            return 1;
        }
        else if(this.priority < o.priority){
            return -1;
        }
        else {
            return 0;
        }
    }

    
}
