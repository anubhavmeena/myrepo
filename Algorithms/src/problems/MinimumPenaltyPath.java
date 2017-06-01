/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

import graphs.Edge;
import graphs.Graph;
import graphs.Vertex;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author ANMEENA
 */
public class MinimumPenaltyPath {

    public static void getDijkstraShortestPath(Graph g, Vertex s, Map<Edge, List> map) {
        Queue<Vertex> Q = new PriorityQueue(g.vertices.size());
        HashSet<Vertex> S = new HashSet();
        int orTIllNow = 0;
        for (Vertex u : g.vertices) {
            u.d = Integer.MAX_VALUE;
            u.p = null;
        }
        s.d = 0;
        Q.add(s);
        while (!Q.isEmpty()) {
            Vertex u = Q.poll();
            S.add(u);
            for (Vertex v : u.adjList) {
                if (!S.contains(v)) {
                    Edge e = g.getEdge(u, v);
                    List<Integer> l = map.get(e);
                    int w = Integer.MAX_VALUE;
                    for (Integer c : l){
                        if(w >  (u.d | c)){
                            w = u.d | c;
                        }
                    }
                    if (v.d > w) {
                        v.d = w;
                        v.p = u;
                    }
                    Q.add(v);
                }
            }
        }
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Graph g = new Graph();
        Map<Edge, List> map = new HashMap();
        int N = in.nextInt();
        int M = in.nextInt();

        for (int i = 1; i <= N; i++) {
            g.addVertex(new Vertex(String.valueOf(i)));
        }
        while (M-- > 0) {
            String u = in.next();
            String v = in.next();

            int c = in.nextInt();

            List<Integer> cl;

            Edge e = new Edge(g.getVertex(u),g.getVertex(v));
            
            if (map.containsKey(e)) {
                cl = map.get(e);
            } else {
                cl = new ArrayList<Integer>();
            }
            cl.add(c);
            map.put(e, cl);
            g.addEdge(u, v, c);
        }

        String S = in.next();
        String D = in.next();

        getDijkstraShortestPath(g, g.getVertex(S),map);

        System.out.println(g.getVertex(D).d);

    }
}
