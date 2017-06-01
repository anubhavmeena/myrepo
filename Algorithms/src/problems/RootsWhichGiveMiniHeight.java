/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

import graphs.Graph;
import graphs.Vertex;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author ANMEENA
 */
public class RootsWhichGiveMiniHeight {

    public static void main(String[] args) {
        Graph g = new Graph();
        for (int j = 1; j <=5; j++) {
            g.addVertex(new Vertex(String.valueOf(j)));
        }
        g.addEdge(g.getVertex(String.valueOf(1)), g.getVertex(String.valueOf(3)));
        g.addEdge(g.getVertex(String.valueOf(2)), g.getVertex(String.valueOf(3)));
        g.addEdge(g.getVertex(String.valueOf(3)), g.getVertex(String.valueOf(4)));
        g.addEdge(g.getVertex(String.valueOf(4)), g.getVertex(String.valueOf(5)));

        List dia = g.getDiameter();
        if(dia.size()%2==1){
            System.out.println(dia.get(((dia.size()+1)/2)-1));
        }
        else{
            System.out.println(dia.get(dia.size()/2-1));
            System.out.println(dia.get(dia.size()/2));
        }
    }
}
