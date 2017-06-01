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
public class EditDistance {

    public static int[][] LCS(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
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
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
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
        System.out.println(cost[m][n]);
        return path;
    }

    public static int editDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int cost[][] = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            cost[i][0] = i;
        }
        for (int j = 1; j <= n; j++) {
            cost[0][j] = j;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    cost[i][j] = cost[i - 1][j - 1];
                } else {
                    cost[i][j] = 1 + min(cost[i - 1][j], //Insert
                            cost[i][j - 1], // Remove
                            cost[i - 1][j - 1]); // Replace
                }
            }
        }
        return cost[m][n];
    }

    static void printLCS(int[][] path, String X, int i, int j) {
        if (i == 0 || j == 0) {
            return;
        }
        switch (path[i][j]) {
            case 0:
                printLCS(path, X, i - 1, j - 1);
                System.out.print(X.charAt(i - 1));
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
        //printLCS(LCS(s1, s2), s1, s1.length(), s2.length());
        System.out.println(editDistance(s1,s2));
    }
}
