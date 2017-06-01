/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

import graphs.Graph;
import graphs.Vertex;
import java.awt.Color;

/**
 *
 * @author ANMEENA
 */
public class LongestPathBetweenVertices {

    /**
     *
     * @param g
     * @param u
     * @param max
     * @return
     */
    static int dfsVisitmaxCost(Graph g, Vertex u, Max max) {
        System.out.println("U:[" + u.name + "]");
        int localMax1 = 0;
        int localMax2 = 0;
        u.color = Color.GRAY;
        for (Vertex v : u.adjList) {
            if (v.color == Color.WHITE) {
                v.p = u;
                v.l = u.l + 1;
                int r = dfsVisitmaxCost(g,v,max) + g.getEdge(u, v).cost;
                if (r > localMax2) {
                    localMax2 = r;
                }
                if (r > localMax1) {
                    localMax2 = localMax1;
                    localMax1 = r;
                }
                if (localMax1 + localMax2 > max.global){
                    max.global = localMax1 + localMax2;
                }
            }
        }
        u.color = Color.BLACK;
        return localMax1;
    }
    
    public static void main(String[] args) {
        int n = 6;
        int[][] arr = {
            {1, 2, 3},
            {2, 3, 4},
            {2, 6, 2},
            {6, 4, 6},
            {6, 5, 5}};

        Graph g = new Graph();
        for (int j = 1; j <= n; j++) {
            g.addVertex(new Vertex(String.valueOf(j)));
        }
        for (int j = 0; j <arr.length; j++) {
            g.addEdge(g.getVertex(String.valueOf(arr[j][0])), g.getVertex(String.valueOf(arr[j][1])), arr[j][2]);
        }
        Max m = new Max();
        System.out.println(dfsVisitmaxCost(g,g.getVertex(String.valueOf(1)),m));
        System.out.println(m.global);
    }
    
}
    
class Max{
    int global=0;
}