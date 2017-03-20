/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lists;

/**
 *
 * @author ANMEENA
 */
public class SinglyLinkedList<T> {

    Node head;
    Node tail;

    public SinglyLinkedList() {
    }

    void insert(T key) {
        Node n = new Node(key);
        n.next = head;
        head = n;
    }
    
    public void addLast(T key){
        Node n = new Node(key);
        if(tail!=null){
            tail.next = n;
        }
        else{
            head = n;
        }
        tail = n;
    }
    
    public Node removeFirst(){
        Node n = head;
        if(head==tail){
            tail = null;
        }
        if(head!=null){
            head = head.next;
        }
        return n;
    }
    
    void delete(T key) {
        for (Node curr = head, prev = null; curr != null; curr = curr.next) {
            if (curr.value.equals(key)) {
                if (curr == head) {
                    head = curr.next;
                    break;
                }
                prev.next = curr.next;
            }
            prev = curr;
        }
    }
    
    void reverse(){
        Node curr, prev;
        for (curr = head, prev = null; curr != null;) {
            Node tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }
        head = prev;
    }

    void print() {
        for (Node curr = head; curr != null; curr = curr.next) {
            System.out.println(curr.value);
        }
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        
        list.insert(8);
        
        list.print();
        list.reverse();
        System.out.println("-------------------");
        list.print();
    }

}

class Node<T> {

    Node next;
    T value;

    public Node(T value) {
        this.value = value;
    }
}
