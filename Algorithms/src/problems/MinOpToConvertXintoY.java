/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

import graphs.Graph;
import graphs.Vertex;
import java.util.Scanner;

/**
 *
 * @author ANMEENA
 */
public class MinOpToConvertXintoY {

    public static void main(String[] args) {
        while (true) {
            Scanner in = new Scanner(System.in);
            int x = in.nextInt();
            int y = in.nextInt();

            int n = x > y ? 2 * x : 2 * y;
            //x = x*2;
            //x = x - 1;
            Graph g = new Graph(true);
            for (int i = 1; i <= n; i++) {
                g.addVertex(new Vertex(String.valueOf(i)));
            }
            for (int i = 1; i <= n; i++) {
                int a = i * 2;
                int b = i - 1;
                if (a > 0 && a <= n) {
                    g.addEdge(String.valueOf(i), String.valueOf(a), 1);
                }
                if (b > 0 && b <= n) {
                    g.addEdge(String.valueOf(i), String.valueOf(b), 1);
                }
            }
            g.BFS(g.getVertex(String.valueOf(x)));
            System.out.println(g.getVertex(String.valueOf(y)).d);
            g.printPath(g.getVertex(String.valueOf(y)));
            System.out.println();
        }
    }
}
