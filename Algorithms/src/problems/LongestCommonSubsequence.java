package DP;

import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author anubhav
 */
public class LongestCommonSubsequence {

    static void printLCS(char[][] b, char[] x, int i, int j, StringBuffer sb) {
        if (i == 0 || j == 0) {
            return;
        }
        switch (b[i][j]) {
            case 'D':
                printLCS(b, x, i - 1, j - 1, sb);
                sb.append(x[i-1]);
                break;
            case 'V':
                printLCS(b, x, i - 1, j, sb);
                break;
            default:
                printLCS(b, x, i, j - 1, sb);
                break;
        }
    }

    static String findLCS(String X, String Y) {
        String lcs = null;

        char[] x = X.toCharArray();
        char[] y = Y.toCharArray();

        
        int m = x.length + 1;
        int n = y.length + 1;

        int[][] c = new int[m][n];

        char[][] b = new char[m][n];

        for (int i = 0; i < m; i++) {
            c[i][0] = 0;
        }
        for (int j = 0; j < n; j++) {
            c[0][j] = 0;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (x[i-1] == y[j-1]) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                    b[i][j] = 'D';
                } else if (c[i - 1][j] >= c[i][j - 1]) {
                    c[i][j] = c[i - 1][j];
                    b[i][j] = 'V';
                } else {
                    c[i][j] = c[i][j - 1];
                    b[i][j] = 'H';
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(c[i][j]);
            }
            System.out.println();
        }

        StringBuffer sb = new StringBuffer();
        printLCS(b, x, m - 1, n - 1, sb);
        lcs = sb.toString();

        return lcs;
    }

    public static void main(String[] args) {
        String X = "BAADHADBB";
        String Y = "BHSDXAHAECXBSB";

        System.out.println(findLCS(X, Y));

    }
}
