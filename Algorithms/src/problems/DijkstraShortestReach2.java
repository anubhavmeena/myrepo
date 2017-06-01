/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

import graphs.Edge;
import graphs.Graph;
import graphs.Vertex;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author ANMEENA
 */
public class DijkstraShortestReach2 {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int q = in.nextInt();

        while (q-- > 0) {
            int n = in.nextInt();
            Graph g = new Graph(n);
            int m = in.nextInt();
            for (int i = 1; i <= n; i++) {
                g.addVertex(new Vertex(String.valueOf(i)));
            }
            
            for (int i = 0; i < m; i++) {
                String u = in.next();
                String v = in.next();
                int c = in.nextInt();

                if (g.getCostfromMatrix(u,v) != c) {
                    g.addEdge(u, v, c);
                }
            }
            String s = in.next();
            Vertex start = g.getVertex(s);
            
            g.getDijkstraShortestPath(g.getVertex(s));

            for (Vertex u : g.vertices) {
                if (u != start) {
                    if (u.d == Integer.MAX_VALUE) {
                        u.d = -1;
                    }
                    System.out.print(u.d + " ");
                }
            }
            System.out.println();
        }

    }
}
