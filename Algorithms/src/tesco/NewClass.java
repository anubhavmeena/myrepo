/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesco;

import java.util.HashSet;

/**
 *
 * @author ANMEENA
 */
public class NewClass {

    static int largestDis(String input1, String input2) {
        try {
            if (input1.length() ==0 || input2.length()==0 || input2.length()>1){
                return -1;
            }
            
            if (input1.contains(input2)) {
                int first = input1.indexOf(input2);
                int last = input1.lastIndexOf(input2);
                if (last == first) {
                    return -1;
                }
                System.out.println(input1);
                System.out.println(first);
                System.out.println(last);
                input1 = input1.substring(first, last);
                System.out.println(input1);
                input1 = input1.replaceAll(input2, "").replaceAll("\\s", "");
                char [] c = input1.toCharArray();
                HashSet<Character> hs = new HashSet<Character>();
                
                for(int i=0; i<c.length;i++){
                    hs.add(c[i]);
                }
                
                System.out.println(input1);
                System.out.println(hs.size());
                return input1.length();
            } else {
                return -1;
            }
        } catch (Exception e) {
            throw new UnsupportedOperationException("SearchLetter(String input1,String input2)");
        }
    }

    static int findTrailingZero(int input1) {
        int numZeros = 0;
        try{
            for (int i = 5; input1 / i >= 1; i *= 5) {
                numZeros += input1 / i;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return numZeros;
    }

    public static void main(String[] args) {
        String a = "tat a  t  sussi  a";
        String c = "a";
        System.out.println(largestDis(a, c));

        a = "the capital of minnesota is saint paul";
        c = "y";
        //System.out.println(largestDis(a,c));
        //System.out.println(findTrailingZero(5));

    }
}
