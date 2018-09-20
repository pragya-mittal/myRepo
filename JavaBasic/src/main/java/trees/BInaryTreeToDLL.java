package trees;

public class BInaryTreeToDLL {
    static class Node
    {
        int data;
        Node left, right;

        Node(int item)
        {
            data = item;
            left = right = null;
        }
    }

    Node root;

    private Node binaryToDLL(Node node) {
        if (node==null)
            return null;
        Node dll = bintree2list(node);
        while (dll.left!=null)
            dll = dll.left;
        return dll;
    }

    private Node bintree2list(Node node) {
        if (node==null)
            return null;

        if (node.left!=null) {
            Node leftNode = bintree2list(node.left);
            while (leftNode.right!=null) {
                leftNode = leftNode.right;
            }
            leftNode.right = node;
            node.left = leftNode;
        }

        if (node.right!=null) {
            Node rightNode = bintree2list(node.right);
            while (rightNode.left!=null) {
                rightNode = rightNode.left;
            }
            rightNode.left = node;
            node.right = rightNode;
        }

        return node;
    }

    void printList(Node node) {
        if (node==null)
            return;
        while(node!=null) {
            System.out.print(node.data + " ");
            node=node.right;
        }
    }

    public static void main(String[] args)
    {
        BInaryTreeToDLL tree = new BInaryTreeToDLL();

        tree.root = new Node(10);
        tree.root.left = new Node(12);
        tree.root.right = new Node(15);
        tree.root.left.left = new Node(25);
        tree.root.left.right = new Node(30);
        tree.root.right.left = new Node(36);

        Node head = tree.binaryToDLL(tree.root);
        tree.printList(head);
    }


}
