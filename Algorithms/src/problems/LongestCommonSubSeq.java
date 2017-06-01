/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

import java.util.Collections;

/**
 *
 * @author ANMEENA
 */
public class LongestCommonSubSeq {

    public static int LCS(Comparable[] s1, Comparable[] s2) {
        int m = s1.length;
        int n = s2.length;
        int cost[][] = new int[m + 1][n + 1];
        int path[][] = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            cost[i][0] = 0;
        }
        for (int j = 1; j <= n; j++) {
            cost[0][j] = 0;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1[i - 1].compareTo(s2[j - 1]) == 0) {
                    cost[i][j] = cost[i - 1][j - 1] + 1;
                    path[i][j] = 0;
                } else if (cost[i - 1][j] > cost[i][j - 1]) {
                    cost[i][j] = cost[i - 1][j];
                    path[i][j] = 1;
                } else {
                    cost[i][j] = cost[i][j - 1];
                    path[i][j] = -1;
                }
            }
        }
        //System.out.println(cost[m][n]);
        //return path;
        return cost[m][n];
    }

    static void printLCS(int[][] path, Comparable[] X, int i, int j) {
        if (i == 0 || j == 0) {
            return;
        }
        switch (path[i][j]) {
            case 0:
                printLCS(path, X, i - 1, j - 1);
                System.out.print(X[i - 1].toString() + " ");
                break;
            case 1:
                printLCS(path, X, i - 1, j);
                break;
            case -1:
                printLCS(path, X, i, j - 1);
                break;
        }
    }

    public static int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    public static void main(String[] args) {
        String s1 = "sunday";
        String s2 = "saturday";
        Character[] coa1 = s1.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
        Character[] coa2 = s2.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
        //printLCS(LCS(coa1, coa2), coa1, s1.length(), s2.length());

        System.out.println("");
        Integer[] in1 = {2, 5, 2, 1, 6, 45, 8, 3};
        Integer[] in2 = {0, 2, 5, 0, 1, 6, 21, 8, 3, 77};

        //printLCS(LCS(in1, in2), in1, in1.length, in2.length);
        String[] dict = {"pintu", "geeksfor", "geeksgeeks", 
                                        " forgeek"} ;
        String str = "geeksforgeeks";
        int max = 0;
        int j=0;
        for (int i = 0; i < dict.length; i++) {
            Character[] c1 = dict[i].chars().mapToObj(c -> (char) c).toArray(Character[]::new);
            Character[] c2 = str.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
            int csq = LCS(c1, c2);
            if(csq>max){
                max = csq;
                j=i;
            }
        }
        System.out.println(dict[j]);
    }
}
