/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import queues.PriorityQ;

/**
 *
 * @author ANMEENA
 */
public class Graph {

    public List<Vertex> vertices;
    public List<Edge> edges;
    public static int time;

    public Graph() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public Vertex getVertex(Vertex v) {
        if (vertices.contains(v)) {
            return vertices.get(vertices.indexOf(v));
        }
        return null;
    }

    public Vertex getVertex(String name) {
        Vertex v = new Vertex(name);
        return getVertex(v);
    }

    public Vertex getVertex(int i, int j) {
        return getVertex(String.valueOf(i) + String.valueOf(j));
    }

    public void addVertex(Vertex v) {
        vertices.add(v);
    }

    public void removeVertex(Vertex v) {
        vertices.remove(v);
    }

    public Edge getEdge(Edge e) {
        if (edges.contains(e)) {
            return edges.get(edges.indexOf(e));
        }
        return null;
    }

    public Edge getEdge(Vertex u, Vertex v) {
        Edge e = new Edge(u, v);
        return getEdge(e);
    }

    public Edge getEdge(Vertex u, Vertex v, boolean isDirected) {
        Edge e = new Edge(u, v, isDirected);
        return getEdge(e);
    }

    public void addEdge(Edge e) {
        e.u.adjList.add(e.v);
        if (!e.isDirected) {
            e.v.adjList.add(e.u);
        }
        edges.add(e);
    }
    
    public void addEdgesFromVertex(Vertex u, List<Vertex> vlist) {
        for(Vertex v : vlist){
            addEdge(new Edge(u,v));
        }
    }
    
    public void removeEdgesFromVertex(Vertex u, List<Vertex> vlist) {
        for(Vertex v : vlist){
            removeEdge(getEdge(u,v));
        }
    }

    public void removeEdge(Edge e) {
        e.u.adjList.remove(e.v);
        if (!e.isDirected) {
            e.v.adjList.remove(e.u);
        }
        edges.remove(e);
    }

    public void addEdge(Vertex u, Vertex v) {
        u.adjList.add(v);
        v.adjList.add(u);
        edges.add(new Edge(u, v));
    }

    public void addEdge(Vertex u, Vertex v, boolean isDirected) {
        u.adjList.add(v);
        if (!isDirected) {
            v.adjList.add(u);
        }
        edges.add(new Edge(u, v, isDirected));
    }

    public void addEdge(Vertex u, Vertex v, Integer cost) {
        u.adjList.add(v);
        v.adjList.add(u);
        edges.add(new Edge(u, v, cost));
    }

    public void addEdge(Vertex u, Vertex v, Integer cost, boolean isDirected) {

        u.adjList.add(v);
        if (!isDirected) {
            v.adjList.add(u);
        }
        edges.add(new Edge(u, v, cost, isDirected));
    }

    public void addEdge(String u, String v) {
        addEdge(getVertex(u), getVertex(v));
    }

    public void addEdge(String u, String v, boolean isDirected) {
        addEdge(getVertex(u), getVertex(v), isDirected);
    }

    public void addEdge(String u, String v, int c) {
        addEdge(getVertex(u), getVertex(v), c);
    }

    public void addEdge(String u, String v, int c, boolean isDirected) {
        addEdge(getVertex(u), getVertex(v), c, isDirected);
    }
    
    public void complement() {
        for(Vertex u : vertices){
            //removeEdgesFromVertex(u,u.adjList);
            List<Vertex> list1 = new ArrayList(u.adjList);
            List<Vertex> list2 = new ArrayList(vertices);
            list2.removeAll(list1);
            list2.remove(u);
            u.adjList = list2;
            //addEdgesFromVertex(u,u.adjList);
        }
        edges = new ArrayList<>();
        
        for(Vertex u : vertices){
            addEdgesFromVertex(u,u.adjList);
        }
    }

    public void transpose() {
        Iterator<Edge> itr = edges.iterator();
        while (itr.hasNext()) {
            Edge e = itr.next();
            e.u.adjList.remove(e.v);
            if (!e.isDirected) {
                e.v.adjList.remove(e.u);
            }
            e.v.adjList.add(e.u);
            if (!e.isDirected) {
                e.u.adjList.add(e.v);
            }
            Vertex tmp = e.u;
            e.u = e.v;
            e.v = tmp;
        }
    }

    public void print() {
        Iterator<Vertex> itr = vertices.iterator();
        while (itr.hasNext()) {
            Vertex u = itr.next();
            for (Vertex v : u.adjList) {
                System.out.println("[" + u.name + "] ---> [" + v.name + "] ");// + getEdge(u, v).cost);
            }
        }

        Iterator<Edge> itre = edges.iterator();
        while (itre.hasNext()) {
            Edge e = itre.next();
            System.out.println(e.toString());
        }

    }
    
