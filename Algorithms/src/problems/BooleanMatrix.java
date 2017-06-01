/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

import graphs.Graph;
import graphs.Vertex;
import java.awt.Color;
import java.util.HashSet;
import static problems.MinMoves.findMinPath;

/**
 *
 * @author ANMEENA
 */
public class BooleanMatrix {

    public static void main(String[] args) {
        int[][] M = {{0, 0, 1, 1, 0},
                    {1, 0, 1, 1, 0},
                    {0, 1, 0, 0, 0},
                    {0, 0, 0, 0, 1}
                    };
        Graph g = new Graph();

        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[i].length; j++) {
                g.addVertex(new Vertex(String.valueOf(i) + String.valueOf(j), M[i][j]));
            }
        }
        Vertex source = null;
        Vertex destination = null;
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[i].length; j++) {
                if (M[i][j] != 0) {
                    if (g.getVertex(i, j + 1) != null && g.getVertex(i, j + 1).key != 0) {
                        g.addEdge(g.getVertex(i, j), g.getVertex(i, j + 1), 1);
                    }
                    if (g.getVertex(i + 1, j) != null && g.getVertex(i + 1, j).key != 0) {
                        g.addEdge(g.getVertex(i, j), g.getVertex(i + 1, j), 1);
                    }
                    if (g.getVertex(i + 1, j + 1) != null && g.getVertex(i + 1, j+1).key != 0) {
                        g.addEdge(g.getVertex(i, j), g.getVertex(i + 1, j+1), 1);
                    }
                    if (g.getVertex(i - 1, j) != null && g.getVertex(i - 1, j).key != 0) {
                        g.addEdge(g.getVertex(i, j), g.getVertex(i - 1, j), 1);
                    }
                    if (g.getVertex(i - 1, j - 1) != null && g.getVertex(i - 1, j - 1).key != 0) {
                        g.addEdge(g.getVertex(i, j), g.getVertex(i - 1, j - 1), 1);
                    }
                    if (g.getVertex(i, j-1) != null && g.getVertex(i, j-1).key != 0) {
                        g.addEdge(g.getVertex(i, j), g.getVertex(i, j-1), 1);
                    }
                    if (g.getVertex(i - 1, j+1) != null && g.getVertex(i - 1, j+1).key != 0) {
                        g.addEdge(g.getVertex(i, j), g.getVertex(i - 1, j+1), 1);
                    }
                    if (g.getVertex(i + 1, j-1) != null && g.getVertex(i + 1, j-1).key != 0) {
                        g.addEdge(g.getVertex(i, j), g.getVertex(i + 1, j-1), 1);
                    }
                }
            }
        }
        int max = 0;
        for(Vertex s : g.vertices){
            if(s.key!=0 && s.color==Color.WHITE){
                HashSet<Vertex> r = g.BFS(s);
                if(r.size() > max){
                    max = r.size();
                }
            }
        }
        System.out.println();
        System.out.println("MAx len:" + max);
    }
}
