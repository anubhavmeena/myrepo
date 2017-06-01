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
public class Jathakam {

    /**
     * @param args the command line arguments
     */
    public static boolean isBorderCell(int i, int j, int s) {
        if (i == 0 || i == s - 1 || j == 0 || j == s - 1) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String[] grid = new String[n];
        for (int grid_i = 0; grid_i < n; grid_i++) {
            grid[grid_i] = in.next();
        }
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        char[][] c = new char[n][n];

        for (int i = 0; i < n; i++) {
            c[i] = grid[i].toCharArray();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!isBorderCell(i, j, n)) {
                    boolean isCavity = true;
                    for (int k = 0; k < dx.length; k++) {
                        if (Character.getNumericValue(c[i + dx[k]][j + dy[k]]) >= Character.getNumericValue(c[i][j])) {
                            isCavity = false;
                            break;
                        }
                    }
                    if (isCavity) {
                        c[i][j] = 'X';
                    }
                }
            }
        }
        System.out.println();
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c.length; j++) {
                System.out.print(c[i][j]);
            }
            System.out.println();
        }
    }

}
