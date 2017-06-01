/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

import graphs.Graph;
import graphs.Vertex;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author ANMEENA
 */
public class MinimumCostPathLRBU {

    public static HashSet<Vertex> getDijkstraShortestPath(Graph g,Vertex s) {
        Queue<Vertex> Q = new PriorityQueue(g.vertices.size());
        HashSet<Vertex> S = new HashSet();
        for (Vertex u : g.vertices) {
                u.d = Integer.MAX_VALUE/2;
                u.p = null;
        }
        s.d = 0;
        Q.add(s);
        int sum = 0;
        while (!Q.isEmpty()) {
            Vertex u = Q.poll();
            S.add(u);
            sum += u.d;
            for (Vertex v : u.adjList) {
                if(!S.contains(v)){
                    int w = u.d + v.key;
                    if (v.d > w) {
                        v.d = w;
                        v.p = u;
                    }
                    Q.add(v);
                }
            }
        }
        return S;
    }
    
    public static void main(String[] args) {
        int[][] M = {
            {31, 100, 65, 12, 18},
            {10, 13, 47, 157, 6},
            {100, 113, 174, 11, 33},
            {88, 124, 41, 20, 140},
            {99, 32, 111, 41, 20}
        };
        Graph g = new Graph();

        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[i].length; j++) {
                g.addVertex(new Vertex(String.valueOf(i) + String.valueOf(j), M[i][j]));
            }
        }
        
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[i].length; j++) {
                if (M[i][j] != 0) {
                    if (g.getVertex(i, j + 1) != null && g.getVertex(i, j + 1).key != 0) {
                        g.addEdge(g.getVertex(i, j), g.getVertex(i, j + 1), 1);
                    }
                    if (g.getVertex(i + 1, j) != null && g.getVertex(i + 1, j).key != 0) {
                        g.addEdge(g.getVertex(i, j), g.getVertex(i + 1, j), 1);
                    }
                    if (g.getVertex(i - 1, j) != null && g.getVertex(i - 1, j).key != 0) {
                        g.addEdge(g.getVertex(i, j), g.getVertex(i - 1, j), 1);
                    }
                    if (g.getVertex(i, j-1) != null && g.getVertex(i, j-1).key != 0) {
                        g.addEdge(g.getVertex(i, j), g.getVertex(i, j-1), 1);
                    }
                }
            }
        }
        
        getDijkstraShortestPath(g,g.getVertex("00"));
        
        LinkedList<Vertex> path = new LinkedList();
        Vertex z = g.getVertex("44");
        while (z.p != null) {
            path.add(z);
            z = z.p;
        }
        path.add(g.getVertex("00"));
        Collections.reverse(path);
        int sum = 0;
        for(Vertex u : path){
            System.out.print(u.name + " ---> ");
        }
        System.out.println("Cost:"+g.getVertex("44").d);
    }
}
