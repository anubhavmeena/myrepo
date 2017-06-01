/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

/**
 *
 * @author anubhav
 */
public class ShiftZeros {
    
    public static void swap(int A[], int i, int j){
        int T = A[i];
        A[i] = A[j];
        A[j] = T;
    }
    
    public static void shiftZeros(int A[]){
        int p=-1;
        for(int i=0; i < A.length; i++){
            if(A[i]!=0){
                p++;
                swap(A,i,p);
            }
        }
    }
    
    public static void main(String[] args) {
        int A[] = {5,3,0,2,1,6,0,0,4,9};
        shiftZeros(A);
        for(Integer a : A){
            System.out.print(a+", ");
        }
    }
}
