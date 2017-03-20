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
 * @param <K>
 */
public class QuickSort{

    public static int partition(Comparable[] A, int p, int r) {
        int i = p-1;
        for(int j=p; j<r; j++){
            if(A[j].compareTo(A[r])<0) {
                i++;
                swap(A,i,j);
            }
        }
        swap(A,i+1,r);
        return i+1;
    }

    public static void quicksort(Comparable[] A, int p, int r) {
        if (p < r) {
            int q = partition(A, p, r);
            quicksort(A, p, q - 1);
            quicksort(A, q + 1, r);
        }
    }

    public static void sort(Comparable[] A) {
        quicksort(A, 0, A.length - 1);
    }
    
    protected static void swap(Comparable[] A, int i, int j){
        Comparable tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    protected static void print(Comparable[] A) {
        System.out.println(Arrays.toString(A));
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Integer[] a = {13, 19, 9, 5, 12, 8, 7, 4, 21, 2, 6, 11};
        Character[] c = {'a', 'z', 'b', 'h', 'j', 'x'};
        String[] s = {"apple","zebra","cat", "ball","owl","goat","donkey","pig","tiger"};
        
        print(s);
        sort(s);
        print(s);
    }
}
