/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

import graphs.Graph;
import graphs.Vertex;
import java.util.Scanner;

/**
 *
 * @author ANMEENA
 */
public class BFS_ShortestReach {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        
        
        while(q-->0){
            
            Graph g = new Graph();
            
            int n = in.nextInt();
            int m = in.nextInt();
        
            for (int i = 1; i <= n; i++) {
                g.addVertex(new Vertex(String.valueOf(i)));
            }

            for(int i=0;i<m;i++){
                String u = in.next();
                String v = in.next();
                g.addEdge(u,v);
            }

            String s = in.next();
            
            g.BFS(g.getVertex(s));
            
            for (Vertex u : g.vertices) {
                 if (u != g.getVertex(s)) {
                     if(u.d == Integer.MAX_VALUE){
                         u.d = -1;
                     }
                     else{
                         u.d *=6;
                     }
                     System.out.print(u.d+" ");
                 }
            }
             System.out.println();
        }
        
    }
}
