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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 *
 * @author ANMEENA
 */
public class Graph {

    public List<Vertex> vertices;
    public List<Edge> edges;
    public static int time;
    public int[][] costMatrix;
    public boolean isDirected;
    
    public Graph() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        isDirected = false;
    }
    
    public Graph(boolean b){
        this();
        isDirected = b;
    }
    
    public Graph(int n){
        this();
        costMatrix = new int[n+1][n+1];
    }
    
    public Graph(int n, boolean b){
        this();
        costMatrix = new int[n+1][n+1];
        isDirected = b;
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
        Edge e = new Edge(u, v, isDirected);
        return getEdge(e);
    }

    public void addEdge(Edge e) {
        e.u.adjList.add(e.v);
        if (!isDirected) {
            e.v.adjList.add(e.u);
        }
        edges.add(e);
    }
    
    public void connect(Edge e) {
        e.u.adjList.add(e.v);
        if (!isDirected) {
            e.v.adjList.add(e.u);
        }
    }

    public void addEdgesFromVertex(Vertex u, List<Vertex> vlist) {
        for (Vertex v : vlist) {
            addEdge(new Edge(u, v, isDirected));
        }
    }

    public void removeEdgesFromVertex(Vertex u, List<Vertex> vlist) {
        for (Vertex v : vlist) {
            removeEdge(getEdge(u, v));
        }
    }

    public void removeEdge(Edge e) {
        e.u.adjList.remove(e.v);
        if (!isDirected) {
            e.v.adjList.remove(e.u);
        }
        edges.remove(e);
    }
    
    public void disconnect(Edge e) {
        e.u.adjList.remove(e.v);
        if (!isDirected) {
            e.v.adjList.remove(e.u);
        }
    }

    public void addEdge(Vertex u, Vertex v) {
        addEdge(u,v,isDirected);
    }

    public void addEdge(Vertex u, Vertex v, boolean isDirected) {
        Edge e = new Edge(u, v, isDirected);
        addEdge(e);
    }
    
    public int getCostfromMatrix(String u, String v){
        return costMatrix[Integer.parseInt(u)][Integer.parseInt(v)];
    }
    
    public void addCost2Matrix(Vertex u, Vertex v, int cost){
        costMatrix[Integer.parseInt(u.name)][Integer.parseInt(v.name)] = cost;
        costMatrix[Integer.parseInt(v.name)][Integer.parseInt(u.name)] = cost;
    }

    public void addEdge(Vertex u, Vertex v, Integer cost) {
        Edge e = getEdge(u, v);
        if (e == null) {
            addEdge(u, v, cost, isDirected);
            //addCost2Matrix(u, v, cost);
        } else {
            if (e.cost > cost) {
                e.cost = cost;
                //addCost2Matrix(u, v, cost);
            }
        }
    }

    public void addEdge(Vertex u, Vertex v, Integer cost, boolean isDirected) {
        Edge e = new Edge(u, v, cost, isDirected);
        addEdge(e);
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
        for (Vertex u : vertices) {
            //removeEdgesFromVertex(u,u.adjList);
            List<Vertex> list1 = new ArrayList(u.adjList);
            List<Vertex> list2 = new ArrayList(vertices);
            list2.removeAll(list1);
            list2.remove(u);
            u.adjList = list2;
            //addEdgesFromVertex(u,u.adjList);
        }
        edges = new ArrayList<>();

        for (Vertex u : vertices) {
            addEdgesFromVertex(u, u.adjList);
        }
    }

    public void transpose() {
        Iterator<Edge> itr = edges.iterator();
        while (itr.hasNext()) {
            Edge e = itr.next();
            e.u.adjList.remove(e.v);
            if (!isDirected) {
                e.v.adjList.remove(e.u);
            }
            e.v.adjList.add(e.u);
            if (!isDirected) {
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

    public boolean isBipartite(Vertex s) {
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
        sum += s.key;
        while (!Q.isEmpty()) {
            Vertex u = Q.poll();
            System.out.println(u.toString());
            if (u.color == Color.RED) {
                nextColor = Color.BLUE;
            } else if (u.color == Color.BLUE) {
                nextColor = Color.RED;
            }
            for (Vertex v : u.adjList) {
                if (v.color == u.color) {
                    return false;
                }
                if (v.color == Color.WHITE) {
                    v.color = nextColor;
                    v.d = u.d + 1;
                    v.p = u;
                    Q.add(v);
                    sum += v.key;
                }
            }
            //u.color = Color.BLACK;
        }
        return true;
    }
    
    public void printPath(Vertex z){
        if(z==null){
            return;
        }
        printPath(z.p);
        System.out.print(z.name+"-->");
    }
    
    public void printEdges(){
        for(Edge e : edges){
            System.out.println(e);
        }
    }
    
    public List getDiameter(){
        LinkedList<Vertex> list = new LinkedList();
        Vertex u = getLastVertexBFS(vertices.get(0));
        Vertex v = getLastVertexBFS(u);
        return getShortestPath(u, v);
    }
    
    public HashSet<Vertex> BFS(Vertex s) {
        //System.out.println("BFS [" + s.name + "]");
        HashSet<Vertex> S = new HashSet();
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
        sum += s.key;
        while (!Q.isEmpty()) {
            Vertex u = Q.poll();
            S.add(u);
            //System.out.println(u.toString());
            for (Vertex v : u.adjList) {
                if (v.color == Color.WHITE) {
                    v.color = Color.GRAY;
                    v.d = u.d + 1;
                    v.p = u;
                    Q.add(v);
                    sum += v.key;
                }
            }
            u.color = Color.BLACK;
        }
        return S;
    }

    public Vertex getLastVertexBFS(Vertex s) {
        Vertex last = null;
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
        while (!Q.isEmpty()) {
            Vertex u = Q.poll();
            last = u;
            //System.out.println(u.toString());
            for (Vertex v : u.adjList) {
                if (v.color == Color.WHITE) {
                    v.color = Color.GRAY;
                    v.d = u.d + 1;
                    v.p = u;
                    Q.add(v);
                }
            }
            u.color = Color.BLACK;
        }
        return last;
    }
    
    
    public Graph getSubGraphBFS(Vertex s) {
        //System.out.println("BFS [" + s.name + "]");
        Graph SG = new Graph();
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
        while (!Q.isEmpty()) {
            Vertex u = Q.poll();
            SG.addVertex(u.clone());
            //System.out.println(u.toString());
            for (Vertex v : u.adjList) {
                if (v.color == Color.WHITE) {
                    v.color = Color.GRAY;
                    v.d = u.d + 1;
                    v.p = u;
                    Q.add(v);
                    SG.addEdge(getEdge(u, v).clone());
                }
            }
            u.color = Color.BLACK;
        }
        return SG;
    }
    
    
    public int dfsVisitmax(Vertex u) {
        //System.out.println("U:[" + u.name + "]");
        int max = 0;
        time++;
        u.d = time;
        u.color = Color.GRAY;
        for (Vertex v : u.adjList) {
            if (v.color == Color.WHITE) {
                v.p = u;
                v.l = u.l + 1;
                int r = dfsVisitmax(v);
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
            int r = dfsVisitmax(u);
            if (r > m1) {
                m2 = m1;
                m1 = r;
            }
        }
        System.out.println("m1:" + m1 + " m2:" + m2);
        return m1 + m2;
    }
    
    public HashSet<Vertex> dfsVisit(Vertex u) {
        //System.out.println("U:[" + u.name + "]");
        HashSet<Vertex> S = new HashSet();
        time++;
        u.d = time;
        u.color = Color.GRAY;
        for (Vertex v : u.adjList) {
            if (v.color == Color.WHITE) {
                v.p = u;
                v.l = u.l + 1;
                S.addAll(dfsVisit(v));
            }
        }
        time++;
        u.color = Color.BLACK;
        u.f = time;
        S.add(u);
        return S;
    }
    
    public void dfsVisitDAG(Vertex u,LinkedList<Vertex> listDAG) {
        //System.out.println("U:[" + u.name + "]");
        time++;
        u.d = time;
        u.color = Color.GRAY;
        for (Vertex v : u.adjList) {
            if (v.color == Color.WHITE) {
                v.p = u;
                v.l = u.l + 1;
                dfsVisitDAG(v, listDAG);
            }
        }
        time++;
        u.color = Color.BLACK;
        u.f = time;
        listDAG.addFirst(u);
    }

    public List<HashSet<Vertex>> DFS() {
        List<HashSet<Vertex>> listCC = new ArrayList<>();
        for (Vertex u : vertices) {
            u.color = Color.WHITE;
            u.p = null;
        }
        time = 0;
        for (Vertex u : vertices) {
            if (u.color == Color.WHITE) {
                 listCC.add(dfsVisit(u));
                 //System.out.println("------");
            }
        }
        return listCC;
    }
    
    public List<Vertex> getDAG() {
        LinkedList<Vertex> listDAG = new LinkedList<>();
        for (Vertex u : vertices) {
            u.color = Color.WHITE;
            u.p = null;
        }
        time = 0;
        for (Vertex u : vertices) {
            if (u.color == Color.WHITE) {
                 dfsVisitDAG(u,listDAG);
            }
        }
        return listDAG;
    }
    
    public boolean hasCycle(Vertex u){
        u.color = Color.GRAY;
        for (Vertex v : u.adjList) {
            if (v.color == Color.WHITE) {
                v.p = u;
                v.l = u.l + 1;
                if(hasCycle(v)){
                    return true;
                }
            }
            else if(v.color != Color.WHITE && v != u.p){
                return true;
            }
        }
        u.color = Color.BLACK;
        return false;
    }
    
    public HashSet<Edge> getPrimsMST(Vertex r) {
        HashSet<Edge> A = new HashSet();
        for(Vertex u : vertices){
            u.d = Integer.MAX_VALUE;
            u.p = null;
        }
        Queue<Vertex> Q = new PriorityQueue(vertices);
        r.d = 0;
        while(!Q.isEmpty()){
            System.out.println(Q);
            Vertex u = Q.poll();
            System.out.println(u.toString());
            for(Vertex v : u.adjList){
                Edge e = getEdge(u, v);
                if(Q.contains(v) && e.cost < v.d){
                    System.out.println("TRUE");
                    v.d = e.cost;
                    v.p = u;
                    Q.remove(v);
                    Q.add(v);
                }
            }
        }
        
        for(Vertex u : vertices){
            if(u.p!=null){
                A.add(getEdge(u, u.p));
            }
        }
        return A;
    }
    
    public HashSet<Edge> getKruskalsMST() {
        HashSet<Edge> A = new HashSet();
        Map<Vertex,HashSet<Vertex>> M = new HashMap(); 
        for(Vertex u : vertices){
            HashSet<Vertex> hs = new HashSet();
            hs.add(u);
            M.put(u, hs);
        }
        Collections.sort(edges);
        for(Edge e : edges){
            HashSet<Vertex> setU = M.get(e.u);
            HashSet<Vertex> setV = M.get(e.v);
            if(!setU.equals(setV)){
                A.add(e);   
                HashSet<Vertex> setZ = new HashSet();
                setZ.addAll(setU);
                setZ.addAll(setV);
                for(Vertex u : setZ){
                    M.put(u, setZ);
                }
            }
        }
        return A;
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

    public void initializeSingleSource(Vertex s) {
        for (Vertex u : vertices) {
            u.d = 999;
            u.p = null;
        }
        s.d = 0;
    }

    public void relax(Vertex u, Vertex v) {
        if (v.d > (u.d + getEdge(u, v).cost)) {
            v.d = u.d + getEdge(u, v).cost;
            v.p = u;
        }
    }
    
    public void runDijkstraSP(Vertex s){
        initializeSingleSource(s);
        PriorityQueue<Vertex> Q = new PriorityQueue<>(vertices.size());
        HashSet<Vertex> S = new HashSet();
        Q.add(s);
        while (!Q.isEmpty()) {
           Vertex u = Q.remove();
           S.add(u);
           for (Vertex v : u.adjList) {
               if(!S.contains(v)){
                    relax(u,v);
                    Q.add(v);
               }
           }
        }
    }
    
    public int getCost(Vertex u, Vertex v){
        return getEdge(u, v).cost;
        //return costMatrix[Integer.parseInt(u.name)][Integer.parseInt(v.name)];
    }

    public void getDijkstraShortestPath(Vertex s) {
        Queue<Vertex> Q = new PriorityQueue(vertices.size());
        HashSet<Vertex> S = new HashSet();
        for (Vertex u : vertices) {
                u.d = Integer.MAX_VALUE/2;
                u.p = null;
        }
        s.d = 0;
        Q.add(s);
        while (!Q.isEmpty()) {
            Vertex u = Q.poll();
            S.add(u);
            for (Vertex v : u.adjList) {
                if(!S.contains(v)){
                    int w = u.d + getCost(u,v);
                    if (v.d > w) {
                        v.d = w;
                        v.p = u;
                    }
                    Q.add(v);
                }
            }
        }
    }

    public LinkedList<Vertex> getShortestPath(Vertex s, Vertex z) {
        LinkedList<Vertex> path = new LinkedList();
        getDijkstraShortestPath(s);
        Vertex v = z;
        while (v.p != null) {
            path.add(v);
            v = v.p;
        }
        path.add(s);
        Collections.reverse(path);
        return path;
    }

    public void printShortestPath(Vertex s, Vertex z) {
        
    }
    
    static void testMST() {
        Graph g = new Graph();

        char a = 'a';
        for (int i = 0; i < 9; i++) {
            g.addVertex(new Vertex(String.valueOf(a++)));
        }
        
        

        g.addEdge(g.getVertex("a"), g.getVertex("b"), 4);
        g.addEdge(g.getVertex("a"), g.getVertex("h"), 8);
        g.addEdge(g.getVertex("b"), g.getVertex("c"), 8);
        g.addEdge(g.getVertex("b"), g.getVertex("h"), 11);
        g.addEdge(g.getVertex("c"), g.getVertex("i"), 2);
        g.addEdge(g.getVertex("c"), g.getVertex("d"), 7);
        g.addEdge(g.getVertex("c"), g.getVertex("f"), 4);
        g.addEdge(g.getVertex("d"), g.getVertex("f"), 14);
        g.addEdge(g.getVertex("d"), g.getVertex("e"), 9);
        g.addEdge(g.getVertex("e"), g.getVertex("f"), 10);
        g.addEdge(g.getVertex("f"), g.getVertex("g"), 2);
        g.addEdge(g.getVertex("g"), g.getVertex("h"), 1);
        g.addEdge(g.getVertex("g"), g.getVertex("i"), 6);
        g.addEdge(g.getVertex("h"), g.getVertex("i"), 7);
        
        //g.print();
        System.out.println(g.getKruskalsMST());
        System.out.println(g.getPrimsMST(g.getVertex("a")));
        
        
        
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
        //findMaxProdOfNonIntersectingPaths();
        testMST();
    }

}

class VertexComparator implements Comparator<Vertex> {

    @Override
    public int compare(Vertex x, Vertex y) {
        if (x.key < y.key) {
            return -1;
        }
        if (x.key > y.key) {
            return 1;
        }
        return 0;
    }
}
