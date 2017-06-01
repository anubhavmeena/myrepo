/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

import graphs.Edge;
import graphs.Graph;
import graphs.Vertex;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author ANMEENA
 */
public class SnakesandLadders {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();

        while (q-- > 0) {
            Graph g = new Graph(100, true);

            for (int i = 1; i <= 100; i++) {
                g.addVertex(new Vertex(String.valueOf(i)));
            }

            HashMap<Integer, Integer> hm = new HashMap();

            int n = in.nextInt();

            while (n-- > 0) {
                int u = in.nextInt();
                int v = in.nextInt();
                hm.put(u, v);
            }

            int m = in.nextInt();

            while (m-- > 0) {
                int u = in.nextInt();
                int v = in.nextInt();
                hm.put(u, v);
            }

            for (int i = 1; i < 100; i++) {
                for (int j = 1; j <= 6; j++) {
                    if (i + j <= 100) {
                        if (hm.containsKey(i + j)) {
                            g.addEdge(g.getVertex(String.valueOf(i)), g.getVertex(String.valueOf(hm.get(i + j))), 1);
                        } else {
                            g.addEdge(g.getVertex(String.valueOf(i)), g.getVertex(String.valueOf(i + j)), 1);

                        }
                    }
                }
            }
            //g.printEdges();
            //LinkedList<Vertex> path = g.getShortestPath(g.getVertex("1"), g.getVertex("100"));
            g.BFS(g.getVertex("1"));
            int steps = g.getVertex("100").d;
            if(steps == Integer.MAX_VALUE){
                steps = -1;
            }
            System.out.println(steps);
//            for(Vertex u : path){
//                System.out.print("["+u.name+"]-->");
//            }
            System.out.println();
        }
    }

    public static void main2(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();

        while (q-- > 0) {
            Graph g = new Graph(100, true);
            HashSet<Vertex> snakeHole = new HashSet();

            for (int i = 1; i <= 100; i++) {
                g.addVertex(new Vertex(String.valueOf(i)));
            }

            for (int i = 1; i < 100; i++) {
                g.addEdge(g.getVertex(String.valueOf(i)), g.getVertex(String.valueOf(i + 1)), 1);
            }

            int n = in.nextInt();

            while (n-- > 0) {
                int u = in.nextInt();
                int v = in.nextInt();
                g.addEdge(g.getVertex(String.valueOf(u)), g.getVertex(String.valueOf(v)), 0);
            }

            int m = in.nextInt();

            while (m-- > 0) {
                int u = in.nextInt();
                int v = in.nextInt();
                snakeHole.add(g.getVertex(String.valueOf(u)));
                g.addEdge(g.getVertex(String.valueOf(u)), g.getVertex(String.valueOf(v)), 0);
            }

            LinkedList<Vertex> path = g.getShortestPath(g.getVertex("1"), g.getVertex("100"));

            //System.out.println();
            ListIterator<Vertex> ltr = path.listIterator();
            int steps = 0;
            int last = 1;
            while (ltr.hasNext()) {
                Vertex c = ltr.next();
                try {
                    Vertex d = ltr.next();
                    System.out.println("last:" + last + " curr:" + Integer.parseInt(c.name) + " next:" + Integer.parseInt(d.name) + " step:" + steps);
                    if (Integer.parseInt(d.name) == 100 || Integer.parseInt(d.name) - Integer.parseInt(c.name) > 1 || Integer.parseInt(d.name) - Integer.parseInt(c.name) < 0) {
                        steps += Math.ceil((Double.valueOf(c.name) - last) / 6);
                        last = Integer.parseInt(d.name);
                        System.out.println("step = " + steps);
                    }
                    ltr.previous();
                } catch (NoSuchElementException e) {
                    break;
                }
            }
            System.out.println();
            //System.out.println(g.getVertex("100").d);
            System.out.println(steps);
        }
    }
}
