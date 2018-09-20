package linkedlist;

public class IntersectionLink {
    Node head;

    static class Node {
        int data;
        Node next;
        public Node (int data) {
            this.data = data;
            next = null;
        }
    }

    static int getLength (Node node) {
        if (node == null)
            return -1;
        int counter = 0;
        while (node!=null) {
            node = node.next;
            counter++;
        }
        return counter;
    }

    static Node findIntersection (Node node1, Node node2, int diff) {
        while (diff!=0 && node1!=null) {
            node1 = node1.next;
            diff--;
        }

        while (node1!=null) {
            if(node1 == node2) {
                return node1;
            }
            node1 = node1.next;
            node2 = node2.next;
        }
        System.out.println("No intersection");
        return null;
    }

    static Node findIntersection (Node node1, Node node2) {
        int length1 = getLength(node1);
        int length2 = getLength(node2);

        int diff;
        if (length1==-1 || length2 == -1) {
            System.out.println("No intersection present");
            return null;
        }

        Node intersect;

        if (length1 > length2) {
            intersect = findIntersection(node1, node2, length1-length2);
        } else {
            intersect = findIntersection(node2, node1, length2-length1);
        }

        return intersect;
    }

    public static void main(String[] args) {
        IntersectionLink list1 = new IntersectionLink();
        IntersectionLink list2 = new IntersectionLink();

        list1.head = new Node(1);
        list1.head.next = new Node(2);
        list1.head.next.next = new Node(3);
        list1.head.next.next.next = new Node(9);
        list1.head.next.next.next.next = new Node(10);
        list1.head.next.next.next.next.next = new Node(11);

        list2.head = new Node(4);
        list2.head.next = new Node(5);
        list2.head.next.next = new Node(6);
        list2.head.next.next.next = new Node(7);
        list2.head.next.next.next.next = new Node(8);
        list2.head.next.next.next.next.next = list1.head.next.next.next;

        System.out.println("Intersection : " + IntersectionLink.findIntersection(list1.head, list2.head).data);



    }
}