    public boolean isBipartite(Vertex s){
        System.out.println("BFS [" + s.name + "]");
        Color nextColor = Color.RED;
        int sum = 0;
        for (Vertex u : vertices) {
            if (u != s) {
                u.color = Color.WHITE;
                u.d = Integer.MAX_VALUE;
                u.p = null;
            }
        }
        s.color = nextColor;
        s.d = 0;
        s.p = null;
        Queue<Vertex> Q = new PriorityQueue();
        Q.add(s);
        sum += s.value;
        while (!Q.isEmpty()) {
            Vertex u = Q.poll();
            System.out.println(u.toString());
            if(u.color==Color.RED){
                nextColor = Color.BLUE;
            }
            else if(u.color==Color.BLUE){
                nextColor = Color.RED;
            }
            for (Vertex v : u.adjList) {
                if(v.color == u.color){
                    return false;
                }
                if (v.color == Color.WHITE) {
                    v.color = nextColor;
                    v.d = u.d + 1;
                    v.p = u;
                    Q.add(v);
                    sum += v.value;
                }
            }
            //u.color = Color.BLACK;
        }
        return true;
    }

    public int BFS(Vertex s) {
        System.out.println("BFS [" + s.name + "]");
        int sum = 0;
        for (Vertex u : vertices) {
            if (u != s) {
                u.color = Color.WHITE;
                u.d = Integer.MAX_VALUE;
                u.p = null;
            }
        }
        s.color = Color.GRAY;
        s.d = 0;
        s.p = null;
        Queue<Vertex> Q = new PriorityQueue();
        Q.add(s);
        sum += s.value;
        while (!Q.isEmpty()) {
            Vertex u = Q.poll();
            System.out.println(u.toString());
            for (Vertex v : u.adjList) {
                if (v.color == Color.WHITE) {
                    v.color = Color.GRAY;
                    v.d = u.d + 1;
                    v.p = u;
                    Q.add(v);
                    sum += v.value;
                }
            }
            u.color = Color.BLACK;
        }
        return sum;
    }

    public int dfsVisit(Vertex u) {
        System.out.println("U:[" + u.name + "]");
        int max = 0;
        time++;
        u.d = time;
        u.color = Color.GRAY;
        for (Vertex v : u.adjList) {
            if (v.color == Color.WHITE) {
                v.p = u;
                v.l = u.l + 1;
                int r = dfsVisit(v);
                if (r > max) {
                    max = r;
                }
            }
        }
        time++;
        u.color = Color.BLACK;
        u.f = time;
        return max + 1;
    }

    public int DFS(Vertex s) {
        System.out.println("S:[" + s.name + "]");

        int m1 = 0, m2 = 0;
        for (Vertex u : vertices) {
            u.color = Color.WHITE;
            u.p = null;
            u.l = 0;
        }
        time = 0;
        s.color = Color.GRAY;
        for (Vertex u : s.adjList) {
            int r = dfsVisit(u);
            if (r > m1) {
                m2 = m1;
                m1 = r;
            }
        }
        System.out.println("m1:" + m1 + " m2:" + m2);
        return m1 + m2;
    }

    public void DFS() {
        for (Vertex u : vertices) {
            u.color = Color.WHITE;
            u.p = null;
        }
        time = 0;
        for (Vertex u : vertices) {
            if (u.color == Color.WHITE) {
                // dfsVisit(u);
            }
        }
    }
    
    public void getMST(){
        
    }

    public int getMinWeightCycle() {
        int min = 999;

        Iterator<Edge> itr = edges.iterator();

        while (itr.hasNext()) {
            Edge e = itr.next();
            System.out.println(e.toString());
            e.u.adjList.remove(e.v);
            e.v.adjList.remove(e.u);
            int d = getShortestPathDistance(e.u, e.v);
            System.out.println("==" + d);
            if (min > d + e.cost) {
                min = d + e.cost;
                System.out.println("Min cycl:" + e.u.name + "," + e.v.name);
            }
            e.u.adjList.add(e.v);
            e.v.adjList.add(e.u);
        }
        return min;
    }

    public int getShortestPathDistance(Vertex s, Vertex z) {
        getShortestPath(s, z);
        return z.d;
    }

