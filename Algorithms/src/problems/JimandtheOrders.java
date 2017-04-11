/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author ANMEENA
 */
public class JimandtheOrders {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int f[] = new int[N];
        HashSet<Integer> hs = new HashSet();
        Map<Integer, List> map = new HashMap();
        for (int i = 0; i < N; i++) {
            f[i] = in.nextInt() + in.nextInt();
            hs.add(f[i]);
            List<Integer> l = new ArrayList();
            if(map.containsKey(f[i])){
                l = map.get(f[i]);
            }
            l.add(i+1);
            Collections.sort(l);
            map.put(f[i], l);
        }
        Arrays.sort(f);
        for (int i = 0; i < f.length; i++) {
            if(hs.contains(f[i])){
                Iterator itr = map.get(f[i]).iterator();
                while(itr.hasNext()){
                    System.out.print(itr.next()+" ");
                }
                hs.remove(f[i]);
            }
        }
    }
}
