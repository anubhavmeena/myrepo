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
public class MergeSort {
    
    private static Integer[] mergesort(Integer[] a){
        int n = a.length;
        System.out.println(n);
        print(a);
        if(n<=1){
            return a;
        }
        int k = n/2;
       // System.out.println(k);
        Integer[] x = mergesort(Arrays.copyOfRange(a, 0, k));
        Integer[] y = mergesort(Arrays.copyOfRange(a,k, n));
        Integer[] z = new Integer[x.length+y.length];
        int i=0;
        int j=0;
        int q = 0;
        System.out.print("x=");print(x);
        System.out.print("y=");print(y);
        while(i<x.length && j<y.length){
            if(x[i] <= y[j]){
                z[q] = x[i];
                i++;
                q++;
            }
            else{
                z[q] = y[j];
                j++;
                q++;
            }
        }
        
        if(i==x.length){
            while(j<y.length){
                z[q] = y[j];
                j++;
                q++;
            }
        }
        else{
             while(i<x.length){
                z[q] = x[i];
                i++;
                q++;
            }
        }
        
        System.out.print("z=");print(z);
        return z;
        
    }
    
    private static void print (Integer[] a){
        System.out.println(Arrays.toString(a));
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Integer[] a = {3,41,52,26,38,57,9,49};
        print(a);
        print(mergesort(a));
    }
}
