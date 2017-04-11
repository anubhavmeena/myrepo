/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author ANMEENA
 */
public class Vertex implements Comparable<Vertex> {

    public String name;
    public int value;
    public List<Vertex> adjList;
    public int d;
    public int f;
    public int l;
    public Vertex p;
    public boolean visited;
    public Color color;

    public Vertex(String name) {
        this.name = name;
        this.d = 0;
        adjList = new ArrayList<>();
    }
    
    public Vertex(String name, int value) {
        this.name = name;
        this.d = 0;
        this.value = value;
        this.color = Color.WHITE;
        adjList = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Vertex) {
            Vertex v = (Vertex) o;
            if (this.name.equals(v.name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.name);
        return hash;
    }

//    @Override
//    public int compareTo(Vertex o) {
//        if (this.d == o.d) {
//            return 0;
//        } else if (this.d < o.d) {
//            return -11;
//        } else {
//            return 1;
//        }
//    }
    @Override
    public int compareTo(Vertex o) {
        if (this.d < o.d) {
            return -1;
        }
        if (this.d > o.d) {
            return 1;
        }
        return 0;
    }

    public String toString() {
        return "[" + this.name + "]" + " d=" + d + " f=" + f + " l=" + l +" val="+value;
    }
}
