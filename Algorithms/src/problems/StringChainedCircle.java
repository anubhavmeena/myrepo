/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

import graphs.Edge;
import graphs.Graph;
import graphs.Vertex;
import java.awt.Color;

/**
 *
 * @author ANMEENA
 */
public class StringChainedCircle {
    
    public static boolean findChain(String[] A){
        Graph g = new Graph();
        for(String s : A){
            String first = String.valueOf(s.charAt(0));
            String last = String.valueOf((s.charAt(s.length()-1)));
            
            Vertex u = g.getVertex(first);
            if(u==null){
                 u = new Vertex(first);
                 g.addVertex(u);
            }
            Vertex v = g.getVertex(last);
            if(v==null){
                v = new Vertex(last);
                g.addVertex(v);
            }
            if(!first.equals(last)){
                g.addEdge(g.getVertex(first),g.getVertex(last));
            }
        }
        Vertex s = g.getVertex(String.valueOf(A[0].charAt(0)));
        if(g.BFS(s).size()==g.vertices.size()){
            for (Vertex u : g.vertices) {
                u.color = Color.WHITE;
                u.p = null;
            }
            return g.hasCycle(g.getVertex(s));
        }
        return false;
    }
    
    public static void main(String[] args) {
        String [] s = {"gee", "king"};
        System.out.println(findChain(s));
        System.out.println(findChain(new String[]{"for", "geek", "rig", "kaf"}));
        System.out.println(findChain(new String[]{"aab", "bac", "aaa", "cda"}));
        System.out.println(findChain(new String[]{"aaa", "bbb", "baa", "aab"}));
        System.out.println(findChain(new String[]{"aaa"}));
        System.out.println(findChain(new String[]{"aaa", "bbb"}));
        System.out.println(findChain(new String[]{"abc", "efg", "cde", "ghi", "ija"}));
        System.out.println(findChain(new String[]{"ijk", "kji", "abc", "cba"}));
        
    }
}
