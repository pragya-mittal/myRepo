package linkedlist;

public class Recursive2 {

    Node head;
    static class Node {
        int data;
        Node next;
        public Node (int data) {
            this.data = data;
            next = null;
        }

    }

    private Node recursiveReverse(Node node) {
        Node newHead; //base case - tail of original linked list
        if((node.next == null)) {
            return node;
        }
        newHead = recursiveReverse(node.next);
        //reverse the link e.g. C->D->null will be null
//        System.out.print("Pre : " + node.data + " ");
        node.next.next = node;
        node.next = null;

        System.out.print(node.data + " ");
        return newHead;
    }


    public static void main(String[] args) {
        Recursive2 reverse = new Recursive2();
        reverse.head = new Node(1);
        reverse.head.next = new Node(2);
        reverse.head.next.next = new Node(3);
        reverse.head.next.next.next = new Node(4);
        reverse.head.next.next.next.next = new Node(5);
        reverse.head.next.next.next.next.next = new Node(6);
        reverse.head.next.next.next.next.next.next = new Node(7);

        Node newNode = reverse.recursiveReverse(reverse.head);
        System.out.println("Me");
        while (newNode!= null) {
            System.out.print(newNode.data + " ");
            newNode = newNode.next;
        }

    }


}
