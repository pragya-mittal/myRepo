package trees;


//Inorder - left, root,right - gives nodes in non-decreasing order
//Preorder - root, left, right - create copy of the tree
//Postorder - left,right, root - deletion of tree

public class TreeCreation {
    static class Node {
        int key;
        Node left,right;
        public Node (int key) {
            this.key = key;
            left = right = null;
        }
    }
    Node root;

    public TreeCreation() {
        root = null;
    }

    public TreeCreation(int key) {
        root = new Node(key);
    }

    void printInorder(Node node) {
        if (node == null)
            return;
        printInorder(node.left);
        System.out.print(node.key + " ");
        printInorder(node.right);
    }

    void printPreorder(Node node) {
        if (node == null)
            return;
        System.out.print(node.key + " ");
        printPreorder(node.left);
        printPreorder(node.right);
    }

    void printPostorder(Node node) {
        if (node == null)
            return;
        printPostorder(node.left);
        printPostorder(node.right);
        System.out.print(node.key + " ");
    }

    public static void main(String[] args) {
        TreeCreation treeCreation = new TreeCreation();
//        treeCreation.root = new Node(1);
//        treeCreation.root.left = new Node(2);
//        treeCreation.root.right = new Node(3);
//        treeCreation.root.left.left = new Node(4);
//        treeCreation.root.left.right = new Node(5);
//        treeCreation.root.right.left = new Node(6);
//        treeCreation.root.right.right = new Node(7);


        treeCreation.root = new Node(8);
        treeCreation.root.left = new Node(3);
        treeCreation.root.right = new Node(10);
        treeCreation.root.left.left = new Node(1);
        treeCreation.root.left.right = new Node(6);
        treeCreation.root.left.right.left = new Node(4);
        treeCreation.root.left.right.right = new Node(7);
        treeCreation.root.right.right = new Node(14);
        treeCreation.root.right.right.left = new Node(13);

        System.out.println("Inorder : ");
        treeCreation.printInorder(treeCreation.root);
        System.out.println("\nPreorder : ");
        treeCreation.printPreorder(treeCreation.root);
        System.out.println("\nPostorder : ");
        treeCreation.printPostorder(treeCreation.root);

        TreeCreation treeCreation1 = new TreeCreation();
        treeCreation1.root = new Node(3);
        treeCreation1.root.left = new Node(1);
        treeCreation1.root.right = new Node(5);
        treeCreation1.root.left.left = new Node(6);
        treeCreation1.root.left.right = new Node(2);
        treeCreation1.root.left.right.right = new Node(8);
        treeCreation1.root.right.right = new Node(9);
        treeCreation1.root.right.left = new Node(4);

        System.out.println("Inorder : ");
        treeCreation.printInorder(treeCreation1.root);

    }

}

//class Node {
//    int key;
//    Node left,right;
//    public Node (int key) {
//        this.key = key;
//        left = right = null;
//    }
//}
