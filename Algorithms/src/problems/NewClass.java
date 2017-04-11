/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

/**
 *
 * @author ANMEENA
 */
public class NewClass {
    public static void main(String[] args) {
        double rate = 0.11;
        int days = 30;
        double p = 48000;
        
        for(int i=0;i<days;i++){
            p += p*rate/100;
        }
        System.out.println(p);
    }
}
