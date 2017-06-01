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
public class FindNonDuplicateNUmber {
    public static void main(String[] args) {
        int[] arr = {2,3,3,2,5,7,6,5,6};
        int r = 0;
        for(int i=0;i<arr.length;i++){
            r ^= arr[i];
        }
        System.out.println(r);
    }
}
