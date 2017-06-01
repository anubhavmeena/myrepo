/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author ANMEENA
 */
public class SortEvenAscendingOddDescending {
    public static void main(String[] args) {
        Integer arr [] = {0, 4, 5, 3, 7, 2, 1, -5, -2};
        Arrays.sort(arr, new NumberComparator());
        for(Integer i : arr){
            System.err.print(i+" ");
        }
    }
}

class NumberComparator implements Comparator{

    @Override
    public int compare(Object o1, Object o2) {
        if(o1 instanceof Integer && o2 instanceof Integer){
            Integer a = (Integer) o1;
            Integer b = (Integer) o2;
            if(a%2==0){
                if(b%2==0){
                    if(a<b){
                        return -1;
                    }
                    else{
                        return 1;
                    }
                }
                else{
                    return 1;
                }
            }
            else{
                if(b%2==1){
                    if(a<b){
                        return 1;
                    }
                    else{
                        return -1;
                    }
                }
                else{
                    return -1;
                }
            }
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}