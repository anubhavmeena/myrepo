/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

/**
 *
 * @author ANMEENA
 */
public class LongestIncreasingSubSeq {

    public static int LISS(int[] arr) {
        int n = arr.length;
        int lis[] = new int[n];
        int i, j, max = 0;

        /* Initialize LIS values for all indexes */
        for (i = 0; i < n; i++) {
            lis[i] = 1;
        }

        /* Compute optimized LIS values in bottom up manner */
        for (i = 1; i < n; i++) {
            for (j = 0; j < i; j++) {
                if (arr[i] > arr[j] && lis[i] < lis[j] + 1) {
                    lis[i] = lis[j] + 1;
                }
            }
        }

        /* Pick maximum of all LIS values */
        for (i = 0; i < n; i++) {
            if (max < lis[i]) {
                max = lis[i];
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int arr[] = {50, 3, 10, 7, 40, 80};
        System.out.println(LISS(arr));
    }
}
