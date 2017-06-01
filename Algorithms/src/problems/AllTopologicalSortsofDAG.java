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
public class AllTopologicalSortsofDAG {
    
    static void getAllTopologies(Graph g){
        Vertex maxIDV;
        int max = 0;
        for(Vertex u : g.vertices){
            if(u.getDegree()>max){
                max = u.getDegree();
                maxIDV = u; 
            }
        }
    }
    
    public static void main(String[] args) {
        Graph g = new Graph(true);
        for (int j = 0; j <= 8; j++) {
            g.addVertex(new Vertex(String.valueOf(j)));
        }
        g.addEdge(g.getVertex(String.valueOf(5)), g.getVertex(String.valueOf(2)), 1);
        g.addEdge(g.getVertex(String.valueOf(5)), g.getVertex(String.valueOf(0)), 1);
        g.addEdge(g.getVertex(String.valueOf(4)), g.getVertex(String.valueOf(0)), 1);
        g.addEdge(g.getVertex(String.valueOf(4)), g.getVertex(String.valueOf(1)), 1);
        g.addEdge(g.getVertex(String.valueOf(2)), g.getVertex(String.valueOf(3)), 1);
        g.addEdge(g.getVertex(String.valueOf(3)), g.getVertex(String.valueOf(1)), 1);
        
        for(Vertex u : g.vertices){
            u.color = Color.WHITE;
        }
        getAllTopologies(g);
    }
}
