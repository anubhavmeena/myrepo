/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

import graphs.Graph;
import graphs.Vertex;

/**
 *
 * @author ANMEENA
 */
public class MaxSumWithNo2ElementsAdj {
    public static void main(String[] args) {
        int arr[] = {1, 20, 3};
        
        int inc = arr[0];
        int exc = 0;
        int costa [] = new int[arr.length];
        
        for(int i = 1; i<costa.length; i++){
            int incp = inc;
            int excp = exc;
            exc = Math.max(excp, incp);
            inc = excp + arr[i];
            costa[i] = Math.max(inc, exc);
        }
        System.out.println(costa[costa.length-1]);
        
    }
}
