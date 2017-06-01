/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

import graphs.Edge;
import graphs.Graph;
import static graphs.Graph.time;
import graphs.Vertex;
import java.awt.Color;
import java.util.HashSet;

/**
 *
 * @author ANMEENA
 */
public class DelEdgeMinSubtreeSumDiff {
    
    public static int dfsVisit(Vertex u, int ts, Edge min) {
        System.out.println("U:["+u.name+"]");
        int totalcost = 0;
        time++;
        u.d = time;
        u.color = Color.GRAY;
        for (Vertex v : u.adjList) {
            if (v.color == Color.WHITE) {
                v.p = u;
                v.l = u.l + 1;
                int cost = dfsVisit(v, ts, min);
                totalcost += cost;
                System.out.println("["+u.name+"]<--->"+"["+v.name+"] Cost("+Math.abs(ts - cost)+"-"+cost+"):"+Math.abs(Math.abs(ts - cost) - cost));
                if(min.cost > Math.abs(Math.abs(ts - cost) - cost)){
                    min.u = u;
                    min.v = v;
                    min.cost = Math.abs(Math.abs(ts - cost) - cost);
                }
            }
        }
        time++;
        u.color = Color.BLACK;
        u.f = time;
        //System.out.println("CurCost:"+cost);
        return totalcost + u.key;
    }
    
    public static Edge DFS(Graph g, Vertex s, int ts) {
        System.out.println("S:["+s.name+"]");
        Edge e = new Edge(null, null, Integer.MAX_VALUE);
        for (Vertex u : g.vertices) {
            u.color = Color.WHITE;
            u.p = null;
            u.l = 0;
        }
        time = 0;
        s.color = Color.GRAY;
        dfsVisit(s,ts,e);
        return e;
    }
    
    public static void main(String[] args) {
        Graph g = new Graph();
        
        g.addVertex(new Vertex("0",4));
        g.addVertex(new Vertex("1",2));
        g.addVertex(new Vertex("2",1));
        g.addVertex(new Vertex("3",6));
        g.addVertex(new Vertex("4",3));
        g.addVertex(new Vertex("5",5));
        g.addVertex(new Vertex("6",2));
        
        g.addEdge("0", "1", 1);
        g.addEdge("0", "2", 1);
        g.addEdge("0", "3", 1);
        g.addEdge("2", "4", 1);
        g.addEdge("2", "5", 1);
        g.addEdge("3", "6", 1);
        
        HashSet<Vertex> totalSum = g.BFS(g.getVertex("0"));
        
        System.out.println("TotalSum: "+totalSum);
        
        System.out.println(DFS(g,g.getVertex("0"),totalSum.size()).toString());

    }
}
