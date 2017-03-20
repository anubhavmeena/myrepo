/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queues;

/**
 *
 * @author ANMEENA
 */
public class Stack<K> {
    
    public int size;
    K[] A;
    private int top = -1;
    
    public Stack(){
        this.size=10;
        A = (K[]) new Object[size];
    }
    
    public Stack(int size){
        this.size=size;
        A = (K[]) new Object[size];
    }
    
    public boolean isEmpty(){
        if(top<0){
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     *
     * @param key
     * @throws java.lang.Exception
     */
    public void push(K key) throws Exception{
        if(top < A.length-1){
            top++;
            A[top] = key;
        }
        else{
            throw new Exception("Stack Overflow");
        }
    }
    
    /**
     *
     * @return
     */
    public K pop() throws Exception{
        if(!isEmpty()){
            top--;
            return A[top+1];
        }
        else{
            throw new Exception("Stack Empty");
        }
    }
    
    public static void main(String[] args) {
        Stack<Integer> s = new Stack();
        
        try {
             s.push(5);
             s.push(10);
             s.push(9);
             s.push(11);
             s.push(5);
             s.push(10);
             s.push(9);
             s.push(11);
             s.push(5);
             s.push(13);
             while(!s.isEmpty()){
                System.out.println(s.pop());
             }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        
        Stack<String> str = new Stack();
        
        try {
             str.push("apple");
             str.push("ball");
             str.push("cat");
             str.push("dog");
             str.push("egg");
             str.push("fish");
             str.push("goat");
             str.push("horse");
             str.push("iphone");
             str.push("jack");
             
             while(!str.isEmpty()){
                System.out.println(str.pop());
             }
                     
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
}
