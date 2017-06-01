/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

import graphs.Edge;
import graphs.Graph;
import graphs.Vertex;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author ANMEENA
 */
public class EvenTree {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();

        Graph g = new Graph();

        for (int i = 1; i <= N; i++) {
            g.addVertex(new Vertex(String.valueOf(i)));
        }

        while (M-- > 0) {
            String u = in.next();
            String v = in.next();
            g.addEdge(u, v);
        }
        
        int count = 0;
        Iterator<Edge> itr = g.edges.iterator();
        while(itr.hasNext()){
            Edge e = itr.next();
            g.disconnect(e);
            if(g.BFS(e.u).size()%2 == 0 && g.BFS(e.v).size()%2 == 0){
                count++;
            }
            g.connect(e);
        }
        System.out.println(count);
    }

    static int getMaxRemovableEdges(Graph g) {
        int sum = 0;
        if (g.vertices.size() == 0 || g.vertices.size() % 2 == 1) {
            return 0;
        }
        if (g.vertices.size() == 2) {
            return 1;
        }
        for (Edge e : g.edges) {
            g.removeEdge(e);
            Graph l = g.getSubGraphBFS(e.u);
            Graph r = g.getSubGraphBFS(e.v);
            sum = getMaxRemovableEdges(l) + getMaxRemovableEdges(r);
//            if (max < sum) {
//                max = sum;
//            }
        }
        sum++;
        return sum;
    }
}
