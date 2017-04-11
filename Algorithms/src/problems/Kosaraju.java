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
public class Kosaraju {
    
    public static boolean checkIsConnected(Graph g, Vertex s){
        g.BFS(s);
        for(Vertex u : g.vertices){
            if(u.color==Color.WHITE){
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        Graph g = new Graph();
        
        for (int i = 0; i < 5; i++) {
            g.addVertex(new Vertex(String.valueOf(i)));
        }
        
//        g.addEdge("0", "1", true);
//        g.addEdge("1", "2", true);
//        g.addEdge("2", "4", true);
//        g.addEdge("2", "3", true);
//        g.addEdge("3", "0", true);
//        g.addEdge("4", "2", true);
//        
//        if(checkIsConnected(g,g.getVertex("2"))&& checkIsConnected(g.transpose(),g.getVertex("2"))){
//            System.out.println("TRUE");
//        }
//        else{
//            System.out.println("FALSE");
//        }
        
//        g.addEdge("0", "1", true);
//        g.addEdge("1", "2", true);
//        g.addEdge("2", "3", true);
//        g.addEdge("3", "4", true);
//
//        
//        if(checkIsConnected(g,g.getVertex("2")) && checkIsConnected(g.transpose(),g.getVertex("2"))){
//            System.out.println("TRUE");
//        }
//        else{
//            System.out.println("FALSE");
//        }
        
        g.addEdge("0", "1");
        g.addEdge("0", "2");
        g.addEdge("0", "3");
        g.addEdge("1", "2");
        g.addEdge("3", "4");
        
        //g.print();
        g.complement();
        g.print();
        if(g.isBipartite(g.getVertex("0"))){
             System.out.println("TRUE");
        }
        else{
             System.out.println("FALSE");
        }
        
    }
}
