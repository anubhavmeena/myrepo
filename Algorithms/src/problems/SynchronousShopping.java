/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

import graphs.Graph;
import graphs.Vertex;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ANMEENA
 */
public class SynchronousShopping {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();
        int M = in.nextInt();
        int K = in.nextInt();

        Graph g = new Graph(N);

        for (int i = 1; i <= N; i++) {
            g.addVertex(new Vertex(String.valueOf(i)));
        }
        
        HashSet<Integer> kTYPES = new HashSet();
        
        for (int i = 0; i < K; i++) {
            kTYPES.add(i+1);
        }
        
        ArrayList<Integer>[] T = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            ArrayList list = new ArrayList();
            int P = in.nextInt();
            while (P-- > 0) {
                list.add(in.nextInt());
            }
            T[i] = list;
        }
        for (int i = 0; i < M; i++) {
            String u = in.next();
            String v = in.next();
            int c = in.nextInt();
            g.addEdge(u, v, c);
        }
        List<Vertex> path = g.getShortestPath(g.getVertex("1"), g.getVertex(String.valueOf(N)));
        
        HashSet<Integer> typesCollected = new HashSet();
        
        for(Vertex u : path){
            for(Integer type : T[Integer.valueOf(u.name)-1]){
                typesCollected.add(type);
            }
        }
        
        System.out.println(path);
        System.out.println(typesCollected);
        System.out.println(typesCollected.equals(kTYPES));
        kTYPES.removeAll(typesCollected);
        System.out.println("Not found:"+kTYPES);
    }
}
