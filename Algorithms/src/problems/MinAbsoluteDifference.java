/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author ANMEENA
 */
public class MinAbsoluteDifference {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        for (int a_i = 0; a_i < n; a_i++) {
            a[a_i] = in.nextInt();
        }
        Arrays.sort(a);
        int min = Integer.MAX_VALUE;
        for (int i=0;i<n-1;i++){
            if (Math.abs(a[i+1]-a[i])<min){
                min = Math.abs(a[i+1]-a[i]);
            }
        }
        System.out.println(min);
    }
}
