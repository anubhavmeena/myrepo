/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queues;

import java.util.Arrays;

/**
 *
 * @author ANMEENA
 * @param <K>
 */
public class PriorityQ<K extends Comparable<K>> {

    K[] A = newArray(20);

    @SafeVarargs
    static <E> E[] newArray(int length, E... array) {
        return Arrays.copyOf(array, length);
    }

    int qSize;

    public PriorityQ() {
        qSize = 0;
    }

    public PriorityQ(K[] Z) {
        qSize = Z.length;
        for (int i = 0; i < Z.length; i++) {
            A[i] = Z[i];
        }
        buildMaxHeap();
    }

    public void buildMaxHeap() {
        for (int i = qSize / 2 - 1; i >= 0; i--) {
            heapify(i);
        }
    }

    protected void heapify(int i) {

        int l = left(i);
        int r = right(i);
        int largest = i;

        if (l < qSize && A[l].compareTo(A[i]) < 0) {
            largest = l;
        }
        if (r < qSize && A[r].compareTo(A[largest]) < 0) {
            largest = r;
        }

        if (largest != i) {
            swap(A, i, largest);
            heapify(largest);
        }

    }

    protected void swap(K[] Z, int i, int j) {
        K tmp = Z[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public boolean isEmpty() {
        if (qSize == 0) {
            return true;
        }
        return false;
    }

    public void insert(K key) {
        A[qSize] = key;
        qSize++;
        //print(A);
        int i = qSize - 1;
        while (i > 0 && A[parent(i)].compareTo(A[i]) < 0) {
            swap(A, i, parent(i));
            i = parent(i);
        }
    }

    public K removeMax() {
        K val = null;
        if (!isEmpty()) {
            val = A[0];
            A[0] = A[qSize - 1];
            qSize--;
            //print(A);
            heapify(0);
        }
        return val;
    }

    protected static int parent(int i) {
        return (i + 1) / 2 - 1;
    }

    protected static int left(int i) {
        return (i + 1) * 2 - 1;
    }

    protected static int right(int i) {
        return (i + 1) * 2;
    }

    protected void print() {
        System.out.println(Arrays.toString(A));
    }

    public static void main(String[] args) {

        Integer[] k = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        Character[] c = {'a', 'z', 'b', 'h', 'j', 'x'};

        PriorityQ<Character> pq = new PriorityQ(c);

        System.out.println(pq.removeMax());
        pq.print();
        pq.insert('q');
        pq.insert('w');
        pq.insert('s');
        pq.print();
        System.out.println(pq.removeMax());
        System.out.println(pq.removeMax());
        System.out.println(pq.removeMax());

    }
}
