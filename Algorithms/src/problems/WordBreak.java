/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

import java.util.Arrays;

/**
 *
 * @author anubhav
 */
public class WordBreak {
    static String dictionary[] = {"mobile","samsung","sam","sung","man","mango",
                           "icecream","and","go","i","like","ice","cream", "news","paper"};
    
    static boolean contains(String s){
        System.out.println(s);
        for(String d : dictionary){
            if(d.equals(s)){
                return true;
            }
        }
        return false;
    }
    
    static boolean wordbreak(String s, boolean DP[]){
        
        for(int i=1;i<=s.length();i++){
            if(DP[i]==false && contains(s.substring(0, i))){
                DP[i] = true;
            }
            if(DP[i]==true){
                if(i==s.length()){
                    return true;
                }
                for(int j=i+1;j<=s.length();j++){
                    if(DP[j]==false && contains(s.substring(i, j))){
                        DP[j] = true;
                        System.out.println("--");
                    }
                    if(j==s.length() && DP[j]==true){
                        return true;
                    }
                }   
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        String s  = "papermaninewsgo";
        boolean DP[] = new boolean[s.length()+1];
        boolean R = wordbreak(s,DP);
        System.out.println(Arrays.toString(DP));
        if(R){
            System.out.println("YES");
            System.out.println();
            int p = 0;
            for(int i=1; i<DP.length; i++){
                if(DP[i]){
                    System.out.print(s.substring(p, i)+ " ");
                    p = i;
                }
            }
        }
    }
}
