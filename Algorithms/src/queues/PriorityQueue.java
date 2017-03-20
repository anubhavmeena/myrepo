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
public class PriorityQueue {
    Integer[] A = new Integer[15];
    int qSize;
    
    public PriorityQueue() {
        qSize= 0;
    }
    
    public PriorityQueue(Integer[] K) {
        qSize= K.length;
        for(int i=0; i<K.length; i++){
            A[i] = K[i];
        }
        buildMaxHeap(A);
    }
    
    public boolean isEmpty(){
        if(qSize==0){
            return true;
        }
        return false;
    }
    
    public void insert(int key){
        A[qSize] = key;
        qSize++;
        //print(A);
        int i = qSize-1;
        while(i>0 && A[parent(i)] < A[i]){
            swap(A,i,parent(i));
            i=parent(i);
        }
    }
    
    public int removeMax(){
        if(!isEmpty()){
            int val = A[0];
            A[0] = A[qSize-1];
            qSize--;
            //print(A);
            heapify(0);
            return val;
        }
        return -1;
    }
    
    protected static int parent(int i){
        return (i+1)/2 - 1;
    }
    
    protected static int left(int i){
        return (i+1)*2 - 1;
    }
    
    protected static int right(int i){
        return (i+1)*2;
    }
    
    protected void swap(Integer[] A, int i, int j){
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
    
    protected void heapify(int i){
        
        int l = left(i);
        int r = right(i);
        int largest = i;
        
        if(l < qSize && A[l] > A[i]){
            largest = l;
        }
        if(r < qSize && A[r] > A[largest]){
            largest = r;
        }
        
        if(largest!=i){
            swap(A,i,largest);
            heapify(largest);
        }
        
    }
    
    protected void buildMaxHeap(Integer[] A){
        //qSize = A.length;
        for(int i=qSize/2-1; i>=0 ; i--){
            //System.out.println("i="+i+"A[i]="+A[i]);
            heapify(i);
            //print(A);
        }
    }
    
    protected void print(Integer[] A){
        System.out.println(Arrays.toString(A));
    }
    
    public static void main(String[] args) {
        
        Integer[] k = {4,1,3,2,16,9,10,14,8,7};
        
        PriorityQueue pq = new PriorityQueue();
        
        System.out.println(pq.removeMax());
        pq.print(pq.A);
        pq.insert(99);
        pq.insert(55);
        pq.insert(101);
        pq.insert(9);
        pq.insert(1);
        pq.insert(10);
        pq.print(pq.A);
        System.out.println(pq.removeMax());
        System.out.println(pq.removeMax());
        System.out.println(pq.removeMax());
        
    }
    
}
