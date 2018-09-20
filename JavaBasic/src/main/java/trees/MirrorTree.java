package trees;

public class MirrorTree {
    Node root;
    static class Node {
        int key;
        Node left,right;
        public Node (int key) {
            this.key = key;
            left = right = null;
        }
    }

//    Node createMirror(Node node, Node mirrorNode) {
//        if (node == null)
//            return null;
//        if (mirrorNode==null) {
//            mirrorNode = new Node(node.key);
//        }
//        if (node.left!=null) {
//            mirrorNode.right = node.left;
//            createMirror(node.left, mirrorNode.right);
//        }
//        if (node.right!=null) {
//            mirrorNode.left = node.right;
//            createMirror(node.right, mirrorNode.left);
//        }
//        return mirrorNode;
//    }


    void createMirror(Node node) {
        if (node==null)
            return;

        createMirror(node.left);
        createMirror(node.right);
        Node temp = node.left;
        node.left = node.right;
        node.right = temp;
    }


    void printInorder(Node node) {
        if (node==null)
            return;
        printInorder(node.left);
        System.out.print(node.key + " ");
        printInorder(node.right);
    }


    public static void main(String[] args) {
        MirrorTree treeHeight = new MirrorTree();
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
        treeHeight.printInorder(treeHeight.root);
        treeHeight.createMirror(treeHeight.root);
        System.out.println();
        treeHeight.printInorder(treeHeight.root);

    }
}
