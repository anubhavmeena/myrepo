/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

/**
 *
 * @author anubhav
 */
public class RearrangeObjects {

    static int count = 0;
    
    static void swap(Obj A[], int i, int j) {
        Obj T = A[i];
        A[i] = A[j];
        A[j] = T;
    }

    static void reorder(Obj[] A, int s, int e) {
        if (s >= e) {
            return;
        }
        int p = s;
        for (int i = s + 1; i < e; i++) {
            if (A[i].getType().equals(A[p].getType()) && i!=p+1) {
                p++;
                swap(A, i, p);
            }
            count++;
        }
        reorder(A, p + 1, e);
    }

    static void print(Obj[] A) {
        for (Obj o : A) {
            System.out.print(o.toString() + ", ");
        }
    }

    public static void main(String[] args) {
        Obj[] ARR = new Obj[]{new B(), new A(), new C(), new B(), new C(), new A(), new A(), new C()};
        print(ARR);
        reorder(ARR, 0, ARR.length);
        System.out.println("");
        print(ARR);
        System.out.println("");
        System.out.println(count);
    }
}

interface Obj {
    public String getType();
}

class A implements Obj {

    final String type = "A";

    public String getType() {
        return this.type;
    }

    public String toString() {
        return this.type;
    }
}

class B implements Obj {

    final String type = "B";

    public String getType() {
        return this.type;
    }

    public String toString() {
        return this.type;
    }
}

class C implements Obj {

    final String type = "C";

    public String getType() {
        return this.type;
    }

    public String toString() {
        return this.type;
    }
}
