/*
You can perform the following operation on some string, :

Capitalize zero or more of 's lowercase letters at some index i (i.e., make them uppercase).
Delete all of the remaining lowercase letters in .
Given  queries in the form of two strings,  and , determine if it's possible to make  equal to  by performing the above operation on . If  can be transformed into , print YES on a new line; otherwise, print NO.

Input Format

The first line contains a single integer, , denoting the number of queries. The  subsequent lines describe each query in the following format:

The first line of each query contains a single string, .
The second line of each query contains a single string, .
Constraints

String  consists only of uppercase and lowercase English letters.
String  consists only of uppercase English letters.
Output Format

For each query, print YES on a new line if it's possible to make string  equal to string  by performing the operation specified above; otherwise, print NO.

Sample Input

1
daBcd
ABC
Sample Output

YES
Explanation

We have  daBcd and  ABC. We perform the following operation:

Capitalize the letters a and c in  so that  dABCd.
Delete all the remaining lowercase letters in  so that  ABC.
Because we were able to successfully convert  to , we print YES on a new line.
 */
package problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author ANMEENA
 */
public class Abbreviation {
    
    public static String calculateDP(String a, String b){
        boolean [][] DP = new boolean [a.length()+1][b.length()+1];
        
        char[] s1 = a.toCharArray();
        char[] s2 = b.toCharArray();
        
        for(int i=0;i<=a.length();i++){
            for(int j=0;j<=b.length();j++){
                DP[i][j] = false;
            }
        }
        
        DP[0][0] = true;
        
        for(int i=0; i < s1.length;i++){
            for(int j=0; j<=s2.length; j++){
                if(DP[i][j]){
                    if(j < s2.length && Character.toUpperCase(s1[i])==s2[j]){
                        DP[i+1][j+1] = true;
                    }
                    else if(Character.isLowerCase(s1[i])){
                        DP[i+1][j] = true;
                    }
                }
            }
        }
        
        return DP[a.length()][b.length()] ? "YES" : "NO";
    }
    
    public static void main(String[] args) throws MalformedURLException, IOException {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        //Scanner in = new Scanner(System.in);
        
        
        URL oracle = new URL("https://hr-testcases-us-east-1.s3.amazonaws.com/15427/input06.txt?AWSAccessKeyId=AKIAJAMR4KJHHUS76CYQ&Expires=1490618415&Signature=xtpM6wJTeN8ic5Km1HvKWVnHdH4%3D&response-content-type=text%2Fplain");
        BufferedReader in = new BufferedReader(
        new InputStreamReader(oracle.openStream()));

        int n = Integer.parseInt(in.readLine())*2;
        
        List<String> list = new ArrayList<>();
        
        while(n-->0){
            String inputLine = in.readLine();
            System.out.println(inputLine);
            list.add(inputLine);
        }
        
        while(!list.isEmpty()){
            String a = list.remove(0);
            String b = list.remove(0);
            System.out.println(calculateDP(a,b));
        }
        
    }
}
