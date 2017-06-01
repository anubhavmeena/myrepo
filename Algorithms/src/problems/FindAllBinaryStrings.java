/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import javafx.scene.layout.Priority;

/**
 *
 * @author ANMEENA
 */
public class FindAllBinaryStrings {

    public static void main(String[] args) {
        String s = "1??0?101";
        HashSet<String> hs = new HashSet();
        Queue<String> q = new PriorityQueue<>();
        q.add(s);
        while (!q.isEmpty()) {
            String ss = q.poll();
            if (ss.contains("?")) {
                q.add(ss.replaceFirst("\\?", "0"));
                q.add(ss.replaceFirst("\\?","1"));
            } else {
                hs.add(ss);
                System.out.println(ss);
            }
        }
    }
}
