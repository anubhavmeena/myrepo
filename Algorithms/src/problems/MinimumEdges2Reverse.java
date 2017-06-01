/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

import graphs.Graph;
import graphs.Vertex;

/**
 *
 * @author ANMEENA
 */
public class MinimumEdges2Reverse {
    public static void main(String[] args) {
        int edges[][] = {{0, 1}, {2, 1}, {2, 3}, {5, 1},
                     {4, 5}, {6, 4}, {6, 3}};
        
        Graph g = new Graph(true);
        for(int i=0; i<7;i++){
            g.addVertex(new Vertex(String.valueOf(i)));
        }
        for(int [] e: edges){
            g.addEdge(g.getVertex(String.valueOf(e[0])), g.getVertex(String.valueOf(e[1])),0);
            g.addEdge(g.getVertex(String.valueOf(e[1])), g.getVertex(String.valueOf(e[0])),1);
        }
        g.getDijkstraShortestPath(g.getVertex(String.valueOf(0)));
        System.out.println(g.getVertex(String.valueOf(6)).d);
        g.printPath(g.getVertex(String.valueOf(6)));
    }
}
