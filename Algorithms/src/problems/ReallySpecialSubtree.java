/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

import graphs.Edge;
import graphs.Graph;
import graphs.Vertex;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author ANMEENA
 */
public class ReallySpecialSubtree {
    
    
    public static int getPrimsMST(Graph g,Vertex r) {
        for(Vertex u : g.vertices){
            u.d = Integer.MAX_VALUE;
            u.p = null;
        }
        Queue<Vertex> Q = new PriorityQueue(g.vertices);
        r.d = 0;
        Q.remove(r);
        Q.add(r);
        while(!Q.isEmpty()){
            Vertex u = Q.poll();
            for(Vertex v : u.adjList){
                Edge e = g.getEdge(u, v);
                if(Q.contains(v) && e.cost < v.d){
                    v.d = e.cost;
                    v.p = u;
                    Q.remove(v);
                    Q.add(v);
                }
            }
        }
        int weightSum = 0;
        for(Vertex u : g.vertices){
            if(u.p!=null){
                weightSum += g.getEdge(u, u.p).cost;
            }
        }
        return weightSum;
    }

    public static int getRST(Graph g) {
        HashSet<Edge> A = new HashSet();
        Map<Vertex,HashSet<Vertex>> M = new HashMap(); 
        for(Vertex u : g.vertices){
            HashSet<Vertex> hs = new HashSet();
            hs.add(u);
            M.put(u, hs);
        }
        //EdgeComparator ec = new EdgeComparator();
        Collections.sort(g.edges);
        int weightSum = 0;
        for(Edge e : g.edges){
            HashSet<Vertex> setU = M.get(e.u);
            HashSet<Vertex> setV = M.get(e.v);
            if(!setU.equals(setV)){
                A.add(e);
                weightSum += e.cost;
                HashSet<Vertex> setZ = new HashSet();
                setZ.addAll(setU);
                setZ.addAll(setV);
                for(Vertex u : setZ){
                    M.put(u, setZ);
                }
            }
        }
        return weightSum;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Graph g = new Graph();

        int N = in.nextInt();
        int M = in.nextInt();

        for (int i = 1; i <= N; i++) {
            g.addVertex(new Vertex(String.valueOf(i)));
        }
        while(M-->0){
            String u = in.next();
            String v = in.next();
            int c = in.nextInt();
            g.addEdge(u, v, c);
        }
        
        String S = in.next();
        
        System.out.println(getPrimsMST(g,g.getVertex(S)));
    }
    
}

class EdgeComparator implements Comparator<Edge> {

    public int compare(Edge x, Edge y) {
        if (x.cost < y.cost) {
            return -1;
        }
        else if (x.cost > y.cost) {
            return 1;
        }
        else{
            if (Integer.valueOf(x.u.name) + x.cost + Integer.valueOf(x.v.name) < Integer.valueOf(y.u.name) + y.cost + Integer.valueOf(y.v.name)) {
                return -1;
            }
            else if (Integer.valueOf(x.u.name) + x.cost + Integer.valueOf(x.v.name) > Integer.valueOf(y.u.name) + y.cost + Integer.valueOf(y.v.name)) {
                return 1;
            }
            else{
                return 0;
            }
        }
    }
}