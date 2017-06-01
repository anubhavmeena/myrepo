/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

import graphs.Graph;
import graphs.Vertex;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ANMEENA
 */
public class MinStepsReq {
    public static void main(String[] args) {
        int arr[] = {0, 1, 2, 3, 4, 5, 6, 7, 5, 4,
                 3, 6, 0, 1, 2, 3, 4, 5, 7};
        
        Graph g = new Graph();
        
        for(int i=0; i<arr.length;i++){
            Vertex v = new Vertex(String.valueOf(i),arr[i]);
            g.addVertex(v);
            
        }
        for(int i=0; i<arr.length-1;i++){
            g.addEdge(g.getVertex(String.valueOf(i)), g.getVertex(String.valueOf(i+1)),1);
            for(Vertex u : g.vertices){
                if(u.key == arr[i] && Integer.valueOf(u.name)!=i){
                    g.addEdge(g.getVertex(String.valueOf(i)), u,1);
                }
            }
        }
        g.BFS(g.getVertex(String.valueOf(0)));
        System.out.println(g.getVertex(String.valueOf(arr.length-1)).d);
        g.printPath(g.getVertex(String.valueOf(arr.length-1)));
    }
}
