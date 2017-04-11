/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

import java.util.Scanner;

/**
 *
 * @author ANMEENA
 */
public class SherlockandCost {

    static void print(int [][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int solve(int[] b) {
        int[][] DP = new int[b.length][2];

        DP[0][0] = 0;
        DP[0][1] = 0;
        for (int i = 0; i < b.length - 2; i++) {
            DP[i + 1][0] = Math.max(DP[i][0], DP[i][1] + Math.abs(b[i] - 1));
            DP[i + 1][1] = Math.max(DP[i][0] + Math.abs(b[i + 1] - 1), DP[i][1] + Math.abs(b[i] - b[i + 1]));
        }

        return Math.max(DP[b.length - 2][0], DP[b.length - 2][1]);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int nTestCases = Integer.parseInt(in.next());

        while (nTestCases-- > 0) {
            int bLength = Integer.parseInt(in.next());
            int[] b = new int[bLength + 1];
            int[] a = new int[bLength + 1];
            int i = 0;
            while (bLength-- > 0) {
                b[i++] = Integer.parseInt(in.next());
            }
            System.out.println(solve(b));
        }
    }
}
