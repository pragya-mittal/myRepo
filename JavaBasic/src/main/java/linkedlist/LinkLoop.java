package linkedlist;

public class LinkLoop {
    Node head;

    static class Node {
        int data;
        Node next;
        public Node(int data) {
            this.data = data;
            next = null;
        }
    }

    Node findLoop() {
        Node slow = head;
        Node fast = head;
        slow = slow.next;
        fast = fast.next.next;

        while (slow!=fast && fast!=null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast==null) {
            System.out.println("No loop present");
            return null;
        }

        slow = head;
        while (slow != fast) {
            slow=slow.next;
            fast = fast.next;
        }

        return slow;
    }

    int loopLength(Node loopStart) {
        Node node1= loopStart;
        int length = 1;
        while (node1.next!= loopStart) {
            node1 = node1.next;
            length++;
        }
        return length;
    }

    public static void main(String[] args) {
        LinkLoop linkLoop = new LinkLoop();
        linkLoop.head = new Node(1);
        linkLoop.head.next = new Node(2);
        linkLoop.head.next.next = new Node(3);
        linkLoop.head.next.next.next = new Node(4);
        linkLoop.head.next.next.next.next = new Node(5);
        linkLoop.head.next.next.next.next.next = new Node(6);
        linkLoop.head.next.next.next.next.next.next = new Node(7);
        linkLoop.head.next.next.next.next.next.next.next = new Node(8);
        linkLoop.head.next.next.next.next.next.next.next.next = linkLoop.head.next.next;

        if (linkLoop.findLoop() != null) {
            System.out.println(linkLoop.findLoop().data);
            System.out.println("Loop length is : " + linkLoop.loopLength(linkLoop.findLoop()));
        }

    }
}
