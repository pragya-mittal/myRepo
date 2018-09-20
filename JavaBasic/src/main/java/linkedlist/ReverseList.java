package linkedlist;

// https://www.geeksforgeeks.org/reverse-a-linked-list/

public class ReverseList {
    Node head;
    static class Node {
        int data;
        Node next;
        public Node (int data) {
            this.data = data;
            next = null;
        }

    }

    Node reverse (Node curr, Node prev) {
        if (curr == null) {
            return null;
        }
        if (curr.next == null) {
            head = curr;
            curr.next = prev;
            return curr;
        }
        Node next = curr.next;
        curr.next = prev;
        reverse(next, curr);
//        prev = curr;
//        curr = next;
//        reverse(curr, prev);
        return head;
    }

    public static void main(String[] args) {
        ReverseList reverseList = new ReverseList();
        reverseList.head = new Node(1);
        reverseList.head.next = new Node(2);
        reverseList.head.next.next = new Node(3);
        reverseList.head.next.next.next = new Node(4);
        reverseList.head.next.next.next.next = new Node(5);
        reverseList.head.next.next.next.next.next = new Node(6);
        reverseList.head.next.next.next.next.next.next = new Node(7);

        Node rev = reverseList.reverse(reverseList.head, null);
        System.out.print("Hey : ");
        while (rev != null) {
            System.out.print(rev.data + " ");
            rev = rev.next;
        }
    }
}
