/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

/**
 *
 * @author ANMEENA
 */
public class RandomizedSelect {
    
    public static int randomizedPartition(Integer[] A, int p, int r){
            int i = p-1;
            for(int j=p;j<r;j++){
                if(A[j]<A[r]){
                    i++;
                    swap(A,i,j);
                }
            }
            swap(A,i+1,r);
            return i+1;
    }
    
    
    public static int randomizedSelect(Integer[] A, int p, int r, int i){
        if(p==r){
            return A[p];
        }
        int q = randomizedPartition(A, p, r);
        int k = q-p+1;
        
        if(i==k){
            return A[q];
        }
        if(i<k){
            return randomizedSelect(A, p, q-1, i);
        }
        else{
            return randomizedSelect(A, q+1, r, i-k);
        }
    }
    
    protected static void swap(Integer[] A, int i, int j) {
        Integer tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
    
    public static void main(String[] args) {
        Integer[] k = {4, 11,0, 99, 3, 2, 16, 9, 10, 14, 8, 7};
        System.out.println(randomizedSelect(k,0,k.length-1,2));
    }
}
