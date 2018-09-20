package trees;

public class IdenticalTree {

    Node root;
    static class Node {
        int key;
        Node left,right;
        public Node (int key) {
            this.key = key;
            left = right = null;
        }
    }

    static boolean isIdentical(Node node1, Node node2) {
        if (node1==null && node2==null)
            return true;
        if (node1!=null && node2!=null) {
            if (node1.key==node2.key)
                return (isIdentical(node1.left,node2.left) && isIdentical(node1.right,node2.right));
        }

        return false;
    }

    public static void main(String[] args) {
        IdenticalTree treeHeight = new IdenticalTree();
        treeHeight.root = new Node(1);
        treeHeight.root.left = new Node(2);
        treeHeight.root.left.left = new Node(4);
        treeHeight.root.left.left.left = new Node(8);
        treeHeight.root.left.right = new Node(5);
        treeHeight.root.right = new Node(3);
        treeHeight.root.right.left = new Node(6);
        treeHeight.root.right.left.left = new Node(9);
        treeHeight.root.right.right = new Node(7);
        treeHeight.root.right.right.right = new Node(10);
        treeHeight.root.right.right.right.left = new Node(11);

        IdenticalTree treeHeight1 = new IdenticalTree();
        treeHeight1.root = new Node(1);
        treeHeight1.root.left = new Node(2);
        treeHeight1.root.left.left = new Node(4);
        treeHeight1.root.left.left.left = new Node(8);
        treeHeight1.root.left.right = new Node(5);
        treeHeight1.root.right = new Node(3);
        treeHeight1.root.right.left = new Node(6);
        treeHeight1.root.right.left.left = new Node(9);
        treeHeight1.root.right.right = new Node(7);
        treeHeight1.root.right.right.right = new Node(10);
//        treeHeight1.root.right.right.right.left = new Node(11);

        if (isIdentical(treeHeight1.root, treeHeight.root)) {
            System.out.println("Trees are identical");
        } else {
            System.out.println("Non identical");
        }
    }
}
