/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

import graphs.Graph;
import graphs.Vertex;
import java.awt.Color;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author ANMEENA
 */
public class MinMoves {
    
    public static int findMinPath(Graph g, Vertex s, Vertex d){
        for(Vertex u : g.vertices){
            if(u!=s){
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
        while(!Q.isEmpty()){
            Vertex u = Q.poll();
            for(Vertex v : u.adjList){
                if(v.color == Color.WHITE){
                    v.color = Color.GRAY;
                    v.d = u.d + 1;
                    v.p = u;
                    if(v==d){
                        break;
                    }
                    Q.add(v);
                }
            }
            u.color = Color.BLACK;
        }
        return printPath(d)-1;
    }
    
    public static int printPath(Vertex z){
        if(z==null){
            return 0;
        }
        int len = printPath(z.p);
        System.out.print("["+z.name+"]-->");
        return len+1;
    }
    
    public static void main(String[] args) {
        int [][] M =   {{ 3 , 3 , 1 , 0 },
                        { 3 , 0 , 3 , 3 },
                        { 2 , 3 , 0 , 3 },
                        { 0 , 3 , 3 , 3 }
                        };
        Graph g = new Graph();
        
        for(int i=0; i < M.length; i++){
            for(int j=0; j < M[i].length; j++){
                g.addVertex(new Vertex(String.valueOf(i)+String.valueOf(j),M[i][j]));
            }
        }
        Vertex source = null;
        Vertex destination = null;
        for(int i=0; i < M.length; i++){
            for(int j=0; j < M[i].length; j++){
                if(M[i][j]!=0){
                    if(M[i][j]==1){
                        source = g.getVertex(String.valueOf(i)+String.valueOf(j));
                    }
                    if(M[i][j]==2){
                        destination = g.getVertex(String.valueOf(i)+String.valueOf(j));
                    }
                    if(g.getVertex(i,j+1)!=null && g.getVertex(i,j+1).key!=0){
                        g.addEdge(g.getVertex(i,j), g.getVertex(i,j+1), 1);
                    }
                    if(g.getVertex(i+1,j)!=null && g.getVertex(i+1,j).key!=0){
                        g.addEdge(g.getVertex(i,j), g.getVertex(i+1,j), 1);
                    }
                }
            }
        }
        int dis = findMinPath(g, source, destination);
        System.out.println();
        System.out.println("Path len:"+dis);
    }
}
