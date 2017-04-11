/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author ANMEENA
 */
public class PermutingTwoArrays {

    public static int binarySearch(int[] inputArr, int key) {
		
        int start = 0;
        int end = inputArr.length - 1;
        int mid = 0;
        while (start <= end) {
            mid = (start + end) / 2;
            if (key == inputArr[mid]) {
                return mid;
            }
            if (key < inputArr[mid]) {
            	end = mid - 1;
            } else {
            	start = mid + 1;
            }
        }
        if(inputArr[mid] < key){
            return mid + 1;
        }
        else{
            return mid;
        }
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int Q = in.nextInt();
        
        for (int q = 0; q < Q; q++) {
            int N = in.nextInt();
            int K = in.nextInt();
            
            int[] a = new int[N];
            int[] b = new int[N];
            int[] c = new int[N];

            for (int i = 0; i < N; i++) {
                a[i] = in.nextInt();
            }
            for (int i = 0; i < N; i++) {
                b[i] = in.nextInt();
            }

            Arrays.sort(b);

            Map<Integer,Integer> map = new HashMap();

            for (int i = 0, j = 0; i < b.length; ++i) {
                if(map.containsKey(b[i])){
                    map.put(b[i],map.get(b[i])+1);
                }
                else{
                    map.put(b[i], 1);
                }
            }
            boolean flag = true;

            for (int i = 0; i < N && flag; i++) {
                int P = K - a[i];
                if (P > 0) {
                    int x = binarySearch(b,P);
                    if ( x < b.length) {
                        while (x < b.length) {
                            if (map.get(b[x]) > 0) {
                                map.put(b[x],map.get(b[x])-1);
                                c[i] = b[x];
                                break;
                            } else {
                                x++;
                            }
                        }
                        if (x == b.length) {
                            flag = false;
                        }
                    } else {
                        flag = false;
                    }
                }
            }
            System.out.println(flag ? "YES" : "NO");
        }
    }
}
