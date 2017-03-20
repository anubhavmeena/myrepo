/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

import java.util.Arrays;

/**
 *
 * @author ANMEENA
 */
public class MaxSubArray {
    
    private static Sum findMaxCrossingSubArray(Integer[]a, int low, int mid, int high){
        int lsum = -99999;
        int sum = 0;
        int maxleft = mid;
        for(int i=mid; i>=low; i--){
            sum += a[i];
            if(sum>lsum){
                lsum=sum;
                maxleft=i;
            }
        }
        
        int rsum = -9999;
        sum = 0;
        int maxright = mid;
        for(int i=mid+1; i<=high; i++){
            sum += a[i];
            if(sum>rsum){
                rsum=sum;
                maxright=i;
            }
        }
        Sum nSum = new Sum();
        nSum.low = maxleft;
        nSum.high = maxright;
        nSum.sum = lsum + rsum;
        
        return nSum;
        
    }
    
    private static Sum findMaxSubArray(Integer[]a, int low, int high){
        
        if(low==high){
            System.out.println("Low=High");
            Sum sum = new Sum();
            sum.low = low;
            sum.high = high;
            sum.sum = a[low];
            return sum;
        }
        
        int mid = (high+low)/2;
        
        System.out.println("Low="+low);
        System.out.println("High="+high);
        System.out.println();
        Sum leftSum = findMaxSubArray(a,low,mid);
        Sum rightSum = findMaxSubArray(a,mid+1,high);
        Sum crossSum = findMaxCrossingSubArray(a,low,mid,high);
        
        if(leftSum.sum>= rightSum.sum && leftSum.sum>=crossSum.sum){
            return leftSum;
        }
        else if(rightSum.sum>= leftSum.sum && rightSum.sum>=crossSum.sum){
            return leftSum;
        }
        else{
            return crossSum;
        }
    }
    
    private static Sum find(Integer[]a){
        Integer b[] = new Integer[a.length-1];
        for(int i=0; i<a.length-1; i++){
            b[i] = a[i+1] - a[i];
        }
        print(b);
        Sum sum = findMaxSubArray(b,0,b.length-1);
        return sum;
    }
    
    private static void print (Integer[] a){
        System.out.println(Arrays.toString(a));
    }
    
    public static void main(String[] args) {
        Integer[] a = {100, 113, 110, 85, 105, 102, 86, 63, 81, 101, 94, 106, 101, 79, 94, 90, 97};
        Sum sum = find(a);
        System.out.println(sum.low+","+sum.high+","+sum.sum);
    }
}

class Sum{
    int low;
    int high;
    int sum;
}
