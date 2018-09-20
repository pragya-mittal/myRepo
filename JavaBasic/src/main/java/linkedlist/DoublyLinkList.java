package linkedlist;

public class DoublyLinkList {
    Node head;

    static class Node
    {
        int data;
        Node prev;
        Node next;


        // Constructor to create a new node
        // next and prev is by default initialized as null
        Node(int d){data=d; prev = null; next = null;}
    }

    void printDLL() {
        Node last = head;
        while(last!=null){
            System.out.println(last.data);
            last = last.next;
        }
    }

    public static void main(String[] args) {

        DoublyLinkList doublyLinkList = new DoublyLinkList();
        doublyLinkList.head = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);

        doublyLinkList.head.next = node2;
        node2.prev = doublyLinkList.head;

        node2.next = node3;
        node3.prev = node2;
        node3.next = node4;
        node4.prev = node3;
        node4.next = node5;
        node5.prev = node4;
        doublyLinkList.printDLL();
    }
}
