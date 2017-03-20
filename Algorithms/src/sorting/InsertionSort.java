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
public class InsertionSort {

    private static void sort(Integer[] a){
        for(int i=1;i<a.length;i++){
            int key = a[i];
            for(int j=0;j<i;j++){
                if(key < a[j]){
                    insert(a,j,i);
                    break;
                }
            }
        }
    }
    
    private static void insert(Integer[] a, int x, int y){
        int prev = a[y];
        for(int i=x; i<=y; i++){
            int tmp = a[i];
            a[i] = prev;
            prev = tmp;
        }
    }
    
    private static void print (Integer[] a){
        System.out.println(Arrays.toString(a));
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Integer[] a = {5,2,4,6,1,3};
        print(a);
        sort(a);
        print(a);
    }
    
}
