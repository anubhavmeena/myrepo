/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ANMEENA
 */
public class Edge implements Comparable<Edge>,Cloneable{

    public Vertex u;
    public Vertex v;
    public Integer cost;
    boolean isDirected;

    public Edge(Vertex u, Vertex v) {
        this.u = u;
        this.v = v;
        this.cost = 0;
        isDirected = false;
    }

    public Edge(Vertex u, Vertex v, boolean isdirected) {
        this.u = u;
        this.v = v;
        this.cost = 0;
        this.isDirected = isdirected;
    }

    public Edge(Vertex u, Vertex v, Integer cost) {
        this.u = u;
        this.v = v;
        this.cost = cost;
        isDirected = false;
    }

    public Edge(Vertex u, Vertex v, Integer cost, boolean isDirected) {
        this.u = u;
        this.v = v;
        this.cost = cost;
        this.isDirected = isDirected;
    }

    public boolean equals(Object o) {

        if (o instanceof Edge) {
            Edge e = (Edge) o;
            if (this.isDirected == e.isDirected) {
                if (!this.isDirected) {
                    if ((this.u == e.u && this.v == e.v) || (this.u == e.v && this.v == e.u)) {
                        return true;
                    }
                } else {
                    if (this.u == e.u && this.v == e.v) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.u);
        hash = 47 * hash + Objects.hashCode(this.v);
        hash = 47 * hash + Objects.hashCode(this.isDirected);

        return hash;
    }

    public String toString() {
        return u.name + " <----> " + v.name + " Cost: " + cost +"\n";
    }

    public Edge clone(){
        try {
            return (Edge)super.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Edge.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public int compareTo(Edge o) {
        if(this.cost < o.cost){
            return -1;
        }
        else if(this.cost > o.cost){
            return 1;
        }
        return 0;
    }

}
