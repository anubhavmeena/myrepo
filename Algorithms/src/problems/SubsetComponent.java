/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

import graphs.Graph;
import graphs.Vertex;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ANMEENA
 */
public class SubsetComponent {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();

        long[] A = new long[N];
        for (int i = 0; i < N; i++) {
            long m = Long.parseUnsignedLong(in.next());
            A[i] = m;
        }

        ArrayList<Integer>[] B = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            int k = 0;
            B[i] = new ArrayList();
            for (int j = 0; j < 64; j++) {
                if (((A[i] >> j) & 1) == 1) {
                    B[i].add(j);
                    //System.err.println("["+A[i]+"] j:"+j);
                }
            }
        }

//        for(int i = 0;i<B.length;i++){
//            System.out.println(B[i].toString());
//        }
        ArrayList<int[]> C = new ArrayList();

        getCombinations("", N, C);

        int sum = 0;
        for (int[] P : C) {
            Graph g = new Graph();
            HashSet<Long> hs = new HashSet();
            for (int i = 0; i < 64; i++) {
                g.addVertex(new Vertex(String.valueOf(i)));
            }
            for (int i = 0; i < P.length; i++) {
                //System.out.print(b[i]);
                if (P[i] == 1) {
                    hs.add(A[i]);
                    for (int j = 0; j < B[i].size(); j++) {
                        for (int k = j + 1; k < B[i].size(); k++) {
                            g.addEdge(String.valueOf(B[i].get(j)), String.valueOf(B[i].get(k)));
                        }
                    }
                }
            }
            List<HashSet<Vertex>> listCC = g.DFS();
            System.out.println(hs.toString()+" cc:"+listCC.size());
            sum += listCC.size();
        }
        System.out.println(sum);
    }

    public static void getCombinations(final String currentString, final int upTo, ArrayList<int[]> C) {

        if (upTo == 0) {
            //System.out.println(currentString);
            int[] num = new int[currentString.length()];
            for (int i = 0; i < currentString.length(); i++) {
                num[i] = currentString.charAt(i) - '0';
            }
            C.add(num);
            return;
        }
        getCombinations(currentString + "0", upTo - 1, C);
        getCombinations(currentString + "1", upTo - 1, C);
    }
}
