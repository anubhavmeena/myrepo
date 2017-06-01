/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

import graphs.Graph;
import graphs.Vertex;
import java.awt.Color;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ANMEENA
 */
public class Journey2theMoon {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();
        int P = in.nextInt();

        Graph g = new Graph();
        HashMap<Vertex, HashSet> map = new HashMap<>(N);

        for (int i = 0; i < N; i++) {
            g.addVertex(new Vertex(String.valueOf(i)));
        }

        while (P-- > 0) {
            String u = in.next();
            String v = in.next();
            g.addEdge(u, v);
        }
        long ways = 0;
        long sum = 0;
        List<HashSet<Vertex>> listCC = g.DFS();

        for (HashSet<Vertex> hs : listCC) {
            ways +=  sum * hs.size();
            sum += hs.size();
        }
        //ways *= binomial(listCC.size(),2);
        
        System.out.println(ways);
    }

    static Integer binomial(final int N, final int K) {
        Integer ret = 1;
        for (int k = 0; k < K; k++) {
            ret = ret * Integer.valueOf(N - k) / Integer.valueOf(k + 1);
        }
        return ret;
    }
}
