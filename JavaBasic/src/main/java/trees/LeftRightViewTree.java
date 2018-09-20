package trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LeftRightViewTree {
    static class Node {
        int key;
        Node left,right;
        public Node (int key) {
            this.key = key;
            left = right = null;
        }
    }

    Node root;

    private Node createTree()
    {
        this.root = new Node(1);
        Node n1   = new Node(2);
        Node n2   = new Node(3);
        Node n3   = new Node(4);
        Node n4   = new Node(5);
        Node n5   = new Node(6);
        Node n6   = new Node(8);
        Node n7   = new Node(9);
        Node n8   = new Node(10);

        root.left  = n1;
        root.right = n2;

        n1.left  = n3;
        n1.right = n4;

        n2.left  = n5;

        n3.right = n6;

        n5.right = n7;

        n6.right = n8;

        return root;
    }

    int max_level;

    //Approach - 1 : Modifying preorder
    //Time Complexity is O(n)
    //Space Complexity is O(1)
    void printLeftView(Node node, int level) {
        if (node==null)
            return;
        if (level > max_level) {
            System.out.print(node.key + " ");
            max_level = level;
        }
        printLeftView(node.left, level+1);
        printLeftView(node.right, level+1);
    }

    void printRightView(Node node, int level) {
        if (node==null)
            return;
        if (level > max_level) {
            System.out.print(node.key + " ");
            max_level = level;
        }
        printRightView(node.right, level+1);
        printRightView(node.left, level+1);
    }

    // Approach 2 - Using queue
    // Time Complexity is O(n)
    // Space Complexity is O(n)

    static class NodeLevel {
        Node node; int level;
        public NodeLevel(Node node, int level) {
            this.node = node; this.level = level;
        }
    }

    void printRightViewUsingQueue(Node node, int level) {
        if (node==null)
            return;
        LinkedList<NodeLevel> list = new LinkedList();
        list.add(new NodeLevel(node, 0));
        while (!list.isEmpty()) {
            NodeLevel nodeLevel = list.remove();
            if (nodeLevel.level > max_level) {
                System.out.print(nodeLevel.node.key + " ");
                max_level = nodeLevel.level;
            }
            if (nodeLevel.node.right!=null)
                list.add(new NodeLevel(nodeLevel.node.right, nodeLevel.level + 1));
            if (nodeLevel.node.left!=null)
                list.add(new NodeLevel(nodeLevel.node.left, nodeLevel.level + 1));
        }

    }

    void printLeftViewUsingQueue(Node node, int level) {
        if (node==null)
            return;
        LinkedList<NodeLevel> list = new LinkedList();
        list.add(new NodeLevel(node, 0));
        while (!list.isEmpty()) {
            NodeLevel nodeLevel = list.remove();
            if (nodeLevel.level > max_level) {
                System.out.print(nodeLevel.node.key + " ");
                max_level = nodeLevel.level;
            }
            if (nodeLevel.node.left!=null)
                list.add(new NodeLevel(nodeLevel.node.left, nodeLevel.level + 1));
            if (nodeLevel.node.right!=null)
                list.add(new NodeLevel(nodeLevel.node.right, nodeLevel.level + 1));
        }

    }


    public static void main(String[] args) {
        LeftRightViewTree leftRightViewTree = new LeftRightViewTree();
        leftRightViewTree.createTree();
        leftRightViewTree.max_level = -1;
        leftRightViewTree.printLeftView(leftRightViewTree.root, 0);
        System.out.println("\n");
        leftRightViewTree.max_level = -1;
        leftRightViewTree.printLeftViewUsingQueue(leftRightViewTree.root, 0);
        System.out.println("\n");
        leftRightViewTree.max_level = -1;
        leftRightViewTree.printRightView(leftRightViewTree.root, 0);
        System.out.println("\n");
        leftRightViewTree.max_level = -1;
        leftRightViewTree.printRightViewUsingQueue(leftRightViewTree.root, 0);


    }
}
