/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorting;

import java.util.Arrays;

/**
 *
 * @author ANMEENA
 */
public class HeapSort {
    
    static int heapsize;
    
    protected static int parent(int i){
        return (i+1)/2;
    }
    
    protected static int left(int i){
        return (i+1)*2 - 1;
    }
    
    protected static int right(int i){
        return (i+1)*2;
    }
    
    protected static void swap(Integer[] A, int i, int j){
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
    
    protected static void heapify(Integer[] A, int i){
        
        int l = left(i);
        int r = right(i);
        int largest = i;
        
        if(l < heapsize && A[l] > A[i]){
            largest = l;
        }
        if(r < heapsize && A[r] > A[largest]){
            largest = r;
        }
        
        if(largest!=i){
            swap(A,i,largest);
            heapify(A,largest);
        }
        
    }
    
    protected static void buildMaxHeap(Integer[] A){
        heapsize = A.length;
        for(int i=A.length/2-1; i>=0 ; i--){
            System.out.println("i="+i+"A[i]="+A[i]);
            heapify(A,i);
            print(A);
        }
    }
    
    public static void heapsort(Integer[] A){
        buildMaxHeap(A);
        for(int i = A.length-1; i>0; i--){
            swap(A,0,i);
            heapsize = heapsize - 1;
            heapify(A,0);
        }
    }
    
    protected static void print (Integer[] A){
        System.out.println(Arrays.toString(A));
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Integer[] a = {4,1,3,2,16,9,10,14,8,7};
        print(a);
        heapsort(a);
        print(a);
    }
}
