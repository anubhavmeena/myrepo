/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

import graphs.Graph;
import static graphs.Graph.time;
import graphs.Vertex;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author ANMEENA
 */
public class FindPathMorethanKlength {

    public static Vertex dfsVisit(Graph g, Vertex u, int sum, int K) {
        //System.out.println("U:[" + u.name + "]");
        time++;
        u.d = time;
        u.color = Color.GRAY;
        for (Vertex v : u.adjList) {
            if (v.color == Color.WHITE) {
                v.p = u;
                v.l = u.l + 1;
                v.d = sum + g.getEdge(u, v).cost;
                if (sum + g.getEdge(u, v).cost > K) {
                    System.out.println(v.d);
                    System.out.println(v.toString());
                    return v;
                }
                Vertex r = dfsVisit(g, v, sum + g.getEdge(u, v).cost, K);
                if (r != null) {
                    return r;
                } else {
                    v.color = Color.WHITE;
                }
            }
        }
        time++;
        u.color = Color.WHITE;
        u.f = time;
        return null;
    }

    public static Vertex DFSK(Graph g, Vertex s, int K) {
        for (Vertex u : g.vertices) {
            u.color = Color.WHITE;
            u.p = null;
        }
        time = 0;
        return dfsVisit(g, s, 0, K);
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        for (int j = 0; j <= 8; j++) {
            g.addVertex(new Vertex(String.valueOf(j)));
        }
        g.addEdge(g.getVertex(String.valueOf(0)), g.getVertex(String.valueOf(1)), 4);
        g.addEdge(g.getVertex(String.valueOf(0)), g.getVertex(String.valueOf(7)), 8);
        g.addEdge(g.getVertex(String.valueOf(1)), g.getVertex(String.valueOf(2)), 8);
        g.addEdge(g.getVertex(String.valueOf(1)), g.getVertex(String.valueOf(7)), 11);
        g.addEdge(g.getVertex(String.valueOf(2)), g.getVertex(String.valueOf(3)), 7);
        g.addEdge(g.getVertex(String.valueOf(2)), g.getVertex(String.valueOf(8)), 2);
        g.addEdge(g.getVertex(String.valueOf(2)), g.getVertex(String.valueOf(5)), 4);
        g.addEdge(g.getVertex(String.valueOf(3)), g.getVertex(String.valueOf(4)), 9);
        g.addEdge(g.getVertex(String.valueOf(3)), g.getVertex(String.valueOf(5)), 14);
        g.addEdge(g.getVertex(String.valueOf(4)), g.getVertex(String.valueOf(5)), 10);
        g.addEdge(g.getVertex(String.valueOf(5)), g.getVertex(String.valueOf(6)), 2);
        g.addEdge(g.getVertex(String.valueOf(6)), g.getVertex(String.valueOf(8)), 6);
        g.addEdge(g.getVertex(String.valueOf(6)), g.getVertex(String.valueOf(7)), 1);
        g.addEdge(g.getVertex(String.valueOf(7)), g.getVertex(String.valueOf(8)), 7);

        Vertex r = DFSK(g, g.getVertex(String.valueOf(0)), 58);
        g.printPath(r);
        System.out.println("");
        //System.out.println(g.getVertex(String.valueOf(8)).d);
    }
}