    public List<Vertex> getShortestPath(Vertex s, Vertex z) {
        List<Vertex> path = null;

        PriorityQ<Vertex> Q;
        Q = new PriorityQ();

        for (Vertex u : vertices) {
            u.d = 9999;
            u.p = null;
            Q.insert(u);
        }
        s.d = 0;
        s.p = null;
        while (!Q.isEmpty()) {
            Vertex u = Q.removeMax();
            for (Vertex v : u.adjList) {
                if (v.d > u.d + getEdge(u, v).cost) {
                    v.d = u.d + getEdge(u, v).cost;
                    v.p = u;
                }
            }
        }
        System.out.println(s.name + "," + z.name);
        Vertex v = z;
        path = new LinkedList();
        while (v != null) {
            //System.out.println(v.name);
            path.add(v);
            v = v.p;
        }
        //path.add(s);
        Collections.reverse(path);

        return path;
    }

    static void findMinWeightCycle() {
        Graph g = new Graph();

        for (int i = 0; i < 9; i++) {
            g.addVertex(new Vertex(String.valueOf(i)));
        }

        g.addEdge(g.getVertex("0"), g.getVertex("1"), 4);
        g.addEdge(g.getVertex("0"), g.getVertex("7"), 8);
        g.addEdge(g.getVertex("1"), g.getVertex("2"), 8);
        g.addEdge(g.getVertex("1"), g.getVertex("7"), 11);
        g.addEdge(g.getVertex("2"), g.getVertex("8"), 2);
        g.addEdge(g.getVertex("2"), g.getVertex("3"), 7);
        g.addEdge(g.getVertex("2"), g.getVertex("5"), 4);
        g.addEdge(g.getVertex("3"), g.getVertex("5"), 14);
        g.addEdge(g.getVertex("3"), g.getVertex("4"), 9);
        g.addEdge(g.getVertex("4"), g.getVertex("5"), 10);
        g.addEdge(g.getVertex("5"), g.getVertex("6"), 2);
        g.addEdge(g.getVertex("6"), g.getVertex("7"), 1);
        g.addEdge(g.getVertex("6"), g.getVertex("8"), 6);
        g.addEdge(g.getVertex("7"), g.getVertex("8"), 7);

        g.print();

        List<Vertex> path = g.getShortestPath(g.getVertex("0"), g.getVertex("5"));
        for (Vertex u : path) {
            System.out.print(u.name + " --> ");
        }
        System.out.println();
        System.out.println(g.getShortestPathDistance(g.getVertex("0"), g.getVertex("5")));

        System.out.println("Minimum weight cycle:" + g.getMinWeightCycle());
    }

    static void findMaxProdOfNonIntersectingPaths() {
        Graph g = new Graph();

        for (int i = 0; i < 8; i++) {
            g.addVertex(new Vertex(String.valueOf(i + 1)));
        }
        g.addEdge(g.getVertex("1"), g.getVertex("3"), 1);
        g.addEdge(g.getVertex("1"), g.getVertex("8"), 1);
        g.addEdge(g.getVertex("2"), g.getVertex("6"), 1);
        g.addEdge(g.getVertex("3"), g.getVertex("5"), 1);
        g.addEdge(g.getVertex("4"), g.getVertex("8"), 1);
        g.addEdge(g.getVertex("6"), g.getVertex("8"), 1);
        g.addEdge(g.getVertex("7"), g.getVertex("8"), 1);

        //g.addEdge(g.getVertex("1"), g.getVertex("2"), 1);
        //g.addEdge(g.getVertex("2"), g.getVertex("3"), 1);
        //g.addEdge(g.getVertex("3"), g.getVertex("4"), 1);
        Iterator<Vertex> itrv = g.vertices.iterator();

        while (itrv.hasNext()) {
            Vertex v = itrv.next();
            //System.out.println(v.toString());
        }

        int maxProd = 0;

        Iterator<Edge> itr = g.edges.iterator();

        while (itr.hasNext()) {
            Edge e = itr.next();
            System.out.println(e.toString());
            e.u.adjList.remove(e.v);
            e.v.adjList.remove(e.u);

            int maxLengthU = g.DFS(e.u);
            int maxLengthV = g.DFS(e.v);
            int prod = maxLengthU * maxLengthV;
            System.out.println("maxLengthU:" + maxLengthU + " maxLengthV:" + maxLengthV + " prod:" + prod);
            if (prod > maxProd) {
                maxProd = prod;
            }
            e.u.adjList.add(e.v);
            e.v.adjList.add(e.u);
        }

        System.out.println("MaxProd:" + maxProd);

    }

    public static void main(String[] args) {
        //findMinWeightCycle();
        findMaxProdOfNonIntersectingPaths();
    }

}

class VertexComparator implements Comparator<Vertex> {

    @Override
    public int compare(Vertex x, Vertex y) {
        if (x.d < y.d) {
            return -1;
        }
        if (x.d > y.d) {
            return 1;
        }
        return 0;
    }
}
