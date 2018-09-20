package linkedlist;

/**
 * Created by pragya.mittal on 2/15/18.
 */
public class BasicLinkedList {
    Node head;
    static class Node  {
        int data;
        Node next;
        Node (int data) {
            this.data = data;
            next = null;
        }
    }

    public void createList() {
        BasicLinkedList linkedList = new BasicLinkedList();
        linkedList.head = new Node(1);
        Node second = new Node(2);
        Node third = new Node(3);

        linkedList.head.next = second;
        second.next = third;
    }

    public void printList() {
        Node n = head;
        while(n!=null) {
            System.out.println("Data : " + n.data);
            n = n.next;
        }
    }

    public static void main(String[] args) {
        BasicLinkedList llist = new BasicLinkedList();
        llist.createList();
        System.out.println(llist.head.data);

    }

}
