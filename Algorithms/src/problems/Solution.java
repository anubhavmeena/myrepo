/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

import java.math.BigInteger;
import java.util.Scanner;

/**
 *
 * @author ANMEENA
 */
public class Solution {
    
    static int t1;
    static int t2;
    static BigInteger[] FIB;
    
    static BigInteger Fib(int i){
        BigInteger fib;
        
        if(FIB[i]!=null){
            return FIB[i];
        }
        else if(i<1){
            fib = new BigInteger("0");
        }
        else if(i==1){
            fib = new BigInteger(String.valueOf(t1));
        }
        else if(i==2){
            fib = new BigInteger(String.valueOf(t2));
        }
        else{
             fib = Fib(i-2).add(Fib(i-1).pow(2));
             FIB[i] = fib;
        }
        return fib;
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);

        t1 = Integer.parseInt(in.next());
        t2 = Integer.parseInt(in.next());
        int n = Integer.parseInt(in.next());
        
        FIB = new BigInteger[n+1];
        
        System.out.println(Fib(n));
    }
}