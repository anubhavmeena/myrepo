/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

import java.util.HashMap;
import java.util.Set;
import queues.Stack;

/**
 *
 * @author ANMEENA
 */
public class NextGreaterElement {

    public static void main(String[] args) throws Exception {
        int[] A = {8,1,5,3,0,9,7,2,12,10,77,33,21};
        Stack<Integer> S = new Stack();
        HashMap<Integer, Integer> map = new HashMap();
        for (int i = 0; i < A.length; i++) {
            while (!S.isEmpty()) {
                int popped = S.pop();
                if (popped < A[i]) {
                    map.put(popped, A[i]);
                } else {
                    S.push(popped);
                    break;
                }
            }
            S.push(A[i]);
        }
        while (!S.isEmpty()) {
            int popped = S.pop();
            map.put(popped, -1);
        }
        for (Integer n : map.keySet()) {
            System.out.println(n + " --> " + map.get(n));
        }
    }
}
